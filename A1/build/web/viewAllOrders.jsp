<%--
    Document   : viewAllOrders
    Created on : Jun 4, 2025, 5:55:30 PM
    Author     : User
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.*, model.*, util.DBConnection" %>
<%@ page session="true" %>

<%
    User user = (User) session.getAttribute("currentUser");
    if (user == null || !user.getEmail().equals("admin@example.com")) {
        response.sendRedirect("login.jsp");
        return;
    }

    Connection conn = DBConnection.getConnection();
    OrderDAO orderDAO = new OrderDAO(conn);
    MenuDAO menuDAO = new MenuDAO(conn);
    PaymentDAO paymentDAO = new PaymentDAO(conn);
    List<Order> orderList = orderDAO.getAllOrders();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Orders - Admin View - LaparGila</title>
    <link rel="icon" type="image/png" href="images/favicon.png">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #E0F7FA; /* Soft mint background */
            margin: 0;
            padding: 0;
            color: #333;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .navbar {
            background-color: #4DB6AC; /* Mint accent */
            color: white;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            box-sizing: border-box;
        }

        .navbar-brand {
            font-size: 1.5em;
            font-weight: 700;
            color: white;
            text-decoration: none;
        }

        .navbar-links {
            list-style: none;
            padding: 0;
            margin: 0;
            display: flex;
            align-items: center;
        }

        .navbar-links li {
            margin-left: 20px;
        }

        .navbar-links a {
            color: white;
            text-decoration: none;
            font-weight: 500;
            transition: color 0.3s;
        }

        .navbar-links a:hover {
            color: #26A69A; /* Darker mint on hover */
        }

        .navbar-profile {
            display: flex;
            align-items: center;
        }

        .navbar-profile span {
            margin-right: 10px;
            font-weight: 600;
        }

        .navbar-profile a {
            color: white;
            text-decoration: none;
            margin-left: 10px;
            padding: 8px 12px;
            border-radius: 5px;
            border: 1px solid white;
            transition: background-color 0.3s, color 0.3s;
        }

        .navbar-profile a:hover {
            background-color: #26A69A; /* Darker mint on hover */
        }

        .content {
            padding: 20px;
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 100%;
            box-sizing: border-box;
        }

        h2 {
            color: #264653;
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 90%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: white;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px 15px;
            text-align: left;
        }

        th {
            background-color: #4DB6AC; /* Mint accent */
            color: white;
            font-weight: 600;
            text-transform: uppercase;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        td a {
            color: #4DB6AC;
            text-decoration: none;
            transition: color 0.3s;
        }

        td a:hover {
            color: #26A69A;
        }

        td.paid {
            color: green;
            font-weight: bold;
        }

        td.unpaid {
            color: red;
            font-weight: bold;
        }

        .footer {
            text-align: center;
            padding: 20px;
            background-color: #f4f4f4;
            color: #777;
            border-top: 1px solid #ddd;
            width: 100%;
            box-sizing: border-box;
        }

        .admin-navigation {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }

        .admin-navigation a {
            display: inline-block;
            margin: 0 10px;
            padding: 10px 15px;
            background-color: #4DB6AC; /* Mint accent */
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .admin-navigation a:hover {
            background-color: #26A69A; /* Darker mint on hover */
        }
    </style>
</head>

<body>

<nav class="navbar">
    <a href="#" class="navbar-brand">LaparGila</a>
    <ul class="navbar-links">
        <li><a href="menu.jsp">Menu</a></li>
        <li><a href="viewAllOrders.jsp">View All Orders</a></li>
    </ul>
    <div class="navbar-profile">
        <span>Welcome, <%= user.getName() %></span>
        <a href="editProfile.jsp">My Profile</a>
        <a href="logout.jsp" style="color:red;">Logout</a>
    </div>
</nav>

<div class="content">
    <h2>All Orders</h2>

    <div class="admin-navigation">
        <a href="adminDashboard.jsp">Dashboard</a>
    </div>

    <table>
        <thead>
            <tr>
                <th>Order ID</th>
                <th>User ID</th>
                <th>Menu Item</th>
                <th>Quantity</th>
                <th>Status</th>
                <th>Order Date</th>
                <th>Payment</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (Order o : orderList) {
                MenuItem item = menuDAO.getMenuById(o.getMenuId());
                boolean isPaid = paymentDAO.isOrderPaid(o.getOrderId());
        %>
            <tr>
                <td><%= o.getOrderId() %></td>
                <td><%= o.getUserId() %></td>
                <td><%= item != null ? item.getName() : "Unknown" %></td>
                <td><%= o.getQuantity() %></td>
                <td><%= o.getStatus() %></td>
                <td><%= o.getOrderDate() %></td>
                <td class="<%= isPaid ? "paid" : "unpaid" %>">
                    <%= isPaid ? "Paid" : "Unpaid" %>
                </td>
                <td>
                    <a href="editOrder.jsp?id=<%= o.getOrderId() %>">Edit</a>
                </td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<footer class="footer">
    &copy; 2024 LaparGila. All rights reserved.
</footer>

</body>
</html>