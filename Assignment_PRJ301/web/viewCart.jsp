<%-- 
    Document   : viewCart
    Created on : Dec 2, 2024, 9:38:37 AM
    Author     : lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map"%>
<%@page import="controller.session.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Store</title>
        <link rel="stylesheet" href="css/viewCart.css"/>
    </head>
    <body>
        <h1>Your Cart includes:</h1>     
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart.items}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <form action="DispatchController">
                    <c:forEach var="entry" items="${cart.items}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}  
                            </td>
                            <td>
                                ${entry.key} 
                            </td>
                            <td>
                                ${entry.value} 
                            </td>
                            <td>
                                <input type="checkbox" name="chkItem" value="${entry.key}" />                               
                            </td>
                        </tr> 
                    </c:forEach>

                    <tr>
                        <td colspan="3">
                            <a href="productStore.html">Add more Items to your Cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove selected Items" name="btAction" />
                        </td>
                    </tr>
                </form>
            </tbody>
        </table>
    </c:if>         
    <c:if test="${empty cart.items}">
        <h1>No cart exited</h1>
    </c:if>

</body>
    <button onclick="window.location.href='productStore.html'" class="back-productStore">Back</button>
    <button onclick="window.location.href='home.jsp'" class="home-button">Home</button>
</html>
