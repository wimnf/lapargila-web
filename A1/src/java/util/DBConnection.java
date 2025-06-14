package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // newer driver
            String user = System.getenv("MYSQLHOST");
            System.out.println(user);
            return DriverManager.getConnection("jdbc:mysql://mysql-iyu9.railway.internal:3306/railway?useSSL=false", 
                                              "root", "ZUSUMHvlcUbhwUjJAMxblpdzTSnluvsC");
            
            //mysql://root:ZUSUMHvlcUbhwUjJAMxblpdzTSnluvsC@mysql-iyu9.railway.internal:3306/railway
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
