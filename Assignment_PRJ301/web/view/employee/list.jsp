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
        <title>List Employee</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            $(document).ready(function () {
                $('.editable').on('blur', function () {
                    var field = $(this).data('field');
                    var id = $(this).data('id');
                    var value = $(this).text();

                    $.ajax({
                        url: '/employee/update',
                        type: 'POST',
                        data: {
                            id: id,
                            field: field,
                            value: value
                        },
                        success: function (response) {
                            console.log(response); // Cập nhật thành công
                        },
                        error: function (xhr) {
                            alert('Error: ' + xhr.responseText); // Lỗi cập nhật
                        }
                    });
                });
            });
            function removeEmployees(id)
            {
                var result = confirm("Are you sure?");
                if (result)
                {
                    document.getElementById("formRemove" + id).submit();
                }
            }

            function confirmUpdate(id) {
                var result = confirm("Are you sure you want to update this employee?");
                if (result) {
                    document.getElementById("updateForm" + id).submit();
                }
            }

            function updateEmployee(id, field, value) {
                $.ajax({
                    url: '/employee/update', // Đảm bảo URL này trỏ đến đúng servlet
                    type: 'POST',
                    data: {
                        id: id,
                        field: field,
                        value: value
                    },
                    success: function (response) {
                        alert("Employee updated successfully!");
                    },
                    error: function (error) {
                        alert("Error updating employee");
                        console.log(error);
                    }
                });
            }

            // Ví dụ cách gọi hàm updateEmployee từ một ô nhập liệu
            $('#name_' + id).on('change', function () {
                var name = $(this).val();
                updateEmployee(id, 'name', name);
            });

            function saveEmployee(id) {
                // Thu thập dữ liệu từ dòng tương ứng
                var name = document.getElementById("name_" + id).value;
                var gender = document.querySelector('input[name="gender_' + id + '"]:checked').value;
                var dob = document.getElementById("dob_" + id).value;
                var phonenumber = document.getElementById("phonenumber_" + id).value;
                var address = document.getElementById("address_" + id).value;
                var sid = document.getElementById("sid_" + id).value;
                var did = document.getElementById("did_" + id).value;

                // Gửi dữ liệu bằng AJAX
                $.ajax({
                    url: '/employee/update', // URL của servlet xử lý
                    type: 'POST',
                    data: {
                        id: id,
                        name: name,
                        gender: gender,
                        dob: dob,
                        phonenumber: phonenumber,
                        address: address,
                        sid: sid,
                        did: did
                    },
                    success: function (response) {
                        alert("Employee updated successfully!");
                    },
                    error: function (error) {
                        alert("Error updating employee");
                        console.log(error);
                    }
                });
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
        <h1>Employee List</h1>

        <jsp:include page="../master/shortprofile.jsp"></jsp:include>
            <h3><a href="create">Create Employee</a></h3>
            <form id="employeeForm">
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
                            <td>
                                <input type="radio" name="gender_${e.id}" value="male" ${e.gender ? 'checked' : ''}> Male
                                <input type="radio" name="gender_${e.id}" value="female" ${!e.gender ? 'checked' : ''}> Female
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
                                <input type="button" value="Remove" onclick="removeEmployees(${e.id})" style="background-color: red; color: white"/>
                                <form id="formRemove${e.id}" action="delete" method="POST"> 
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

