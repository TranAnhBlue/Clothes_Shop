<%-- 
    Document   : register
    Created on : Jun 16, 2024, 4:22:47 PM
    Author     : chien
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>BulkShop - Electronics Shop HTML Template | Register</title>
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

        <!--offcanvas menu area end-->
        <!-- End Mobile Menu Area -->
        <!-- Start BreadCrumb Area -->
        <div class="breadcrumb-area pt-50 pb-50" style="background-image: url('assets/img/breadcrumb.jpg');">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb-content">
                            <h2>Register Account</h2>
                            <ul>
                                <li><a href="login">Login</a></li>
                                <li class="active">Register</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End BreadCrumb Area -->

        <!-- Start Login Register Form -->
        <div class="login-register-form pt-70 pb-70">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 offset-lg-3">
                        <div class="login-register-form-full">

                    <form id="registerForm" action="register" method="post">
    <input type="text" class="form-control" name="nameU" id="name" placeholder="Enter name" value="${param.nameU != null ? param.nameU : ''}">
    <input type="text" class="form-control" name="phoneU" id="phone" placeholder="Enter phone number" value="${param.phoneU != null ? param.phoneU : ''}" >
    <input type="email" class="form-control" name="emailU" id="email" placeholder="Enter your email" value="${param.emailU != null ? param.emailU : ''}">
    <input type="text" class="form-control" name="addressU" id="address" placeholder="Enter address" value="${param.addressU != null ? param.addressU : ''}">
    <div><label>Date of birth &nbsp;</label> <input type="date" class="form-control" name="dateU" id="date" ></div>
    <div style="margin-bottom: 20px; display: flex ">
        <div style="margin-right: 50px">Choose gender:</div>
        <div style="display: inline-block; margin-right: 10px">
            <input type="radio" name="genderU" id="male" value="1" checked="">
            <label for="male">Male</label>
        </div>
        <div style="display: inline-block; margin-left: 10px; margin-right: 10px">
            <input type="radio" name="genderU" id="female" value="2">
            <label for="female">Female</label>
        </div>
        <div style="display: inline-block; margin-left: 10px;">
            <input type="radio" name="genderU" id="other" value="3">
            <label for="other">Other</label>
        </div>
    </div>
    <input type="text" class="form-control" name="usernameU" id="username" placeholder="Enter username" value="${param.usernameU != null ? param.usernameU : ''}">
    <input type="password" class="form-control" name="passU" id="pass" placeholder="Enter password" value="${param.passU != null ? param.passU : ''}">
    <input type="password" class="form-control" name="repassU" id="repass" placeholder="Confirm password" value="${param.repassU != null ? param.repassU : ''}">

    <p style="color: #0061f2;">${requestScope.suc}</p>
    <button class="button-1" id="resetBtn" type="button">Create Account</button>
</form>



                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Login Register Form -->

        <div class="scroll-area">
            <i class="fa fa-angle-up"></i>
        </div>

        <!-- Js File -->
        <script src="assets/js/jquery-3.5.1.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/script.js"></script>

<script>
    document.getElementById('resetBtn').addEventListener('click', function(event) {
        var name = document.getElementById('name').value.trim();
        var phone = document.getElementById('phone').value.trim();
        var email = document.getElementById('email').value.trim();
        var address = document.getElementById('address').value.trim();
        var date = document.getElementById('date').value.trim();
        var username = document.getElementById('username').value.trim();
        var pass = document.getElementById('pass').value.trim();
        var repass = document.getElementById('repass').value.trim();
        
//        var nameRegex = /^[A-Za-z\s]+$/;
        var phoneRegex = /^0[0-9]{9,10}$/;
        var emailRegex =  /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        var addressRegex = /^[A-Za-z0-9\s,.'-]{3,}$/;
        var usernameRegex = /^[A-Za-z0-9_\.]+$/;
        var passRegex =/^(?=.*[a-z])(?=.*[A-Z]).{8,}$/;

        var isValid = true;

        if (!name) {
            alert('Name cannot be empty');
            isValid = false;
        } 
//        else if (!nameRegex.test(name)) {
//            alert('Invalid name');
//            isValid = false;
//        }
        else if (!phone) {
            alert('Phone cannot be empty');
            isValid = false;
        } else if (!phoneRegex.test(phone)) {
            alert('Phone must be 10-11 digits and start with 0');
            isValid = false;
        } else if (!email) {
            alert('Email cannot be empty');
            isValid = false;
        } else if (!emailRegex.test(email)) {
            alert('Invalid email');
            isValid = false;
        } else if (!address) {
            alert('Address cannot be empty');
            isValid = false;
        } else if (!addressRegex.test(address)) {
            alert('Invalid address');
            isValid = false;
        } else if (!date) {
            alert('Date of birth cannot be empty');
            isValid = false;
        } else if (!username) {
            alert('Username cannot be empty');
            isValid = false;
        } else if (!usernameRegex.test(username)) {
            alert('Invalid username');
            isValid = false;
        } else if (!pass) {
            alert('Password cannot be empty');
            isValid = false;
        } else if (!passRegex.test(pass)) {
            alert('Password must be at least 8 characters, uppercase and lowercase');
            isValid = false;
        } else if (!repass) {
            alert('Re-enter password cannot be empty');
            isValid = false;
        } else if (pass !== repass) {
            alert('Passwords do not match');
            isValid = false;
        }

        if (isValid) {
            // Submit the form
            document.getElementById('registerForm').submit();
        }
    });
</script>



    </body>
</html>
