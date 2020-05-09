<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/9
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="account/saveAccount" method="post">
        姓名：<input type="text" name="name"><br/>
        金额：<input type="text" name="money"><br/>
        <input type="submit" value="save">
    </form>
</body>
</html>
