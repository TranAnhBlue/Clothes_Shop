    <%-- 
    Document   : create
    Created on : Oct 16, 2024, 5:22:17 PM
    Author     : lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            /* CSS cho bảng */
            table {
                width: 100%;
                border: 2px solid black; /* Đường viền cho bảng */
                border-collapse: collapse; /* Gộp các đường viền vào nhau */
                margin-bottom: 20px; /* Khoảng cách giữa các bảng */
            }

            /* CSS cho ô trong bảng */
            table th, table td {
                border: 1px solid black; /* Đường viền cho từng ô */
                padding: 10px; /* Khoảng cách bên trong các ô */
                text-align: center; /* Căn giữa nội dung */
            }

            /* Định dạng tiêu đề bảng */
            table th {
                background-color: #f2f2f2; /* Màu nền tiêu đề */
                font-weight: bold;
            }

            /* CSS cho phần tiêu đề của bảng */
            .header {
                font-size: 18px;
                font-weight: bold;
                text-align: center; /* Căn giữa nội dung của thẻ <p>.header */
                margin: 10px 0;
            }

            /* CSS cho bảng đầu tiên */
            #personnelTable {
                background-color: #e8f4f8; /* Màu nền nhẹ cho bảng */
            }

            /* CSS cho bảng thứ hai */
            #productionPlanTable {
                background-color: #f8e8f8;
            }

            /* CSS cho bảng tổng số sản phẩm */
            #totalProductsTable {
                background-color: #e8f8e8;
            }

            /* CSS cho viền bao quanh tất cả bảng */
            .container {
                border: 3px solid black; /* Đường viền bao quanh tất cả các bảng */
                padding: 20px; /* Tạo khoảng cách giữa viền và nội dung */
                margin: 20px auto; /* Căn giữa phần bao viền theo chiều ngang */
                max-width: 1200px; /* Giới hạn độ rộng tối đa cho phần bao viền */
            }
        </style>
    </head>
    <body>
        <h2>Create Production Plan</h2>
        <form action="create" method="POST">
            <table border="1" cellpadding="5" cellspacing="0" style="text-align: center">
            Plan Name: <input type="text" name="name"/> <br/>
            From: <input type="date" name="from"/> To: <input type="date" name="to" /> <br/>
            Workshop: <select name="did" >
                <c:forEach items="${requestScope.depts}" var="d">
                    <option value="${d.id}">${d.name}</option>
                </c:forEach>
            </select>
            <br/>
            <table border="1" cellpadding="5" cellspacing="0" style="text-align: center">
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Estimated Effort</th>
                </tr>
                <c:forEach items="${requestScope.products}" var="p">
                    <tr>
                        <td>${p.name}<input type="hidden" name="pid" value="${p.id}"></td>
                        <td><input type="text" name="quantity${p.id}"/></td>
                        <td><input type="text" name="effort${p.id}"/></td>
                    </tr>    
                </c:forEach>
            </table>
            <input type="submit" value="Save"/>
        </form>
        <a href="../home.jsp">
            <button type="button" style="background-color: yellow; color: black">Back to Home</button>
        </a>
    </body>
</html>

