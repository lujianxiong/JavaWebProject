<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
</head>
<body>

<h1>登录</h1>
<p style="color: red; font-weight: 900">${msg }</p>
<form action="<c:url value='/LoginServlet'/>" method="post">
用户名：<input type="text" name="username" value="${user.username }" /><br/>
密    码：<input type="password" name="password" value="${user.password }" /><br/>
<input type="submit" value="登录" />
</form>

</body>
</html>