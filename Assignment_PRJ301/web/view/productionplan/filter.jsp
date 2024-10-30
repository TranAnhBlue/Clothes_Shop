<%-- 
    Document   : filter
    Created on : Oct 23, 2024, 7:04:41 PM
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
        <h2>Filter Production Plan</h2>
        <form action="filter" method="GET">
            <table border="1" cellpadding="5" cellspacing="0" style="text-align: center">
            Id: <input type="text" name="id" value="${param.id ne null ? param.id : p.id}"/> <br/>
            Name: <input type="text" name="name" value="${param.name ne null ? param.plname : p.plname}"/><br/>
            Start date: <input type="date" name="start" value="${param.start}"/> - End date: <input type="date" name="end" value="${param   .end}"/> <br/>
            Department: 
            <select name="did">
                <option value="-1">--------------ALL--------------</option>
                <c:forEach items="${requestScope.depts}" var="d">
                    <option ${param.did ne null and param.did eq d.id ?"selected=\"selected\"":""} 
                        value="${d.id}">${d.name}</option>
                </c:forEach>
            </select> 
            <br/>
            <input type="submit" value="Search"/>
        </form>
        <table border="1" cellpadding="5" cellspacing="0" style="text-align: center">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Department</th>
            </tr>
            <c:forEach items="${requestScope.pps}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.start}</td>
                    <td>${p.end}</td>
                    <td>${p.dept.name}</td>
                </tr>
            </c:forEach>
        </table>
        <a href="../home.jsp">
            <button type="button" style="background-color: yellow; color: black">Back to Home</button>
        </a>
    </body>
</html>
