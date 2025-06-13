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
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password, phone, location) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getLocation());
            return ps.executeUpdate() > 0;
        }
    }

    //Retrieve for login
    public User loginUser(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setUserId(rs.getInt("user_id"));
                    u.setName(rs.getString("name"));
                    u.setEmail(rs.getString("email"));
                    u.setPassword(rs.getString("password"));
                    u.setPhone(rs.getString("phone"));
                    u.setLocation(rs.getString("location"));
                    return u;
                }
            }
        }
        return null;
    }

    // Update user (including phone & location)
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET name=?, email=?, password=?, phone=?, location=? WHERE user_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getLocation());
            ps.setInt(6, user.getUserId());
            ps.executeUpdate();
        }
    }

    // ✅ Delete user (after deleting related orders)
    public void deleteUser(int id) throws SQLException {
    // Step 1: Get order IDs of this user
    String getOrders = "SELECT order_id FROM orders WHERE user_id=?";
    List<Integer> orderIds = new ArrayList<>();
    try (PreparedStatement ps = conn.prepareStatement(getOrders)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                orderIds.add(rs.getInt("order_id"));
            }
        }
    }

    // Step 2: Delete related payments
    String deletePayments = "DELETE FROM payments WHERE order_id=?";
    try (PreparedStatement ps = conn.prepareStatement(deletePayments)) {
        for (int orderId : orderIds) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
        }
    }

    // Step 3: Delete orders
    String deleteOrders = "DELETE FROM orders WHERE user_id=?";
    try (PreparedStatement ps = conn.prepareStatement(deleteOrders)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    // Step 4: Delete user
    String deleteUser = "DELETE FROM users WHERE user_id=?";
    try (PreparedStatement ps = conn.prepareStatement(deleteUser)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}

}
