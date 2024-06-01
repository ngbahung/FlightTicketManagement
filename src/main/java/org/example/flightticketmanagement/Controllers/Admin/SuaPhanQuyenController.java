package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.TaiKhoan;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SuaPhanQuyenController implements Initializable {

    @FXML
    private MFXTextField email_txtfld;

    @FXML
    private MFXButton luu_btn;

    @FXML
    private MFXTextField maTaiKhoan_txtfld;

    @FXML
    private MFXPasswordField matKhau_txtfld;

    @FXML
    private MFXTextField ten_txtfld;

    @FXML
    private MFXDatePicker ngay_datepicker;

    @FXML
    private MFXTextField sdt_txtfld;

    @FXML
    private MFXComboBox<String> vaiTro_combobox;


    @FXML
    void updateAccount() {
        String maTaiKhoan = maTaiKhoan_txtfld.getText();
        String ten = ten_txtfld.getText();
        String sdt = sdt_txtfld.getText();
        String email = email_txtfld.getText();
        String matKhau = matKhau_txtfld.getText();
        String vaiTro = vaiTro_combobox.getValue();
        String maQuyen = roleMap.get(vaiTro);
        LocalDate ngayTao = ngay_datepicker.getValue();

        if (ten.isEmpty() || sdt.isEmpty() || email.isEmpty() || matKhau.isEmpty() || vaiTro.isEmpty()) {
            alert.errorMessage("Vui lòng điền đầy đủ thông tin.");
            return;
        }

        String sql = "UPDATE TaiKhoan SET ten = ?, sdt = ?, email = ?, password = ?, maQuyen = ?, created = ? WHERE maTaiKhoan = ?";

        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, ten);
            prepare.setString(2, sdt);
            prepare.setString(3, email);
            prepare.setString(4, matKhau);
            prepare.setString(5, maQuyen);
            prepare.setObject(6, ngayTao != null ? ngayTao.atStartOfDay() : null);
            prepare.setString(7, maTaiKhoan);

            int rowsUpdated = prepare.executeUpdate();
            if (rowsUpdated > 0) {
                alert.successMessage("Cập nhật tài khoản thành công.");
                parentController.refreshTable();
                closeWindow();
            } else {
                alert.errorMessage("Không thể cập nhật tài khoản. Vui lòng thử lại.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Lỗi khi cập nhật tài khoản. Vui lòng kiểm tra lại.");
        }
    }

    private TaiKhoan selectedTaiKhoan;
    private PhanQuyenController parentController;

    private final AlertMessage alert = new AlertMessage();
    private final Map<String, String> roleMap = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateRoleComboBox();
    }

    public void setSelectedTaiKhoan(TaiKhoan taiKhoan) {
        this.selectedTaiKhoan = taiKhoan;
        if (!roleMap.isEmpty()) {
            populateFields();
        }
    }

    public void setParentController(PhanQuyenController parentController) {
        this.parentController = parentController;
    }

    private void populateFields() {
        if (selectedTaiKhoan != null) {
            maTaiKhoan_txtfld.setText(selectedTaiKhoan.getMaTaiKhoan());
            maTaiKhoan_txtfld.setDisable(true); // Disable the account ID field
            ten_txtfld.setText(selectedTaiKhoan.getTen());
            sdt_txtfld.setText(selectedTaiKhoan.getSDT());
            email_txtfld.setText(selectedTaiKhoan.getEmail());
            matKhau_txtfld.setText(selectedTaiKhoan.getPassword());

            LocalDateTime created = selectedTaiKhoan.getCreated();
            if (created != null) {
                ngay_datepicker.setValue(created.toLocalDate());
            }
            String tenVaiTro = getRoleName(selectedTaiKhoan.getMaQuyen());
            vaiTro_combobox.setValue(tenVaiTro);
            vaiTro_combobox.getSelectionModel().selectItem(selectedTaiKhoan.getMaQuyen());
            vaiTro_combobox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedTaiKhoan.setMaQuyen(roleMap.get(newValue)));
        }
    }


    private String getRoleName(String maQuyen) {
        for (Map.Entry<String, String> entry : roleMap.entrySet()) {
            if (entry.getValue().equals(maQuyen)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void populateRoleComboBox() {
        try (Connection connect = DatabaseDriver.getConnection();
             Statement statement = connect.createStatement();
             ResultSet result = statement.executeQuery("SELECT MaQuyen, TenQuyen FROM QUYEN")) {

            while (result.next()) {
                String maQuyen = result.getString("MaQuyen");
                String tenQuyen = result.getString("TenQuyen");
                roleMap.put(tenQuyen, maQuyen);
                vaiTro_combobox.getItems().add(tenQuyen);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Lỗi khi tải vai trò. Vui lòng kiểm tra lại.");
        }
    }


    private void closeWindow() {
        Stage stage = (Stage) luu_btn.getScene().getWindow();
        stage.close();
    }
}
