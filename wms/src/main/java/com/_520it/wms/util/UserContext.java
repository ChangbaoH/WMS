package com._520it.wms.util;

import com._520it.wms.domain.Employee;
import com.opensymphony.xwork2.ActionContext;

import java.util.Set;

/*
 *@Auther:HCB
 *@Date:2022/5/29
 *@Description:用户上下文
 *Version:1.0
 */
public class UserContext {

    private static final String USER_IN_SESSION = "user_in_session";
    private static final String PERMISSION_IN_SESSION = "permissions_in_session";

    //保存当前用户到session中
    public static void setEmployee(Employee employee) {
        if (employee != null){
            ActionContext.getContext().getSession().put(USER_IN_SESSION, employee);
        }else {
            ActionContext.getContext().getSession().clear();
        }
    }

    //获取当前用户
    public static Employee getCurrentEmployee(){
        return (Employee) ActionContext.getContext().getSession().get(USER_IN_SESSION);
    }
    //获取当前用户的权限表达式
    public static Set<String> getCurrentExpressions(){
        return (Set<String>) ActionContext.getContext().getSession().get(PERMISSION_IN_SESSION);
    }

    //保存当前用户所有的权限表达式
    public static void setPermissions(Set<String> permissionSet) {

        ActionContext.getContext().getSession().put(PERMISSION_IN_SESSION, permissionSet);
    }
}
