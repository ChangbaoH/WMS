package com._520it.wms.vo;
//封装了订货报表的的结果
//1.分组类型
//2.订货数量
//3.订货总金额

import lombok.Getter;

import java.math.BigDecimal;
@Getter
public class OrderChartVO {
    private String groupType;
    private BigDecimal totalNumber;
    private BigDecimal totalAmount;

    public OrderChartVO(String groupType, BigDecimal totalNumber, BigDecimal totalAmount) {
        this.groupType = groupType;
        this.totalNumber = totalNumber;
        this.totalAmount = totalAmount;
    }
}
