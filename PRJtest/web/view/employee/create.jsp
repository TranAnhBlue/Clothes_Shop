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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/employeecreate.css">
    </head>
    <h2>Create Employee</h2>
    <body>
        <!-- Form để tạo nhân viên mới -->

        <jsp:include page="../master/shortprofile.jsp"></jsp:include>
            <form action="create" method="post">
                <table border="1" cellpadding="5" cellspacing="0" style="text-align: center">
                    Name: <input type="text" name="ename" required/><br/>
                    Gender: 
                    <input type="radio" name="gender" value="male" required/> Male
                    <input type="radio" name="gender" value="female" required/> Female<br/>
                    Dob: <input type="date" name="dob" required/><br/>
                    Address: <input type="text" name="address" required/><br/>
                    Phone Number: <input type="text" name="phonenumber" required/><br/>
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
                </select><br/>
                <input type="submit" value="Create">
            </table>
        </form>
    </body>   
</html>
<a href="../employee/list">
    <button type="button">Back</button>
</a>
