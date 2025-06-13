/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
package controller;

import util.DBConnection;
import model.OrderDAO;
import model.Order;

import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "PlaceOrderServlet", urlPatterns = {"/PlaceOrderServlet"})
public class PlaceOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int menuId = Integer.parseInt(req.getParameter("menu_id"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        HttpSession session = req.getSession();
        model.User user = (model.User) session.getAttribute("currentUser");

        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setMenuId(menuId);
        order.setQuantity(quantity);
        order.setStatus("pending");
        order.setOrderDate(LocalDateTime.now());

        try (Connection conn = DBConnection.getConnection()) {
            OrderDAO dao = new OrderDAO(conn);
            dao.insertOrder(order);
            resp.sendRedirect("viewOrders.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

