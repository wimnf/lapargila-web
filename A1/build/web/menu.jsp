<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.*, model.*, util.DBConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="model.User" %>

<%
    User user = (User) session.getAttribute("currentUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Connection conn = DBConnection.getConnection();
    if (conn == null) {
        request.setAttribute("errorMessage", "Database connection failed. Check DBConnection.java and MySQL.");
    }

    MenuDAO menuDAO = null;
    List<MenuItem> menuList = null;

    if (conn != null) {
        menuDAO = new MenuDAO(conn);
        menuList = menuDAO.getAllMenuItems();
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu - LaparGila</title>
    <link rel="icon" type="image/png" href="images/favicon.png">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #E0F7FA;
            margin: 0;
            padding: 0;
            color: #333;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .navbar {
            background-color: #4DB6AC;
            color: white;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            display: flex;
            justify-content: space-between;
            align-items: center;
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
            color: #26A69A;
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
            background-color: #26A69A;
        }

        .content {
            padding: 20px;
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h1 {
            color: #264653;
            text-align: center;
            margin-bottom: 20px;
        }

        .menu-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            width: 90%;
            max-width: 1200px;
        }

        .menu-card {
            width: 250px;
            margin: 15px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            transition: transform 0.3s ease;
        }

        .menu-card:hover {
            transform: translateY(-5px);
        }

        .menu-card img {
            width: 100px;
            height: 100px;
            object-fit: cover;
            display: block;
            margin: 10px auto;
            border-radius: 5px;
        }

        .menu-card-content {
            padding: 15px;
            text-align: center;
        }

        .menu-card h3 {
            color: #264653;
            margin-bottom: 8px;
        }

        .menu-card p {
            color: #666;
            font-size: 0.9em;
            margin-bottom: 12px;
        }

        .menu-card .price {
            font-weight: bold;
            color: #4DB6AC;
            margin-bottom: 12px;
        }

        .menu-card .actions {
            display: flex;
            justify-content: space-around;
            padding: 0 15px 15px;
        }

        .menu-card .actions a,
        .menu-card .actions button {
            padding: 8px 12px;
            border-radius: 5px;
            text-decoration: none;
            color: white;
            background-color: #4DB6AC;
            transition: background-color 0.3s;
            border: none;
            cursor: pointer;
        }

        .menu-card .actions a:hover,
        .menu-card .actions button:hover {
            background-color: #26A69A;
        }

        .footer {
            text-align: center;
            padding: 20px;
            background-color: #f4f4f4;
            color: #777;
            border-top: 1px solid #ddd;
        }

        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<nav class="navbar">
    <a href="#" class="navbar-brand">LaparGila</a>
    <ul class="navbar-links">
    <li><a href="menu.jsp">Menu</a></li>
    <% if (user.getEmail().equals("admin@example.com")) { %>
        <li><a href="addMenu.jsp">Add Menu Item</a></li>
        <li><a href="viewAllOrders.jsp">View All Orders</a></li>
    <% } else { %>
        <li><a href="placeOrder.jsp">Place Order</a></li>
        <li><a href="viewOrders.jsp">My Orders</a></li>
    <% } %>
</ul>

    <div class="navbar-profile">
        <span>Welcome, <%= user.getName() %></span>
        <a href="editProfile.jsp">My Profile</a>
        <a href="logout.jsp" style="color:red;">Logout</a>
    </div>
</nav>

<div class="content">
    <h1>LaparGila Food Menu</h1>

    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <div class="error-message">
            <%= errorMessage %>
        </div>
    <%
        }
    %>

    <div class="menu-container">
        <%
            if (menuList != null) {
                for (MenuItem item : menuList) {
        %>
            <div class="menu-card">
                <img src="images/<%= item.getName().replaceAll("\\s+", "").toLowerCase() %>.jfif" alt="<%= item.getName() %>">
                <div class="menu-card-content">
                    <h3><%= item.getName() %></h3>
                    <p><%= item.getDescription() %></p>
                    <p class="price">RM <%= String.format("%.2f", item.getPrice()) %></p>
                    <div class="actions">
                        <% if (user.getEmail().equals("admin@example.com")) { %>
                            <a href="editMenu.jsp?id=<%= item.getMenuId() %>">Edit</a>
                            <a href="DeleteMenuServlet?id=<%= item.getMenuId() %>" onclick="return confirm('Delete this item?')">Delete</a>
                        <% } else { %>
                            <form action="placeOrder.jsp" method="get" style="display:inline;">
                                <input type="hidden" name="menu_id" value="<%= item.getMenuId() %>">
                                <button type="submit">Order</button>
                            </form>
                        <% } %>
                    </div>
                </div>
            </div>
        <%
                }
            } else {
        %>
            <p>No menu items found.</p>
        <%
            }
        %>
    </div>
</div>

<footer class="footer">
    &copy; 2024 LaparGila. All rights reserved.
</footer>

</body>
</html>
