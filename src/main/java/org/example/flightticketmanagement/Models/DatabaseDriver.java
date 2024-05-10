package org.example.flightticketmanagement.Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseDriver {
    public Connection conn;

    public DatabaseDriver() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:TRINH","sys as sysdba","Trinh@3004");
            if (conn != null) {
                System.out.println("Successfully connected to database");
            } else {
                System.out.println("Error connecting to database");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}