<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/8
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery.min.js"></script>
    <script>
        $(function () {
            $("#btn").click(function () {
                $.ajax({
                    url:"user/TestAjax",
                    type:"post",
                    dataType:"json",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"username":"Rous","password":"123","age":18}',
                    success:function (data) {
                        $("#tx").text(data.username);
                        $("#tx1").text(data.password);
                        $("#tx2").text(data.age);
                    }
                });
            });
        });
    </script>
</head>
<body>
    <button id="btn">发送Ajax请求</button>
    <div id="tx" style="color: red"></div>
    <div id="tx1" style="color: aquamarine"></div>
    <div id="tx2" style="color: rebeccapurple"></div>

</body>
</html>
