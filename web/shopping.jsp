<%-- 
    Document   : shopping
    Created on : Mar 1, 2022, 1:11:37 PM
    Author     : HuyDev
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <body>
        <h1 style="color: red">
            Welcome, ${sessionScope.LASTNAME}
        </h1>
        <a href="LogoutController">Logout</a>
        <a href="homePage">Home</a>
        <h1>Shopping</h1>
        <c:set var="result" value="${requestScope.PRODUCT}"/>
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Product</th>
                        <th>Price</th>
                        <th>Available</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}">

                    <form action="addToCart">
                        <tr>
                            <td>
                                ${dto.productid}
                                <input type="hidden" name="productid" 
                                       value="${dto.productid}">
                            </td>
                            <td>
                                ${dto.nameproduct}
                            </td>
                            <td>
                                ${dto.price}
                            </td>
                            <td>
                                ${dto.available}
                            </td>
                            <td>
                                <input type="submit" value="Add to Cart" 
                                       name="btAction" />
                            </td>
                        </tr>
                    </form>

                </c:forEach>

            </tbody>
        </table>

    </c:if>
    <c:if test="${empty result}">
        <h2 style="color: red">Product Not Found</h2>
    </c:if>
    <br><!-- comment -->
    <a href="viewCart">View Your Card</a>
</body>
</html>
