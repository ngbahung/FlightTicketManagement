package org.example.flightticketmanagement.Controllers.Manager;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.CT_HangVe;
import org.example.flightticketmanagement.Models.ChuyenBay;
import org.example.flightticketmanagement.Models.DatabaseDriver;

import java.net.URL;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SuaLichChuyenBayController implements Initializable {

    @FXML
    private TextField maChuyenBay_txtfld;

    @FXML
    private TextField maDuongBay_txtfld;

    @FXML
    private DatePicker ngayBay_datepicker;


    @FXML
    private DatePicker ngayHaCanh_datepicker;

    @FXML
    private ComboBox<String> gioBay_combobox;

    @FXML
    private ComboBox<String> gioHaCanh_combobox;

    @FXML
    private TextField gia_txtfld;

    @FXML
    private TableView<CT_HangVe> hangVe_tableview;

    @FXML
    private TableColumn<CT_HangVe, String> tenHangVe_tbcl;

    @FXML
    private TableColumn<CT_HangVe, Integer> soLuongGhe_tbcl;

    @FXML
    private TextField thoiGianBay_txtfld;

    @FXML
    private ComboBox<String> tenDuongBay_combobox;

    @FXML
    private MFXButton xoaHangVe_btn;

    @FXML
    private MFXButton themHangVe_btn;

    @FXML
    private MFXButton luu_btn;

    @FXML
    void moThemHangVeChuyenBay() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Manager/ThemHangVe_ChuyenBay2.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Thêm Hạng Vé - Chuyến Bay");
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
            stage.setScene(new Scene(root));

            ThemHangVe_ChuyenBay_2Controller controller = loader.getController();
            controller.setParentController(this);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            alert.errorMessage("Could not load the ThemHangVe_ChuyenBay.fxml file.");
        }
    }

    @FXML
    void xoaHangVe() {
        CT_HangVe selectedHangVe = hangVe_tableview.getSelectionModel().getSelectedItem(); // Changed to CT_HangVe

        if (selectedHangVe != null) {
            boolean confirmed = alert.confirmationMessage("Bạn có chắc chắn muốn xóa hạng vé này?");

            if (confirmed) {
                // Thực hiện xóa dữ liệu từ cơ sở dữ liệu
                String deleteQuery = "DELETE FROM CT_HANGVE WHERE MaChuyenBay = ? AND MaHangVe = ?";
                try {
                    PreparedStatement statement = connect.prepareStatement(deleteQuery);
                    statement.setString(1, getGeneratedMaChuyenBay()); // Sử dụng phương thức getGeneratedMaChuyenBay() để lấy mã chuyến bay
                    statement.setString(2, selectedHangVe.getMaHangVe()); // Changed to getMaHangVe() for CT_HangVe
                    statement.executeUpdate();

                    // Xóa thành công, cập nhật lại TableView
                    displayHangVe(getGeneratedMaChuyenBay());
                    alert.successMessage("Hạng vé đã được xóa thành công.");
                } catch (SQLException e) {
                    e.printStackTrace();
                    alert.errorMessage("Đã xảy ra lỗi khi xóa dữ liệu từ cơ sở dữ liệu.");
                }
            }
        } else {
            alert.errorMessage("Vui lòng chọn một hạng vé để xóa.");
        }
    }

    @FXML
    void xuLyLuaChonDuongBay() {
        String selectedTenDuongBay = tenDuongBay_combobox.getValue();
        if (selectedTenDuongBay != null && !selectedTenDuongBay.isEmpty()) {
            String maDuongBay = getMaDuongBayFromTenDuongBay(selectedTenDuongBay);
            maDuongBay_txtfld.setText(maDuongBay);
        }
    }

    @FXML
    void luuButtonClicked() {
        try {
            // Lấy thông tin từ các trường nhập liệu
            String maChuyenBay = maChuyenBay_txtfld.getText();
            String maDuongBay = maDuongBay_txtfld.getText();
            LocalDateTime thoiGianXuatPhat = LocalDateTime.of(ngayBay_datepicker.getValue(),
                    LocalTime.parse(gioBay_combobox.getValue(), DateTimeFormatter.ofPattern("HH:mm:ss")));
            LocalDateTime thoiGianHaCanh = LocalDateTime.of(ngayHaCanh_datepicker.getValue(),
                    LocalTime.parse(gioHaCanh_combobox.getValue(), DateTimeFormatter.ofPattern("HH:mm:ss")));
            double giaVe = Double.parseDouble(gia_txtfld.getText());

            // Tạo câu lệnh SQL để cập nhật dữ liệu vào bảng CHUYENBAY
            String updateChuyenBayQuery = "UPDATE CHUYENBAY SET MaDuongBay = ?, TGXP = ?, TGKT = ?, GiaVe = ? WHERE MaChuyenBay = ?";
            PreparedStatement updateChuyenBayStatement = connect.prepareStatement(updateChuyenBayQuery);
            updateChuyenBayStatement.setString(1, maDuongBay);
            updateChuyenBayStatement.setTimestamp(2, Timestamp.valueOf(thoiGianXuatPhat));
            updateChuyenBayStatement.setTimestamp(3, Timestamp.valueOf(thoiGianHaCanh));
            updateChuyenBayStatement.setDouble(4, giaVe);
            updateChuyenBayStatement.setString(5, maChuyenBay);

            // Thực thi câu lệnh SQL cập nhật dữ liệu vào bảng CHUYENBAY
            int rowsUpdated = updateChuyenBayStatement.executeUpdate();

            if (rowsUpdated == 0) {
                // Nếu không có hàng nào được cập nhật, chèn mới dữ liệu vào bảng CHUYENBAY
                String insertChuyenBayQuery = "INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe) " +
                        "VALUES (?, ?, ?, ?, 0, ?)";
                PreparedStatement insertChuyenBayStatement = connect.prepareStatement(insertChuyenBayQuery);
                insertChuyenBayStatement.setString(1, maChuyenBay);
                insertChuyenBayStatement.setString(2, maDuongBay);
                insertChuyenBayStatement.setTimestamp(3, Timestamp.valueOf(thoiGianXuatPhat));
                insertChuyenBayStatement.setTimestamp(4, Timestamp.valueOf(thoiGianHaCanh));
                insertChuyenBayStatement.setDouble(5, giaVe);

                // Thực thi câu lệnh SQL chèn dữ liệu vào bảng CHUYENBAY
                insertChuyenBayStatement.executeUpdate();
            }

            // Tạo các câu lệnh SQL để cập nhật dữ liệu vào bảng CT_HANGVE và thực thi chúng
            for (CT_HangVe ctHangVe : hangVe_tableview.getItems()) {
                String updateCTHangVeQuery = "UPDATE CT_HANGVE SET SoGheTrong = ? WHERE MaChuyenBay = ? AND MaHangVe = ?";
                PreparedStatement updateCTHangVeStatement = connect.prepareStatement(updateCTHangVeQuery);
                updateCTHangVeStatement.setInt(1, ctHangVe.getSoGheTrong());
                updateCTHangVeStatement.setString(2, maChuyenBay);
                updateCTHangVeStatement.setString(3, ctHangVe.getMaHangVe());

                // Thực thi câu lệnh SQL cập nhật dữ liệu vào bảng CT_HANGVE
                int ctRowsUpdated = updateCTHangVeStatement.executeUpdate();

                if (ctRowsUpdated == 0) {
                    // Nếu không có hàng nào được cập nhật, chèn mới dữ liệu vào bảng CT_HANGVE
                    String insertCTHangVeQuery = "INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) " +
                            "VALUES (?, ?, ?, 0)";
                    PreparedStatement insertCTHangVeStatement = connect.prepareStatement(insertCTHangVeQuery);
                    insertCTHangVeStatement.setString(1, maChuyenBay);
                    insertCTHangVeStatement.setString(2, ctHangVe.getMaHangVe());
                    insertCTHangVeStatement.setInt(3, ctHangVe.getSoGheTrong());

                    // Thực thi câu lệnh SQL chèn dữ liệu vào bảng CT_HANGVE
                    insertCTHangVeStatement.executeUpdate();
                }
            }

            // Hiển thị thông báo thành công
            alert.successMessage("Dữ liệu đã được lưu xuống cơ sở dữ liệu thành công.");
            if (parentController != null) {
                parentController.layDuLieu(null, null, null);
            }
            closeStage();
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Đã xảy ra lỗi khi lưu dữ liệu xuống cơ sở dữ liệu.");
        }
    }


    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private final AlertMessage alert = new AlertMessage();

    private LichChuyenBayController parentController;
    public void setParentController(LichChuyenBayController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();

        populateTenDuongBayComboBox();
        populateTimeComboBox(gioBay_combobox);
        populateTimeComboBox(gioHaCanh_combobox);

        gioBay_combobox.valueProperty().addListener((obs, oldVal, newVal) -> tinhThoiGianBay());
        gioHaCanh_combobox.valueProperty().addListener((obs, oldVal, newVal) -> tinhThoiGianBay());
        ngayBay_datepicker.valueProperty().addListener((obs, oldVal, newVal) -> tinhThoiGianBay());
        ngayHaCanh_datepicker.valueProperty().addListener((obs, oldVal, newVal) -> tinhThoiGianBay());

        tenHangVe_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(getTenHangVe(cellData.getValue().getMaHangVe())));
        soLuongGhe_tbcl.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSoGheTrong()).asObject());
    }

    public void setData(ChuyenBay chuyenBay) {
        // Thiết lập thông tin cơ bản
        maChuyenBay_txtfld.setText(chuyenBay.getMaChuyenBay());
        maDuongBay_txtfld.setText(chuyenBay.getMaDuongBay());
        // Thực hiện truy vấn SQL để lấy tên đường bay từ bảng DUONGBAY
        String tenDuongBay = getTenDuongBay(chuyenBay.getMaDuongBay());
        tenDuongBay_combobox.setValue(tenDuongBay); // Sử dụng setValue() thay vì valueProperty().setValue()

        // Thiết lập ngày và giờ xuất phát
        LocalDateTime thoiGianXuatPhat = chuyenBay.getThoiGianXuatPhat();
        if (thoiGianXuatPhat != null) {
            ngayBay_datepicker.setValue(thoiGianXuatPhat.toLocalDate());
            gioBay_combobox.getItems().add(thoiGianXuatPhat.toLocalTime().toString());
            gioBay_combobox.getSelectionModel().select(0);
        }

        // Thiết lập ngày và giờ hạ cánh
        LocalDateTime thoiGianKetThuc = chuyenBay.getThoiGianKetThuc();
        if (thoiGianKetThuc != null) {
            ngayHaCanh_datepicker.setValue(thoiGianKetThuc.toLocalDate());
            gioHaCanh_combobox.getItems().add(thoiGianKetThuc.toLocalTime().toString());
            gioHaCanh_combobox.getSelectionModel().select(0);
        }

        gia_txtfld.setText(String.valueOf(chuyenBay.getGiaVe()));
        tinhThoiGianBay();

        xuLyLuaChonDuongBay();

        // Hiển thị thông tin hạng vé trên tableview
        displayHangVe(chuyenBay.getMaChuyenBay());
    }

    private void displayHangVe(String maChuyenBay) {
        ObservableList<CT_HangVe> hangVeList = FXCollections.observableArrayList();
        String query = "SELECT HV.MaHangVe, HV.TenHangVe, CTHV.SoGheTrong FROM HANGVE HV JOIN CT_HANGVE CTHV ON HV.MaHangVe = CTHV.MaHangVe WHERE CTHV.MaChuyenBay = ?";
        try {
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setString(1, maChuyenBay);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String maHangVe = resultSet.getString("MaHangVe");
                String tenHangVe = resultSet.getString("TenHangVe");
                int soGheTrong = resultSet.getInt("SoGheTrong");
                CT_HangVe ctHangVe = new CT_HangVe(maChuyenBay, maHangVe, soGheTrong, 0);
                hangVeList.add(ctHangVe);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while retrieving seat count from the database.");
        }
        // Set items in the TableView
        hangVe_tableview.setItems(hangVeList);
    }

    private String getTenDuongBay(String maDuongBay) {
        String sql = "SELECT TenDuongBay FROM DUONGBAY WHERE MaDuongBay = ?";
        try {
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, maDuongBay);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("TenDuongBay");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while retrieving flight route name from the database.");
        }
        return "";
    }

    private void populateTimeComboBox(ComboBox<String> comboBox) {
        for (int h = 0; h < 24; h++) {
            for (int m = 0; m < 60; m++) {
                comboBox.getItems().add(String.format("%02d:%02d:00", h, m));
            }
        }
    }

    private void populateTenDuongBayComboBox() {
        String query = "SELECT TenDuongBay FROM DuongBay";
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                tenDuongBay_combobox.getItems().add(result.getString("TenDuongBay"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Could not populate route names.");
        }
    }

    private String getMaDuongBayFromTenDuongBay(String tenDuongBay) {
        String sql = "SELECT MaDuongBay FROM DUONGBAY WHERE TenDuongBay = ?";
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, tenDuongBay);
            result = prepare.executeQuery();
            if (result.next()) {
                return result.getString("MaDuongBay");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while retrieving flight route code from the database.");
        }
        return "";
    }

    private void tinhThoiGianBay() {
        if (ngayBay_datepicker.getValue() != null && ngayHaCanh_datepicker.getValue() != null &&
                gioBay_combobox.getValue() != null && gioHaCanh_combobox.getValue() != null) {
            LocalDateTime departure = LocalDateTime.of(ngayBay_datepicker.getValue(),
                    LocalTime.parse(gioBay_combobox.getValue(), DateTimeFormatter.ofPattern("HH:mm:ss")));
            LocalDateTime arrival = LocalDateTime.of(ngayHaCanh_datepicker.getValue(),
                    LocalTime.parse(gioHaCanh_combobox.getValue(), DateTimeFormatter.ofPattern("HH:mm:ss")));

            Duration duration = Duration.between(departure, arrival);
            thoiGianBay_txtfld.setText(String.format("%d giờ %d phút", duration.toHours(), duration.toMinutesPart()));
        }
    }

    public String getGeneratedMaChuyenBay() {
        return maChuyenBay_txtfld.getText();
    }

    public void themCT_HangVeTable(CT_HangVe ctHangVe) {
        ObservableList<CT_HangVe> ctHangVeList = hangVe_tableview.getItems();
        boolean found = false;

        // Kiểm tra xem CT_HangVe đã tồn tại trong danh sách chưa
        for (CT_HangVe existingCT : ctHangVeList) {
            if (existingCT.getMaHangVe().equals(ctHangVe.getMaHangVe())) {
                // Nếu đã tồn tại, cập nhật số ghế trống
                existingCT.setSoGheTrong(existingCT.getSoGheTrong() + ctHangVe.getSoGheTrong());
                found = true;
                break;
            }
        }

        // Nếu không tìm thấy CT_HangVe đã tồn tại, thêm mới vào danh sách
        if (!found) {
            ctHangVeList.add(ctHangVe);
        }
    }

    public String getTenHangVe(String maHangVe){
        String query = "SELECT TenHangVe FROM HANGVE WHERE MaHangVe = ?";
        try {
            prepare = connect.prepareStatement(query);
            prepare.setString(1, maHangVe);
            result = prepare.executeQuery();
            if (result.next()) {
                return result.getString("TenHangVe");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Could not fetch class name.");
        }
        return null;
    }

    private void closeStage() {
        Stage stage = (Stage) luu_btn.getScene().getWindow();
        stage.close();
    }
}
