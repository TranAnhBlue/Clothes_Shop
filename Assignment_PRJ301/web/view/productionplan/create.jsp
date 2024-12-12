    <%-- 
    Document   : create
    Created on : Oct 16, 2024, 5:22:17 PM
    Author     : lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Production Plan</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/productionplancreate.css">
    </head>
    <body>
        <h2>Create Production Plan</h2>
        <form action="create" method="POST">
            <table border="1" cellpadding="5" cellspacing="0" style="text-align: center">
            Plan Name: <input type="text" name="name"/> <br/>
            From: <input type="date" name="from"/> To: <input type="date" name="to" /> <br/>
            Workshop: <select name="did" >
                <c:forEach items="${requestScope.depts}" var="d">
                    <option value="${d.id}">${d.name}</option>
                </c:forEach>
            </select>
            <br/>
            <table border="1" cellpadding="5" cellspacing="0" style="text-align: center">
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Estimated Effort</th>
                </tr>
                <c:forEach items="${requestScope.products}" var="p">
                    <tr>
                        <td>${p.name}<input type="hidden" name="pid" value="${p.id}"></td>
                        <td><input type="text" name="quantity${p.id}"/></td>
                        <td><input type="text" name="effort${p.id}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" value="Save"/>
        </form>
        <a href="../home.jsp">
            <button type="button" style="background-color: yellow; color: black">Back to Home</button>
        </a>
    </body>
</html>

