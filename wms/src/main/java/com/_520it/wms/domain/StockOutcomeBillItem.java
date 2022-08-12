package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//销售出库单明细
@Getter
@Setter
public class StockOutcomeBillItem extends BaseDomain{
    private BigDecimal salePrice;//销售价格
    private BigDecimal number;//销售数量
    private BigDecimal amount;//销售小计
    private String remark;//备注

    private Product product;//货品
    private StockOutcomeBill bill;//单据
}
