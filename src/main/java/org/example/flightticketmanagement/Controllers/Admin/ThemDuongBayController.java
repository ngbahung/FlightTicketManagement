package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;

public class ThemDuongBayController implements Initializable {

    @FXML
    private MFXButton luuDuongBay_btn;

    @FXML
    private TextField maDuongBay_txf;

    @FXML
    private MFXComboBox<String> sanBayDen_cbx;

    @FXML
    private MFXComboBox<String> sanBayDi_cbx;

    @FXML
    private TextField tenDuongBay_txf;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    private QuyDinhController parentController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fetch data from SANBAY table
        List<String> sanBayList = getSanBayData();
        sanBayDi_cbx.getItems().addAll(sanBayList);
        sanBayDen_cbx.getItems().addAll(sanBayList);

        sanBayDi_cbx.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            String tenVietTatDi = getTenVietTat(newValue);
            System.out.println("TenVietTat for SanBayDi: " + tenVietTatDi);
            updateTenDuongBay();
        });

        sanBayDen_cbx.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            String tenVietTatDen = getTenVietTat(newValue);
            System.out.println("TenVietTat for SanBayDen: " + tenVietTatDen);
            updateTenDuongBay();
        });

        // Set the default value for MaDuongBay
        maDuongBay_txf.setText(generateMaDuongBay());

        luuDuongBay_btn.setOnAction(this::luuDuongBay);
    }

    private List<String> getSanBayData() {
        List<String> sanBayList = new ArrayList<>();
        String sql = "SELECT TenSanBay FROM SANBAY";
        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql);
             ResultSet result = prepare.executeQuery()) {
            while (result.next()) {
                sanBayList.add(result.getString("TenSanBay"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanBayList;
    }


    public void setParentController(QuyDinhController parentController) {
        this.parentController = parentController;
    }

    @FXML
    void luuDuongBay(ActionEvent event) {
        String sanBayDi = sanBayDi_cbx.getSelectionModel().getSelectedItem();
        String sanBayDen = sanBayDen_cbx.getSelectionModel().getSelectedItem();
        String tenDuongBay = tenDuongBay_txf.getText();

        if (sanBayDi.equals(sanBayDen)) {
            alert.errorMessage("Sân bay đi và sân bay đến không được trùng nhau!");
            return;
        }

        if (tenDuongBay.isEmpty()) {
            alert.errorMessage("Vui lòng điền đầy đủ thông tin");
            return;
        }

        String sql = "INSERT INTO DUONGBAY(MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, TrangThai) VALUES(?, ?, ?, ?, ?)";
        String maDuongBay = generateMaDuongBay();
        String maSanBayDi = getMaSanBay(sanBayDi);
        String maSanBayDen = getMaSanBay(sanBayDen);

        try {
            connect = DatabaseDriver.getConnection();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, maDuongBay);
            prepare.setString(2, maSanBayDi);
            prepare.setString(3, maSanBayDen);
            prepare.setString(4, tenDuongBay);
            prepare.setInt(5, 1);

            int rowsAffected = prepare.executeUpdate();
            if (rowsAffected > 0) {
                alert.successMessage("Thêm đường bay thành công");
                if (parentController != null) {
                    parentController.refreshDuongBayData();
                }
                closeWindow();
            } else {
                alert.errorMessage("Thêm đường bay thất bại");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (connect != null && !connect.isClosed()) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    private String generateMaDuongBay() {
        String sql = "SELECT maDuongBay FROM DUONGBAY ORDER BY maDuongBay desc fetch first row only";
        String maDuongBay = "DB000";
        Connection tempConnect = null;
        PreparedStatement tempPrepare = null;
        ResultSet tempResult = null;

        try {
            tempConnect = DatabaseDriver.getConnection();
            tempPrepare = tempConnect.prepareStatement(sql);
            tempResult = tempPrepare.executeQuery();
            if (tempResult.next()) {
                String lastMaDuongBay = tempResult.getString("maDuongBay");
                int number = Integer.parseInt(lastMaDuongBay.substring(2));
                number++;
                maDuongBay = "DB" + String.format("%03d", number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (tempResult != null) tempResult.close();
                if (tempPrepare != null) tempPrepare.close();
                if (tempConnect != null && !tempConnect.isClosed()) tempConnect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return maDuongBay;

    }


    private String getTenVietTat(String tenSanBay) {
        String tenVietTat = null;
        String sql = "SELECT TenVietTat FROM SANBAY WHERE TenSanBay = ?";
        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {
            prepare.setString(1, tenSanBay);
            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    tenVietTat = result.getString("TenVietTat");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenVietTat;
    }

    // Method to fetch MaSanBay from SANBAY table
    private String getMaSanBay(String tenSanBay) {
        String maSanBay = null;
        String sql = "SELECT MaSanBay FROM SANBAY WHERE TenSanBay = ?";
        try {
            connect = DatabaseDriver.getConnection();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, tenSanBay);
            result = prepare.executeQuery();
            if (result.next()) {
                maSanBay = result.getString("MaSanBay");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null && !connect.isClosed()) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return maSanBay;
    }


    private void updateTenDuongBay() {
        if (sanBayDi_cbx.getValue() != null && sanBayDen_cbx.getValue() != null) {
            String tenDuongBay = getTenVietTat(sanBayDi_cbx.getValue()) + "-" + getTenVietTat(sanBayDen_cbx.getValue());
            tenDuongBay_txf.setText(tenDuongBay);
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) luuDuongBay_btn.getScene().getWindow();
        stage.close();
    }
}
