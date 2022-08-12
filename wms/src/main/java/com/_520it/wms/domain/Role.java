package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
 *@Auther:HCB
 *@Date:2022/5/28
 *@Description:角色对象
 *Version:1.0
 */
@Getter@Setter
public class Role extends BaseDomain{
    private String name;//角色名称
    private String sn;//角色编码
    //一个角色拥有多个权限，一个权限也可以被赋值给多个角色,单向多对多
    private List<Permission> permissions = new ArrayList<>();
    //一个角色拥有多个菜单，一个菜单也可以被赋值给多个角色,单向多对多
    private List<SystemMenu> menus = new ArrayList<>();

}
