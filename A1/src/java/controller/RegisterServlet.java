/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */

package controller;

import model.User;
import model.UserDAO;
import util.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Get all input fields from the registration form
        String name     = req.getParameter("name");
        String email    = req.getParameter("email");
        String password = req.getParameter("password");
        String phone    = req.getParameter("phone");
        String location = req.getParameter("location");

        // Create a User object and set values
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setLocation(location);

        try (Connection conn = DBConnection.getConnection()) {
            UserDAO dao = new UserDAO(conn);

            // Try to insert user into the database
            if (dao.registerUser(user)) {
                resp.sendRedirect("login.jsp");  // Success
            } else {
                resp.getWriter().println("Registration failed. Try again.");
            }
        } catch (Exception e) {
            throw new ServletException("Registration error", e);
        }
    }
}
