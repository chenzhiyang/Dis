<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重新设置密码</title>
<style type="text/css">
.error {
	color: red;
	padding-left: 5px;
}
</style>
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
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/npm.js"></script>
</head>
<% String CHECK_CODE=request.getParameter("checkCode");
   request.getSession().setAttribute("CHECK_CODE", CHECK_CODE); 
   System.out.println(request.getSession().getAttribute("CHECK_CODE"));%>
<body style="background-color: #ecf0f1;">
	<div class="concise_header">
		<a href="login.jsp"><img src="assets/logo.png" height="100%" /></a>
		<h3 style="display: inline;">
			<span class="glyphicon glyphicon-euro"></span>爱打折
		</h3>
	</div>
	<div class="content">
		<form
			action="${pageContext.request.contextPath}/servlet/ChangePasswordServlet"
			method="post" role="form">
			<span class="error" style="display: block;">${errors.passwordError}</span>
			<label for="password" class="col-sm-2 control-label">用户名：</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="userName"
					value="${userName}" readonly="readonly" /><br />
			</div>
			<label for="password" class="col-sm-2 control-label">新密码：</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" name="newPassword" /><span
					class="error">${errors.newPassword }</span><br />
			</div>
			<label for="password" class="col-sm-2 control-label">确认新密码：</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" name="newPassword2" /><span
					class="error">${errors.newPassword2 }</span><br />
			</div>
			<input type="submit" class="btn btn-primary btn-lg" value="修改" />
		</form>
	</div>
	<footer> 这里是页脚 </footer>
</body>
</html>