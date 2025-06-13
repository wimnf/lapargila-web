 <%--
    Document   : register
    Created on : May 26, 2025, 9:26:49 PM
    Author     : User
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - LaparGila</title>
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
            justify-content: center;
            align-items: center;
        }

        form {
            max-width: 500px;
            margin: 20px;
            padding: 30px;
            background-color: white;
            border-radius: 15px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
            border: 1px solid #e0e0e0;
            box-sizing: border-box;
            width: 100%; /* Added for responsiveness */
        }

        .logo {
            text-align: center;
            margin-bottom: 20px;
        }

        .logo span {
            font-size: 3em;
            font-weight: 900;
            color: #424242; /* Dark grey */
            letter-spacing: -1px;
        }

        .food-icon {
            display: block;
            text-align: center;
            font-size: 2em;
            margin-bottom: 10px;
            color: #4DB6AC; /* Mint accent */
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #616161; /* Medium grey */
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        textarea {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 2px solid #E0E0E0;
            border-radius: 8px;
            font-size: 16px;
            transition: all 0.3s;
            box-sizing: border-box;
        }

        input[type="text"]:focus,
        input[type="email"]:focus,
        input[type="password"]:focus,
        textarea:focus {
            border-color: #4DB6AC; /* Mint accent */
            outline: none;
            box-shadow: 0 0 0 3px rgba(77, 182, 172, 0.2);
        }

        input[type="submit"] {
            background-color: #4DB6AC; /* Mint button */
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
            background-color: #26A69A; /* Darker mint on hover */
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(77, 182, 172, 0.3);
        }

        a {
            color: #4DB6AC;
            text-decoration: none;
            font-weight: 500;
            transition: color 0.3s;
        }

        a:hover {
            color: #26A69A;
        }
    </style>
</head>
<body>

<form action="RegisterServlet" method="post">
    <div class="logo">
    <img src="images/logo.png" alt="Lapar Gila Logo" style="max-width: 120px; margin-bottom: 10px;">
    <br>
        <span>LAPAR GILA</span>
    </div>

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" required>

    <label for="location">Location:</label>
    <textarea id="location" name="location" rows="3" required></textarea>

    <input type="submit" value="Register">

    <p style="text-align: center; margin-top: 20px;">
        Already have an account? <a href="login.jsp">Login</a>
    </p>
</form>

</body>
</html>