<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
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
      		    <c:forEach var="book" items="${books}">
	      			<tr><td rowspan=3><img width=100 height=100 src="<%=basePath%>PicSvl?isbn=${book.isbn}"/></td><td colspan=2 align=center style="color:red"><a href="<%=basePath%>BookDetailSvl?isbn=${book.isbn}">${book.bname}</a></td></tr>
	       			<tr><td>商品价格</td><td>${book.price}</td></tr>
	       			<tr><td>出版社</td><td>${book.press}</td></tr>           		       			
       			</c:forEach>			
    	</table>
      	</td>
      </tr>
    </table>
  </body>
</html>
