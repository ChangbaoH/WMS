package com._520it.wms.vo;

import lombok.Getter;

@Getter
//订货报表时分组查询的类型
public enum SaleGroupByType {
    EMPLOYEE("obj.saleman.name","obj.saleman","销售人员"),
    PRODUCT("obj.product.name","obj.product","货品名称"),
    BRAND("obj.product.brand.name","obj.product.brand","品牌名称"),
    CLIENT("obj.client.name","obj.client","客户"),
    MONTH("date_format(obj.vdate,'%Y-%m')","date_format(obj.vdate,'%Y-%m')","销售日期(月)"),
    DAY("date_format(obj.vdate,'%Y-%m-%d')","date_format(obj.vdate,'%Y-%m-%d')","销售日期(日)");


    private SaleGroupByType(String groupValue, String groupBy, String groupType){
        this.groupValue = groupValue;
        this.groupBy = groupBy;
        this.groupType = groupType;
    }
    private String groupType;//分组类型，页面显示的分组选择
    private String groupValue;//分组的名称，查询的内容
    private String groupBy;//分组查询，分组的选项
}
