<%-- 
    Document   : list
    Created on : Oct 23, 2024, 9:37:33 AM
    Author     : lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function removeShifts(id)
            {
                var result = confirm("Are you sure?");
                if (result)
                {
                    document.getElementById("formRemove" + id).submit();
                }
            }
        </script>
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
        <h1>Shift List</h1>
        <jsp:include page="../master/shortprofile.jsp"></jsp:include>
            <table border="1" cellpadding="5" cellspacing="0" style="text-align: center">
                <tr>          
                    <td>ID</td>
                    <td>Name</td>
                    <td>Start Time</td>
                    <td>End Time</td>
                    <td>Salary Level</td>
                    <td>Salary Amount</td>
                    <td>     </td>
                </tr>
            <c:forEach items="${requestScope.shifts}" var="shift">
                <tr>
                    <td>${shift.id}</td>
                    <td>${shift.name}</td>
                    <td>${shift.start}</td>
                    <td>${shift.end}</td>
                    <td>${shift.salary.level}</td>
                    <td>${shift.salary.salary}</td>
                    <td>
                        <a href="update?id=${shift.id}">Edit</a>
                        <input type="button" value="Remove" onclick="removeShifts(${shift.id})"/>
                        <form id="formRemove${shift.id}" action="delete" method="POST"> 
                            <input type="hidden" name="id" value="${shift.id}"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>

        </table>
        <a href="../home.jsp">
            <button type="button" style="background-color: yellow; color: black">Back to Home</button>
        </a> 
    </body>
</html>

