package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.example.flightticketmanagement.Models.BaoCaoNam;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.BaoCaoThang;
import org.example.flightticketmanagement.Models.DatabaseDriver;

public class DoanhThuController implements Initializable {
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

    @FXML
    private BarChart<String, Number> doanhthunam_barchart;

    @FXML
    private PieChart doanhthuthang_piechart;



    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();
    private BigDecimal tongDoanhThuNam = BigDecimal.valueOf(0.0);

    private Integer DTN_namBaoCao = 0;

    private ReportController reportController;
    private boolean DTN_isThongKeThanhCong = false;
    private List<BaoCaoNam> listBaoCaoNam = new ArrayList<BaoCaoNam>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportController = new ReportController();
        connect = DatabaseDriver.getConnection();
        DTNam_FillDataForComboBoxNam();
        DTThang_FillDataForComboBoxNam();
        DTThang_FillDataForComboBoxThang();
        dtNam_thongKe_btn.setOnMouseClicked(event -> DTNam_LoadData());
        dtThang_thongKe_btn.setOnMouseClicked(event -> DTThang_LoadData());
        dtNam_inBaoCao_btn.setOnMouseClicked(event -> InBaoCaoNam());
        dtThang_inBaoCao_btn.setOnMouseClicked(event -> InBaoCaoThang());
    }

    public void InBaoCaoNam() {
        if (DTN_isThongKeThanhCong == false) {
            alert.errorMessage("Vui lòng thống kê doanh thu trước khi xuất báo cáo!");
            return;
        }
        reportController.PrintReportBaoCaoNam(DTN_namBaoCao, listBaoCaoNam);
    }

    public void DTNam_UpdateData(Integer namBaoCao, BigDecimal tongDoanhThuNam){
        String query = "WITH Months AS (" +
                "    SELECT LEVEL AS Thang FROM dual CONNECT BY LEVEL <= 12" +
                ") " +
                "SELECT" +
                "    m.Thang as Thang," +
                "    NVL(b.SoChuyenBay, 0) AS SoChuyenBay," +
                "    NVL(b.DoanhThu, 0) AS DoanhThu " +
                "FROM" +
                "    Months m " +
                "LEFT JOIN" +
                "    BAOCAONAM b ON m.Thang = b.Thang AND b.Nam = ? " +
                "ORDER BY" +
                "    m.Thang";
        try (PreparedStatement prepare = connect.prepareStatement(query)) {
            prepare.setInt(1, namBaoCao);
            try (ResultSet result = prepare.executeQuery()) {
                dtNam_tableview.getItems().clear();
                listBaoCaoNam.clear();

                // Data for BarChart
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("Doanh Thu");

                while (result.next()) {
                    BigDecimal doanhThu = result.getBigDecimal("DoanhThu");
                    Double tyLe = doanhThu.divide(tongDoanhThuNam, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    BaoCaoNam baoCaoNam = new BaoCaoNam(
                            result.getInt("Thang"),
                            result.getInt("SoChuyenBay"),
                            result.getBigDecimal("DoanhThu"),
                            tyLe
                    );
                    listBaoCaoNam.add(baoCaoNam);
                    dtNam_tableview.getItems().add(baoCaoNam);

                    // Add data to BarChart series
                    series.getData().add(new XYChart.Data<>(Integer.toString(result.getInt("Thang")), doanhThu));
                }

                // Update BarChart
                doanhthunam_barchart.getData().clear();
                boolean add = doanhthunam_barchart.getData().add(series);

                DTN_isThongKeThanhCong = true;

                dtNam_tongdt_txtfld.setText(tongDoanhThuNam.toString() + " VNĐ");
                dtNam_Thang_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getThang())));
                dtNam_soChuyen_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getSoChuyenBay())));
                dtNam_dt_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoanhThu().toString()));
                dtNam_tiLe_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTyLe().toString()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while loading data from the database.");
        }
    }


    public void DTNam_LoadTongDT() {
        if (dtNam_cbbox_namSelection.getSelectionModel().isEmpty()) {
            alert.errorMessage("Vui lòng chọn năm cần thống kê");
            return;
        }

        DTN_namBaoCao = dtNam_cbbox_namSelection.getSelectionModel().getSelectedItem();
        DTN_isThongKeThanhCong = false;

        String query = "WITH Months AS (" +
                "    SELECT LEVEL AS Thang FROM dual CONNECT BY LEVEL <= 12" +
                ") " +
                "SELECT" +
                "    m.Thang, " +
                "    NVL(b.DoanhThu, 0) AS DoanhThu " +
                "FROM" +
                "    Months m " +
                "LEFT JOIN" +
                "    BAOCAONAM b ON m.Thang = b.Thang AND b.Nam = ? " +
                "ORDER BY" +
                "    m.Thang";

        try (PreparedStatement prepare = connect.prepareStatement(query)) {
            prepare.setInt(1, DTN_namBaoCao);
            try (ResultSet result = prepare.executeQuery()) {
                tongDoanhThuNam = BigDecimal.valueOf(0.0);
                while (result.next()) {
                    tongDoanhThuNam = tongDoanhThuNam.add(result.getBigDecimal("DoanhThu"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while loading data from the database.");
        }
    }

    public void DTNam_LoadData(){
        DTNam_LoadTongDT();
        DTNam_UpdateData(DTN_namBaoCao, tongDoanhThuNam);
        // Update BarChart with new data
        UpdateBarChartWithData(listBaoCaoNam);
    }

    public void DTNam_FillDataForComboBoxNam() {
        int currentNam = LocalDate.now().getYear();
        for (int i = currentNam; i >= 2000; i--) {
            dtNam_cbbox_namSelection.getItems().add(i);
        }
    }

    // bao cao thang

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
    @FXML
    private MFXButton dtThang_inBaoCao_btn;

    private BigDecimal tongDoanhThuThang = BigDecimal.valueOf(0.0);
    private Integer DTT_namBaoCao = 0;
    private Integer DTT_thangBaoCao = 0;

    private boolean DTT_isThongKeThanhCong = false;
    private List<BaoCaoThang> listBaoCaoThang = new ArrayList<BaoCaoThang>();

    public void InBaoCaoThang() {
        if (DTT_isThongKeThanhCong == false) {
            alert.errorMessage("Vui lòng thống kê doanh thu trước khi xuất báo cáo!");
            return;
        }
        reportController.PrintReportBaoCaoThang(DTT_namBaoCao, DTT_thangBaoCao, listBaoCaoThang);
    }

    public void DTThang_UpDateData(Integer namBaoCao, Integer thangBaoCao, BigDecimal tongDoanhThuThang) {
        String query = "SELECT MaChuyenBay, SoVeDaBan, DoanhThu " +
                "FROM BAOCAOTHANG " +
                "WHERE Thang = ? AND Nam = ?";

        try (PreparedStatement prepare = connect.prepareStatement(query)) {
            prepare.setInt(1, thangBaoCao);
            prepare.setInt(2, namBaoCao);
            try (ResultSet result = prepare.executeQuery()) {
                dtThang_tableView.getItems().clear();
                listBaoCaoThang.clear();

                // Data for PieChart
                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

                int stt = 1;
                while (result.next()) {
                    BigDecimal doanhThu = result.getBigDecimal("DoanhThu");
                    Double tyLe = doanhThu.divide(tongDoanhThuThang, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    BaoCaoThang baoCaoThang = new BaoCaoThang(
                            stt,
                            result.getString("MaChuyenBay"),
                            result.getInt("SoVeDaBan"),
                            doanhThu,
                            tyLe
                    );
                    listBaoCaoThang.add(baoCaoThang);
                    dtThang_tableView.getItems().add(baoCaoThang);

                    // Add data to PieChart
                    pieChartData.add(new PieChart.Data(result.getString("MaChuyenBay"), doanhThu.doubleValue()));

                    stt++;
                }

                // Update PieChart
                doanhthuthang_piechart.setData(pieChartData);

                DTT_isThongKeThanhCong = true;
                dtThang_tongdt_txfl.setText(tongDoanhThuThang.toString() + " VNĐ");

                dtThang_stt_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getStt())));
                dtThang_macb_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaChuyenBay()));
                dtThang_soVe_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getSoVeDaBan())));
                dtThang_doanhThu_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoanhThu().toString()));
                dtThang_tyLe_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData.getValue().getTyLe())));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while loading data from the database.");
        }
    }


    public void DTThang_LoadTongDT() {
        if (dtThang_cbbox_namSelection.getSelectionModel().isEmpty()) {
            alert.errorMessage("Vui lòng chọn năm cần thống kê");
            return;
        }
        if (dtThang_cbbox_thangSelection.getSelectionModel().isEmpty()) {
            alert.errorMessage("Vui lòng chọn tháng cần thống kê");
            return;
        }
        DTT_namBaoCao = dtThang_cbbox_namSelection.getSelectionModel().getSelectedItem();
        DTT_thangBaoCao = dtThang_cbbox_thangSelection.getSelectionModel().getSelectedItem();
        DTT_isThongKeThanhCong = false;

        String query = "SELECT DoanhThu " +
                "FROM BAOCAOTHANG " +
                "WHERE Thang = ? AND Nam = ?";

        try (PreparedStatement prepare = connect.prepareStatement(query)) {
            prepare.setInt(1, DTT_thangBaoCao);
            prepare.setInt(2, DTT_namBaoCao);
            try (ResultSet result = prepare.executeQuery()) {
                tongDoanhThuThang = BigDecimal.valueOf(0.0);
                while (result.next()) {
                    tongDoanhThuThang = tongDoanhThuThang.add(result.getBigDecimal("DoanhThu"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while loading data from the database.");
        }
    }

    public void DTThang_FillDataForComboBoxNam() {
        int currentYear = LocalDate.now().getYear();
        for (int i = currentYear; i >= 2000; i--) {
            dtThang_cbbox_namSelection.getItems().add(i);
        }
    }

    public void DTThang_FillDataForComboBoxThang() {
        for (int i = 1; i <= 12; i++) {
            dtThang_cbbox_thangSelection.getItems().add(i);
        }
    }

    public void DTThang_LoadData() {
        DTThang_LoadTongDT();
        DTThang_UpDateData(DTT_namBaoCao, DTT_thangBaoCao, tongDoanhThuThang);
        // Update PieChart with new data
        UpdatePieChartWithData(listBaoCaoThang);
    }

    private void UpdateBarChartWithData(List<BaoCaoNam> listBaoCaoNam) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Doanh Thu");

        for (BaoCaoNam baoCaoNam : listBaoCaoNam) {
            series.getData().add(new XYChart.Data<>(Integer.toString(baoCaoNam.getThang()), baoCaoNam.getDoanhThu()));
        }

        doanhthunam_barchart.getData().clear();
        doanhthunam_barchart.getData().add(series);
    }

    private void UpdatePieChartWithData(List<BaoCaoThang> listBaoCaoThang) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (BaoCaoThang baoCaoThang : listBaoCaoThang) {
            pieChartData.add(new PieChart.Data(baoCaoThang.getMaChuyenBay(), baoCaoThang.getDoanhThu().doubleValue()));
        }

        doanhthuthang_piechart.setData(pieChartData);
    }

}