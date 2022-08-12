<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
  <title>信息管理系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
  <link href="/style/common_style.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="/js/jquery/jquery.js"></script>
  <script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
  <script type="text/javascript" src="/js/plugins/jquery-validation/jquery.validate.min.js"></script>
  <script type="text/javascript" src="/js/system/stockOutcomeBill.js"></script>
  <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript">

    //清除数据
    function clearTrData(copyTr){
      copyTr.find("[tag=name]").val("");
      copyTr.find("[tag=pid]").val("");
      copyTr.find("[tag=salePrice]").val("");
      copyTr.find("[tag=number]").val("");
      copyTr.find("[tag=remark]").val("");
      copyTr.find("[tag=brand]").text("");
      copyTr.find("[tag=amount]").text("");
    }
    $(function (){
      //使用on绑定
      $("#edit_table_body").on("click",".searchproduct",function (){//选择采购的货品
        var json = window.showModalDialog("product_selectProductList.action","","dialogWidth=900px;dialogHeight=700px");
        console.debug(json);
        var tr = $(this).closest("tr");
        tr.find("[tag=name]").val(json.name);
        tr.find("[tag=pid]").val(json.id);
        tr.find("[tag=salePrice]").val(json.salePrice);
        tr.find("[tag=brand]").text(json.brandName);
      }).on("change","[tag=salePrice],[tag=number]",function (){//显示小计
        var tr = $(this).closest("tr");
        var salePrice = parseFloat(tr.find("[tag=salePrice]").val());
        var number = parseFloat(tr.find("[tag=number]").val());
        if(salePrice && number){
          tr.find("[tag=amount]").text((salePrice*number).toFixed(2));
        }
      }).on("click",".removeItem",function (){//删除明细
        var tr = $(this).closest("tr");
        if($("#edit_table_body tr").size()==1){//最后一行清空
          clearTrData(tr);
        }else {//删除当前行
          tr.remove();
        }
      });
      //添加明细
      $(".appendRow").click(function (){
        var copyTr = $("#edit_table_body tr:first").clone();//克隆一行
        //清除数据
        clearTrData(copyTr);
        copyTr.appendTo($("#edit_table_body"));
      });
      //提交表单
      $(".btn_submit").click(function (){
        //重新设置每条明细的name
        $.each($("#edit_table_body tr"),function (index,item){
          var tr = $(item);
          tr.find("[tag=pid]").prop("name","stockOutcomeBill.items["+index+"].product.id");
          tr.find("[tag=salePrice]").prop("name","stockOutcomeBill.items["+index+"].salePrice");
          tr.find("[tag=number]").prop("name","stockOutcomeBill.items["+index+"].number");
          tr.find("[tag=remark]").prop("name","stockOutcomeBill.items["+index+"].remark");
        })
        $("#editForm").submit();
      });
      //时间
      $("input[name='stockOutcomeBill.vdate']").addClass("Wdate").click(function (){
        WdatePicker({
          maxDate: new Date()
        });
      });
    });
  </script>
</head>
<body>
<!-- ============================================================ -->
<%@include file="/WEB-INF/views/common/common_msg.jsp"%>
<!-- ============================================================ -->
<s:form name="editForm" action="stockOutcomeBill_saveOrUpdate.action" method="post" id="editForm">
  <s:hidden name="stockOutcomeBill.id"/>
  <div id="container">
    <div id="nav_links">
      <span style="color: #1A5CC6;">销售出库单编辑</span>
      <div id="page_close">
        <a>
          <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
        </a>
      </div>
    </div>
    <div class="ui_content">
      <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
        <tr>
          <td class="ui_text_rt" width="140">订单编号</td>
          <td class="ui_text_lt">
            <s:textfield name="stockOutcomeBill.sn" cssClass="ui_input_txt02"/>
          </td>
        </tr>

        <tr>
          <td class="ui_text_rt" width="140">仓库</td>
          <td class="ui_text_lt">
            <s:select list="#depots" name="stockOutcomeBill.depot.id" listKey="id" listValue="name" cssClass="ui_select03"/>
          </td>
        </tr>

        <tr>
          <td class="ui_text_rt" width="140">客户</td>
          <td class="ui_text_lt">
            <s:select list="#clients" name="stockOutcomeBill.client.id" listKey="id" listValue="name" cssClass="ui_select03"/>
          </td>
        </tr>

        <tr>
          <td class="ui_text_rt" width="140">业务时间</td>
          <td class="ui_text_lt">
            <s:date name="stockOutcomeBill.vdate" format="yyyy-MM-dd" var="ov"/>
            <s:textfield name="stockOutcomeBill.vdate" cssClass="ui_input_txt02" value="%{ov}"/>
          </td>
        </tr>

        <tr>
          <td class="ui_text_rt" width="140">明细</td>
        </tr>
        <tr>
          <td></td>
          <td>
            <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
            <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
              <thead>
              <tr>
                <th width="200">货品</th>
                <th width="120">品牌</th>
                <th width="80">价格</th>
                <th width="80">数量</th>
                <th width="80">金额小计</th>
                <th width="150">备注</th>
                <th width="80">操作</th>
              </tr>
              </thead>
              <tbody id="edit_table_body">
              <!-- 新增 -->
              <s:if test="stockOutcomeBill.id==null">
                <tr>
                  <td>
                    <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt02" tag="name"/>
                    <img src="/images/common/search.png" class="searchproduct"/>
                    <s:hidden name="stockOutcomeBill.items.product.id" tag="pid"/>
                  </td>
                  <td><span tag="brand"></span></td>
                  <td><s:textfield tag="salePrice" name="stockOutcomeBill.items.salePrice"
                                   cssClass="ui_input_txt04"/></td>
                  <td><s:textfield tag="number" name="stockOutcomeBill.items.number"
                                   cssClass="ui_input_txt04"/></td>
                  <td><span tag="amount"></span></td>
                  <td><s:textfield tag="remark" name="stockOutcomeBill.items.remark"
                                   cssClass="ui_input_txt02"/>
                  </td>
                  <td>
                    <a href="javascript:;" class="removeItem">删除明细</a>
                  </td>
                </tr>
              </s:if>
              <!-- 更改 -->
              <s:else>
                <s:iterator value="stockOutcomeBill.items">
                  <tr>
                    <td>
                      <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt02" tag="name" name="product.name"/>
                      <img src="/images/common/search.png" class="searchproduct"/>
                      <s:hidden name="product.id" tag="pid"/>
                    </td>
                    <td><span tag="brand"><s:property value="product.brand.name"/></span></td>
                    <td><s:textfield tag="salePrice" name="salePrice"
                                     cssClass="ui_input_txt04"/></td>
                    <td><s:textfield tag="number" name="number"
                                     cssClass="ui_input_txt04"/></td>
                    <td><span tag="amount"><s:property value="amount"/></span></td>
                    <td><s:textfield tag="remark" name="remark"
                                     cssClass="ui_input_txt02"/>
                    </td>
                    <td>
                      <a href="javascript:;" class="removeItem">删除明细</a>
                    </td>
                  </tr>
                </s:iterator>
              </s:else>

              </tbody>
            </table>
          </td>
        </tr>


        <tr>
          <td>&nbsp;</td>
          <td class="ui_text_lt">
            &nbsp;<input type="button" value="确定保存" class="ui_input_btn01 btn_submit"/>
            &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
          </td>
        </tr>
      </table>
    </div>
  </div>
</s:form>
</body>
</html>
