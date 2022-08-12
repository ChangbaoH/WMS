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

    <title>WMS-到货入库单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript">
        $(function (){
            $("input[name='qo.beginDate']").addClass("Wdate").click(function (){
                WdatePicker({
                    maxDate: $("input[name='qo.endDate']").val()||new Date()
                });
            });
            $("input[name='qo.endDate']").addClass("Wdate").click(function (){
                WdatePicker({
                    minDate: $("input[name='qo.beginDate']").val(),
                    maxDate: new Date()
                });
            });
        });
    </script>
</head>
<body>
<!-- ============================================================ -->
<%@include file="/WEB-INF/views/common/common_msg.jsp" %>
<!-- ============================================================ -->
<s:form id="searchForm" namespace="/" action="stockIncomeBill" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        业务时间
                        <s:date name="qo.beginDate" format="yyyy-MM-dd" var="bd"/>
                        <s:textfield name="qo.beginDate" cssClass="ui_input_txt02" value="%{bd}"/>
                        ~
                        <s:date name="qo.endDate" format="yyyy-MM-dd" var="ed"/>
                        <s:textfield name="qo.endDate" cssClass="ui_input_txt02" value="%{ed}"/>
                        仓库
                        <s:select list="#depots" name="qo.depotId" listKey="id" listValue="name"
                                  headerKey="-1" headerValue="所有仓库" cssClass="ui_select03"/>
                        订单状态
                        <s:select list="#{-1:'全部',0:'未审核',1:'已审核'}" name="qo.status" cssClass="ui_select02"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_redirect"
                               data-url="<s:url namespace="/" action="stockIncomeBill_input"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>订单编号</th>
                        <th>业务时间</th>
                        <th>仓库</th>
                        <th>入库总数量</th>
                        <th>入库总金额</th>
                        <th>制单人</th>
                        <th>审核人</th>
                        <th>审核状态</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.result">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" autocomplete="off" class="acb"
                                       data-eid="<s:property value="id"/>"/></td>
                            <td><s:property value="sn"/></td>
                            <td>
                                <s:date name="vdate" format="yyyy-MM-dd"/>
                            </td>
                            <td><s:property value="depot.name"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
                            <td><s:property value="inputUser.name"/></td>
                            <td><s:property value="auditor.name"/></td>
                            <td>
                                <s:if test="status==0"><span style="color:green">未审核</span></s:if>
                                <s:elseif test="status==1"><span style="color:red">已审核</span></s:elseif>
                            </td>
                            <td>
                                <s:if test="status==1">
                                    <s:url var="showUrl" namespace="/" action="stockIncomeBill_show">
                                        <s:param name="stockIncomeBill.id" value="id"/>
                                    </s:url>
                                    <a href="<s:property value="#showUrl"/>">查看</a>
                                </s:if>
                                <s:else>
                                    <s:url var="auditUrl" namespace="/" action="stockIncomeBill_audit">
                                        <s:param name="stockIncomeBill.id" value="id"/>
                                    </s:url>
                                    <a href="<s:property value="#auditUrl"/>">审核</a>

                                    <s:url var="editUrl" namespace="/" action="stockIncomeBill_input">
                                        <s:param name="stockIncomeBill.id" value="id"/>
                                    </s:url>
                                    <a href="<s:property value="#editUrl"/>">编辑</a>

                                    <s:url var="deleteUrl" namespace="/" action="stockIncomeBill_delete">
                                        <s:param name="stockIncomeBill.id" value="id"/>
                                    </s:url>
                                    <a href="javascript:;" class="btn_delete"
                                       data-url='<s:property value="#deleteUrl"/>'>删除</a>
                                </s:else>


                            </td>
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

