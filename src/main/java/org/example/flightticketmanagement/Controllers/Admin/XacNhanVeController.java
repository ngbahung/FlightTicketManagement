package org.example.flightticketmanagement.Controllers.Admin;

import com.google.common.eventbus.EventBus;
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

    private static final EventBus eventBus = new EventBus();

    public XacNhanVeController() {}

    public static EventBus getEventBus() {
        return eventBus;
    }

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
        closeStage(huy_btn);
    }

    @FXML
    private void handleHuyButtonAction() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Xác nhận hủy");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Bạn có chắc chắn muốn hủy thao tác này không?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            deleteBooking();
            if (manHinhDatVeController != null) {
                manHinhDatVeController.closeStage();
            }
            Stage stage = (Stage) huy_btn.getScene().getWindow();
            stage.close();
        }
    }

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

    public void initData(String maKH, String hoten, String cccd, String email, String sdt,
                         String diaChi, String maVe, String maGhe, String thanhTien, String maChuyenBay,
                         String sanBayDi, String sanBayDen, String ngayBay, String gioBay) {
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

    public void generateCustomerId(String cccd) {
        String sql = "{call GenerateCustomerId(?, ?)}";

        try (CallableStatement callableStatement = connect.prepareCall(sql)) {
            callableStatement.setString(1, cccd);
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.execute();

            String maKH = callableStatement.getString(2);
            maKH_txtfld.setText(maKH);
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Không thể tạo mã khách hàng mới hoặc kiểm tra khách hàng hiện có.");
        }
    }

    private void handleInsertBooking(boolean isDatVe) {
        generateCustomerId(cccd_txtfld.getText());
        int trangThai = isDatVe ? 1 : 0;
        if (insertBooking(isDatVe, trangThai, maChuyenBay_txtfld.getText().trim(), maGhe_txtfld.getText().trim())) {
            alert.successMessage(isDatVe ? "Đặt vé thành công!" : "Đặt chỗ thành công!");
            if (manHinhDatVeController != null) {
                manHinhDatVeController.closeStage();
            }
            eventBus.post(new Object());
            closeStage(isDatVe ? datVe_btn : datCho_btn);
        } else {
            alert.errorMessage("Có lỗi xảy ra khi đặt vé hoặc đặt chỗ.");
        }
    }

    private boolean insertBooking(boolean isDatVe, int trangThai, String maChuyenBay, String maHangVe) {
        String maKH = maKH_txtfld.getText().trim();
        String hoten = hoten_txtfld.getText().trim();
        String cccd = cccd_txtfld.getText().trim();
        String email = email_txtfld.getText().trim();
        String sdt = sdt_txtfld.getText().trim();
        String diaChi = diaChi_txtfld.getText().trim();
        String maVe = maVe_txtfld.getText().trim();

        LocalDateTime now = LocalDateTime.now();
        Timestamp ngayMuaVe = Timestamp.valueOf(now);
        Timestamp ngayThanhToan = isDatVe ? Timestamp.valueOf(now) : null;

        try {
            String callProcedureSql = "{CALL SellTicket(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            try (CallableStatement cs = connect.prepareCall(callProcedureSql)) {
                cs.setString(1, maKH);
                cs.setString(2, hoten);
                cs.setString(3, cccd);
                cs.setString(4, email);
                cs.setString(5, sdt);
                cs.setString(6, diaChi);
                cs.setString(7, maVe);
                cs.setTimestamp(8, ngayMuaVe);
                if (ngayThanhToan == null) {
                    cs.setNull(9, java.sql.Types.TIMESTAMP);
                } else {
                    cs.setTimestamp(9, ngayThanhToan);
                }
                cs.setInt(10, trangThai);
                cs.execute();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Có lỗi xảy ra khi đặt vé hoặc đặt chỗ.");
            return false;
        }
    }

    private void deleteBooking() {
        String maVe = maVe_txtfld.getText().trim();
        String deleteBookingSql = "DELETE FROM VE WHERE MAVE = ?";
        try {
            connect = DatabaseDriver.getConnection();
            prepare = connect.prepareStatement(deleteBookingSql);
            prepare.setString(1, maVe);
            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Có lỗi xảy ra khi xóa vé.");
        }
    }

    private void closeStage(MFXButton button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}