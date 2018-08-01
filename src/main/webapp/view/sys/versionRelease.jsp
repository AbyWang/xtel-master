<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" />
<title>数据库管理</title>

<!-- Jquery组件引用 -->
<script src="plug-in/jquery/jquery-1.9.1.js"></script>
<!-- <script src="https://cdn.bootcss.com/jquery/1.12.3/jquery.min.js"></script> -->

<!-- bootstrap组件引用 -->
<link href="plug-in/bootstrap3.3.5/css/bootstrap.min.css" rel="stylesheet">
<script src="plug-in/bootstrap3.3.5/js/bootstrap.min.js"></script>
<!-- <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->

<!-- icheck组件引用 -->
<link href="plug-in/icheck-1.x/skins/square/_all.css" rel="stylesheet">
<script type="text/javascript" src="plug-in/icheck-1.x/icheck.js"></script>

<!-- Validform组件引用 -->
<link href="plug-in/themes/bootstrap-ext/css/validform-ext.css" rel="stylesheet" />
<script type="text/javascript" src="plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js"></script>
<script type="text/javascript" src="plug-in/Validform/js/Validform_Datatype_zh-cn.js"></script>
<script type="text/javascript" src="plug-in/Validform/js/datatype_zh-cn.js"></script>
<script type="text/javascript" src="plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js"></script>
<!-- Layer组件引用 -->
<script src="plug-in/layer/layer.js"></script>
<script src="plug-in/laydate/laydate.js"></script>
<!-- <script src="https://cdn.bootcss.com/layer/3.1.0/layer.js"></script> -->

<!-- 通用组件引用 -->
<link href="plug-in/bootstrap3.3.5/css/default.css" rel="stylesheet" />
<script src="plug-in/themes/bootstrap-ext/js/common.js"></script>
</head>
<body style="margin: 40px">
	<form id="formobj" action="jeecgListDemoController.do?doAdd" class="form-horizontal validform" role="form"  method="post">
		<input type="hidden" id="btn_sub" class="btn_sub"/>
		<div class="form-group">
			<label for="name" class="col-sm-3 control-label">版本号：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:40%">
				<input type="text" class="form-control input-sm required" id="versionId" placeholder="请输入名称"></div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-3 control-label">苹果端安装包地址：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:40%">
				<input type="text" class="form-control input-sm required" id="versionId" placeholder="请输入名称"></div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 安卓端安装包：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:40%">
					<input id="salary" name="salary" type="text" class="form-control input-sm required" placeholder="请输入工资"  data-msg-required="" aria-required="true" />
				</div>				

			</div>
		</div>
		
				<div class="form-group">
			<label class="col-sm-3 control-label"> PC端安装包：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<input id="salary" name="salary" type="text" class="form-control input-sm required" placeholder="请输入工资"  data-msg-required="" aria-required="true" />
				</div>				

			</div>
		</div>
			
		
		<div class="form-group">
			<label class="col-sm-3 control-label">更新日志：</label>
			<div class="col-sm-7">
				<div class="input-group" style="width:100%">
					<textarea id="content" name="content" class="form-control input-sm" placeholder="请输入个人介绍"  rows="4"></textarea>
				</div>
			</div>
		</div>
		
	</form>	

</body>
</html>

<script type="text/javascript">
	$(document).ready(function() {
		//日期控件初始化
	    laydate.render({
		   elem: '#birthday'
		  ,type: 'datetime'
		  ,trigger: 'click' //采用click弹出
		  ,ready: function(date){
		  	 $("#birthday").val(DateJsonFormat(date,this.format));
		  }
		});
		
		//单选框/多选框初始化
		$('.i-checks').iCheck({
			labelHover : false,
			cursor : true,
			checkboxClass : 'icheckbox_square-blue',
			radioClass : 'iradio_square-blue',
			increaseArea : '20%'
		});
		
		//表单提交
		$("#formobj").Validform({
			tiptype:function(msg,o,cssctl){
				if(o.type==3){
					validationMessage(o.obj,msg);
				}else{
					removeMessage(o.obj);
				}
			},
			btnSubmit : "#btn_sub",
			btnReset : "#btn_reset",
			ajaxPost : true,
			beforeSubmit : function(curform) {
			},
			usePlugin : {
				passwordstrength : {
					minLen : 6,
					maxLen : 18,
					trigger : function(obj, error) {
						if (error) {
							obj.parent().next().find(
									".Validform_checktip")
									.show();
							obj.find(".passwordStrength")
									.hide();
						} else {
							$(".passwordStrength").show();
							obj.parent().next().find(
									".Validform_checktip")
									.hide();
						}
					}
				}
			},
			callback : function(data) {
				var win = window.parent;
				if (data.success == true) {
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					parent.layer.close(index); 
					win.tip(data.msg,'1');
				} else {
					win.tip(data.msg,'2');
				}
				win.reloadTable();
			}
		});
	});
</script>