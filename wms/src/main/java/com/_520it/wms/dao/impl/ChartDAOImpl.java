package com._520it.wms.dao.impl;

import com._520it.wms.dao.IChartDAO;
import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.QueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.vo.OrderChartVO;
import com._520it.wms.vo.OrderGroupByType;
import com._520it.wms.vo.SaleChartVO;
import com._520it.wms.vo.SaleGroupByType;
import lombok.Setter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ChartDAOImpl implements IChartDAO {

    @Setter
    protected SessionFactory sessionFactory;

    @Override
    public List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo) {
        //当前分组的枚举对象
        OrderGroupByType groupByType = OrderGroupByType.valueOf(qo.getGroupType().toUpperCase());
        //OrderGroupByType groupByType = OrderGroupByType.valueOf(qo.getGroupType());

        Session session = sessionFactory.getCurrentSession();
        StringBuilder hql = new StringBuilder(80);
        hql.append("SELECT NEW OrderChartVO(");
        hql.append(groupByType.getGroupValue());

        hql.append(",SUM(obj.number),SUM(obj.amount)) FROM OrderBillItem obj");
        hql.append(qo.getQuery());
        hql.append(" GROUP BY ");
        hql.append(groupByType.getGroupBy());
        Query query = session.createQuery(hql.toString());
        setPlaceParameters(qo,query);//设置占位符参数
        return query.list();
    }

    @Override
    public List<SaleChartVO> querySaleChart(SaleChartQueryObject qo) {
        //当前分组的枚举对象
        SaleGroupByType groupByType = SaleGroupByType.valueOf(qo.getGroupType().toUpperCase());

        Session session = sessionFactory.getCurrentSession();
        StringBuilder hql = new StringBuilder(80);
        hql.append("SELECT NEW SaleChartVO(");
        hql.append(groupByType.getGroupValue());

        hql.append(",SUM(obj.number),SUM(obj.saleAmount),SUM(obj.saleAmount - obj.costAmount)) FROM SaleAccount obj");
        hql.append(qo.getQuery());
        hql.append(" GROUP BY ");
        hql.append(groupByType.getGroupBy());

        System.out.println(hql.toString());

        Query query = session.createQuery(hql.toString());
        setPlaceParameters(qo,query);//设置占位符参数
        return query.list();




    }





    //把queryObject中的参数设置到query中
    private void setPlaceParameters(QueryObject qo, Query query) {
        for (int i = 0; i < qo.getParameters().size(); i++){
            query.setParameter(i, qo.getParameters().get(i));
        }
    }
}
