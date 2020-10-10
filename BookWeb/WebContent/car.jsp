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
            border: 1px red solid;
            outline: none;
        }
        tr{
            height: 40px;
        }
        td,th{
            width: 33.3%;
        }
    </style>
<!--    使用jquery需要引入jquery环境：CDN-->
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<!--    javascript代码-->
    <script>
        // jquery
        $(function () {//这是jquery的入门函数
            //总价变化
            //1.绑定事件（单击事件）
            //筛选元素
            //$("input")  所有的input标签
            //$("input[name='bid']") input标签中  name属性值是bid的元素
            //$("input[name='bid']").click(function(){})  为选中的元素绑定click事件，单击是执行function的代码
            //复选框单击时可能是选中也可能是取消
            $("input[name='bid']").click(function(){
                //单击时执行
                //$(this)  当前单击的这个对象（复选框）
                //$(this).prop("checked")  当前单击后，复选框的checked属性值，如果是选中，值为true，否则为false
                if($(this).prop("checked")){
                    //选中
                    //获取这个复选框对应的的书籍的单价
                    var price=$(this).parent().next().next().text();//得到价格的文本信息  text()返回的是字符串
                    var currTotal=$("#totalPrice").text();//$("#totalPrice")  找到id属性值为totalPrice的jquery对象
                    if(currTotal==''||currTotal==null){
                        currTotal=0;
                    }
                    //重新计算当前已选书籍的总价
                    // Number.parseFloat() 将字符串转为float，再进行计算
                    currTotal=Number.parseFloat(currTotal)+Number.parseFloat(price);
                    //更新总价
                    $("#totalPrice").text(currTotal);
                }else{
                    //取消
                    var price=$(this).parent().next().next().text();
                    var currTotal=$("#totalPrice").text();
                    if(currTotal==''||currTotal==null){
                        currTotal=0;
                    }
                    currTotal=Number.parseFloat(currTotal)-Number.parseFloat(price);
                    $("#totalPrice").text(currTotal);
                }

            });
            //2.全选
            $("#chkall").click(function(){
                //如果 全选，书籍的全部复选框选中
                //如果 全选取消，书籍的全部复选框取消
                $("input[name='bid']").prop("checked",$(this).prop("checked"));
                //
                if($(this).prop("checked")){//全选时，更新总价
                    var num=0;
                    $(".singlePrice").each(function(){//遍历每一个单价
                        num+=Number.parseFloat($(this).text());//num+=...  ==> num=num+...

                    });
                    $("#totalPrice").text(num);

                }else{
                    $("#totalPrice").text(0);
                }

            });
            //书籍复选框与全选框状态一致
            $("input[name='bid']").click(function () {
                //$("input[name='bid']:checked")：
                // 所有书籍前的复选框的选中的个数等于
                //所有书籍复选框的元素的个数
                if( $("input[name='bid']:checked").length== $("input[name='bid']").length){
                    //是全选
                    $("#chkall").prop("checked",true);
                }else{
                    $("#chkall").prop("checked",false);
                }

            });

        })
        //表单验证
        // onsubmit：表单提交时触发
        function checkForm() {
            var price=$("#totalPrice").text();
            if(price==0){
                //没有选择任何书籍时  表单不提交
                alert("请选择书籍");
                return  false;
            }else{
                //有选择书籍时 表达提交
                //val()  :表单元素的值（比如：输入的用户名、密码  复选  单选。。。。）
                //text():标签的文本内容   <p>....</p>
                $("#hiddenTotal").val(price);
                alert("下单成功");
                return true;
            }
        }
    </script>
</head>
<body>
<form action="book/pay" method="post" onsubmit="return checkForm()">
<table>
    <tr>
        <th>
            <input type="checkbox" id="chkall">全选
        </th>
        <th>书名</th>
        <th>价格</th>
    </tr>
    
    <!-- 遍历购物车 -->
    <c:forEach items="${car }" var="book">
	    <tr>
	        <td>
	            <input type="checkbox" name="bid" value=${book.id }>
	        </td>
	        <td>${book.bname }</td>
	        <td class="singlePrice">${book.price }</td>
	    </tr>
    </c:forEach>
    
    <tr>
        <td>总价：<span id="totalPrice">0</span></td>
        <!-- colspan="2" 单元格跨列-->
        <td colspan="2">
            <input type="hidden" id="hiddenTotal" name="totalPrice"/>
            <input  type="submit" class="btn" value="结算"/>
        </td>
    </tr>

</table>
</form>
</body>
</html>