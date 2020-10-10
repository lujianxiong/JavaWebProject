<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	*{
		margin:0;
		padding: 0;
	}
	
	div{
		width:50%;
		margin-left:25%;
		height:350px;
		margin-top:30px;
		padding-top:30px;
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
	    <form action="book/update" method="post">
	    	<!-- id和bno是无法修改的，所以把隐藏起来 -->
	    	<input type="hidden" placeholder="书籍编号" name="bno" value="${currBook.bno }"><br/>
	    	<input type="hidden" placeholder="书籍编号" name="id" value="${currBook.id }"><br/>
	    	
			<input type="text" placeholder="书名" name="bname" value="${currBook.bname }"><br/>
			<input type="text" placeholder="作者" name="author" value="${currBook.author }"><br/>
			<input type="text" placeholder="价格" name="price" value="${currBook.price }"><br/>
			<input type="text" placeholder="出版社" name="press" value="${currBook.press }"><br/>
			<input type="submit" value="修改" class="btn"><br/>
			<input type="reset" value="清空" class="btn">
	    </form>
	
		
	</div>

</body>
</html>