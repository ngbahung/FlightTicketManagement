package org.example.flightticketmanagement.Controllers.Manager;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.CT_HangVe;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.HangVe;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ThemHangVe_ChuyenBayController implements Initializable {
    @FXML
    private MFXComboBox<HangVe> hangVe_combox;

    @FXML
    private MFXButton luuHangVe_btn;

    @FXML
    private MFXTextField soLuongGhe_txtfld;


    @FXML
    void luuHangVe() {
        HangVe selectedHangVe = hangVe_combox.getValue();
        if (selectedHangVe == null || soLuongGhe_txtfld.getText().isEmpty()) {
            alert.errorMessage("Please select a class and enter the number of seats.");
            return;
        }

        String maHangVe = selectedHangVe.getMaHangVe();
        String maChuyenBay = parentController.getGeneratedMaChuyenBay();
        Integer soGheTrong = Integer.parseInt(soLuongGhe_txtfld.getText());

        CT_HangVe ctHangVe = new CT_HangVe(maChuyenBay, maHangVe, soGheTrong, 0);

        parentController.themCT_HangVeToTable(ctHangVe);

        // Clear the selection of hangVe_combox
        hangVe_combox.getSelectionModel().clearSelection();

        // Close the current window
        Stage stage = (Stage) luuHangVe_btn.getScene().getWindow();
        stage.close();
    }

    private Connection connect;

    private final AlertMessage alert = new AlertMessage();
    private ThemLichChuyenBayController parentController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();
        ObservableList<HangVe> hangVeList = getHangVeList();
        hangVe_combox.setItems(hangVeList);
        chuyenDoiHangVeCombobox(hangVeList);
    }

    public ObservableList<HangVe> getHangVeList() {
        ObservableList<HangVe> hangVeList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM HANGVE WHERE TRANGTHAI = 1";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String maHangVe = rs.getString("MaHangVe");
                String tenHangVe = rs.getString("TenHangVe");
                float heSo = rs.getFloat("HeSo");
                int trangThai = rs.getInt("TrangThai");

                HangVe hangVe = new HangVe(maHangVe, tenHangVe, heSo, trangThai);
                hangVeList.add(hangVe);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hangVeList;
    }

    public void setParentController(ThemLichChuyenBayController parentController) {
        this.parentController = parentController;
    }

    private void chuyenDoiHangVeCombobox(ObservableList<HangVe> hangVeList) {
        hangVe_combox.setConverter(new StringConverter<>() {
            @Override
            public String toString(HangVe hangVe) {
                return hangVe != null ? hangVe.getTenHangVe() : "";
            }

            @Override
            public HangVe fromString(String s) {
                // Chuyển đổi tất cả các ký tự trong chuỗi s sang chữ thường để so sánh không phân biệt chữ hoa và chữ thường
                String lowerCaseS = s.toLowerCase();

                // Sử dụng hàm filter để lấy ra đối tượng HangVe có tên tương ứng với chuỗi s (không phân biệt chữ hoa và chữ thường)
                return hangVeList.stream()
                        .filter(hv -> hv.getTenHangVe().toLowerCase().equals(lowerCaseS))
                        .findFirst()
                        .orElse(null);
            }
        });
    }


}
