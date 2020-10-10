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
    
    <title>My JSP 'edit.jsp' starting page</title>
    
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
<h3 align="center">编辑客户</h3>
<form action="<c:url value='/CustomerServlet/edit'/>" method="post">
	<!-- 要传递cid -->
	<input type="hidden" name="cid" value="${cstm.cid }"/>
	<table border="0" align="center" width="800px" style="padding-left:220px">
				<tr>
			<td width="100px">客户名称</td>
			<td width="40%">
				<input type="text" name="cname" value="${cstm.cname }" />
			</td>
			<td align="left" style="color:red;">
				<label id="cnameError" class="error">&nbsp;</label>
			</td>
		</tr>
		<tr>
			<td>客户性别</td>
			<td>
				<input type="radio" name="gender" value="male" id="male" <c:if test="${cstm.gender eq 'male' }">checked='checked'</c:if> />
				<label for="male">男</label>
				<input type="radio" name="gender" value="female" id="female" <c:if test="${cstm.gender eq 'female' }">checked='checked'</c:if>  />
				<label for="female">女</label>
			</td>
			<td align="left" style="color:red;">
				<label id="genderError" class="error">&nbsp;</label>
			</td>
		</tr>
		
		<tr>
			<td>客户生日</td>
			<td>
				<input type="text" name="birthday" value="${cstm.birthday }" />
			</td>
			<td>
				<label id="birthdayError" class="error">&nbsp;</label>
			</td>
		</tr>
		
		<tr>
			<td>手机</td>
			<td>
				<input type="text" name="cellphone" value="${cstm.cellphone }" />
			</td>
			<td>
				<label id="cellphoneError" class="error">&nbsp;</label>
			</td>
		</tr>
		
		<tr>
			<td>客户邮箱</td>
			<td>
				<input type="text" name="email" value="${cstm.email }" />
			</td>
			<td>
				<label id="emailError" class="error">&nbsp;</label>
			</td>
		</tr>
		
		<tr>
			<td>描述</td>
			<td>
				<textarea rows="5" cols="30" name="description" >${cstm.description }</textarea>
			</td>
			<td>
				<label id="descriptionError" class="error">&nbsp;</label>
			</td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2">
				<input type="submit" value="提交保存" />
				<input type="reset" value="重置" />
			</td>
		</tr>
	</table>
</form>
  </body>
</html>
