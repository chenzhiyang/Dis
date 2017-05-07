<%@page import="com.cj.discount.model.User"%>
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

<title>商户修改</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
			alert("店名不能为空，请重新输入");
			return false;
		}
		if (document.getElementById("address").value == "") {
			alert("地址不能为空，请重新输入");
			return false;
		}
		if (document.getElementById("isAccept").checked == false) {
			alert("您未同意注册条款");
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
	<%
		if (request.getAttribute("bflag") == "exist") {
	%>
	<script type="text/javascript">
		alert("用户名已存在");
	</script>
	<%
		}
	%>
	<div class="concise_header">
		<a href="login.jsp"><img src="assets/logo.png" height="100%" /></a>
		<h3 style="display: inline;">
			<span class="glyphicon glyphicon-euro"></span>爱打折
		</h3>
	</div>
	<div align="center" id="normal_content">
		<form name="regForm" action="servlet/BusinessUpdateServlet"
			method="post" onsubmit="return myCheck();">
			<table border="0" width="390">
				<%
					User user = new User();
					if (request.getSession().getAttribute("business") != null) {
						user = (User) request.getSession().getAttribute("business");
				%>
				<tr>
					<td class="lalel">用户名：</td>
					<td class="controler"><input type="text" class="form-control"
						id="username" name="username" value="<%=user.getUsername()%>" /></td>
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
					<td class="lalel">商家名：</td>
					<td class="controler"><input type="text" class="form-control"
						id="name" name="name" value="<%=user.getName()%>" /></td>
				</tr>
				<tr>
					<td valign="top">商家类别：</td>
					<td class="controler"><input type="radio" name="type"
						value="furniture" checked="checked"> 家具城 &nbsp; <input
						type="radio" name="type" value="shop"> 百货商场 &nbsp; <input
						type="radio" name="type" value="store"> 专卖店 &nbsp; <input
						type="radio" name="type" value="circuit"> 电器城 &nbsp;</td>
				</tr>
				<tr>
					<td class="lalel">商家地址：</td>
					<td class="controler"><input type="text" class="form-control"
						id="address" name="address" value="<%=user.getAddress()%>" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						class="btn btn-primary btn-lg" value="修改" />&nbsp;&nbsp; <input
						type="reset" value="取消" />&nbsp;&nbsp;</td>
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
