module org.example.flightticketmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;


    opens org.example.flightticketmanagement to javafx.fxml;

    exports org.example.flightticketmanagement;
    exports org.example.flightticketmanagement.Controllers;
    exports org.example.flightticketmanagement.Controllers.Admin;
    exports org.example.flightticketmanagement.Controllers.Manager;
    exports org.example.flightticketmanagement.Controllers.Staff;
    exports org.example.flightticketmanagement.Models;
    exports org.example.flightticketmanagement.Views;

}