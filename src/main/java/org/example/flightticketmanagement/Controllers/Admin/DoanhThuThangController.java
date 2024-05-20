package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.BaoCaoThang;
import org.example.flightticketmanagement.Models.DatabaseDriver;

public class DoanhThuThangController implements Initializable {
    @FXML
    private ComboBox<Integer> dtThang_cbbox_namSelection;

    @FXML
    private ComboBox<Integer> dtThang_cbbox_thangSelection;

    @FXML
    private TableColumn<BaoCaoThang, String> dtThang_doanhThu_tbcolumn;

    @FXML
    private TableColumn<BaoCaoThang, String> dtThang_macb_tbcolumn;

    @FXML
    private TableColumn<BaoCaoThang, String> dtThang_soVe_tbcolumn;

    @FXML
    private TableColumn<BaoCaoThang, String> dtThang_stt_tbcolumn;

    @FXML
    private TableColumn<BaoCaoThang, String> dtThang_tyLe_tbcolumn;

    @FXML
    private TableView<BaoCaoThang> dtThang_tableView;

    @FXML
    private MFXButton dtThang_thongKe_btn;

    @FXML
    private TextField dtThang_tongdt_txfl;

    //tao connection
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    private BigDecimal tongDoanhThuThang = BigDecimal.valueOf(0.0);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();
        DTThang_FillDataForComboBoxNam();
        DTThang_FillDataForComboBoxThang();
        dtThang_thongKe_btn.setOnMouseClicked(event -> DTThang_LoadData());
    }

    public void DTThang_LoadData() {
        if(dtThang_cbbox_namSelection.getSelectionModel().isEmpty()){
            alert.errorMessage("Vui lòng chọn năm cần thống kê");
        }
        if(dtThang_cbbox_thangSelection.getSelectionModel().isEmpty()){
            alert.errorMessage("Vui lòng chọn tháng cần thống kê");
        }
        try {
            Integer namBaoCao = dtThang_cbbox_namSelection.getSelectionModel().getSelectedItem();
            Integer thangBaoCao = dtThang_cbbox_thangSelection.getSelectionModel().getSelectedItem();

            String query = "SELECT MaChuyenBay, SoVeDaBan, DoanhThu, TyLe " +
                    "FROM BAOCAOTHANG " +
                    "WHERE Thang = ? " +
                    "AND Nam = ?";
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, thangBaoCao);
            prepare.setInt(2, namBaoCao);
            result = prepare.executeQuery();

            dtThang_tableView.getItems().clear();

            int stt = 1;
            while (result.next()) {
                // Tạo đối tượng BaoCaoThang mới từ kết quả truy vấn và thêm vào TableView
                BaoCaoThang baoCaoThang = new BaoCaoThang(
                        stt,
                        result.getString("MaChuyenBay"),
                        result.getInt("SoVeDaBan"),
                        result.getBigDecimal("DoanhThu"),
                        result.getFloat("TyLe")
                );
                dtThang_tableView.getItems().add(baoCaoThang);
                stt++;
                tongDoanhThuThang = tongDoanhThuThang.add(baoCaoThang.getDoanhThu());
            }
            dtThang_tongdt_txfl.setText(tongDoanhThuThang.toString() + " VNĐ");

            dtThang_stt_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getStt())));
            dtThang_macb_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMaChuyenBay())));
            dtThang_soVe_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getSoVeDaBan())));
            dtThang_doanhThu_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoanhThu().toString()));
            dtThang_tyLe_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTyLe().toString()));
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while loading data from the database.");
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public void DTThang_FillDataForComboBoxNam() {
        int currentYear = LocalDate.now().getYear();
        for(int i = currentYear; i >= 2000; i--){
            dtThang_cbbox_namSelection.getItems().add(i);
        }
    }
    public void DTThang_FillDataForComboBoxThang() {
        for(int i = 1; i <= 12; i++){
            dtThang_cbbox_thangSelection.getItems().add(i);
        }
    }

}
