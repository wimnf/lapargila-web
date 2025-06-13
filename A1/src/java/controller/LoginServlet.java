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

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String pwd = req.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            UserDAO dao = new UserDAO(conn);
            User user = dao.loginUser(email, pwd);

            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("currentUser", user);

                if (user.getEmail().equals("admin@example.com")) {
                    resp.sendRedirect("adminDashboard.jsp");  
                } else {
                    resp.sendRedirect("menu.jsp");            
                }

            } else {
                resp.getWriter().println("Invalid email or password.");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
