package com._520it.wms.web.action;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.query.SystemMenuQueryObject;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.util.RequiredPermission;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemMenuAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Setter
	private ISystemMenuService systemMenuService;
	@Getter
	private SystemMenu systemMenu = new SystemMenu();

	@Getter
	private SystemMenuQueryObject qo = new SystemMenuQueryObject();

	@RequiredPermission("系统菜单列表")
	@InputConfig(methodName = "input")
	@Override
	public String execute() throws Exception {
		try {
			if (qo.getParentId()>0){
				putContext("menus", systemMenuService.queryMenusByParentId(qo.getParentId()));
			}
			putContext("pageResult", systemMenuService.query(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return LIST;
	}

	@RequiredPermission("系统菜单编辑")
	//进入录入界面
	public String input() throws Exception {
		if (qo.getParentId() < 0){
			putContext("parentName","根菜单");
		}else {
			putContext("parentName",systemMenuService.get(qo.getParentId()).getName());
		}

		if (systemMenu.getId() != null){
			systemMenu = systemMenuService.get(systemMenu.getId());
		}
		return INPUT;
	}

	@RequiredPermission("系统菜单删除")
	//删除操作
	public String delete() throws Exception {
		try {
			if (systemMenu.getId() != null){
				systemMenuService.delete(systemMenu.getId());
				putResponseText("删除成功!");
			}
		}catch (Exception e){
			e.printStackTrace();
			putResponseText(e.getMessage());
		}
		return NONE;
	}

	@RequiredPermission("系统菜单保存或更新")
	//新增或更新操作
	public String saveOrUpdate() throws Exception {
		try {
			if (qo.getParentId() > 0){
				SystemMenu parent = systemMenuService.get(qo.getParentId());
				systemMenu.setParent(parent);
			}
			if (systemMenu.getId() == null){
				systemMenuService.save(systemMenu);
				addActionMessage("保存成功");
			}else {
				systemMenuService.update(systemMenu);
				addActionMessage("修改成功");
			}
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return SUCCESS;
	}

	public String loadMenusByParentSn() throws Exception {
		List<SystemMenu> menus = systemMenuService.queryMenusByParentSn(qo.getParentSn());
		List<Object> jsonList = new ArrayList<>();
		for (SystemMenu menu : menus) {
			jsonList.add(menu.toJson());
		}
		putJson(jsonList);
		return NONE;
	}



	//只会在SaveOrUpdate前执行
	public void prepareSaveOrUpdate() throws Exception {
		if (systemMenu.getId() != null){
			systemMenu = systemMenuService.get(systemMenu.getId());
		}
	}

}
