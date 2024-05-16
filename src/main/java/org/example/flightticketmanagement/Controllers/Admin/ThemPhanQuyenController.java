package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextFormatter;
import org.example.flightticketmanagement.Controllers.Admin.PhanQuyenController;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ThemPhanQuyenController implements Initializable {
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
    private MFXButton them_btn;

    @FXML
    private MFXComboBox<String> vaiTro_combobox;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();

    private PhanQuyenController parentController;

    public void setParentController(PhanQuyenController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sdt_txtfld.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("0\\d*") && change.getControlNewText().length() <= 10) ? change : null));

        ngay_datepicker.setEditable(false);

        vaiTro_combobox.setItems(FXCollections.observableArrayList("Admin", "Manager", "Staff"));
        maTaiKhoan_txtfld.setText(generateAccountID());
        maTaiKhoan_txtfld.setDisable(true);

        ten_txtfld.textProperty().addListener((observable, oldValue, newValue) -> {
            email_txtfld.setText(generateEmail(newValue));
            matKhau_txtfld.setText(generatePassword(newValue));
        });
    }

    @FXML
    void addAccount(ActionEvent event) {
        String ten = ten_txtfld.getText();
        String sdt = sdt_txtfld.getText();

        // Kiểm tra các trường bắt buộc
        if (ten.isEmpty() || sdt.isEmpty() || sdt.length() != 10 || !sdt.startsWith("0") || vaiTro_combobox.getValue() == null || ngay_datepicker.getValue() == null) {
            alert.errorMessage("Please fill in all required fields correctly.");
            return;
        }

        String email = generateEmail(ten);
        String password = generatePassword(ten);
        String vaiTro = vaiTro_combobox.getValue();
        String maQuyen = getRoleCode(vaiTro);

        String sql = "INSERT INTO TaiKhoan (maTaiKhoan, ten, email, password, maQuyen) VALUES (?, ?, ?, ?, ?)";
        String maTaiKhoan = generateAccountID();

        try {
            connect = DatabaseDriver.getConnection();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, maTaiKhoan);
            prepare.setString(2, ten);
            prepare.setString(3, email);
            prepare.setString(4, password);
            prepare.setString(5, maQuyen);

            int rowsAffected = prepare.executeUpdate();
            if (rowsAffected > 0) {
                alert.successMessage("New account has been added successfully.");
                them_btn.getScene().getWindow().hide();

                // Notify parent controller to refresh the table
                if (parentController != null) {
                    parentController.refreshTable();
                }
            } else {
                alert.errorMessage("Failed to add the account. Please check the logs for more details.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Failed to add the account. Please check the logs for more details.");
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

    private String generateEmail(String name) {
        String[] nameParts = name.split("\\s+");
        if (nameParts.length < 2) {
            return "default@gmail.com";  // Fallback if the name is not as expected
        }
        String firstName = removeDiacritics(nameParts[0].toLowerCase());
        String lastName = removeDiacritics(nameParts[nameParts.length - 1].toLowerCase());
        return lastName + firstName.charAt(0) + "@gmail.com";
    }

    private String generatePassword(String name) {
        String[] nameParts = name.split("\\s+");
        if (nameParts.length < 2) {
            return "default";  // Fallback if the name is not as expected
        }
        String firstName = removeDiacritics(nameParts[0].toLowerCase());
        String lastName = removeDiacritics(nameParts[nameParts.length - 1].toLowerCase());
        return lastName + firstName.charAt(0);
    }

    private String removeDiacritics(String str) {
        return java.text.Normalizer.normalize(str, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    private String generateAccountID() {
        String sql = "SELECT maTaiKhoan FROM TaiKhoan ORDER BY maTaiKhoan DESC FETCH FIRST 1 ROWS ONLY";
        String maTaiKhoan = "MA000";
        Connection tempConnect = null;
        PreparedStatement tempPrepare = null;
        ResultSet tempResult = null;

        try {
            tempConnect = DatabaseDriver.getConnection();
            tempPrepare = tempConnect.prepareStatement(sql);
            tempResult = tempPrepare.executeQuery();

            if (tempResult.next()) {
                String lastID = tempResult.getString("maTaiKhoan");
                int id = Integer.parseInt(lastID.substring(2)) + 1;
                maTaiKhoan = String.format("MA%03d", id);
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
        return maTaiKhoan;
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
