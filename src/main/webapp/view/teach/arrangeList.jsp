<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width" />
<base href="<%=basePath%>">
<title>排课申请</title>

<!-- bootstrap组件引用 -->
<link rel="stylesheet" href="plug-in/bootstrap3.3.5/css/bootstrap.min.css" />
<link rel="stylesheet" href="plug-in/ztree/css/zTreeStyle/zTreeStyle.css" />
<link href="plug-in/layui/css/layui.css" rel="stylesheet">
<link href="plug-in/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="plug-in/bootstrap3.3.5/css/default.css" rel="stylesheet" />
<link href="plug-in/layui/css/layui.css" rel="stylesheet">
<!-- Jquery组件引用 -->
<script src="plug-in/jquery/jquery-1.9.1.js"></script>
<script src="plug-in/bootstrap3.3.5/js/bootstrap.min.js"></script>
<script src="plug-in/bootstrap-table/bootstrap-table.js"></script>
 <script src="js/bootstrap-curdtools.js"></script>

</head>

<body>
        <!-- <div class="panel-body" style="padding-bottom:0px;">
			<div class="row">
			<div class="col-md-2" >
	          	<div id="groupTree"  class="ztree"  style="border:1px solid transparent;margin-top:20px;"></div>
		   </div>

			<div class="col-md-10">
			             <div class="embed-responsive embed-responsive-4by3">
                             <iframe  id="listFrame" class="embed-responsive-item" src="view/teach/apply.jsp"></iframe>
						</div>
					</div>
				</div>
			</div> -->

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
              <table class="table table-striped table-bordered table-hover" id="arrangelist">

       </table>
        </div>
    </div>
 <script src="js/common.js"></script>
 <script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js"></script>  
 <script type="text/javascript" src="js/curdtools_zh-cn.js"></script> 
<script src="js/util.js"></script> 
<script type="text/javascript" src="plug-in/layui/layui.js"></script>  

<script>
var path = "<%=path%>";

// 树加载
/**
$(function(){
	loadTree();
})

var bedValid=0;
//加载树
var groupTree;
function loadTree() {
	var zNodes;
	jQuery.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : path + "/group/listAllGroup",
		success : function(data) { //请求成功后处理函数。
	       if(data.code=="1"){
	    	  console.log(data);
			zNodes = data.data; //把后台封装好的简单Json格式赋给zNodes  
	      }
		}
	});
	var ztreeCreator = new ZtreeCreator('groupTree', path
			+ "/group/listAllGroup", zNodes).setCallback(
					{onClick:zTreeOnLeftClick})
					.initZtree({}, function(treeObj) {
						groupTree = treeObj
				});
}
//左击
function zTreeOnLeftClick(event, treeId, treeNode) {
	var id = treeNode.id;
	url=path + '/view/teach/apply.jsp?groupId='+id;
	$("#listFrame").attr("src", url);
}*/

var groupId;
$(function () {
    groupId=getQueryString("groupId");
    loadTable(groupId);
});
var data=[];
function loadTable(groupId,flag){
    var url=path+ "/teachingController/listCourseApply";
    if(isOrNotEmpty(groupId)){
        url=setParam(url,"groupId",groupId);
    }
    var defaultColunms = arrangelist.initColumn();
    var table = new BSTable("arrangelist",url, defaultColunms);
    table.init();
    if(flag==1){
        table.refresh();
    }
}
arrangelist.initColumn= function () {
    return [
        {title: 'id',field: 'COURSEID', align: 'center', valign: 'middle',width:'50px'},
        {title: '群组名称',field: 'GROUPNAME', align: 'center', valign: 'middle',width:'50px'},
        {title: '课程名称',field: 'NAME', align: 'center', valign: 'middle',width:'50px'},
        {title: '课程讲师',field: 'LECTUREID', align: 'center', valign: 'middle',width:'50px'},
        {title: '总课时',field: 'TOTALCLASS', align: 'center', valign: 'middle',width:'50px'},
        {title: '价格',field: 'PRICE', align: 'center', valign: 'middle',width:'50px'},
        {title: '课程类型',field: 'TYPE', align: 'center', valign: 'middle',width:'50px',
            formatter: function (value, row, index) {
            if(value==0){
                return "传统直播授课";
            }else{
                return "智能授课";
            }}
            },

        {title: '申课状态',field: 'STATUS', align: 'center', valign: 'middle',width:'50px',
            formatter: function (value, row, index) {
                if(value==1){
                    return "<span style='color:red'>待审核</span>";
                }else if(value==2){
                    return "审核通过";
                }else if(value==3){
                    return "课程进行中";
                }else if(value==4){
                    return "课程已结束";
                }

            }},
     {title: '操作', align: 'center', valign: 'middle',width:'50px', formatter: 
                        function (value, row, index) {
                        data[index]=row;
                            return [
                             "<button class='btn btn-xs btn-info' onclick=detail('"+index +"')><span class='glyphicon glyphicon-info-sign aria-hidden='true'></span>详细</button>&nbsp",
                             '<button class="btn btn-primary btn-xs" onclick=check("'+index+'")><span class="glyphicon glyphicon-check" aria-hidden="true"></span>审核</button>&nbsp;'
                  ].join('') 
              }}]
     };

function check(index){
    var courseId=data[index].COURSEID;
    var status=data[index].STATUS;
    layui.use('layer', function(){
        var layer = layui.layer;
        if(status!=1){
        	layer.msg("已审核",  {
                icon: 2,
                //2秒关闭（如果不配置，默认是3秒）
                time: 2000 
              })
              return ;
        }
        layer.confirm('是否通过?', {icon: 1,btn: ['通过','取消'],title:'课程审核'}, function(index){
        layer.close(index);
        	$.ajax({
                type : 'POST',
                url : "teachingController/courseApply",// 请求的action路径
                data : {courseId:courseId},
                success : function(data) {
                    layer.msg(data.message, {
                        icon: data.code,
                        //2秒关闭（如果不配置，默认是3秒）
                        time: 2000 
                      }, function(){
                        if(data.code=="1"){
                        	loadTable(groupId,1);
                        }
                });
             }
         });
       });
    });
 }
function detail(index){
	 var courseId=data[index].COURSEID;
	 var name=data[index].NAME;
      layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                   type: 2, 
                   title:"课程详情"+name,
                   area: ['700px', '520px'],
                   content: 'teachingController/getoCourseDetail?courseId='+courseId ,
                   end:function(){
                   }
                 });
            })
}
</script>
</body>
</html>
