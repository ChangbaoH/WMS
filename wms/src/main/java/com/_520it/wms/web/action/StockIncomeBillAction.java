package com._520it.wms.web.action;

import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.query.StockIncomeBillQueryObject;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IStockIncomeBillService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class StockIncomeBillAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Setter
	private IStockIncomeBillService stockIncomeBillService;
	@Getter
	private StockIncomeBill stockIncomeBill = new StockIncomeBill();
	@Setter
	private IDepotService depotService;
	@Getter
	private StockIncomeBillQueryObject qo = new StockIncomeBillQueryObject();

	@RequiredPermission("到货入库单列表")
	@InputConfig(methodName = "input")
	@Override
	public String execute() throws Exception {
		try {
			putContext("depots",depotService.listAll());
			//putContext("StockIncomeBills", StockIncomeBillService.query(qo));
			putContext("pageResult", stockIncomeBillService.query(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return LIST;
	}

	@RequiredPermission("到货入库单编辑")
	//进入录入界面
	public String input() throws Exception {
		putContext("depots",depotService.listAll());
		if (stockIncomeBill.getId() != null){
			stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
		}
		return INPUT;
	}

	@RequiredPermission("到货入库单删除")
	//删除操作
	public String delete() throws Exception {
		if (stockIncomeBill.getId() != null){
			stockIncomeBillService.delete(stockIncomeBill.getId());
		}
		return NONE;
	}

	@RequiredPermission("到货入库单保存或更新")
	//新增或更新操作
	public String saveOrUpdate() throws Exception {
		try {
			if (stockIncomeBill.getId() == null){
				stockIncomeBillService.save(stockIncomeBill);
				addActionMessage("保存成功");
			}else {
				stockIncomeBillService.update(stockIncomeBill);
				addActionMessage("修改成功");
			}
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return SUCCESS;
	}

	@RequiredPermission("到货入库单审核")
	public String audit(){
		if (stockIncomeBill.getId() != null){
			stockIncomeBillService.audit(stockIncomeBill.getId());
			addActionMessage("审核成功!");
		}
		return SUCCESS;
	}

	//只会在SaveOrUpdate前执行
	public void prepareSaveOrUpdate() throws Exception {
		if (stockIncomeBill.getId() != null){
			stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
			stockIncomeBill.setDepot(null);
			stockIncomeBill.getItems().clear();
		}
	}

}
