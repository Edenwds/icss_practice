<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'checkout.jsp' starting page</title>
    
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
  <form action="<%=basePath%>user/PayMoneySvl" method="post">
    <table align="center" width=90%>
      <jsp:include page="mhead.jsp"></jsp:include>
      <tr>
      	<td>
      		<table border="1" width=100%> 
      			<tr><td>书名</td><td>作者</td><td>商品价格</td><td width="5%">数量</td></tr>		       
       			    <c:forEach var="bk" items="${books}"> 			
       			     	<tr><td>${bk.bname}</td><td>${bk.author}</td><td>${bk.price}</td><td >${bk.buycount}本</tr>				
					</c:forEach>       			
      			    <tr><td colspan=4 align=center>账户余额：￥ ${user.account}  &nbsp;&nbsp;&nbsp;&nbsp; 商品总价：￥${allmoney}</td></tr>
      			    <tr><input type="hidden" name="allmoney" value="${allmoney}"></tr>
    		</table>
      	</td>
      </tr>
      <tr>      	
      		<td align="center"><input type="submit" value="付款确认">&nbsp; <a href="<%=basePath%>MainSvl">返回</a></td>        	
      </tr>    
    </table>
    </form>
  </body>
</html>
