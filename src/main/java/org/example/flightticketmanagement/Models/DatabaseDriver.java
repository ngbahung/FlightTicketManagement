package org.example.flightticketmanagement.Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseDriver {
    private static final String user = "TIKIMANA";
    private static final String password = "password";
    private static final String port = "1521";
    private static final String database = "TRINH";
    private static final String url = "jdbc:oracle:thin:@localhost:" + port + ":" + database;
    public Connection con;

    public Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, user, password);
            if (con != null) {
                System.out.println("Successfully connected to database");
            } else {
                System.out.println("Error connecting to database");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
