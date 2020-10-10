<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新闻管理系统主页</title>

<style>
	*{
            margin: 0;
            padding: 0;
        }
		
	.main{
		text-align:center;
	}	
	
	.main table{
		width:300px;
		margin-left:5%;
		margin-top:10px;
	}
	
	.btn{
            display:inline-block;
            background-color: #D1E0EF;
            color: #000;
            font-size: 10px;
            font-weight:bold;
            outline: none;
            width: 50px;
            height: 20px;
            line-height:20px;
            text-decoration: none;
        }
        .btn:hover {
            background-color:#1CBAB4;
            color: white;
        }

</style>

</head>
<body>
	<div class="top">
		<%@include file="top.jsp"%><br />
	</div>
	
	<h3>增加新闻</h3><br />
	<div class="main">
		<form action="news/add" method="post">
			<table>
				<tr>
					<td>新闻标题：</td>
					<td><input id="a" type="text" name="title" /></td>
					<td></td>
				</tr>
				<tr>
					<td>新闻内容：</td>
					<td><textarea id="a" name="content" rows="5"></textarea></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="添加" class="btn"/></td>
					<td></td>
				</tr>
			</table>
	
		</form>
	</div>


</body>
</html>