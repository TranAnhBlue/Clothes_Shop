<%-- 
    Document   : home
    Created on : Oct 22, 2024, 9:53:35 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        <link rel="stylesheet" href="home.css">
    </head>
    <body>
        <header class="header">
            <div class="container">
                <h1>Welcome, ${account.getUsername()}!</h1>
                <nav class="navbar">
                    <ul>
                        <li><a href="employee/list">ManageEmployees</a></li>
                        <li><a href="employee/filter">FilterEmployee</a></li>
                        <li><a href="productionplan/list">ListProductionPlan</a></li>
                        <li><a href="productionplan/create">CreateProductionPlan</a></li>
                        <li><a href="productionplan/filter">FilterProductionPlan</a></li>
                        <li><a href="shift/list">ManageShiftsAndSalaries</a></li>
                        <li><a href="logout" class="logout-btn">Logout</a></li>
                    </ul>
                </nav>
            </div>
        </header>
        <main class="main-content">
            <div class="container">
                <h2>Dashboard</h2>
                <div class="dashboard-summary">
                    <div class="summary-item">
                        <h3>Total Employees</h3>
                        <a href="employee/list">37</a>
<!--                        <p>${employeeCount}</p>-->  
                    </div>
                    <div class="summary-item">
                        <h3>Active Production Plans</h3>
                        <a href="productionplan/list">9</a>
                    </div>
                    <div class="summary-item">
                        <h3>Shifts Today</h3>
                        <a href="shift/list">3</a>
                    </div>
                </div>

                <p>Select a section from the menu above to manage the system.</p>

            </div>
        </main>
        <footer class="footer">
            <div class="container">
                <p>&copy; PRJ301 - Assignment</p>
            </div>
        </footer>
    </body>
</html>