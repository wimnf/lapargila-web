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

@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/UpdateUserServlet"})
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. Get input values from form
        int id = Integer.parseInt(req.getParameter("user_id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pwd = req.getParameter("password");
        String phone = req.getParameter("phone");
        String location = req.getParameter("location");

        // 2. Keep old password if none entered
        HttpSession session = req.getSession();
        User oldUser = (User) session.getAttribute("currentUser");
        if (pwd == null || pwd.isEmpty()) {
            pwd = oldUser.getPassword();
        }

        // 3. Store data in User object
        User user = new User();
        user.setUserId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(pwd);
        user.setPhone(phone);
        user.setLocation(location);  // ✅ add this line

        try (Connection conn = DBConnection.getConnection()) {
            UserDAO dao = new UserDAO(conn);
            dao.updateUser(user);

            // 4. Save updated user to session
            session.setAttribute("currentUser", user);

            // 5. Success message
            session.setAttribute("profileUpdated", "Profile updated successfully.");

            // 6. Redirect
            resp.sendRedirect("editProfile.jsp");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
