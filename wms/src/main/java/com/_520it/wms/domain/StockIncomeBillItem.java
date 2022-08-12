package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//到货入库明细
@Getter
@Setter
public class StockIncomeBillItem extends BaseDomain{
    private BigDecimal costPrice;//入库价格/成本价
    private BigDecimal number;//入库数量
    private BigDecimal amount;//入库小计
    private String remark;//备注

    private Product product;//货品
    private StockIncomeBill bill;//单据
}
