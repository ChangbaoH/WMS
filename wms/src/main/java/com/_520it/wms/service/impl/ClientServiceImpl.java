package com._520it.wms.service.impl;

import com._520it.wms.dao.IClientDAO;
import com._520it.wms.domain.Client;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IClientService;
import lombok.Setter;

import java.util.List;

/*
 *@Auther:HCB
 *@Date:2022/5/29
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
public class ClientServiceImpl implements IClientService {

    @Setter
    private IClientDAO clientDAO;

    @Override
    public void save(Client r) {
        clientDAO.save(r);
    }

    @Override
    public void update(Client r) {
        clientDAO.update(r);
    }

    @Override
    public void delete(Long id) {
        clientDAO.delete(id);
    }

    @Override
    public Client get(Long id) {
        return clientDAO.get(id);
    }

    @Override
    public List<Client> listAll() {
        return clientDAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return clientDAO.query(qo);
    }
}
