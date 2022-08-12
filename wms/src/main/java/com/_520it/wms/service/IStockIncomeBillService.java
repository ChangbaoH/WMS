package com._520it.wms.service;

import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IStockIncomeBillService {
	void save(StockIncomeBill stockIncomeBill);
	void update(StockIncomeBill stockIncomeBill);
	void delete(Long id);
	
	StockIncomeBill get(Long id);
	List<StockIncomeBill> listAll();

	PageResult query(QueryObject qo);

    void audit(Long id);
}
