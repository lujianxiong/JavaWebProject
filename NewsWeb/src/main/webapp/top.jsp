<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新闻管理系统导航栏</title>
    <style>
    	*{
            margin: 0;
            padding: 0;
        }
        .info{
            font-size: 16px;
            font-weight:bold;
            outline: none;
            width:70px;
            height:20px;
            line-height:20px;
            text-decoration: none;
            color:black;
        }

        .info:hover {
            background-color:#1CBAB4;
            color: white;
        }

    </style>
</head>
<body>
<h3>欢迎光临 新闻管理系统！</h3>
<hr />
<a href="index.jsp" class="info">增加新闻</a>
<a href="news/display" class="info">显示新闻</a>
<a href="search.jsp" class="info">查询新闻</a>
<hr />
</body>
</html>