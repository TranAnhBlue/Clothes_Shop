<%-- 
    Document   : create
    Created on : Oct 27, 2024, 12:08:18 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Tạo Bản Ghi Điểm Danh</title>
    </head>
    <body>
        <h1>Nhập thông tin điểm danh</h1>
        <form action="create" method="post">
            <label for="eid">Employee ID:</label>
            <input type="number" id="eid" name="eid" required><br><br>

            <label for="actualQuantity">Actual Quantity:</label>
            <input type="number" id="actualQuantity" name="actualQuantity" required><br><br>

            <label for="anpha">Anpha:</label>
            <input type="text" id="anpha" name="anpha" required><br><br>

            <label for="note">Note:</label>
            <textarea id="note" name="note"></textarea><br><br>

            <input type="submit" value="Save">
        </form>
    </body>
</html>
