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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/shiftlist.css">

    </head>
    <body>
        <h1>Shift List</h1>
        <jsp:include page="../master/shortprofile.jsp"></jsp:include>
            <table border="1" cellpadding="5" cellspacing="0" style="text-align: center">
                <tr>          
                    <th>ID</th>
                    <th>Name</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Salary Level</th>
                    <th>Salary Amount</th>
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

