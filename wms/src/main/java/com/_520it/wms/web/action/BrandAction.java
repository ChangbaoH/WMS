package com._520it.wms.web.action;

import com._520it.wms.domain.Brand;
import com._520it.wms.query.BrandQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class BrandAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Setter
	private IBrandService brandService;
	@Getter
	private Brand brand = new Brand();

	@Getter
	private BrandQueryObject qo = new BrandQueryObject();

	@RequiredPermission("品牌列表")
	@InputConfig(methodName = "input")
	@Override
	public String execute() throws Exception {
		try {
			//putContext("Brands", BrandService.query(qo));
			putContext("pageResult", brandService.query(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return LIST;
	}

	@RequiredPermission("品牌编辑")
	//进入录入界面
	public String input() throws Exception {
		if (brand.getId() != null){
			brand = brandService.get(brand.getId());
		}
		return INPUT;
	}

	@RequiredPermission("品牌删除")
	//删除操作
	public String delete() throws Exception {
		if (brand.getId() != null){
			brandService.delete(brand.getId());
		}
		return NONE;
	}

	@RequiredPermission("品牌保存或更新")
	//新增或更新操作
	public String saveOrUpdate() throws Exception {
		try {
			if (brand.getId() == null){
				brandService.save(brand);
				addActionMessage("保存成功");
			}else {
				brandService.update(brand);
				addActionMessage("修改成功");
			}
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return SUCCESS;
	}


	//只会在SaveOrUpdate前执行
	public void prepareSaveOrUpdate() throws Exception {
		if (brand.getId() != null){
			brand = brandService.get(brand.getId());
		}
	}

}
