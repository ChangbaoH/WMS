//权限移动
$(function (){
    //1.左右移动
    $("#selectAll").click(function () {
        $(".all_selectList option").appendTo($(".selected_selectList"));
    });
    $("#deselectAll").click(function () {
        $(".selected_selectList option").appendTo($(".all_selectList"));
    });

    $("#select").click(function () {
        $(".all_selectList option:selected").appendTo($(".selected_selectList"));
    });
    $("#deselect").click(function () {
        $(".selected_selectList option:selected").appendTo($(".all_selectList"));
    });

    //2.从权限列表中去除已经分配的权限
    var ids = $.map($(".selected_selectList option"),function (item){
        return item.value;
    })
    $.each($(".all_selectList option"),function (index,item) {
        var id = item.value;
        if($.inArray(id,ids)>=0){
            $(item).remove();
        }
    })
});

//菜单移动
$(function (){
    //1.左右移动
    $("#mselectAll").click(function () {
        $(".all_menus_selectList option").appendTo($(".selected_menus_selectList"));
    });
    $("#mdeselectAll").click(function () {
        $(".selected_menus_selectList option").appendTo($(".all_menus_selectList"));
    });

    $("#mselect").click(function () {
        $(".all_menus_selectList option:selected").appendTo($(".selected_menus_selectList"));
    });
    $("#mdeselect").click(function () {
        $(".selected_menus_selectList option:selected").appendTo($(".all_menus_selectList"));
    });

    //2.从权限列表中去除已经分配的权限
    var ids = $.map($(".selected_menus_selectList option"),function (item){
        return item.value;
    })
    $.each($(".all_menus_selectList option"),function (index,item) {
        var id = item.value;
        if($.inArray(id,ids)>=0){
            $(item).remove();
        }
    })
});

$(function () {
    $("#editForm").submit(function (){
        $(".selected_selectList option").prop("selected",true);
        $(".selected_menus_selectList option").prop("selected",true);
    });
});