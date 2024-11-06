<%-- 
    Document   : login
    Created on : Jun 12, 2024, 7:13:08 PM
    Author     : chien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  class="no-js" lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title> Login</title>
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/normalize.css">
        <link rel="stylesheet" href="assets/css/style.css">

    </head>
    <body>


        <div class="breadcrumb-area pt-50 pb-50" style="background-image: url('assets/img/breadcrumb.jpg');">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb-content">
                            <h2>Login</h2>
                            <ul>
                                <li><a href="Home">Home</a></li>
                                <li class="active">Login</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Start Login Register Form -->
        <div class="login-register-form pt-60 pb-60" style="justify-content: center ; align-items:center; height: 70vh; ">

            <div class="container">

                <div class="row">
                    <div class="col-lg-5 offset-lg-4 text-center">
                        <div class="login-register-form-full"  style="background-color: #ffffff; padding: 30px; border-radius: 8px; box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);">
                            <h3 style="margin-bottom: 35px"><img src="assets/img/logo3.jpg"></h3>
                            <form action="login" method="post">
                                <input type="text" class="form-control" id="email"  value="${username}" name="username" placeholder="Your Username" required>
                                <div id="email-error" class="error"></div>
                                <input type="password" class="form-control"  value="${password}" name="password" placeholder="Your Password" required>
                                <div class="row">
                                    <div class="col-6">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" ${(cookie.remem eq 'ON')?"checked":""} name="rem" style="transform: scale(0.75);" value="" id="flexCheckDefault">
                                                <label class="form-check-label" >Remember me</label></div>
                                        
                                    </div>
                                    <div class="col-6 text-right">
                                        <a href="resetPass">Forgot password?</a>
                                    </div>
                                </div>
                                            <p>${requestScope.err}</p>
                                <button class="button-1" type="submit">Log In</button>
                            </form>
                            <p>Don't Have an Account? Please <a href="register">Sign up now</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Login Register Form -->





        <!-- Js File -->

        <script src="assets/js/jquery-3.5.1.min.js"></script>

        <script src="assets/js/script.js"></script>
        <script>
//            function checkEmailValidity() {
//                const input = document.getElementById("email");
//                const email = input.value.trim();
//
//                if (email === "") {
//                    input.setCustomValidity("Please input your email!");
//                } else if (!email.endsWith("@gmail.com")) {
//                    input.setCustomValidity("Please enter an email ending with @gmail.com");
//                } else {
//                    input.setCustomValidity("");
//                }
//            }
        </script>
    </body>
</html>