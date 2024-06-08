package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SuaThamSoQuyDinhController implements Initializable {

    @FXML
    private MFXButton luuThamSo_btn;

    @FXML
    private TextField maxTGDung_tf;

    @FXML
    private TextField minTGDung_tf;

    @FXML
    private TextField numSBTGmax_tf;

    @FXML
    private TextField tgBayToiThieu_tf;

    @FXML
    private TextField tgChamNhatDatVe_tf;

    @FXML
    private TextField tgChamNhatHuyVe_tf;

    private QuyDinhController parentController;

    private Connection connect;
    private Statement statement;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    @FXML
    void saveChangesOnParameter(ActionEvent event) {
        // Null check for text fields
        if (maxTGDung_tf.getText().isEmpty() || minTGDung_tf.getText().isEmpty() || numSBTGmax_tf.getText().isEmpty() ||
                tgBayToiThieu_tf.getText().isEmpty() || tgChamNhatDatVe_tf.getText().isEmpty() || tgChamNhatHuyVe_tf.getText().isEmpty()) {
            alert.errorMessage("Vui lòng điền đầy đủ thông tin");
            return;
        }
        if (maxTGDung_tf.getText().isEmpty() || minTGDung_tf.getText().isEmpty() ||
                tgBayToiThieu_tf.getText().isEmpty() || tgChamNhatDatVe_tf.getText().isEmpty() || tgChamNhatHuyVe_tf.getText().isEmpty()) {
            alert.errorMessage("Vui lòng điền đầy đủ thông tin");
            return;
        }

        // Parsing values and validation
        int maxTGDung;
        int minTGDung;
        int numSBTGmax;
        int tgBayToiThieu;
        int tgChamNhatDatVe;
        int tgChamNhatHuyVe;

        try {
            maxTGDung = Integer.parseInt(maxTGDung_tf.getText());
            minTGDung = Integer.parseInt(minTGDung_tf.getText());
            numSBTGmax = Integer.parseInt(numSBTGmax_tf.getText());
            tgBayToiThieu = Integer.parseInt(tgBayToiThieu_tf.getText());
            tgChamNhatDatVe = Integer.parseInt(tgChamNhatDatVe_tf.getText());
            tgChamNhatHuyVe = Integer.parseInt(tgChamNhatHuyVe_tf.getText());
        } catch (NumberFormatException e) {
            alert.errorMessage("Vui lòng nhập số hợp lệ");
            return;
        }

        // Validating logical constraints
        if (maxTGDung < 0 || minTGDung < 0 || numSBTGmax < 0 || tgBayToiThieu < 0 || tgChamNhatDatVe < 0 || tgChamNhatHuyVe < 0) {
            alert.errorMessage("Vui lòng nhập số nguyên dương");
            return;
        }
        if (maxTGDung < 0 || minTGDung < 0 || tgBayToiThieu < 0 || tgChamNhatDatVe < 0 || tgChamNhatHuyVe < 0) {
            alert.errorMessage("Vui lòng nhập số nguyên dương");
            return;
        }


        if (maxTGDung < minTGDung) {
            alert.errorMessage("Thời gian dừng tối đa phải lớn hơn thời gian dừng tối thiểu");
            return;
        }

        if (tgChamNhatDatVe < tgChamNhatHuyVe) {
            alert.errorMessage("Thời gian chậm nhất đặt vé phải lớn hơn thời gian chậm nhất hủy vé");
            return;
        }

        String query = "UPDATE THAMSO SET GiaTri = ? WHERE MaThuocTinh = ?";

        try (Connection connect = DatabaseDriver.getConnection()) {
            try (PreparedStatement preState = connect.prepareStatement(query)) {
                // Updating each parameter
                preState.setInt(1, maxTGDung);
                preState.setString(2, "TGDTD");
                preState.executeUpdate();

                preState.setInt(1, minTGDung);
                preState.setString(2, "TGDTT");
                preState.executeUpdate();

                preState.setInt(1, numSBTGmax);
                preState.setString(2, "SSBTGTMD");
                preState.executeUpdate();

                preState.setInt(1, tgBayToiThieu);
                preState.setString(2, "TGBTT");
                preState.executeUpdate();

                preState.setInt(1, tgChamNhatDatVe);
                preState.setString(2, "TGTTDV");
                preState.executeUpdate();

                preState.setInt(1, tgChamNhatHuyVe);
                preState.setString(2, "TGTT_HV");
                preState.executeUpdate();
            }

            alert.successMessage("Cập nhật tham số thành công");
            parentController.loadThamSoData();
            closeWindow();
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Cập nhật tham số thất bại");
        }
    }

    public void setParentController(QuyDinhController parentController) {
        this.parentController = parentController;
    }

    private void closeWindow() {
        Stage stage = (Stage) luuThamSo_btn.getScene().getWindow();
        stage.close();
    }

    private void populateFields() {
        String query = "SELECT * FROM THAMSO";
        try {
            connect = DatabaseDriver.getConnection();
            statement = connect.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                String maThuocTinh = result.getString("MaThuocTinh");
                int giaTri = result.getInt("GiaTri");

                switch (maThuocTinh) {
                    case "TGBTT":
                        tgBayToiThieu_tf.setText(String.valueOf(giaTri));
                        break;
                    case "SSBTGTMD":
                        numSBTGmax_tf.setText(String.valueOf(giaTri));
                        break;
                    case "TGDTT":
                        minTGDung_tf.setText(String.valueOf(giaTri));
                        break;
                    case "TGDTD":
                        maxTGDung_tf.setText(String.valueOf(giaTri));
                        break;
                    case "TGTTDV":
                        tgChamNhatDatVe_tf.setText(String.valueOf(giaTri));
                        break;
                    case "TGTT_HV":
                        tgChamNhatHuyVe_tf.setText(String.valueOf(giaTri));
                        break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        luuThamSo_btn.setOnAction(this::saveChangesOnParameter);
        populateFields();
    }
}
