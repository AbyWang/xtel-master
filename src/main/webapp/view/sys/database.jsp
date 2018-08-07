<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" >
<head>
<meta name="viewport" content="width=device-width" />
<title>数据库管理</title>

<link href="plug-in/Validform/css/style.css" rel="stylesheet" />
<link href="plug-in/Validform/css/demo.css" rel="stylesheet" />
<link href="plug-in/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="plug-in/bootstrap3.3.5/css/bootstrap.min.css" rel="stylesheet">
<link href="plug-in/layui/css/layui.css" rel="stylesheet">
<!-- 通用组件引用 -->
<link href="plug-in/bootstrap3.3.5/css/default.css" rel="stylesheet" />
<style>
.header-line {
    font-weight:900;
    padding-bottom:10px;
    padding-top:10px;
    border-bottom:1px solid #eeeeee;
    text-transform:uppercase;
    color: #a94442;
    text-align: left
}
h4{
   text-align: left;
   padding-bottom:18px !important;
   color:#a94442

}
.nav{
   margin-left:30%;

}
.registerform{
  text-align:left;
}
#myModalLabel{
  text-align:center;
}

</style>
</head>
<body>
 <div class="content-wrapper">
     <!--  <div class="container-fluid"> -->   
      <div class="container-fluid">
        <div class="row pad-botm">
            <div class="col-md-12">
                <h3 class="header-line">数据库配置</h3>
                
            </div>
  </div>
 <div class="table-responsive">
            <!-- class="text-nowrap" 强制不换行 -->
         	<table id="dbList"></table>
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
					数据库修改
				</h4>
			</div>
			<div class="modal-body">

				<div class="form-group">
                   <label>数据库url:</label>
                    <input class="form-control" type="text" id="url" datatype="*" />
                         
                    <div class="Validform_checktip"></div>
                                                              
                 </div>
                 <div class="form-group">
                        <label >数据库用户名:</label>
                        <input class="form-control" type="text"  id="username"  datatype="*"/>
                        
                        <div class="Validform_checktip">
                  </div>
                                                              
                  <div class="form-group">
                       <label>数据库密码:</label>
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
	<!--  <div class="row nav" >
            <div class="col-md-6 col-sm-6 col-xs-12">

                        <div class="panel-body">
                            <form class="registerform" >
                                        <div class="form-group">
                                            <label>数据库地址:</label>
                                            <input class="form-control" type="text" datatype="*" />
                                             </br>         
                                              <div class="Validform_checktip"></div>
                                                              
                                        </div>
                                          <div class="form-group">
                                            <label>数据库端口号:</label>
                                            <input class="form-control" type="text"  datatype="*"/>
                                           </br>
                                        </div>
                                        <div class="form-group">
                                            <label>数据库名称:</label>
                                            <input class="form-control" type="text" datatype="*"/>
                                                     </br>
                                        </div>
                                        
                                          <div class="form-group">
                                            <label>数据库账号:</label>
                                            <input class="form-control" type="text" datatype="*"/>
                                                   </br>
                                        </div>
                                          <div class="form-group">
                                            <label class="control-label">数据库密码:</label>
                                            <input class="form-control" type="text" datatype="*"/>
                                             </br>
                                            </br>
                                        </div>
                                 
                                        <button type="submit" class="btn btn-success btn-lg btn-block">修改</button>
                                        <span id="msgdemo" style="margin-left:30px;"></span>
                                    </form>
                        </div>
                  </div>
              </div>
           </div>
       </div>
      -->  
     <!-- Jquery组件引用 -->
     <script src="plug-in/jquery/jquery-1.9.1.js"></script>
     <script src="plug-in/bootstrap3.3.5/js/bootstrap.min.js"></script>
     <!-- bootstrap组件引用 --><script src="plug-in/bootstrap-table/bootstrap-table.js"></script>
    <script src="plug-in/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="plug-in/bootstrap-table/bootstrap-table.js"></script>
    <script src="plug-in/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <!-- Validform组件引用 -->
     <script type="text/javascript" src="plug-in/Validform/Validform_v5.3.2.js"></script> 
     <script type="text/javascript" src="<%=path%>/plug-in/sweet-alert/js/sweetalert2.min.js"></script>
     <script src="js/common.js"></script>    
     <script type="text/javascript" src="plug-in/layui/layui.js"></script>  
     <script type="text/javascript" src="plug-in/layui/custom/custom_layer.js"></script>  
  <script>
  var data=[]; 
  var path = "<%=path%>";

  	

  dbList.initColumn= function () {
      return [
          {title: '数据库url',field: 'url', align: 'center', valign: 'middle',width:'50px'},
          {title: '数据库名称', field: 'username', align: 'center', valign: 'middle',width:'50px'},
          {title: '数据库密码', field: 'password', align: 'center', valign: 'middle',width:'50px'}, {title: '操作', align: 'center', valign: 'middle',width:'50px', formatter: 
	      function (value, row, index) {
        	    data[index]=row;
	            return "<button class='btn btn-sm btn-danger'  onclick=updateDb('"+index+"')><i class='icon-edit bigger-180'></i>修改</button>&nbsp;"
	        }}]

  };
    function updateDb(index){
       var rdata=data[index];
    	$('#myModal').modal('show');
    	$('#url').val(rdata.url);
    	$('#username').val(rdata.username);
    	$('#password').val(rdata.password);
    }
    
    function loadTable(flag){
    	
		var defaultColunms = dbList.initColumn();
	    var table = new BSTable("dbList",path+ "/systemController/getDbInfo", defaultColunms);
	    table.init();
	   	console.log(flag);
	    if(flag==1){
	    	console.log(1);
	    	table.refresh();
	    }
    }
    
  $(function(){
	     loadTable();
		//$(".registerform").Validform();  //就这一行代码！;
		$(".registerform").Validform({

			tiptype:function(msg,o,cssctl){
		    if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
		    var objtip=o.obj.siblings(".Validform_checktip");
					cssctl(objtip,o.type);
			
					objtip.text(msg);
				
				}else{
					var objtip=o.obj.find("#msgdemo");
					cssctl(objtip,o.type);
					objtip.text(msg);
		
				}
			},
			ajaxPost:true,
			callback:function(form){
			doUpdate();

		}
	})
  });
   
  
  function doUpdate(){
	  
       var url=$("#url").val();
       var username=$("#username").val();
       var password=$("#password").val();
       console.log(url);
       console.log(username);
       console.log(password);
	    $.ajax({
		      type : 'POST',
		      url : "systemController/updateDbInfo",// 请求的action路径
		      data : {
		    	  url:url,
		    	  username:username,
		    	  password:password
		      },
		      success : function(data) {
		    	  layer.alert(data.message, {
		              icon: data.code ? 1 : 2
		          }, function (index) {
		    	  if(data.code==1){
		    		  $('#myModal').modal('hide');
						layer.close(index);
						loadTable(1);
		    	  }
		       });
		}
    });
  }
  </script>                    
  </body>             
</html>
                            
                            