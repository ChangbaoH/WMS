package com._520it.wms.web.action;

import com._520it.wms.domain.Client;
import com._520it.wms.query.ClientQueryObject;
import com._520it.wms.service.IClientService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class ClientAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Setter
	private IClientService clientService;
	@Getter
	private Client client = new Client();

	@Getter
	private ClientQueryObject qo = new ClientQueryObject();

	@RequiredPermission("客户列表")
	@InputConfig(methodName = "input")
	@Override
	public String execute() throws Exception {
		try {
			//putContext("Clients", ClientService.query(qo));
			putContext("pageResult", clientService.query(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return LIST;
	}

	@RequiredPermission("客户编辑")
	//进入录入界面
	public String input() throws Exception {
		if (client.getId() != null){
			client = clientService.get(client.getId());
		}
		return INPUT;
	}

	@RequiredPermission("客户删除")
	//删除操作
	public String delete() throws Exception {
		if (client.getId() != null){
			clientService.delete(client.getId());
		}
		return NONE;
	}

	@RequiredPermission("客户保存或更新")
	//新增或更新操作
	public String saveOrUpdate() throws Exception {
		try {
			if (client.getId() == null){
				clientService.save(client);
				addActionMessage("保存成功");
			}else {
				clientService.update(client);
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
		if (client.getId() != null){
			client = clientService.get(client.getId());
		}
	}

}
