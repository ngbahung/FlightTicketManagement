module org.example.flightticketmanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.flightticketmanagement to javafx.fxml;
    exports org.example.flightticketmanagement;
    exports org.example.flightticketmanagement.Controllers to javafx.fxml;
}