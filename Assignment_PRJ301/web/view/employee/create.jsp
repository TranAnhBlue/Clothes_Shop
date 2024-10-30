<%-- 
    Document   : create
    Created on : Oct 16, 2024, 6:14:52 PM
    Author     : lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Employee</title>
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
        <!-- Form để tạo nhân viên mới -->
        <h2>Create Employee</h2>
        <jsp:include page="../master/shortprofile.jsp"></jsp:include>
        <form action="create" method="post">
            <table border="1" cellpadding="5" cellspacing="0" style="text-align: center">
            Name: <input type="text" name="ename" required/><br>
            Gender: 
            <input type="radio" name="gender" value="male" required/> Male
            <input type="radio" name="gender" value="female" required/> Female<br>
            Dob: <input type="date" name="dob" required/><br>
            Address: <input type="text" name="address" required/><br>
            Phone Number: <input type="text" name="phonenumber" required/><br>
            Salary: 
            <select name="sid" required>
            <c:forEach items="${requestScope.sals}" var="s">
                <option value="${s.id}">${s.salary}</option>
            </c:forEach>
            </select><br/>
             Department: 
            <select name="did" required> <!-- Added required attribute -->
                <c:forEach var="d" items="${requestScope.depts}">
                    <option value="${d.id}">${d.name}</option>
                </c:forEach>
            </select><br>
            <input type="submit" value="Create">
            </table>
        </form>
        <a href="../employee/list">
            <button type="button">Back</button>
        </a>
    </body>
</html>
