package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // newer driver
            Connection conn = DriverManager.getConnection('jdbc:mysql://mysql-iyu9.railway.internal:3306/railway?sslMode=REQUIRED&allowPublicKeyRetrieval=true&useSSL=true', 
                                              'root', 'ZUSUMHvlcUbhwUjJAMxblpdzTSnluvsC');
            System.out.println(conn);
            return conn;
            //mysql://root:ZUSUMHvlcUbhwUjJAMxblpdzTSnluvsC@mysql-iyu9.railway.internal:3306/railway
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
