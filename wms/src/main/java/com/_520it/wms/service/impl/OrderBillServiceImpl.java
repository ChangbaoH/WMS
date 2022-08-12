package com._520it.wms.service.impl;

import com._520it.wms.dao.IOrderBillDAO;
import com._520it.wms.domain.OrderBill;
import com._520it.wms.domain.OrderBillItem;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IOrderBillService;
import com._520it.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/*
 *@Auther:HCB
 *@Date:2022/5/29
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
public class OrderBillServiceImpl implements IOrderBillService {

    @Setter
    private IOrderBillDAO orderBillDAO;

    @Override
    public void save(OrderBill bill) {
        //1.设置制单人和制单时间
        bill.setInputUser(UserContext.getCurrentEmployee());
        bill.setInputTime(new Date());
        //2.手动重新设置单据的未审核状态
        bill.setStatus(OrderBill.NORMAL);
        parseItems(bill);
        //6.保存单据
        orderBillDAO.save(bill);
    }

    @Override
    public void update(OrderBill bill) {
        //1.判断审核状态
        if(bill.getStatus() == OrderBill.NORMAL){
            parseItems(bill);
            orderBillDAO.update(bill);
        }
    }

    @Override
    public void delete(Long id) {
        orderBillDAO.delete(id);
    }

    @Override
    public OrderBill get(Long id) {
        return orderBillDAO.get(id);
    }

    @Override
    public List<OrderBill> listAll() {
        return orderBillDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return orderBillDAO.query(qo);
    }

    @Override
    public void audit(Long id) {
        OrderBill bill = orderBillDAO.get(id);
        //判断当前单据的审核状态
        if(bill.getStatus() == OrderBill.NORMAL){
            //设置已审核状态
            bill.setStatus(OrderBill.AUDIT);
            //设置审核人和审核时间
            bill.setAuditor(UserContext.getCurrentEmployee());
            bill.setAuditTime(new Date());
            //更新,可以不写
            orderBillDAO.update(bill);
        }
    }

    //处理明细
    private void parseItems(OrderBill bill) {
        bill.setTotalNumber(BigDecimal.ZERO);
        bill.setTotalAmount(BigDecimal.ZERO);
        for (OrderBillItem item : bill.getItems()) {
            //3.处理单据和明细之间的关系
            item.setBill(bill);
            //4.计算单挑明细的小计
            item.setAmount(item.getNumber().multiply(item.getCostPrice().setScale(2, RoundingMode.HALF_UP)));
            //5.计算单据的总数量和总金额
            bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
            bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
        }
    }
}
