<%-- 
    Document   : list
    Created on : Oct 27, 2024, 12:47:12 PM
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
        <div class="container">
            <jsp:include page="../master/shortprofile3.jsp"></jsp:include>    
                <table border="1" cellpadding="5" cellspacing="0" style="text-align: center">
                    <p style="font-weight: 700"> List of Personnel Available for Deployment at Workshop No. 01
                    </p>
                    <tr> 
                        <td style="font-weight: 700"> Employee ID </td>
                        <td style="font-weight: 700"> Full Name </td>
                        <td style="font-weight: 700"> Salary Level </td>
                        <td style="font-weight: 700"> Hourly Rate </td>
                    </tr>

                <c:forEach items="${requestScope.eps}" var="ep">
                    <tr>
                        <td style="text-align: center">${ep.eid}</td>
                        <td style="text-align: center">${ep.fullname}</td>
                        <td style="text-align: center">${ep.salary_level}</td>
                        <td style="text-align: center">${ep.hourly_rate}</td>
                    </tr>
                </c:forEach>

            </table>
            <table border="1" cellpadding="5" cellspacing="0" style="text-align: center">
                <p style="font-weight: 700"> Assigned Production Plan for Workshop No. 01 – October 1, 2024 </p>
                <tr>
                    <td style="font-weight: 700"> Date </td>
                    <td style="font-weight: 700"> Product ID </td>
                    <td style="font-weight: 700"> Product Name </td>
                    <td style="font-weight: 700"> Shift </td>
                    <td style="font-weight: 700"> Quantity (unit:piece) </td>
                    <td style="font-weight: 700"> Note </td>
                </tr>

                <c:forEach items="${requestScope.pps}" var="pp">
                    <tr>
                        <td style="text-align: center">${pp.date}</td>
                        <td style="text-align: center">${pp.id}</td>
                        <td style="text-align: center">${pp.name}</td>
                        <td style="text-align: center">${pp.shift}</td>
                        <td style="text-align: center">${pp.quantity}</td>
                        <td style="text-align: center">${pp.note}</td>
                    </tr>

                </c:forEach>

            </table>

            <table border="1" cellpadding="5" cellspacing="0" style="text-align: center"> 
                <p style="font-weight: 700"> Detailed Personnel Deployment Plan for October 1, 2024 </p>
                <tr>
                    <td style="font-weight: 700"> Employee ID </td>
                    <td style="font-weight: 700"> Employee Name </td>
                    <td style="font-weight: 700"> Product ID </td>
                    <td style="font-weight: 700"> Product Name </td>
                    <td style="font-weight: 700"> Shift </td>
                    <td style="font-weight: 700"> Ordered Quantity </td>
                </tr>
                <c:forEach items="${requestScope.dps}" var="dp">
                    <tr>
                        <td style="text-align: center">${dp.eid}</td>
                        <td style="text-align: center">${dp.fullname}</td>
                        <td style="text-align: center">${dp.pid}</td>
                        <td style="text-align: center">${dp.pname}</td>
                        <td style="text-align: center">${dp.shift}</td>
                        <td style="text-align: center">${dp.ordered_quantity}</td>
                    </tr>
                </c:forEach>
            </table>


            <table border="1" cellpadding="5" cellspacing="0" style="text-align: center"> 
                <p style="font-weight: 700"> The total number of products planned to be completed on October 1, 2024, is calculated as
                    follows: </p>
                <tr>
                    <td style="font-weight: 700"> Product ID </td>
                    <td style="font-weight: 700"> Today Production </td>
                    <td style="font-weight: 700"> Cumulative Quantity </td>
                    <td style="font-weight: 700"> Remaining </td>
                </tr>
                <c:forEach items="${requestScope.tpps}" var="t">
                    <tr>
                        <td style="text-align: center">${t.id}</td>
                        <td style="text-align: center">${t.today_production}</td>
                        <td style="text-align: center">${t.cumulative_quantity}</td>
                        <td style="text-align: center">${t.remaining}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
