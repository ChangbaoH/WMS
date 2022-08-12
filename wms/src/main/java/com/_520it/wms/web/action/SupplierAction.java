package com._520it.wms.web.action;

import com._520it.wms.domain.Supplier;
import com._520it.wms.query.SupplierQueryObject;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class SupplierAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Setter
	private ISupplierService supplierService;
	@Getter
	private Supplier supplier = new Supplier();

	@Getter
	private SupplierQueryObject qo = new SupplierQueryObject();

	@RequiredPermission("供应商列表")
	@InputConfig(methodName = "input")
	@Override
	public String execute() throws Exception {
		try {
			//putContext("Suppliers", SupplierService.query(qo));
			putContext("pageResult", supplierService.query(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return LIST;
	}

	@RequiredPermission("供应商编辑")
	//进入录入界面
	public String input() throws Exception {
		if (supplier.getId() != null){
			supplier = supplierService.get(supplier.getId());
		}
		return INPUT;
	}

	@RequiredPermission("供应商删除")
	//删除操作
	public String delete() throws Exception {
		if (supplier.getId() != null){
			supplierService.delete(supplier.getId());
		}
		return NONE;
	}

	@RequiredPermission("供应商保存或更新")
	//新增或更新操作
	public String saveOrUpdate() throws Exception {
		try {
			if (supplier.getId() == null){
				supplierService.save(supplier);
				addActionMessage("保存成功");
			}else {
				supplierService.update(supplier);
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
		if (supplier.getId() != null){
			supplier = supplierService.get(supplier.getId());
		}
	}

}
