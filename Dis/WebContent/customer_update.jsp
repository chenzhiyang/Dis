<%@page import="com.cj.discount.model.Customer"%>
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

<title>用户注册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script language="javascript">
	function myCheck() {
		if (document.getElementById("username").value == "") {
			alert("用户名不能为空，请重新输入");
			return false;
		}
		if (document.getElementById("password").value == "") {
			alert("密码不能为空，请重新输入");
			return false;
		}
		if (document.getElementById("password").value != document
				.getElementById("confirmpass").value) {
			alert("两次密码不一致，请重新输入");
			return false;
		}
		if (document.getElementById("name").value == "") {
			alert("用户名不能为空，请重新输入");
			return false;
		}

	}
</script>
<!--
	作者：575969960@qq.com
	时间：2017-03-18
	描述：引入bootstrap和css
	-->
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/NEW/content.css" />
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/npm.js"></script>
</head>

<body style="background-color: #ecf0f1;">
	<div id="normal_header">
		<h1>
			<span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>用户修改
		</h1>
	</div>
	<hr>
	<%
		if (request.getAttribute("cflag") == "exist") {
	%>
	<script type="text/javascript">
		alert("用户名已存在");
	</script>
	<%
		}
	%>
	<div align="center" id="normal_content">
		<form name="regForm" action="servlet/CustomerUpdateServlet"
			method="post" onsubmit="return myCheck();">
			<table border="0" width="400">
				<%
					Customer customer = new Customer();
					if (request.getSession().getAttribute("customer") != null) {
						customer = (Customer) request.getSession().getAttribute(
								"customer");
				%>
				<tr>
					<td class="lalel">用户名：</td>
					<td class="controler"><input type="text" class="form-control"
						id="username" name="username" value="<%=customer.getUsername()%>" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td class="controler"><input type="password"
						class="form-control" id="password" name="password"></td>
				</tr>
				<tr>
					<td width="200">确认密码：</td>
					<td class="controler"><input type="password"
						class="form-control" id="confirmpass" name="confirmpass"></td>
				</tr>
				<tr>
					<td class="lalel">昵称：</td>
					<td class="controler"><input type="text" id="name"
						class="form-control" name="name" value="<%=customer.getName()%>" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input
						class="btn btn-primary btn-lg" type="submit" value="修改" />&nbsp;&nbsp;
						<input type="reset" value="取消" />&nbsp;&nbsp;</td>
				</tr>
				<%
					}
				%>
			</table>
		</form>
	</div>
	<footer> 这里是页脚 </footer>
</body>
</html>
