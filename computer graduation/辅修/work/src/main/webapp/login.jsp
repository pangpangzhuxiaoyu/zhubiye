<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="http://localhost:8080/work/css/login.css" rel="stylesheet">
</head>

<body>
<div id="loginDiv" style="height: 350px">
    <form action="/work/user/login" id="form">
        <h1 id="loginMsg">请登录</h1>
        <div id="errorMsg">${login_msg} ${register_msg}</div>
        <p>用户名：<input id="username" name="username" type="text"></p>

        <p>密&nbsp;&nbsp;&nbsp;&nbsp;码：<input id="password" name="password" type="password"></p>
        <div id="subDiv">
            <input type="submit" class="button" value="登入">
            <input type="reset" class="button" value="重置">&nbsp;&nbsp;&nbsp;
            <a href="http://localhost:8080/work/register.jsp">没有账号？</a>
        </div>
    </form>
</div>

</body>
</html>