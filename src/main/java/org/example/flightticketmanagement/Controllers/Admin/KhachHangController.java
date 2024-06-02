package org.example.flightticketmanagement.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import org.example.flightticketmanagement.Models.KhachHang;

import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class KhachHangController implements Initializable {

    @FXML
    private TextField cccd_txtfld;

    @FXML
    private TextField diaChi_txtfld;

    @FXML
    private TextField email_txtfld;

    @FXML
    private TextField hoten_txtfld;

    @FXML
    private Label informationWarningText;

    @FXML
    private TextField maKH_txtfld;

    @FXML
    private Label numOfPassengersDisplay;

    @FXML
    private TextField sdt_txtfld;

    @FXML
    private Button selectSeats;

    static public int passengersToBeAdded;
    static public int initialPassengersToBeAdded;
    static public int curPassenger = 0;

    @FXML
    void nextPassengerButtonPress(ActionEvent event) {
        if (passengersToBeAdded > 0 && addPassenger()) {
            passengersToBeAdded--;
            curPassenger++;
            if (curPassenger != initialPassengersToBeAdded) {
                numOfPassengersDisplay.setText("Passenger " + (curPassenger + 1));
            }
            else
            {
                numOfPassengersDisplay.setText("Press Select Seat");
                //System.out.println(Passenger.passengers.size());
            }
        }
        else {
            if (passengersToBeAdded == 0) {
                informationWarningText.setText("Max Number of Passengers Reached");
            }
        }
    }

    public boolean addPassenger() throws ParseException {

        KhachHang tmpPassenger = new KhachHang();

        KhachHang.khachHang.add(tmpPassenger);

        cccd_txtfld.setText("");
        hoten_txtfld.setText("");
        diaChi_txtfld.setText("");
        email_txtfld.setText("");
        sdt_txtfld.setText("");
        informationWarningText.setText("");

        return true;
    }

    @FXML
    void switchToSeatSelection(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
