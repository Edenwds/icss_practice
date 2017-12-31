<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台登录页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="Stylesheet" type="text/css" href="<%=path%>/css/index.css" />
	<link rel="Stylesheet" type="text/css" href="<%=path%>/css/back.css" />
	<style type="text/css">
		.news-list {
			height: 100%;
		}
	</style>
  </head>
  
  	<body>
		<nav class="navbar">
			<div class="container navbar-content">
				<a href="#">后台管理</a>
				<a href="#">登录页面</a>
				<div class="denglu">
				</div>
			</div>
			<!-- /.container-fluid -->
		</nav>

		<img src="<%=path%>/img/beijing.jpg" class="index-bg">
		<div class="container">
			<h1>后台管理</h1>
		
			<div class="news-list">
				<form id="myform" action="<%=basePath%>LoginBackSvl" method="post">
					<table align="center">

						<tr height=150></tr>
						<tr>
							<td align="center">
								<h2>管理员登录</h2></td>
						</tr>
						<tr height=50></tr>
						<tr>
							<td>用户名：</td>
							<td> <input type="text" name="aname" id="aname"></td>
							<td><span id="titleMsg" style="color:#F00" align="left"></span></td>
						</tr>
						<tr>
							<td>密码：</td>
							<td> <input type="password" name="apwd" id="pwd"> </td>
							<td><span id="pwdMsg" style="color:#F00" align="left"></span></td>
						</tr>
						<tr>
						<td align="right" colspan="2"> <input type="submit" value="登录" > </td>
						</tr>
						<tr>
							<td align="center">${msg}</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>
</html>
