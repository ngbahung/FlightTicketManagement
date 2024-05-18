module org.example.flightticketmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires de.jensd.fx.glyphs.fontawesome;
    requires com.oracle.database.jdbc;
    requires java.sql;

    opens org.example.flightticketmanagement to javafx.fxml;

    exports org.example.flightticketmanagement;
    exports org.example.flightticketmanagement.Controllers;
    exports org.example.flightticketmanagement.Controllers.Admin;
    exports org.example.flightticketmanagement.Controllers.Manager;
    exports org.example.flightticketmanagement.Controllers.Staff;
    exports org.example.flightticketmanagement.Models;
    exports org.example.flightticketmanagement.Views;

}