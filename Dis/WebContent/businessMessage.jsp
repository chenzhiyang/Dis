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

<title>商家信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<h1>用户注册</h1>
	<hr>
	<div align="center">
		<form name="regForm" action="servlet/RegServlet" method="post"
			onsubmit="return myCheck();">
			<table border="0" width="400">
				<tr>
					<td class="lalel">邮箱：</td>
					<td class="controler"><input type="text" id="mail" name="mail" /></td>
				</tr>
				<tr>
					<td class="lalel">用户名：</td>
					<td class="controler"><input type="text" id="username"
						name="username" /></td>
				</tr>
				<tr>
					<td class="label">密码：</td>
					<td class="controler"><input type="password" id="password"
						name="password"></td>
				</tr>
				<tr>
					<td class="label" width="200">确认密码：</td>
					<td class="controler"><input type="password" id="confirmpass"
						name="confirmpass"></td>
				</tr>
				<tr>
					<td class="lalel">商家名：</td>
					<td class="controler"><input type="text" id="name" name="name" /></td>
				</tr>
				<tr>
					<td class="label">商家类别：</td>
					<td class="controler"><input type="radio" name="type"
						value="furniture" checked="checked"> 家具城 &nbsp; <input
						type="radio" name="type" value="shop"> 百货商场 &nbsp; <input
						type="radio" name="type" value="store"> 专卖店 &nbsp; <input
						type="radio" name="type" value="circuit"> 电器城 &nbsp;</td>
				</tr>
				<tr>
					<td class="lalel">商家地址：</td>
					<td class="controler"><input type="text" id="address"
						name="address" /></td>
				</tr>
				<tr>
					<td class="label" valign="top">注册条款：</td>
					<td class="controler"><textarea name="term" rows="7" cols="40"
							readonly="readonly">&nbsp;&nbsp;当您按照注册页面提示填写信息、阅读并同意协议且完成全部注册程序后，即表示您已充分阅读、理解并接受协议的全部内容。如您因平台服务与爱打折发生争议的，适用《爱打折平台服务协议》处理。如您在使用平台服务过程中与其他用户发生争议的，依您与其他用户达成的协议处理。</textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="注册" />&nbsp;&nbsp;
						<input type="reset" value="取消" />&nbsp;&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>
</body>