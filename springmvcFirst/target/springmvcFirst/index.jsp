<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/6
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>入门程序</h3>
   <form action="RequestBody" method="post">
      姓名：<input type="text" name="uname"/><br>
      年龄：<input type="text" name="uage" /><br>
      生日：<input type="text" name="udate" /><br>
           <input type="submit" value="save">
   </form><br>
<%--
    RequestParam
    RequestBody
    CookieValue
    ModelAttribute
    SessionAttributes
--%>
    <a href="RequestParam?username=zhangsan&password=123">RequestParam注解</a><br/>
    <a href="CookieValue">CookieValue注解</a><br>
    <a href="ModelAttribute">ModelAttribute注解</a><br>
    <a href="saveSessionAttributes">SessionAttributes注解--存入session值</a><br/>
    <a href="findSessionAttributes">SessionAttributes注解--获取session值</a><br/>
    <a href="delete">SessionAttributes注解--删除session值</a>
</body>
</html>
