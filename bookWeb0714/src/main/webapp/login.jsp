<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书籍管理系统登录页</title>
<style>
	*{
		margin:0;
		padding: 0;
	}
	
	div{
		width:50%;
		margin-left:25%;
		height:200px;
		margin-top:100px;
		padding-top:100px;
		text-align:center;
		border:1px #ccc solid;
	}
	input{
		width:200px;
		height:30px;
		margin-top:10px;
	}
	
	.btn{
		background-color: #0C83F4;
		color:white;
		font-size:18px;
		outline:none;
		border:1px #0C83F4 solid;
	}
	
</style>

</head>
<body>
	<div>
		<form action="admin" method="post">
		<!-- alt+/ 默认代码提示快捷键 -->
			<input type="text" name="username" placeholder="帐号"/><br/>
			<input type="password" name="password" placeholder="密码"/><br/>
			<input type="submit" value="登录" class="btn"/>
		</form>
			
	</div>
</body>
</html>