package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {
    public static Connection getConnection() throws SQLException {
        Properties dbProperties = DBPropertyUtil.loadDatabaseProperties();
        String url = dbProperties.getProperty("db.url");
        String user = dbProperties.getProperty("db.username");
        String password = dbProperties.getProperty("db.password");

        // Load the driver class
        try {
            Class.forName(dbProperties.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database driver not found", e);
        }

        // Establish and return the connection
        return DriverManager.getConnection(url, user, password);
    }
}