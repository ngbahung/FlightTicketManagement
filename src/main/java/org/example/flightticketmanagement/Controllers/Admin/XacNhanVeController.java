package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;

import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class XacNhanVeController implements Initializable {
    @FXML
    private MFXTextField cccd_txtfld;

    @FXML
    private MFXTextField diaChi_txtfld;

    @FXML
    private MFXTextField email_txtfld;

    @FXML
    private MFXTextField gioBay_txtfld;

    @FXML
    private MFXTextField hoten_txtfld;

    @FXML
    private MFXTextField maChuyenBay_txtfld;

    @FXML
    private MFXTextField maGhe_txtfld;

    @FXML
    private MFXTextField maKH_txtfld;

    @FXML
    private MFXTextField maVe_txtfld;

    @FXML
    private MFXTextField ngayBay_txtfld;

    @FXML
    private MFXTextField sanBayDen_txtfld;

    @FXML
    private MFXTextField sanBayDi_txtfld;

    @FXML
    private MFXTextField sdt_txtfld;

    @FXML
    private MFXTextField thanhTien_txtfld;

    @FXML
    private MFXButton sua_btn;
    @FXML
    private MFXButton huy_btn;
    @FXML
    private MFXButton datCho_btn;
    @FXML
    private MFXButton datVe_btn;

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    @FXML
    private void handleSuaButtonAction() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Xác nhận sửa");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Bạn có chắc chắn muốn hủy thao tác này không?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            deleteBooking();
            Stage stage = (Stage) sua_btn.getScene().getWindow();
            stage.close();
        }
        closeStage();
    }

    @FXML
    private void handleHuyButtonAction() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Xác nhận hủy");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Bạn có chắc chắn muốn hủy thao tác này không?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            clearFields();
            deleteBooking();
            if (manHinhDatVeController != null) {
                manHinhDatVeController.closeStage();
            }
            Stage stage = (Stage) huy_btn.getScene().getWindow();
            stage.close();
        }
    }




    private boolean isDatVe;

    private ManHinhDatVeController manHinhDatVeController;


    public void setManHinhDatVeController(ManHinhDatVeController controller) {
        this.manHinhDatVeController = controller;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();
        generateCustomerId(cccd_txtfld.getText());
        datVe_btn.setOnAction(e -> handleInsertBooking(true)); // for datVe (booking ticket)
        datCho_btn.setOnAction(e -> handleInsertBooking(false)); // for datCho (reserving seat)
    }

    public void initData(boolean isDatVe, String maKH, String hoten, String cccd, String email, String sdt,
                         String diaChi, String maVe, String maGhe, String thanhTien, String maChuyenBay,
                         String sanBayDi, String sanBayDen, String ngayBay, String gioBay) {
        this.isDatVe = isDatVe;
        maKH_txtfld.setText(maKH);
        hoten_txtfld.setText(hoten);
        cccd_txtfld.setText(cccd);
        email_txtfld.setText(email);
        sdt_txtfld.setText(sdt);
        diaChi_txtfld.setText(diaChi);
        maVe_txtfld.setText(maVe);
        maGhe_txtfld.setText(maGhe);
        thanhTien_txtfld.setText(thanhTien);
        maChuyenBay_txtfld.setText(maChuyenBay);
        sanBayDi_txtfld.setText(sanBayDi);
        sanBayDen_txtfld.setText(sanBayDen);
        ngayBay_txtfld.setText(ngayBay);
        gioBay_txtfld.setText(gioBay);
    }

    private void generateCustomerId(String cccd) {
        // SQL query to check if customer already exists
        String checkSql = "SELECT MAKHACHHANG FROM KHACHHANG WHERE CCCD = ?";
        // SQL query to get the maximum customer ID
        String maxIdSql = "SELECT MAX(MAKHACHHANG) AS MaxMaKH FROM KHACHHANG";

        try (PreparedStatement checkPs = connect.prepareStatement(checkSql)) {
            checkPs.setString(1, cccd);
            try (ResultSet checkRs = checkPs.executeQuery()) {
                if (checkRs.next()) {
                    // Customer exists, set the existing ID
                    String existingMaKH = checkRs.getString("MAKHACHHANG");
                    maKH_txtfld.setText(existingMaKH);
                } else {
                    // Customer does not exist, generate a new ID
                    try (PreparedStatement maxIdPs = connect.prepareStatement(maxIdSql);
                         ResultSet maxIdRs = maxIdPs.executeQuery()) {
                        if (maxIdRs.next()) {
                            String maxMaKH = maxIdRs.getString("MaxMaKH");
                            if (maxMaKH != null) {
                                int newMaKH = Integer.parseInt(maxMaKH.substring(2)) + 1;
                                maKH_txtfld.setText(String.format("KH%03d", newMaKH));
                            } else {
                                maKH_txtfld.setText("KH001");
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Không thể tạo mã khách hàng mới hoặc kiểm tra khách hàng hiện có.");
        }
    }


    private void handleInsertBooking(boolean isDatVe) {
        generateCustomerId(cccd_txtfld.getText());
        if (insertBooking(isDatVe)) {
            alert.successMessage(isDatVe ? "Đặt vé thành công!" : "Đặt chỗ thành công!");
            closeStage();
        } else {
            alert.errorMessage("Có lỗi xảy ra khi đặt vé hoặc đặt chỗ.");
        }
    }

    private boolean insertBooking(boolean isDatVe) {
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
        Timestamp ngayMuaVe = Timestamp.valueOf(now);
        Timestamp ngayThanhToan = isDatVe ? Timestamp.valueOf(now) : null;  // Set to current time if isDatVe, otherwise null

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
            String insertBookingSql = "INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connect.prepareStatement(insertBookingSql)) {
                ps.setString(1, maCT_DATVE);
                ps.setString(2, maVe);
                ps.setString(3, maKH);
                ps.setTimestamp(4, ngayMuaVe);
                if (ngayThanhToan == null) {
                    ps.setNull(5, java.sql.Types.TIMESTAMP);
                } else {
                    ps.setTimestamp(5, ngayThanhToan);
                }
                ps.setInt(6, isDatVe ? 1 : 0); // 1 for datVe, 0 for datCho
                ps.executeUpdate();
                return true; // Booking successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Booking failed
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

    private void deleteBooking() {
        String maVe = maVe_txtfld.getText().trim();
        String deleteBookingSql = "DELETE FROM VE WHERE MAVE = ?";
        try (PreparedStatement ps = connect.prepareStatement(deleteBookingSql)) {
            ps.setString(1, maVe);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Có lỗi xảy ra khi xóa vé.");
        }
    }


    private void closeStage() {
        Stage stage = (Stage) datVe_btn.getScene().getWindow();
        stage.close();
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
