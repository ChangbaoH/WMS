package com._520it.wms.service.impl;

import com._520it.wms.dao.IProductDAO;
import com._520it.wms.domain.Product;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductService;
import lombok.Setter;

import java.util.List;

/*
 *@Auther:HCB
 *@Date:2022/5/29
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
public class ProductServiceImpl implements IProductService {

    @Setter
    private IProductDAO productDAO;

    @Override
    public void save(Product r) {
        productDAO.save(r);
    }

    @Override
    public void update(Product r) {
        productDAO.update(r);
    }

    @Override
    public void delete(Long id) {
        productDAO.delete(id);
    }

    @Override
    public Product get(Long id) {
        return productDAO.get(id);
    }

    @Override
    public List<Product> listAll() {
        return productDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return productDAO.query(qo);
    }
}
