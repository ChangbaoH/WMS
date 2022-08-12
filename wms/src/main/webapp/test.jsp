<%--
  Created by IntelliJ IDEA.
  User: 14378
  Date: 2022/6/10
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript">
        $(function (){
            $.dialog({
                title:'操作提示',
                icon:'face-smile',
                content:'kkkk',
                resize:false,
                drag:false,
                ok:function (){
                    alert("kkkkkkkk");
                },
                cancel:true

            });
        });
    </script>
</head>
<body>
test
</body>
</html>
