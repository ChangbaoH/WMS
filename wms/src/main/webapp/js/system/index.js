//加载当前日期
function loadDate(){
	var time = new Date();
	var myYear = time.getFullYear();
	var myMonth = time.getMonth() + 1;
	var myDay = time.getDate();
	if (myMonth < 10) {
		myMonth = "0" + myMonth;
	}
	document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."	+ myDay;
}
//====================================================================
//========zTree=======
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	},
	callback:{
		onClick: function (event,treeId,treeNode){
			if(treeNode.action){
				//切换当前位置
				$("#here_area").html("当前位置：系统&nbsp;>&nbsp;"+treeNode.name);
				//打开对应页面
				$("#rightMain").prop("src",treeNode.action+".action");
			}
		}
	},
	async: {
		enable: true,
		url:"/systemMenu_loadMenusByParentSn.action",
		autoParam:["sn=qo.parentSn"],
	}
};

//动态菜单树
var zNodes={
	'business':[
		{ id:1, pId:0, name:"业务管理", open:true, isParent:true, sn:'business'},

	],
	'system':[
		{ id:1, pId:0, name:"系统管理", open:true, isParent:true, sn:'system'},

	],
	'chart':[
		{ id:1, pId:0, name:"报表管理", open:true, isParent:true, sn:'chart'},

	]
};

//加载菜单
function loadMenu(sn){
	$.fn.zTree.init($("#dleft_tab1"), setting, zNodes[sn]);
}
//==================================================================
$(function(){
	//文档加载完毕，默认加载business菜单树
	loadMenu('business');
	//-----------------------------------------------
	//菜单面板的切换
	$("#TabPage2 li").click(function (){
		//先移除所有li的选中状态
		$.each($("#TabPage2 li"),function (index,item){
			$(item).removeClass("selected");
			$(item).children("img").prop("src","/images/common/"+(index+1)+".jpg");
		});
		//选中的哪一个根菜单就设置selected样式
		$(this).addClass("selected");
		$(this).children("img").prop("src","/images/common/"+($(this).index()+1)+"_hover.jpg");

		//修改模块的标题
		$("#nav_module img").prop("src","/images/common/module_"+($(this).index()+1)+".png")
		//切换菜单,加载该菜单对应的菜单树
		loadMenu($(this).data("rootmenu"));

	});

	//-----------------------------------------------
	//加载日期
	loadDate();
	// 显示隐藏侧边栏
	$("#show_hide_btn").click(function() {
		switchSysBar();
	});
});
//===================================================================
/**
 * 隐藏或者显示侧边栏
 * 
 **/
function switchSysBar(flag){
	var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
	if( flag==true ){	// flag==true
		left_menu_cnt.show(500, 'linear');
		side.css({width:'280px'});
		$('#top_nav').css({width:'77%', left:'304px'});
    	$('#main').css({left:'280px'});
	}else{
        if ( left_menu_cnt.is(":visible") ) {
			left_menu_cnt.hide(10, 'linear');
			side.css({width:'60px'});
        	$('#top_nav').css({width:'100%', left:'60px', 'padding-left':'28px'});
        	$('#main').css({left:'60px'});
        	$("#show_hide_btn").find('img').attr('src', '/images/common/nav_show.png');
        } else {
			left_menu_cnt.show(500, 'linear');
			side.css({width:'280px'});
			$('#top_nav').css({width:'77%', left:'304px', 'padding-left':'0px'});
        	$('#main').css({left:'280px'});
        	$("#show_hide_btn").find('img').attr('src', '/images/common/nav_hide.png');
        }
	}
}


