package com._520it.wms.web.action;

import com._520it.wms.domain.Department;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

/*
 *@Auther:HCB
 *@Date:2022/5/26
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
public class DepartmentAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Setter
    private IDepartmentService departmentService;

    @Getter
    private Department department = new Department();

    @Getter
    private QueryObject qo = new QueryObject();

    @RequiredPermission("部门列表")
    @InputConfig(methodName = "input")
    //查询列表
    public String execute() throws Exception {
        try {
            putContext("pageResult",departmentService.query(qo));
        }catch (Exception e){
           e.printStackTrace();
           addActionError(e.getMessage());
        }

        return LIST;
    }

    @RequiredPermission("部门编辑")
    //进入录入界面
    public String input() throws Exception {
        if (department.getId() != null){
            department = departmentService.get(department.getId());
        }
        return INPUT;
    }

    @RequiredPermission("部门删除")
    //删除操作
    public String delete() throws Exception {
        if (department.getId() != null){
            departmentService.delete(department.getId());
        }
        return SUCCESS;
    }

    @RequiredPermission("部门保存或更新")
    //新增或更新操作
    public String saveOrUpdate() throws Exception {
        try {
            if (department.getId() == null){
                departmentService.save(department);
                addActionMessage("保存成功");
            }else {
                departmentService.update(department);
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
        if (department.getId() != null){
            department = departmentService.get(department.getId());
        }
    }
}
