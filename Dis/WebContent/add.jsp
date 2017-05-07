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

<title>添加活动信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link type="text/css" rel="stylesheet" href="css/style1.css" />

<script type="text/javascript" src="js/Calendar3.js"></script>
<script language="javascript">
	function myCheck() {
		if (document.getElementById("activityName").value == "") {
			alert("活动名不能为空，请重新输入");
			return false;
		}
		if (document.getElementById("control_date").value == "") {
			alert("日期不能为空，请重新输入");
			return false;
		}
		if (document.getElementById("content").value == "") {
			alert("折扣内容不能为空，请重新输入");
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
			<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>添加活动
		</h1>
	</div>
	<hr>
	<div align="center" id="normal_content">
		<form name="addForm" action="servlet/AddServlet" method="post"
			enctype="multipart/form-data" onsubmit=" returnmyCheck();">
			<table border="0" width="400">
				<tr>
					<td class="lalel">活动名：</td>
					<td class="controler"><input type="text" class="form-control"
						id="activityName" name="activityName" /></td>
				</tr>
				<tr>
					<td>开始时间：</td>
					<td class="controler"><input class="form-control"
						name="fromTime" type="text" id="control_date" size="10"
						maxlength="10" onclick="new Calendar().show(this);"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<td>结束时间：</td>
					<td class="controler"><input class="form-control"
						name="toTime" type="text" id="control_date" size="10"
						maxlength="10" onclick="new Calendar().show(this);"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<td class="lalel">折扣内容：</td>
					<td class="controler"><input class="form-control" type="text"
						id="content" name="content" /></td>
				</tr>

				<tr>
					<td class="lalel">选择图片：</td>
					<td><input type="file" name="file1"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						class="btn btn-primary btn-lg" value="提交" />&nbsp;&nbsp; <a
						href="activity.jsp">取消</a>&nbsp;&nbsp;</td>
					<!-- <input type="reset" value="取消" /> -->
				</tr>
			</table>
		</form>
	</div>
	<footer> 这里是页脚 </footer>
</body>
</html>
