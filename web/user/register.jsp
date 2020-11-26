<%--
  Created by IntelliJ IDEA.
  User: Adminstrator
  Date: 2020/11/24
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>register</title>
    <script type="text/javascript">
        function _change() {
            var ele = document.getElementById("verify");
            ele.src = "<c:url value='/VerifyCodeServlet'/>?xxx="+new Date().getTime();
        }
    </script>
</head>
<body>
    <h1>注册</h1>
    <p style="color: red; font-weight: 900">${msg}</p>
    <form action="<c:url value='/RegisterServlet'/>" method="post">
        用户名：<input type="text" name="username"/>${errors.username}<br>
        密&nbsp;&nbsp;码：<input type="password" name="password"/>${errors.password}<br>
        验证码：<input type="text" name="verifyCode" size="3" value="${user.verifyCode}"/>
        <img src="<c:url value='/VerifyCodeServlet'/>" id="verify" border="2"/><!--IE浏览器 注意id名称不能同名-->
        <a href="javascript:_change()">换一张</a><br>
        <input type="submit" name="sub" value="注册"/>
    </form>
</body>
</html>