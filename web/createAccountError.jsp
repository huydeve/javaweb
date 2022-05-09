<%-- 
    Document   : createAccountError
    Created on : Mar 2, 2022, 9:43:39 AM
    Author     : HuyDev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <div>Create New Account</div>
        <form action="CreateAccountServlet" method="POST">
            <c:set var = "errors" value="${requestScope.INSERT_ERROR}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(6-20)<br><!-- comment -->
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color ="red"><!-- comment -->
                ${errors.usernameLengthErr}
                </font><br/>  
            </c:if>
            <c:if test="${not empty errors.usernameIsExited}">
                <font color ="red"><!-- comment -->
                ${errors.usernameIsExited}
                </font><br/>  
            </c:if>
       
            Password* <input type="password" name="txtPassword" value="" />(6-30)<br><!-- comment -->
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color ="red"><!-- comment -->
                ${errors.passwordLengthErr}
                </font><br/>  
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /><br><!-- comment -->
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color ="red"><!-- comment -->
                ${errors.confirmNotMatched}
                </font><br/>  
            </c:if>
            Full name* <input type="text" name="txtFullname" value="${param.txtFullname}" />(2-50)<br><!-- comment -->
            <c:if test="${not empty errors.fullNameLengthErr}">
                <font color ="red"><!-- comment -->
                ${errors.fullNameLengthErr}
                </font><br/>  
            </c:if>
            <input type="submit" value="Create Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
