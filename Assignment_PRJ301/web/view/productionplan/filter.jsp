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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/productionplanfilter.css">
        
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
