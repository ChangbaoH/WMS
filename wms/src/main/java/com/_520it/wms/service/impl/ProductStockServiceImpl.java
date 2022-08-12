package com._520it.wms.service.impl;

import com._520it.wms.dao.IProductStockDAO;
import com._520it.wms.domain.Depot;
import com._520it.wms.domain.Product;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductStockService;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/*
 *@Auther:HCB
 *@Date:2022/5/29
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
public class ProductStockServiceImpl implements IProductStockService {

    @Setter
    private IProductStockDAO productStockDAO;

    @Override
    public void save(ProductStock r) {
        productStockDAO.save(r);
    }

    @Override
    public void update(ProductStock r) {
        productStockDAO.update(r);
    }

    @Override
    public void delete(Long id) {
        productStockDAO.delete(id);
    }

    @Override
    public ProductStock get(Long id) {
        return productStockDAO.get(id);
    }

    @Override
    public List<ProductStock> listAll() {
        return productStockDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return productStockDAO.query(qo);
    }

    @Override
    public void stockIncome(Depot depot, Product product, BigDecimal number, BigDecimal costPrice) {
        //1.判断某货品是否存在于某个仓库，某个库存中
        ProductStock ps = productStockDAO.getByDepotAndProduct(depot.getId(), product.getId());
        BigDecimal amount = number.multiply(costPrice).setScale(2,RoundingMode.HALF_UP);
        if (ps != null) {
            //2.如果存在
            //2.1 重新计算库存数量，库存金额，库存价格
            ps.setStoreNumber(ps.getStoreNumber().add(number));
            ps.setAmount(ps.getAmount().add(amount));
            ps.setPrice(ps.getAmount().divide(ps.getStoreNumber(),2, RoundingMode.HALF_UP));
            //2.2更新库存
            productStockDAO.update(ps);
        } else {
            //3.如果不存在
            ps = new ProductStock();
            //3.1手动创建库存对象，设置仓库,货品,库存数量，库存金额，库存价格
            ps.setDepot(depot);
            ps.setProduct(product);
            ps.setStoreNumber(number);
            ps.setPrice(costPrice);
            ps.setAmount(amount);
            //3.2
            productStockDAO.save(ps);
        }
    }

    @Override
    public BigDecimal stockOutcome(Depot depot, Product product, BigDecimal number) {
        //1.判断某货品是否存在于某个仓库，某个库存中
        ProductStock ps = productStockDAO.getByDepotAndProduct(depot.getId(), product.getId());
        if (ps == null || ps.getStoreNumber().compareTo(number)<0){
            throw new RuntimeException(product.getName()+"库存不足"+ number);
        }
        ps.setStoreNumber(ps.getStoreNumber().subtract(number));
        //销售出库不会涉及库存价格的变动
        ps.setAmount(ps.getStoreNumber().multiply(ps.getPrice()));
        //2.2更新库存
        productStockDAO.update(ps);
        return ps.getPrice();
    }
}
