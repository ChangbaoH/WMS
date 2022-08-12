package com._520it.wms.service.impl;

import com._520it.wms.dao.ISystemMenuDAO;
import com._520it.wms.domain.SystemMenu;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.query.SystemMenuQueryObject;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.util.UserContext;
import com._520it.wms.vo.SystemMenuVO;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 *@Auther:HCB
 *@Date:2022/5/29
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
public class SystemMenuServiceImpl implements ISystemMenuService {

    @Setter
    private ISystemMenuDAO systemMenuDAO;

    @Override
    public void save(SystemMenu r) {
        systemMenuDAO.save(r);
    }

    @Override
    public void update(SystemMenu r) {
        systemMenuDAO.update(r);
    }

    @Override
    public void delete(Long id) {
        SystemMenuQueryObject qo = new SystemMenuQueryObject();
        qo.setParentId(id);
        PageResult  pageResult = systemMenuDAO.query(qo);
        if (pageResult.getTotalCount()>0){
            throw new RuntimeException("该对象正在被使用！");
        }
        systemMenuDAO.delete(id);
    }

    @Override
    public SystemMenu get(Long id) {
        return systemMenuDAO.get(id);
    }

    @Override
    public List<SystemMenu> listAll() {
        return systemMenuDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return systemMenuDAO.query(qo);
    }

    @Override
    public List<SystemMenuVO> queryMenusByParentId(Long parentId) {
        List<SystemMenuVO> menus = new ArrayList<>();
        SystemMenu currentParent = systemMenuDAO.get(parentId);
        listParents(menus,currentParent);
        Collections.reverse(menus);
        return menus;
    }

    @Override
    public List<SystemMenu> queryChildrenMenus() {
        return systemMenuDAO.queryChildrenMenus();
    }

    @Override
    public List<SystemMenu> queryMenusByParentSn(String parentSn) {
        if (UserContext.getCurrentEmployee().isAdmin()){
            return systemMenuDAO.queryMenusByParentSn(parentSn);
        }else {
            return systemMenuDAO.queryMenusByParentSnAndRole(parentSn,UserContext.getCurrentEmployee().getRoles());
        }

    }

    //递归查询父菜单
    private void listParents(List<SystemMenuVO> menus, SystemMenu currentParent) {
        if (currentParent != null){
            SystemMenuVO vo = new SystemMenuVO();
            vo.setId(currentParent.getId());
            vo.setName(currentParent.getName());
            menus.add(vo);
            listParents(menus,currentParent.getParent());
        }
    }
}
