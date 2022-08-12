/**加载权限**/
$(function (){
    $(".btn_load").click(function (){
        var url = $(this).data("url");
        $.dialog({
            title:"温馨提示",
            content:"确定要加载所有的权限吗?",
            icon:"face-smile",
            cancel:true,
            ok:function (){
                var dialog = $.dialog({
                    title:"操作提示",
                    icon:"face-smile"
                });

                $.get(url,function (){
                    dialog.content("权限加载完毕!").button({
                        name:'朕知道了',
                        callback:function (){
                            window.location.reload();
                        }
                    });
                })
            }
        });
    });
})

