<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加成功</title>
    <meta http-equiv="refresh" content="5;url=<%=basePath%>back/AddNewsSvl">
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
			height: 80%;
			}
			.tupian{
				position:relative;
				top:140px;
				left:350px;
			}
			
			.tiaozhuan{
			   position:relative;
			   top:200px;
			   left:110px;
			}
	</style>
	<script type="text/javascript">
	    var i = 6;
	    function shownum(){
	    	i = i - 1;
	    	document.getElementById("time").innerHTML=i + "秒后自动返回添加页面";
	    	setTimeout('shownum()',1000);
	    }
	</script>
  </head>
  
 		<body onload="shownum()">
		<nav class="navbar">
			<div class="container navbar-content">
				<a href="#">首页</a>
				<a href="#">热点资讯</a>
				<div class="denglu">
					<input type="text" onmouseoff="this.className='input_off'" autocomplete="off" maxlength="255" value="" id="inputwd" class="ss" name="wd" placeholder="搜索" data-inputcolor="#9c9c9c" />
					<a href="#" id="example">退出</a>
				</div>
			</div>
			<!-- /.container-fluid -->
		</nav>

		<img src="img/beijing.jpg" class="index-bg">
		<div class="container">
			<h1>后台管理</h1>
	
			<div class="news-list">
				<img src="<%=path %>/img/chenggong.png"  style=" width:300px;hight:90px;" class="tupian">
				<span id="time" class="tiaozhuan"></span>
			</div>
		</div>

	</body>
</html>
