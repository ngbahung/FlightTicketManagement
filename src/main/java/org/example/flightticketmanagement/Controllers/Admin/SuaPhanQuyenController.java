package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.TaiKhoan;

import java.net.URL;
import java.sql.*;
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
    private MFXComboBox<String> vaiTro_combobox;

    private TaiKhoan selectedTaiKhoan;
    private PhanQuyenController parentController;

    private final AlertMessage alert = new AlertMessage();
    private final Map<String, String> roleMap = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateRoleComboBox();
        luu_btn.setOnAction(this::updateAccount);
    }

    public void setSelectedTaiKhoan(TaiKhoan taiKhoan) {
        this.selectedTaiKhoan = taiKhoan;
        populateFields();
    }

    public void setParentController(PhanQuyenController parentController) {
        this.parentController = parentController;
    }

    private void populateFields() {
        if (selectedTaiKhoan != null) {
            maTaiKhoan_txtfld.setText(selectedTaiKhoan.getMaTaiKhoan());
            maTaiKhoan_txtfld.setDisable(true); // Disable the account ID field
            ten_txtfld.setText(selectedTaiKhoan.getTen());
            email_txtfld.setText(selectedTaiKhoan.getEmail());
            matKhau_txtfld.setText(selectedTaiKhoan.getPassword());
            vaiTro_combobox.setValue(roleMap.get(selectedTaiKhoan.getMaQuyen()));
        }
    }

    private void populateRoleComboBox() {
        try (Connection connect = DatabaseDriver.getConnection();
             Statement statement = connect.createStatement();
             ResultSet result = statement.executeQuery("SELECT MaQuyen, TenQuyen FROM QUYEN")) {

            while (result.next()) {
                String maQuyen = result.getString("MaQuyen");
                String tenQuyen = result.getString("TenQuyen");
                roleMap.put(maQuyen, tenQuyen);
                vaiTro_combobox.getItems().add(tenQuyen);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Lỗi khi tải vai trò. Vui lòng kiểm tra lại.");
        }
    }

    @FXML
    void updateAccount(ActionEvent event) {
        String maTaiKhoan = maTaiKhoan_txtfld.getText();
        String ten = ten_txtfld.getText();
        String email = email_txtfld.getText();
        String matKhau = matKhau_txtfld.getText();
        String vaiTro = vaiTro_combobox.getValue();

        if (ten.isEmpty() || email.isEmpty() || matKhau.isEmpty() || vaiTro.isEmpty()) {
            alert.errorMessage("Vui lòng điền đầy đủ thông tin.");
            return;
        }

        String sql = "UPDATE TaiKhoan SET ten = ?, email = ?, password = ?, maQuyen = (SELECT MaQuyen FROM QUYEN WHERE TenQuyen = ?) WHERE maTaiKhoan = ?";

        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setString(1, ten);
            prepare.setString(2, email);
            prepare.setString(3, matKhau);
            prepare.setString(4, vaiTro);
            prepare.setString(5, maTaiKhoan);

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

    private void closeWindow() {
        Stage stage = (Stage) luu_btn.getScene().getWindow();
        stage.close();
    }
}
