package com._520it.wms.web.interceptor;

import com._520it.wms.domain.Employee;
import com._520it.wms.util.PermissionUtil;
import com._520it.wms.util.RequiredPermission;
import com._520it.wms.util.UserContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.lang.reflect.Method;
import java.util.Set;


/*
 *@Auther:HCB
 *@Date:2022/5/29
 *@Description:权限检查拦截器
 *Version:1.0
 */
public class SecurityInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Employee current = UserContext.getCurrentEmployee();

        if (current != null){
            //1.判断是否是超级管理员
            if(current.isAdmin()){
                return actionInvocation.invoke();
            }
            //2.判断当前方法是否有标签
            String methodName = actionInvocation.getProxy().getMethod();
            Method actionMethod = actionInvocation.getProxy().getAction().getClass().getDeclaredMethod(methodName);
            RequiredPermission rp = actionMethod.getAnnotation(RequiredPermission.class);
            if(rp == null){
                return actionInvocation.invoke();
            }
            //3.获取当前请求方法的权限表达式
            String exp = PermissionUtil.buildExpression(actionMethod);
            //4.判断当前session中是否存在此表达式
            Set<String> permissions = UserContext.getCurrentExpressions();
            if (permissions.contains(exp)){
                return actionInvocation.invoke();
            }
            return "nopermission";
        }
        return "nopermission";
    }
}
