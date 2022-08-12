package com._520it.wms.service.impl;

import com._520it.wms.dao.IEmployeeDAO;
import com._520it.wms.domain.Employee;
import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.util.MD5;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeServiceImpl implements IEmployeeService {

	@Setter
	private IEmployeeDAO employeeDAO;
	
	@Override
	public void save(Employee e) {
		//密码加密
		e.setPassword(MD5.encode(e.getPassword()));
		employeeDAO.save(e);
		
	}

	@Override
	public void update(Employee e) {
		employeeDAO.update(e);
		
	}

	@Override
	public void delete(Long id) {
		employeeDAO.delete(id);
		
	}

	@Override
	public Employee get(Long id) {		
		return employeeDAO.get(id);
		
	}

	@Override
	public List<Employee> listAll() {		
		return employeeDAO.listAll();
		
	}


	@Override
	public PageResult query(QueryObject qo) {
		return employeeDAO.query(qo);
	}

	@Override
	public void checkLogin(String username, String password) {
		//1.查询当前的账户
		Employee employee = employeeDAO.checkLogin(username,MD5.encode(password));
		if (employee == null){
			throw new RuntimeException("亲，账号或者密码有误！");
		}
		//2.把账户密码存储在session中
		UserContext.setEmployee(employee);
		//3.把当前用户的权限查询出来并存储到session中
		if (!employee.isAdmin()){
			Set<String> permissionSet = new HashSet<>();//存放当前用户的所以权限表达式
			List<Role> roles = employee.getRoles();
			for (Role role : roles) {
				List<Permission> permissions = role.getPermissions();
				for (Permission p : permissions) {
					permissionSet.add(p.getExpression());
				}
			}
			UserContext.setPermissions(permissionSet);
		}
	}

    @Override
    public void batchDelete(List<Long> ids) {
        employeeDAO.batchDelete(ids);
    }
}
