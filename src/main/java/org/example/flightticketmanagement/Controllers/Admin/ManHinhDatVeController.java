package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.ChuyenBay;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.Ve;

import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ManHinhDatVeController implements Initializable {
    @FXML
    private MFXTextField maKH_txtfld;

    @FXML
    private MFXTextField diaChi_txtfld;

    @FXML
    private MFXTextField cccd_txtfld;

    @FXML
    private MFXButton datCho_btn;

    @FXML
    private MFXButton datVe_btn;

    @FXML
    private MFXTextField email_txtfld;

    @FXML
    private TableColumn<Ve, String> giaTien_tbcl;

    @FXML
    private MFXTextField gioBay_txtfld;

    @FXML
    private TableColumn<Ve, String> hangVe_tbcl;

    @FXML
    private MFXTextField hoten_txtfld;

    @FXML
    private MFXTextField maCB_txtfld;

    @FXML
    private TableColumn<Ve, Integer> maGhe_tbcl;

    @FXML
    private MFXTextField maGhe_txtfld;

    @FXML
    private MFXTextField maVe_txtfld;

    @FXML
    private TableColumn<Ve, String> mave_tbcl;

    @FXML
    private MFXTextField ngayBay_txtfld;

    @FXML
    private MFXButton quayLai_btn;

    @FXML
    private MFXTextField sanBayDen_txtfld;

    @FXML
    private MFXTextField sanBayDi_txtfld;

    @FXML
    private MFXTextField sdt_txtfld;

    @FXML
    private MFXTextField thanhTien_txtfld;

    @FXML
    private TableView<Ve> ve_tableview;

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();

        // Thiết lập các cột của TableView
        mave_tbcl.setCellValueFactory(new PropertyValueFactory<>("maVe"));
        hangVe_tbcl.setCellValueFactory(new PropertyValueFactory<>("maHangVe"));
        maGhe_tbcl.setCellValueFactory(new PropertyValueFactory<>("maGhe"));
        giaTien_tbcl.setCellValueFactory(new PropertyValueFactory<>("giaTien"));

        // Add listener to the table view selection
        ve_tableview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showTicketDetails(newValue);
            }
        });

        // Set text fields to be non-editable
        maKH_txtfld.setEditable(false);
        maGhe_txtfld.setEditable(false);
        maVe_txtfld.setEditable(false);
        thanhTien_txtfld.setEditable(false);

        // Set button action handlers
        datCho_btn.setOnAction(e -> handleBooking(false));
        datVe_btn.setOnAction(e -> handleBooking(true));
    }

    public void setFlightDetails(ChuyenBay flight) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        maCB_txtfld.setText(flight.getMaChuyenBay());
        sanBayDi_txtfld.setText(getSanBayDi(flight.getMaDuongBay()));
        sanBayDen_txtfld.setText(getSanBayDen(flight.getMaDuongBay()));
        ngayBay_txtfld.setText(flight.getThoiGianXuatPhat().format(dateFormatter));
        gioBay_txtfld.setText(flight.getThoiGianXuatPhat().format(timeFormatter));

        // Gọi phương thức để tải các vé của chuyến bay
        loadVeForFlight(flight.getMaChuyenBay());
    }

    private String getSanBayDi(String maDuongBay) {
        String sql = "SELECT sb.TenSanBay FROM DUONGBAY db " +
                "JOIN SANBAY sb ON db.MaSanBayDi = sb.MaSanBay " +
                "WHERE db.MaDuongBay = ?";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, maDuongBay);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("TenSanBay");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "San Bay Di"; // Placeholder if not found
    }

    private String getSanBayDen(String maDuongBay) {
        String sql = "SELECT sb.TenSanBay FROM DUONGBAY db " +
                "JOIN SANBAY sb ON db.MaSanBayDen = sb.MaSanBay " +
                "WHERE db.MaDuongBay = ?";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, maDuongBay);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("TenSanBay");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "San Bay Den"; // Placeholder if not found
    }

    private void loadVeForFlight(String maChuyenBay) {
        String sql = "SELECT VE.MAVE, VE.MACHUYENBAY, VE.MAHANGVE, VE.MAGHE, VE.GIATIEN FROM VE, CT_DATVE WHERE VE.MAVE = CT_DATVE.MAVE AND VE.MACHUYENBAY = ? AND CT_DATVE.TRANGTHAI = 0";

        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, maChuyenBay);
            result = ps.executeQuery();

            ve_tableview.getItems().clear();

            while (result.next()) {
                Ve ve = new Ve(
                        result.getString("MAVE"),
                        result.getString("MACHUYENBAY"),
                        result.getString("MAHANGVE"),
                        result.getInt("MAGHE"),
                        result.getFloat("GIATIEN")
                );
                ve_tableview.getItems().add(ve);
            }

            hangVe_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(getTenHangVe(cellData.getValue().getMaHangVe())));

        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while loading tickets for the flight.");
        }
    }

    private String getTenHangVe(String maHangVe) {
        String sql = "SELECT HV.TENHANGVE FROM VE V " +
                "JOIN HANGVE HV ON HV.MAHANGVE = V.MAHANGVE " +
                "WHERE V.MAHANGVE = ?";
        try (Connection conn = DatabaseDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHangVe);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("TENHANGVE");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Tên hạng vé";
    }

    private void showTicketDetails(Ve ve) {
        maGhe_txtfld.setText(String.valueOf(ve.getMaGhe()));
        maVe_txtfld.setText(ve.getMaVe());
        thanhTien_txtfld.setText(String.valueOf(ve.getGiaTien()));
    }

    private void handleBooking(boolean isDatVe) {
        if (validateInputs()) {
            generateCustomerId();

            // Further actions to handle the booking process (insert into database, etc.)
            if (isDatVe) {
                // Handle dat ve logic
                insertBooking(true);
            } else {
                // Handle dat cho logic
                insertBooking(false);
            }
        }
    }

    private boolean validateInputs() {
        String hoten = hoten_txtfld.getText().trim();
        String cccd = cccd_txtfld.getText().trim();
        String email = email_txtfld.getText().trim();
        String sdt = sdt_txtfld.getText().trim();
        String diaChi = diaChi_txtfld.getText().trim();

        if (hoten.isEmpty() || cccd.isEmpty() || email.isEmpty() || sdt.isEmpty() || diaChi.isEmpty()) {
            alert.errorMessage("Vui lòng nhập đầy đủ thông tin.");
            return false;
        }

        if (cccd.length() != 12 || !cccd.matches("\\d+")) {
            alert.errorMessage("CCCD phải gồm 12 số.");
            return false;
        }

        if (!email.endsWith("@gmail.com")) {
            alert.errorMessage("Email phải có đuôi @gmail.com.");
            return false;
        }

        if (sdt.length() != 10 || !sdt.matches("\\d+")) {
            alert.errorMessage("Số điện thoại phải gồm 10 số.");
            return false;
        }

        return true;
    }

    private void generateCustomerId() {
        String sql = "SELECT MAX(MAKHACHHANG) AS MaxMaKH\n" +
                "FROM VE JOIN CT_DATVE ON VE.MAVE = CT_DATVE.MAVE";
        try (PreparedStatement ps = connect.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String maxMaKH = rs.getString("MaxMaKH");
                if (maxMaKH != null) {
                    int newMaKH = Integer.parseInt(maxMaKH.substring(2)) + 1;
                    maKH_txtfld.setText(String.format("KH%03d", newMaKH));
                } else {
                    maKH_txtfld.setText("KH001");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Không thể tạo mã khách hàng mới.");
        }
    }

    private void insertBooking(boolean isDatVe) {
        String maKH = maKH_txtfld.getText().trim();
        String hoten = hoten_txtfld.getText().trim();
        String cccd = cccd_txtfld.getText().trim();
        String email = email_txtfld.getText().trim();
        String sdt = sdt_txtfld.getText().trim();
        String diaChi = diaChi_txtfld.getText().trim();
        String maVe = maVe_txtfld.getText().trim();

        // Generate a new unique MaCT_DATVE
        String maCT_DATVE = generateMaCT_DATVE();

        // Get current timestamp for NgayMuaVe and NgayThanhToan
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String ngayMuaVe = now.format(formatter);
        String ngayThanhToan = isDatVe ? now.format(formatter) : null;  // Set to current time if isDatVe, otherwise null

        try {
            // Insert customer into KHACHHANG table if not exists
            String checkCustomerSql = "SELECT COUNT(*) FROM KHACHHANG WHERE MAKHACHHANG = ?";
            try (PreparedStatement ps = connect.prepareStatement(checkCustomerSql)) {
                ps.setString(1, maKH);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next() && rs.getInt(1) == 0) {
                        String insertCustomerSql = "INSERT INTO KHACHHANG (MAKHACHHANG, HOTEN, CCCD, EMAIL, SDT, DIACHI) VALUES (?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement insertPs = connect.prepareStatement(insertCustomerSql)) {
                            insertPs.setString(1, maKH);
                            insertPs.setString(2, hoten);
                            insertPs.setString(3, cccd);
                            insertPs.setString(4, email);
                            insertPs.setString(5, sdt);
                            insertPs.setString(6, diaChi);
                            insertPs.executeUpdate();
                        }
                    }
                }
            }

            // Insert booking into CT_DATVE table
            String insertBookingSql = "INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES (?, ?, ?, TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS'), ?)";
            try (PreparedStatement ps = connect.prepareStatement(insertBookingSql)) {
                ps.setString(1, maCT_DATVE);
                ps.setString(2, maVe);
                ps.setString(3, maKH);
                ps.setString(4, ngayMuaVe);
                ps.setString(5, ngayThanhToan);
                ps.setInt(6, isDatVe ? 1 : 0);  // 1 for datVe, 0 for datCho
                ps.executeUpdate();
            }

            alert.successMessage(isDatVe ? "Đặt vé thành công!" : "Đặt chỗ thành công!");

            // Clear fields after successful booking
            clearFields();

        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Có lỗi xảy ra khi đặt vé hoặc đặt chỗ.");
        }
    }

    private String generateMaCT_DATVE() {
        String sql = "SELECT MAX(MaCT_DATVE) AS MaxMaCT_DATVE FROM CT_DATVE";
        try (PreparedStatement ps = connect.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String maxMaCT_DATVE = rs.getString("MaxMaCT_DATVE");
                if (maxMaCT_DATVE != null) {
                    int newMaCT_DATVE = Integer.parseInt(maxMaCT_DATVE.substring(4)) + 1;
                    return String.format("CTDV%03d", newMaCT_DATVE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "CTDV001";  // Default value if no existing records
    }


    private void clearFields() {
        maKH_txtfld.clear();
        hoten_txtfld.clear();
        cccd_txtfld.clear();
        email_txtfld.clear();
        sdt_txtfld.clear();
        diaChi_txtfld.clear();
        maVe_txtfld.clear();
        maGhe_txtfld.clear();
        thanhTien_txtfld.clear();
    }

}
