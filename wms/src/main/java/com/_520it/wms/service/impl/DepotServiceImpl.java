package com._520it.wms.service.impl;

import com._520it.wms.dao.IDepotDAO;
import com._520it.wms.domain.Depot;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepotService;
import lombok.Setter;

import java.util.List;

/*
 *@Auther:HCB
 *@Date:2022/5/29
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
public class DepotServiceImpl implements IDepotService {

    @Setter
    private IDepotDAO depotDAO;

    @Override
    public void save(Depot r) {
        depotDAO.save(r);
    }

    @Override
    public void update(Depot r) {
        depotDAO.update(r);
    }

    @Override
    public void delete(Long id) {
        depotDAO.delete(id);
    }

    @Override
    public Depot get(Long id) {
        return depotDAO.get(id);
    }

    @Override
    public List<Depot> listAll() {
        return depotDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return depotDAO.query(qo);
    }
}
