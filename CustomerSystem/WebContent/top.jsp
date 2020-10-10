<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<!-- 它的作用是为本页面所有的表单和超链接指定显示的框架！ -->
  	<base target="main" />
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	a:link {
		color:blue;
	}
	a:hover {
		color:red;
	}
</style>	
  </head>
  
  
  <body style="text-align:center;">
  <br/>
<h1>客户关系管理系统</h1>
<a href="<c:url value='/add.jsp' />">添加客户</a> 
<a href="<c:url value='/CustomerServlet/findAll' />">查询客户</a> 
<a href="<c:url value='/query.jsp' />">高级搜索</a>
  </body>
</html>
