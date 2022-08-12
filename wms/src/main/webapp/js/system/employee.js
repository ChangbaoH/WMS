//表单校验
$(function (){
    if ($("#editForm").size()>0){
        $("#editForm").validate({
            //编写规则
            rules:{
                'employee.name':{
                    required:true,
                    rangelength:[2,16]
                },
                'employee.password':{
                    required:true,
                    minlength:4
                },
                'repassword':{
                    required:true,
                    equalTo:"#password"
                },
                'employee.age':{
                    required:true,
                    range:[18,150]
                }
            },
            //校验失败的信息
            messages:{
                'employee.name':{
                    required:"用户名必填",
                    rangelength:"用户名长度为{0}-{1}个字符"
                },
                'employee.password':{
                    required:"密码必填",
                    minlength:"密码长度须大于4"
                },
                'repassword':{
                    required:"验证密码必填",
                    equalTo:"验证密码错误"
                },
                'employee.age':{
                    required:"年龄必填",
                    range:"年龄需在{0}-{1}之间"
                }
            }

        });
    }

});


