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
    
    <title>My JSP 'query.jsp' starting page</title>
    
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
<h3 align="center">高级搜索</h3>
<form action="<c:url value='/CustomerServlet/query'/>" method="post">
	<table border="0" align="center" width="800px" style="padding-left:220px">
		<tr>
			<td width="100px">客户名称</td>
			<td>
				<input type="text" name="cname" />
			</td>
		</tr>
		<tr>
			<td>客户性别</td>
			<td>
				<select name="gender">
					<option value="">===请选择性别===</option>
					<option value="male">男</option>
					<option value="female">女</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>手机</td>
			<td>
				<input type="text" name="cellphone" />
			</td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td>
				<input type="text" name="email" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="submit" value="搜索" />
				<input type="reset" value="重置" />
			</td>
		</tr>
	</table>
</form>
  </body>
</html>
