<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Product Management</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .table-container {
                margin-top: 20px;
            }
        </style>
        <script>
            function validateForm() {
                const priceFrom = document.forms["filterForm"]["priceFrom"].value;
                const priceTo = document.forms["filterForm"]["priceTo"].value;
                const maxPrice = ${maxPrice};

                if (priceFrom && priceFrom <= 0) {
                    alert("Min Price must be greater than 0");
                    return false;
                }

                if (priceTo && priceTo >= maxPrice) {
                    alert("Max Price must be less than " + maxPrice);
                    return false;
                }

                return true;
            }
        </script>
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="mb-4">Product Management</h1>
            <a href="addproduct" class="btn btn-success mb-3">Add New Product</a>

            <!-- Search and Filter Form -->
            <form name="filterForm" action="listproduct" method="get" onsubmit="return validateForm()">
                <!-- Search by name -->
                <input type="text" name="search" placeholder="Search by name" value="${search}">

                <!-- Categories Dropdown -->
                <select name="category">
                    <option value="">All Categories</option>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.cid}" <c:if test="${category.cid == param.category}">selected</c:if>>${category.name}</option>
                    </c:forEach>
                </select>

                <!-- Brands Dropdown -->
                <select name="brand">
                    <option value="">All Brands</option>
                    <c:forEach var="brand" items="${brands}">
                        <option value="${brand.bid}" <c:if test="${brand.bid == param.brand}">selected</c:if>>${brand.name}</option>
                    </c:forEach>
                </select>

                <!-- Genders Dropdown -->
                <select name="gender">
                    <option value="">All Genders</option>
                    <c:forEach var="gender" items="${genders}">
                        <option value="${gender.gid}" <c:if test="${gender.gid == param.gender}">selected</c:if>>${gender.description}</option>
                    </c:forEach>
                </select>

                <!-- Price Range -->
                <input type="number" name="priceFrom" placeholder="Min Price" value="${priceFrom}">
                <input type="number" name="priceTo" placeholder="Max Price" value="${priceTo}">

                <!-- Submit Button -->
                <button type="submit" class="btn btn-primary">Filter</button>
            </form>

            <!-- Product Table -->
            <div class="table-container">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Category</th>
                            <th>Brand</th>
                            <th>Gender</th>
                            <th>Size</th>
                            <th>Release Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td>${product.pid}</td>
                                <td><a href="productdetail?pid=${product.pid}">${product.name}</a></td>
                                <td>${product.quantity}</td>
                                <td>${product.price}</td>
                                <td>${product.category.name}</td>
                                <td>${product.brand.name}</td>
                                <td>${product.gender.description}</td>
                                <td>
                                    <c:forEach var="size" items="${product.sizes}" varStatus="status">
                                        ${size.name}<c:if test="${!status.last}">,</c:if>
                                    </c:forEach>
                                </td>
                                <td>${product.releaseDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Pagination -->
            <nav aria-label="Page navigation example" class="mt-4">
                <ul class="pagination justify-content-center">
                    <c:if test="${pageIndex > 1}">
                        <li class="page-item">
                            <a class="page-link" href="listproduct?page=${pageIndex - 1}&search=${param.search}&category=${param.category}&brand=${param.brand}&gender=${param.gender}&priceFrom=${param.priceFrom}&priceTo=${param.priceTo}">Previous</a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <li class="page-item ${pageIndex == i ? 'active' : ''}">
                            <a class="page-link" href="listproduct?page=${i}&search=${param.search}&category=${param.category}&brand=${param.brand}&gender=${param.gender}&priceFrom=${param.priceFrom}&priceTo=${param.priceTo}">${i}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${pageIndex < totalPages}">
                        <li class="page-item">
                            <a class="page-link" href="listproduct?page=${pageIndex + 1}&search=${param.search}&category=${param.category}&brand=${param.brand}&gender=${param.gender}&priceFrom=${param.priceFrom}&priceTo=${param.priceTo}">Next</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
