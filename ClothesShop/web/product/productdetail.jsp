<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Product Detail</title>

    <!-- Custom fonts for this template -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="adminassets/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="adminassets/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
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
                    <h1 class="h3 mb-2 text-gray-800">Product Detail</h1>
                    <a href="listproduct" class="btn btn-primary mb-3">Back to Products</a>

                    <!-- Product Detail Card -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h2 class="m-0 font-weight-bold text-primary">${product.name}</h2>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <p><strong>ID:</strong> ${product.pid}</p>
                                    <p><strong>Quantity:</strong> ${product.quantity}</p>
                                    <p><strong>Price:</strong> $${product.price}</p>
                                    <p><strong>Category:</strong> ${product.category.name}</p>
                                    <p><strong>Brand:</strong> ${product.brand.name}</p>
                                    <p><strong>Gender:</strong> ${product.gender.description}</p>
                                    <p><strong>Sizes:</strong>
                                        <c:forEach var="size" items="${product.sizes}" varStatus="status">
                                            ${size.name}<c:if test="${!status.last}">, </c:if>
                                        </c:forEach>
                                    </p>
                                    <p><strong>Release Date:</strong> ${product.releaseDate}</p>
                                    <p><strong>Description:</strong> ${product.describe}</p>
                                </div>
                                <div class="col-md-6">
                                    <img src="${product.img}" alt="${product.name}" class="img-fluid">
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- End of Main Content -->
        </div>
        <!-- End of Content Wrapper -->
    </div>
    <!-- End of Page Wrapper -->

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
