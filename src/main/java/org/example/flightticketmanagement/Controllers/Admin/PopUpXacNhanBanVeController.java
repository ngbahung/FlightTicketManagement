package org.example.flightticketmanagement.Controllers.Admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.flightticketmanagement.Models.ChuyenBay;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.Model;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class PopUpXacNhanBanVeController implements Initializable {
    @FXML
    private Label airportFrom;

    @FXML
    private Label airportTo;

    @FXML
    private Button confirmationButton;

    @FXML
    private Label dayLabel;

    @FXML
    private Label timeLabel;

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization code, if needed
    }

    public void setFlightDetails(ChuyenBay flight, String airportFromName, String airportToName) {
        dayLabel.setText(flight.getThoiGianKetThuc().getDayOfMonth()
                + "-" + flight.getThoiGianKetThuc().getMonthValue()
                + "-" + flight.getThoiGianKetThuc().getYear());

        timeLabel.setText(flight.getThoiGianXuatPhat().getHour() + ":" + flight.getThoiGianXuatPhat().getMinute() + ":" + flight.getThoiGianXuatPhat().getSecond()
                + " - " + flight.getThoiGianKetThuc().getHour() + ":" + flight.getThoiGianKetThuc().getMinute() + ":" + flight.getThoiGianKetThuc().getSecond());

        airportFrom.setText(airportFromName);
        airportTo.setText(airportToName);
    }

    public static void handleHBoxClick(ChuyenBay flight, int numOfPassengers) throws IOException, SQLException {
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.initStyle(StageStyle.UTILITY);
        newStage.setTitle("Flight Details");
        newStage.setResizable(false);
        newStage.setX(593.0);
        newStage.setY(312.5);

        FXMLLoader fxmlLoader = new FXMLLoader(Model.getInstance().getClass().getResource("/Fxml/Admin/PopUpXacNhanBanVe.fxml"));
        Scene newScene = new Scene(fxmlLoader.load());
        PopUpXacNhanBanVeController controller = fxmlLoader.getController();


        try (Connection connection = DatabaseDriver.getConnection()) {

            String query = "SELECT sb1.TenSanBay AS TenSanBayDi, sb2.TenSanBay AS TenSanBayDen " +
                    "FROM CHUYENBAY cb " +
                    "JOIN DUONGBAY db ON cb.MaDuongBay = db.MaDuongBay " +
                    "JOIN SANBAY sb1 ON db.MaSanBayDi = sb1.MaSanBay " +
                    "JOIN SANBAY sb2 ON db.MaSanBayDen = sb2.MaSanBay " +
                    "WHERE cb.MaChuyenBay = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, flight.getMaChuyenBay());
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String tenSanBayDi = resultSet.getString("TenSanBayDi");
                        String tenSanBayDen = resultSet.getString("TenSanBayDen");
                        controller.setFlightDetails(flight, tenSanBayDi, tenSanBayDen);
                    }
                }
            }
        }

        newStage.setScene(newScene);
        newStage.show();
    }
}
