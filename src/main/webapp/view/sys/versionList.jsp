<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width" />
<base href="<%=basePath%>">
<title>版本列表</title>
<!-- Jquery组件引用 -->

<link href="plug-in/bootstrap3.3.5/css/bootstrap.min.css" rel="stylesheet">
<link href="plug-in/bootstrap3.3.5/css/default.css" rel="stylesheet" />
<!-- bootstrap table组件以及中文包的引用 -->
<link href="plug-in/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<script src="plug-in/jquery/jquery-1.9.1.js"></script>
<script src="plug-in/bootstrap3.3.5/js/bootstrap.min.js"></script>
<script src="plug-in/bootstrap-table/bootstrap-table.js"></script>
<script src="plug-in/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<!-- Layer组件引用 -->
<script src="plug-in/layui/layui.js"></script>
<!-- 通用组件引用 -->
<script src="js/common.js"></script>
</head>
     <div class="panel-body" style="padding-bottom:0px;">
        <!-- 搜索 -->
        <div id="toolbar">
            <button id="btn_add" type="button" class="btn btn-primary btn-sm" onclick="add('新增','','jeecgDemoList',600,400)">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
            </button>
           <!--  <button id="btn_edit" type="button" class="btn btn-success btn-sm" onclick="update('修改','','jeecgDemoList',600,400)">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
            </button> -->
              </div>
        <div class="table-responsive">
            <!-- class="text-nowrap" 强制不换行 -->
         	<table id="versionList"></table>
        </div>
    </div>
    
    <!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content"  >
		  <form class="registerform" >
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" 
						aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					版本发布
				</h4>
			</div>
			<div class="modal-body">

				<div class="form-group">
                   <label>版本号:</label>
                    <input class="form-control" type="text" id="url" datatype="*" />
                         
                    <div class="Validform_checktip"></div>
                                                              
                 </div>
                 <div class="form-group">
                        <label >ios安装包:</label>
                        <input class="form-control" type="text"  id="username"  datatype="*"/>
                        
                        <div class="Validform_checktip">
                  </div>
                                                              
                  <div class="form-group">
                       <label>android安装包:</label>
                       <input class="form-control" type="text" id="password"  datatype="*"/>
                      <div class="Validform_checktip">
                 </div>   
                 <div class="form-group">
                       <label>PC安装包:</label>
                       <input class="form-control" type="text" id="password"  datatype="*"/>
                      <div class="Validform_checktip">
                 </div> 
                  <div class="form-group">
                       <label>更新日志:</label>
                       <input class="form-control" type="text" id="password"  datatype="*"/>
                      <div class="Validform_checktip">
                 </div>                                                      
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" 
						data-dismiss="modal">关闭
				</button>
				<button type="submit"   class="btn btn-primary">
					提交更改
				</button>
			</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">
var path = "<%=path%>";
var data=[]; 
$(function () {
	loadTable();
});
	
	
	
function updateVersion(index){
    var rdata=data[index];
 	$('#myModal').modal('show');
 }

function loadTable(flag){
	
	var defaultColunms = versionList.initColumn();
    var table = new BSTable("versionList",path+ "/systemController/listSystemVersion", defaultColunms);
    table.init();
    if(flag==1){
    	table.refresh();
    }
}

versionList.initColumn= function () {
    return [
        {title: 'id',field: 'ID', align: 'center', valign: 'middle',width:'50px'},
        {title: '版本号', field: 'VERSION', align: 'center', valign: 'middle',width:'50px'},
        {title: '时间', field: 'TIME', align: 'center', valign: 'middle',width:'50px',
        	  formatter: function (value, row, index) {
        	        return changeDateFormat(value)
        	    }},
        {title: '发布内容', field: 'RELEASENOTE', align: 'center', valign: 'middle',width:'50px'},
        {title: '发布人', field: 'USERID', align: 'center', valign: 'middle',width:'50px'},
        {title: '状态', field: 'STATUS', align: 'center', valign: 'middle',width:'50px',formatter: 
  	    function (value, row, index) {
        	if(value==1){
        		return "<font color='green'>使用中</font>";
        	}else{
        		return "<font color='red'>不可用</font>";
        	}
        }},
       {title: '操作', align: 'center', valign: 'middle',width:'50px', formatter: 
	      function (value, row, index) {
    	    data[index]=row;
            return "<button class='btn btn-sm btn-success'  onclick=updateVersion('"+index+"')><i class='icon-edit bigger-180'></i>修改</button>&nbsp;"
           }
        }];}
</script>
</body>
</html>
