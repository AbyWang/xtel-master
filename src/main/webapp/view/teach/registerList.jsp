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
<title>课程管理</title>


<link href="plug-in/bootstrap3.3.5/css/bootstrap.min.css" rel="stylesheet">
<link href="plug-in/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="plug-in/layui/css/layui.css" rel="stylesheet">

<!-- Jquery组件引用 -->
<script src="plug-in/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript" src="plug-in/layui/layui.js"></script> 
<script src="plug-in/bootstrap3.3.5/js/bootstrap.min.js"></script>
<!-- 通用组件引用 -->
<link href="plug-in/bootstrap3.3.5/css/default.css" rel="stylesheet" />
<script src="js/bootstrap-curdtools.js"></script>
<script src="plug-in/bootstrap-table/bootstrap-table.js"></script>
<script src="plug-in/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>


</head>

<body>

        <div class="panel-body" style="padding-bottom:0px;">
        <!-- 搜索 -->
		<div class="accordion-group">
			<div id="collapse_search" class="accordion-body collapse">
				<div class="accordion-inner">
					<div class="panel panel-default" style="margin-bottom: 0px;">
            				<div class="panel-body" >
			                <form id="searchForm" class="form form-horizontal" action="" method="post">
			                    <div class="col-xs-12 col-sm-6 col-md-4">
			                        <label  for="name">名称：</label>
			                        <div class="input-group col-md-12">
			                        	<input type="text" class="form-control input-sm" id="name" name="name">
			                        </div>
			                    </div>
			                    <div class="col-xs-12 col-sm-6 col-md-4">
			                         <div  class="input-group col-md-12" style="margin-top:20px">
			                         <a type="button" onclick="jeecgDemoSearch();" class="btn btn-primary btn-rounded  btn-bordered btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查询</a>
			                         <a type="button" onclick="jeecgDemoRest();" class="btn btn-primary btn-rounded  btn-bordered btn-sm"><span class="glyphicon glyphicon-repeat" aria-hidden="true"></span> 重置</a>
			                         </div>
			                    </div>
			                </form>
			                </div>
			             </div>
			       </div>
			</div>
		</div>
        <div id="toolbar">
            <button id="btn_add" type="button" class="btn btn-primary btn-sm" onclick="add('新增','','jeecgDemoList',600,400)">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
            </button>
            <button id="btn_edit" type="button" class="btn btn-success btn-sm" onclick="update('修改','','jeecgDemoList',600,400)">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
            </button>
            <button id="btn_delete" type="button" class="btn btn-danger btn-sm"  onclick="deleteALLSelect('批量删除','','jeecgDemoList',600,400)">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>批量删除
            </button>
            <a class="btn btn-default btn-sm" data-toggle="collapse" href="#collapse_search" id="btn_collapse_search" >
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 检索 </a>
        </div>
        <div class="table-responsive">
            <!-- class="text-nowrap" 强制不换行 -->
         	<table id="roleList"></table>
        </div>
    </div>
<script src="js/common.js"></script>
<script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js"></script>  
<script type="text/javascript" src="js/curdtools_zh-cn.js"></script>  
<script type="text/javascript">
var path = "<%=path%>";
$(function () {
	loadTable();
	
});
function loadTable(flag){
	var defaultColunms = roleList.initColumn();
    var table = new BSTable("roleList",path+"/teachingController/listRegister", defaultColunms);
    table.init();
    if(flag==1){
        table.refresh();
    } 
}
var data=[];
roleList.initColumn= function () {
    return [
       {title: 'id',field: 'ID', align: 'center', valign: 'middle',width:'50px'},
        {title: '课程名称',field: 'COURSENAME', align: 'center', valign: 'middle',width:'50px'},
        {title: '申请人',field: 'USERNAME', align: 'center', valign: 'middle',width:'50px'},
        {title: '申请时间',field: 'TIME', align: 'center', valign: 'middle',width:'50px',formatter:
        	function (value, row, index) {
        	return changeDateFormat(value);
        }},
        {title: '操作', align: 'center', valign: 'middle',width:'50px', formatter: 
        	function (value, row, index) {
            data[index]=row;
            return '<button class="btn btn-info btn-xs" onclick=check("'+index+'")><span class="glyphicon glyphicon-check" aria-hidden="true"></span>审核</button>';
        }}]
  };
  
  function check(index){
    var id=data[index].ID;
    var name=data[index].USERNAME;
    layui.use('layer', function(){
        var layer = layui.layer;

        layer.confirm('是否通过?', {icon: 1,btn: ['通过','取消'],title:'报名审核'}, function(index){
        layer.close(index);
        $.ajax({
            type : 'POST',
            url : "teachingController/registerApply",// 请求的action路径
            data : {id:id},
            success : function(data) {
                layer.msg(data.message, {
                    icon: data.code,
                    //2秒关闭（如果不配置，默认是3秒）
                    time: 2000 
                  }, function(){
                    if(data.code=="1"){
                        loadTable(1);
                    }
            });
              
         }
        });
     });
  });
   // createdialog(name,"报名审核","lessonCenterController/registerApply?id="+id);
  }
</script>
</body>
</html>
