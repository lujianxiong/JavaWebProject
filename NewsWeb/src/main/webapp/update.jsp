<%@page import="com.news.entity.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新闻修改页</title>

<style>
	*{
            margin: 0;
            padding: 0;
        }
        
	#top{
		width:100%;
		height:20%;
	}
	
	.main{
		height:80%;
		width:100%;
		margin-left:3%;
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
	<div id="top">
		<%@include file="top.jsp"%><br />
	</div>
	
	<h3>修改新闻</h3><br />
	<div class="main">
		<form action="news/update" method="post">
			<table>
				<tr>
					<td>新闻标题：</td>
					<td><input type="text" name="title" value="${currNews.title }"/></td>
					<td></td>
				</tr>
				<tr>
					<td>新闻内容：</td>
					<td><textarea name="content" rows="5" >${currNews.content }</textarea>
					<input type="hidden" name="id" value="${currNews.id }" />  </td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="修改" class="btn"/></td>
					<td></td>
				</tr>
			</table>
	
		</form>
	</div>


</body>
</html>