package com._520it.wms.service.impl;

import com._520it.wms.dao.IProductStockDAO;
import com._520it.wms.dao.ISaleAccountDAO;
import com._520it.wms.dao.IStockOutcomeBillDAO;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.domain.SaleAccount;
import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.domain.StockOutcomeBillItem;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.service.IStockOutcomeBillService;
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
public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {

    @Setter
    private IStockOutcomeBillDAO stockOutcomeBillDAO;

    @Setter
    private ISaleAccountDAO saleAccountDAO;
    @Setter
    private IProductStockService productStockService;

    @Override
    public void save(StockOutcomeBill bill) {
        //1.设置制单人和制单时间
        bill.setInputUser(UserContext.getCurrentEmployee());
        bill.setInputTime(new Date());
        //2.手动重新设置单据的未审核状态
        bill.setStatus(StockOutcomeBill.NORMAL);
        parseItems(bill);
        //6.保存单据
        stockOutcomeBillDAO.save(bill);
    }

    @Override
    public void update(StockOutcomeBill bill) {
        //1.判断审核状态
        if (bill.getStatus() == StockOutcomeBill.NORMAL) {
            parseItems(bill);
            stockOutcomeBillDAO.update(bill);
        }
    }

    @Override
    public void delete(Long id) {
        stockOutcomeBillDAO.delete(id);
    }

    @Override
    public StockOutcomeBill get(Long id) {
        return stockOutcomeBillDAO.get(id);
    }

    @Override
    public List<StockOutcomeBill> listAll() {
        return stockOutcomeBillDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return stockOutcomeBillDAO.query(qo);
    }

    //到货入库审核，审核之后每条明细都应该存储到库存中
    @Override
    public void audit(Long id) {
        StockOutcomeBill bill = stockOutcomeBillDAO.get(id);
        //判断当前单据的审核状态
        if (bill.getStatus() == StockOutcomeBill.NORMAL) {
            //设置已审核状态
            bill.setStatus(StockOutcomeBill.AUDIT);
            //设置审核人和审核时间
            bill.setAuditor(UserContext.getCurrentEmployee());
            bill.setAuditTime(new Date());
            //操作库存
            for (StockOutcomeBillItem item : bill.getItems()) {
                //库存的出库操作
                BigDecimal costPrice = productStockService.stockOutcome(bill.getDepot(),item.getProduct(),item.getNumber());
                SaleAccount sa = new SaleAccount();
                sa.setVdate(bill.getVdate());
                sa.setNumber(item.getNumber());
                sa.setCostPrice(costPrice);
                sa.setCostAmount(costPrice.multiply(item.getNumber()).setScale(2,RoundingMode.HALF_UP));
                sa.setSalePrice(item.getSalePrice());
                sa.setSaleAmount(item.getAmount());
                sa.setProduct(item.getProduct());
                sa.setClient(bill.getClient());
                sa.setSaleman(bill.getInputUser());

                saleAccountDAO.save(sa);
            }
            //更新,可以不写
            stockOutcomeBillDAO.update(bill);
        }
    }

    //处理明细
    private void parseItems(StockOutcomeBill bill) {
        bill.setTotalNumber(BigDecimal.ZERO);
        bill.setTotalAmount(BigDecimal.ZERO);
        for (StockOutcomeBillItem item : bill.getItems()) {
            //3.处理单据和明细之间的关系
            item.setBill(bill);
            //4.计算单挑明细的小计
            item.setAmount(item.getNumber().multiply(item.getSalePrice().setScale(2, RoundingMode.HALF_UP)));
            //5.计算单据的总数量和总金额
            bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
            bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
        }
    }
}
