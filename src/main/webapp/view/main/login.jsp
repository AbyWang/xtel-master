<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%response.setHeader("Pragma","No-cache");  response.setHeader("Cache-Control","no-cache");  response.setDateHeader("Expires", 0);  response.flushBuffer();%> 
<!DOCTYPE html>
<html lang="en">
	<head>
	
   <meta charset="utf-8" />
   <title>信通远程教学系统</title>
    <!-- bootstrap & fontawesome -->
   <link rel="stylesheet" href="<%=path%>/plug-in/ace/css/bootstrap.css" />
   <link rel="stylesheet" href="<%=path%>/plug-in/ace/css/font-awesome.css" />
    <!-- text fonts -->
    <link rel="stylesheet" href="<%=path%>/plug-in/ace/css/ace-fonts.css" />

    <link rel="stylesheet" href="<%=path%>/plug-in/ace/css/jquery-ui.css" />
    <!-- ace styles -->
    <link rel="stylesheet" href="<%=path%>/plug-in/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />

	</head>
	
	<body class="login-layout light-login">
<div class="main-container">
  <div class="main-content">
    <div class="row">
      <div class="col-sm-10 col-sm-offset-1" style="padding-top:100px">
        <div class="login-container" >
          <div class="center">
            <h1 id="id-text2" class="grey">
              <i class="ace-icon fa fa-leaf green"></i>
                信通远程教学管理平台
            </h1>
          </div>
          <div class="space-6"></div>
          <div class="position-relative">
            <div id="login-box" class="login-box visible widget-box no-border">
              <div class="widget-body">
                <form id="loginForm" class="form-horizontal"  check="<%=path%>/user/checkuser" role="form" action="<%=path%>/login"  method="post">
                <div class="widget-main">
                 <div class="alert alert-warning alert-dismissible" role="alert" id="errMsgContiner">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  <div id="showErrMsg"></div>
				</div>
                  <h4 class="header blue lighter bigger">
                    <i class="ace-icon fa fa-coffee green"></i>
                	    用户登录
                  </h4>
                  <div class="space-6"></div>
                      <label class="block clearfix">
								<span class="block input-icon input-icon-right">
									<input type="text"  name="loginname" class="form-control" placeholder="请输入用户名"  id="loginname" value="admin"/>
									<i class="ace-icon fa fa-user"></i>
								</span>
                      </label>
                      <label class="block clearfix">
								<span class="block input-icon input-icon-right">
									<input type="password" name="password" class="form-control" placeholder="请输入密码" id="password" value="" />
									<i class="ace-icon fa fa-lock"></i>
								</span>
                      </label>
                      <div class="space"></div>
                      <div class="clearfix">
                        <label class="inline">
                          <input type="checkbox" class="ace" id="on_off"  name="remember" value="yes"/>
                          <span class="lbl">记住用户名</span>
                        </label>
                        <button type="button" id="but_login"  onclick="checkUser()" class="width-35 pull-right btn btn-sm btn-primary">
                          <i class="ace-icon fa fa-key"></i>
                          <span class="bigger-110" >登录</span>
                        </button>
                      </div>
                      <div class="space-4"></div>

                </div>
      
                </form>
              </div>
            </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
<script type="text/javascript" src="<%=path%>/plug-in/jquery/jquery-1.9.1.js"></script>
 <script type="text/javascript" src="<%=path%>/plug-in/jquery/jquery.cookie.js"></script>

<script type="text/javascript">
$(function(){
	
	optErrMsg();
});

$("#errMsgContiner").hide();
function optErrMsg(){
	$("#showErrMsg").html('');
	$("#errMsgContiner").hide();
}


//输入验证码，回车登录
$(document).keydown(function(e){
	if(e.keyCode == 13) {
		$("#but_login").click();
	}
});

//验证用户信息
function checkUser(){
  if(!validForm()){
    return false;
  }
  newLogin();
}
//表单验证
function validForm(){
  if($.trim($("#loginname").val()).length==0){
    showErrorMsg("请输入用户名");
    return false;
  }

  if($.trim($("#password").val()).length==0){
    showErrorMsg("请输入密码");
    return false;
  }
  return true;
}

if (window != top) {
	top.location.href = location.href;
}

function newLogin() {
	    setCookie();
	    var actionurl=$('form').attr('action');//提交路径
	    var checkurl=$('form').attr('check');//验证路径
	    var formData = new Object();
	    var data=$(":input").each(function() {
	      formData[this.name] =$("#"+this.name ).val();
	    });
	    $.ajax({
	      async : false,
	      cache : false,
	      type : 'POST',
	      url : checkurl,// 请求的action路径
	      data : formData,
	      error : function() {// 请求失败处理函数
	      },
	      success : function(data) {
	        if (data.code==1) {
	        	window.location.href = actionurl;
	        }else{
	     		showErrorMsg(data.message);
	        }
	      }
	    });
}

//登录提示消息显示
function showErrorMsg(msg){	
  $("#errMsgContiner").show();
  $("#showErrMsg").html(msg);    
  window.setTimeout(optErrMsg,3000); 
}


//设置cookie
function setCookie()
{
	if ($('#on_off').val() == '1') {
		$("input[iscookie='true']").each(function() {
			$.cookie(this.name, $("#"+this.name).val(), "/",24);
			$.cookie("COOKIE_NAME","true", "/",24);
		});
	} else {
		$("input[iscookie='true']").each(function() {
			$.cookie(this.name,null);
			$.cookie("COOKIE_NAME",null);
		});
	}
}
//读取cookie
function getCookie()
{
	var COOKIE_NAME=$.cookie("COOKIE_NAME");
	if (COOKIE_NAME !=null) {
		$("input[iscookie='true']").each(function() {
			$($("#"+this.name).val( $.cookie(this.name)));
            if("admin" == $.cookie(this.name)) {
                $("#randCode").focus();
            } else {
                $("#password").val("");
                $("#password").focus();
            }
        });
		$("#on_off").attr("checked", true);
		$("#on_off").val("1");
	} 
	else
	{
		$("#on_off").attr("checked", false);
		$("#on_off").val("0");
      $("#randCode").focus();
	}
}
</script>
</html>