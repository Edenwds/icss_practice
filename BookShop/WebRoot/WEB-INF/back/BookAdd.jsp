<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'BookAdd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
    <script type="text/javascript">
	   function tijiao(){
	      var isbn = document.getElementById("isbn").value;
	      var bname = document.getElementById("bname").value;
	        var tishi1 = document.getElementById("tishi1");
	      var tishi2 = document.getElementById("tishi2");
	      if(isbn==''){
	         tishi1.innerHTML="isbn不能为空";
	         return;
	      }
	      if(bname==''){
	      	 tishi2.innerHTML="书名不能为空";
	      	 return;
	      }
	      
	      var myform = document.getElementById("myform");
	      myform.submit();	   
	   }	
	</script>
  </head>
  
  <body>
   <table align="center" width=90%>    
     <jsp:include page="bhead.jsp"></jsp:include>  	
      <tr><td align="left"><h2>新书上架</h2></td></tr>
      <tr>
      	<td>
      	<form id=myform action="<%=basePath%>back/BookAddSvl" method="post" enctype="multipart/form-data"">
      		<table border="0" width=60% align="center">  		
      		
      			<tr><td>书号ISBN</td><td><input type="text" name="isbn" id="isbn" value="${book.isbn}"/><span id="tishi1"></span></td></tr>
       			<tr><td>书名</td><td><input type="text" name="bname" id="bname" value="${book.bname}" /><span id="tishi2"></span></td></tr>
       			<tr><td>作者</td><td><input type="text" name="author" value="${book.author}" /></td></tr>
       			<tr><td>出版社</td><td><input type="text" name="press" value="${book.press}"/></td></tr>
       			<tr><td>出版日期</td><td><input class="easyui-datebox" name="pubdate"/></td></tr>
       			<tr><td>价格</td><td><input class="easyui-numberbox" precision="1" name="price" value="0.0"></td></tr>
       			<tr><td>图片上传</td><td><input type="file" name="pic"/></td></tr>
       			<tr><td colspan=2 align=center><input type=button value=提交 onclick="tijiao()" /></td></tr>
       			<tr><td colspan=2 align=center>${msg}</td></tr>
    		</table>
    	</form>
      	</td>
      </tr>
    </table>
  </body>
</html>
