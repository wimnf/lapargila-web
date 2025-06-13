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
import java.util.*;
import java.time.LocalDateTime;

public class OrderDAO {
    private Connection conn;

    public OrderDAO(Connection conn) {
        this.conn = conn;
    }

    // Insert new order
    public void insertOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (user_id, menu_id, quantity, status, order_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getMenuId());
            ps.setInt(3, order.getQuantity());
            ps.setString(4, order.getStatus());
            ps.setTimestamp(5, Timestamp.valueOf(order.getOrderDate()));
            ps.executeUpdate();
        }
    }

    // Retrieve orders for a specific user
    public List<Order> getOrdersByUser(int userId) throws SQLException {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setMenuId(rs.getInt("menu_id"));
                order.setQuantity(rs.getInt("quantity"));
                order.setStatus(rs.getString("status"));
                order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
                list.add(order);
            }
        }
        return list;
    }

    // Update order status
    public void updateOrderStatus(int orderId, String status) throws SQLException {
        String sql = "UPDATE orders SET status = ? WHERE order_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        }
    }

    // Get all orders (for admin)
    public List<Order> getAllOrders() throws SQLException {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setMenuId(rs.getInt("menu_id"));
                order.setQuantity(rs.getInt("quantity"));
                order.setStatus(rs.getString("status"));
                order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
                list.add(order);
            }
        }
        return list;
    }
    
    
       public Order getOrderById(int orderId) throws SQLException {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Order order = new Order();
            order.setOrderId(rs.getInt("order_id"));
            order.setUserId(rs.getInt("user_id"));
            order.setMenuId(rs.getInt("menu_id"));
            order.setQuantity(rs.getInt("quantity"));
            order.setStatus(rs.getString("status"));
            order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
            return order;
        }
    }
    return null;
}

}

