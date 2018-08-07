<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html lang="en">
<head>

    <base href="<%=basePath%>">
<title>��ͨԶ�̽�ѧ����ƽ̨</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="plug-in/ace/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="plug-in/ace/assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="plug-in/ace/assets/css/ace.min.css" />
<link rel="stylesheet" href="plug-in/ace/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="plug-in/ace/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="<%=path%>/plug-in/layui/css/layui.css">
<script type="text/javascript" src="plug-in/layui/layui.js"></script>
<script type="text/javascript" src="plug-in/layui/custom/custom_layer.js"></script>
<script src="plug-in/ace/assets/js/ace-extra.min.js"></script>
<script src="plug-in/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript">
var path = "<%=path%>";
</script>
</head>

<body>

<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand">
				 <small> 
			<!-- 	<img alt="image" width="38" height="38" src="images/st.png" /> -->
				<i class="icon-leaf">
				  </i> ��ͨԶ�̽�ѧ����ƽ̨
				</small>			
				</a>
				<!-- /.brand -->
			</div>
			<!-- /.navbar-header -->

			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">

					<li class="light-blue"><a data-toggle="dropdown" href="#"
						class="dropdown-toggle">
						 <img class="nav-user-photo"  src="plug-in/ace/assets/avatars/user.jpg" />
						  <span class="user-info"> <small>��ӭ����,</small>
						  </span>
						 <i class="icon-caret-down"></i>
					</a>
						<ul
							class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							
							<li><a href="javascript:showUserInfo();"  target="mainframe"> <i class="icon-user green"></i> ��������
							</a></li>
						   <li><a href="javascript:changePwd();"><i class="icon-lock purple"></i>�����޸�</a>
						     </li>
							<li class="divider"></li>

							<li><a href="javascript:logout();"> <i class="icon-off"></i> �˳�
							</a></li>
						</ul></li>
				</ul>
				<!-- /.ace-nav -->
			</div>
			<!-- /.navbar-header -->
		</div>
		<!-- /.container -->
	</div>
	


	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>			</a>

			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'fixed')
					} catch (e) {
					}
				</script>

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="icon-signal"></i>						</button>

						<button class="btn btn-info">
							<i class="icon-pencil"></i>						</button>

						<button class="btn btn-warning">
							<i class="icon-group"></i>						</button>

						<button class="btn btn-danger">
							<i class="icon-cogs"></i>						</button>
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span> <span class="btn btn-info"></span>

						<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>					</div>
				</div>
				<!-- #sidebar-shortcuts -->
                <!--��߲˵� -->
				<ul id="menuList" class="nav nav-list">

				</ul>
				<!-- /.nav-list -->

				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left"
						data-icon1="icon-double-angle-left"
						data-icon2="icon-double-angle-right"></i>				</div>

				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'collapsed')
					} catch (e) {
					}
				</script>
			</div>

			<div class="main-content" id="mains">
			<!-- <iframe id="mainframe" name="mainframe" src="view/main/bootm.jsp"
					style="width: 100%;border: 0px;"> </iframe> -->	
					               <!-- ��ҳ��ͷ�������������� -->
               <div class="breadcrumbs" id="breadcrumbs">
					<!-- <ul class="breadcrumb" id="breadcrumb"></ul> -->
					<div class="layui-tab layui-tab-card" style='box-shadow:none;' lay-filter="tabcard" lay-allowclose="true">
					  <ul class="layui-tab-title  top_tab" id="layuiTabTitle">
					  <!--<li lay-id="201801161150" class="layui-this">��ҳ</li>  -->	
					    	<li class="layui-this" ><cite>��ҳ</cite></li>
					  </ul>
					  <div class="layui-tab-content" id='layuiTabContent'>
					  	<div class="layui-tab-item  layui-show" style="background-color: #fff;width: 1400px;min-height:700px;overflow: scroll;">
					  		<%@include  file="main_index2.jsp"%>
				  		</div>
					  </div>
					</div>
			    <dl class="layui-nav-child" id="tabMenu">
				      <dd><a class="freshCurrTab" href="javascript:;">ˢ�µ�ǰҳ��</a></dd>
				      <dd><a class="closeCurrTab" href="javascript:;">�رյ�ǰҳ��</a></dd>
				      <dd><a class="closeOtherTab" href="javascript:;">�ر�����ҳ��</a></dd>
				      <dd><a class="closeRightTab" href="javascript:;">�ر��ұ�ҳ��</a></dd>
				      <dd><a class="closeLeftTab" href="javascript:;">�ر����ҳ��</a></dd>
				      <dd><a class="closeAllTab" href="javascript:;">�ر�����ҳ��</a></dd>
				    </dl>-
			</div>

			<script type="text/javascript">
				var height = jQuery(window).height() - 50;
				jQuery("#mainframe").attr("height", "" + height + "px;");
			</script>

			<div class="ace-settings-container" id="ace-settings-container">
				<!-- <div class="btn btn-app btn-xs btn-warning ace-settings-btn"
					id="ace-settings-btn">
					<i class="icon-cog bigger-150"></i>				</div> -->

				<div class="ace-settings-box" id="ace-settings-box">
					<div>
						<div class="pull-left">
							<select id="skin-colorpicker" class="hide">
								<option data-skin="default" value="#438EB9">#438EB9</option>
								<option data-skin="skin-1" value="#222A2D">#222A2D</option>
								<option data-skin="skin-2" value="#C6487E">#C6487E</option>
								<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
							</select>
						</div>
						<span>&nbsp; ѡ��Ƥ��</span>					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-navbar" /> <label class="lbl"
							for="ace-settings-navbar"> �̶�������</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-sidebar" /> <label class="lbl"
							for="ace-settings-sidebar"> �̶�������</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-breadcrumbs" /> <label class="lbl"
							for="ace-settings-breadcrumbs">�̶����м</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-rtl" /> <label class="lbl"
							for="ace-settings-rtl">�л������</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-add-container" /> <label class="lbl"
							for="ace-settings-add-container"> �л�խ�� <b></b>
						</label>
					</div>
				</div>
			</div>
			<!-- /#ace-settings-container -->
		</div>
		<!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i>		</a>	</div>
	<!-- /.main-container -->
	<!-- basic scripts -->

	<script src="plug-in/ace/assets/js/bootstrap.min.js"></script>
	<script src="plug-in/ace/assets/js/typeahead-bs2.min.js"></script>

    <script src="plug-in/ace/assets/js/ace-elements.min.js"></script>
	<script src="plug-in/ace/assets/js/ace.min.js"></script>
	<script src="view/main/js/main.js"></script>

   <script type="text/javascript">
	   function logout(){

	        layer.confirm('��ȷ��Ҫע����', {
	            btn: ['ȷ��','ȡ��'], //��ť
	            shade: false //����ʾ����
	        }, function(){
	            location.href="<%=path%>/user/logout";
	        }, function(){
	            return;
	        });
	    }
		
	   $(function(){
		   
	   })
	   
	   
</script>
	
</bo<body>
</html>

