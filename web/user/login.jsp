<%--
  Created by IntelliJ IDEA.
  User: Adminstrator
  Date: 2020/11/24
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<h1>登录</h1>
<p style="color: red; font-weight: 900">${msg}</p>
<form action="<c:url value='/loginServlet'/>" method="post">
    用户名：<input type="text" name="username"/><br>
    密&nbsp;&nbsp;码：<input type="password" name="password"/><br>
    <input type="submit" name="sub" value="登录"/>
</form>
</body>
</html>
