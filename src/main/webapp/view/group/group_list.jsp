<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>群组管理</title>
    <script type="text/javascript">var path = "<%=path%>";</script>

    <link href="plug-in/ace/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="plug-in/font-awesome/css/font-awesome.css" rel="stylesheet" />
	<link rel="stylesheet" href="plug-in/ace/assets/css/ace.min.css" />
	<link rel="stylesheet" href="plug-in/ace/assets/css/ace-rtl.min.css" />
	<link rel="stylesheet" href="plug-in/ace/assets/css/ace-skins.min.css" />
	<link rel="stylesheet" href="plug-in/ace/assets/css/datepicker.css" />
	<link rel="stylesheet" href="plug-in/ace/assets/css/chosen.css" />
	<link rel="stylesheet" href="plug-in/layui/css/layui.css">
	<script type="text/javascript" src="plug-in/layui/layui.js"></script>
	<script type="text/javascript" src="plug-in/layui/custom/custom_layer.js"></script>
	
	<script type="text/javascript" src="plug-in/jquery/jquery-1.9.1.js"></script>
	<script src="plug-in/ace/assets/js/ace-extra.min.js"></script>
	<script src="plug-in/ace/assets/js/bootstrap.min.js"></script>
	<script src="plug-in/ace/assets/js/typeahead-bs2.min.js"></script>
	<script src="plug-in/ace/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script src="plug-in/ace/assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="plug-in/ace/assets/js/markdown/markdown.min.js"></script>
	<script src="plug-in/ace/assets/js/markdown/bootstrap-markdown.min.js"></script>
	<script src="plug-in/ace/assets/js/jquery.hotkeys.min.js"></script>
	<script src="plug-in/ace/assets/js/bootstrap-wysiwyg.min.js"></script>
	<script src="plug-in/ace/assets/js/bootbox.min.js"></script>
	<script src="plug-in/ace/assets/js/ace-elements.min.js"></script>
	<script src="plug-in/ace/assets/js/ace.min.js"></script>
	<script src="plug-in/ace/assets/js/chosen.jquery.min.js"></script>

	

	<script src="plug-in/ace/assets/js/fuelux/data/fuelux.tree-sampledata.js"></script>
	<script src="plug-in/ace/assets/js/fuelux/fuelux.tree.min.js"></script>
	<script src="plug-in/ace/assets/js/ace-elements.min.js"></script>
  </head>
  <body>
	<div class="row">
		<div class="col-xs-12">
			<div class="row">
				<div class="col-sm-6" style="width:100%;">
					<div class="widget-box">
						<div class="widget-header header-color-blue2">
							<h4 class="lighter smaller">菜单树</h4>
						</div>
						<div class="widget-body">
							<div class="widget-main padding-8">
								<div id="menuTree" class="tree"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<style>
	#contextMenu{
	   display:none;
	   position: absolute;
	}
	</style>
	<ul id="contextMenu" class="ui-menu ui-widget ui-widget-content ui-corner-all"
		role="menu" tabindex="0" aria-activedescendant="ui-id-5">
		<li id='menuAdd' class="ui-state-disabled ui-menu-item" role="presentation"
			aria-disabled="true"><a href="javascript:void(0);" id="ui-id-3"
			class="ui-corner-all" tabindex="-1" role="menuitem"  onclick='addMenu();'>新增</a></li>
		<li id='menuEdit' class="ui-menu-item" role="presentation"><a href="javascript:void(0);"
			id="ui-id-4" class="ui-corner-all" tabindex="-1" role="menuitem" onclick='editMenu();'>修改</a>
		</li>
		<li id='menuDelete' class="ui-menu-item" role="presentation"><a href="javascript:void(0);"
			id="ui-id-5" class="ui-corner-all" tabindex="-1" role="menuitem" onclick='deleteMenu();'>删除</a>
		</li>
		<li id='menuAddRoot' class="ui-menu-item" role="presentation"><a href="javascript:void(0);"
			id="ui-id-6" class="ui-corner-all" tabindex="-1" role="menuitem" onclick='addRootMenu();'>新增根节点</a>
		</li>
	</ul>
	<div id="addDiv" style="display:none">
		    <div style="width:300px;margin:0 auto;">
		        <div class="input-group" >
			      <label>群组名称：</label>
			      <br/>
				  <input style="width:300px;height:30px;" id="rightId" type="text" placeholder="" value=""/>
			    </div>
		    </div>
	</div>
	<script type="text/javascript">
	    var currDom = null;
	    var canAddMenu = true;
	    var canAddRootMenu = true;
	    var currData = null;
	    $(function(){
	        $.ajax({
				url : "<%=path%>/group/getGroupTree",
				data : {},
				type : 'post',
				async: false,
				dataType : 'json',
				success : function(data) {
					console.log(data);
				    var treeData = new DataSourceTree({
						data : data.data
					});
					var treee = $('#menuTree').ace_tree(
					{
						dataSource : treeData,
						multiSelect : true,
						loadingHTML : '<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
						'open-icon' : 'icon-minus',
						'close-icon' : 'icon-plus',
						'selectable' : false,
						'selected-icon' : 'icon-ok',
						'unselected-icon' : 'icon-remove'
					});
					document.getElementById("menuTree").oncontextmenu = function(e){
						　return false;
					};
					$(document).on("click", function (e) {
					  var contextMenu = $("#contextMenu");
					  if(e.target!=contextMenu[0]){
					     contextMenu.hide();
					  }
					});
				},
				error : function() {
				    layerUI.showMsg({content:"系统异常，请重试！"});
				}
			});
			
	    })
	    
	    //右键操作
	    function rightClick(evt, data){
	    	console.log(data);
	           currDom = $(evt);
	           currData = data;
	           var addMenuDom = $("#contextMenu").find("#menuAdd");
	           var addRootMenuDom = $("#contextMenu").find("#menuAddRoot");
	           if(data.type=='folder'){
	             addMenuDom.attr("class","ui-menu-item");
	             addRootMenuDom.attr("class","ui-menu-item");
	             canAddMenu = true;
	             canAddRootMenu = true;
	           }else{
	             addMenuDom.attr("class","ui-menu-item ui-state-disabled");
	             addRootMenuDom.attr("class","ui-menu-item ui-state-disabled");
	             canAddMenu = false;
	             canAddRootMenu = false;
	           }
			   var className = $(currDom).attr("class");
			   var x = 0;
			   var y = 0;
			   x = currDom.offset().left;
			   y = currDom.offset().top
			   $("#contextMenu").css({
				  top:y+10,
				  left:x+event.clientX-20
			   });
			   $("#contextMenu").show();
	    }
	    //添加菜单
	    function addMenu(){
	     if(!canAddMenu) return;
	     var content = $("#addDiv").html();
	     layerUI.openWin({
				type:1,
				title:"权限信息添加",
				closeBtn:1,
				maxmin:false,
				offset: 'auto',
				shade:0.00001,
				area: ['380px', '500px'],
				content:content,
			    btns: ['保存','清空','关闭'],
			    yes:function(layero, index){
			        var parentCode = $("#parentCode").val(); 
			        var rightName = $("#rightName").val(); 
			        var permissionCode = $("#permissionCode").val(); 
			        var rightUrl = $("#rightUrl").val(); 
			        var menuIcon = $("#menuIcon").val(); 
			        var rightType = $("#rightType").val();
			        var isLeaf = $("#isLeaf").val();
			        var sortNo = $("#sortNo").val();
			        var remark = $("#remark").val(); 
			        var rightState = $("#rightState").val(); 
			        if(rightName==""){
			          layerUI.showMsg({content:"请输入权限名称！"});
			          return;
			        }
			         if(sortNo==""){
			          layerUI.showMsg({content:"请输入排序编号！"});
			          return;
			        }
			        var params = {
			            parentCode:parentCode,
			            permissionCode:permissionCode,
				        rightName:rightName,
				        rightUrl:rightUrl,
				        rightType:rightType,
				        isLeaf:isLeaf,
				        sortNo:sortNo,
						remark:remark,
						rightState:rightState
					};
	        		if(doSave(params)) layero.close(index);
			    },
			    btn2:function(layero, index){
			        $("#rightName").val(""); 
			        $("#rightUrl").val(""); 
			        $("#permissionCode").val("");
			        $("#rightType").val(1);
			        $("#sortNo").val("");
			        $("#remark").val(""); 
			        $("#rightState").val(1); 
			    },
		        btn3:function(layero, index){
		        	layero.close(index);
			    },
			    //这里必须清空  否则页面ID重复   会发生意想不到的问题
			    success:function(){
			        $("#addDiv").empty();
			        $("#menuIcon").parent().hide();
			        $("#isLeaf").attr("disabled","disabled");
			        $("#parentCode").val(currData.rightCode); 
			    },
			    //这里重新将影藏div中的内容回写，否则再次打开弹出框时将没有内容
			    end:function(){
			       $("#addDiv").html(content);
			    }
			});
	    }
	    //添加根节点菜单
	    function addRootMenu(){
	      if(!canAddRootMenu) return;
	      var content = $("#addDiv").html();
	     layerUI.openWin({
				type:1,
				title:"权限信息添加",
				closeBtn:1,
				offset: 'auto',
				maxmin:false,
				shade:0.00001,
				area: ['380px', '545px'],
				content:content,
			    btns: ['保存','清空','关闭'],
			    yes:function(layero, index){
			        var parentCode = $("#parentCode").val(); 
			        var rightName = $("#rightName").val(); 
			        var rightUrl = $("#rightUrl").val(); 
			        var permissionCode = $("#permissionCode").val();
			        var menuIcon = $("#menuIcon").val();
			        var rightType = $("#rightType").val();
			        var isLeaf = $("#isLeaf").val();
			        var sortNo = $("#sortNo").val();
			        var remark = $("#remark").val(); 
			        var rightState = $("#rightState").val(); 
			        if(rightName==""){
			          layerUI.showMsg({content:"请输入权限名称！"});
			          return;
			        }
			         if(sortNo==""){
			          layerUI.showMsg({content:"请输入排序编号！"});
			          return;
			        }
			        var params = {
			            parentCode:parentCode,
				        rightName:rightName,
				        permissionCode:permissionCode,
				        rightUrl:rightUrl,
				        icon:menuIcon,
				        rightType:rightType,
				        isLeaf:isLeaf,
				        sortNo:sortNo,
						remark:remark,
						rightState:rightState
					};
	        		if(doSave(params)) layero.close(index);
			    },
			    btn2:function(layero, index){
			        $("#rightName").val(""); 
			        $("#rightUrl").val(""); 
			        $("#rightType").val(1);
			        $("#menuIcon").val(""); 
			        $("#permissionCode").val("");
			        $("#sortNo").val("");
			        $("#isLeaf").val("0");
			        $("#remark").val(""); 
			        $("#rightState").val(1); 
			    },
		        btn3:function(layero, index){
		        	layero.close(index);
			    },
			    //这里必须清空  否则页面ID重复   会发生意想不到的问题
			    success:function(){
			        $("#addDiv").empty();
			        $("#menuIcon").parent().show();
			        $("#isLeaf").val("0"); 
			        $("#isLeaf").attr("disabled","disabled");
			        $("#parentCode").val(0); 
			    },
			    //这里重新将影藏div中的内容回写，否则再次打开弹出框时将没有内容
			    end:function(){
			       $("#addDiv").html(content);
			    }
			});
	    }
	    //添加菜单
	    function editMenu(){
	      var area = ['380px', '490px'];
	      if(currData.leaf=='0'){
	         area[1] = '550px';
	      }
	      var content = $("#addDiv").html();
	         layerUI.openWin({
				type:1,
				title:"权限信息修改",
				closeBtn:1,
				maxmin:false,
				shade:0.00001,
				area: area,
				content:content,
			    btns: ['保存','重置','关闭'],
			    yes:function(layero, index){
			        var rightId = $("#rightId").val(); 
			        var parentCode = $("#parentCode").val(); 
			        var permissionCode = $("#permissionCode").val();
			        var rightName = $("#rightName").val(); 
			        var rightUrl = $("#rightUrl").val();
			        var menuIcon = $("#menuIcon").val();
			        var rightType = $("#rightType").val();
			        var isLeaf = $("#isLeaf").val();
			        var sortNo = $("#sortNo").val();
			        var remark = $("#remark").val(); 
			        var rightState = $("#rightState").val(); 
			        if(rightName==""){
			          layerUI.showMsg({content:"请输入权限名称！"});
			          return;
			        }
			         if(sortNo==""){
			          layerUI.showMsg({content:"请输入排序编号！"});
			          return;
			        }
			        var params = {
			            rightId:rightId,
			            rightCode:currData.rightCode,
			            parentCode:parentCode,
			            icon:menuIcon,
			            permissionCode:permissionCode,
				        rightName:rightName,
				        rightUrl:rightUrl,
				        rightType:rightType,
				        isLeaf:isLeaf,
				        sortNo:sortNo,
						remark:remark,
						rightState:rightState
					};
	        		if(doSave(params)) layero.close(index);
			    },
			    btn2:function(layero, index){
			        $("#rightName").val(currData.name); 
			        $("#rightUrl").val(currData.url); 
			        $("#permissionCode").val(currData.permissionCode);
			        $("#rightType").val(currData.rightType);
			        $("#menuIcon").val(currData.icon);
			        $("#isLeaf").val(currData.leaf);
			        $("#isLeaf").attr("disabled","disabled");
			        $("#sortNo").val(currData.sortNo);
			        $("#remark").val(currData.remark); 
			        $("#rightState").val(currData.state); 
			    },
		        btn3:function(layero, index){
		        	layero.close(index);
			    },
			    //这里必须清空  否则页面ID重复   会发生意想不到的问题
			    success:function(){
			        $("#addDiv").empty();
			        $("#rightId").val(currData.id); 
			        $("#parentCode").val(currData.parentCode); 
			        $("#rightName").val(currData.name); 
			        $("#rightUrl").val(currData.url); 
			        $("#permissionCode").val(currData.permissionCode);
			        $("#rightType").val(currData.rightType);
			        if(currData.leaf=='0'){
			           $("#menuIcon").parent().show();
			           $("#menuIcon").val(currData.icon);
			        }else{
			           $("#menuIcon").parent().hide();
			        }
			        $("#isLeaf").val(currData.leaf);
			        $("#isLeaf").attr("disabled","disabled");
			        $("#sortNo").val(currData.sortNo);
			        $("#remark").val(currData.remark); 
			        $("#rightState").val(currData.state); 
			        
			    },
			    //这里重新将影藏div中的内容回写，否则再次打开弹出框时将没有内容
			    end:function(){
			       $("#addDiv").html(content);
			    }
			});
	    }
	    //添加菜单
	    function deleteMenu(){
	      layerUI.confirm({
			 	content:"你确定要删除该数据？",
				yes:function(layero,index){
				   $.ajax({
						url : "<%=path%>/user/deleteRight",
						data : {rightIds:currData.id},
						type : 'post',
						async: false,
						dataType : 'json',
						success : function(data) {
							if(data.code==1){
							   if(currData.leaf==1){
							     refreshNode();
							   }else{
							     refreshRootNode();
							   }
							    layero.close(index);
			            	}
			            	layerUI.showMsg({content:data.message});
						},
						error : function() {
						    layerUI.showMsg({content:"系统异常，请重试！"});
						}
					});
				}
			})
	    }
	    
	    //保存
	     function doSave(params){
	        var success = false;
	        $.ajax({
				url : "<%=path%>/user/saveRight",
				data : params,
				type : 'post',
				async: false,
				dataType : 'json',
				success : function(data) {
					if(data.code==1){
					   if($("#isLeaf").val()=="1"){
					     refreshNode();
					   }else{
					     refreshRootNode();
					   }
	            		success = true;
	            	}
	            	layerUI.showMsg({content:data.message});
				},
				error : function() {
				    layerUI.showMsg({content:"系统异常，请重试！"});
				}
			});
			return success;
	     }
	    //节点刷新当前节点
	    function refreshNode(){
	       var rootFolder = getRootFolder(currDom);
	       if(rootFolder==null) return;
	       var iClassName = rootFolder.find("i").attr("class");
	       //如果当前节点没有展开则不进行刷新
	       if(iClassName=="icon-plus") return;
	       //这里点击两次模拟刷新节点
	       rootFolder.find(".tree-folder-header").trigger("click");
	       rootFolder.find(".tree-folder-header").trigger("click");
	    }
	    //刷新根节点
	    function refreshRootNode(){
	      $("#tabContent").load("<%=path%>/views/user/right.jsp");
	    }
	    //获取当前点击的根节点
	    function getRootFolder(currDom){
	       var className = currDom.attr("class");
	        if(className=="tree-folder"){
	         return currDom;
	       }
	       return getRootFolder(currDom.parent());
	    }
	</script>
</body>
</html>
