<%-- 
    Document   : homepage
    Created on : Feb 28, 2022, 10:30:23 PM
    Author     : HuyDev
--%>

<%@page import="huylnh.ulti.MyApplicationConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
       
        <h1 style="color: red">
            Welcome, ${sessionScope.LASTNAME}
        </h1>
        <a href="LogoutController">Logout</a>
        <form action="homePageController" method="POST">
            <input type="submit" value="Seach User" name="btAction" />
            <input type="submit" value="Shopping" name="btAction" />
        </form>
    </body>
</html>
