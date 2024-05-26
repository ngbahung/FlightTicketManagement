package org.example.flightticketmanagement.Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseDriver {
    private static final String user = "qlcbdemo";
    private static final String password = "password";
    private static final String port = "1521";
    private static final String database = "orcl";
    private static final String url = "jdbc:oracle:thin:@localhost:" + port + ":" + database;
    public static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {
        Connection c = getConnection();
    }
}
