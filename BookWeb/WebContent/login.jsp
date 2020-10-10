<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div{
		width: 50%; /*宽度 */
		height: 200px; /*高度 */
		margin-left: 25%; /*左外边距 */
		text-align: center; /*内容水平居中 */
		border: 1px #999 solid; /*边框 */
		margin-top: 10%;
		padding-top: 100px;
		
	}
	input{
		width: 200px;
		height: 30px;
		margin-top: 5px;
	}
	.btn{
		border: 1px red solid;
		background-color: red;
		color: white;
		outline: none;
	}
	
</style>
</head>
<body>
	<div>
		<c:if test="${param.info==1}">
			<span style="color:red;font-size:14px">账号密码错误！</span>
		</c:if>
		<c:if test="${param.info==2}">
			<span style="color:red;font-size:14px">身份验证已过期，请重新登录！</span>
		</c:if>
		<form action="user/login" method="post">
			<input type="text" name="username" placeholder="请输入用户名"/>
			<br/> <!-- 换行 -->
			<input type="password" name="password" placeholder="请输入密码"/>
			<br/> 
			<input type="submit" value="登录" class="btn"/>
		</form>
	</div>
</body>
</html>