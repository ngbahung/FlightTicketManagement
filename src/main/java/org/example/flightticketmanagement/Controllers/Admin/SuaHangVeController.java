package org.example.flightticketmanagement.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.HangVe;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SuaHangVeController implements Initializable {

    @FXML
    private TextField heSo_textfield;

    @FXML
    private Button luuHangVe_btn;

    @FXML
    private TextField maHangVe_textfield;

    @FXML
    private TextField tenHangVe_textfield;

    private HangVe selectedTicketClass;
    private QuyDinhController parentController;

    private final AlertMessage alert = new AlertMessage();

    @FXML
    void luuHangVe(ActionEvent event) {
        String maHangVe = maHangVe_textfield.getText();
        String tenHangVe = tenHangVe_textfield.getText();
        String heSo = heSo_textfield.getText();

        if (maHangVe.isEmpty() || tenHangVe.isEmpty() || heSo.isEmpty()) {
            alert.errorMessage("Vui lòng điền đầy đủ thông tin");
            return;
        }

        String sql = "UPDATE HANGVE SET TenHangVe = ?, HeSo = ? WHERE MaHangVe = ?";

        try(Connection connect = DatabaseDriver.getConnection();
            PreparedStatement preState = connect.prepareStatement(sql)) {
            preState.setString(1, tenHangVe);
            preState.setDouble(2, Double.parseDouble(heSo));
            preState.setString(3, maHangVe);

            int rowsUpdated = preState.executeUpdate();

            if (rowsUpdated > 0) {
                alert.successMessage("Cập nhật hạng vé thành công");
                parentController.refreshHangVeData();
                closeWindow();
            } else {
                alert.errorMessage("Cập nhật hạng vé thất bại");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Cập nhật hạng vé thất bại");
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) luuHangVe_btn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        luuHangVe_btn.setOnAction(this::luuHangVe);
    }

    private void populateFields() {
        if (selectedTicketClass != null) {
            maHangVe_textfield.setText(selectedTicketClass.getMaHangVe());
            tenHangVe_textfield.setText(selectedTicketClass.getTenHangVe());
            heSo_textfield.setText(String.valueOf(selectedTicketClass.getHeSo()));
        }
    }

    public void setHangVe(HangVe selectedTicketClass) {
        this.selectedTicketClass = selectedTicketClass;
        populateFields();
    }

    public void setParentController(QuyDinhController parentController) {
        this.parentController = parentController;
    }
}
