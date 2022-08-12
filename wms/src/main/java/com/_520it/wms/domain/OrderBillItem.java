package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//采购明细
@Getter
@Setter
public class OrderBillItem extends BaseDomain{
    private BigDecimal costPrice;//采购价格
    private BigDecimal number;//数量
    private BigDecimal amount;//小计
    private String remark;//备注

    private Product product;//货品
    private OrderBill bill;//单据
}
