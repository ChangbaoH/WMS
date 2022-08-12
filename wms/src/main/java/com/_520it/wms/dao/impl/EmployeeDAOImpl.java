package com._520it.wms.dao.impl;

import com._520it.wms.dao.IEmployeeDAO;
import com._520it.wms.domain.Employee;
import com._520it.wms.util.MD5;

public class EmployeeDAOImpl extends GenericDAOImpl<Employee> implements IEmployeeDAO {


    @Override
    public Employee checkLogin(String username, String password) {
        return super.queryForObject("obj.name = ? AND obj.password = ?",username,password);
    }
}
