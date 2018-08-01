var navPaths = [];
$(function(){
	
	
	loadRootMenu();
	
	initLayerUI();

})



//加载菜单信息
function loadRootMenu(){
	$.ajax({
		url : path+"/user/getMenus",
		type : 'post',
		cache : false,
		async: true,
		dataType : 'json',
		success : function(data) {

			if(data.code==1){
				var menus = "";
				var rdata=data.data;
				for(var i=0;i<rdata.length;i++){
		        	  menus += "<li><a href='javascript:void(0)'  class='dropdown-toggle'> <i class='"+rdata[i].icon+"'></i> ";
		        	  menus +="<span class='menu-text'>"+rdata[i].pageName+"</span> <b class='arrow icon-angle-down'></b>";

		        	  var childData=rdata[i].menuFunctions;
		        	  if(childData!=null){
			        	  menus +="</a><ul class='submenu'>";
		        		  for(var j=0;j<childData.length;j++){
		        		    //type=0，证明还有子节点
		        		 //  if(childData[j].type==1){
		        			   
		        		      menus +="<li menuCode='"+childData[j].id+"' menuName='"+childData[j].pageName+"' url='/"+childData[j].url+"' onclick='openTab(this);'><a href='javascript:void(0);'> " ;
		        		      menus +="<i class='icon-double-angle-right'></i>"+ childData[j].pageName+"</a></li>";	
		        		 //    }
		        		  }
		        		  menus+="</ul>"
		        	  }
				       menus+="</li>";
				}
				$("#menuList").html($(menus));
        	}else{
        		//layerUI.showMsg({content:data.message});
        	}
		},
		error : function() {
			layerUI.showMsg({content:"系统异常，请重试！"});
		}
	});
}


function openTab(o){
	var menuId = $(o).attr("menuCode");
	var menuName = $(o).attr("menuName");
	var url = $(o).attr("url");
	navPaths.push(menuName);
	$(".active").attr("class","");
	$(o).attr("class","active");
	$("#pageNotice").html(menuName);
	//创建导航路径
	//$("#breadcrumb").html(createBreadcrumb(navPaths));
	//创建tab页面
	if (url.indexOf("http://")==-1){
		url = path + url;
	}
	tabActive.tabAdd(menuId,menuName,url);
	//子页面加载
	/*if (url.indexOf("http://") == 0) {
		$("#tabContent").html('<iframe class="body-content" id="tabContent" src="'+url+'" frameborder="0" scrolling="auto"  style="width:100%; height: calc(100vh - 100px);"></iframe>');
	} else if (url.indexOf("cda") >= 0 || url.indexOf("dataManager") >= 0) {
		$("#tabContent").html('<iframe class="body-content" id="tabContent" src="'+basePath+url+'" frameborder="0" scrolling="auto"  style="width:100%; height: calc(100vh - 100px);"></iframe>');
	} else {
		$("#tabContent").load(basePath + url);
	}*/
}

var tabActive;
//tab按钮加载
function initLayerUI() {
	layui.use('element', function() {
		var $ = layui.jquery, 
		element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块
		//触发事件
		tabActive = {
			tabAdd : function(menuId, menuName, url) {
				//如果该菜单还未被打开过
				if(!this.existTab(menuId)){
					//新增一个Tab项
					element.tabAdd('tabcard', {
						title : menuName ,
						content : "<iframe id='frame"+menuId+"' src='"+url+"' frameborder='0' scrolling='auto'  style='width:100%; height: calc(100vh - 90px);'></iframe>",
						id : menuId
					})
				}
				element.tabChange('tabcard', menuId);
				var currTab = this.getCurrTab();
				//添加右击事件
				currTab.bind("contextmenu",function(){
					tabActive.openTabMenu(currTab);
					return false;
				})
				$(document).bind("click",function(e){
				  var tabMenu = $("#tabMenu");
				  if(e.target!=tabMenu[0]){
					  tabMenu.hide();
				  }
				})
			},
			existTab:function(tabId){//判断是否已经打开过
				return $("#layuiTabTitle>li[lay-id='"+tabId+"']").length>0;
			},
			deleteOtherTab:function(o){//删除其它所有的tab页
				var tabs = this.getAllTabs();
				tabs.each(function(i, tab){
					if($(tab).attr("lay-id")!=$(o).attr("lay-id")){
						element.tabDelete("tabcard",$(tab).attr("lay-id"));
					}
				})
			},
			deleteAllTab : function(o) {//删除所有tab
				var tabs = this.getAllTabs();
				tabs.each(function(i, tab){
					element.tabDelete("tabcard", $(tab).attr("lay-id"));
				})
			},
			deleteCurrTab : function(o) {//删除当前tab
				element.tabDelete("tabcard", $(o).attr("lay-id"));
			},
			deleteRightTabs : function(o) {//删除右侧所有的tab
				var tabs = this.getRightTabs(o);
				tabs.each(function(i, tab){
					element.tabDelete("tabcard", $(tab).attr("lay-id"));
				})
			},
			refreshCurrTab:function(o){//刷新当前页
				var targetFrame = $("#frame"+$(o).attr("lay-id"));
				var url = targetFrame.attr("src");
				if(url.indexOf("?")!=-1){
					url += "&t="+new Date().getTime()
				}else{
					url += "?t="+new Date().getTime()
				}
				targetFrame.attr("src", url);
			},
			deleteLeftTabs : function(o) {//删除左侧所有的tab
				var tabs = this.getLeftTabs(o);
				tabs.each(function(i, tab){
					element.tabDelete("tabcard", $(tab).attr("lay-id"));
				})
			},
			getAllTabs:function(){//获取所有的tab
				return $("#layuiTabTitle>li");
			},
			getCurrTab:function(){//获取当前选中的tab
				return $("#layuiTabTitle .layui-this");
			},
			getRightTabs:function(o){//获取右侧所有的tab
				return $(o).nextAll();
			},
			getLeftTabs:function(o){//获取左侧所有的tab
				return $(o).prevAll();
			},
			openTabMenu:function(o){
				var left = $(o).offset().left;
				var top = $(o).offset().top;
				var tabMenu = $("#tabMenu");
				tabMenu.find("a").unbind("click");
				tabMenu.find(".freshCurrTab").bind("click",function(){tabActive.refreshCurrTab(o)});
				tabMenu.find(".closeCurrTab").bind("click",function(){tabActive.deleteCurrTab(o)});
				tabMenu.find(".closeRightTab").bind("click",function(){tabActive.deleteRightTabs(o)});
				tabMenu.find(".closeLeftTab").bind("click",function(){tabActive.deleteLeftTabs(o)});
				tabMenu.find(".closeOtherTab").bind("click",function(){tabActive.deleteOtherTab(o)});
				tabMenu.find(".closeAllTab").bind("click",function(){tabActive.deleteAllTab(o)});
				tabMenu.css("width","120px");
				tabMenu.css("minWidth","120px");
				tabMenu.css("left", left-150);
				tabMenu.css("top", top-20);
				tabMenu.show(2);
			}
		}
	});
}