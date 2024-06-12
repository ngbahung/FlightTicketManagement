package org.example.flightticketmanagement.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.SanBay;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SuaSanBayController implements Initializable {

    @FXML
    private TextField idSanBay_textfield;

    @FXML
    private Button luu_btn;

    @FXML
    private TextField tenSanBay_textfield;

    @FXML
    private TextField tenVietTat_textfield;

    @FXML
    private TextField tinhThanh_textfield;

    private SanBay selectedAirport;
    private QuyDinhController parentController;

    private final AlertMessage alert = new AlertMessage();
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        luu_btn.setOnAction(this::updateAirportInfo);

        // Add listener for tenVietTat_txf to convert input to uppercase
        tenVietTat_textfield.textProperty().addListener((observable, oldValue, newValue) -> tenVietTat_textfield.setText(newValue.toUpperCase()));
    }

    public void setSanBay(SanBay selectedAirport) {
        this.selectedAirport = selectedAirport;
        populateFields();
    }

    private void populateFields() {
        if (selectedAirport != null) {
            idSanBay_textfield.setText(selectedAirport.getMaSanBay());
            idSanBay_textfield.setDisable(true);
            tenSanBay_textfield.setText(selectedAirport.getTenSanBay());
            tenVietTat_textfield.setText(selectedAirport.getTenVietTat());
            tinhThanh_textfield.setText(selectedAirport.getDiaChi());
        }
    }

    public void setParentController(QuyDinhController quyDinhController) {
        this.parentController = quyDinhController;
    }

    @FXML
    void updateAirportInfo(ActionEvent event) {
        String maSanBay = idSanBay_textfield.getText();
        String tenSanBay = tenSanBay_textfield.getText();
        String tenVietTat = tenVietTat_textfield.getText();
        String diaChi = tinhThanh_textfield.getText();

        if (maSanBay.isEmpty() || tenSanBay.isEmpty() || tenVietTat.isEmpty() || diaChi.isEmpty()) {
            alert.errorMessage("Vui lòng điền đầy đủ thông tin");
            return;
        }

        String sql = "UPDATE SANBAY SET TenSanBay = ?, TenVietTat = ?, DiaChi = ? WHERE MaSanBay = ?";

        try {
            connect = DatabaseDriver.getConnection();
            prepare = connect.prepareStatement(sql);

            prepare.setString(1, tenSanBay);
            prepare.setString(2, tenVietTat);
            prepare.setString(3, diaChi);
            prepare.setString(4, maSanBay);

            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                alert.successMessage("Cập nhật thông tin sân bay thành công.");
                parentController.refreshAirportsData();
                closeWindow();
            } else {
                alert.errorMessage("Không cập nhật được thông tin sân bay. Vui lòng kiểm tra lại.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Lỗi khi cập nhật thông tin sân bay. Vui lòng kiểm tra lại.");
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) luu_btn.getScene().getWindow();
        stage.close();
    }
}
