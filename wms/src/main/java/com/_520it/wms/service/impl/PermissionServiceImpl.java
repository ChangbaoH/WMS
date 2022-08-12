package com._520it.wms.service.impl;

import com._520it.wms.dao.IPermissionDAO;
import com._520it.wms.domain.Permission;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.util.PermissionUtil;
import com._520it.wms.util.RequiredPermission;
import com._520it.wms.web.action.BaseAction;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.*;

/*
 *@Auther:HCB
 *@Date:2022/5/28
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
public class PermissionServiceImpl implements IPermissionService, ApplicationContextAware {

    private ApplicationContext ctx;
    public void setApplicationContext(ApplicationContext ctx) throws BeansException{
        this.ctx = ctx;
    }
    @Setter
    private IPermissionDAO permissionDAO;

    @Override
    public void delete(Long id) {
        permissionDAO.delete(id);
    }

    @Override
    public List<Permission> listAll() {
        return permissionDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return permissionDAO.query(qo);
    }

    @Override
    public void reload() {
        //0.查询数据库所有的权限表达式
        List<Permission> list = this.listAll();
        Set<String> exps = new HashSet<>();
        for (Permission permission : list) {
            exps.add(permission.getExpression());
        }


        //1.扫描所有的BaseAction的子类
        Map<String, BaseAction> beansMap = ctx.getBeansOfType(BaseAction.class);
        Collection<BaseAction> actions = beansMap.values();
        //2.迭代每个Action类
        for (BaseAction action:actions) {
            //3.迭代每一个Action类中的方法
            Method[] methods = action.getClass().getDeclaredMethods();
            for (Method m: methods) {
                //4.判断当前方法上是否有RequiredPermission标签
                RequiredPermission rp =  m.getAnnotation(RequiredPermission.class);
                String exp = PermissionUtil.buildExpression(m);
                if (!exps.contains(exp)){
                    if(rp!=null){
                        String name = rp.value();
                        Permission permission = new Permission();
                        permission.setName(name);
                        permission.setExpression(exp);
                        permissionDAO.save(permission);
                    }
                }
            }

        }

    }
}
