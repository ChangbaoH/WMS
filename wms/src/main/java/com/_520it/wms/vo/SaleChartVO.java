package com._520it.wms.vo;
//封装了销售报表的的结果
//1.分组类型
//2.订货数量
//3.订货总金额

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class SaleChartVO {
    private String groupType;
    private BigDecimal totalNumber;
    private BigDecimal totalAmount;
    private BigDecimal grossProfit;//毛利润

    public SaleChartVO(String groupType, BigDecimal totalNumber, BigDecimal totalAmount,BigDecimal grossProfit) {
        this.groupType = groupType;
        this.totalNumber = totalNumber;
        this.totalAmount = totalAmount;
        this.grossProfit = grossProfit;
    }
}
