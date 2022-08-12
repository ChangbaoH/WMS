<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>

    <title>WMS-销售报表管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript">
        $(function (){
            $(":input[name='sqo.keyword']").attr("placeholder","货品编码/货品名称");
            $("input[name='sqo.beginDate']").addClass("Wdate").click(function (){
                WdatePicker({
                    maxDate: $("input[name='sqo.endDate']").val()||new Date()
                });
            });
            $("input[name='sqo.endDate']").addClass("Wdate").click(function (){
                WdatePicker({
                    minDate: $("input[name='sqo.beginDate']").val(),
                    maxDate: new Date()
                });
            });
        });

        /** table鼠标悬停换色* */
        $(function() {
            // 如果鼠标移到行上时，执行函数
            $(".table tr").mouseover(function() {
                $(this).css({background : "#CDDAEB"});
                $(this).children('td').each(function(index, ele){
                    $(ele).css({color: "#1D1E21"});
                });
            }).mouseout(function() {
                $(this).css({background : "#FFF"});
                $(this).children('td').each(function(index, ele){
                    $(ele).css({color: "#909090"});
                });
            });
        });

        $(function (){
            $(".type").change(function (){
                var type = $(this).val();
                if (type == "line"){
                    $("#searchForm").prop("action","chart_saleChartByLine.action").submit();
                }else if(type == "pie"){
                    $("#searchForm").prop("action","chart_saleChartByPie.action").submit();
                }
            });

        });

    </script>
</head>
<body>
<!-- ============================================================ -->
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>
<!-- ============================================================ -->
<s:form id="searchForm" namespace="/" action="chart_saleChart" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        货品
                        <s:textfield name="sqo.keyword" cssClass="ui_input_txt02"/>
                        业务时间
                        <s:date name="sqo.beginDate" format="yyyy-MM-dd" var="bd"/>
                        <s:textfield name="sqo.beginDate" cssClass="ui_input_txt02" value="%{bd}"/>
                        ~
                        <s:date name="sqo.endDate" format="yyyy-MM-dd" var="ed"/>
                        <s:textfield name="sqo.endDate" cssClass="ui_input_txt02" value="%{ed}"/>
                        客户
                        <s:select list="#clients" name="sqo.clientId" listKey="id" listValue="name"
                                  headerKey="-1" headerValue="所有客户" cssClass="ui_select02"/>
                        品牌
                        <s:select list="#brands" name="sqo.brandId" listKey="id" listValue="name"
                                  headerKey="-1" headerValue="所有品牌" cssClass="ui_select02"/>
                        分组
                        <s:select list="#saleGroupByTypes" listKey="name()" listValue="groupType"
                                  name="sqo.groupType" cssClass="ui_select03"/>
                        查看图表
                        <s:select list="#{'':'选择图表','line':'线型图','pie':'饼状图'}" name="XX"
                                  cssClass="ui_select03 type"/>
                    </div>
                    <div id="box_bottom">
                        <input type="submit" value="查询" class="ui_input_btn01"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th>分组类型</th>
                        <th>销售总数量</th>
                        <th>销售总金额</th>
                        <th>毛利润</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#saleCharts">
                        <tr>
                            <td><s:property value="groupType"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
                            <td><s:property value="grossProfit"/></td>
                        </tr>
                    </s:iterator>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</s:form>
</body>
</html>

