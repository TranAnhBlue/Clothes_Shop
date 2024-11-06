<%-- 
    Document   : checkOut
    Created on : Jul 8, 2024, 11:56:17 PM
    Author     : chien
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  class="no-js" lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>BulkShop - Electronics Shop HTML Template | Checkout</title>
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

            <!-- Header Middle -->
            <%@include file="../homepage/header.jsp" %>
            <!-- Header Bottom -->
            <%@include file="../homepage/header_bottom.jsp" %>
        </header>
        <!-- Header -->


        <!--offcanvas menu area end-->
        <!-- End Mobile Menu Area -->
        <!-- Start BreadCrumb Area -->
        <div class="breadcrumb-area pt-100 pb-100" style="background-image: url('assets/img/breadcrumb.jpg');">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb-content">
                            <h2>Checkout</h2>
                            <ul>
                                <li><a href="Home">Home</a></li>
                                <li class="active">Checkout</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End BreadCrumb Area -->


        <!-- Start Chekout Page -->
        <section class="checkout-page-wrapper pt-70 pb-70">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="checkout-billing-details-wrap">
                            <h2>Billing Details</h2>
                            <div class="billing-form-wrap">
                                <form id="checkoutForm" class="required" method="post" action="checkout">

                                    <div class="single-input-item">
                                        <label for="f_name" class="required">Name</label>
                                        <input readonly="" type="text" id="f_name" placeholder="${sessionScope.user.fullName}" required="required">
                                    </div>


                                    <div class="single-input-item">
                                        <label for="company_name"  class="required"> Address </label>
                                        <input type="text" required name="addressInput" id="company_name" placeholder="Address Name " value="${param.addressInput}">
                                    </div>


                                    <div class="single-input-item">
                                        <label for="phone" class="required">Phone</label>
                                        <input type="text" id="phone" readonly="" placeholder="${sessionScope.user.phone}">
                                    </div>

                                    <div class="single-input-item">
                                        <label for="order_note" class="required">Order Note</label>
                                        <textarea name="Note" id="order_note" placeholder="Order Note">${param.Note} </textarea>
                                    </div>

                                    <div hidden="" class="custom-control custom-radio">
                                        <input type="radio" id="payNoWallet" value="payNoWallet1" name="pay" checked="" class="custom-control-input">
                                        
                                    </div>
                                    <div hidden="" class="custom-control custom-radio">
                                        <input type="radio" id="payWallet" value="payWallet1" name="pay"  class="custom-control-input">
                                        
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="order-summary-details">
                            <h2>Your Order Summary</h2>
                            <div class="order-summary-content">
                                <div class="order-summary-table table-responsive text-center">
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>Products</th>
                                                <th>Total</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:if test="${not empty sessionScope.cart}">
                                                <c:forEach var="p" items="${sessionScope.cart}">
                                                    <tr>
                                                        <td><a href="#">${p.name}<strong> × ${p.quantity}</strong></a>
                                                        </td>
                                                        <td>$${p.price * p.quantity}</td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                        </tbody>
                                        <tfoot>
                                            <!--                                            <tr>
                                                                                            <td>Sub Total</td>
                                                                                            <td><strong>$${sessionScope.totalPrice}</strong></td>
                                                                                        </tr>-->
                                            <tr>
                                                <td>Payment method</td>
                                                <td class="d-flex justify-content-left">
                                                    <ul class="shipping-type">
                                                        <li>
                                                            <div class="custom-control custom-radio">
                                                                <input type="radio" id="pay1" name="pay" class="custom-control-input" checked="">
                                                                <label class="custom-control-label" >Payment upon receipt</label>
                                                            </div>
                                                        </li>
                                                        <li>
                                                            <div class="custom-control custom-radio">
                                                                <input type="radio" id="pay2" name="pay" class="custom-control-input">
                                                                <label class="custom-control-label" >Pay with wallet</label>
                                                            </div>
                                                        </li>
                                                        <!--                                                        <li>
                                                                                                                    <div class="custom-control custom-radio">
                                                                                                                        <input type="radio" id="payWallet" name="pay" class="custom-control-input">
                                                                                                                        <label class="custom-control-label" for="payWallet">Pay with wallet</label>
                                                                                                                    </div>
                                                                                                                </li>-->
                                                    </ul>
                                                </td>
                                            </tr>

                                        </tfoot>
                                    </table>
                                    <div class="form-group payment">
                                        <!--                                        <h4 class="deal-title">Payment Method</h4>
                                                                                <p>Check / Money order</p>
                                                                                <p>Credit Cart (saved)</p>
                                                                                <ul>
                                                                                    <li><label class="inline"><input class="form-check-input" type="checkbox"><span class="input"></span>Direct Bank
                                                                                            Transder</label></li>
                                                                                    <li><label class="inline"><input class="form-check-input" type="checkbox"><span class="input"></span>Cash on Delivery</label>
                                                                                    </li>
                                                                                    <li><label class="inline"><input class="form-check-input" type="checkbox"><span class="input"></span>Paypal</label>
                                                                                    </li>
                                                                                </ul>-->
                                        <!--                                        <p class="credit">You can pay with your credit<br> card if you don't have a paypal account</p>-->
                                        <span class="grand-total">Grand Total :  <span>$${sessionScope.totalPrice}</span></span>
                                        <p style="color: #0b5ed7">${requestScope.output}</p>
                                    </div>
                                    <button type="submit" onclick="submitCheckoutForm()" class="button-1">Place Order Now</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Chekout Page -->

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

        <script>
            document.getElementById('pay1').addEventListener('change', function () {
                document.getElementById('payNoWallet').checked = this.checked;
                document.getElementById('pay1').checked = this.checked;
            });

            document.getElementById('pay2').addEventListener('change', function () {
                document.getElementById('payWallet').checked = this.checked;
                document.getElementById('pay2').checked = this.checked;
            });

            function submitCheckoutForm() {
                document.getElementById('checkoutForm').submit();
            }
        </script>
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