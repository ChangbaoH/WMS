package com._520it.wms.web.action;

import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.query.StockOutcomeBillQueryObject;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IStockOutcomeBillService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class StockOutcomeBillAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Setter
	private IStockOutcomeBillService stockOutcomeBillService;
	@Getter
	private StockOutcomeBill stockOutcomeBill = new StockOutcomeBill();
	@Setter
	private IDepotService depotService;
	@Setter
	private IClientService clientService;
	@Getter
	private StockOutcomeBillQueryObject qo = new StockOutcomeBillQueryObject();

	@RequiredPermission("销售出库单列表")
	@InputConfig(methodName = "input")
	@Override
	public String execute() throws Exception {
		try {
			putContext("depots",depotService.listAll());
			putContext("clients", clientService.listAll());
			putContext("pageResult", stockOutcomeBillService.query(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return LIST;
	}

	@RequiredPermission("销售出库单编辑")
	//进入录入界面
	public String input() throws Exception {
		putContext("depots",depotService.listAll());
		putContext("clients", clientService.listAll());
		if (stockOutcomeBill.getId() != null){
			stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
		}
		return INPUT;
	}

	@RequiredPermission("销售出库单删除")
	//删除操作
	public String delete() throws Exception {
		if (stockOutcomeBill.getId() != null){
			stockOutcomeBillService.delete(stockOutcomeBill.getId());
		}
		return NONE;
	}

	@RequiredPermission("销售出库单保存或更新")
	//新增或更新操作
	public String saveOrUpdate() throws Exception {
		try {
			if (stockOutcomeBill.getId() == null){
				stockOutcomeBillService.save(stockOutcomeBill);
				addActionMessage("保存成功");
			}else {
				stockOutcomeBillService.update(stockOutcomeBill);
				addActionMessage("修改成功");
			}
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return SUCCESS;
	}

	@RequiredPermission("销售出库单审核")
	public String audit(){
		try {
			if (stockOutcomeBill.getId() != null) {
				stockOutcomeBillService.audit(stockOutcomeBill.getId());
				addActionMessage("审核成功!");
			}
		}catch (Exception e){
			e.printStackTrace();
			addActionMessage(e.getMessage());
		}
		return SUCCESS;
	}

	//只会在SaveOrUpdate前执行
	public void prepareSaveOrUpdate() throws Exception {
		if (stockOutcomeBill.getId() != null){
			stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
			stockOutcomeBill.setDepot(null);
			stockOutcomeBill.setClient(null);
			stockOutcomeBill.getItems().clear();
		}
	}

}
