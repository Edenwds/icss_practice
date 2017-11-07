<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<tr>
      	<td align=right>
      	
      		   <c:if test="${user != null}">
      	  			管理员: ${user.uname} &nbsp;	  		       	         	    	         	          	  	
      				<a href="<%=basePath%>LogoutSvl">退出</a>
      		   </c:if> 	
      	</td>       	
      </tr>
      
      <tr>
      	<td align=center>
      	    <a href="<%=basePath%>back/BookAddSvl">新书上架</a> &nbsp;  <a href="#">书增加库存</a>  &nbsp;  <a href="#">书下架</a> &nbsp;  <a href="#">用户管理</a>
      	    &nbsp;  <a href="#">修改售价</a> &nbsp; <a href="<%=basePath%>back/BuyinfoSvl">用户购买记录</a>
      	</td>
      	</tr>    	
      <tr><td align="left"><h2>购买记录</h2></td></tr>