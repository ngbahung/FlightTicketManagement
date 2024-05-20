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
import org.example.flightticketmanagement.Models.BaoCaoNam;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;


public class DoanhThuNamController implements Initializable {
    //khoi tao cac bien trong view
    @FXML
    private TableView<BaoCaoNam> dtNam_tableview;

    @FXML
    private TableColumn<BaoCaoNam, String> dtNam_Thang_tbcolumn;

    @FXML
    private TableColumn<BaoCaoNam, String> dtNam_soChuyen_tbcolumn;

    @FXML
    private TableColumn<BaoCaoNam, String> dtNam_dt_tbcolumn;

    @FXML
    private TableColumn<BaoCaoNam, String> dtNam_tiLe_tbcolumn;

    @FXML
    private ComboBox<Integer> dtNam_cbbox_namSelection;

    @FXML
    private MFXButton dtNam_inBaoCao_btn;

    @FXML
    private MFXButton dtNam_thongKe_btn;

    @FXML
    private TextField dtNam_tongdt_txtfld;

    //khoi tao connection
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    private BigDecimal tongDoanhThuNam = BigDecimal.valueOf(0.0);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();
        DTNam_FillDataForComboBoxNam();
        dtNam_thongKe_btn.setOnMouseClicked(event -> DTNam_LoadData());
    }

    void DTNam_LoadData() {
        if (dtNam_cbbox_namSelection.getSelectionModel().isEmpty()) {
            alert.errorMessage("Vui lòng chọn năm cần thống kê.");
            return;
        }
        try {
            Integer namBaoCao = dtNam_cbbox_namSelection.getSelectionModel().getSelectedItem();
            String query = "WITH Months AS (" +
                    "    SELECT LEVEL AS Thang FROM dual CONNECT BY LEVEL <= 12" +
                    ") " +
                    "SELECT" +
                    "    m.Thang as thang," +
                    "    NVL(b.SoChuyenBay, 0) AS sochuyenbay," +
                    "    NVL(b.DoanhThu, 0) AS doanhthu," +
                    "    NVL(b.Tyle, 0) AS tyle " +
                    "FROM" +
                    "    Months m " +
                    "LEFT JOIN" +
                    "    BAOCAONAM b ON m.Thang = b.Thang AND b.Nam = (?) " +
                    "ORDER BY" +
                    "    m.Thang";

            prepare = connect.prepareStatement(query);
            prepare.setInt(1, namBaoCao);
            result = prepare.executeQuery();

            dtNam_tableview.getItems().clear();

            while (result.next()) {
                BaoCaoNam bcNam = new BaoCaoNam(
                        result.getInt("thang"),
                        result.getInt("sochuyenbay"),
                        result.getBigDecimal("doanhthu"),
                        result.getFloat("tyle")
                );
                dtNam_tableview.getItems().add(bcNam);

                tongDoanhThuNam = tongDoanhThuNam.add(bcNam.getDoanhThu());
            }
            dtNam_tongdt_txtfld.setText(tongDoanhThuNam.toString() + " VNĐ");

            dtNam_Thang_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getThang())));
            dtNam_soChuyen_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getSoChuyenBay())));
            dtNam_dt_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoanhThu().toString()));
            dtNam_tiLe_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTyLe().toString()));

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

    void DTNam_FillDataForComboBoxNam() {
        int currentNam = LocalDate.now().getYear();
        for (int i = currentNam; i >= 2000; i--) {
            dtNam_cbbox_namSelection.getItems().add(i);
        }
    }
}
