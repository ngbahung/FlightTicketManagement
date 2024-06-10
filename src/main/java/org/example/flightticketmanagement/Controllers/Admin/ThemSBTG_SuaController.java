package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.time.Duration;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ThemSBTG_SuaController implements Initializable{
    @FXML
    private MFXButton luu_btn;

    @FXML
    private MFXComboBox<String> tenSBTG_cbb;

    @FXML
    void xuLyLuu(ActionEvent event) {
        String selectedSanBay = tenSBTG_cbb.getValue();
        String selectedThoiGianDung = thoiGianDung_cbb.getValue();

        if (selectedSanBay == null || selectedThoiGianDung == null) {
            alert.errorMessage("Vui lòng điền đầy đủ thông tin.");
            return;
        }

        Duration layoverDuration = Duration.between(LocalTime.MIN, LocalTime.parse(selectedThoiGianDung));

        try {
            connect = DatabaseDriver.getConnection();
            prepare = connect.prepareStatement("SELECT MaThuocTinh, GiaTri FROM THAMSO WHERE MaThuocTinh IN ('TGDTT', 'TGDTD')");
            result = prepare.executeQuery();

            Duration TGDTT = null;
            Duration TGDTD = null;

            while (result.next()) {
                String maThuocTinh = result.getString("MaThuocTinh");
                int giaTri = result.getInt("GiaTri");

                if ("TGDTT".equals(maThuocTinh)) {
                    TGDTT = Duration.ofMinutes(giaTri);
                } else if ("TGDTD".equals(maThuocTinh)) {
                    TGDTD = Duration.ofHours(giaTri);
                }
            }

            if (TGDTT == null || TGDTD == null) {
                alert.errorMessage("Không thể tải thông số thời gian dừng.");
                return;
            }

            if (layoverDuration.compareTo(TGDTT) < 0 || layoverDuration.compareTo(TGDTD) > 0) {
                alert.errorMessage("Thời gian dừng phải nằm trong khoảng từ " + TGDTT.toMinutes() + " phút đến " + TGDTD.toHours() + " giờ.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Lỗi khi kiểm tra thông số thời gian dừng.");
            return;
        }

        parentController.addSBTGTemp(selectedSanBay, selectedThoiGianDung);
        Stage stage = (Stage) luu_btn.getScene().getWindow();
        stage.close();
    }


    private SanBay getSanBayByName(String tenSanBay) {
        String sql = "SELECT * FROM SANBAY WHERE TenSanBay = ?";
        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {
            prepare.setString(1, tenSanBay);
            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    return new SanBay(
                            result.getString("MaSanBay"),
                            result.getString("TenSanBay"),
                            result.getString("TenVietTat"),
                            result.getString("DiaChi"),
                            result.getInt("TrangThai"),
                            result.getInt("SoThuTu")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    private MFXComboBox<String> thoiGianDung_cbb;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();
    private SuaDuongBayController parentController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();
        populateSanBayComboBox();
        populateThoiGianDungComboBox();
    }

    private void populateSanBayComboBox() {
        try {
            connect = DatabaseDriver.getConnection();
            prepare = connect.prepareStatement("SELECT TenSanBay FROM SanBay");
            result = prepare.executeQuery();
            while (result.next()) {
                tenSBTG_cbb.getItems().add(result.getString("TenSanBay"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.successMessage("Failed to load airports");
        }
    }

    private void populateThoiGianDungComboBox() {
        int hours = 0;
        int minutes = 0;
        while (hours < 24) {
            while (minutes < 60) {
                String time = String.format("%02d:%02d:%02d", hours, minutes, 0); // Add seconds as 00
                thoiGianDung_cbb.getItems().add(time);
                minutes += 5;
            }
            minutes = 0; // Reset minutes to 0 for next hour
            hours++;
        }
    }

    public void setParentController(SuaDuongBayController parentController) {
        this.parentController = parentController;
    }
}
