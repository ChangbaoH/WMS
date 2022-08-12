package com._520it.wms.web.interceptor;

import com._520it.wms.domain.Employee;
import com._520it.wms.util.UserContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/*
 *@Auther:HCB
 *@Date:2022/5/29
 *@Description:登陆检查拦截器
 *Version:1.0
 */
public class CheckLoginInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Employee current = UserContext.getCurrentEmployee();
        if (current == null){
            return Action.LOGIN;
        }
        return actionInvocation.invoke();
    }
}
