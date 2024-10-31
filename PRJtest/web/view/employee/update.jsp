<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Employee</title>       
    </head>
    <body>    
        <jsp:include page="../master/shortprofile.jsp"></jsp:include>
            <h2>Update Employee</h2>
            
            <form action="update" method="POST">
                Id: ${requestScope.e.id} <br/>
            <input type="hidden" name="id" value="${requestScope.e.id}"/>

            Name: <input type="text" name="name" value="${requestScope.e.name}" required> <br/>

            Gender: 
            <input type="radio" name="gender" value="male" ${requestScope.e.gender ? "checked" : ""} required> Male
            <input type="radio" name="gender" value="female" ${!requestScope.e.gender ? "checked" : ""} required> Female <br/>

            Dob: <input type="date" name="dob" value="${requestScope.e.dob}" required> <br/>
            Address: <input type="text" name="address" value="${requestScope.e.address}" required> <br/>
            Phonenumber: <input type="text" name="phonenumber" value="${requestScope.e.phonenumber}" required><br/>

            Salary: 
            <select name="sid" required>
                <c:forEach items="${requestScope.sals}" var="s">
                    <option value="${s.id}" ${requestScope.e.sals.id eq s.id ? "selected" : ""}>${s.salary}</option>
                </c:forEach>
            </select><br/>

            Department: <select name="did" required>
                <c:forEach items="${requestScope.depts}" var="d">
                    <option value="${d.id}" ${requestScope.e.dept.id eq d.id ? "selected" : ""}>${d.name}</option>
                </c:forEach>
            </select> <br/>

            <input type="submit" value="Save"/>
        </form>
        <a href="list">
            <button type="button">Back</button>
        </a> 
    </body>
</html>