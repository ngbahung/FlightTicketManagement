package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.ChuyenBay;

import java.time.LocalDateTime;
import java.util.List;

public class BanVeController {

    @FXML
    private ImageView airplaneLanding;

    @FXML
    private MenuButton bayDenDau_MenuButton;

    @FXML
    private MenuButton bayODau_MenuButton;

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
    private DatePicker ngayBay_datepicker;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private MenuButton seatClassButton;

    @FXML
    private TextField soLuongHanhKhach_txtfld;

    @FXML
    private MFXButton timKiem_btn;

    @FXML
    private VBox vbox;

    private final AlertMessage alert = new AlertMessage();
    private final ChuyenBay.ChuyenBayDAO chuyenBayDAO = new ChuyenBay.ChuyenBayDAO();

    @FXML
    void enableSearchButton(ActionEvent event) {
        if (ngayBay_datepicker.getValue() != null && !soLuongHanhKhach_txtfld.getText().isEmpty()) {
            timKiem_btn.setDisable(false);
        } else {
            timKiem_btn.setDisable(true);
        }
    }

    @FXML
    void fillDataOfFlights(ActionEvent event) {
        try {
            String departure = bayODau_MenuButton.getText();
            String destination = bayDenDau_MenuButton.getText();
            LocalDateTime date = ngayBay_datepicker.getValue().atStartOfDay();
            Integer maxPriceValue = Integer.parseInt(maxPrice.getText());

            List<ChuyenBay> flights = chuyenBayDAO.getChuyenBays(departure, destination, date, maxPriceValue);

            vbox.getChildren().clear(); // Clear previous search results
            for (ChuyenBay flight : flights) {
                Label flightLabel = new Label(flight.getMaChuyenBay() + " - " + flight.getThoiGianXuatPhat());
                vbox.getChildren().add(flightLabel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void setBayDenDautext(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        bayDenDau_MenuButton.setText(item.getText());
    }

    @FXML
    void setBayODauText(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        bayODau_MenuButton.setText(item.getText());
    }

    @FXML
    void setMaxArrivalTimeMenuButtonText(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        maxArrivalTimeButton.setText(item.getText());
    }

    @FXML
    void setMaxDepartTimeMenuButtonText(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        maxDepartTimeButton.setText(item.getText());
    }

    @FXML
    void setSeatClassMenuButtonText(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        seatClassButton.setText(item.getText());
    }

    @FXML
    void validNumForPrice(ActionEvent event) {
        try {
            Integer.parseInt(maxPrice.getText());
            invalidInputForPriceMsg.setVisible(false);
        } catch (NumberFormatException e) {
            invalidInputForPriceMsg.setVisible(true);
        }
    }
}
