package com._520it.wms.service.impl;

import com._520it.wms.dao.IProductStockDAO;
import com._520it.wms.dao.IStockIncomeBillDAO;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.domain.StockIncomeBillItem;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.service.IStockIncomeBillService;
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
public class StockIncomeBillServiceImpl implements IStockIncomeBillService {

    @Setter
    private IStockIncomeBillDAO stockIncomeBillDAO;

    @Setter
    private IProductStockService productStockService;

    @Override
    public void save(StockIncomeBill bill) {
        //1.设置制单人和制单时间
        bill.setInputUser(UserContext.getCurrentEmployee());
        bill.setInputTime(new Date());
        //2.手动重新设置单据的未审核状态
        bill.setStatus(StockIncomeBill.NORMAL);
        parseItems(bill);
        //6.保存单据
        stockIncomeBillDAO.save(bill);
    }

    @Override
    public void update(StockIncomeBill bill) {
        //1.判断审核状态
        if (bill.getStatus() == StockIncomeBill.NORMAL) {
            parseItems(bill);
            stockIncomeBillDAO.update(bill);
        }
    }

    @Override
    public void delete(Long id) {
        stockIncomeBillDAO.delete(id);
    }

    @Override
    public StockIncomeBill get(Long id) {
        return stockIncomeBillDAO.get(id);
    }

    @Override
    public List<StockIncomeBill> listAll() {
        return stockIncomeBillDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return stockIncomeBillDAO.query(qo);
    }

    //到货入库审核，审核之后每条明细都应该存储到库存中
    @Override
    public void audit(Long id) {
        StockIncomeBill bill = stockIncomeBillDAO.get(id);
        //判断当前单据的审核状态
        if (bill.getStatus() == StockIncomeBill.NORMAL) {
            //设置已审核状态
            bill.setStatus(StockIncomeBill.AUDIT);
            //设置审核人和审核时间
            bill.setAuditor(UserContext.getCurrentEmployee());
            bill.setAuditTime(new Date());
            //操作库存
            for (StockIncomeBillItem item : bill.getItems()) {
                //入库操作
                productStockService.stockIncome(bill.getDepot(),item.getProduct(),item.getNumber(),item.getCostPrice());//入库操作
            }
            //更新,可以不写
            stockIncomeBillDAO.update(bill);
        }
    }

    //处理明细
    private void parseItems(StockIncomeBill bill) {
        bill.setTotalNumber(BigDecimal.ZERO);
        bill.setTotalAmount(BigDecimal.ZERO);
        for (StockIncomeBillItem item : bill.getItems()) {
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
