<%-- 
    Document   : list
    Created on : Oct 22, 2024, 8:36:13 PM
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
            function removeProductionPlans(id)
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
        <h1>Production Plan List</h1>
        <jsp:include page="../master/shortprofile.jsp"></jsp:include>
            <h3><a href="create">Create ProductionPlan</a></h3>
            <table border="1" cellpadding="5" cellspacing="0" style="text-align: center">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Quantity</th>
                    <th>Product</th>
                    <th>Estimation</th>
<!--                    <th>  </th>-->

                </tr>
            <c:forEach items="${requestScope.plans}" var="p">
                <tr class="even">
                    <td rowspan="${p.headers.size()}">${p.id}</td>
                    <td rowspan="${p.headers.size()}"><a href="detail?plid=${p.id}">${p.name}</a></td>
                    <td rowspan="${p.headers.size()}">${p.start}</td>
                    <td rowspan="${p.headers.size()}">${p.end}</td>
                    <td>${p.headers[0].quantity}<br/></td>
                    <td>${p.headers[0].product.name}<br/></td>
                    <td>${p.headers[0].estimatedeffort}<br/></td>
                    <td rowspan="${p.headers.size()}">
<!--                        <a href="update?plid=${p.id}" style="background-color: blue; color: white">Edit</a><br/>
                        <input type="button" value="Remove" onclick="removeProductionPlans(${p.id})" style="background-color: red; color: white"/>
                        <form id="formRemove${p.id}" action="delete" method="POST">
                            <input type="hidden" name="id" value="${p.id}"/>
                        </form>-->
                    </td>
                </tr>
                <c:forEach var="i" begin="1" end="${p.headers.size()-1}">
                    <tr>
                        <td>${p.headers[i].quantity}<br/></td>
                        <td>${p.headers[i].product.name}<br/></td>
                        <td>${p.headers[i].estimatedeffort}<br/></td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </table>
        <a href="../home.jsp">
            <button type="button" style="background-color: yellow; color: black">Back to Home</button>
        </a>
    </body>
</html>