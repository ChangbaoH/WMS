package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *@Auther:HCB
 *@Date:2022/5/27
 *@Description:高级查询对象的基类
 *Version:1.0
 */
public class QueryObject {

    @Getter
    @Setter
    private int currentPage = 1;
    @Getter@Setter
    private int pageSize = 5;

    private List<String> conditions = new ArrayList<>();//查询条件
    private List<Object> params = new ArrayList<>();//查询参数

    private boolean build = false;//是否拼接sql

    private void Init(){
        if (!build){
            this.customizedQuery();
            build = true;
        }

    }

    //返回带查询条件的HQL
    public String getQuery() {
        Init();
        if (conditions.size()==0){
            return "";
        }

        return " WHERE " + StringUtils.join(conditions," AND ");
    }


    //返回查询参数
    public List<Object> getParameters() {
        Init();
        return this.params;
    }


    //暴露给子类，设置查询条件
    protected void customizedQuery() {

    }

    //
    protected void addQuery(String condition,Object... args){
        this.conditions.add(condition);
        this.params.addAll(Arrays.asList(args));
    }

    //p判断是否为空
    protected boolean hasLength(String s){
        return s!=null && !"".equals(s.trim());
    }
}
