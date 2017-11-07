<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  	<script type="text/javascript">
  	 	function tijiao(){
  	 		var tishi = document.getElementById("tishi");
  	 		var uname = document.getElementById("uname").value;
  	 		if(uname.trim() ==''){
  	 			tishi.innerHTML="用户名不能空";
  	 			return false;
  	 		}
  	 		var pwd = document.getElementById("pwd").value;
  	 		if(pwd.trim() ==''){
  	 			tishi.innerHTML = "密码不能为空";
  	 			return false;
  	 		}
  	 		
  	 		var myform = document.getElementById("myform");
  	 		myform.submit();
  	 	}
  	</script>
  </head>
  
  <body>
    <form id="myform" action="<%=basePath%>LoginSvl" method="post">
       <table align="center">
   		 <tr height=200></tr>
         <tr>
             <td>用户名：</td>
             <td><input type="text" name="uname" id="uname"></td>
         </tr>
         <tr>
            <td>密码：</td>
            <td><input type="password" name="pwd" id="pwd"></td>
         </tr>
         <tr align="center">
            <td colspan="2"><input type="button" value="提交" onclick="tijiao()"></td>
         </tr>
         <tr align="center">
            <td colspan="2"> ${msg}  <span id="tishi"> </span> </td>
         </tr>
       </table>
    </form>
  </body>
</html>
