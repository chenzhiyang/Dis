<%@page import="com.cj.discount.model.Customer"%>
<%@page import="javax.jws.soap.SOAPBinding.Use"%>
<%@page import="com.cj.discount.model.User"%>
<%@ page language="java" import="java.util.*,java.text.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="css/customer_style.css" />
<title>活动审批</title>
<script language="javascript">
	function del() {
		if (!confirm("是否删除该用户？")) {
			window.event.returnValue = false;
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
		if (request.getSession().getAttribute("action") == "noAction") {
	%>
	<script type="text/javascript">
		alert("您无法对其操作");
	</script>
	<%
		}
	%>
	<div class="concise_header">
		<a href="login.jsp"><img src="assets/logo.png"
			style="width: auto; height: auto; max-width: 100%; max-height: 100%;" /></a>
		<h3 style="display: inline;">
			<span class="glyphicon glyphicon-euro"></span>爱打折
		</h3>
	</div>
	<div id="normal_content" align="center">
		<form>
			<table>
				<tr>
					<th>序号</th>
					<th>用户名</th>
					<th>用户名称</th>
					<th>操作</th>
				</tr>
				<%
					//首先判断session中是否有活动集合
					if (request.getSession().getAttribute("customers") != null) {
				%>
				<!-- 循环的开始 -->
				<%
					ArrayList<Customer> list = (ArrayList<Customer>) request.getSession()
								.getAttribute("customers");
						for (Customer c : list) {
				%>
				<tr name="products" id="product_id_1">
					<td class="thumb"><a><%=c.getId()%></a></td>
					<td class="thumb"><a><%=c.getUsername()%></a></td>
					<td class="thumb"><a><%=c.getName()%></a></td>
					<td class="delete"><a
						href="servlet/CustomerServlet?action=update&id=<%=c.getId()%>">修改</a>/<a
						href="servlet/CustomerServlet?action=delete&id=<%=c.getId()%>"
						onclick="del();">删除</a></td>
				</tr>
				<%
					}
					}
				%>
				<!--循环的结束-->
			</table>
		</form>
	</div>
</body>
</html>