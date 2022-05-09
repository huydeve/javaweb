<%-- 
    Document   : loginError
    Created on : Mar 12, 2022, 10:39:52 AM
    Author     : HuyDev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        
    </head>
    <body>
        <h1>LOGIN</h1>
        <p style="color: red">${requestScope.ERROR}</p>
        <form action="loginController" method="POST">
            <input type="text" placeholder="Username" name="txtUsername" value="" /><br>
            <input type="password" placeholder="Password" name="txtPassword" value="" /><br>
            <br>
            <span>Forgot password?</span>
            <a href="createAccountPage">Registration </a>
            <br>
            <input type="reset" value="Reset" /><br>
            <input type="submit" value="Login" name="btAction" />
        </form>

    </body>
</html>
