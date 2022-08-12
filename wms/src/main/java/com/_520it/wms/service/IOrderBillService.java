package com._520it.wms.service;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IOrderBillService {
	void save(OrderBill orderBill);
	void update(OrderBill orderBill);
	void delete(Long id);
	
	OrderBill get(Long id);
	List<OrderBill> listAll();

	PageResult query(QueryObject qo);
	/*
	 * @Author: HCB
	 * @Description:  单据审核
	 * @Date: 2022-6-23
	 * @Parms:
	 * @ReturnType:
	 */
    void audit(Long id);
}
