package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

/*
 *@Auther:HCB
 *@Date:2022/5/28
 *@Description:权限对象
 *Version:1.0
 */
@Getter@Setter
public class Permission extends BaseDomain{
    private String name;//权限名,如员工删除
    private String expression;//权限表达式,如com._520it.ssh.web.action.EmployeeAction:delete

}
