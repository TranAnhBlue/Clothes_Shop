<%-- 
    Document   : list
    Created on : Oct 16, 2024, 6:14:35 PM
    Author     : lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee List</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            // Update Employee Function
            function confirmUpdate(id) {
                if (confirm("Are you sure you want to update this employee?")) {
                    const formData = {
                        id: id,
                        name: $(`#name_${id}`).val(),
                        gender: $(`input[name="gender_${id}"]:checked`).val(),
                        dob: $(`#dob_${id}`).val(),
                        phonenumber: $(`#phonenumber_${id}`).val(),
                        address: $(`#address_${id}`).val(),
                        salary: $(`#sid_${id}`).val(),
                        dept: $(`#did_${id}`).val()
                    };

                    $.post('/employee/update', formData, function (response) {
                        alert("Employee updated successfully!");
                    }).fail(function (error) {
                        alert("Error updating employee: " + error.responseText);
                    });
                }
            }

            function removeEmployees(id) {
                if (confirm("Are you sure you want to remove this employee?")) {
                    $(`#formRemove${id}`).submit();
                }
            }
        </script>
        <style>
            /* General Page Styling */
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f8f8;
                color: #333;
                margin: 20px;
            }

            h1 {
                text-align: center;
                color: #333;
            }

            h3 a {
                color: #4CAF50;
                text-decoration: none;
                font-weight: bold;
                margin: 10px 0;
                display: inline-block;
                text-align: center;
            }

            h3 a:hover {
                text-decoration: underline;
            }

            /* Table Styling */
            table {
                width: 90%;
                margin: 20px auto;
                border-collapse: collapse;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
                text-align: center;
            }

            th, td {
                padding: 12px;
                border: 1px solid #ddd;
            }

            th {
                background-color: #4CAF50;
                color: white;
            }

            /* Alternating Row Colors */
            tbody tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tbody tr:hover {
                background-color: #e9e9e9;
            }

            /* Form Input Styling */
            input[type="text"], input[type="date"], input[type="radio"] {
                padding: 5px;
                font-size: 14px;
                text-align: center;
                margin: 2px 0;
            }

            /* Update and Remove Buttons */
            button[type="button"], input[type="button"] {
                padding: 8px 15px;
                border: none;
                cursor: pointer;
                font-size: 14px;
                margin: 5px;
                border-radius: 5px;
            }

            /* Specific Colors for Buttons */
            button[onclick^="confirmUpdate"] {
                background-color: #28a745;
                color: white;
            }

            input[type="button"][value="Remove"] {
                background-color: #dc3545;
                color: white;
            }

            /* Back to Home Button */
            a button[type="button"] {
                background-color: #ffeb3b;
                color: black;
                font-weight: bold;
                padding: 10px 20px;
                border-radius: 5px;
                font-size: 16px;
            }

            /* Misc */
            a button[type="button"]:hover {
                background-color: #fdd835;
            }
            /* Align gender options horizontally */
            .gender-container {
                display: flex;
                align-items: center;
                gap: 15px; /* Adjusts spacing between Male and Female options */
            }

            .gender-container label {
                margin-right: 10px; /* Adds space between radio button and label */
                cursor: pointer;
            }

            .gender-container input[type="radio"] {
                margin-right: 5px; /* Adds space between radio button and label */
            }
            .employeeForm{
                width: 100%;
                height: 100%;
            }
        </style>
    </head>
    <body>
        <h1>Employee List</h1>

        <jsp:include page="../master/shortprofile.jsp"></jsp:include>
            <h3><a href="create">Create Employee</a></h3>
            <form class="employeeForm" id="employeeForm">
                <table border="1" cellpadding="5" cellspacing="0" style="text-align: center" id="employeeTable">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Gender</th>
                            <th>Date of Birth</th>
                            <th>Phone Number</th>
                            <th>Address</th>
                            <th>Salary</th>
                            <th>Department</th>
                            <th>      </th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="e" items="${emps}">
                    <form id="updateForm${e.id}" action="update" method="POST">
                        <tr>
                            <td>${e.id}</td>
                            <td>
                                <input type="text" name="name_${e.id}" value="${e.name}">
                            </td>
                            <td class="gender-container">
                                <input type="radio" id="male_${e.id}" name="gender_${e.id}" value="male" ${e.gender ? 'checked' : ''}>
                                <label for="male_${e.id}">Male</label>

                                <input type="radio" id="female_${e.id}" name="gender_${e.id}" value="female" ${!e.gender ? 'checked' : ''}>
                                <label for="female_${e.id}">Female</label>
                            </td>
                            <td>
                                <input type="date" name="dob_${e.id}" value="${e.dob}">
                            </td>                            <td>
                                <input type="text" name="phonenumber_${e.id}" value="${e.phonenumber}">
                            </td>
                            <td>
                                <input type="text" name="address_${e.id}" value="${e.address}">
                            </td>
                            <td>
                                <input type="text" id="sid_${e.id}" value="${e.sals.salary}" required/>
                            </td>
                            <td>
                                <input type="text" id="did_${e.id}" value="${e.dept.name}" required/>
                            </td>
                            <td>    
                                <button type="button" onclick="confirmUpdate(${e.id})" style="background-color: green; color: white;">Update</button>
                                <button type="button" onclick="removeEmployees(${e.id})" style="background-color: red; color: white;">Remove</button>
                                <!-- Form for Deletion -->
                                <form id="formRemoveEmployees${e.id}" action="delete" method="POST">
                                    <input type="hidden" name="id" value="${e.id}"/>
                                </form>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
            <a href="../home.jsp">
                <button type="button" style="background-color: yellow; color: black">Back to Home</button>
            </a>    
    </body>
</html>

