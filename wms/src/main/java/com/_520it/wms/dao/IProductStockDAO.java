package com._520it.wms.dao;


import com._520it.wms.domain.ProductStock;

public interface IProductStockDAO extends IGenericDAO<ProductStock>{

    /*
     * @Author: HCB
     * @Description:  根据货品编号和仓库编号查询某类货品是否存在某个仓库中
     * @Date: 2022-6-24
     * @Parms:
     * @ReturnType:
     */
    ProductStock getByDepotAndProduct(Long depotId, Long productId);
}