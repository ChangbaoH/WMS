package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//采购订单
@Getter
@Setter
public class OrderBill extends BaseDomain{
    public static final int NORMAL = 0;//未审核
    public static final int AUDIT = 1;//已审核

    private String sn;//订单编号
    private Date vdate;//业务时间
    private int status = OrderBill.NORMAL;//单据审核状态，缺省是未审核
    private BigDecimal totalNumber;//采购数量
    private BigDecimal totalAmount;//采购总金额
    private Supplier supplier;//供应商
    private Employee inputUser;//制单人
    private Date inputTime;//制单时间
    private Employee auditor;//审核人
    private Date auditTime;//审核时间

    private List<OrderBillItem> items = new ArrayList<>();//单据明细

}
