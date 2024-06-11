package org.example.flightticketmanagement.Controllers.Manager;

import com.google.common.eventbus.EventBus;
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
import org.example.flightticketmanagement.Models.*;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
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
    private TableColumn<SanBayTrungGian, Integer> stt_tbcl;

    @FXML
    private TableColumn<SanBayTrungGian, String> tenSBTG_tbcl;

    @FXML
    private TableColumn<SanBayTrungGian, String> thoiGianDung_tbcl;

    @FXML
    private TableView<SanBayTrungGian> sanBayTrungGian_tbview;

    private int MIN_FLIGHT_HOURS;

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
            String maDuongBay = getMaDuongBayByTen(selectedTenDuongBay);
            maDuongBay_txtfld.setText(maDuongBay);
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

            // Extract flight hours from thoiGianBay_txtfld
            String thoiGianBay = thoiGianBay_txtfld.getText();
            int flightHours = getFlightHoursFromText(thoiGianBay);

            // Check if flightHours is less than the minimum flight hours
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
            BigDecimal giaVe;
            try {
                // Lấy giá trị từ text field và chuyển đổi sang số nguyên
                int giaVeNguyen = Integer.parseInt(gia_txtfld.getText());

                // Chuyển đổi số nguyên thành BigDecimal và định dạng thành số thập phân
                giaVe = BigDecimal.valueOf(giaVeNguyen).setScale(2, BigDecimal.ROUND_HALF_UP);
            } catch (NumberFormatException e) {
                alert.errorMessage("Giá tiền không hợp lệ. Vui lòng nhập một số nguyên hợp lệ.");
                return;
            }

            // Tạo câu lệnh SQL để cập nhật dữ liệu trong bảng CHUYENBAY
            String updateChuyenBayQuery = "UPDATE CHUYENBAY SET MaDuongBay = ?, TGXP = ?, TGKT = ?, GiaVe = ? WHERE MaChuyenBay = ?";
            connect = DatabaseDriver.getConnection();
            PreparedStatement updateChuyenBayStatement = connect.prepareStatement(updateChuyenBayQuery);
            updateChuyenBayStatement.setString(1, maDuongBay);
            updateChuyenBayStatement.setTimestamp(2, Timestamp.valueOf(thoiGianXuatPhat));
            updateChuyenBayStatement.setTimestamp(3, Timestamp.valueOf(thoiGianHaCanh));
            updateChuyenBayStatement.setBigDecimal(4, giaVe);
            updateChuyenBayStatement.setString(5, maChuyenBay);

            // Thực thi câu lệnh SQL cập nhật dữ liệu trong bảng CHUYENBAY
            updateChuyenBayStatement.executeUpdate();

            // Xóa các hạng vé cũ liên quan đến chuyến bay
            String deleteCTHangVeQuery = "DELETE FROM CT_HANGVE WHERE MaChuyenBay = ?";
            PreparedStatement deleteCTHangVeStatement = connect.prepareStatement(deleteCTHangVeQuery);
            deleteCTHangVeStatement.setString(1, maChuyenBay);
            deleteCTHangVeStatement.executeUpdate();

            // Tạo các câu lệnh SQL để chèn dữ liệu mới vào bảng CT_HANGVE và thực thi chúng
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
            alert.successMessage("Dữ liệu đã được cập nhật thành công.");
            if (parentController != null) {
                parentController.layDuLieu(null, null, null);
            }
            refreshTableView();
            eventBus.post(new Object());
            closeStage();
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Lỗi khi cập nhật dữ liệu vào cơ sở dữ liệu. Vui lòng kiểm tra lại.");
        } catch (NumberFormatException e) {
            alert.errorMessage("Lỗi khi chuyển đổi số giờ. Vui lòng kiểm tra định dạng.");
        }
    }


    private int getFlightHoursFromText(String text) {
        int flightHours = 0;
        try {
            String[] parts = text.split(" ");
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("ngày")) {
                    flightHours += Integer.parseInt(parts[i - 1]) * 24; // Chuyển ngày thành giờ
                } else if (parts[i].equals("giờ")) {
                    flightHours += Integer.parseInt(parts[i - 1]);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            alert.errorMessage("Lỗi khi chuyển đổi thời gian.");
        }
        return flightHours;
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
    private static final EventBus eventBus = new EventBus();

    public SuaLichChuyenBayController() {}

    public static EventBus getEventBus() {
        return eventBus;
    }
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

        // Retrieve MIN_FLIGHT_HOURS from the THAMSO table
        String query = "SELECT GiaTri FROM THAMSO WHERE MaThuocTinh = 'TGBTT'";
        try (PreparedStatement ps = connect.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                MIN_FLIGHT_HOURS = rs.getInt("GiaTri");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Could not retrieve flight duration from the database");
        }

        tenHangVe_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(getTenHangVe(cellData.getValue().getMaHangVe())));
        soLuongGhe_tbcl.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSoGheTrong()).asObject());

        tenDuongBay_combobox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                String maDuongBay = getMaDuongBayByTen(newVal);
                if (maDuongBay != null) {
                    maDuongBay_txtfld.setText(maDuongBay);
                    String thoiGianBay = getThoiGianBayByMaDuongBay(maDuongBay);
                    if (thoiGianBay != null) {
                        String formattedThoiGianBay = formatFlightTime(thoiGianBay);
                        thoiGianBay_txtfld.setText(formattedThoiGianBay);
                        updateArrivalDateTime();
                    }
                    populateSanBayTrungGianTableView(maDuongBay); // Sửa đổi
                }
            }
        });

        // Add listeners to update arrival date and time
        ngayBay_datepicker.valueProperty().addListener((observable, oldValue, newValue) -> updateArrivalDateTime());
        gioBay_combobox.valueProperty().addListener((observable, oldValue, newValue) -> updateArrivalDateTime());
        thoiGianBay_txtfld.textProperty().addListener((observable, oldValue, newValue) -> updateArrivalDateTime());
        gioBay_combobox.setOnMouseClicked(event -> {
            populateTimeComboBox(gioBay_combobox);
        });
        stt_tbcl.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getThuTu()).asObject());
        tenSBTG_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(getTenSanBayTrungGian(cellData.getValue().getMaSanBay())));
        thoiGianDung_tbcl.setCellValueFactory(cellData -> {
            String thoiGianDung = cellData.getValue().getThoiGianDung();
            return new SimpleStringProperty(formatThoiGianDung(thoiGianDung));
        });
    }

    public void setData(ChuyenBay chuyenBay) {
        // Thiết lập thông tin cơ bản
        maChuyenBay_txtfld.setText(chuyenBay.getMaChuyenBay());
        maDuongBay_txtfld.setText(chuyenBay.getMaDuongBay());

        // Thực hiện truy vấn SQL để lấy tên sân bay đi và tên sân bay đến từ bảng DUONGBAY
        String[] tenSanBay = getTenSanBayFromMaDuongBay(chuyenBay.getMaDuongBay());
        String tenDuongBay = tenSanBay[0] + " - " + tenSanBay[1];
        tenDuongBay_combobox.setValue(tenDuongBay);

        LocalDateTime thoiGianXuatPhat = chuyenBay.getThoiGianXuatPhat();
        if (thoiGianXuatPhat != null) {
            ngayBay_datepicker.setValue(thoiGianXuatPhat.toLocalDate());
            gioBay_combobox.getItems().clear(); // Clear previous items
            gioBay_combobox.getItems().add(thoiGianXuatPhat.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            gioBay_combobox.getSelectionModel().selectFirst(); // Select the first item
        } else {
            ngayBay_datepicker.setValue(null);
            gioBay_combobox.getItems().clear();
        }
        DecimalFormat df = new DecimalFormat("####");
        gia_txtfld.setText(df.format(chuyenBay.getGiaVe()));
        xuLyLuaChonDuongBay();
        displayHangVe(chuyenBay.getMaChuyenBay());
    }

    private String[] getTenSanBayFromMaDuongBay(String maDuongBay) {
        String[] tenSanBay = new String[2];
        String sql = "SELECT sb1.TenSanBay AS SanBayDi, sb2.TenSanBay AS SanBayDen " +
                "FROM DuongBay db " +
                "JOIN SanBay sb1 ON db.MaSanBayDi = sb1.MaSanBay " +
                "JOIN SanBay sb2 ON db.MaSanBayDen = sb2.MaSanBay " +
                "WHERE db.MaDuongBay = ?";
        try {
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, maDuongBay);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                tenSanBay[0] = resultSet.getString("SanBayDi");
                tenSanBay[1] = resultSet.getString("SanBayDen");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while retrieving airport names.");
        }
        return tenSanBay;
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

    private void populateSanBayTrungGianTableView(String maDuongBay) {
        String query = "SELECT sbt.MaSanBay, sb.TenSanBay, sbt.ThuTu, sbt.ThoiGianDung " +
                "FROM SANBAYTG sbt " +
                "JOIN SanBay sb ON sbt.MaSanBay = sb.MaSanBay " +
                "WHERE sbt.MaDuongBay = ? " +
                "ORDER BY sbt.ThuTu";
        ObservableList<SanBayTrungGian> sanBayTrungGianList = FXCollections.observableArrayList();
        Map<String, SanBay> sanBayMap = new HashMap<>();

        try {
            prepare = connect.prepareStatement(query);
            prepare.setString(1, maDuongBay);
            result = prepare.executeQuery();

            while (result.next()) {
                String maSanBay = result.getString("MaSanBay");
                String tenSanBay = result.getString("TenSanBay");
                int thuTu = result.getInt("ThuTu");
                String thoiGianDung = result.getString("ThoiGianDung");

                // Create or get SanBay object
                SanBay sanBay = sanBayMap.get(maSanBay);
                if (sanBay == null) {
                    sanBay = new SanBay(maSanBay, tenSanBay, null, null, null, null); // Only essential fields
                    sanBayMap.put(maSanBay, sanBay);
                }

                // Create SanBayTrungGian object
                SanBayTrungGian sanBayTrungGian = new SanBayTrungGian(maDuongBay, maSanBay, thuTu, thoiGianDung);
                sanBayTrungGianList.add(sanBayTrungGian);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Could not fetch intermediate airports.");
        }

        sanBayTrungGian_tbview.setItems(sanBayTrungGianList);
    }

    private String formatFlightTime(String thoiGianBay) {
        if (thoiGianBay != null) {
            // Tách chuỗi thời gian bay thành phần ngày và phần giờ:phút:giây.mili
            String[] parts = thoiGianBay.split(" ");
            int flightDays = Integer.parseInt(parts[0]); // Lấy số ngày bay

            // Tách phần giờ:phút:giây.mili thành các thành phần tương ứng
            String[] timeParts = parts[1].split(":");
            int flightHours = Integer.parseInt(timeParts[0]); // Lấy số giờ bay
            int flightMinutes = Integer.parseInt(timeParts[1]); // Lấy số phút bay
            String[] secondParts = timeParts[2].split("\\."); // Tách giây và mili giây
            int flightSeconds = Integer.parseInt(secondParts[0]); // Lấy số giây bay
            int flightMillis = secondParts.length > 1 ? Integer.parseInt(secondParts[1]) : 0; // Lấy số mili giây bay, mặc định là 0 nếu không có

            // Định dạng lại chuỗi thời gian bay
            return String.format("%d ngày %d giờ %d phút %d giây", flightDays, flightHours, flightMinutes, flightSeconds);
        }
        return null;
    }

    private String formatThoiGianDung(String thoiGianDung) {
        if (thoiGianDung == null || thoiGianDung.isEmpty()) {
            return "";
        }
        String[] parts = thoiGianDung.split(" ");
        if (parts.length == 2) {
            int days = Integer.parseInt(parts[0]);
            String timePart = parts[1];
            String[] timeComponents = timePart.split(":");

            int hours = Integer.parseInt(timeComponents[0]);
            int minutes = Integer.parseInt(timeComponents[1]);
            int seconds = Integer.parseInt(timeComponents[2].split("\\.")[0]); // ignore milliseconds

            StringBuilder formattedTime = new StringBuilder();

            if (days > 0) {
                formattedTime.append(days).append(" ngày ");
            }
            if (hours > 0) {
                formattedTime.append(hours).append(" giờ ");
            }
            if (minutes > 0) {
                formattedTime.append(minutes).append(" phút ");
            }
            if (seconds > 0) {
                formattedTime.append(seconds).append(" giây");
            }

            return formattedTime.toString().trim();
        }
        return thoiGianDung;
    }

    private void updateArrivalDateTime() {
        if (ngayBay_datepicker.getValue() == null || gioBay_combobox.getValue() == null || thoiGianBay_txtfld.getText().isEmpty()) {
            return; // Do nothing if any of the required fields are empty
        }

        try {
            LocalDateTime departure = LocalDateTime.of(
                    ngayBay_datepicker.getValue(),
                    LocalTime.parse(gioBay_combobox.getValue(), DateTimeFormatter.ofPattern("HH:mm:ss"))
            );

            // Parse the flight duration from the thoiGianBay_txtfld
            Duration flightDuration = parseFlightTime(thoiGianBay_txtfld.getText());
            if (flightDuration == null) {
                alert.errorMessage("Thời gian bay không hợp lệ.");
                return;
            }

            // Calculate the arrival time
            LocalDateTime arrival = departure.plus(flightDuration);

            // Set the calculated arrival date and time to the respective fields
            ngayHaCanh_datepicker.setValue(arrival.toLocalDate());
            gioHaCanh_combobox.setValue(arrival.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            alert.errorMessage("Thời gian không hợp lệ.");
        }
    }

    private Duration parseFlightTime(String flightTime) {
        try {
            // Initialize days, hours, minutes, and seconds to zero
            int days = 0;
            int hours = 0;
            int minutes = 0;
            int seconds = 0;

            // Split the string by spaces
            String[] parts = flightTime.split(" ");

            for (int i = 0; i < parts.length; i++) {
                // Check for days
                if (parts[i].equals("ngày")) {
                    days = Integer.parseInt(parts[i - 1]);
                }
                // Check for hours
                else if (parts[i].equals("giờ")) {
                    hours = Integer.parseInt(parts[i - 1]);
                }
                // Check for minutes
                else if (parts[i].equals("phút")) {
                    minutes = Integer.parseInt(parts[i - 1]);
                }
                // Check for seconds
                else if (parts[i].equals("giây")) {
                    seconds = Integer.parseInt(parts[i - 1]);
                }
            }

            // Convert the parsed values to a Duration object
            return Duration.ofDays(days)
                    .plusHours(hours)
                    .plusMinutes(minutes)
                    .plusSeconds(seconds);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null if parsing fails
        }
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

    private void populateTimeComboBox(ComboBox<String> comboBox) {
        for (int h = 0; h < 24; h++) {
            for (int m = 0; m < 60; m++) {
                comboBox.getItems().add(String.format("%02d:%02d:00", h, m));
            }
        }
    }

    private void populateTenDuongBayComboBox() {
        String query = "SELECT " +
                "sb1.TenSanBay AS SanBayDi, " +
                "sb2.TenSanBay AS SanBayDen " +
                "FROM DuongBay db " +
                "JOIN SanBay sb1 ON db.MaSanBayDi = sb1.MaSanBay " +
                "JOIN SanBay sb2 ON db.MaSanBayDen = sb2.MaSanBay " +
                "WHERE db.TrangThai = 1";
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                String sanBayDi = result.getString("SanBayDi");
                String sanBayDen = result.getString("SanBayDen");
                tenDuongBay_combobox.getItems().add(sanBayDi + " - " + sanBayDen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Could not populate route names.");
        }
    }

    private String[] splitTenDuongBay(String tenDuongBay) {
        return tenDuongBay.split(" - ");
    }

    private String getMaDuongBayByTen(String tenDuongBay) {
        String[] parts = splitTenDuongBay(tenDuongBay);
        if (parts.length != 2) {
            alert.errorMessage("Invalid route format.");
            return null;
        }
        String query = "SELECT MaDuongBay FROM DuongBay WHERE MaSanBayDi = (SELECT MaSanBay FROM SanBay WHERE TenSanBay = ?) " +
                "AND MaSanBayDen = (SELECT MaSanBay FROM SanBay WHERE TenSanBay = ?)";
        try {
            prepare = connect.prepareStatement(query);
            prepare.setString(1, parts[0]);
            prepare.setString(2, parts[1]);
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

    private String getThoiGianBayByMaDuongBay(String maDuongBay) {
        String query = "SELECT ThoiGianBay FROM DuongBay WHERE MaDuongBay = ?";
        try {
            prepare = connect.prepareStatement(query);
            prepare.setString(1, maDuongBay);
            result = prepare.executeQuery();
            if (result.next()) {
                return result.getString("ThoiGianBay");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Could not fetch flight duration.");
        }
        return null;
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
