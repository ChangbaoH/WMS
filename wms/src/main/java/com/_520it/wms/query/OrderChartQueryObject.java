package com._520it.wms.query;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
//查询主题,OrderBillItem
//订货报表查询对象
@Getter
@Setter
public class OrderChartQueryObject extends QueryObject{
    private Date beginDate;//业务开始时间
    private Date endDate;//业务结束时间
    private String keyword;//货品名称或编号
    private Long supplierId = -1L;//供应商
    private Long brandId = -1L;//品牌
    private String groupType = "employee";//分组类型

    protected void customizedQuery() {

        if (beginDate != null){
            addQuery("obj.bill.vdate >= ?", DateUtil.getBeginDate(beginDate));
        }
        if (endDate != null){
            addQuery("obj.bill.vdate <= ?", DateUtil.getEndDate(endDate));
        }
        if (supplierId > 0){
            addQuery("obj.bill.supplier.id = ?",supplierId);
        }
        if (hasLength(keyword)){
            String key = "%"+keyword+"%";
            addQuery("obj.product.name LIKE ? OR obj.product.sn LIKE ?",key,key);
        }
        if (brandId>0){
            addQuery("obj.product.brand.id = ?",brandId);
        }
        //审核通过的才能被检索
        addQuery("obj.bill.status = ?", OrderBill.AUDIT);
    }
}
