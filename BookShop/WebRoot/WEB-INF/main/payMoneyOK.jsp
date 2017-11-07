<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'payMoneyOK.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <table align="center" width=90%>
      <jsp:include page="mhead.jsp"></jsp:include>
      <tr>
      	<td>
      		<table border="1" width=100%> 
			    <tr><td align=center colspan=4><font color="red;size=18">付款成功！我们会尽快为您进行配送 </font> </td></tr>      			
       			
      		    <tr><td colspan=4 align=center>账户余额：￥${user.account}  &nbsp;&nbsp;&nbsp;&nbsp; 付款总额：￥${allmoney}</td></tr>
    		</table>
      	</td>
      </tr>
      <tr>
      	
      		<td align="center"> <a href="<%=basePath%>MainSvl">返回主页</a></td>
        	
      </tr>
    
    </table>
  </body>
</html>
