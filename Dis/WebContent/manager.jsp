<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>您好，管理员</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/sdmenu.css" />
<link type="text/css" rel="stylesheet" href="css/customer_style.css" />

<script type="text/javascript" src="js/sdmenu.js"></script>
<script type="text/javascript">
	var myMenu;
	window.onload = function() {
		myMenu = new SDMenu("my_menu");
		myMenu.init();
	};
</script>
<!--
	作者：575969960@qq.com
	时间：2017-03-18
	描述：引入bootstrap和插件
-->

<link rel="stylesheet" type="text/css" href="css/NEW/content.css" />
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/npm.js"></script>
</head>

<body style="background-color: #ecf0f1;">
	<div id="normal_header">
		<h1>
			<span class="glyphicon glyphicon-user" aria-hidden="true"></span>管理
		</h1>
	</div>
	<div id="manager_content">
		<table width="100%" height="500" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="15%" height="100%" valign="top">
					<div id="my_menu" class="sdmenu">
						<div class="collapsed">
							<span>活动</span> <a href="activity_manager.jsp" target="mainFrame">活动管理</a>
						</div>
						<div class="collapsed">
							<span>成员管理</span> <a href="business_manager.jsp"
								target="mainFrame">商户管理</a> <a href="customer_manager.jsp"
								target="mainFrame">用户管理</a>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div id="manager_iframe">
		<iframe name="mainFrame" frameborder="0" marginheight="0"
			marginwidth="0" height="100%" width="100%"> </iframe>
	</div>
</body>
</html>
