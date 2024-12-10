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
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
            }
            h1 {
                text-align: center;
                color: #333;
            }
            table {
                width: 80%;
                margin: 20px auto;
                border-collapse: collapse;
                background-color: #ffffff;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }
            th, td {
                padding: 10px;
                text-align: left;
                border: 1px solid #ddd;
            }
            th {
                background-color: #218838;
                color: white;
                font-weight: bold;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
            a {
                display: block;
                width: 200px;
                margin: 20px auto;
                text-align: center;
                padding: 10px;
                background-color: #28a745;
                color: white;
                text-decoration: none;
                border-radius: 4px;
                font-size: 16px;
            }
            a:hover {
                background-color: #218838;
            }
        </style>
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
