<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@ page language="java" import="java.util.*,com.icss.biz.*,com.icss.entity.*,com.icss.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
// 自动登录校验
/* Object  obj = request.getSession().getAttribute("user");
if(obj == null){
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(int i = 0; i < cookies.length; i++){
			Cookie cookie = cookies[i];
			if(cookie.getName().equals("user")){
				String value = cookie.getValue();
				String [] upwd = value.split(",");
				UserBiz biz = new UserBiz();
				try{
					BUser buser = biz.login(upwd[0], upwd[1]);
					if(buser != null){
						request.getSession().setAttribute("user", buser);
					}
				}catch(Exception e){
					Log.logger.error(e.getMessage());
				}
				break;
			}
		}
	}
} */
request.getRequestDispatcher("/MainSvl").forward(request,response);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
    This is my JSP page. <br>
  </body>
</html>
