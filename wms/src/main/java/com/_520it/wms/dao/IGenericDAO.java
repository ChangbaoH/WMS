package com._520it.wms.dao;


import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

/*
 *@Auther:HCB
 *@Date:2022/5/27
 *@Description:泛型DAO接口，所有接口的父接口
 *Version:1.0
 */
public interface IGenericDAO <T> {
    void save(T obj);
    void update(T obj);
    void delete(Long id);

    T get(Long id);
    List<T> listAll();
    PageResult query(QueryObject qo);
    /*MethodDescription:查询符合条件的多条数据,留给DAO子类使用
    *@param:condition,如obj.name LIKE ?
    *@param:params,参数
    *@param:currentPage,当前页
    *@param:pageSize,页大小
    *@return:查询结果
    *@auther:HCB
    *@date:2022/5/28
    */
    List<T> queryForList(String condition, Object[] params, int currentPage, int pageSize);
    List<T> queryForList(String condition, Object... params);

    /*MethodDescription查询单个结果
    *@param:
    *@return:
    *@auther:HCB
    *@date:
    */
    T queryForObject(String condition, Object... params);

    void batchDelete(List<Long> ids);
}
