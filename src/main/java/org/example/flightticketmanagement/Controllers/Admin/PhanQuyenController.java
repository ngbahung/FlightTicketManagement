package org.example.flightticketmanagement.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.flightticketmanagement.Controllers.AlertMessage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BanVeController implements Initializable {

    @FXML
    private DatePicker datePicker;

    @FXML
    private MenuButton fromWhereMenuButton;

    @FXML
    private Label invalidInputForPriceMsg;

    @FXML
    private Label invalidInputOfPassengersMsg;

    @FXML
    private MenuButton maxArrivalTimeButton;

    @FXML
    private MenuButton maxDepartTimeButton;

    @FXML
    private TextField maxPrice;

    @FXML
    private TextField numOfPassengers;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button searchButton;

    @FXML
    private MenuButton seatClassButton;

    @FXML
    private VBox vbox;

    @FXML
    private MenuButton whereToMenuButton;

    @FXML
    void enableSearchButton(ActionEvent event) {

    }

    @FXML
    void fillDataOfFlights(ActionEvent event) {

    }

    @FXML
    void setFromWhereMenuButtonText(ActionEvent event) {

    }

    @FXML
    void setMaxArrivalTimeMenuButtonText(ActionEvent event) {

    }

    @FXML
    void setMaxDepartTimeMenuButtonText(ActionEvent event) {

    }

    @FXML
    void setSeatClassMenuButtonText(ActionEvent event) {

    }

    @FXML
    void setWhereToMenuButtonText(ActionEvent event) {

    }

    @FXML
    void validNumForPrice(ActionEvent event) {

    }

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
