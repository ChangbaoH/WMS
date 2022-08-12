package com._520it.wms.web.action;

import com._520it.wms.domain.Depot;
import com._520it.wms.query.DepotQueryObject;
import com._520it.wms.service.IDepotService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class DepotAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Setter
	private IDepotService depotService;
	@Getter
	private Depot depot = new Depot();

	@Getter
	private DepotQueryObject qo = new DepotQueryObject();

	@RequiredPermission("仓库列表")
	@InputConfig(methodName = "input")
	@Override
	public String execute() throws Exception {
		try {
			//putContext("Depots", DepotService.query(qo));
			putContext("pageResult", depotService.query(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return LIST;
	}

	@RequiredPermission("仓库编辑")
	//进入录入界面
	public String input() throws Exception {
		if (depot.getId() != null){
			depot = depotService.get(depot.getId());
		}
		return INPUT;
	}

	@RequiredPermission("仓库删除")
	//删除操作
	public String delete() throws Exception {
		if (depot.getId() != null){
			depotService.delete(depot.getId());
		}
		return NONE;
	}

	@RequiredPermission("仓库保存或更新")
	//新增或更新操作
	public String saveOrUpdate() throws Exception {
		try {
			if (depot.getId() == null){
				depotService.save(depot);
				addActionMessage("保存成功");
			}else {
				depotService.update(depot);
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
		if (depot.getId() != null){
			depot = depotService.get(depot.getId());
		}
	}

}
