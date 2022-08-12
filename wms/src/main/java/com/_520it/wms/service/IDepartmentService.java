package com._520it.wms.service;

import com._520it.wms.domain.Department;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IDepartmentService {
	void save(Department d);
	void update(Department d);
	void delete(Long id);

	Department get(Long id);
	List<Department> listAll();
	PageResult query(QueryObject qo);
}
