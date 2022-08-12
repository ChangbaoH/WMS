package com._520it.wms.web.action;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.query.OrderBillQueryObject;
import com._520it.wms.service.IOrderBillService;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class OrderBillAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Setter
	private IOrderBillService orderBillService;
	@Getter
	private OrderBill orderBill = new OrderBill();
	@Setter
	private ISupplierService supplierService;
	@Getter
	private OrderBillQueryObject qo = new OrderBillQueryObject();

	@RequiredPermission("采购订单列表")
	@InputConfig(methodName = "input")
	@Override
	public String execute() throws Exception {
		try {
			putContext("suppliers",supplierService.listAll());
			//putContext("OrderBills", OrderBillService.query(qo));
			putContext("pageResult", orderBillService.query(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return LIST;
	}

	@RequiredPermission("采购订单编辑")
	//进入录入界面
	public String input() throws Exception {
		putContext("suppliers",supplierService.listAll());
		if (orderBill.getId() != null){
			orderBill = orderBillService.get(orderBill.getId());
		}
		return INPUT;
	}

	@RequiredPermission("采购订单删除")
	//删除操作
	public String delete() throws Exception {
		if (orderBill.getId() != null){
			orderBillService.delete(orderBill.getId());
		}
		return NONE;
	}

	@RequiredPermission("采购订单保存或更新")
	//新增或更新操作
	public String saveOrUpdate() throws Exception {
		try {
			if (orderBill.getId() == null){
				orderBillService.save(orderBill);
				addActionMessage("保存成功");
			}else {
				orderBillService.update(orderBill);
				addActionMessage("修改成功");
			}
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return SUCCESS;
	}

	@RequiredPermission("采购订单审核")
	public String audit(){
		if (orderBill.getId() != null){
			orderBillService.audit(orderBill.getId());
			addActionMessage("审核成功!");
		}
		return SUCCESS;
	}

	@RequiredPermission("采购订单查看")
	//删除操作
	public String show() throws Exception {
		if (orderBill.getId() != null){
			orderBill = orderBillService.get(orderBill.getId());
		}
		return "show";
	}
	//只会在SaveOrUpdate前执行
	public void prepareSaveOrUpdate() throws Exception {
		if (orderBill.getId() != null){
			orderBill = orderBillService.get(orderBill.getId());
			orderBill.setSupplier(null);
			orderBill.getItems().clear();//打破明细关系
		}
	}

}
