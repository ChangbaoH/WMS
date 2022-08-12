package com._520it.wms.web.action;

import com._520it.wms.domain.Permission;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class PermissionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Setter
	private IPermissionService permissionService;

	@Getter
	private Permission permission = new Permission();

	@Getter
	private QueryObject qo = new QueryObject();

	@RequiredPermission("权限列表")
	@InputConfig(methodName = "input")
	@Override
	public String execute() throws Exception {
		//putContext("Permissions", PermissionService.query(qo));
		putContext("pageResult",permissionService.query(qo));
		return LIST;
	}

	@RequiredPermission("权限删除")
	//删除操作
	public String delete() throws Exception {
		if (permission.getId() != null){
			permissionService.delete(permission.getId());
		}
		return SUCCESS;
	}

	@RequiredPermission("权限加载")
	//加载权限
	public String reload() throws Exception {
		permissionService.reload();
		return NONE;
	}


}
