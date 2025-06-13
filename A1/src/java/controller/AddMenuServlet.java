/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
package controller;

import model.MenuItem;
import model.MenuDAO;
import util.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet; // ✅ Required for mapping
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "AddMenuServlet", urlPatterns = {"/AddMenuServlet"})
public class AddMenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String desc = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));

        MenuItem item = new MenuItem();
        item.setName(name);
        item.setDescription(desc);
        item.setPrice(price);

        try (Connection conn = DBConnection.getConnection()) {
            MenuDAO dao = new MenuDAO(conn);
            if (dao.addItem(item)) {
                resp.sendRedirect("menu.jsp");
            } else {
                resp.getWriter().println("Failed to add menu item.");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
