package org.example.flightticketmanagement.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ThemHangVeController implements Initializable {
    @FXML
    private TextField heSo_textfield;

    @FXML
    private Button luuHangVe_btn;

    @FXML
    private TextField themHangVe_textfield;

    @FXML
    private TextField maHangVe_textfield;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    private QuyDinhController parentController;

    @FXML
    void luuNewHangVe(ActionEvent event) {
        String tenHangVe = themHangVe_textfield.getText();
        String heSo = heSo_textfield.getText();

        if (tenHangVe.isEmpty() || heSo.isEmpty()) {
            alert.errorMessage("Vui lòng điền đầy đủ thông tin");
            return;
        }

        String sql = "INSERT INTO HANGVE(MaHangVe, TenHangVe, HeSo, TrangThai) VALUES(?, ?, ?, ?)";
        String maHangVe = generateMaHangVe();

        try {
            connect = DatabaseDriver.getConnection();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, maHangVe);
            prepare.setString(2, tenHangVe);
            prepare.setFloat(3, Float.parseFloat(heSo));
            prepare.setInt(4, 1);

            int rowsAffected = prepare.executeUpdate();
            if (rowsAffected > 0) {
                alert.successMessage("Thêm hạng vé thành công");
                if (parentController != null) {
                    parentController.refreshHangVeData();
                }
                closeWindow();
            } else {
                alert.errorMessage("Thêm hạng vé thất bại");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Thêm sân bay thất bại");
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private String generateMaHangVe() {
        String sql = "SELECT MaHangVe FROM HANGVE ORDER BY MaHangVe DESC FETCH FIRST ROW ONLY";
        String maHangVe = "HV000";

        Connection tempConnect = null;
        PreparedStatement tempPrepare = null;
        ResultSet tempResult = null;

        try {
            tempConnect = DatabaseDriver.getConnection();
            tempPrepare = tempConnect.prepareStatement(sql);
            tempResult = tempPrepare.executeQuery();

            if (tempResult.next()) {
                String lastID = tempResult.getString("MaHangVe");
                int number = Integer.parseInt(lastID.substring(3)) + 1;
                maHangVe = "HV" + String.format("%03d", number);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maHangVe;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        maHangVe_textfield.setText(generateMaHangVe());
        luuHangVe_btn.setOnAction(this::luuNewHangVe);

    }

    public void setParentController(QuyDinhController parentController) {
        this.parentController = parentController;
    }

    private void closeWindow() {
        Stage stage = (Stage) luuHangVe_btn.getScene().getWindow();
        stage.close();
    }
}
