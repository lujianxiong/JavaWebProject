<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>欢迎光临Daxiong的系统！</h1>
<c:choose>
	<c:when test="${empty sessionScope.sessionUser }">滚！</c:when>
	<c:otherwise>
		${sessionScope.sessionUser }
	</c:otherwise>
</c:choose>

</body>
</html>