package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/*
 *@Auther:HCB
 *@Date:2022/5/27
 *@Description:员工的高级查询对象，封装了高级查询信息
 *Version:1.0
 */
@Getter
@Setter
public class ProductStockQueryObject extends QueryObject{
    private String keyword;//货品名称或者编码
    private Long depotId = -1L;//所在仓库
    private Long brandId = -1L;//品牌
    private BigDecimal storeLimit;//货存阈值

    public void customizedQuery(){
        if (hasLength(keyword)){
            String key = "%"+keyword+"%";
            addQuery("obj.product.name LIKE ? OR obj.product.sn LIKE ?",key,key);
        }
        if (depotId > 0){
            addQuery("obj.depot.id = ?",depotId);
        }
        if (brandId > 0){
            addQuery("obj.product.brand.id = ?",brandId);
        }
        if (storeLimit != null){
            addQuery("obj.storeNumber <= ?",storeLimit);
        }
        //查询即时库存，库存两必须大于0
        addQuery("obj.storeNumber > ?",BigDecimal.ZERO);

    }
    
}
