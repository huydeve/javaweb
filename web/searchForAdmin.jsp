<%-- 
    Document   : search
    Created on : Mar 1, 2022, 9:46:55 AM
    Author     : HuyDev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
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
        </form> <br/>

        <c:set var="searchValue" value ="${param.txtSearchValue}" />
        <c:if test="${not empty searchValue}">

            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <h3 style="color: red">${sessionScope.ERROR_PASSWORD}</h3>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Admin</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="updateUser">

                            <tr>
                                <td>
                                    ${counter.count}
                                    .</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}" />
                                </td>
                                <td>
                                   
                                </td>
                                <td>
                                    <input type="text" name="txtLastname" 
                                           value="${dto.lastname}" />
                                </td>
                                <td>

                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked ="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteAccount" 
                                           value="deleteUser">
                                        <c:param name="btAction" 
                                                 value="Delete"/>
                                        <c:param name="pk" 
                                                 value="${dto.username}"/>
                                        <c:param name="lastSearchValue" 
                                                 value="${searchValue}"/>
                                    </c:url>
                                    <a href="${deleteAccount}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" 
                                           name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>

                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if> 

        <c:if test="${empty result}">
            <h2>
                No Found!
            </h2>
        </c:if> 
    </c:if> 

</body>
</html>
