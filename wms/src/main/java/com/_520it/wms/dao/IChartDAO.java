package com._520it.wms.dao;

import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.vo.OrderChartVO;
import com._520it.wms.vo.SaleChartVO;

import java.util.List;
import java.util.Map;

/*
 * @Author: HCB
 * @Description:  报表查询的DAO
 * @Date: 2022-6-24 
 * @Parms: 
 * @ReturnType: 
 */
public interface IChartDAO {
    /*
     * @Author: HCB
     * @Description:  订货报表查询
     * @Date: 2022-6-24 
     * @Parms: 
     * @ReturnType: 
     */
    List<OrderChartVO> queryOrderChart(OrderChartQueryObject qo);

    /*
     * @Author: HCB
     * @Description:  销售报表查询
     * @Date: 2022-6-24
     * @Parms:
     * @ReturnType:
     */
    List<SaleChartVO> querySaleChart(SaleChartQueryObject qo);
    
}
