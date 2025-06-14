package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // newer driver
            return DriverManager.getConnection(
                "jdbc:mysql://mysql-iyu9.railway.internal:3306/railway", 
                "root",                                    // MySQL username
                "ZUSUMHvlcUbhwUjJAMxblpdzTSnluvsC"                                          // MySQL password (blank by default)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
