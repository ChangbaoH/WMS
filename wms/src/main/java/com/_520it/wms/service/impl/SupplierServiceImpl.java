package com._520it.wms.service.impl;

import com._520it.wms.dao.ISupplierDAO;
import com._520it.wms.domain.Supplier;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.ISupplierService;
import lombok.Setter;

import java.util.List;

/*
 *@Auther:HCB
 *@Date:2022/5/29
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
public class SupplierServiceImpl implements ISupplierService {

    @Setter
    private ISupplierDAO supplierDAO;

    @Override
    public void save(Supplier r) {
        supplierDAO.save(r);
    }

    @Override
    public void update(Supplier r) {
        supplierDAO.update(r);
    }

    @Override
    public void delete(Long id) {
        supplierDAO.delete(id);
    }

    @Override
    public Supplier get(Long id) {
        return supplierDAO.get(id);
    }

    @Override
    public List<Supplier> listAll() {
        return supplierDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return supplierDAO.query(qo);
    }
}
