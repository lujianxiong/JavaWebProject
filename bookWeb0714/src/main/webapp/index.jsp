<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书籍管理系统主页</title>
<style>
	*{
		margin:0;
		padding:0;
	}
	.top{
		width:90%;
		margin-left:5%;
		text-align:center;
		height:80px;
		padding-top:5%;
	}
	.top input{
		width:400px;
		height:30px;
	}
	.top .btn{
		width:80px;
		height:32px;
		background-color:#1CBAB4;
		color:white;
		font-size:16px;
		outline:none;
		border:1px #1CBAB4 solid;
		position:relative;    /*相对于自己原位置定位*/
		left:-8px;
		top:1px;
		
	}
	
	.main{
		width:90%;
		margin-left:5%;
	}
	.main table{
		width:90%;
		margin-left:5%;
		margin-top:10px;
		text-align:center;
		border:1px #4B9A4F solid;
		border-collapse:collapse;
	}
	.main td,th{
		border:1px #4B9A4F solid;
	}
	.main tr{
		height:50px;
	}
	.main .btn{
		display:inline-block;
		background-color:#F0F0F0;
		color:#000;
		font-size: 16px;
		font-weight:bold;
		outline: none;
		width:70px;
		height:30px;
		line-height:30px;
		text-decoration: none;
	}
	.main .btn:hover {
		background-color:#1CBAB4;
		color: white;
	}
	
</style>

</head>
<body>
	<div class="top">
		<form action="book/search" method="post">
			<input type="text" placeholder="请输入书名" name="word">
			<input type="submit" value="搜索" class="btn">
		</form>
	</div>
	
	<div class="main">
	<a href="addBook.jsp" class="btn" style="margin-left: 5%;text-align: center;">添加书籍</a>
	<table>
		<tr>
			<th>书籍编号</th>
			<th>书名</th>
			<th>作者</th>
			<th>价格</th>
			<th>出版社</th>
			<th>相关操作</th>
		</tr>
		
		<c:forEach items="${books}" var="book">
		<tr>
			<td>${book.bno }</td>
			<td>${book.bname }</td>
			<td>${book.author }</td>
			<td>${book.price }</td>
			<td>${book.press }</td>
			<td>
				<a href="book/find?id=${book.id }"  class="btn">修改</a>
				<a href="book/del?id=${book.id }"  class="btn">删除</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>