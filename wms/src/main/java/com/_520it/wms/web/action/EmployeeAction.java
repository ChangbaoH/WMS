package com._520it.wms.web.action;

import com._520it.wms.domain.Employee;
import com._520it.wms.query.EmployeeQueryObject;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAction extends BaseAction {

	private static final long serialVersionUID = 1L;



	@Setter
	private IEmployeeService employeeService;

	@Setter
	private IDepartmentService departmentService;

	@Setter
	private IRoleService roleService;

	@Getter
	private Employee employee = new Employee();

	@Setter
	private List<Long> ids = new ArrayList<>();

	@Getter
	private QueryObject qo = new EmployeeQueryObject();

	@RequiredPermission("员工列表")
	//请牢记为什么这个标签要贴在此处,配合store拦截器
	@InputConfig(methodName = "input")
	@Override
	public String execute() throws Exception {
		try {
			putContext("depts",departmentService.listAll());
			//putContext("employees", employeeService.query(qo));
			putContext("pageResult",employeeService.query(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("员工编辑")
	//进入录入界面
	public String input() throws Exception {
		putContext("depts",departmentService.listAll());
		putContext("roles",roleService.listAll());
		if (employee.getId() != null){
			employee = employeeService.get(employee.getId());
		}
		return INPUT;
	}

	@RequiredPermission("员工删除")
	//删除操作
	public String delete() throws Exception {
		if (employee.getId() != null){
			employeeService.delete(employee.getId());
		}
		return NONE;
	}

	@RequiredPermission("员工保存或更新")
	//新增或更新操作
	public String saveOrUpdate() throws Exception {
		try {
			if (employee.getId() == null){
				employeeService.save(employee);
				addActionMessage("保存成功！");
			}else {
				employeeService.update(employee);
				addActionMessage("更改成功！");
			}
		}catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}


	//只会在SaveOrUpdate前执行
	public void prepareSaveOrUpdate() throws Exception {
		if (employee.getId() != null){
			employee = employeeService.get(employee.getId());
		}
		//打破employee和department的关系
		employee.setDept(null);
		employee.getRoles().clear();
	}


	@RequiredPermission("员工批量删除")
	//删除操作
	public String batchDelete() throws Exception {
		if (ids.size() > 0){
			employeeService.batchDelete(ids);
		}
		return NONE;
	}

}
