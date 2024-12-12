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
        <title>List Detail</title>
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
            /* General Styles */
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 20px;
                color: #333;
            }

            h1, h3 {
                text-align: center;
                color: #333;
            }

            h3 a {
                color: #4CAF50;
                text-decoration: none;
            }

            h3 a:hover {
                text-decoration: underline;
            }

            /* Table Styles */
            table {
                width: 90%;
                margin: 20px auto;
                border-collapse: collapse;
                box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);
            }

            th, td {
                padding: 12px;
                text-align: center;
                border: 1px solid #ddd;
            }

            th {
                background-color: #4CAF50;
                color: white;
            }

            /* Alternating Row Colors */
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            /* Hover Effect */
            tr:hover {
                background-color: #e9e9e9;
            }

            /* Button Styles */
            a button, input[type="button"] {
                padding: 10px 20px;
                margin: 5px;
                cursor: pointer;
                border: none;
                font-size: 16px;
            }

            /* Edit and Remove Buttons */
            a[style*="background-color: blue"] {
                background-color: #2196F3;
                color: white;
                padding: 5px 10px;
                border-radius: 5px;
                text-decoration: none;
            }

            input[type="button"] {
                background-color: #f44336;
                color: white;
                border-radius: 5px;
            }

            /* Back to Home Button */
            a button[type="button"] {
                background-color: #FFEB3B;
                color: black;
                font-weight: bold;
                border-radius: 5px;
            }
        </style>
    </head>
    <body>
        <h1>Production Plan List</h1>
        <jsp:include page="../master/shortprofile.jsp"></jsp:include>
            <h3><a href="create">Create Production Plan</a></h3>
            <table border="1" cellpadding="5" cellspacing="0" style="text-align: center">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Quantity</th>
                    <th>Product</th>
                    <th>Estimation</th>
                    <th>Action</th>

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
                        <a href="update?plid=${p.id}" style="background-color: blue; color: white">Edit</a><br/>
                        <input type="button" value="Remove" onclick="removeProductionPlans(${p.id})" style="background-color: red; color: white"/>
                        <form id="formRemove${p.id}" action="delete" method="POST">
                            <input type="hidden" name="id" value="${p.id}"/>
                        </form>
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