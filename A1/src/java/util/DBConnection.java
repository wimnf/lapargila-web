package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // newer driver
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/lapargila_db", // match your database name
                "root",                                    // MySQL username
                "admin"                                          // MySQL password (blank by default)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
