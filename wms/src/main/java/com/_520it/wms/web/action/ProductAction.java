package com._520it.wms.web.action;

import com._520it.wms.domain.Product;
import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IProductService;
import com._520it.wms.service.impl.BrandServiceImpl;
import com._520it.wms.util.FileUploadUtil;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

public class ProductAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Setter
	private IProductService productService;
	@Getter
	private Product product = new Product();
	@Setter
	private IBrandService brandService;
	@Getter
	private ProductQueryObject qo = new ProductQueryObject();
	@Setter
	private File pic;//上传文件
	@Setter
	private String picFileName;//上传文件的原始名称

	@RequiredPermission("货品列表")
	@InputConfig(methodName = "input")
	@Override
	public String execute() throws Exception {
		try {
			putContext("brands",brandService.listAll());
			//putContext("Products", ProductService.query(qo));
			putContext("pageResult", productService.query(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return LIST;
	}

	@RequiredPermission("货品编辑")
	//进入录入界面
	public String input() throws Exception {
		putContext("brands",brandService.listAll());
		if (product.getId() != null){
			product = productService.get(product.getId());
		}
		return INPUT;
	}

	@RequiredPermission("货品删除")
	//删除操作
	public String delete() throws Exception {
		if (product.getId() != null){
			product = productService.get(product.getId());
			if (product.getImagePath() != null){
				FileUploadUtil.deleteFile(product.getImagePath());
			}
			productService.delete(product.getId());
		}
		return NONE;
	}

	@RequiredPermission("货品保存或更新")
	//新增或更新操作
	public String saveOrUpdate() throws Exception {
		try {
			//更新操作时，删除之前的图片
			if (product.getId()!= null && pic!= null && product.getSmallImagePath()!=null){
				FileUploadUtil.deleteFile(product.getImagePath());
			}
			if (pic != null){
				//返回图片保存的路径
				String savePath = FileUploadUtil.uploadFile(pic,picFileName);
				product.setImagePath(savePath);//设置货品保存的路径
			}
			if (product.getId() == null){
				productService.save(product);
				addActionMessage("保存成功");
			}else {
				productService.update(product);
				addActionMessage("修改成功");
			}
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return SUCCESS;
	}


	@RequiredPermission("货品选择列表")
	public String selectProductList() throws Exception {
		putContext("brands",brandService.listAll());
		//putContext("Products", ProductService.query(qo));
		qo.setPageSize(20);
		putContext("pageResult", productService.query(qo));
		return "selectProductList";
	}

	//只会在SaveOrUpdate前执行
	public void prepareSaveOrUpdate() throws Exception {
		if (product.getId() != null){
			product = productService.get(product.getId());
			product.setBrand(null);
		}
	}

}
