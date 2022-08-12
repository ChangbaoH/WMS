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

    <title>WMS-订货报表管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript">
        $(function (){
            $(":input[name='oqo.keyword']").attr("placeholder","货品编码/货品名称");
            $("input[name='oqo.beginDate']").addClass("Wdate").click(function (){
                WdatePicker({
                    maxDate: $("input[name='oqo.endDate']").val()||new Date()
                });
            });
            $("input[name='oqo.endDate']").addClass("Wdate").click(function (){
                WdatePicker({
                    minDate: $("input[name='oqo.beginDate']").val(),
                    maxDate: new Date()
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
        });
    </script>
</head>
<body>
<!-- ============================================================ -->
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>
<!-- ============================================================ -->
<s:form id="searchForm" namespace="/" action="chart_orderChart" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        货品
                        <s:textfield name="oqo.keyword" cssClass="ui_input_txt02"/>
                        业务时间
                        <s:date name="oqo.beginDate" format="yyyy-MM-dd" var="bd"/>
                        <s:textfield name="oqo.beginDate" cssClass="ui_input_txt02" value="%{bd}"/>
                        ~
                        <s:date name="oqo.endDate" format="yyyy-MM-dd" var="ed"/>
                        <s:textfield name="oqo.endDate" cssClass="ui_input_txt02" value="%{ed}"/>
                        供应商
                        <s:select list="#suppliers" name="oqo.supplierId" listKey="id" listValue="name"
                                  headerKey="-1" headerValue="所有供应商" cssClass="ui_select02"/>
                        品牌
                        <s:select list="#brands" name="oqo.brandId" listKey="id" listValue="name"
                                  headerKey="-1" headerValue="所有品牌" cssClass="ui_select02"/>
                        分组
                        <s:select list="#orderGroupByTypes" listKey="name()" listValue="groupType"
                                  name="oqo.groupType" cssClass="ui_select03"/>

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
                        <th>订货总数量</th>
                        <th>订货总金额</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#orderCharts">
                        <tr>
                            <td><s:property value="groupType"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
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

