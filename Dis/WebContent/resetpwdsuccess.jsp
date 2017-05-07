<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码修改成功</title>
<!--
	作者：575969960@qq.com
	时间：2017-03-18
	描述：引入bootstrap和插件
-->
<link rel="stylesheet" type="text/css" href="css/NEW/default.css" />
<link rel="stylesheet" type="text/css" href="css/NEW/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/NEW/styles.css" />
<link rel="stylesheet" type="text/css" href="css/NEW/content.css" />
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/npm.js"></script>
</head>
<body style="background-color: #ecf0f1;">
	<div class="concise_header">
		<a href="login.jsp"><img src="assets/logo.png" height="100%" /></a>
		<h3 style="display: inline;">
			<span class="glyphicon glyphicon-euro"></span>爱打折
		</h3>
	</div>
	<div id="normal_content">
		<h3>密码修改成功！</h3>
		<a href="${pageContext.request.contextPath }/login.jsp">登录</a>
	</div>
	<footer> 这里是页脚 </footer>
</body>
</html>