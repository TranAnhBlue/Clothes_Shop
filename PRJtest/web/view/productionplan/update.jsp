<%-- 
    Document   : update
    Created on : Oct 23, 2024, 11:37:59 AM
    Author     : lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Production Plan</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/productionplanupdate.css">

    </head>
    <body>
        <jsp:include page="../master/shortprofile.jsp"></jsp:include>
            <h2>Update Production Plan</h2>
            <form action="update" method="POST">
                ID: ${requestScope.plan.id} <br/>
            <input type="hidden" name="plid" value="${requestScope.plan.id}"/>

            Name: <input type="text" name="plname" value="${requestScope.plan.name}" required/> <br/>

            Start Date: <input type="date" name="startdate" value="${requestScope.plan.start}" required/> <br/>

            End Date: <input type="date" name="enddate" value="${requestScope.plan.end}" required/> <br/>

            Department: <select name="did" required\>
                <c:forEach items="${requestScope.depts}" var="d">
                    <option 
                        ${requestScope.plan.dept.id eq d.id?"selected=\"selected\"":""}
                        value="${d.id}">${d.name}</option>
                </c:forEach>
            </select> <br/>

            <input type="submit" value="Save"/>
        </form>
        <a href="list">
            <button type="button">Back</button>
        </a> 
    </body>
</html>
