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

public class MenuDAO {
    private Connection conn;

    public MenuDAO(Connection conn) {
        this.conn = conn;
    }

    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuList = new ArrayList<>();
        String sql = "SELECT * FROM menu_items";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MenuItem item = new MenuItem();
                item.setMenuId(rs.getInt("menu_id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                menuList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuList;
    }
    
    // inside MenuDAO class
public boolean addItem(MenuItem item) throws SQLException {
    String sql = "INSERT INTO menu_items (name, description, price) VALUES (?, ?, ?)";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, item.getName());
        ps.setString(2, item.getDescription());
        ps.setDouble(3, item.getPrice());
        return ps.executeUpdate() > 0;
    }
}

public void updateMenuItem(MenuItem item) throws SQLException {
    String sql = "UPDATE menu_items SET name=?, description=?, price=? WHERE menu_id=?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, item.getName());
        ps.setString(2, item.getDescription());
        ps.setDouble(3, item.getPrice());
        ps.setInt(4, item.getMenuId());
        ps.executeUpdate();
    }
}


public void deleteMenuItem(int id) throws SQLException {
    // Step 1: Delete payments linked to orders of this menu
    String deletePayments = "DELETE FROM payments WHERE order_id IN (SELECT order_id FROM orders WHERE menu_id=?)";
    try (PreparedStatement ps = conn.prepareStatement(deletePayments)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    // Step 2: Delete orders linked to the menu
    String deleteOrders = "DELETE FROM orders WHERE menu_id=?";
    try (PreparedStatement ps = conn.prepareStatement(deleteOrders)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    // Step 3: Finally delete the menu item
    String deleteMenu = "DELETE FROM menu_items WHERE menu_id=?";
    try (PreparedStatement ps = conn.prepareStatement(deleteMenu)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}




public MenuItem getMenuById(int id) {
    MenuItem item = null;
    String sql = "SELECT * FROM menu_items WHERE menu_id=?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                item = new MenuItem();
                item.setMenuId(rs.getInt("menu_id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return item;
}




}
