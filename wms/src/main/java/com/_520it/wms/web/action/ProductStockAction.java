package com._520it.wms.web.action;

import com._520it.wms.domain.ProductStock;
import com._520it.wms.query.ProductStockQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class ProductStockAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Setter
	private IProductStockService productStockService;
	@Setter
	private IDepotService depotService;
	@Setter
	private IBrandService brandService;

	@Getter
	private ProductStockQueryObject qo = new ProductStockQueryObject();

	@RequiredPermission("即时库存报表")
	@Override
	public String execute() throws Exception {
		qo.setPageSize(20);
		putContext("depots", depotService.listAll());
		putContext("brands", brandService.listAll());
		putContext("pageResult", productStockService.query(qo));
		return LIST;
	}

}
