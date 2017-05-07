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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script language="javascript">
	function myCheck() {
		if (document.getElementById("username").value == "") {
			alert("用户名不能为空，请重新输入");
			return false;
		}
		if (document.getElementById("mail").value == "") {
			alert("邮箱不能为空，请重新输入");
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
	<header>
	<h1>用户注册</h1>
	<nav> <a href="login.jsp">主页</a> <a href="">About</a> </nav> </header>
	<%
		if (request.getAttribute("flag1")=="exist") {
	%>
	<script type="text/javascript">
		alert("该邮箱已注册过");
	</script>
	<%
		}
	%>
	<%
		if (request.getAttribute("flag2")=="exist") {
	%>
	<script type="text/javascript">
		alert("用户名已存在");
	</script>
	<%
		}
	%>
	<div align="center" class="content">
		<div id="huanying" align="center">
			<h1>
				<a href="#"> <span class="glyphicon glyphicon-user"></span>
				</a>欢迎注册
			</h1>
		</div>
		<hr />
		<form name="regForm" action="servlet/RegServlet" method="post"
			role="form" onsubmit="return myCheck();">
			<div class="form-group">
				<label for="username" class="col-sm-2 control-label">邮箱：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="mail" name="mail" /><br />
				</div>
			</div>
			<div class="form-group">
				<label for="username" class="col-sm-2 control-label">用户名：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="username"
						name="username" /><br />
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label">密码：</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password"
						name="password"><br />
				</div>
			</div>
			<div class="form-group">
				<label for="confirmpass" class="col-sm-2 control-label">确认密码：</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="confirmpass"
						name="confirmpass"><br />
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">商家名：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name" name="name" /><br />
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">商家类别：</label> <label
					class="radio-inline"> <input type="radio" name="type"
					value="furniture" checked="checked"> 家具城 &nbsp;
				</label> <label class="radio-inline"> <input type="radio"
					name="type" value="shop"> 百货商场 &nbsp;
				</label> <label class="radio-inline"> <input type="radio"
					name="type" value="store"> 专卖店 &nbsp;
				</label> <label class="radio-inline"> <input type="radio"
					name="type" value="circuit"> 电器城 &nbsp;
				</label>
			</div>
			<div class="form-group">
				<label for="address" class="col-sm-2 control-label">商家地址：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="address" name="address" /><br />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">注册条款：</label>
				<textarea name="term" rows="7" cols="50" readonly="readonly">&nbsp;&nbsp;
								当您按照注册页面提示填写信息、阅读并同意协议且完成全部注册程序后，即表示您已充分阅读、理解并接受协议的全部内容。如您因平台服务与爱打折发生争议的，适用《爱打折平台服务协议》处理。如您在使用平台服务过程中与其他用户发生争议的，依您与其他用户达成的协议处理。
							</textarea>
				<br />
			</div>
			<div class="checkbox">
				<label for="address" class="col-sm-2 control-label"></label> <label>
					<input type="checkbox" id="isAccept" name="isAccept" value="true">是否接受以上条款<br />
				</label>
			</div>
			<div align="center">
				<input type="submit" class="btn btn-primary btn-lg" value="注册" />&nbsp;&nbsp;
				<input type="button" value="取消" onclick="javascript:history.back();"
					id="quxiao" />&nbsp;&nbsp;
			</div>
			</table>
		</form>
	</div>
	<footer> 这里是页脚 </footer>

	<!--
    	作者：575969960@qq.com
    	时间：2017-03-18
    	描述：以下为浮动菜单脚本
   -->
	<script>window.jQuery || document.write('<script src="js/jquery-2.1.1.min.js"><\/script>')</script>
	<script>
	$(function () {
	    $(window).scroll(function () {
	        var winTop = $(window).scrollTop();
	        if (winTop >= 30) {
	            $('body').addClass('sticky-header');
	        } else {
	            $('body').removeClass('sticky-header');
	        }
	    });
	});
	</script>
</body>
</html>
