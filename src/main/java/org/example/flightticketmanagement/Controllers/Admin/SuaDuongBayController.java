package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.DuongBay;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SuaDuongBayController implements Initializable {

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

    private DuongBay selectedDuongBay;

    private QuyDinhController parentController;

    private final AlertMessage alert = new AlertMessage();

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    void luuDuongBay(ActionEvent event) {
        String maDuongBay = maDuongBay_txf.getText();
        String tenSanBayDi = sanBayDi_cbx.getValue();
        String tenSanBayDen = sanBayDen_cbx.getValue();
        String tenDuongBay = tenDuongBay_txf.getText();
        String maSanBayDi = getMaSanBay(tenSanBayDi);
        String maSanBayDen = getMaSanBay(tenSanBayDen);
        String sql = "UPDATE DUONGBAY SET MaSanBayDi = ?, MaSanBayDen = ?, TenDuongBay = ? WHERE MaDuongBay = ?";
        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {
            prepare.setString(1, maSanBayDi);
            prepare.setString(2, maSanBayDen);
            prepare.setString(3, tenDuongBay);
            prepare.setString(4, maDuongBay);

            int rowsUpdated = prepare.executeUpdate();
            if (rowsUpdated > 0) {
                alert.successMessage("Cập nhật đường bay thành công");
                parentController.refreshDuongBayData();
                closeWindow();
            } else {
                alert.errorMessage("Cập nhật đường bay thất bại");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Cập nhật đường bay thất bại");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fetch data from SANBAY table
        List<String> sanBayList = getSanBayData();
        sanBayDi_cbx.getItems().addAll(sanBayList);
        sanBayDen_cbx.getItems().addAll(sanBayList);

        sanBayDi_cbx.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            String tenVietTatDi = getTenVietTat(newValue);
            updateTenDuongBay();
        });

        sanBayDen_cbx.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            String tenVietTatDen = getTenVietTat(newValue);
            updateTenDuongBay();
        });

        luuDuongBay_btn.setOnAction(this::luuDuongBay);

    }

    public void setDuongBay(DuongBay selectedDuongBay) {
        this.selectedDuongBay = selectedDuongBay;
        populateFields();
    }

    public void setParentController(QuyDinhController quyDinhController) {
        this.parentController = quyDinhController;
    }

    private void closeWindow() {
        Stage stage = (Stage) luuDuongBay_btn.getScene().getWindow();
        stage.close();
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
        }
        return maSanBay;
    }


    private void updateTenDuongBay() {
        if (sanBayDi_cbx.getValue() != null && sanBayDen_cbx.getValue() != null) {
            String tenDuongBay = getTenVietTat(sanBayDi_cbx.getValue()) + "-" + getTenVietTat(sanBayDen_cbx.getValue());
            tenDuongBay_txf.setText(tenDuongBay);
        }
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

    private void populateFields() {
        if (selectedDuongBay != null) {
            maDuongBay_txf.setText(selectedDuongBay.getMaDuongBay());
            maDuongBay_txf.setDisable(true);
            tenDuongBay_txf.setText(selectedDuongBay.getTenDuongBay());
            // Set the selected items of the ComboBoxes based on the DuongBay object
            sanBayDi_cbx.getSelectionModel().selectItem(selectedDuongBay.getTenSanBayDi());
            sanBayDen_cbx.getSelectionModel().selectItem(selectedDuongBay.getTenSanBayDen());
        }
    }
}
