<%-- 
    Document   : searchWelcome
    Created on : Feb 21, 2022, 12:06:28 AM
    Author     : HuyDev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>

        <h1 style="color: red">
            Welcome, ${sessionScope.LASTNAME}
        </h1>
        <a href="LogoutController">Logout</a>
        <a href="homePage">Home</a>
        <h1>SEARCH USER BY LAST NAME</h1>
        <form action="searchLastName">
            Search <input type="text" name="txtSearchValue" 
                          value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" />
        </form>

    </body>
</html>
