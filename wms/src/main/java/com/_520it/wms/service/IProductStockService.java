package com._520it.wms.service;

import com._520it.wms.domain.Depot;
import com._520it.wms.domain.Product;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.math.BigDecimal;
import java.util.List;

public interface IProductStockService {
	void save(ProductStock productStock);
	void update(ProductStock productStock);
	void delete(Long id);
	
	ProductStock get(Long id);
	List<ProductStock> listAll();

	PageResult query(QueryObject qo);

	/*
	 * @Author: HCB
	 * @Description:  入库操作
	 * @Date: 2022-6-24
	 * @Parms: depot:入库的仓库
	 * @Parms: product:入库的商品
	 * @Parms: number:入库的数量
	 * @Parms: costPrice:入库的成本价格
	 * @ReturnType:
	 */
	void stockIncome(Depot depot, Product product, BigDecimal number, BigDecimal costPrice);
	/*
	 * @Author: HCB
	 * @Description:  出库操作
	 * @Date: 2022-6-24 
	 * @Parms: depot: 出库的仓库
	 * @Parms: product: 出库的货品
	 * @Parms: number：出库的数量
	 * @ReturnType:返回库存价格
	 */

	BigDecimal stockOutcome(Depot depot, Product product, BigDecimal number);
}
