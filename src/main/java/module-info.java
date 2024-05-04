module org.example.flightticketmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;


    opens org.example.flightticketmanagement to javafx.fxml;
    exports org.example.flightticketmanagement.Controllers to javafx.fxml;
    exports org.example.flightticketmanagement;
}