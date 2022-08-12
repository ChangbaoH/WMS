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
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>

    <title>WMS-即时库存报表</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript">
        $(function (){
            $(":input[name='qo.keyword']").attr("placeholder","货品编码/货品名称");
/*            $(":input[name='qo.keyword']").val("货品编码/货品名称");
            $(":input[name='qo.keyword']").focus(function (){
                $(this).val("");
            }).blur(function (){
                $(this).val($(this).val() || "货品编码/货品名称");
            });*/
        });
    </script>
</head>
<body>

<s:form id="searchForm" namespace="/" action="productStock" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        货品
                        <s:textfield name="qo.keyword" cssClass="ui_input_txt03"/>
                        仓库
                        <s:select list="#depots" name="qo.depotId" listKey="id" listValue="name"
                                  headerKey="-1" headerValue="所有供应商" cssClass="ui_select03"/>
                        品牌
                        <s:select list="#brands" name="qo.brandId" listKey="id" listValue="name"
                                  headerKey="-1" headerValue="所有供应商" cssClass="ui_select03"/>
                        库存阈值
                        <s:textfield name="qo.storeLimit" cssClass="ui_input_txt02"/>
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                   </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th>货品编号</th>
                        <th>货品名称</th>
                        <th>所在仓库</th>
                        <th>货品品牌</th>
                        <th>库存价格</th>
                        <th>库存数量</th>
                        <th>库存总金额</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.result">
                        <tr>
                            <td><s:property value="product.sn"/></td>
                            <td><s:property value="product.name"/></td>
                            <td><s:property value="depot.name"/></td>
                            <td><s:property value="product.brand.name"/></td>
                            <td><s:property value="price"/></td>
                            <td><s:property value="storeNumber"/></td>
                            <td><s:property value="amount"/></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
            <%@include file="/WEB-INF/views/common/common_page.jsp" %>

        </div>
    </div>
</s:form>
</body>
</html>

