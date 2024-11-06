<%-- 
    Document   : listCate
    Created on : Jun 3, 2024, 9:17:58 PM
    Author     : chien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html  class="no-js" lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>BulkShop - Electronics Shop HTML Template | Shop Left Sidebar</title>
        <link rel="icon" href="assets/img/icon.png" type="image/gif" sizes="16x16">
        <link rel="icon" href="assets/img/icon.png" type="image/gif" sizes="18x18">
        <link rel="icon" href="assets/img/icon.png" type="image/gif" sizes="20x20">

        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/fontawesome.all.min.css">
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="assets/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="assets/css/magnific-popup.css">
        <link rel="stylesheet" href="assets/css/normalize.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/css/responsive.css">

    </head>
    <body>
        <div id="preloader" class="preeloader">
            <div class="sk-circle">
                <div class="sk-circle1 sk-child"></div>
                <div class="sk-circle2 sk-child"></div>
                <div class="sk-circle3 sk-child"></div>
                <div class="sk-circle4 sk-child"></div>
                <div class="sk-circle5 sk-child"></div>
                <div class="sk-circle6 sk-child"></div>
                <div class="sk-circle7 sk-child"></div>
                <div class="sk-circle8 sk-child"></div>
                <div class="sk-circle9 sk-child"></div>
                <div class="sk-circle10 sk-child"></div>
                <div class="sk-circle11 sk-child"></div>
                <div class="sk-circle12 sk-child"></div>
            </div>
        </div>
        <div class="off_canvars_overlay"></div>
        <!-- Header -->
        <header class="header">
            <!-- Header Top -->
            <div class="header-top">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-5">
                            <div class="top-text">
                                <p>Welcome to Electronics Shop</p>
                            </div>
                        </div>
                        <div class="col-lg-7">
                            <div class="top-list">
                                <a href="#"><i class="fas fa-mobile-alt"></i> +02456 054546</a>
                                <a href="wishlist.html"><i class="far fa-heart"></i> Wishlist</a>
                                <a href="login.html"><i class="fas fa-user"></i> Login / Register</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Header Middle -->
            <div class="header-middle pt-30 pb-30">
                <div class="container">
                    <div class="row">
                        <!-- Logo -->
                        <div class="col-lg-2">
                            <div class="logo">
                                <h2><a href="index.html"><img src="assets/img/logo.png"></a></h2>
                            </div>
                        </div>
                        <!-- Search Bar -->
                        <div class="col-lg-8">
                            <div class="header-search-form">
                                <form action="#">
                                    <select class="form-select">
                                        <option selected>All Categories</option>
                                        <option value="1">Mobile</option>
                                        <option value="2">LifeStyle</option>
                                        <option value="3">Leptop</option>
                                        <option value="4">Cell Phones</option>
                                        <option value="5">Game & Consoles</option>
                                        <option value="6">Smart Watchs</option>
                                        <option value="7">Smartphone</option>
                                    </select>
                                    <input type="search" name="search" placeholder="Search keyword here...">
                                    <button type="submit"><i class="fas fa-search"></i></button>
                                </form>
                            </div>	
                        </div>
                        <!-- MiniCart -->
                        <div class="col-lg-2">
                            <div class="desktop-mini-cart">
                                <div class="mini-cart">
                                    <div class="mini-cart-icon">
                                        <i class="fas fa-shopping-cart"></i>
                                        <span class="counter">02</span>
                                        <span class="counter-cart"><small>Your Cart</small>$10.00</span>
                                        <!-- Mini Cart Content -->
                                        <div class="minicart-content-wrapper">
                                            <ul class="cart-list-full">
                                                <!-- Single -->
                                                <li class="cart-list-single">
                                                    <img src="assets/img/product/1.jpg" alt="img">
                                                    <h5><a href="#">simple product</a></h5>
                                                    <span class="price">$120</span>
                                                    <div class="close"><i class="fas fa-times"></i></div>
                                                </li>
                                                <!-- Single -->
                                                <li class="cart-list-single">
                                                    <img src="assets/img/product/2.jpg" alt="img">
                                                    <h5><a href="#">simple product</a></h5>
                                                    <span class="price">$120</span>
                                                    <div class="close"><i class="fas fa-times"></i></div>
                                                </li>
                                            </ul>
                                            <h2 class="subtotal">Subtotal : <span>$220</span></h2>
                                            <div class="minicart-btn">
                                                <a class="button-1" href="cart.html">View Cart</a>
                                                <a class="button-2" href="#">Checkout</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Header Bottom -->
            <div class="header-bottm">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="logo-2">
                                <h2><a href="index.html"><img src="assets/img/logo.png"></a></h2>
                            </div>
                            <div class="canvas_open">
                                <a href="javascript:void(0)"><i class="fas fa-bars"></i></a>
                            </div>
                            <div class="mobile-mini-cart">
                                <div class="mini-cart">
                                    <div class="mini-cart-icon">
                                        <i class="fas fa-shopping-cart"></i>
                                        <span class="counter">02</span>
                                        <span class="counter-cart"><small>Your Cart</small>$10.00</span>
                                        <!-- Mini Cart Content -->
                                        <div class="minicart-content-wrapper">
                                            <ul class="cart-list-full">
                                                <!-- Single -->
                                                <li class="cart-list-single">
                                                    <img src="assets/img/product/1.jpg" alt="img">
                                                    <h5><a href="#">simple product</a></h5>
                                                    <span class="price">$120</span>
                                                    <div class="close"><i class="fas fa-times"></i></div>
                                                </li>
                                                <!-- Single -->
                                                <li class="cart-list-single">
                                                    <img src="assets/img/product/2.jpg" alt="img">
                                                    <h5><a href="#">simple product</a></h5>
                                                    <span class="price">$120</span>
                                                    <div class="close"><i class="fas fa-times"></i></div>
                                                </li>
                                            </ul>
                                            <h2 class="subtotal">Subtotal : <span>$220</span></h2>
                                            <div class="minicart-btn">
                                                <a class="button-1" href="cart.html">View Cart</a>
                                                <a class="button-2" href="#">Checkout</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="menu">
                                <nav>
                                    <ul>
                                        <li><a href="#">Home</a></li>
                                        <li><a href="about.html">About</a></li>
                                        <li><a href="#">Page <i class="fa fa-angle-down"></i></a>
                                            <ul class="submenu-item">
                                                <li><a href="cart.html">Cart</a></li>
                                                <li><a href="wishlist.html"> Wishlist</a></li>
                                                <li><a href="checkout.html">Checkout</a></li>
                                                <li><a href="login.html">Login</a></li>
                                                <li><a href="register.html">Register</a></li>
                                                <li><a href="reset-password.html">Reset Password</a></li>
                                                <li><a href="privacy-policy.html">Privacy Policy</a></li>
                                                <li><a href="terms-condition.html">Terms & Condition</a></li>
                                                <li><a href="404.html">404 Error</a></li>
                                                <li><a href="faq.html">Faq</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="shop.html">Shop <i class="fa fa-angle-down"></i></a>
                                            <ul class="submenu-item">
                                                <li><a href="shop.html">Shop</a></li>
                                                <li><a href="shop2-columns.html">Shop 2 Columns</a></li>
                                                <li><a href="shop-grid.html">Shop Grid</a></li>
                                                <li><a href="shop-left-sidebar.html">Shop Left Sidebar</a></li>
                                                <li><a href="shop-list.html">Shop List</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="#">Elements <i class="fa fa-angle-down"></i></a>
                                            <ul class="mega-sub-menu">
                                                <li>
                                                    <a class="menu-title" href="#">Element List</a>
                                                    <ul>
                                                        <li><a href="element-infobox.html">Element Info Box</a></li>
                                                        <li><a href="element-breadcrumb.html">Element Breadcrum</a></li>
                                                        <li><a href="element-heading.html">Element Headding</a></li>
                                                        <li><a href="element-post.html">Element Post Element</a></li>
                                                        <li><a href="element-pricing.html">Element Pricing</a></li>
                                                    </ul>
                                                </li>
                                                <li>
                                                    <a class="menu-title" href="#">Element</a>
                                                    <ul>
                                                        <li><a href="element-product-category.html">Element Product Category</a></li>
                                                        <li><a href="element-product-style.html">Element Product Style</a></li>
                                                        <li><a href="element-product-tab.html">Element Product Tab</a></li>
                                                        <li><a href="element-team-style.html">Element Team</a></li>
                                                        <li><a href="element-testimonial.html">Element Testimonial</a></li>
                                                    </ul>
                                                </li>
                                                <li>
                                                    <a class="menu-title" href="#">Element List</a>
                                                    <ul>
                                                        <li><a href="shop.html">Element Shop</a></li>
                                                        <li><a href="shop2-columns.html">Element Shop 2 Columns</a></li>
                                                        <li><a href="shop-grid.html">Element Shop Grid</a></li>
                                                        <li><a href="shop-left-sidebar.html">Element Shop Left Sidebar</a></li>
                                                        <li><a href="shop-list.html">Element Shop List</a></li>
                                                    </ul>
                                                </li>
                                                <li>
                                                    <a class="menu-title" href="#">Element List</a>
                                                    <ul>
                                                        <li><a href="product-details.html">Element Shop Single</a></li>
                                                        <li><a href="cart.html">Element Cart Page</a></li>
                                                        <li><a href="checkout.html">Element CheckOut Page</a></li>
                                                        <li><a href="wishlist.html">Element Wishlist</a></li>
                                                    </ul>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a href="blog.html">blog <i class="fa fa-angle-down"></i></a>
                                            <ul class="submenu-item">
                                                <li><a href="blog.html">Blog</a></li>
                                                <li><a href="blog-grid.html">Blog Grid</a></li>
                                                <li><a href="single.html">Blog Single</a></li>
                                            </ul>
                                        </li>
                                        <li><a href="contact.html">Contact</a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- Header -->

        <!-- Start Mobile Menu Area -->
        <div class="mobile-menu-area">

            <!--offcanvas menu area start-->
            <div class="off_canvars_overlay">

            </div>
            <div class="offcanvas_menu">
                <div class="offcanvas_menu_wrapper">
                    <div class="canvas_close">
                        <a href="javascript:void(0)"><i class="fas fa-times"></i></a>  
                    </div>
                    <div class="mobile-logo">
                        <h2><a href="index.html"><img src="assets/img/logo.png"></a></h2>
                    </div>
                    <div id="menu" class="text-left ">
                        <ul class="offcanvas_main_menu">
                            <li class="menu-item-has-children">
                                <a href="index.html">Home</a>
                            </li>
                            <li class="menu-item-has-children">
                                <a href="about.html">about Us</a>
                            </li>
                            <li class="menu-item-has-children">
                                <a href="#">Page</a>
                                <ul class="sub-menu">
                                    <li><a href="cart.html">Cart</a></li>
                                    <li><a href="wishlist.html"> Wishlist</a></li>
                                    <li><a href="checkout.html">Checkout</a></li>
                                    <li><a href="login.html">Login</a></li>
                                    <li><a href="register.html">Register</a></li>
                                    <li><a href="reset-password.html">Reset Password</a></li>
                                    <li><a href="privacy-policy.html">Privacy Policy</a></li>
                                    <li><a href="terms-condition.html">Terms & Condition</a></li>
                                    <li><a href="404.html">404 Error</a></li>
                                    <li><a href="faq.html">Faq</a></li>
                                </ul>
                            </li>
                            <li class="menu-item-has-children">
                                <a href="#">Shop</a>
                                <ul class="sub-menu">
                                    <li><a href="shop.html">Shop</a></li>
                                    <li><a href="shop2-columns.html">Shop 2 Columns</a></li>
                                    <li><a href="shop-grid.html">Shop Grid</a></li>
                                    <li><a href="shop-left-sidebar.html">Shop Left Sidebar</a></li>
                                    <li><a href="shop-list.html">Shop List</a></li>
                                </ul>
                            </li>
                            <li class="menu-item-has-children">
                                <a href="#">Elements</a>
                                <ul class="sub-menu">
                                    <li class="menu-item-has-children">
                                        <a href="#">Elements</a>
                                        <ul class="sub-menu">
                                            <li><a href="element-infobox.html">Element Info Box</a></li>
                                            <li><a href="element-breadcrumb.html">Element Breadcrum</a></li>
                                            <li><a href="element-heading.html">Element Headding</a></li>
                                            <li><a href="element-post.html">Element Post Element</a></li>
                                            <li><a href="element-pricing.html">Element Pricing</a></li>
                                        </ul>
                                    </li>
                                    <li class="menu-item-has-children">
                                        <a href="#">Elements</a>
                                        <ul class="sub-menu">
                                            <li><a href="element-product-category.html">Element Product Category</a></li>
                                            <li><a href="element-product-style.html">Element Product Style</a></li>
                                            <li><a href="element-product-tab.html">Element Product Tab</a></li>
                                            <li><a href="element-team-style.html">Element Team</a></li>
                                            <li><a href="element-testimonial.html">Element Testimonial</a></li>
                                        </ul>
                                    </li>
                                    <li class="menu-item-has-children">
                                        <a href="#">Elements</a>
                                        <ul class="sub-menu">
                                            <li><a href="shop.html">Element Shop</a></li>
                                            <li><a href="shop2-columns.html">Element Shop 2 Columns</a></li>
                                            <li><a href="shop-grid.html">Element Shop Grid</a></li>
                                            <li><a href="shop-left-sidebar.html">Element Shop Left Sidebar</a></li>
                                            <li><a href="shop-list.html">Element Shop List</a></li>
                                        </ul>
                                    </li>
                                    <li class="menu-item-has-children">
                                        <a href="#">Elements</a>
                                        <ul class="sub-menu">
                                            <li><a href="product-details.html">Element Shop Single</a></li>
                                            <li><a href="cart.html">Element Cart Page</a></li>
                                            <li><a href="checkout.html">Element CheckOut Page</a></li>
                                            <li><a href="wishlist.html">Element Wishlist</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li class="menu-item-has-children">
                                <a href="#">Blog</a>
                                <ul class="sub-menu">
                                    <li><a href="blog.html">Blog</a></li>
                                    <li><a href="blog-grid.html">Blog Grid</a></li>
                                    <li><a href="single.html">Blog Single</a></li>
                                </ul>
                            </li>
                            <li class="menu-item-has-children">
                                <a href="contact.html"> Contact Us</a> 
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!--offcanvas menu area end-->
        <!-- End Mobile Menu Area -->
        <!-- Start BreadCrumb Area -->


        <!-- Start Shop Area -->
        <section class="shop-area pt-70 pb-70">
            <div class="container">
                <div class="row">
                    <!-- Siderbar -->
                    <div class="col-lg-4">
                        <!-- Single -->

                        <!-- Single -->
                        <div class="sidebar-widgets">
                            <h4 class="title">Manager</h4>
                            <div class="widgets-latest-product-full">
                                <!-- Single -->
                                <div class="widgets-latest-product-single mb-30">

                                    <div class="content">
                                        <h4><a href="#">Products</a></h4>

                                    </div>
                                </div>
                                <div class="widgets-latest-product-single mb-30">

                                    <div class="content">
                                        <h4><a class="active" href="listCate">Category</a></h4>

                                    </div>
                                </div>
                                <div class="widgets-latest-product-single mb-30">

                                    <div class="content">
                                        <h4><a href="#">News</a></h4>

                                    </div>
                                </div>
                                <div class="widgets-latest-product-single mb-30">

                                    <div class="content">
                                        <h4><a href="#">User</a></h4>

                                    </div>
                                </div>
                                <div class="widgets-latest-product-single mb-30">

                                    <div class="content">
                                        <h4><a href="#">Cart</a></h4>

                                    </div>
                                </div>
                                <div class="widgets-latest-product-single mb-30">

                                    <div class="content">
                                        <h4><a href="listBrand">Brand</a></h4>

                                    </div>
                                </div>

                                <!-- Single -->

                            </div>
                        </div>
                        <!-- Single -->
                        <div class="sidebar-widgets">
                            <h4 class="title">Blog categories</h4>
                            <ul>
                                <li><a href="#">All about Digital</a></li>
                                <li><a href="#">Smartphone & Tablett</a></li>
                                <li><a href="#">Camera</a></li>
                                <li><a href="#">Printer & ink</a></li>
                                <li><a href="#">Cameras</a></li>
                            </ul>
                        </div>
                        <!-- Single -->
                        <div class="sidebar-widgets">
                            <h4 class="title">Tags</h4>
                            <div class="tags">
                                <a href="#">camera</a>
                                <a href="#">Computer</a>
                                <a href="#">Mobile</a>
                                <a href="#">Bag</a>
                                <a href="#">shoes</a>
                                <a href="#">Store</a>
                                <a href="#">smartphone</a>
                                <a href="#">Watch</a>
                            </div>
                        </div>
                        <!-- Single -->
                        <div class="sidebar-widgets">
                            <h4 class="title">Recent Post</h4>
                            <ul>
                                <li><a href="#">Gallery Post with Supported Animation</a></li>
                                <li><a href="#">Announcement – Standard Post without Image</a></li>
                                <li><a href="#">We’re the best Designers from UK</a></li>
                                <li><a href="#">A Beautiful Day – Standard Post with Image</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <!-- Shop Top Pagination -->
                        <div class="row section-bg pt-20 pb-20 mb-30">
                            <div class="col-lg- col-md-6 order-2 order-md-1">
                                <div class="sidebar-widgets">
                                    <%
        String searchCate = request.getParameter("searchCate");
        if (searchCate == null || searchCate.isEmpty()) {
            searchCate = "Enter search key here";
        }
                                    %>
                                    <!-- Search single -->
                                    <form action="listCate">
                                        <input type="search" name="searchCate" placeholder="<%= searchCate %>">
                                        <button type="submit"><i class="fas fa-search"></i></button>
                                    </form>
                                </div>
                            </div>

                            <div class="col-lg-5 col-md-6 order-1 order-md-2 sidebar-widgets">
                                <div class="top-bar-right">
                                    <select class="form-select" aria-label="Default select example">

                                        <option value="1">Sort by Name</option>
                                        <option value="2">Sort by Cid</option>

                                    </select>
                                </div>
                                <div class="top-bar-right">

                                    <div class="product-amount">
                                        <p>Showing 1–16 of 21 results</p>
                                    </div>

                                </div>
                            </div>


                        </div>
                        <!-- Shop -->
                        <div class="row">

                            <!-- List Category -->

                            <div style="margin-left: 10px; margin-right: 100px">
                                <div style="text-align: start">
                                    <a href="addCate">
                                        <button class="btn btn-success btn-sm" type="button" data-toggle="tooltip" data-placement="top" title="Edit">Add Category</button>
                                    </a>
                                </div>
                                <table class="table table-striped table-bordered table-title" cellspacing="0" rules="all" border="1" id="gvLO"
                                       style="border-collapse:collapse;">
                                    <thead>
                                        <tr>
                                            <th scope="col">ID</th>
                                            <th scope="col">Category Name</th>

                                            <th scope="col">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.listC}" var="c">
                                            <tr>
                                                <td>${c.cid}</td>
                                                <td>${c.name}</td>


                                                <td><li class="list-inline-item">
                                            <a href="deleteCate?cid=${c.cid}" onclick="return checkdelete()"><button class="btn btn-danger btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-trash"></i></button></a>
                                        </li>
                                        <li class="list-inline-item">
                                            <a href="updateCate?cidUpdate=${c.cid}"><button class="btn btn-success btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-edit"></i></button></a>
                                        </li>
                                        </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>

                            </div>

                            <!-- End List Cate -->

                            <!-- Pagging Start -->
                            <div class="row">
                                <div class="col-12 mb-30">
                                    <div class="page-pagination text-center">

                                        <ul>
                                            <c:set var="currentIndex" value="${param.index != null ? param.index : 1}" />
                                            <c:forEach begin="1" end="${requestScope.endPage}" var="i">
                                                <li>

                                                    <a class="${i == currentIndex ? 'active' : ''}" href="listCate?index=${i}&searchCate=${param.searchCate}">${i}</a>


                                                </li>
                                            </c:forEach>


                                        </ul>

                                    </div>
                                </div>
                            </div>
                            <!-- Pagging End -->


                        </div>
                        <!-- Pagination -->

                    </div>
                </div>
            </div>
        </section>
        <!-- End Shop Area -->

        <!-- Start Footer Area -->
        <footer class="footer">
            <!-- Footer Top -->
            <div class="footer-top pt-50">
                <div class="container">
                    <div class="row">
                        <!-- SIngle -->
                        <div class="col-lg-3 col-md-6 mb-30">
                            <div class="footer-widgets-single">
                                <h2><img src="assets/img/white-logo.png"></h2>
                                <p> Lorem ipsum dolor sit amet, consectetuipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqut enim ad minim veniamquis </p>
                            </div>
                        </div>
                        <!-- SIngle -->
                        <div class="col-lg-3 col-md-6 mb-30">
                            <div class="footer-widgets-single">
                                <h3>My account</h3>
                                <ul>
                                    <li><a href="#">Sign In</a></li>
                                    <li><a href="#">View Cart</a></li>
                                    <li><a href="#">My Wishlist</a></li>
                                    <li><a href="#">Terms & Conditions</a></li>
                                    <li><a href="#">Contact us</a></li>
                                    <li><a href="#">Track My Order</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- SIngle -->
                        <div class="col-lg-3 col-md-6 mb-30">
                            <div class="footer-widgets-single">
                                <h3>Categories</h3>
                                <ul>
                                    <li><a href="#">Home Audio & Theater</a></li>
                                    <li><a href="#">TV & Video</a></li>
                                    <li><a href="#">Camera, Photo & Video</a></li>
                                    <li><a href="#">Cell Phones & Accessories</a></li>
                                    <li><a href="#">Headphones</a></li>
                                    <li><a href="#">Video Games</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- SIngle -->
                        <div class="col-lg-3 col-md-6 mb-30">
                            <div class="footer-widgets-single">
                                <h3>Newsletter</h3>
                                <p> Get notified of new products, limited releases, and more. </p>
                                <form action="#">
                                    <input type="email" name="email" placeholder="Your Email">
                                    <button type="submit" class="button-1">Subscribe</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Footer Bottom -->
            <div class="footer-bottom pt-30 pb-30">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="copyright-text">
                                <p> &copy; Copyright 2021 <a href="https://codepopular.com" target="_blank">CodePopular</a> All Rights Reserved. </p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <ul class="footer_payment">
                                <li><a href="#"><img src="assets/img/visa.png" alt="visa"></a></li>
                                <li><a href="#"><img src="assets/img/discover.png" alt="discover"></a></li>
                                <li><a href="#"><img src="assets/img/master_card.png" alt="master_card"></a></li>
                                <li><a href="#"><img src="assets/img/paypal.png" alt="paypal"></a></li>
                                <li><a href="#"><img src="assets/img/amarican_express.png" alt="amarican_express"></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- End Footer Area -->



        <div class="scroll-area">
            <i class="fa fa-angle-up"></i>
        </div>


        <!-- Js File -->
        <script src="assets/js/modernizr.min.js"></script>
        <script src="assets/js/jquery-3.5.1.min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/js/jquery.nav.min.js"></script>
        <script src="assets/js/jquery.magnific-popup.min.js"></script>
        <script src="assets/js/mixitup.min.js"></script>
        <script src="assets/js/wow.min.js"></script>
        <script src="assets/js/script.js"></script>
        <script src="assets/js/mobile-menu.js"></script>
        <script>
                                                function checkdelete() {
                                                    return confirm("Deleting a category means deleting all products in this category. Are you sure want to delete?");
                                                }
        </script>
    </body>
</html>