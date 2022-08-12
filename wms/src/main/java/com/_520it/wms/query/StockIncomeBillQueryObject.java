package com._520it.wms.query;

import com._520it.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/*
 *@Auther:HCB
 *@Date:2022/5/27
 *@Description:员工的高级查询对象，封装了高级查询信息
 *Version:1.0
 */
@Getter
@Setter
public class StockIncomeBillQueryObject extends QueryObject{

    private Date beginDate;//业务开始时间
    private Date endDate;//业务结束时间
    private Long depotId = -1L;//供应商ID
    private int status = -1;//订单审核状态
    public void customizedQuery(){
        if (depotId > 0){
            addQuery("obj.depot.id = ?",depotId);
        }
        if (status > -1){
            addQuery("obj.status = ?",status);
        }
        if (beginDate != null){

            addQuery("obj.vdate >= ?", DateUtil.getBeginDate(beginDate));
        }
        if (endDate != null){
            addQuery("obj.vdate <= ?", DateUtil.getEndDate(endDate));
        }
    }
    
}
