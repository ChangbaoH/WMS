package com._520it.wms.service.impl;

import com._520it.wms.dao.IDepartmentDAO;
import com._520it.wms.domain.Department;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;
import lombok.Setter;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {

	@Setter
	private IDepartmentDAO departmentDAO;
	
	@Override
	public void save(Department d) {
		departmentDAO.save(d);
	}

	@Override
	public void update(Department d) {
		departmentDAO.update(d);
		
	}

	@Override
	public void delete(Long id) {
		departmentDAO.delete(id);
		
	}

	@Override
	public Department get(Long id) {
		return departmentDAO.get(id);
		
	}

	@Override
	public List<Department> listAll() {
		return departmentDAO.listAll();
		
	}

	@Override
	public PageResult query(QueryObject qo) {
		return departmentDAO.query(qo);
	}

}
