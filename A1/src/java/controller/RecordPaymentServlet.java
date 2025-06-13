/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */

package controller;

import model.Payment;
import model.PaymentDAO;
import util.DBConnection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;

@WebServlet(name = "RecordPaymentServlet", urlPatterns = {"/RecordPaymentServlet"})
public class RecordPaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int orderId = Integer.parseInt(req.getParameter("order_id"));
            double amount = Double.parseDouble(req.getParameter("amount"));
            String method = req.getParameter("method");

            Payment payment = new Payment();
            payment.setOrderId(orderId);
            payment.setAmount(amount);
            payment.setMethod(method);
            payment.setStatus("paid"); // ✅ Automatically set as paid
            payment.setPaymentDate(LocalDateTime.now());

            try (Connection conn = DBConnection.getConnection()) {
                PaymentDAO dao = new PaymentDAO(conn);
                dao.insertPayment(payment);
            }

            // Redirect to user order page
            resp.sendRedirect("viewOrders.jsp");

        } catch (Exception e) {
            throw new ServletException("Payment failed", e);
        }
    }
}
