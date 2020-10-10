<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        table{
            width: 80%;
            margin-left: 10%;
            text-align: center;
            border-collapse: collapse;/*合并边框线*/
            margin-top: 20px;
            font-size: 15px;
        }
        table,td,th{
            border:1px #999 solid;
        }
        a{
            text-decoration: none;
            color: black;
        }
        .btn{
            display: inline-block;
            background-color: red;
            color: white;
            text-align: center;
            width: 100px;
            height: 30px;
            line-height: 30px;
        }
        tr{
            height: 40px;
        }
        td,th{
            width: 20%;
        }
    </style>
</head>
<body>
    <table>
        <tr>
            <th>书名</th>
            <th>作者</th>
            <th>价格</th>
            <th>出版社</th>
            <th></th>
        </tr>
        
        <!-- 遍历显示数据库中的数据信息 -->
        <c:forEach items="${currBooks}" var="book">
	        <tr>
	            <td><a href="">${book.bname }</a></td>
	            <td>${book.author }</td>
	            <td>${book.price }</td>
	            <td>${book.press }</td>
	            <td>
	                <a href="book/addCar?bid=${book.id}" class="btn" onclick="alert('添加成功！')">加入购物车</a>
	            </td>
	        </tr>
        </c:forEach>
        
    </table>
</body>
</html>