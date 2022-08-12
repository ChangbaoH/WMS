package com._520it.wms.service.impl;

import com._520it.wms.dao.IBrandDAO;
import com._520it.wms.domain.Brand;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IBrandService;
import lombok.Setter;

import java.util.List;

/*
 *@Auther:HCB
 *@Date:2022/5/29
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
public class BrandServiceImpl implements IBrandService {

    @Setter
    private IBrandDAO brandDAO;

    @Override
    public void save(Brand r) {
        brandDAO.save(r);
    }

    @Override
    public void update(Brand r) {
        brandDAO.update(r);
    }

    @Override
    public void delete(Long id) {
        brandDAO.delete(id);
    }

    @Override
    public Brand get(Long id) {
        return brandDAO.get(id);
    }

    @Override
    public List<Brand> listAll() {
        return brandDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return brandDAO.query(qo);
    }
}
