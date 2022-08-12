package com._520it.wms.dao.impl;

import com._520it.wms.dao.ISystemMenuDAO;
import com._520it.wms.domain.Role;
import com._520it.wms.domain.SystemMenu;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;
import java.util.Queue;

public class SystemMenuDAOImpl extends GenericDAOImpl<SystemMenu> implements ISystemMenuDAO {


    @Override
    public List<SystemMenu> queryChildrenMenus() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT obj FROM SystemMenu obj WHERE obj.parent IS NOT NULL";
        Query query = session.createQuery(hql);
        return query.list();
    }

    @Override
    public List<SystemMenu> queryMenusByParentSn(String parentSn) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT obj FROM SystemMenu obj WHERE obj.parent.sn = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,parentSn);
        return query.list();
    }

    @Override
    public List<SystemMenu> queryMenusByParentSnAndRole(String parentSn, List<Role> roles) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT m FROM Role r JOIN r.menus m WHERE m.parent.sn = :parentSn AND r IN (:roles)";
        Query query = session.createQuery(hql);
        query.setParameter("parentSn",parentSn);
        query.setParameterList("roles",roles);
        return query.list();
    }

}
