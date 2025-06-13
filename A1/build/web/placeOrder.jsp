<%--
    Document   : placeOrder
    Created on : Jun 4, 2025, 4:59:06 PM
    Author     : User
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.*, model.*, util.DBConnection" %>
<%@ page session="true" %>
<%@ page import="model.User" %>

<%
    User user = (User) session.getAttribute("currentUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Connection conn = DBConnection.getConnection();
    MenuDAO menuDAO = new MenuDAO(conn);
    List<MenuItem> menuList = menuDAO.getAllMenuItems();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Place Order - LaparGila</title>
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
            width: 100%; /* Full width */
            box-sizing: border-box; /* Include padding in width */
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
            width: 100%; /* Full width */
            box-sizing: border-box; /* Include padding in width */
        }

        h2 {
            color: #264653;
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            max-width: 600px;
            width: 100%;
            padding: 30px;
            background-color: white;
            border-radius: 15px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
            border: 1px solid #e0e0e0;
            box-sizing: border-box;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #616161;
        }

        select,
        input[type="number"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 2px solid #E0E0E0;
            border-radius: 8px;
            font-size: 16px;
            transition: all 0.3s;
            box-sizing: border-box;
        }

        select:focus,
        input[type="number"]:focus {
            border-color: #4DB6AC;
            outline: none;
            box-shadow: 0 0 0 3px rgba(77, 182, 172, 0.2);
        }

        input[type="submit"] {
            background-color: #4DB6AC;
            color: white;
            border: none;
            padding: 14px 20px;
            width: 100%;
            border-radius: 8px;
            font-size: 18px;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        input[type="submit"]:hover {
            background-color: #26A69A;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(77, 182, 172, 0.3);
        }

        p {
            margin-top: 20px;
            text-align: center;
        }

        p a {
            color: #4DB6AC;
            text-decoration: none;
            font-weight: 500;
            transition: color 0.3s;
        }

        p a:hover {
            color: #26A69A;
        }

        .footer {
            text-align: center;
            padding: 20px;
            background-color: #f4f4f4;
            color: #777;
            border-top: 1px solid #ddd;
            width: 100%; /* Full width */
            box-sizing: border-box; /* Include padding in width */
        }
    </style>
</head>
<body>

<nav class="navbar">
    <a href="#" class="navbar-brand">LaparGila</a>
    <ul class="navbar-links">
        <li><a href="menu.jsp">Menu</a></li>
        <li><a href="viewOrders.jsp">My Orders</a></li>
    </ul>
    <div class="navbar-profile">
        <span>Welcome, <%= user.getName() %></span>
        <a href="editProfile.jsp">My Profile</a>
        <a href="logout.jsp" style="color:red;">Logout</a>
    </div>
</nav>

<div class="content">
    <h2>Place Your Order</h2>

    <form action="PlaceOrderServlet" method="post">
        <label for="menu_id">Select Item:</label>
        <select name="menu_id" required>
            <% for (MenuItem item : menuList) { %>
                <option value="<%= item.getMenuId() %>">
                    <%= item.getName() %> - RM<%= String.format("%.2f", item.getPrice()) %>
                </option>
            <% } %>
        </select>

        <label for="quantity">Quantity:</label>
        <input type="number" name="quantity" min="1" required>

        <input type="submit" value="Place Order">
    </form>

    <p><a href="menu.jsp">Back to Menu</a></p>
</div>

<footer class="footer">
    &copy; 2024 LaparGila. All rights reserved.
</footer>

</body>
</html>
