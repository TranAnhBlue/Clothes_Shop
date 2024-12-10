<%-- 
    Document   : filter
    Created on : Oct 16, 2024, 6:14:57 PM
    Author     : lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Filter Employee</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/employeefilter.css">
    </head>
    <body>
        <!-- Form để lọc thông tin nhân viên -->
        <h2>Filter Employee</h2>
        <form action="filter" method="GET"> 
            <table border="1" cellpadding="5" cellspacing="0" style="text-align: center">
                ID: <input type="text" name="eid" value="${param.eid ne null ? param.eid : e.eid}"/> <br/>
                Name: <input type="text" name="ename" value="${param.ename ne null ? param.ename : e.ename}"/> <br/>
                Gender: <input type="radio" ${param.gender ne null and param.gender eq "male"?"checked=\"checked\"":""} 
                               name="gender" value="male"/> Male
                <input type="radio" ${param.gender ne null and param.gender eq "female"?"checked=\"checked\"":""}
                       name="gender" value="female"/> Female
                <input type="radio" ${param.gender eq null or param.gender eq "both"?"checked=\"checked\"":""} name="gender" value="both"/> Both
                <br/>
                Dob - From : <input type="date" name="from" value="${param.from}"/> - To: <input type="date" name="to" value="${param.to}"/> <br/>
                Address: <input type="text" name="address" value="${param.address ne null ? param.address : e.address}"/> <br/>
                Salary: 
                <select name="sid">
                    <option value="-1">--------------ALL--------------</option>
                    <c:forEach items="${requestScope.sals}" var="s">
                        <option ${param.sid ne null and param.sid eq s.id ?"selected=\"selected\"":""}
                            value="${s.id}">${s.salary}</option>
                    </c:forEach>
                </select><br/>
                Department: 
                <select name="did">
                    <option value="-1">--------------ALL--------------</option>
                    <c:forEach items="${requestScope.depts}" var="d">
                        <option ${param.did ne null and param.did eq d.id ?"selected=\"selected\"":""} 
                            value="${d.id}">${d.name}</option>
                    </c:forEach>
                </select> 
                <br/>
                Phone Number: <input type="text" name="phonenumber" value="${param.phonenumber}"/> <br/>
                <input type="submit" value="Search"/>
            </table>
        </form>

        <!-- Bảng hiển thị kết quả tìm kiếm -->
        <table border="1px">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Gender</th>
                <th>Dob</th>
                <th>Address</th>
                <th>Phone Number</th>
                <th>Department</th>
                <th>Salary</th>
            </tr>
            <c:forEach items="${requestScope.emps}" var="e">
                <tr>
                    <td>${e.id}</td>
                    <td>${e.name}</td>
                    <td>${e.gender?"Male":"Female"}</td>
                    <td>${e.dob}</td>
                    <td>${e.address}</td>
                    <td>${e.phonenumber}</td>
                    <td>${e.dept.name}</td>
                    <td>${e.sals.salary}</td>
                </tr>
            </c:forEach>
        </table>
        <a href="../home.jsp">
            <button type="button" style="background-color: yellow; color: black">Back to Home</button>
        </a>
    </body>

</html>

