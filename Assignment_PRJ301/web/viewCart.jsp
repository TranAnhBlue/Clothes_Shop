
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map"%>
<%@page import="controller.session.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Store</title>
        <link rel="stylesheet" href="css/viewCart.css"/>
        <style>
            /* Phần thanh toán */
            #paymentSection {
                background-color: #ffffff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                margin-top: 20px;
                max-width: 600px;
                margin-left: auto;
                margin-right: auto;
            }

            #paymentSection h2 {
                text-align: center;
                color: #2c3e50;
                margin-bottom: 20px;
            }

            #paymentSection label {
                display: block;
                margin-top: 10px;
                font-weight: bold;
                color: #333;
            }

            #paymentSection input[type="text"],
            #paymentSection input[type="email"],
            #paymentSection input[type="month"],
            #paymentSection select {
                width: calc(100% - 20px);
                padding: 10px;
                margin: 5px 0;
                border: 1px solid #ddd;
                border-radius: 4px;
                box-sizing: border-box;
                font-size: 14px;
            }

            #paymentSection input[type="submit"] {
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 4px;
                padding: 10px 20px;
                cursor: pointer;
                font-size: 16px;
                width: 100%;
                margin-top: 10px;
            }

            #paymentSection input[type="submit"]:hover {
                background-color: #0056b3;
            }

            /* Định dạng cho các trường nhập liệu */
            #paymentSection input[type="text"]:focus,
            #paymentSection input[type="email"]:focus,
            #paymentSection input[type="month"]:focus,
            #paymentSection select:focus {
                border-color: #007bff;
                outline: none;
                box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
            }

            /* Responsive Design */
            @media (max-width: 768px) {
                #paymentSection {
                    padding: 15px;
                }

                #paymentSection input[type="submit"] {
                    font-size: 14px;
                }
            }

        </style>
        <script>
            // JavaScript function to show message after payment
            function showSuccessMessage() {
                console.log("Thanh toán thành công");
                alert("Thanh toán thành công! Cảm ơn bạn đã mua hàng.");
                // Clear cart from session (assuming this is done server-side)
                window.location.href = 'ClearCartController'; // This should redirect to a controller that clears the cart
            }
        </script>
        <style>
            /* Các định dạng CSS khác */
        </style>
    </head>
    <body>
        <h1>Your Cart includes:</h1>     
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart.items}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <form action="DispatchController">
                    <c:forEach var="entry" items="${cart.items}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${entry.key}</td>
                            <td>${entry.value}</td>
                            <td>
                                <input type="checkbox" name="chkItem" value="${entry.key}" />                               
                            </td>
                        </tr> 
                    </c:forEach>

                    <tr>
                        <td colspan="3">
                            <a href="productStore.html">Add more Items to your Cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove selected Items" name="btAction" />
                        </td>
                    </tr>
                </form>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty cart.items}">
        <h1>No cart exists</h1>
    </c:if>

    <!-- Phần thanh toán -->
    <section id="paymentSection">
        <h2>Payment Details</h2>
        <form action="productStore.html" method="POST" onsubmit="showSuccessMessage()">
            <!-- Các trường nhập liệu khác -->
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required /><br />

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required /><br />

            <label for="address">Shipping Address:</label>
            <input type="text" id="address" name="address" required /><br />

            <label for="paymentMethod">Payment Method:</label>
            <select id="paymentMethod" name="paymentMethod" required>
                <option value="creditCard">Credit Card</option>
                <option value="paypal">PayPal</option>
                <option value="bankTransfer">Bank Transfer</option>
            </select><br />

            <label for="cardNumber">Card Number:</label>
            <input type="text" id="cardNumber" name="cardNumber" required /><br />

            <label for="expiryDate">Expiry Date:</label>
            <input type="month" id="expiryDate" name="expiryDate" required /><br />

            <label for="cvv">CVV:</label>
            <input type="text" id="cvv" name="cvv" required /><br />

            <input type="submit" value="Proceed to Payment" />
        </form>
    </section>
</body>
<button onclick="window.location.href = 'home.jsp'" class="home-button">Home</button>
</html>
