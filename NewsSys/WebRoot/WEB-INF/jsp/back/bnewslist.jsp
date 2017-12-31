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
    
    <title>后台新闻列表页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%=path %>/css/index.css">
	<script type="text/javascript" src="<%=path %>/js/jquery-1.8.3.min.js"></script>
	<link rel="Stylesheet" type="text/css" href="<%=path %>/css/back.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
	 <script type="text/javascript">
       		function tijiao(){
       			var checkID=[];
       			$("input[name='checkbox']:checked").each(function(i){
       			      checkID[i] = $(this).val();
       			});
       			
       			$.ajax(
            		{data:{'checkID':checkID},
            		  dataType:'text',
            		  success: function(data){
            		  	alert(data);
            		  },
            		  type:'post',
            		  url:'<%=basePath%>back/AddHotNewsSvl',
            		  traditional:true
            		}
            	);
       		}
      
     </script>
		<style>
			.news-list {
			height: 120%;
			}
			<!-- .datalist {
				border: 1px solid #0058a3;
				/* 表格边框 */
				font-family: Arial;
				border-collapse: collapse;
				/* 边框重叠 */
				background-color: #E0E0E0;
				/* 表格背景色 */
				font-size: 14px;
			}
			
			.datalist th {
				/* 行名称边框 */
				background-color: #B8B8B8;
				/* 行名称背景色 */
				color: #FFFFFF;
				/* 行名称颜色 */
				font-weight: bold;
				padding-top: 4px;
				padding-bottom: 4px;
				padding-left: 12px;
				padding-right: 12px;
				text-align: center;
			}
			
			.datalist td {
				/* 单元格边框 */
				text-align: left;
				padding-top: 4px;
				padding-bottom: 4px;
				padding-left: 10px;
				padding-right: 10px;
			}
			
			.datalist tr.altrow {
				background-color: #c7e5ff;
				/* 隔行变色 */
			}
			.daohang{
			   position:relative;
			   top:0px;
			   left:37px;
			}
			-->
		</style>
  </head>
  
  <body>
		<nav class="navbar">
			<div class="container navbar-content">
				<a target="_self" href="<%=basePath%>back/ToNewsListSvl" >新闻管理</a>
				<a target="_self" href="<%=basePath%>back/ToNewsListSvl" >新闻列表</a>
				<div class="denglu">
					<c:if test="${admin!=null}">
						<font color="white">欢迎您：${admin.aname} !</font>
					</c:if>
					<a href="<%=basePath%>/back/LogoutBackSvl" id="example">退出</a>
				</div>
			</div>
			<!-- /.container-fluid -->
		</nav>

		<img src="img/beijing.jpg" class="index-bg">
		<div class="container">
			<h1>后台管理</h1>
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
						<a target="_self" href="<%=basePath%>back/ToNewsListSvl" title=""> 新闻列表 </a>
					</li>
					<li>&nbsp;&nbsp;&nbsp;</li>
					<li class="item">
						<a target="_self" href="<%=basePath%>back/AddNewsSvl" title=""> 添加新闻 </a>
					</li>
					<li>&nbsp;&nbsp;&nbsp;</li>

				</ul>
			</div>
			<div class="news-list">
				<table align="center" width=90%>
					<!-- 查询条件 -->
					<tr>
						<td>
							<form action="<%=basePath%>back/BNewslistSvl" method="post">
								<table>
									<tr>
										<td align="left"><span >新闻分类：</span></td>
											<td><select class="easyui-combobox" panelHeight="auto" style="width:100px" name="ntype"  >
												<c:forEach var="cg" items="${allcg}">
													<option value="${cg.cid}" <c:if test="${cg.cid==ntype}">selected</c:if>>${cg.cname}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td align=left>关键字：</td>
										<td><input type="text" name="keyword" value="${keyword}" /></td>
									</tr>
									<tr>
										<td align=left>开始日期： </td>
										<td><input class="easyui-datebox" name="beginDate" value="${beginDate}" /></td>
										<td align=left>结束日期： </td>
										<td><input class="easyui-datebox" name="endDate" value="${endDate}" /></td>
										<td><input type=submit value="查询" /></td>
									</tr>
								</table>
							</form>
						</td>
					</tr>

					<tr>
						<th align=left>
							<table border="1" width=100% class="datalist"  style="table-layout:fixed;">
								<tr>
									<th width="40px"  scope="col"><input type="button" value="设置热点"  onclick="tijiao()"/></th>
									<th width="50px"scope="col">新闻编号</th>
									<th width="150px"scope="col">新闻标题</th>
									<th width="70px"scope="col">发布时间</th>
									<th width="50px"scope="col">新闻来源</th>
									<th width="40px"scope="col">是否热点</th>
							   </tr>
								<c:forEach var="news" items="${newslist}">

									<tr>
										<td ><input type="checkbox" value="${news.nid}" name="checkbox" id="checkbox"  <c:if test="${news.ishot=='是'}">disabled</c:if> /></td>
										<td>${news.nid}</td>
										<td style="white-space: nowrap;text-overflow: ellipsis;overflow: hidden;" >${news.title}</td>
										<td style="white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">${news.pubtime}</td>
										<td>${news.source}</td>
										<td>${news.ishot}</td>
									</tr>
								</c:forEach>

								<tr>
									<td colspan=6>
										<table id="tblTurnPage" cellSpacing="0" cellPadding="1" width="100%" border="0" style="font-family:arial;color:black;font-size:12px;">
											<tr>
												<td>总记录数：${allRows} </td>
												<td>总页数：${allPages} </td>
												<td>当前页：${pageno}</td>
												<td>
													<a href="<%=basePath%>back/BNewslistSvl?page=1&ntype=${ntype}&keyword=${keyword}&beginDate=${beginDate}&endDate=${endDate}">首页||</a>
													<a href="<%=basePath%>back/BNewslistSvl?page=${pageno-1}&ntype=${ntype}&keyword=${keyword}&beginDate=${beginDate}&endDate=${endDate}">《前页&nbsp;||&nbsp;</a>
													<a href="<%=basePath%>back/BNewslistSvl?page=${pageno+1}&ntype=${ntype}&keyword=${keyword}&beginDate=${beginDate}&endDate=${endDate}">后页》||</a>
													<a href="<%=basePath%>back/BNewslistSvl?page=${allPages}&ntype=${ntype}&keyword=${keyword}&beginDate=${beginDate}&endDate=${endDate}">末页||</a>
												</td>
											</tr>
										</table>
									</td>
								</tr>

							</table>

					    </tr>
				</table>
			</div>
		</div>
    
	</body>
</html>
