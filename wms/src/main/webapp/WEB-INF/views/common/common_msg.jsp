<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
    <s:if test="hasActionMessages()">
        var msg = '<s:property value="actionMessages[0]"/>';
        $.dialog({
            title:"温馨提示",
            content:msg,
            icon:"face-smile",
            ok:true
        });
    </s:if>

    <s:if test="hasActionErrors()">
        var msg = '<s:property value="actionErrors[0]"/>';
        $.dialog({
            title:"温馨提示",
            content:msg,
            icon:"face-smile",
            ok:true
        });
    </s:if>
</script>











<%--
<s:if test="hasActionErrors()">
    var msg  = "<s:property value="actionErrors"/>";
    $.dialog({
    title:"温馨提示",
    content:msg,
    icon:"error",
    ok:true
    });
</s:if>


<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript">
	<s:if test="hasActionErrors()">
		var msg  = "<s:property value="actionErrors"/>";
		$.dialog({
			title:"温馨提示",
			content:msg,
			icon:"error",
			ok:true
		});
	</s:if>
	<s:if test="hasActionMessages()">
		var msg = "<s:property value="actionErrors[0]"/>";
		$.dialog({
			title:"温馨提示",
			content:msg,
			icon:"face-smile",
			ok:true
		});
	</s:if>
</script>--%>

	
	