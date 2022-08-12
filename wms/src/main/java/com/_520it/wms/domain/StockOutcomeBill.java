package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//销售出库单
@Getter
@Setter
public class StockOutcomeBill extends BaseDomain{
    public static final int NORMAL = 0;//未审核
    public static final int AUDIT = 1;//已审核

    private String sn;//订单编号
    private Date vdate;//业务时间
    private int status = StockOutcomeBill.NORMAL;//单据审核状态，缺省是未审核
    private BigDecimal totalNumber;//出库数量
    private BigDecimal totalAmount;//出库总金额
    private Depot depot;//出库仓库
    private Client client;//客户
    private Employee inputUser;//制单人
    private Date inputTime;//制单时间
    private Employee auditor;//审核人
    private Date auditTime;//审核时间

    private List<StockOutcomeBillItem> items = new ArrayList<>();//单据明细

}
