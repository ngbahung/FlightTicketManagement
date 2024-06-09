package org.example.flightticketmanagement.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ThemSanBayController implements Initializable {

    @FXML
    private TextField idNewSanBay_textfield;

    @FXML
    private Button luu_btn;

    @FXML
    private TextField tenNewSanBay_textfield;

    @FXML
    private TextField tenVietTatNew_textfield;

    @FXML
    private TextField tinhThanhNew_textfield;

    private Connection connect;
    private PreparedStatement prepare;

    private final AlertMessage alert = new AlertMessage();

    private QuyDinhController parentController;

    public void setParentController(QuyDinhController parentController) {
        this.parentController = parentController;
    }

    @FXML
    void saveNewAirport(ActionEvent event) {
        String ten = tenNewSanBay_textfield.getText();
        String tenVietTat = tenVietTatNew_textfield.getText();
        String tinhThanh = tinhThanhNew_textfield.getText();

        if (ten.isEmpty() || tenVietTat.isEmpty() || tinhThanh.isEmpty()) {
            alert.errorMessage("Vui lòng điền đầy đủ thông tin");
            return;
        }

        String sql = "INSERT INTO sanbay(MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai) VALUES(?, ?, ?, ?, ?)";
        String maSanBay = generateMaSanBay();

        try {
            connect = DatabaseDriver.getConnection();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, maSanBay);
            prepare.setString(2, ten);
            prepare.setString(3, tenVietTat);
            prepare.setString(4, tinhThanh);
            prepare.setInt(5, 1);

            int rowsAffected = prepare.executeUpdate();
            if (rowsAffected > 0) {
                alert.successMessage("Thêm sân bay thành công");
                if (parentController != null) {
                    parentController.refreshAirportsData();
                }
                closeWindow();
            } else {
                alert.errorMessage("Thêm sân bay thất bại");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Thêm sân bay thất bại");
        }
    }

    private String generateMaSanBay() {
        String sql = "SELECT MaSanBay FROM sanbay ORDER BY MaSanBay DESC FETCH FIRST ROW ONLY";
        String maSanBay = "SBD000";

        Connection tempConnect;
        PreparedStatement tempPrepare;
        ResultSet tempResult;

        try {
            tempConnect = DatabaseDriver.getConnection();
            tempPrepare = tempConnect.prepareStatement(sql);
            tempResult = tempPrepare.executeQuery();

            if (tempResult.next()) {
                String lastID = tempResult.getString("MaSanBay");
                int number = Integer.parseInt(lastID.substring(3)) + 1;
                maSanBay = "SBD" + String.format("%03d", number);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maSanBay;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idNewSanBay_textfield.setText(generateMaSanBay());
        luu_btn.setOnAction(this::saveNewAirport);
        tenVietTatNew_textfield.textProperty().addListener((observable, oldValue, newValue) -> tenVietTatNew_textfield.setText(newValue.toUpperCase()));
    }

    private void closeWindow() {
        Stage stage = (Stage) luu_btn.getScene().getWindow();
        stage.close();
    }
}
