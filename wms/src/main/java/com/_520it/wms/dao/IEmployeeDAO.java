package com._520it.wms.dao;

import com._520it.wms.domain.Employee;

import java.util.List;

public interface IEmployeeDAO extends IGenericDAO<Employee>{
    Employee checkLogin(String username, String password);

}
