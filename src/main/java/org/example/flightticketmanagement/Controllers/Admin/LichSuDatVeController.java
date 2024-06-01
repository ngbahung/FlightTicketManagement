package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.CT_DatVe;
import org.example.flightticketmanagement.Models.DatabaseDriver;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

public class LichSuDatVeController implements Initializable {
    @FXML
    private MFXButton refresh_btn;

    @FXML
    private MenuButton sanbayden_menubtn;

    @FXML
    private MenuButton sanbaydi_menubtn;

    @FXML
    private MFXDatePicker ngay_datepicker;

    @FXML
    private TableColumn<CT_DatVe, String> phDC_maVe_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> phDC_tenKH_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> phDC_sdt_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> phDC_ngayBay_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> phDC_hangVe_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> phDC_maGhe_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> phDC_sanBayDi_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> phDC_sanBayDen_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> phDC_gioBay_tbcl;

    @FXML
    private TableColumn<CT_DatVe, Float> phDC_giaTien_tbcl;

    @FXML
    private TableView<CT_DatVe> phDC_tbview;

    @FXML
    private TableColumn<CT_DatVe, String> veDD_maVe_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> veDD_tenKH_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> veDD_sdt_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> veDD_ngayBay_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> veDD_hangVe_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> veDD_maGhe_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> veDD_sanBayDi_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> veDD_sanBayDen_tbcl;

    @FXML
    private TableColumn<CT_DatVe, String> veDD_gioBay_tbcl;

    @FXML
    private TableColumn<CT_DatVe, Float> veDD_giaTien_tbcl;

    @FXML
    private MFXButton thanhToan_btn;

    @FXML
    private TableView<CT_DatVe> veDaDat_tbview;

    @FXML
    void search() {
        LocalDateTime selectedDate = ngay_datepicker.getValue() != null ? ngay_datepicker.getValue().atStartOfDay() : null;
        String sanBayDi = sanbaydi_menubtn.getText();
        String sanBayDen = sanbayden_menubtn.getText();

        veDaDat_tbview.getItems().clear();
        phDC_tbview.getItems().clear();

        String baseQuery = "SELECT * FROM CT_DATVE WHERE TrangThai = ?";
        StringBuilder conditions = new StringBuilder();

        // Build conditions based on user input
        if (selectedDate != null) {
            conditions.append(" AND MaVe IN (SELECT V.MaVe FROM Ve V JOIN ChuyenBay CB ON V.MaChuyenBay = CB.MaChuyenBay WHERE TRUNC(CB.TGXP) = ?)");
        }
        if (!"Sân bay đi".equals(sanBayDi)) {
            conditions.append(" AND MaVe IN (SELECT V.MaVe FROM Ve V JOIN ChuyenBay CB ON V.MaChuyenBay = CB.MaChuyenBay JOIN DuongBay DB ON CB.MaDuongBay = DB.MaDuongBay JOIN SanBay SBDi ON DB.MaSanBayDi = SBDi.MaSanBay WHERE SBDi.TenSanBay = ?)");
        }
        if (!"Sân bay đến".equals(sanBayDen)) {
            conditions.append(" AND MaVe IN (SELECT V.MaVe FROM Ve V JOIN ChuyenBay CB ON V.MaChuyenBay = CB.MaChuyenBay JOIN DuongBay DB ON CB.MaDuongBay = DB.MaDuongBay JOIN SanBay SBDen ON DB.MaSanBayDen = SBDen.MaSanBay WHERE SBDen.TenSanBay = ?)");
        }

        String finalQuery = baseQuery + conditions;

        try {
            prepareAndExecuteQuery(finalQuery, selectedDate, sanBayDi, sanBayDen, veDaDat_tbview, 1);
            prepareAndExecuteQuery(finalQuery, selectedDate, sanBayDi, sanBayDen, phDC_tbview, 0);
            if (veDaDat_tbview.getItems().isEmpty() || phDC_tbview.getItems().isEmpty()) {
                alert.errorMessage("Không tìm thấy dữ liệu.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Could not load data from the database.");
        }
    }

    @FXML
    void handleThanhToan() {
        CT_DatVe selectedVe = phDC_tbview.getSelectionModel().getSelectedItem();

        if (selectedVe == null) {
            alert.errorMessage("Vui lòng chọn một phiếu đặt chỗ để thanh toán.");
            return;
        }

        boolean confirm = alert.confirmationMessage("Bạn có chắc chắn muốn thanh toán phiếu đặt chỗ này?");
        if (confirm) {
            try {
                callUpdateTicketStatusProcedure(selectedVe.getMaCT_DatVe(), 1);  // Call the procedure with status 1 (confirm)
                loadData();  // Reload data
                alert.successMessage("Thanh toán thành công.");
            } catch (SQLException e) {
                alert.errorMessage("Không thể thanh toán phiếu đặt chỗ.");
            }
        }
    }

    @FXML
    void cancelTicketOrReservation() {
        CT_DatVe selectedVeDaDat = veDaDat_tbview.getSelectionModel().getSelectedItem();
        CT_DatVe selectedPhDC = phDC_tbview.getSelectionModel().getSelectedItem();

        if (selectedVeDaDat == null && selectedPhDC == null) {
            alert.errorMessage("Vui lòng chọn một vé hoặc phiếu đặt chỗ để hủy.");
            return;
        }

        String message = "Bạn có chắc chắn muốn hủy ";
        CT_DatVe selected;
        if (selectedVeDaDat != null) {
            message += "vé này?";
            selected = selectedVeDaDat;
        } else {
            message += "phiếu đặt chỗ này?";
            selected = selectedPhDC;
        }

        boolean confirm = alert.confirmationMessage(message);
        if (confirm) {
            try {
                callUpdateTicketStatusProcedure(selected.getMaCT_DatVe(), 2);  // Call the procedure with status 2 (cancel)
                loadData();  // Reload the data
                alert.successMessage("Hủy vé hoặc phiếu đặt chỗ thành công.");
            } catch (SQLException e) {
                e.printStackTrace();
                alert.errorMessage("Không thể hủy vé hoặc phiếu đặt chỗ.");
            }
        }
    }

    @FXML
    void xuatVe(){
        CT_DatVe selectedVeDaDat = veDaDat_tbview.getSelectionModel().getSelectedItem();
        if (selectedVeDaDat == null) {
            alert.errorMessage("Vui lòng chọn một vé để xuất vé.");
            return;
        }

        String maVe = selectedVeDaDat.getMaVe();
        String tenKhachHang = "";
        try {
            tenKhachHang = getTenKhachHang(selectedVeDaDat.getMaKhachHang());
        } catch (Exception e) {
            e.printStackTrace();
            alert.errorMessage("Không thể lấy tên khách hàng.");
        }

        String SDT = getSDT(selectedVeDaDat.getMaKhachHang());
        String maGhe = getMaGhe(maVe);
        String hangVe = getTenHangVe(maVe);
        String giaVe = getGiaTien(maVe)+"";
        String sanBayDi = getSanBayDi(maVe);
        String sanBayDen = getSanBayDen(maVe);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");// dd-MM--yyyy
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        String ngayBay = "";
        String gioBay = "";

        Timestamp ngayBayTimestamp = getNgayBay(maVe);
        Timestamp gioBayTimestamp = getGioBay(maVe);

        if (ngayBayTimestamp != null) {
            ngayBay = dateFormat.format(ngayBayTimestamp);
        }

        if (gioBayTimestamp != null) {
            gioBay = timeFormat.format(gioBayTimestamp);
        }

        ReportController report = new ReportController();
        report.PrintVe(maVe, tenKhachHang,  SDT,  maGhe,  hangVe,  giaVe,  sanBayDi,  sanBayDen,  ngayBay,  gioBay);
        // Thực hiện các thao tác cần thiết với các giá trị này, ví dụ: in ra console hoặc thực hiện các xử lý khác
    }


    private Connection connect;

    private final AlertMessage alert = new AlertMessage();
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();
        // Setup table columns
        setupTableViewColumns(phDC_maVe_tbcl, phDC_tenKH_tbcl, phDC_sdt_tbcl, phDC_ngayBay_tbcl, phDC_hangVe_tbcl,
                phDC_maGhe_tbcl, phDC_sanBayDi_tbcl, phDC_sanBayDen_tbcl, phDC_gioBay_tbcl, phDC_giaTien_tbcl);

        setupTableViewColumns(veDD_maVe_tbcl, veDD_tenKH_tbcl, veDD_sdt_tbcl, veDD_ngayBay_tbcl, veDD_hangVe_tbcl,
                veDD_maGhe_tbcl, veDD_sanBayDi_tbcl, veDD_sanBayDen_tbcl, veDD_gioBay_tbcl, veDD_giaTien_tbcl);

        // Load data
        try {
            loadData();
        } catch (SQLException e) {
            alert.errorMessage("Could not load data from the database.");
        }

        refresh_btn.setOnAction(event -> {
            // Xóa hết các lựa chọn và đặt lại giá trị mặc định
            sanbaydi_menubtn.setText("Sân bay đi");
            sanbayden_menubtn.setText("Sân bay đến");
            ngay_datepicker.setValue(null);
            try {
                loadData();
            } catch (SQLException e) {
                alert.errorMessage("Could not load data from the database.");
            }
        });

        sanbaydi_menubtn.setOnShowing(event -> updateSanBayMenuItems());
        sanbayden_menubtn.setOnShowing(event -> updateSanBayMenuItems());

        // Add listener to enable/disable thanhToan_btn based on selection in phDC_tbview
        phDC_tbview.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends CT_DatVe> observable, CT_DatVe oldValue, CT_DatVe newValue) -> thanhToan_btn.setDisable(newValue == null)
        );
    }

    private void setupTableViewColumns(TableColumn<CT_DatVe, String> maVeCol,
                                       TableColumn<CT_DatVe, String> tenKHCol,
                                       TableColumn<CT_DatVe, String> sdtCol,
                                       TableColumn<CT_DatVe, String> ngayBayCol,
                                       TableColumn<CT_DatVe, String> hangVeCol,
                                       TableColumn<CT_DatVe, String> maGheCol,
                                       TableColumn<CT_DatVe, String> sanBayDiCol,
                                       TableColumn<CT_DatVe, String> sanBayDenCol,
                                       TableColumn<CT_DatVe, String> gioBayCol,
                                       TableColumn<CT_DatVe, Float> giaTienCol) {
        maVeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaVe()));
        tenKHCol.setCellValueFactory(cellData -> new SimpleStringProperty(getTenKhachHang(cellData.getValue().getMaKhachHang())));
        sdtCol.setCellValueFactory(cellData -> new SimpleStringProperty(getSDT(cellData.getValue().getMaKhachHang())));
        hangVeCol.setCellValueFactory(cellData -> new SimpleStringProperty(getTenHangVe(cellData.getValue().getMaVe())));
        maGheCol.setCellValueFactory(cellData -> new SimpleStringProperty(getMaGhe(cellData.getValue().getMaVe())));
        sanBayDiCol.setCellValueFactory(cellData -> new SimpleStringProperty(getSanBayDi(cellData.getValue().getMaVe())));
        sanBayDenCol.setCellValueFactory(cellData -> new SimpleStringProperty(getSanBayDen(cellData.getValue().getMaVe())));

        ngayBayCol.setCellValueFactory(cellData -> {
            Timestamp ngayBayTimestamp = getNgayBay(cellData.getValue().getMaVe());
            LocalDate ngayBayDate = (ngayBayTimestamp != null) ? ngayBayTimestamp.toLocalDateTime().toLocalDate() : null;
            return new SimpleStringProperty(parseLocalDate(ngayBayDate).orElse(""));
        });


        gioBayCol.setCellValueFactory(cellData -> {
            Timestamp gioBayTimestamp = getGioBay(cellData.getValue().getMaVe());
            LocalDateTime gioBayDateTime = (gioBayTimestamp != null) ? gioBayTimestamp.toLocalDateTime() : null;
            return new SimpleStringProperty(parseLocalDateTime(gioBayDateTime).orElse(""));
        });

        giaTienCol.setCellValueFactory(cellData -> {
            float giaTien = getGiaTien(cellData.getValue().getMaVe());
            return new SimpleFloatProperty(giaTien).asObject();
        });
    }

    private Optional<String> parseLocalDate(LocalDate date) {
        try {
            return Optional.of(date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    private Optional<String> parseLocalDateTime(LocalDateTime dateTime) {
        try {
            if (dateTime != null) {
                return Optional.of(dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private void loadData() throws SQLException {
        veDaDat_tbview.getItems().clear();
        phDC_tbview.getItems().clear();

        String queryVeDaDat = "SELECT * FROM CT_DATVE WHERE TrangThai = 1";
        PreparedStatement prepare = connect.prepareStatement(queryVeDaDat);
        ResultSet result = prepare.executeQuery();

        while (result.next()) {
            Timestamp ngayMuaVeTimestamp = result.getTimestamp("NgayMuaVe");
            LocalDateTime ngayMuaVe = (ngayMuaVeTimestamp != null) ? ngayMuaVeTimestamp.toLocalDateTime() : null;

            Timestamp ngayThanhToanTimestamp = result.getTimestamp("NgayThanhToan");
            LocalDateTime ngayThanhToan = (ngayThanhToanTimestamp != null) ? ngayThanhToanTimestamp.toLocalDateTime() : null;

            CT_DatVe datVe = new CT_DatVe(
                    result.getString("MaCT_DATVE"),
                    result.getString("MaVe"),
                    result.getString("MaKhachHang"),
                    ngayMuaVe,
                    ngayThanhToan,
                    result.getString("TrangThai")
            );

            veDaDat_tbview.getItems().add(datVe);
        }

        String queryPhDC = "SELECT * FROM CT_DATVE WHERE TrangThai = 0";
        prepare = connect.prepareStatement(queryPhDC);
        result = prepare.executeQuery();

        while (result.next()) {
            Timestamp ngayMuaVeTimestamp = result.getTimestamp("NgayMuaVe");
            LocalDateTime ngayMuaVe = (ngayMuaVeTimestamp != null) ? ngayMuaVeTimestamp.toLocalDateTime() : null;

            Timestamp ngayThanhToanTimestamp = result.getTimestamp("NgayThanhToan");
            LocalDateTime ngayThanhToan = (ngayThanhToanTimestamp != null) ? ngayThanhToanTimestamp.toLocalDateTime() : null;

            CT_DatVe datVe = new CT_DatVe(
                    result.getString("MaCT_DATVE"),
                    result.getString("MaVe"),
                    result.getString("MaKhachHang"),
                    ngayMuaVe,
                    ngayThanhToan,
                    result.getString("TrangThai")
            );

            phDC_tbview.getItems().add(datVe);
        }
    }

    // Lấy tên khách hàng từ mã khách hàng
    public String getTenKhachHang(String p_MaKhachHang) {
        String tenKhachHang;
        try (CallableStatement statement = connect.prepareCall("{call GET_TEN_KHACH_HANG(?, ?)}")) {
            statement.setString(1, p_MaKhachHang);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.execute();
            tenKhachHang = statement.getString(2);
        } catch (Exception e) {
            tenKhachHang = "";
        }
        return tenKhachHang;
    }

    // Lấy sân bay đi từ mã vé
    public String getSanBayDi(String p_MaVe) {
        String sanBayDi;
        try (CallableStatement statement = connect.prepareCall("{call GET_SAN_BAY_DI(?, ?)}")) {
            statement.setString(1, p_MaVe);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.execute();
            sanBayDi = statement.getString(2);
        } catch (Exception e) {
            sanBayDi = "";
        }
        return sanBayDi;
    }

    // Lấy sân bay đến từ mã vé
    public String getSanBayDen(String p_MaVe)  {
        String sanBayDen;
        try (CallableStatement statement = connect.prepareCall("{call GET_SAN_BAY_DEN(?, ?)}")) {
            statement.setString(1, p_MaVe);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.execute();
            sanBayDen = statement.getString(2);
        } catch (Exception e) {
            sanBayDen = "";
        }
        return sanBayDen;
    }

    // Lấy ngày bay từ mã vé
    public Timestamp getNgayBay(String p_MaVe) {
        Timestamp ngayBay;
        try (CallableStatement statement = connect.prepareCall("{call GET_NGAY_BAY(?, ?)}")) {
            statement.setString(1, p_MaVe);
            statement.registerOutParameter(2, Types.TIMESTAMP);
            statement.execute();
            ngayBay = statement.getTimestamp(2);
        } catch (Exception e) {
            ngayBay = null;
        }
        return ngayBay;
    }

    // Lấy giờ bay từ mã vé
    public Timestamp getGioBay(String p_MaVe)  {
        Timestamp gioBay;
        try (CallableStatement statement = connect.prepareCall("{call GET_GIO_BAY(?, ?)}")) {
            statement.setString(1, p_MaVe);
            statement.registerOutParameter(2, Types.TIMESTAMP);
            statement.execute();
            gioBay = statement.getTimestamp(2);
        } catch (Exception e) {
            gioBay = null;
        }
        return gioBay;
    }

    // Lấy số điện thoại từ mã khách hàng
    public String getSDT(String p_MaKhachHang) {
        String sdt;
        try (CallableStatement statement = connect.prepareCall("{call GET_SDT(?, ?)}")) {
            statement.setString(1, p_MaKhachHang);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.execute();
            sdt = statement.getString(2);
        } catch (Exception e) {
            sdt = "";
        }
        return sdt;
    }

    // Lấy mã ghế từ mã vé
    public String getMaGhe(String p_MaVe)  {
        String maGhe;
        try (CallableStatement statement = connect.prepareCall("{call GET_MA_GHE(?, ?)}")) {
            statement.setString(1, p_MaVe);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.execute();
            maGhe = statement.getString(2);
        } catch (Exception e) {
            maGhe = "";
        }
        return maGhe;
    }

    // Lấy giá tiền từ mã vé
    public float getGiaTien(String p_MaVe)  {
        float giaTien;
        try (CallableStatement statement = connect.prepareCall("{call GET_GIA_TIEN(?, ?)}")) {
            statement.setString(1, p_MaVe);
            statement.registerOutParameter(2, Types.FLOAT);
            statement.execute();
            giaTien = statement.getFloat(2);
        } catch (Exception e) {
            giaTien = 0;
        }
        return giaTien;
    }

    // Lấy tên hạng vé từ mã hạng vé
    public String getTenHangVe(String p_MaVe) {
        String tenHangVe;
        try (CallableStatement statement = connect.prepareCall("{call GET_TEN_HANG_VE(?, ?)}")) {
            statement.setString(1, p_MaVe);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.execute();
            tenHangVe = statement.getString(2);
        } catch (Exception e) {
            tenHangVe = "";
        }
        return tenHangVe;
    }

    private void prepareAndExecuteQuery(String query, LocalDateTime selectedDate, String sanBayDi, String sanBayDen, TableView<CT_DatVe> tableView, int trangThai) throws SQLException {
        try (PreparedStatement prepare = connect.prepareStatement(query)) {
            prepare.setInt(1, trangThai);
            int paramIndex = 2;

            if (selectedDate != null) {
                prepare.setTimestamp(paramIndex++, Timestamp.valueOf(selectedDate));
            }
            if (!"Sân bay đi".equals(sanBayDi)) {
                prepare.setString(paramIndex++, sanBayDi);
            }
            if (!"Sân bay đến".equals(sanBayDen)) {
                prepare.setString(paramIndex++, sanBayDen);
            }

            try (ResultSet result = prepare.executeQuery()) {
                while (result.next()) {
                    LocalDateTime ngayMuaVe = result.getTimestamp("NgayMuaVe") != null ? result.getTimestamp("NgayMuaVe").toLocalDateTime() : null;
                    LocalDateTime ngayThanhToan = result.getTimestamp("NgayThanhToan") != null ? result.getTimestamp("NgayThanhToan").toLocalDateTime() : null;

                    CT_DatVe datVe = new CT_DatVe(
                            result.getString("MaCT_DATVE"),
                            result.getString("MaVe"),
                            result.getString("MaKhachHang"),
                            ngayMuaVe,
                            ngayThanhToan,
                            result.getString("TrangThai")
                    );

                    tableView.getItems().add(datVe);
                }
            }
        }
    }

    private void callUpdateTicketStatusProcedure(String maCT_DATVE, int trangThai) throws SQLException {
        String sql = "{call update_ticket_status(?, ?)}";  // SQL to call the stored procedure
        try (CallableStatement callableStatement = connect.prepareCall(sql)) {
            callableStatement.setString(1, maCT_DATVE);
            callableStatement.setInt(2, trangThai);
            callableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Có lỗi xảy ra khi cập nhật trạng thái vé.");
        }
    }

    private void updateSanBayMenuItems() {
        // Clear the current items in the menu
        sanbaydi_menubtn.getItems().clear();
        sanbayden_menubtn.getItems().clear();

        // Use a Set to avoid duplicates
        Set<String> sanBayDiSet = new HashSet<>();
        Set<String> sanBayDenSet = new HashSet<>();

        // Fetch the airports for the booked tickets table view
        for (CT_DatVe ctDatVe : veDaDat_tbview.getItems()) {
            String sanBayDi = getSanBayDi(ctDatVe.getMaVe());
            String sanBayDen = getSanBayDen(ctDatVe.getMaVe());
            if (!sanBayDi.isEmpty()) sanBayDiSet.add(sanBayDi);
            if (!sanBayDen.isEmpty()) sanBayDenSet.add(sanBayDen);
        }

        // Fetch the airports for the pending tickets table view
        for (CT_DatVe ctDatVe : phDC_tbview.getItems()) {
            String sanBayDi = getSanBayDi(ctDatVe.getMaVe());
            String sanBayDen = getSanBayDen(ctDatVe.getMaVe());
            if (!sanBayDi.isEmpty()) sanBayDiSet.add(sanBayDi);
            if (!sanBayDen.isEmpty()) sanBayDenSet.add(sanBayDen);
        }

        // Add the list of airports to the departure menu button
        for (String sanBay : sanBayDiSet) {
            MenuItem menuItem = new MenuItem(sanBay);
            menuItem.setOnAction(event -> sanbaydi_menubtn.setText(sanBay));
            sanbaydi_menubtn.getItems().add(menuItem);
        }

        // Add the list of airports to the arrival menu button
        for (String sanBay : sanBayDenSet) {
            MenuItem menuItem = new MenuItem(sanBay);
            menuItem.setOnAction(event -> sanbayden_menubtn.setText(sanBay));
            sanbayden_menubtn.getItems().add(menuItem);
        }
    }
}



