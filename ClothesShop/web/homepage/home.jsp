<%-- 
    Document   : home
    Created on : Jun 20, 2024, 10:22:00 PM
    Author     : KieuTuanPC
--%>
<%@ page import="model.Category" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  class="no-js" lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Clothes Shop</title>
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
        <script src="${pageContext.request.contextPath}/assets/js/jquery-3.5.1.min.js" type="text/javascript"></script>

    </head>
    <body>

        <div class="off_canvars_overlay"></div>
        <!-- Header -->
        <header class="header">
            <!-- Header Top -->

            <!-- Header Middle -->
            <%@include file="header.jsp" %>

            <!-- Header Bottom -->
            <%@include file="../homepage/header_bottom.jsp" %>
        </header>
        <!-- Header -->

        <!-- Start Mobile Menu Area -->
        <div class="mobile-menu-area">

            <!--offcanvas menu area start-->
            <div class="off_canvars_overlay"></div>
            <div class="offcanvas_menu">
                <div class="offcanvas_menu_wrapper">
                    <div class="canvas_close">
                        <a href="javascript:void(0)"><i class="fas fa-times"></i></a>
                    </div>
                    <div class="mobile-logo">
                        <h2><a href="Home"><img src="assets/img/logo.png" alt="Logo"></a></h2>
                    </div>
                    <div id="menu" class="text-left">
                        <ul class="offcanvas_main_menu">
                            <li><a href="/ClothesShop/Home">Home</a></li>
                            <li class="menu-item-has-children">
                                <a href="/ClothesShop/AllProduct">Shop</a>
                                <ul class="sub-menu">
                                    <li><a href="/ClothesShop/AllProduct">All Products</a></li>
                                    <li><a href="gender-products?gender=men">Men</a></li>
                                    <li><a href="gender-products?gender=women">Women</a></li>
                                    <li><a href="gender-products?gender=other">Other</a></li>
                                </ul>
                            </li>
                            <li class="menu-item-has-children">
                                <a href="#">Collections</a>
                            </li>
                            <li class="menu-item-has-children">
                                <a href="#">Blog</a>
                                <ul class="sub-menu">
                                    <li><a href="blog.html">Blog</a></li>
                                    <li><a href="blog-grid.html">Blog Grid</a></li>
                                    <li><a href="single.html">Blog Single</a></li>
                                </ul>
                            </li>
                            <li><a href="contact.html">Contact Us</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!--offcanvas menu area end-->
        <!-- End Mobile Menu Area -->
        <!-- Start Hero Area -->
        <section class="hero-area">
            <div class="hero-area-full owl-carousel">
                <div class="hero-slider-single" style="background-image: url('assets/img/clothing-accessories-men-women-ready-1117305.jpg');">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="hero-slider-single-content">
                                    <h2>Leading Fashion <span>Manufacturer</span></h2>
                                    <p>Ostromi Is A Specialist In High-Quality Clothing And Accessories</p>
                                    <a class="button-1" href="/ClothesShop/AllProduct">Shop Now</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="hero-slider-single" style="background-image: url('assets/img/0eded263a69560e7bc89d9da84441f07.jpg');">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="hero-slider-single-content">
                                    <h2>Leading Fashion <span>Manufacturer</span></h2>
                                    <p>Ostromi Is A Specialist In High-Quality Clothing And Accessories</p>
                                    <a class="button-1" href="/ClothesShop/AllProduct">Shop Now</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="hero-slider-single" style="background-image: url('assets/img/pngtree-men-s-shirts-hang-on-racks-in-a-closet-picture-image_2615102.jpg');">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="hero-slider-single-content">
                                    <h2>Leading Fashion <span>Manufacturer</span></h2>
                                    <p>Ostromi Is A Specialist In High-Quality Clothing And Accessories</p>
                                    <a class="button-1" href="/ClothesShop/AllProduct">Shop Now</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Hero Area -->
        <!-- Start banner Area -->
        <section class="banner-area container">
            <div class="row">
                <c:forEach var="image" items="${bannerImages}">
                    <div class="col-lg-3 col-md-6 mb-30" style="padding: 15px;">
                        <div class="banner-item" style="overflow: hidden; display: flex; align-items: center; justify-content: center;">
                            <a href="#">
                                <img src="${image}" alt="img" style="width: 300px; height: 300px; object-fit: cover;">
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </section>
        <!-- End banner Area -->
        <!-- Start Product Style -->
        <section class="pb-50 pt-50">
            <div class="container">
                <div class="row">
                    <div class="product-tab-style product-tab-style-1">
                        <div class="row">
                            <div class="col-lg-6">
                                <h3 class="deal-title">Featured Products</h3>
                            </div>
                            <div class="col-lg-6">
                                <ul class="nav nav-tabs" id="myTab" role="tablist">
                                    <li class="nav-item" role="presentation">
                                        <span class="nav-link" id="featured1-tab" data-bs-toggle="tab" data-bs-target="#featured1" role="tab" aria-controls="featured1" aria-selected="false">Featured</span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="tab-content product-tab-full" id="myTabContent">
                            <div class="tab-pane product-tab-item fade show active" id="popular1" role="tabpanel" aria-labelledby="popular1-tab">
                                <div class="product-tab-slider-full owl-carousel">
                                    <!-- Iterating through products -->
                                    <c:forEach var="product" items="${products}">
                                        <div class="product-single product-style-1">
                                            <div class="product-thumbnail" style="width: 100%; height: 400px; overflow: hidden;">
                                                <a href="DetailProduct?pid=${product.pid}">
                                                    <img src="${product.img}" alt="${product.name}" style="width: 100%; height: 400px; object-fit: cover; display: block;">
                                                    <img src="${product.img}" alt="${product.name}" style="width: 100%; height: 400px; object-fit: cover; display: block;">
                                                </a>
                                            </div>
                                            <div class="product-content">
                                                <h4><a href="DetailProduct?pid=${product.pid}">${product.name}</a></h4>
                                                <div class="pricing">
                                                    <span>$${product.price}</span>
                                                </div>
                                                <div class="product-content-btn">
                                                    <ul>
                                                        <li><a onclick="addToWishList(${product.pid})"><i class="far fa-heart"></i></a></li>
                                                        <li><a onclick="addToCart(${product.pid})" class="active">Add to Cart</a></li>
                                                        <li><a href="#"><i class="far fa-eye"></i></a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="latest-product pt-70 pb-70">
            <div class="container">
                <!-- Section Title -->
                <div class="row">
                    <div class="col-12">
                        <div class="section-headding-1 mb-50">
                            <h2><span>Latest Products</span></h2>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div class="latest-product-full owl-carousel">
                            <!-- Single -->
                            <c:forEach var="product2" items="${products2}">
                                <div class="product-single">
                                    <div class="product-thumbnail">
                                        <a href="DetailProduct?pid=${product2.pid}}">
                                            <img src="${product2.img}" alt="${product2.name}" style="width: 100%; height: 300px; object-fit: cover; border:2px solid">
                                        </a>
                                        <div class="product-thumbnail-overly">
                                            <ul>
                                                <li><a onclick="addToCart(${product2.pid})"><i class="fas fa-shopping-cart"></i></a></li>
                                                <li><a onclick="addToWishList(${product2.pid})"><i class="far fa-heart"></i></a></li>
                                                <li><a href="#"><i class="far fa-eye"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="product-content">
                                        <h4><a href="DetailProduct?pid=${product2.pid}">${product2.name}</a></h4>
                                        <div class="pricing">
                                            <span>$${product2.price}</span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

            </div>
        </section>


        <!-- End Our Latest Product -->

        <!-- Start Deal Product -->
        <!-- Start Deal Product -->

        <!-- Start Product Widget List Area -->

        <!-- End Product Widget List Area -->

        <!-- Start Latest Blog -->
      

        <!-- End Latest Blog -->

        <!-- Start Instagram Feed -->
        <!-- End Instagram Feed -->

        <!-- Start Footer Area -->
        <%@include file="../homepage/footer.jsp" %>

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
    </body>
</html>