package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.CT_DatVe;
import org.example.flightticketmanagement.Models.DatabaseDriver;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class LichSuDatVeController implements Initializable {

    @FXML
    private MFXButton huyVeorDatCho_btn;

    @FXML
    private MFXDatePicker ngay_datepicker;

    @FXML
    private TableColumn<CT_DatVe, String> phDC_maVe_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> phDC_tenKH_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> phDC_sdt_tbcl;

    @FXML
    private TableColumn<CT_DatVe, LocalDateTime> phDC_ngayBay_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> phDC_hangVe_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> phDC_maGhe_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> phDC_sanBayDi_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> phDC_sanBayDen_tbcl;

    @FXML
    private TableColumn<CT_DatVe, LocalDateTime> phDC_gioBay_tbcl;

    @FXML
    private TableColumn<CT_DatVe, Integer> phDC_giaTien_tbcl;

    @FXML
    private TableView<CT_DatVe> phDC_tbview;

    @FXML
    private TableColumn<CT_DatVe, String> veDD_maVe_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> veDD_tenKH_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> veDD_sdt_tbcl;

    @FXML
    private TableColumn<CT_DatVe, LocalDateTime> veDD_ngayBay_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> veDD_hangVe_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> veDD_maGhe_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> veDD_sanBayDi_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> veDD_sbden_tbcl;

    @FXML
    private TableColumn<CT_DatVe, LocalDateTime> veDD_gioBay_tbcl;

    @FXML
    private TableColumn<CT_DatVe, Integer> veDD_giaTien_tbcl;

    @FXML
    private TableView<CT_DatVe> veDaDat_tbview;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert veDD_ngayBay_tbcl != null : "fx:id=\"veDD_ngayBay_tbcl\" was not injected: check your FXML file 'LichSuDatVe.fxml'.";
        initializeTableViewColumns();
        loadData();
    }

    private void initializeTableViewColumns() {
        duplicate(phDC_maVe_tbcl, phDC_tenKH_tbcl, phDC_sdt_tbcl, phDC_ngayBay_tbcl, phDC_hangVe_tbcl, phDC_maGhe_tbcl, phDC_sanBayDi_tbcl, phDC_sanBayDen_tbcl, phDC_gioBay_tbcl, phDC_giaTien_tbcl);

        duplicate(veDD_maVe_tbcl, veDD_tenKH_tbcl, veDD_sdt_tbcl, veDD_ngayBay_tbcl, veDD_hangVe_tbcl, veDD_maGhe_tbcl, veDD_sanBayDi_tbcl, veDD_sbden_tbcl, veDD_gioBay_tbcl, veDD_giaTien_tbcl);
    }

    private void duplicate(TableColumn<CT_DatVe, String> phDCMaVeTbcl, TableColumn<CT_DatVe, String> phDCTenKHTbcl, TableColumn<CT_DatVe, String> phDCSdtTbcl, TableColumn<CT_DatVe, LocalDateTime> phDCNgayBayTbcl, TableColumn<CT_DatVe, String> phDCHangVeTbcl, TableColumn<CT_DatVe, String> phDCMaGheTbcl, TableColumn<CT_DatVe, String> phDCSanBayDiTbcl, TableColumn<CT_DatVe, String> phDCSanBayDenTbcl, TableColumn<CT_DatVe, LocalDateTime> phDCGioBayTbcl, TableColumn<CT_DatVe, Integer> phDCGiaTienTbcl) {
        phDCMaVeTbcl.setCellValueFactory(new PropertyValueFactory<>("maVe"));
        phDCTenKHTbcl.setCellValueFactory(new PropertyValueFactory<>("maKhachHang"));
        phDCSdtTbcl.setCellValueFactory(new PropertyValueFactory<>("maKhachHang")); // Assuming this will be handled properly
        phDCNgayBayTbcl.setCellValueFactory(new PropertyValueFactory<>("ngayMuaVe"));
        phDCHangVeTbcl.setCellValueFactory(new PropertyValueFactory<>("trangThai")); // Assuming hangVe is part of trangThai
        phDCMaGheTbcl.setCellValueFactory(new PropertyValueFactory<>("maCT_DatVe")); // Assuming maGhe will be handled
        phDCSanBayDiTbcl.setCellValueFactory(new PropertyValueFactory<>("maCT_DatVe")); // Adjust as necessary
        phDCSanBayDenTbcl.setCellValueFactory(new PropertyValueFactory<>("maCT_DatVe")); // Adjust as necessary
        phDCGioBayTbcl.setCellValueFactory(new PropertyValueFactory<>("ngayThanhToan"));
        phDCGiaTienTbcl.setCellValueFactory(new PropertyValueFactory<>("trangThai")); // Assuming this is part of trangThai
    }

    private void loadData() {
        ObservableList<CT_DatVe> dataList = FXCollections.observableArrayList();

        String query = "SELECT * FROM CT_DATVE"; // Adjust this query to include necessary joins

        try {
            connect = DatabaseDriver.getConnection();
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();

            while (result.next()) {
                String maCT_DatVe = result.getString("MaCT_DATVE");
                String maVe = result.getString("MaVe");
                String maKhachHang = result.getString("MaKhachHang");
                LocalDateTime ngayMuaVe = result.getTimestamp("NgayMuaVe").toLocalDateTime();
                LocalDateTime ngayThanhToan = result.getTimestamp("NgayThanhToan").toLocalDateTime();
                String trangThai = result.getString("TrangThai");

                CT_DatVe ctDatVe = new CT_DatVe(maCT_DatVe, maVe, maKhachHang, ngayMuaVe, ngayThanhToan, trangThai);
                dataList.add(ctDatVe);
            }

            phDC_tbview.setItems(dataList);
            veDaDat_tbview.setItems(dataList);

        } catch (Exception e) {
            alert.errorMessage("Failed to load data");
        }
    }
}
