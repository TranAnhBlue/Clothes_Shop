<%-- 
    Document   : list
    Created on : Dec 10, 2024, 10:16:16 AM
    Author     : lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product List</title>
    </head>
    <body>
        <h1>Product List</h1>
        <form>
            <table border="1">
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${products}" var="pro">
                        <tr>
                            <td>${pro.id}</td>
                            <td>${pro.name}</td>
                            <td>${pro.price}</td>
                            <td>${pro.description}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="../productStore.html">Add product to cart</a>
        </form>
    </body>
</html>
