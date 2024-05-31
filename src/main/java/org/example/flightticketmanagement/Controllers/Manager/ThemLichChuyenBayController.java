package org.example.flightticketmanagement.Controllers.Manager;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import org.example.flightticketmanagement.Models.DatabaseDriver;

import java.net.URL;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ThemLichChuyenBayController implements Initializable {
    @FXML
    private TextField gia_txtfld;

    @FXML
    private ComboBox<String> gioBay_combobox;

    @FXML
    private ComboBox<String> gioHaCanh_combobox;

    @FXML
    private TableView<CT_HangVe> hangVe_tableview;

    @FXML
    private MFXButton luu_btn;

    @FXML
    private TextField maChuyenBay_txtfld;

    @FXML
    private TextField maDuongBay_txtfld;

    @FXML
    private DatePicker ngayBay_datepicker;

    @FXML
    private DatePicker ngayHaCanh_datepicker;

    @FXML
    private TableColumn<CT_HangVe, Integer> soLuongGhe_tbcl;

    @FXML
    private ComboBox<String> tenDuongBay_combobox;

    @FXML
    private TableColumn<CT_HangVe, String> tenHangVe_tbcl;

    @FXML
    private MFXButton themHangVe_btn;

    @FXML
    private TextField thoiGianBay_txtfld;

    @FXML
    private MFXButton xoaHangVe_btn;

    private int MIN_FLIGHT_HOURS;
    // Reference to the parent controller
    private LichChuyenBayController parentController;

    public void setParentController(LichChuyenBayController parentController) {
        this.parentController = parentController;
    }



    @FXML
    void xoaHangVe() {
        CT_HangVe selectedHangVe = hangVe_tableview.getSelectionModel().getSelectedItem();

        if (selectedHangVe != null) {
            boolean confirmed = alert.confirmationMessage("Bạn có chắc chắn muốn xóa hạng vé này?");

            if (confirmed) {
                hangVe_tableview.getItems().remove(selectedHangVe);
                alert.successMessage("Hạng vé đã được xóa thành công.");
            }
        } else {
            alert.errorMessage("Vui lòng chọn một hạng vé để xóa.");
        }
    }


    @FXML
    void luuButtonClicked() {
        try {

            // Check if all the required fields are filled
            if (maChuyenBay_txtfld.getText().isEmpty() || maDuongBay_txtfld.getText().isEmpty() ||
                    ngayBay_datepicker.getValue() == null || ngayHaCanh_datepicker.getValue() == null ||
                    gioBay_combobox.getValue() == null || gioHaCanh_combobox.getValue() == null ||
                    gia_txtfld.getText().isEmpty()) {
                alert.errorMessage("Hãy điền đủ thông tin.");
                return;
            }

            // Check if the flight date is less than or equal to the landing date
            if (ngayBay_datepicker.getValue().isAfter(ngayHaCanh_datepicker.getValue())) {
                alert.errorMessage("Ngày xuất phát không thể sau ngày hạ cánh.");
                return;
            }

            // Check if the flight duration is within the minimum and maximum flight hours
            LocalDateTime departure = LocalDateTime.of(ngayBay_datepicker.getValue(),
                    LocalTime.parse(gioBay_combobox.getValue(), DateTimeFormatter.ofPattern("HH:mm:ss")));
            LocalDateTime arrival = LocalDateTime.of(ngayHaCanh_datepicker.getValue(),
                    LocalTime.parse(gioHaCanh_combobox.getValue(), DateTimeFormatter.ofPattern("HH:mm:ss")));
            Duration duration = Duration.between(departure, arrival);
            long flightHours = duration.toHours();

            if (flightHours < MIN_FLIGHT_HOURS) {
                alert.errorMessage("Độ dài chuyến bay phải lớn hơn " + MIN_FLIGHT_HOURS + " giờ.");
                return;
            }

            // Check if there is at least one ticket class information added
            if (hangVe_tableview.getItems().isEmpty()) {
                alert.errorMessage("Vui lòng thêm ít nhất một hạng vé cho chuyến bay.");
                return;
            }
            // Lấy thông tin từ các trường nhập liệu
            String maChuyenBay = maChuyenBay_txtfld.getText();
            String maDuongBay = maDuongBay_txtfld.getText();
            LocalDateTime thoiGianXuatPhat = LocalDateTime.of(ngayBay_datepicker.getValue(),
                    LocalTime.parse(gioBay_combobox.getValue(), DateTimeFormatter.ofPattern("HH:mm:ss")));
            LocalDateTime thoiGianHaCanh = LocalDateTime.of(ngayHaCanh_datepicker.getValue(),
                    LocalTime.parse(gioHaCanh_combobox.getValue(), DateTimeFormatter.ofPattern("HH:mm:ss")));
            double giaVe = Double.parseDouble(gia_txtfld.getText());

            // Tạo câu lệnh SQL để chèn dữ liệu vào bảng CHUYENBAY
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

            // Tạo các câu lệnh SQL để chèn dữ liệu vào bảng CT_HANGVE và thực thi chúng
            for (CT_HangVe ctHangVe : hangVe_tableview.getItems()) {
                String insertCTHangVeQuery = "INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) " +
                        "VALUES (?, ?, ?, 0)";
                PreparedStatement insertCTHangVeStatement = connect.prepareStatement(insertCTHangVeQuery);
                insertCTHangVeStatement.setString(1, maChuyenBay);
                insertCTHangVeStatement.setString(2, ctHangVe.getMaHangVe());
                insertCTHangVeStatement.setInt(3, ctHangVe.getSoGheTrong());

                // Thực thi câu lệnh SQL chèn dữ liệu vào bảng CT_HANGVE
                insertCTHangVeStatement.executeUpdate();
            }

            // Hiển thị thông báo thành công
            alert.successMessage("Dữ liệu đã được lưu xuống cơ sở dữ liệu thành công.");
            if (parentController != null) {
                parentController.layDuLieu(null, null, null);
            }
            refreshTableView();
            closeStage();
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Đã xảy ra lỗi khi lưu dữ liệu xuống cơ sở dữ liệu.");
        }
    }

    private void refreshTableView() {
        // Fetch the updated data from the database
        if (parentController != null) {
            parentController.layDuLieu(null, null, null);
        }
    }

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();




    @FXML
    void moThemHangVeChuyenBay() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Manager/ThemHangVe_ChuyenBay.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Thêm Hạng Vé - Chuyến Bay");
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
            stage.setScene(new Scene(root));

            ThemHangVe_ChuyenBayController controller = loader.getController();
            controller.setParentController(this);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            alert.errorMessage("Could not load the ThemHangVe_ChuyenBay.fxml file.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();
        populateTenDuongBayComboBox();
        populateTimeComboBox(gioBay_combobox);
        populateTimeComboBox(gioHaCanh_combobox);

        // Retrieve MIN_FLIGHT_HOURS from the THAMSO table
        String query = "SELECT GiaTri FROM THAMSO WHERE MaThuocTinh = 'TGBTT'";
        try (PreparedStatement ps = connect.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                MIN_FLIGHT_HOURS = rs.getInt("GiaTri");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Could not fetch the minimum flight hours.");
        }

        tenDuongBay_combobox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                maDuongBay_txtfld.setText(getMaDuongBayByTen(newValue));
            }
        });

        generateMaChuyenBay();

        for (ComboBox<String> stringComboBox : Arrays.asList(gioBay_combobox, gioHaCanh_combobox)) {
            stringComboBox.valueProperty().addListener((obs, oldVal, newVal) -> tinhThoiGianBay());
        }
        ngayBay_datepicker.valueProperty().addListener((obs, oldVal, newVal) -> tinhThoiGianBay());
        ngayHaCanh_datepicker.valueProperty().addListener((obs, oldVal, newVal) -> tinhThoiGianBay());

        // Initialize table columns
        tenHangVe_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(getTenHangVe(cellData.getValue().getMaHangVe())));
        soLuongGhe_tbcl.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSoGheTrong()).asObject());

        // Initialize the TableView with an empty ObservableList
        hangVe_tableview.setItems(FXCollections.observableArrayList());
    }

    private void generateMaChuyenBay() {
        String generatedCode = generateNewMaChuyenBay();
        maChuyenBay_txtfld.setText(generatedCode);
    }

    private String generateNewMaChuyenBay() {
        String sql = "SELECT MAX(MaChuyenBay) AS MaxMaChuyenBay FROM CHUYENBAY";
        try (PreparedStatement ps = connect.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String maxMaVe = rs.getString("MaxMaChuyenBay");
                if (maxMaVe != null) {
                    int newMaVeNumber = Integer.parseInt(maxMaVe.substring(2)) + 1;
                    return String.format("CB%03d", newMaVeNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "CB001";
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

    private String getMaDuongBayByTen(String tenDuongBay) {
        String query = "SELECT MaDuongBay FROM DuongBay WHERE TenDuongBay = ?";
        try {
            prepare = connect.prepareStatement(query);
            prepare.setString(1, tenDuongBay);
            result = prepare.executeQuery();
            if (result.next()) {
                return result.getString("MaDuongBay");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Could not fetch route code.");
        }
        return null;
    }

    private void populateTimeComboBox(ComboBox<String> comboBox) {
        for (int h = 0; h < 24; h++) {
            for (int m = 0; m < 60; m++) {
                comboBox.getItems().add(String.format("%02d:%02d:00", h, m));
            }
        }
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

    public void themCT_HangVeToTable(CT_HangVe ctHangVe) {
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

        // Cập nhật lại TableView
        hangVe_tableview.setItems(ctHangVeList);
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

    public void setLichChuyenBayController(LichChuyenBayController parentController) {
        this.parentController = parentController;
    }
}
