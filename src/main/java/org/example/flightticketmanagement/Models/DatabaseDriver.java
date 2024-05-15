package org.example.flightticketmanagement.Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseDriver {
    public Connection conn;

    public Connection getDBDriver() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","fly","password");
            if (conn != null) {
                System.out.println("Successfully connected to database");
            } else {
                System.out.println("Error connecting to database");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void main(String[] args) {
        DatabaseDriver driver = new DatabaseDriver();
        Connection connectDB = driver.getDBDriver();
    }
}
