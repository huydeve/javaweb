<%-- 
    Document   : viewCart
    Created on : Mar 1, 2022, 6:29:46 PM
    Author     : HuyDev
--%>
<%@page import="java.util.Map"%>
<%@page import="huylnh.cart.CartObject"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <h1 style="color: red">
            Welcome, ${sessionScope.LASTNAME}
        </h1>
        <a href="homePage">Home</a>
        <a href="homePageController?btAction=Shopping">Shopping</a>
        <!-- 1.cust goes to cart place-->
        <c:if test="${not empty sessionScope}">
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${not empty cart}">
                <c:set var="items" value="${cart.getItems()}"/>

                <c:if test="${not empty items}">
                    <c:set var="dao" value="${requestScope.DAO}"/>
                    
                    <h2>Your Cart</h2>

                    <form action="DispatchRemoveOrCheckOutController">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Product ID</th>
                                    <th>Product</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="id" items="${items.keySet()}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>
                                            ${id}
                                        </td>
                                        <td>
                                            ${dao.getProductById(id).getNameproduct()}
                                        </td>
                                        <td>
                                            ${items.get(id)}
                                        </td>
                                        <td>
                                            ${dao.getProductById(id).getPrice()*items.get(id)}
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkItems" value="${id}" />
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="3">
                                        <a href="homePageController?btAction=Shopping">Add more Items to Cart</a>
                                        <!-- Qua trang jsp phai goi cach khac> -->
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove Selected Items" 
                                               name="btAction" />
                                    </td>
                                    <td>
                                        <input type="submit" value="Check out" name="btAction" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>

                </c:if>


            </c:if>

        </c:if>

        <c:if test="${empty cart or empty items}">
            <h2>Cart is empty</h2> 
        </c:if>
        <h3 style="color: red">${sessionScope.ERROR_VIEW}</h3>
    </body>
</html>
