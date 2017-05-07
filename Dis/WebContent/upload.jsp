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

<title>文件上传</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
		<form
			action="${pageContext.request.contextPath}/servlet/UploadHandleServlet"
			enctype="multipart/form-data" method="post" role="form">
			<div class="form-group">
				<label for="username" class="col-sm-2 control-label">上传用户：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="username"><br />
				</div>
				<div class="form-group">
					<label for="username" class="col-sm-2 control-label">上传文件1：</label>
					<div class="col-sm-10">
						<input type="file" name="file1"><br />
					</div>
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">上传文件2：</label>
						<div class="col-sm-10">
							<input type="file" name="file2"><br />
						</div>
						<div align="center">
							<input type="text" class="form-control" name="user"><br />
							<input class="btn btn-primary btn-lg" type="submit" value="提交">
						</div>
		</form>
	</div>

	<footer> 这里是页脚 </footer>
</body>
</html>
