<%@page import="com.cj.discount.model.Activity"%>
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
<link type="text/css" rel="stylesheet" href="css/style1.css" />
<title>活动审批</title>
<script language="javascript">
	function pass() {
		if (!confirm("是否通过该活动？")) {
			window.event.returnValue = false;
		}
	}

	function reject() {
		if (!confirm("是否驳回该活动？")) {
			window.event.returnValue = false;
		}
	}
	
	function del(){
		if (!confirm("是否删除该活动？")) {
			window.event.returnValue = false;
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
	<%
		if (request.getSession().getAttribute("action") == "noAction") {
	%>
	<script type="text/javascript">
		alert("您无法对其操作");
	</script>
	<%
		}
	%>
	<header>
	<h1>
		<span class="glyphicon glyphicon-sunglasses" aria-hidden="true"></span>我的活动信息
	</h1>
	<nav> <a href="activity_manager.jsp">主页</a> <a href="#">about</a>
	</nav> </header>
	<div id="normal_content" style="width: 1400px; height: 900px;"
		class="acivity_manager" align="center">
		<form>
			<table>
				<tr>
					<th>活动名称</th>
					<th>发布时间</th>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>活动状态</th>
					<th>活动内容</th>
					<th>点赞数</th>
					<th>操作</th>
				</tr>
				<%
					//首先判断session中是否有活动集合
					if (request.getSession().getAttribute("activities") != null) {
				%>
				<!-- 循环的开始 -->
				<%
					ArrayList<Activity> list = (ArrayList<Activity>) request
								.getSession().getAttribute("activities");
						for (Activity a : list) {
				%>
				<tr name="products" id="product_id_1">
					<td class="thumb"><a><%=a.getName()%></a></td>
					<td class="date">
						<%
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
									String issueDate = sdf.format(a.getIssueDate());
						%> <%=issueDate%>
					</td>
					<td class="date">
						<%
							String fromDate = sdf.format(a.getFromDate());
						%> <%=fromDate%>
					</td>
					<td class="date">
						<%
							String toDate = sdf.format(a.getToDate());
						%> <%=toDate%>
					</td>
					<td class="thumb"><a><%=a.getType()%></a></td>
					<td class="thumb"><a><%=a.getContent()%></a></td>
					<td class="thumb"><a><%=a.getZan()%></a></td>
					<td class="delete"><a
						href="servlet/ManagerServlet?action=pass&id=<%=a.getId()%>"
						onclick="pass();">通过</a>/<a
						href="servlet/ManagerServlet?action=reject&id=<%=a.getId()%>"
						onclick="reject();">驳回</a>/<a
						href="servlet/ManagerServlet?action=delete&id=<%=a.getId()%>"
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
	<footer>这也是页脚</footer>
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