<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>爱打折欢迎您</title>
<!-- Libraries -->
<link type="text/css" href="css/login.css" rel="stylesheet" />
<link type="text/css" href="css/smoothness/jquery-ui-1.7.2.custom.html"
	rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/easyTooltip.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.7.2.custom.min.js"></script>
<!-- End of Libraries -->
</head>
<body>
	<%
		if (request.getSession().getAttribute("eFlag") == "pswerror") {
	%>
	<script type="text/javascript">
		alert("用户名或密码错误，请重新登录");
	</script>
	<%
		}
	%>
	<div id="container">
		<div class="logo">
			<a href="#"><img src="assets/logo.png" alt="" /></a>
		</div>
		<div id="box">
			<form action="servlet/LoginServlet" method="post">
				<p class="main">
					<label>用户名: </label> <input name="username" value="" /> <label>密码:
					</label> <input type="password" name="password" value=""> <a
						href='/Dis/forgotpwd.jsp'>忘记密码</a>
				</p>
				<p class="space">
					<input type="submit" value="登录" class="login"
						style="cursor: pointer;" /> <input type="button" value="注册"
						onclick="window.location.href='register.jsp'" class="register"
						style="cursor: pointer;" />
				</p>
			</form>
		</div>
	</div>
</body>
</html>