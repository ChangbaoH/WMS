package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *@Auther:HCB
 *@Date:2022/6/15
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
//系统菜单
@Getter
@Setter
public class SystemMenu extends BaseDomain implements IJsonObject{

    private String name;//菜单名称
    private String url;//菜单链接，子菜单拥有，父菜单没有
    private String sn;//菜单编号，父菜单有，子菜单没有，用于加载自己的子菜单

    private SystemMenu parent;//父菜单，many2one
    private List<SystemMenu> children = new ArrayList<>();

    @Override
    public Object toJson() {
        Map<String,Object> json = new HashMap<>();
        json.put("id",id);
        json.put("name",name);
        json.put("pId",this.parent!=null? this.parent.getId():null);
        json.put("action",url);

        return json;
    }
}
