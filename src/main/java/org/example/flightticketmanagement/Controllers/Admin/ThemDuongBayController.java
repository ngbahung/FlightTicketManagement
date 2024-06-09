package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Controllers.Manager.ThemHangVe_ChuyenBayController;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.SanBay;
import org.example.flightticketmanagement.Models.SanBayTrungGian;

public class ThemDuongBayController implements Initializable {

    @FXML
    private MFXButton luuDuongBay_btn;

    @FXML
    private TextField maDuongBay_txf;

    @FXML
    private MFXComboBox<String> sanBayDen_cbx;

    @FXML
    private MFXComboBox<String> sanBayDi_cbx;

    @FXML
    private TextField tenDuongBay_txf;

    @FXML
    private TableView<SanBayTrungGian> sanBayTrungGian_tbview;

    @FXML
    private TableColumn<SanBayTrungGian, Integer> stt_tbcl;

    @FXML
    private TableColumn<SanBayTrungGian, String> tenSBTG_tbcl;

    @FXML
    private MFXButton themSBTG_btn;

    @FXML
    private TableColumn<SanBayTrungGian, String> thoiGianDung_tbcl;

    @FXML
    private MFXButton xoaSBTG_btn;

    @FXML
    void moThemSanBayTrungGian(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/ThemSBTG.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Thêm sân bay trung gian");
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
            stage.setScene(new Scene(root));

            ThemSBTGController controller = loader.getController();
            controller.setParentController(this);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            alert.errorMessage("Could not load the ThemSBTG.fxml file.");
        }
    }

    @FXML
    void xoaSBTG(ActionEvent event) {
        SanBayTrungGian selectedSBTG = sanBayTrungGian_tbview.getSelectionModel().getSelectedItem();
        if (selectedSBTG == null) {
            alert.errorMessage("Vui lòng chọn sân bay trung gian để xóa.");
            return;
        }

        boolean confirmed = alert.confirmationMessage("Bạn có chắc chắn muốn xóa sân bay trung gian này không?");
        if (!confirmed) {
            return;
        }

        deleteSBTG(selectedSBTG.getMaDuongBay(), selectedSBTG.getMaSanBay(), selectedSBTG.getThuTu());
        reorderSBTG(selectedSBTG.getMaDuongBay());

        sanBayTrungGianTempList.remove(selectedSBTG);
        sanBayTrungGian_tbview.getItems().clear();
        sanBayTrungGian_tbview.getItems().addAll(sanBayTrungGianTempList);

        for (int i = 0; i < sanBayTrungGianTempList.size(); i++) {
            sanBayTrungGianTempList.get(i).setThuTu(i + 1);
        }
    }

    private void reorderSBTG(String maDuongBay) {
        String selectSql = "SELECT MaSanBay, ThuTu FROM SANBAYTG WHERE MaDuongBay = ? ORDER BY ThuTu";
        String updateSql = "UPDATE SANBAYTG SET ThuTu = ? WHERE MaDuongBay = ? AND MaSanBay = ?";

        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement selectStmt = connect.prepareStatement(selectSql);
             PreparedStatement updateStmt = connect.prepareStatement(updateSql)) {

            selectStmt.setString(1, maDuongBay);
            ResultSet result = selectStmt.executeQuery();

            int thuTu = 1;
            while (result.next()) {
                String maSanBay = result.getString("MaSanBay");

                updateStmt.setInt(1, thuTu);
                updateStmt.setString(2, maDuongBay);
                updateStmt.setString(3, maSanBay);
                updateStmt.executeUpdate();

                thuTu++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Lỗi khi sắp xếp lại thứ tự sân bay trung gian: " + e.getMessage());
        }
    }

    private void deleteSBTG(String maDuongBay, String maSanBay, int thuTu) {
        String sql = "DELETE FROM SANBAYTG WHERE MaDuongBay = ? AND MaSanBay = ? AND ThuTu = ?";
        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {
            prepare.setString(1, maDuongBay);
            prepare.setString(2, maSanBay);
            prepare.setInt(3, thuTu);
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Lỗi khi xóa sân bay trung gian: " + e.getMessage());
        }
    }

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    private QuyDinhController parentController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fetch data from SANBAY table
        List<String> sanBayList = getSanBayData();
        sanBayDi_cbx.getItems().addAll(sanBayList);
        sanBayDen_cbx.getItems().addAll(sanBayList);

        // Initialize table columns
        stt_tbcl.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getThuTu()).asObject());
        tenSBTG_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(getTenSanBayTrungGian(cellData.getValue().getMaSanBay())));
        thoiGianDung_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(formatThoiGianDung(cellData.getValue().getThoiGianDung())));

        sanBayDi_cbx.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            String tenVietTatDi = getTenVietTat(newValue);
            updateTenDuongBay();
        });

        sanBayDen_cbx.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            String tenVietTatDen = getTenVietTat(newValue);
            updateTenDuongBay();
        });

        // Set the default value for MaDuongBay
        maDuongBay_txf.setText(generateMaDuongBay());

        luuDuongBay_btn.setOnAction(this::luuDuongBay);
    }

    private List<String> getSanBayData() {
        List<String> sanBayList = new ArrayList<>();
        String sql = "SELECT TenSanBay FROM SANBAY";
        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql);
             ResultSet result = prepare.executeQuery()) {
            while (result.next()) {
                sanBayList.add(result.getString("TenSanBay"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanBayList;
    }

    public String getTenSanBayTrungGian(String maSanBay) {
        String query = "SELECT TenSanBay FROM SanBay WHERE MaSanBay = ?";
        try {
            prepare = connect.prepareStatement(query);
            prepare.setString(1, maSanBay);
            result = prepare.executeQuery();
            if (result.next()) {
                return result.getString("TenSanBay");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Could not fetch airport name.");
        }
        return ""; // Trả về chuỗi rỗng nếu không tìm thấy tên sân bay
    }

    public void setParentController(QuyDinhController parentController) {
        this.parentController = parentController;
    }

    private List<SanBayTrungGian> sanBayTrungGianTempList = new ArrayList<>();

    public void addSBTGTemp(String sanBay, String thoiGianDung) {
        String maSanBay = getMaSanBay(sanBay);
        if (maSanBay == null) {
            alert.errorMessage("Không tìm thấy mã sân bay cho sân bay: " + sanBay);
            return;
        }

        // Validate against departure and arrival airports
        if (sanBay.equals(sanBayDi_cbx.getValue()) || sanBay.equals(sanBayDen_cbx.getValue())) {
            alert.errorMessage("Sân bay trung gian không được trùng với sân bay đi hoặc sân bay đến.");
            return;
        }

        // Check if the intermediate airport is already in the list
        for (SanBayTrungGian sbtg : sanBayTrungGianTempList) {
            if (sbtg.getMaSanBay().equals(maSanBay)) {
                alert.errorMessage("Sân bay trung gian đã tồn tại trong danh sách.");
                return;
            }
        }
        if (maSanBay == null) {
            alert.errorMessage("Không tìm thấy mã sân bay cho sân bay: " + sanBay);
            return;
        }

        // Xác định thứ tự tiếp theo cho sân bay trung gian
        int thuTu = sanBayTrungGianTempList.size() + 1;

        // Tạo đối tượng SanBayTrungGian và thêm vào danh sách tạm thời
        SanBayTrungGian sbtg = new SanBayTrungGian("", maSanBay, thuTu, thoiGianDung);
        sanBayTrungGianTempList.add(sbtg);

        // Cập nhật TableView
        sanBayTrungGian_tbview.getItems().clear();
        sanBayTrungGian_tbview.getItems().addAll(sanBayTrungGianTempList);
    }

    public void addSBTG(String maDuongBay, String maSanBay, String thoiGianDung) {

        // Xác định thứ tự tiếp theo cho sân bay trung gian
        int thuTu = getNextThuTu(maDuongBay);

        // Chuyển đổi thời gian dừng thành định dạng INTERVAL
        String interval = "INTERVAL '0 " + thoiGianDung + "' DAY TO SECOND";

        // Lưu dữ liệu vào cơ sở dữ liệu với truy vấn động
        String sql = "INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung) VALUES (?, ?, ?, " + interval + ")";
        try (Connection connect = DatabaseDriver.getConnection();
             Statement statement = connect.createStatement()) {
            String formattedSQL = String.format("INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung) VALUES ('%s', '%s', %d, %s)",
                    maDuongBay, maSanBay, thuTu, interval);
            int rowsAffected = statement.executeUpdate(formattedSQL);
            if (rowsAffected > 0) {
                // Thêm vào TableView nếu lưu thành công
                SanBayTrungGian sbtg = new SanBayTrungGian(maDuongBay, maSanBay, thuTu, thoiGianDung);
                sanBayTrungGian_tbview.getItems().add(sbtg);
            } else {
                alert.errorMessage("Thêm sân bay trung gian thất bại");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Lỗi khi thêm sân bay trung gian: " + e.getMessage());
        }
    }

    private int getNextThuTu(String maDuongBay) {
        String sql = "SELECT MAX(ThuTu) AS MaxThuTu FROM SANBAYTG WHERE MaDuongBay = ?";
        int thuTu = 1; // Mặc định là 1 nếu MaDuongBay chưa tồn tại
        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {
            prepare.setString(1, maDuongBay);
            try (ResultSet result = prepare.executeQuery()) {
                if (result.next() && result.getInt("MaxThuTu") > 0) {
                    thuTu = result.getInt("MaxThuTu") + 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Lỗi khi xác định thứ tự: " + e.getMessage());
        }
        return thuTu;
    }

    private String getMaSanBay(String tenSanBay) {
        String maSanBay = null;
        String sql = "SELECT MaSanBay FROM SANBAY WHERE TenSanBay = ?";
        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {
            prepare.setString(1, tenSanBay);
            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    maSanBay = result.getString("MaSanBay");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maSanBay;
    }

    @FXML
    void luuDuongBay(ActionEvent event) {
        String sanBayDi = sanBayDi_cbx.getSelectionModel().getSelectedItem();
        String sanBayDen = sanBayDen_cbx.getSelectionModel().getSelectedItem();
        String tenDuongBay = tenDuongBay_txf.getText();

        if (sanBayDi.equals(sanBayDen)) {
            alert.errorMessage("Sân bay đi và sân bay đến không được trùng nhau!");
            return;
        }

        if (tenDuongBay.isEmpty()) {
            alert.errorMessage("Vui lòng điền đầy đủ thông tin");
            return;
        }

        for (SanBayTrungGian sbtg : sanBayTrungGianTempList) {
            if (sbtg.getMaSanBay().equals(getMaSanBay(sanBayDi)) || sbtg.getMaSanBay().equals(getMaSanBay(sanBayDen))) {
                alert.errorMessage("Sân bay trung gian không được trùng với sân bay đi hoặc sân bay đến.");
                return;
            }
        }

        String sql = "INSERT INTO DUONGBAY(MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, TrangThai) VALUES(?, ?, ?, ?, ?)";
        String maDuongBay = generateMaDuongBay();
        String maSanBayDi = getMaSanBay(sanBayDi);
        String maSanBayDen = getMaSanBay(sanBayDen);

        try {
            connect = DatabaseDriver.getConnection();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, maDuongBay);
            prepare.setString(2, maSanBayDi);
            prepare.setString(3, maSanBayDen);
            prepare.setString(4, tenDuongBay);
            prepare.setInt(5, 1);

            int rowsAffected = prepare.executeUpdate();
            if (rowsAffected > 0) {
                alert.successMessage("Thêm đường bay thành công");

                // Lưu các sân bay trung gian từ danh sách tạm vào cơ sở dữ liệu
                for (SanBayTrungGian sbtg : sanBayTrungGianTempList) {
                    addSBTG(maDuongBay, sbtg.getMaSanBay(), sbtg.getThoiGianDung());
                }

                if (parentController != null) {
                    parentController.refreshDuongBayData();
                }
                closeWindow();
            } else {
                alert.errorMessage("Thêm đường bay thất bại");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateMaDuongBay() {
        String maDuongBay = "DB001";
        CallableStatement callableStmt = null;

        try {
            connect = DatabaseDriver.getConnection();
            callableStmt = connect.prepareCall("{CALL GENERATE_MA_DUONGBAY(?)}");
            callableStmt.registerOutParameter(1, java.sql.Types.VARCHAR);

            callableStmt.execute();
            maDuongBay = callableStmt.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maDuongBay;
    }

    private String getTenVietTat(String tenSanBay) {
        String tenVietTat = null;
        String sql = "SELECT TenVietTat FROM SANBAY WHERE TenSanBay = ?";
        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {
            prepare.setString(1, tenSanBay);
            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    tenVietTat = result.getString("TenVietTat");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenVietTat;
    }

    private void updateTenDuongBay() {
        if (sanBayDi_cbx.getValue() != null && sanBayDen_cbx.getValue() != null) {
            String tenDuongBay = getTenVietTat(sanBayDi_cbx.getValue()) + "-" + getTenVietTat(sanBayDen_cbx.getValue());
            tenDuongBay_txf.setText(tenDuongBay);
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) luuDuongBay_btn.getScene().getWindow();
        stage.close();
    }

    public String getMaDuongBay() {
        return maDuongBay_txf.getText();
    }

    private String formatThoiGianDung(String thoiGianDung) {
        try {
            // Assuming thoiGianDung is in the format "HH:mm:ss"
            String[] parts = thoiGianDung.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            int seconds = Integer.parseInt(parts[2]);
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } catch (Exception e) {
            e.printStackTrace();
            alert.errorMessage("Could not format thoiGianDung.");
            return thoiGianDung;
        }
    }
}
