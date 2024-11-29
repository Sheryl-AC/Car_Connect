package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBPropertyUtil {
    private static final String PROPERTY_FILE = "src/main/resources/db.properties";

    public static Properties loadDatabaseProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTY_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database properties", e);
        }
        return properties;
    }
    public static void main(String[]args) {
            Properties dbProperties = DBPropertyUtil.loadDatabaseProperties();
            String url = dbProperties.getProperty("db.url");
            //String user = dbProperties.getProperty("db.username");
            //String password = dbProperties.getProperty("db.password");

    }
    }