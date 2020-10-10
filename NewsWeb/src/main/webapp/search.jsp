<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新闻查询页</title>
	<style>
		*{
			margin:0;
			padding:0;
		}
		
		.main{
			width:90%;
			margin-left:5%;
		}

		.main .btn{
			display:inline-block;
			background-color:#F0F0F0;
			color:#000;
			font-size: 16px;
			font-weight:bold;
			outline: none;
			width:70px;
			height:25px;
			line-height:25px;
			text-decoration: none;
		}
		.main .btn:hover {
			background-color:#1CBAB4;
			color: white;
		}
		
	</style>
</head>
<body>

<div id="top">
		<%@include file="top.jsp"%><br />
	</div>
	
	<h3>查询新闻</h3><br />
	<div class="main">
		<form action="news/search" method="post">
			<input type="text" placeholder="请输入新闻关键字" name="title">
			<input type="submit" value="搜索" class="btn">
		</form>
	</div>

</body>
</html>