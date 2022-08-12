<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/highcharts/highcharts.src.js"></script>

    <title>WMS-销售报表线性图</title>
    <script type="text/javascript">
        $(function () {
            $('#container').highcharts({
                title: {
                    text: '销售报表线性图',
                    x: -20 //center
                },
                subtitle: {
                    text: '按照<s:property value="#groupBy"/>分组',
                    x: -20
                },
                xAxis: {
                    categories: <s:property value="#groupTypes" escapeHtml="false"/>
                },
                yAxis: {
                    title: {
                        text: '销售总金额(￥(元))'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    valueSuffix: '元'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    name: '销售总金额',
                    data: <s:property value="#totalAmounts" escapeHtml="false"/>
                }]
            });
        });
    </script>
</head>
<body>
    <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</body>
</html>

