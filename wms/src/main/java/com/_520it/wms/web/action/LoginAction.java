package com._520it.wms.web.action;

import com._520it.wms.service.IEmployeeService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Setter;

/*
 *@Auther:HCB
 *@Date:2022/5/29
 *@Description:登录
 *Version:1.0
 */
public class LoginAction extends BaseAction {

    @Setter
    private String username;
    @Setter
    private String password;

    @Setter
    private IEmployeeService employeeService;

    @InputConfig(resultName = "login")
    public String execute(){
        try {
            employeeService.checkLogin(username,password);
        } catch (Exception e){
            super.addActionError(e.getMessage());
            return LOGIN;
        }

        return SUCCESS;
    }
}
