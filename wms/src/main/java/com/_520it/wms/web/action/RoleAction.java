package com._520it.wms.web.action;

import com._520it.wms.domain.Role;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class RoleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Setter
	private IRoleService roleService;
	@Setter
	private IPermissionService permissionService;
	@Setter
	private ISystemMenuService systemMenuService;
	@Getter
	private Role role = new Role();

	@Getter
	private QueryObject qo = new QueryObject();

	@RequiredPermission("角色列表")
	@InputConfig(methodName = "input")
	@Override
	public String execute() throws Exception {
		try {
			//putContext("Roles", RoleService.query(qo));
			putContext("pageResult", roleService.query(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return LIST;
	}

	@RequiredPermission("角色编辑")
	//进入录入界面
	public String input() throws Exception {
		putContext("permissions",permissionService.listAll());
		putContext("menus",systemMenuService.queryChildrenMenus());
		if (role.getId() != null){
			role = roleService.get(role.getId());
		}
		return INPUT;
	}

	@RequiredPermission("角色删除")
	//删除操作
	public String delete() throws Exception {
		if (role.getId() != null){
			roleService.delete(role.getId());
		}
		return SUCCESS;
	}

	@RequiredPermission("角色保存或更新")
	//新增或更新操作
	public String saveOrUpdate() throws Exception {
		try {
			if (role.getId() == null){
				roleService.save(role);
				addActionMessage("保存成功");
			}else {
				roleService.update(role);
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
		if (role.getId() != null){
			role = roleService.get(role.getId());
		}
		role.getPermissions().clear();
		role.getMenus().clear();
	}

}
