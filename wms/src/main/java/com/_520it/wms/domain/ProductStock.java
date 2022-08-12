package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//货品库存
@Getter
@Setter
public class ProductStock extends BaseDomain{
    private Product product;//那个货品
    private Depot depot;//仓库
    private BigDecimal storeNumber;//库存数量
    private BigDecimal price;//库存价格 移动加权平均
    private BigDecimal amount;//库存金额

}
