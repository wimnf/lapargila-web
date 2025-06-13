/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */

package model;

import java.sql.*;
import java.time.LocalDateTime;

public class PaymentDAO {
    private Connection conn;

    public PaymentDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertPayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO payments (order_id, amount, method, status, payment_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, payment.getOrderId());
            ps.setDouble(2, payment.getAmount());
            ps.setString(3, payment.getMethod());
            ps.setString(4, payment.getStatus());
            ps.setTimestamp(5, Timestamp.valueOf(payment.getPaymentDate()));
            ps.executeUpdate();
        }
    }
    
    public boolean isOrderPaid(int orderId) throws SQLException {
    String sql = "SELECT COUNT(*) FROM payments WHERE order_id = ? AND status = 'paid'";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    }
    return false;
}

}
