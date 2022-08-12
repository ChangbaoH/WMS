package com._520it.wms.service;
import com._520it.wms.domain.Permission;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IPermissionService {
	//删除权限
	void delete(Long id);
	//查询所有权限
	List<Permission> listAll();
	//分页查询
	PageResult query(QueryObject qo);
	//加载权限
	void reload();
}
