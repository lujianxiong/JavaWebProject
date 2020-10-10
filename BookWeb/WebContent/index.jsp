<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <style>
        body{
          /*  background-color: green;*/
            height: 650px;
        }
        *{
            margin: 0;
            padding: 0;
        }
        .top{
            width: 90%;
            margin-left: 5%;
            height:20% ;
            /*background-color: orange;*/
        }
        .main{
            width:90%;
            margin-left: 5%;
            height: 80%;

        }
        .personal{
            width: 100%;
            height: 20%;
            /*background-color: white;*/
        }
        .top_main{
            width: 100%;
            height: 80%;
            /*background-color: #999;*/
        }
        .personal ul{
            list-style-type: none;
            float: right;
        }
        .personal li{
            float: left;
            margin-left: 10px;
            font-size: 14px;

        }
        
        .personal .btn{
        	text-decoration:none;
        	color:#000;
        	display:inline-block;
        	width:70px;
        	text-align:center;
        	background-color:#F0F0F0;
        }
        .personal .btn:hover{
        	background-color:red;
        	color:white;
        }
        
        
        
        .logo{
            width: 30%;
            height: 100%;
            /*background-color: deeppink;*/
            float: left;
        }
        .logo img{
            width: 100%;
            height: 100%;
        }
        .search{
            width: 70%;
            height: 100%;
            margin-left: 30%;
            /*background-color: greenyellow;*/
        }
        .search input{
            margin-top: 5%;
            border:1px red solid;

        }
        .search .txt{
            width: 300px;
            height: 30px;
        }
        .search .btn{
            width: 70px;
            height: 32px;
            background-color: red;
            color: white;
            position: relative;
            left: -4px;
        }
        .left{
            width: 20%;
            height: 100%;

            float: left;
        }
        .right{
            width: 80%;
            height: 100%;

            margin-left: 20%;
        }
        .left ul{

            list-style-type: none;
            margin-top: 20px;

        }
        .left li{

            text-align: center;
            line-height: 30px;
            margin-top:3px;

        }
        .left li a{
            display: inline-block;
            text-decoration: none;
            color: black;
			background-color: #F0F0F0;
            width: 100%;
        }
        .left li a:hover{
            background-color: red;
            color: white;
        }
        .right iframe{
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
    <div class="top">
        <div class="personal">
            <ul>
            	<!-- 获取session域中的当前的用户 -->
                <li><span>${currUser.username }</span></li>
                <li><a href="car.jsp" target="main" class="btn">购物车</a></li>
                <li><a href="order/selectByUser" target="main" class="btn">历史订单</a></li>
                <li><a href="user/logout" class="btn">LOGOUT</a></li>
            </ul>
        </div>
        <div class="top_main">
            <div class="logo">
                <img src="images/ddlogo.gif" alt="">
            </div>
            <div class="search">
                <form action="book/search" method="post" target="main">
                    <input type="text" name="bname" placeholder="请输入书名" class="txt">
                    <input type="submit" value="搜索"  class="btn">
                </form>
            </div>
        </div>

    </div>
    <div class="main">
            <div class="left">
                <ul>
                	<c:forEach items="${booktypes}" var="type">
                        <li><a href="book/findByType?typeid=${type.id }" target="main">${type.booktype}</a></li>
                	</c:forEach>
                </ul>
            </div>
            <div class="right">
                <iframe name="main"  src="books.jsp" scrolling="no" frameborder="no"></iframe>
            </div>
    </div>

</body>
</html>