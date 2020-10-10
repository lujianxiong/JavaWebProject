<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新闻显示页</title>

    <style>
    
        *{
            margin: 0;
            padding: 0;
        }
       
        #top{
			width:100%;
			height:20%;
		}
        .main {
        	height:80%;
            width: 96%;
            margin-left: 2%;

        }
        .main table{
            width: 96%;
            margin-left: 2%;
            border:1px #1CBAB4 solid;
            border-collapse: collapse;
            text-align: center;
            margin-top: 30px;
            margin-botton:30px;
        }
        .main td,th{
            border:1px #1CBAB4 solid;
        }
        
        .main .btn{
            display:inline-block;
            background-color: #F0F0F0;
            color: #000;
            font-size: 12px;
            font-weight:bold;
            outline: none;
            width: 70px;
            height: 30px;
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
<div id="top">
	<%@include file="top.jsp"%><br />
</div>


<div class="main">
    <table>
        <tr>
            <th><span style="font-size:20px">编号</span></th>
            <th><span style="font-size:20px">新闻标题</span></th>
            <th><span style="font-size:20px">新闻内容</span></th>
            <th><span style="font-size:20px">修改</span></th>
            <th><span style="font-size:20px">删除</span></th>
        </tr>
        <c:forEach items="${news}" var="news">
            <tr>
                <td>${news.id }</td>
                <td>${news.title }</td>
                <td>${news.content }</td>
                <td><a href= "news/find?id=${news.id}">修改</a></td>
                <td><a href= "news/delete?id=${news. id}"onclick="if(!confirm('确认删除?'))return false;">删除</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>