/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */

package controller;

import model.OrderDAO;
import util.DBConnection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "UpdateOrderServlet", urlPatterns = {"/UpdateOrderServlet"})
public class UpdateOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(req.getParameter("order_id"));
        String status = req.getParameter("status");

        try (Connection conn = DBConnection.getConnection()) {
            OrderDAO dao = new OrderDAO(conn);
            dao.updateOrderStatus(orderId, status);
            resp.sendRedirect("viewAllOrders.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

