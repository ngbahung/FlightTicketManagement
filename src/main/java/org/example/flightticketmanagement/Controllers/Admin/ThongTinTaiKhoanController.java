package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.Model;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ThongTinTaiKhoanController implements Initializable {
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
    private MFXButton sua_btn;

    @FXML
    private MFXButton luu_btn;

    @FXML
    private MFXButton huy_btn;

    @FXML
    private MFXTextField ten_txtfld;

    @FXML
    private MFXTextField vaitro_txtfld;

    @FXML
    private Label info_lbl;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private String originalTen;
    private String originalSdt;
    private String originalMatKhau;

    private final AlertMessage alert = new AlertMessage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        makeFieldsReadOnly();
        setupSuaButton();
        setupLuuButton();
        setupHuyButton();
        styleInfoLabel();
    }

    private void loadData() {
        connect = DatabaseDriver.getConnection();
        String loggedInUserId = Model.getInstance().getLoggedInUserId();  // Get logged-in user ID

        String sql = "SELECT T.MaTaiKhoan, T.Ten, T.Sdt, T.Email, T.Password, T.Created, Q.TenQuyen " +
                "FROM TAIKHOAN T " +
                "JOIN QUYEN Q ON T.MaQuyen = Q.MaQuyen " +
                "WHERE T.MaTaiKhoan = ?";
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, loggedInUserId);  // Use logged-in user ID
            result = prepare.executeQuery();

            if (result.next()) {
                maTaiKhoan_txtfld.setText(result.getString("MaTaiKhoan"));
                ten_txtfld.setText(result.getNString("Ten"));
                originalTen = result.getNString("Ten");
                sdt_txtfld.setText(result.getString("Sdt"));
                originalSdt = result.getString("Sdt");
                email_txtfld.setText(result.getString("Email"));
                matKhau_txtfld.setText(result.getString("Password"));
                originalMatKhau = result.getString("Password");
                ngay_datepicker.setValue(result.getDate("Created").toLocalDate());
                vaitro_txtfld.setText(result.getNString("TenQuyen"));
            }
        } catch (SQLException e) {
            alert.errorMessage("Không kết nối được với database.");
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
            } catch (SQLException e) {
                alert.errorMessage("Không kết nối được với database.");
            }
        }
    }

    private void makeFieldsReadOnly() {
        email_txtfld.setEditable(false);
        maTaiKhoan_txtfld.setEditable(false);
        matKhau_txtfld.setEditable(false);
        ngay_datepicker.setDisable(true);  // Disabling the date picker
        sdt_txtfld.setEditable(false);
        ten_txtfld.setEditable(false);
        vaitro_txtfld.setEditable(false);

        sua_btn.setDisable(false);  // Ensure the button is enabled for clicking
        luu_btn.setDisable(true);
        huy_btn.setDisable(true);// Initially disable the save button
    }

    private void setupSuaButton() {
        sua_btn.setOnAction(event -> {
            enableEditing();
            luu_btn.setDisable(false);
            sua_btn.setDisable(true);
            huy_btn.setDisable(false);
        });
    }

    private void setupLuuButton() {
        luu_btn.setOnAction(event -> {
            if (validatePhoneNumber()) {
                if (dataChanged()) {
                    saveChanges();
                    makeFieldsReadOnly();
                    clearLabelsAndBorders();
                    luu_btn.setDisable(true);
                    sua_btn.setDisable(false);
                    huy_btn.setDisable(true);
                } else {
                    info_lbl.setText("Không có thay đổi nào để lưu.");
                }
            } else {
                info_lbl.setText("Số điện thoại không hợp lệ.");
            }
        });
    }

    private void setupHuyButton() {
        huy_btn.setOnAction(event -> {
            resetFields(); // Gọi phương thức resetFields() khi nhấn nút "Hủy"
        });
    }

    private void resetFields() {
        loadData(); // Load lại dữ liệu để đặt lại giá trị ban đầu
        makeFieldsReadOnly(); // Đặt lại trạng thái chỉ đọc cho các trường
        clearLabelsAndBorders(); // Xóa thông báo và các đường viền
        sua_btn.setDisable(false); // Kích hoạt nút "Sửa"
        luu_btn.setDisable(true); // Vô hiệu hóa nút "Lưu"
    }

    private void enableEditing() {
        ten_txtfld.setEditable(true);
        sdt_txtfld.setEditable(true);
        matKhau_txtfld.setEditable(true);

        String borderColorStyle = "-fx-border-color: #080C53; -fx-border-width: 2px;";

        ten_txtfld.setStyle(borderColorStyle);
        sdt_txtfld.setStyle(borderColorStyle);
        matKhau_txtfld.setStyle(borderColorStyle);
    }



    private void clearLabelsAndBorders() {
        info_lbl.setText(""); // Clear the info label
        clearBorderStyles(); // Clear border styles of text fields
    }

    private void clearBorderStyles() {
        String clearColorStyle = "-fx-border-color: #AFABAB;"; // Define transparent border color style

        ten_txtfld.setStyle(clearColorStyle);
        sdt_txtfld.setStyle(clearColorStyle);
        matKhau_txtfld.setStyle(clearColorStyle);
    }


    private boolean validatePhoneNumber() {
        String sdt = sdt_txtfld.getText();
        return sdt.matches("\\d{10}");
    }

    private boolean dataChanged() {
        String editedTen = ten_txtfld.getText();
        String editedSdt = sdt_txtfld.getText();
        String editedMatKhau = matKhau_txtfld.getText();

        return !editedTen.equals(originalTen) || !editedSdt.equals(originalSdt) || !editedMatKhau.equals(originalMatKhau);
    }

    private void saveChanges() {
        connect = DatabaseDriver.getConnection();

        String sql = "UPDATE TAIKHOAN SET Ten = ?, Sdt = ?, Password = ? WHERE MaTaiKhoan = ?";
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setNString(1, ten_txtfld.getText());
            prepare.setString(2, sdt_txtfld.getText());
            prepare.setString(3, matKhau_txtfld.getText());
            prepare.setString(4, maTaiKhoan_txtfld.getText());
            prepare.executeUpdate();

            alert.successMessage("Thông tin đã được cập nhật thành công.");
        } catch (SQLException e) {
            alert.errorMessage("Không kết nối được với database.");
        } finally {
            try {
                if (prepare != null) prepare.close();
            } catch (SQLException e) {
                alert.errorMessage("Không kết nối được với database.");
            }
        }
    }

    private void styleInfoLabel() {
        info_lbl.setStyle("-fx-font-weight: bold; -fx-text-fill: #080C53;");
    }
}
