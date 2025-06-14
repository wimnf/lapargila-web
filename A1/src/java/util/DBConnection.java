package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // newer driver
            return DriverManager.getConnection('jdbc:mysql://ballast.proxy.rlwy.net:52598/railway', 
                                              'root', 'ZUSUMHvlcUbhwUjJAMxblpdzTSnluvsC');
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
