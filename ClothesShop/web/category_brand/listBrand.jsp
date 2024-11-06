<%-- 
    Document   : listBrand
    Created on : Jun 22, 2024, 10:19:05 PM
    Author     : chien
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Clothes Shop Manager</title>

        <!-- Custom fonts for this template -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="adminassets/css/sb-admin-2.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
        <!-- Custom styles for this page -->
        <link href="adminassets/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/fontawesome.all.min.css">


    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
              <%@include file="../homepage/sidebar_admin.jsp" %>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">
                <!-- Main Content -->
                <div id="content">

                  <!-- Topbar -->
                    <%@include file="../homepage/header_admin.jsp" %>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">
                        <!-- Page Heading -->
                        <h1 >Brand List</h1>
                        <div id="newCateForm" style="display: none;">
                            <form action="listBrand" method="get" style="margin-bottom: 20px">

                                <fieldset class="form-group">

                                    <input type="text" value="" class="form-control col-md-4" name="addBrand_name" placeholder="Enter Brand Name" id="name" required>
                                </fieldset>

                                <div class="text-left" style="margin-left: 12px">
                                    <button type="submit" onclick="myFunction()" class="btn btn-success">Add</button>
                                    <button type="button" onclick="cancelAdd()" class="btn btn-secondary ml-2">Cancel</button>
                                </div>

                            </form>
                        </div>

                        <button type="button" id="newCateButton" style="margin-bottom: 20px" class="btn btn-primary" onclick="showForm()">Add Brand</button>




                        <!-- Search and Filter Form -->
                        <form id="filterForm" name="filterForm" action="listBrand" method="get" onsubmit="return validateForm()">
                            <div class="form-row">
                                <!-- Search properties -->
                                <%
                               String searchBrand = request.getParameter("searchBrand");
                                %>

                                <div class="col-md-2 mb-3">
                                    <input type="text" name="searchBrand" placeholder="Search Brand" value="<%= searchBrand != null ? searchBrand : "" %>" class="form-control">
                                </div>


                                <!-- Genders Dropdown -->
                                <div class="col-md-2 mb-3">
                                    <select class="form-select" name="sortBrand" aria-label="Default select example">

                                        <option value="1" ${requestScope.sortBrand == 1 ? 'selected' : ''}>Sort by Bid</option>
                                        <option value="2"  ${requestScope.sortBrand == 2 ? 'selected' : ''}>Sort by Name</option>

                                    </select>
                                </div>
                                <div class="col-md-2 mb-3">
                                    <select class="form-select" name="sortTypeB" aria-label="Default select example">

                                        <option value="1" ${requestScope.sortTypeB == 1 ? 'selected' : ''}>Increase</option>
                                        <option value="2" ${requestScope.sortTypeB == 2 ? 'selected' : ''}>Decrease</option>
                                    </select>
                                </div>

                                <div class="col-md-1 mb-1">
                                    <input class="form-control" readonly="" value="Total: ${requestScope.countB}">
                                </div>

                                <!-- Submit Button (hidden) -->
                                <div class="col-md-2 mb-3" style="display: none;">
                                    <button type="submit" class="btn btn-primary btn-block">Search</button>
                                </div>
                            </div>
                        </form>
                        <div style="color: green">${add_suc}</div>
                        <div style="color: green">${update_suc}</div>
                        <div style="color: #0061f2">${out}</div>
                        <!-- Product Table -->
                        <div class="card shadow  row container-fluid  " style="display: flex; justify-content: center; align-items: center;">
                            <div class="card-body  col-md-6" >
                                <div class="table-responsive">
                                    <table class="table table-bordered border-1" id="dataTable" width="100%">
                                        <thead>
                                            <tr>

                                                <th scope="col">ID</th>
                                                <th scope="col">Brand Name</th>

                                                <th scope="col">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.listB}" var="c">
                                                <tr>
                                                    <td>${c.bid}</td>
                                                    <td>${c.name}</td>


                                                    <td><li class="list-inline-item">
                                                <a href="#" onclick="return checkDelete('${c.bid}')"><button class="btn btn-danger btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-trash"></i></button></a>
                                            </li>
                                            <li class="list-inline-item">
                                                <a href="updateBrand?bidUpdate=${c.bid}"><button class="btn btn-success btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-edit"></i></button></a>
                                            </li>
                                            </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>

                                    </table>

                                </div>

                            </div>
                        </div>

                        <!-- Paging -->
                        <nav aria-label="Page navigation example" class="mt-4" style="margin-top: 1.5rem;">
                            <ul class="pagination justify-content-center" style="list-style: none; padding: 0; display: flex;">
                                <c:set var="currentIndex" value="${param.index != null ? param.index : 1}" />

                                <!-- Previous button -->
                                <c:if test="${currentIndex > 2}">
                                    <li style="margin: 0 5px;">
                                        <a href="listBrand?index=${currentIndex - 2}&searchBrand=${param.searchBrand}&sortBrand=${param.sortBrand}&sortTypeB=${param.sortTypeB}"
                                           style="text-decoration: none; padding: 8px 16px; border: 1px solid #ddd; border-radius: 4px;">
                                              ${currentIndex -2}
                                        </a>
                                    </li>
                                </c:if>

                                <!-- Page links -->
                                <c:forEach begin="${currentIndex > 2 ? currentIndex - 1 : 1}" end="${(currentIndex < endPage - 1) ? currentIndex + 1 : endPage}" var="i">
                                    <li style="margin: 0 5px;">
                                        <a class="${i == currentIndex ? 'active' : ''}" href="listBrand?index=${i}&searchBrand=${param.searchBrand}&sortBrand=${param.sortBrand}&sortTypeB=${param.sortTypeB}"
                                           style="text-decoration: none; padding: 8px 16px; border: 1px solid #ddd; border-radius: 4px; color: ${i == currentIndex ? '#fff' : '#007bff'}; background-color: ${i == currentIndex ? '#007bff' : '#fff'};">
                                            ${i}
                                        </a>
                                    </li>
                                </c:forEach>

                                <!-- Next button -->
                                <c:if test="${currentIndex +2 <= endPage}">
                                    <li style="margin: 0 5px;">
                                        <a href="listBrand?index=${currentIndex + 2}&searchBrand=${param.searchBrand}&sortBrand=${param.sortBrand}&sortTypeB=${param.sortTypeB}"
                                           style="text-decoration: none; padding: 8px 16px; border: 1px solid #ddd; border-radius: 4px;">
                                             ${currentIndex + 2 }
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                        <!-- End paging -->




                    </div>
                    <!-- /.container-fluid -->
                </div>
                <!-- End of Main Content -->
            </div>
            <!-- End of Content Wrapper -->
        </div>
        <!-- End of Page Wrapper -->

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const form = document.getElementById("filterForm");
                const elements = form.querySelectorAll('input, select');

                elements.forEach(element => {
                    element.addEventListener('change', function () {
                        form.submit();
                    });
                });
            });
        </script>
        <script>
            function myFunction() {
                const inpObj = document.getElementById("name");

                // Reset custom validity
                inpObj.setCustomValidity("");

                const value = inpObj.value.trim();
                const valueLength = value.length;

                // Regex to check if value contains only alphanumeric characters
                const alphanumericRegex = /^[a-zA-Z0-9]+$/;

                if (valueLength <= 1) {
                    inpObj.setCustomValidity("Text is too short. Please enter between 2 and 30 characters.");
                } else if (valueLength > 30) {
                    inpObj.setCustomValidity("Text is too long. Please enter between 2 and 30 characters.");
                } else if (!alphanumericRegex.test(value)) {
                    inpObj.setCustomValidity("Please enter only alphanumeric characters.");
                } else {
                    inpObj.setCustomValidity(""); // Clear any previous validation message
                }
            }
        </script>
        <script>
            function showForm() {
                document.getElementById('newCateForm').style.display = 'block';
                document.getElementById('newCateButton').style.display = 'none';
            }

            function cancelAdd() {
                document.getElementById('newCateForm').style.display = 'none';
                document.getElementById('newCateButton').style.display = 'block';
            }

        </script>
        <script>
            function checkDelete(uid) {
                // Thêm mã kiểm tra hợp lệ của form nếu cần
                if (confirm("Delete brand with bid = " + uid + "?")) {
                    window.location = 'deleteBrand?bid=' + uid;
                }
            }
        </script>
        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
        <!-- Custom scripts for all pages-->
        <script src="adminassets/js/sb-admin-2.min.js"></script>
        <!-- Page level plugins -->
        <script src="adminassets/vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="adminassets/vendor/datatables/dataTables.bootstrap4.min.js"></script>
        <!-- Page level custom scripts -->
        <script src="adminassets/js/demo/datatables-demo.js"></script>

    </body>

</html>
