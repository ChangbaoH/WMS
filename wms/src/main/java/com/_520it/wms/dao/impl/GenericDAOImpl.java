package com._520it.wms.dao.impl;

import com._520it.wms.dao.IGenericDAO;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/*
 *@Auther:HCB
 *@Date:2022/5/27
 *@Description:泛型DAO接口的实现类
 *Version:1.0
 */
public class GenericDAOImpl <T> implements IGenericDAO<T> {

    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Class<T> targetType;

    public GenericDAOImpl(){
        //获取DAO实现类的带泛型信息的父类
        ParameterizedType pType = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取带带泛型父类的泛型参数
        targetType = (Class<T>) pType.getActualTypeArguments()[0];
    }

    @Override
    public void save(T obj) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(obj);
    }

    @Override
    public void update(T obj) {
        Session session = sessionFactory.getCurrentSession();
        session.update(obj);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();

        T obj = (T) session.get(targetType, id);
        session.delete(obj);
    }

    @Override
    public T get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.get(targetType, id);
    }

    @Override
    public List<T> listAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(targetType).list();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int pageSize = qo.getPageSize();
        int currentPage = qo.getCurrentPage();
        Session session = sessionFactory.getCurrentSession();
        //查询结果总数
        String countHql = "SELECT COUNT(obj) FROM "+ targetType.getSimpleName() +" obj" + qo.getQuery();
        Query query = session.createQuery(countHql);
        //设置占位符参数
        setPlaceParameters(qo, query);
        int totalCount = ((Long) query.uniqueResult()).intValue();
        if (totalCount == 0){
            return PageResult.empty(pageSize);
        }
        //查询结果集
        String resultHql = "SELECT obj FROM "+ targetType.getSimpleName() +" obj" + qo.getQuery();
        query = session.createQuery(resultHql);
        //设置占位符参数
        setPlaceParameters(qo, query);
        if (currentPage > 0 && pageSize >0){
            query.setFirstResult((currentPage-1)*pageSize);
            query.setMaxResults(pageSize);
        }

        List<T> results = query.list();
        return new PageResult(totalCount,results,currentPage,pageSize);
    }

    @Override
    public List<T> queryForList(String condition, Object[] params, int currentPage, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sb = new StringBuilder(80);
        sb.append("SELECT obj FROM "+ targetType.getSimpleName() +" obj");
        //设置条件
        if (params!=null && params.length>0){
            sb.append(" WHERE " + condition);
        }
        Query query = session.createQuery(sb.toString());
        //设置参数
        if (params!=null){
            for (int i = 0; i < params.length; i++){
                query.setParameter(i, params[i]);
            }
        }
        //设置分页
        if (currentPage > 0 && pageSize >0){
            query.setFirstResult((currentPage-1)*pageSize);
            query.setMaxResults(pageSize);
        }
        return query.list();
    }

    @Override
    public List<T> queryForList(String condition, Object[] params) {
        return queryForList(condition,params,-1,-1);
    }

    @Override
    public T queryForObject(String condition, Object... params) {
        List<T> list = queryForList(condition,params);
        return list.size()==1? list.get(0):null;
    }

    //把queryObject中的参数设置到query中
    private void setPlaceParameters(QueryObject qo, Query query) {
        for (int i = 0; i < qo.getParameters().size(); i++){
            query.setParameter(i, qo.getParameters().get(i));
        }
    }

    //批量删除
    @Override
    public void batchDelete(List<Long> ids) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "DELETE FROM "+targetType.getSimpleName()+" obj WHERE obj.id IN (:ids)";
        Query query = session.createQuery(hql);
        query.setParameterList("ids",ids);
        query.executeUpdate();
    }

}
