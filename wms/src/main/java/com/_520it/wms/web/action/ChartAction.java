package com._520it.wms.web.action;

import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IChartService;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.util.RequiredPermission;

import com._520it.wms.vo.OrderGroupByType;
import com._520it.wms.vo.SaleChartVO;
import com._520it.wms.vo.SaleGroupByType;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChartAction extends BaseAction{

    @Setter
    private IChartService chartService;
    @Setter
    private IBrandService brandService;
    @Setter
    private ISupplierService supplierService;
    @Setter
    private IClientService clientService;
    @Getter
    private OrderChartQueryObject oqo = new OrderChartQueryObject();
    @Getter
    private SaleChartQueryObject sqo = new SaleChartQueryObject();

    @RequiredPermission("订货报表")
    public String orderChart() throws Exception{
        putContext("orderCharts",chartService.queryOrderChart(oqo));
        putContext("brands",brandService.listAll());
        putContext("suppliers",supplierService.listAll());
        putContext("orderGroupByTypes", OrderGroupByType.values());
        return "orderChart";
    }
    @RequiredPermission("销售报表")
    public String saleChart() throws Exception{
        putContext("saleCharts",chartService.querySaleChart(sqo));
        putContext("brands",brandService.listAll());
        putContext("clients",clientService.listAll());
        putContext("saleGroupByTypes", SaleGroupByType.values());
        return "saleChart";
    }
    @RequiredPermission("销售线性图表")
    public String saleChartByLine() throws Exception{
        List<SaleChartVO> list = chartService.querySaleChart(sqo);
        List<String> groupTypes = new ArrayList<>();
        List<BigDecimal> totalAmounts = new ArrayList<>();
        for (SaleChartVO vo : list) {
            groupTypes.add(vo.getGroupType());
            totalAmounts.add(vo.getTotalAmount());
        }
        putContext("groupTypes", JSON.toJSONString(groupTypes));
        putContext("totalAmounts", JSON.toJSONString(totalAmounts));
        putContext("groupBy", SaleGroupByType.valueOf(sqo.getGroupType().toUpperCase()).getGroupType());

        return "saleChartByLine";
    }

    @RequiredPermission("销售饼状图表")
    public String saleChartByPie() throws Exception{
        List<SaleChartVO> list = chartService.querySaleChart(sqo);
        List<Object> datas = new ArrayList<>();//Object[]封装分组类型和销售总金额
        for (SaleChartVO vo : list) {
            datas.add(new Object[]{vo.getGroupType(),vo.getTotalAmount()});
        }
        putContext("datas", JSON.toJSONString(datas));
        putContext("groupBy", SaleGroupByType.valueOf(sqo.getGroupType().toUpperCase()).getGroupType());

        return "saleChartByPie";
    }

}
