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
    
    <title>添加新闻</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=path %>/editormd/examples/css/style.css" />
    <link rel="stylesheet" href="<%=path %>/editormd/css/editormd.css" />
    <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="<%=path%>/css/index.css">
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/new.js"></script>
	<link rel="stylesheet"href="<%=path%>/css/back.css" />
	<link rel="stylesheet"href="<%=path%>/css/button.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/themes/icon.css">
	<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
	<style type="text/css">
	   .biaoti{
	   		position:relative;
	   		left:-440px;
	   		color:000000;
	   }
	</style>
  </head>
  
  <body>
		<nav class="navbar">
			<div class="container navbar-content">
				<a target="_self" href="<%=basePath%>/back/ToNewsListSvl">新闻管理</a>
				<a target="_self" href="<%=basePath%>/back/AddNewsSvl">添加新闻</a>
				<div class="denglu">
					<c:if test="${admin!=null}">
						<font color="white">欢迎您：${admin.aname} !</font>
					</c:if>
					<a href="<%=basePath%>/back/LogoutBackSvl" id="example">退出</a>
				</div>
			</div>
			<!-- /.container-fluid -->
		</nav>

		<img src="<%=path%>/img/beijing.jpg" class="index-bg">
		<div class="container">
			<div class="biaoti"><h1>后台管理</h1></div>
			<div class="daohang">
				<style>
					li {
						float: left;
						list-style: none;
						font-size:20px;
					}
				</style>
				<ul class="colsItem cols01">
					<li class="total">
						<a target="_self" href="<%=basePath%>/back/ToNewsListSvl" title=""> 新闻列表 </a>
					</li>
					<li>&nbsp;&nbsp;&nbsp;</li>
					<li class="item">
						<a target="_self" href="<%=basePath%>/back/AddNewsSvl" title=""> 添加新闻 </a>
					</li>
					<li>&nbsp;&nbsp;&nbsp;</li>

				</ul>
			</div>
			<div class="news-list">
				<form action="<%=basePath%>back/AddNewsSvl" method="post" id="myform" enctype="multipart/form-data">
					<table align="center" width=100%>
						<tr>
							
						</tr>
						<tr>
							<td align="left"><span style="font-size:20px;font-weight:bold;">分类：</span>
								<select class="easyui-combobox" panelHeight="auto"  style="width:100px" name="ntype">
									<c:forEach var="cg" items="${allcg}">
										<option value="${cg.cid}">${cg.cname}</option>
									</c:forEach>
								</select>
							</td>
							<td align="left"><span style="font-size:20px;font-weight:bold;">封面</span>
								<input type="file" name="file">
								<input type="button" value="发布" onclick="tijiao2()" style="height:40px;width:80px;" class="button button-glow button-rounded button-caution" istyle="height:10px;"/> 
						</tr>

						<tr>
							<td align="left"><span style="font-size:20px;font-weight:bold;">标题：</span></td>
						</tr>
						<tr>
							<td align="left">
								<input type="text" id="title" name="title" style="width:500px;height: 30px;">
								<td><span id="titleMsg" style="color:#F00" align="left"></span></td>
							</td>
						</tr>
						<tr>
							<td align="left"><span style="font-size:20px;font-weight:bold;">来源：</span></td>
						</tr>
						<tr>
							<td align="left">
								<input type="text" name="source" style="width:500px;height: 30px;">
								<td><span id="titleMsg" style="color:#F00" align="left"></span></td>
							</td>
						</tr>
						<tr>
							<td align="left"><span style="font-size:20px;font-weight:bold;">内容：</span></td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								 <div id="layout">
         								 <!--  <input type="button" value="发布" onclick="tijiao()" style="height:40px;width:80px;" class="button button-glow button-rounded button-caution" istyle="height:10px;"/> -->
           								 	<div id="test-editormd">
                							<textarea style="display:none;" class="editormd-html-textarea" id="editormd">本地上传图片宽度最大限制为550px,图片名称只能包括字母、数字、下划线</textarea>
											<textarea class="editormd-html-textarea" name="text" id="editormdhtml"></textarea>
            								</div>
            
      						  </div>
							</td>
						</tr>

					</table>
					</form>	
			</div>
		</div>
	  <script src="<%=path %>/editormd/examples/js/jquery.min.js"></script>
        <script src="<%=path %>/editormd/editormd.min.js"></script>
        <script type="text/javascript">
			var testEditor;

            $(function() {
                testEditor = editormd("test-editormd", {
                    width   : "100%",
                    height  : 1000,
                    syncScrolling : "single",
                    path    : "<%=path %>/editormd/lib/",
                    imageUpload : true,
                    imageFormats : ["jpg","jpeg","gif","png","bmp","webp"],
                    imageUploadURL : "<%=basePath%>PicServlet",
                    saveHTMLToTextarea : true
                });
           
                /*
                // or
                testEditor = editormd({
                    id      : "test-editormd",
                    width   : "90%",
                    height  : 640,
                    path    : "../lib/"
                });
                */
            });
 
       
            function tijiao2(){
				var myform = document.getElementById("myform");
				myform.submit();            
            }
        </script>
	</body>
</html>
