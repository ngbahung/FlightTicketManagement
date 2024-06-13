package org.example.flightticketmanagement.Controllers.Admin;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.CT_DatVe;
import org.example.flightticketmanagement.Models.DatabaseDriver;

import java.io.IOException;
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

public class VeHuyController implements Initializable {
    @FXML
    private MFXTextField tenkhachhang_txtfld;

    @FXML
    private MFXTextField mave_txtfld;


    @FXML
    private MFXButton refresh_btn;

    @FXML
    private MenuButton sanbayden_menubtn;

    @FXML
    private MenuButton sanbaydi_menubtn;

    @FXML
    private MFXDatePicker ngay_datepicker;

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
    private TableView<CT_DatVe> veDaDat_tbview;

    @FXML
    void search() {
        LocalDateTime selectedDate = ngay_datepicker.getValue() != null ? ngay_datepicker.getValue().atStartOfDay() : null;
        String sanBayDi = sanbaydi_menubtn.getText();
        String sanBayDen = sanbayden_menubtn.getText();
        String maVe = mave_txtfld.getText().trim();
        String tenKhachHang = tenkhachhang_txtfld.getText().trim();

        if (selectedDate == null && "Sân bay đi".equals(sanBayDi) && "Sân bay đến".equals(sanBayDen) && maVe.isEmpty() && tenKhachHang.isEmpty()) {
            alert.errorMessage("Vui lòng nhập ít nhất một tiêu chí tìm kiếm.");
            return;
        }

        veDaDat_tbview.getItems().clear();

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
        if (!maVe.isEmpty()) {
            conditions.append(" AND MaVe = ?");
        }
        if (!tenKhachHang.isEmpty()) {
            conditions.append(" AND MaKhachHang IN (SELECT MaKhachHang FROM KhachHang WHERE HOTEN LIKE ?)");
        }

        String finalQuery = baseQuery + conditions;

        try {
            prepareAndExecuteQuery(finalQuery, selectedDate, sanBayDi, sanBayDen, maVe, tenKhachHang, veDaDat_tbview, 2);
            if (veDaDat_tbview.getItems().isEmpty()) {
                alert.errorMessage("Không tìm thấy dữ liệu.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private final EventBus eventBusVeBiHuy = XacNhanVeController.getEventBus();
    private final AlertMessage alert = new AlertMessage();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();
        eventBusVeBiHuy.register(this);
        setupTableViewColumns(veDD_maVe_tbcl, veDD_tenKH_tbcl, veDD_sdt_tbcl, veDD_ngayBay_tbcl, veDD_hangVe_tbcl,
                veDD_maGhe_tbcl, veDD_sanBayDi_tbcl, veDD_sanBayDen_tbcl, veDD_gioBay_tbcl, veDD_giaTien_tbcl);

        // Load data
        loadData();

        refresh_btn.setOnAction(event -> {
            // Xóa hết các lựa chọn và đặt lại giá trị mặc định
            sanbaydi_menubtn.setText("Sân bay đi");
            sanbayden_menubtn.setText("Sân bay đến");
            ngay_datepicker.setValue(null);
            mave_txtfld.setText("");
            tenkhachhang_txtfld.setText("");
            loadData();
        });

        sanbaydi_menubtn.setOnShowing(event -> updateSanBayMenuItems());
        sanbayden_menubtn.setOnShowing(event -> updateSanBayMenuItems());

    }

    @Subscribe
    public void handleUpdateData(Object event) {
        loadData();
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

    private void loadData() {
        try {
            veDaDat_tbview.getItems().clear();

            String queryVeDaDat = "SELECT * FROM CT_DATVE WHERE TrangThai = 2";
            try (PreparedStatement prepare = connect.prepareStatement(queryVeDaDat);
                 ResultSet result = prepare.executeQuery()) {

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
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    private void prepareAndExecuteQuery(String query, LocalDateTime selectedDate, String sanBayDi, String sanBayDen, String maVe, String tenKhachHang, TableView<CT_DatVe> tableView, int trangThai) throws SQLException {

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
            if (!maVe.isEmpty()) {
                prepare.setString(paramIndex++, maVe);
            }
            if (!tenKhachHang.isEmpty()) {
                prepare.setString(paramIndex++, "%" + tenKhachHang + "%");
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
}
