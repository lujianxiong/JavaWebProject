<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>

<script type="text/javascript">
//如果一个表单项的name和<img>的id相同，那么可能在IE浏览器上运行会出问题！
	function _change() {
		//1、获取img元素
		var ele = document.getElementById("vCode");
		ele.src="<c:url value='/VerifyCodeServlet'/>?xxx="+ new Date().getTime();
	}
</script>

</head>
<body>
<h1>注册</h1>
<p style="color: red; font-weight: 900">${msg }</p>
<form action="<c:url value='/RegisterServlet' />" method="post">
用户名：<input type="text" name="username" value="${user.username }" />${errors.username }<br/>
密    码：<input type="password" name="password" value="${user.password }" />${errors.password }<br/>
验证码：<input type="text" name="verifyCode" value="${user.verifyCode }" size="3" />
	  <img id="vCode" src="<c:url value='/VerifyCodeServlet'/>" border="2" />
	  <a href="javascript:_change()">换一张</a>${errors.verifyCode }<br/>
<input type="submit" value="注册" />
</form>

</body>
</html>