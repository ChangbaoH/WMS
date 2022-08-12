package com._520it.wms.service;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.vo.SystemMenuVO;

import java.util.List;

public interface ISystemMenuService {
	void save(SystemMenu systemMenu);
	void update(SystemMenu systemMenu);
	void delete(Long id);
	
	SystemMenu get(Long id);
	List<SystemMenu> listAll();

	PageResult query(QueryObject qo);

	//查询多级父级菜单列表
    List<SystemMenuVO> queryMenusByParentId(Long parentId);

	List<SystemMenu> queryChildrenMenus();

	/*
	 * @Author: HCB
	 * @Description:  根据当前登录用户的角色和父级菜单的sn下的查询所有的子菜单
	 * @Date: 2022/6/21
	 * @Parms:String parentSn
	 * @ReturnType: List<SystemMenu>
	 */
	List<SystemMenu> queryMenusByParentSn(String parentSn);
}
