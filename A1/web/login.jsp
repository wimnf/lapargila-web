<%-- 
    Document   : login
    Created on : May 26, 2025, 9:29:07 PM
    Author     : User
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login - LaparGila</title>
  <link rel="icon" type="image/png" href="images/favicon.png">
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
    
    .login-container {
        max-width: 500px;
        margin: 20px;
        padding: 30px;
        background-color: white;
        border-radius: 15px;
        box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
        border: 1px solid #e0e0e0;
        text-align: center;
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
    label {
        display: block;
        text-align: left;
        margin-bottom: 8px;
        font-weight: 600;
        color: #616161; /* Medium grey */
    }
    
    input[type="email"],
    input[type="password"] {
        width: 100%;
        padding: 12px;
        margin-bottom: 20px;
        border: 2px solid #E0E0E0;
        border-radius: 8px;
        font-size: 16px;
        transition: all 0.3s;
        box-sizing: border-box;
    }
    
    input[type="email"]:focus,
    input[type="password"]:focus {
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
        margin-top: 10px;
    }
    
    input[type="submit"]:hover {
        background-color: #26A69A; /* Darker mint on hover */
        transform: translateY(-2px);
        box-shadow: 0 5px 15px rgba(77, 182, 172, 0.3);
    }
    
    .register-link {
        margin-top: 20px;
        color: #616161;
    }
    
    .register-link a {
        color: #4DB6AC;
        text-decoration: none;
        font-weight: 600;
    }
    
    .register-link a:hover {
        text-decoration: underline;
    }
  </style>
  <script>
    function validateForm() {
      var email = document.forms["loginForm"]["email"].value;
      var pwd = document.forms["loginForm"]["password"].value;
      if (!email || !pwd) {
        alert("Please enter email and password.");
        return false;
      }
      return true;
    }
  </script>
</head>
<body>
  <div class="login-container">
    <div class="logo">
    <img src="images/logo.png" alt="Lapar Gila Logo" style="max-width: 120px; margin-bottom: 10px;">
    <br>
    <span>LAPAR GILA</span>
</div>

    <form name="loginForm" action="LoginServlet" method="post" onsubmit="return validateForm()">
      <label>Email:</label>
      <input type="email" name="email" required placeholder="Enter your email">
      
      <label>Password:</label>
      <input type="password" name="password" required placeholder="Enter your password">
      
      <input type="submit" value="LOGIN">
    </form>
    
    <div class="register-link">
      New user? <a href="register.jsp">Register here</a>
    </div>
  </div>
</body>
</html>