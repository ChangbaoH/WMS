package com._520it.wms.query;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

//查询主题,SaleAccount
//销售报表查询对象
@Getter
@Setter
public class SaleChartQueryObject extends QueryObject{
    private Date beginDate;//业务开始时间
    private Date endDate;//业务结束时间
    private String keyword;//货品名称或编号
    private Long clientId = -1L;//供应商
    private Long brandId = -1L;//品牌
    private String groupType = "employee";//分组类型

    protected void customizedQuery() {

        if (beginDate != null){
            addQuery("obj.vdate >= ?", DateUtil.getBeginDate(beginDate));
        }
        if (endDate != null){
            addQuery("obj.vdate <= ?", DateUtil.getEndDate(endDate));
        }
        if (clientId > 0){
            addQuery("obj.client.id = ?",clientId);
        }
        if (hasLength(keyword)){
            String key = "%"+keyword+"%";
            addQuery("obj.product.name LIKE ? OR obj.product.sn LIKE ?",key,key);
        }
        if (brandId>0){
            addQuery("obj.product.brand.id = ?",brandId);
        }
    }
}
