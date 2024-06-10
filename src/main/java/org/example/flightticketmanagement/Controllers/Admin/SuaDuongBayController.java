package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.DuongBay;
import org.example.flightticketmanagement.Models.SanBayTrungGian;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SuaDuongBayController implements Initializable {

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

    private DuongBay selectedDuongBay;

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

    private QuyDinhController parentController;

    private final AlertMessage alert = new AlertMessage();

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    void luuDuongBay(ActionEvent event) {
        String maDuongBay = maDuongBay_txf.getText();
        String tenSanBayDi = sanBayDi_cbx.getValue();
        String tenSanBayDen = sanBayDen_cbx.getValue();
        String tenDuongBay = tenDuongBay_txf.getText();
        String maSanBayDi = getMaSanBay(tenSanBayDi);
        String maSanBayDen = getMaSanBay(tenSanBayDen);

        // Kiểm tra trùng lặp sân bay đi và sân bay đến
        if (tenSanBayDi.equals(tenSanBayDen)) {
            alert.errorMessage("Sân bay đi và sân bay đến không được trùng nhau");
            return;
        }

        // Kiểm tra trùng lặp sân bay trung gian với sân bay đi và sân bay đến
        for (SanBayTrungGian sbtg : sanBayTrungGianTempList) {
            String tenSanBayTG = getTenSanBayTrungGian(sbtg.getMaSanBay());
            if (tenSanBayTG.equals(tenSanBayDi) || tenSanBayTG.equals(tenSanBayDen)) {
                alert.errorMessage("Sân bay trung gian không được trùng với sân bay đi hoặc sân bay đến");
                return;
            }
        }

        // Kiểm tra trùng lặp trong danh sách sân bay trung gian
        for (int i = 0; i < sanBayTrungGianTempList.size(); i++) {
            for (int j = i + 1; j < sanBayTrungGianTempList.size(); j++) {
                if (sanBayTrungGianTempList.get(i).getMaSanBay().equals(sanBayTrungGianTempList.get(j).getMaSanBay())) {
                    alert.errorMessage("Các sân bay trung gian không được trùng nhau");
                    return;
                }
            }
        }

        String sql = "UPDATE DUONGBAY SET MaSanBayDi = ?, MaSanBayDen = ?, TenDuongBay = ? WHERE MaDuongBay = ?";
        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {
            prepare.setString(1, maSanBayDi);
            prepare.setString(2, maSanBayDen);
            prepare.setString(3, tenDuongBay);
            prepare.setString(4, maDuongBay);

            int rowsUpdated = prepare.executeUpdate();
            if (rowsUpdated > 0) {
                alert.successMessage("Cập nhật đường bay thành công");

                // Lưu và cập nhật các sân bay trung gian
                for (SanBayTrungGian sbtg : sanBayTrungGianTempList) {
                    if (sbtg.getMaDuongBay().isEmpty()) {
                        // Sân bay trung gian mới
                        addSBTG(maDuongBay, sbtg.getMaSanBay(), sbtg.getThoiGianDung());
                    } else {
                        // Cập nhật sân bay trung gian hiện có
                        updateSBTG(maDuongBay, sbtg.getMaSanBay(), sbtg.getThoiGianDung(), sbtg.getThuTu());
                    }
                }

                parentController.refreshDuongBayData();
                closeWindow();
            } else {
                alert.errorMessage("Cập nhật đường bay thất bại");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Cập nhật đường bay thất bại");
        }
    }


    @FXML
    void moThemSanBayTrungGian(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/ThemSBTG_Sua.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Thêm sân bay trung gian");
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
            stage.setScene(new Scene(root));

            ThemSBTG_SuaController controller = loader.getController();
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



    private List<SanBayTrungGian> sanBayTrungGianTempList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();
        // Fetch data from SANBAY table
        List<String> sanBayList = getSanBayData();
        sanBayDi_cbx.getItems().addAll(sanBayList);
        sanBayDen_cbx.getItems().addAll(sanBayList);

        sanBayDi_cbx.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            updateTenDuongBay();
        });

        sanBayDen_cbx.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            updateTenDuongBay();
        });
        luuDuongBay_btn.setOnAction(this::luuDuongBay);

        stt_tbcl.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getThuTu()).asObject());
        tenSBTG_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(getTenSanBayTrungGian(cellData.getValue().getMaSanBay())));
        thoiGianDung_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(getThoiGianDungFormatted(cellData.getValue().getThoiGianDung())));
    }

    public void addSBTGTemp(String sanBay, String thoiGianDung) {
        // Lấy mã sân bay từ tên sân bay
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
        // Format the thoiGianDung string to ensure it ends with ":00"
        thoiGianDung = thoiGianDung.replace("0.0", "00");

        // Remove the leading "0 " part
        if (thoiGianDung.startsWith("0 ")) {
            thoiGianDung = thoiGianDung.substring(2);
        }

        // Xác định thứ tự tiếp theo cho sân bay trung gian
        int thuTu = getNextThuTu(maDuongBay);

        // Chuyển đổi thời gian dừng thành định dạng INTERVAL
        String interval = String.format("INTERVAL '0 %s' DAY TO SECOND", thoiGianDung);

        // Lưu dữ liệu vào cơ sở dữ liệu với truy vấn động
        String sql = "INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung) VALUES (?, ?, ?, " + interval + ")";
        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {
            prepare.setString(1, maDuongBay);
            prepare.setString(2, maSanBay);
            prepare.setInt(3, thuTu);
            int rowsAffected = prepare.executeUpdate();
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

    public void setDuongBay(DuongBay selectedDuongBay) {
        this.selectedDuongBay = selectedDuongBay;
        populateFields();
    }

    public void setParentController(QuyDinhController quyDinhController) {
        this.parentController = quyDinhController;
    }

    private void closeWindow() {
        Stage stage = (Stage) luuDuongBay_btn.getScene().getWindow();
        stage.close();
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

    private String getMaSanBay(String tenSanBay) {
        String maSanBay = null;
        String sql = "SELECT MaSanBay FROM SANBAY WHERE TenSanBay = ?";
        try {
            connect = DatabaseDriver.getConnection();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, tenSanBay);
            result = prepare.executeQuery();
            if (result.next()) {
                maSanBay = result.getString("MaSanBay");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maSanBay;
    }

    private void updateTenDuongBay() {
        if (sanBayDi_cbx.getValue() != null && sanBayDen_cbx.getValue() != null) {
            String tenDuongBay = getTenVietTat(sanBayDi_cbx.getValue()) + "-" + getTenVietTat(sanBayDen_cbx.getValue());
            tenDuongBay_txf.setText(tenDuongBay);
        }
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

    private void populateFields() {
        if (selectedDuongBay != null) {
            maDuongBay_txf.setText(selectedDuongBay.getMaDuongBay());
            maDuongBay_txf.setDisable(true);
            tenDuongBay_txf.setText(selectedDuongBay.getTenDuongBay());
            // Set the selected items of the ComboBoxes based on the DuongBay object
            sanBayDi_cbx.getSelectionModel().selectItem(selectedDuongBay.getTenSanBayDi());
            sanBayDen_cbx.getSelectionModel().selectItem(selectedDuongBay.getTenSanBayDen());
            populateSanBayTrungGian();
        }
    }

    private void populateSanBayTrungGian() {
        // Xóa dữ liệu hiện có trong TableView
        sanBayTrungGian_tbview.getItems().clear();
        sanBayTrungGianTempList.clear();  // Clear the temp list before populating

        // Lấy danh sách các sân bay trung gian từ cơ sở dữ liệu dựa trên mã đường bay được chọn
        String sql = "SELECT * FROM SANBAYTG WHERE MaDuongBay = ?";
        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {
            prepare.setString(1, selectedDuongBay.getMaDuongBay());
            try (ResultSet result = prepare.executeQuery()) {
                // Duyệt qua các hàng trong kết quả
                while (result.next()) {
                    // Tạo đối tượng SanBayTrungGian từ dữ liệu trong cơ sở dữ liệu
                    SanBayTrungGian sbtg = new SanBayTrungGian(
                            result.getString("MaDuongBay"),
                            result.getString("MaSanBay"),
                            result.getInt("ThuTu"),
                            result.getString("ThoiGianDung")
                    );
                    // Thêm đối tượng SanBayTrungGian vào danh sách
                    sanBayTrungGianTempList.add(sbtg);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Lỗi khi lấy danh sách sân bay trung gian: " + e.getMessage());
        }

        // Hiển thị danh sách sân bay trung gian lên TableView
        sanBayTrungGian_tbview.getItems().addAll(sanBayTrungGianTempList);
    }

    private String getThoiGianDungFormatted(String thoiGianDung) {
        // Assuming thoiGianDung is in format "0 hh:mm:ss"
        if (thoiGianDung != null && !thoiGianDung.isEmpty()) {
            String[] parts = thoiGianDung.split(" ");
            if (parts.length == 2) {
                String[] timeParts = parts[1].split(":");
                if (timeParts.length == 3) {
                    int hours = Integer.parseInt(timeParts[0]);
                    int minutes = Integer.parseInt(timeParts[1]);
                    if (hours == 0) {
                        return String.format("%d:%02d", hours, minutes);
                    } else {
                        return String.format("%d:%02d", hours, minutes);
                    }
                }
            }
        }
        return thoiGianDung; // Return as is if format is unexpected
    }

    private void updateSBTG(String maDuongBay, String maSanBay, String thoiGianDung, int thuTu) {
        // Format the thoiGianDung string to ensure it ends with ":00"
        thoiGianDung = thoiGianDung.replace("0.0", "00");

        // Remove the leading "0 " part
        if (thoiGianDung.startsWith("0 ")) {
            thoiGianDung = thoiGianDung.substring(2);
        }

        // Chuyển đổi thời gian dừng thành định dạng INTERVAL
        String interval = String.format("INTERVAL '0 %s' DAY TO SECOND", thoiGianDung);

        String sql = "UPDATE SANBAYTG SET ThoiGianDung = " + interval + " WHERE MaDuongBay = ? AND MaSanBay = ? AND ThuTu = ?";
        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {
            prepare.setString(1, maDuongBay);
            prepare.setString(2, maSanBay);
            prepare.setInt(3, thuTu);
            int rowsUpdated = prepare.executeUpdate();
            if (rowsUpdated == 0) {
                alert.errorMessage("Cập nhật sân bay trung gian thất bại");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Lỗi khi cập nhật sân bay trung gian: " + e.getMessage());
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
}
