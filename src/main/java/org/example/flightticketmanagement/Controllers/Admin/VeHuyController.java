package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.flightticketmanagement.Models.CT_DatVe;
import org.example.flightticketmanagement.Models.DatabaseDriver;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class VeHuyController implements Initializable {

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


    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();

        setupTableViewColumns(veDD_maVe_tbcl, veDD_tenKH_tbcl, veDD_sdt_tbcl, veDD_ngayBay_tbcl, veDD_hangVe_tbcl,
                veDD_maGhe_tbcl, veDD_sanBayDi_tbcl, veDD_sanBayDen_tbcl, veDD_gioBay_tbcl, veDD_giaTien_tbcl);

        // Load data
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
