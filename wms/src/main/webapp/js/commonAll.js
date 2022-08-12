/**高级查询和分页**/
$(function (){
	$(":input[name='qo.currentPage']")
		.prop("type","number")
		.css("width","50px")
		.prop("max",$(".total_page").data("page"))
		.prop("min","1");
	//翻页
	$(".btn_page").click(function (){
		//获取去往的页面
		var pageNo = $(this).data("page") || $(":input[name='qo.currentPage']").val();
		//把该页面设置到qo.currentPage中
		$(":input[name='qo.currentPage']").val(pageNo);
		//提交表单
		$("#searchForm").submit();
	});
	//选择分页最多的条数
	$(":input[name='qo.pageSize']").change(function (){
		$("#searchForm").submit();
	});
});

/**跳转**/
$(function (){
     $(".btn_redirect").click(function (){
     	window.location.href=$(this).data("url");
     });

});

/**删除操作**/
$(function (){
	$(".btn_delete").click(function () {
		var url = $(this).data("url");
		$.dialog({
			title:"温馨提示",
			content:"你确定要删除吗?",
			icon:"face-smile",
			cancel:true,
			ok:function (){
				var dialog = $.dialog({
					title:"操作提示",
					icon:"face-smile"
				});

				$.get(url,function (data){
					dialog.content(data).button({
						name:'朕知道了',
						callback:function (){
							window.location.reload();
						}
					});
				});
			}
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

/**批量删除**/
/**禁用将表单序列化**/
jQuery.ajaxSettings.traditional = true;
$(function (){
	//全选
	$("#all").click(function () {
		$(".acb").prop("checked",this.checked)
	});
	//点击批量删除
	$(".btn_batch_delete").click(function (){
		var url = $(this).data("url");
		/*        var ids = [];
                $.each($(".acb:checked"),function (index,item){
                    ids[index] = $(item).data("eid");
                });*/
		var ids = $.map($(".acb:checked"),function (item){
			return $(item).data("eid");
		});
		if (ids.length == 0){
			$.dialog({
				title:"温馨提示",
				content:"请选择需要选择的条目",
				icon:"face-smile",
				ok:true
			});
			return;
		}

		$.dialog({
			title:"温馨提示",
			content:"你确定要删除这批数据吗吗?",
			icon:"face-smile",
			cancel:true,
			ok:function (){
				var dialog = $.dialog({
					title:"操作提示",
					icon:"face-smile"
				});
				$.get(url,{ids:ids},function (){
					dialog.content("删除成功!").button({
						name:'朕知道了',
						callback:function (){
							window.location.reload();
						}
					});
				});
			}
		});

	});
});