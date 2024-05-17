package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextFormatter;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.TaiKhoan;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SuaPhanQuyenController implements Initializable {
    @FXML
    private MFXTextField email_txtfld;

    @FXML
    private MFXTextField maTaiKhoan_txtfld;

    @FXML
    private MFXPasswordField matKhau_txtfld;

    @FXML
    private MFXDatePicker ngay_datepicker;

    @FXML
    private MFXTextField sdt_txtfld;

    @FXML
    private MFXTextField ten_txtfld;

    @FXML
    private MFXButton sua_btn;

    @FXML
    private MFXComboBox<String> vaiTro_combobox;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    private PhanQuyenController parentController;

    public void setParentController(PhanQuyenController parentController) {
        this.parentController = parentController;
    }

    public void setAccountData(TaiKhoan taiKhoan) {
        maTaiKhoan_txtfld.setText(taiKhoan.getMaTaiKhoan());
        ten_txtfld.setText(taiKhoan.getTen());
        email_txtfld.setText(taiKhoan.getEmail());
        matKhau_txtfld.setText(taiKhoan.getPassword());
        sdt_txtfld.setText(taiKhoan.getSDT());
        ngay_datepicker.setValue(taiKhoan.getCreated().toLocalDate());
        vaiTro_combobox.setValue(taiKhoan.getMaQuyen());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sdt_txtfld.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("0\\d*") && change.getControlNewText().length() <= 10) ? change : null));

        vaiTro_combobox.setItems(FXCollections.observableArrayList("Admin", "Manager", "Staff"));
        maTaiKhoan_txtfld.setDisable(true);
    }

    @FXML
    void updateAccount(ActionEvent event) {
        String maTaiKhoan = maTaiKhoan_txtfld.getText();
        String ten = ten_txtfld.getText();
        String sdt = sdt_txtfld.getText();

        // Kiểm tra các trường bắt buộc
        if (ten.isEmpty() || sdt.isEmpty() || sdt.length() != 10 || !sdt.startsWith("0") || vaiTro_combobox.getValue() == null) {
            alert.errorMessage("Vui lòng điền đầy đủ thông tin.");
            return;
        }

        String email = email_txtfld.getText();
        String password = matKhau_txtfld.getText();
        String vaiTro = vaiTro_combobox.getValue();
        String maQuyen = getRoleCode(vaiTro);
        LocalDate ngay = ngay_datepicker.getValue();

        String sql = "UPDATE TaiKhoan SET ten = ?, email = ?, password = ?, sdt = ?, maQuyen = ?, created = ? WHERE maTaiKhoan = ?";

        try {
            connect = DatabaseDriver.getConnection();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, ten);
            prepare.setString(2, email);
            prepare.setString(3, password);
            prepare.setString(4, sdt);
            prepare.setString(5, maQuyen);
            prepare.setObject(6, ngay);
            prepare.setString(7, maTaiKhoan);

            int rowsAffected = prepare.executeUpdate();
            if (rowsAffected > 0) {
                alert.successMessage("Tài khoản đã được sửa thành công. ");
                sua_btn.getScene().getWindow().hide();

                // Notify parent controller to refresh the table
                if (parentController != null) {
                    parentController.refreshTable();
                }
            } else {
                alert.errorMessage("Không sửa được tài khoản. Vui lòng kiểm tra lại.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Không sửa được tài khoản. Vui lòng kiểm tra lại.");
        } finally {
            try {
                if (prepare != null) {
                    prepare.close();
                }
                if (connect != null && !connect.isClosed()) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String getRoleCode(String role) {
        switch (role) {
            case "Admin":
                return "RL0001";
            case "Manager":
                return "RL0002";
            case "Staff":
                return "RL0003";
            default:
                return null;
        }
    }
}
