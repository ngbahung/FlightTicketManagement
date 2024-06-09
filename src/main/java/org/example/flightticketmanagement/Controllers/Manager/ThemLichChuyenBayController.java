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
import org.example.flightticketmanagement.Models.CT_HangVe;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.SanBay;
import org.example.flightticketmanagement.Models.SanBayTrungGian;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ThemLichChuyenBayController implements Initializable {
    @FXML
    private TextField gia_txtfld;

    @FXML
    private TableColumn<SanBayTrungGian, Integer> stt_tbcl;

    @FXML

    private TableColumn<SanBayTrungGian, String> tenSBTG_tbcl;

    @FXML
    private TableColumn<SanBayTrungGian, String> thoiGianDung_tbcl;


    @FXML
    private TableView<SanBayTrungGian> sanBayTrungGian_tbview;

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
        ObservableList<CT_HangVe> selectedTicketClasses = hangVe_tableview.getSelectionModel().getSelectedItems();

        if (selectedTicketClasses.isEmpty()) {
            alert.errorMessage("Không chọn hạng vé nào. Vui lòng chọn ít nhất một hạng vé.");
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Xác nhận xóa hạng vé");
        confirmationAlert.setHeaderText("Bạn có muốn xóa các hạng vé đã chọn?");
        confirmationAlert.setContentText("Hành động này sẽ thay đổi danh sách hạng vé của bạn");

        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            String query = "DELETE FROM CT_HangVe WHERE MaHangVe = ?";

            try {
                connect = DatabaseDriver.getConnection();
                prepare = connect.prepareStatement(query);

                for (CT_HangVe ct_hangVe : selectedTicketClasses) {
                    prepare.setString(1, ct_hangVe.getMaHangVe());
                    prepare.addBatch();
                }

                int[] results = prepare.executeBatch();
                if (results.length > 0) {
                    alert.successMessage("Hạng vé đã được xóa thành công.");
                    hangVe_tableview.getItems().removeAll(selectedTicketClasses);
                } else {
                    alert.errorMessage("Không xóa được hạng vé đã chọn.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                alert.errorMessage("Lỗi khi đang xóa hạng vé. Vui lòng kiểm tra lại.");
            }
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
            BigDecimal giaVe = new BigDecimal(gia_txtfld.getText());

            // Parse and format the price
            DecimalFormat df = new DecimalFormat("#.00");  // Set the format to two decimal places
            String formattedGiaVe = df.format(giaVe);

            // Tạo câu lệnh SQL để chèn dữ liệu vào bảng CHUYENBAY
            String insertChuyenBayQuery = "INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe) " +
                    "VALUES (?, ?, ?, ?, 0, ?)";
            connect = DatabaseDriver.getConnection();
            PreparedStatement insertChuyenBayStatement = connect.prepareStatement(insertChuyenBayQuery);
            insertChuyenBayStatement.setString(1, maChuyenBay);
            insertChuyenBayStatement.setString(2, maDuongBay);
            insertChuyenBayStatement.setTimestamp(3, Timestamp.valueOf(thoiGianXuatPhat));
            insertChuyenBayStatement.setTimestamp(4, Timestamp.valueOf(thoiGianHaCanh));
            insertChuyenBayStatement.setBigDecimal(5, giaVe);

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
            eventBus.post(new Object());
            closeStage();
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Lỗi khi lưu dữ liệu vào cơ sở dữ liệu. Vui lòng kiểm tra lại.");
        } catch (NumberFormatException e) {
            alert.errorMessage("Lỗi khi chuyển đổi số giờ. Vui lòng kiểm tra định dạng.");
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
    private static final EventBus eventBus = new EventBus();

    public ThemLichChuyenBayController() {}

    public static EventBus getEventBus() {
        return eventBus;
    }

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

        tenDuongBay_combobox.valueProperty().addListener((observableValue, oldValue, newValue) -> maDuongBay_txtfld.setText(getMaDuongBayByTen(newValue)));

        generateMaChuyenBay();

        // Initialize table columns
        tenHangVe_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(getTenHangVe(cellData.getValue().getMaHangVe())));
        soLuongGhe_tbcl.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSoGheTrong()).asObject());

        // Initialize the TableView with an empty ObservableList
        hangVe_tableview.setItems(FXCollections.observableArrayList());

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

        stt_tbcl.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getThuTu()).asObject());
        tenSBTG_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(getTenSanBayTrungGian(cellData.getValue().getMaSanBay())));
        // Initialize the thoiGianDung_tbcl column with formatted time
        thoiGianDung_tbcl.setCellValueFactory(cellData -> {
            String thoiGianDung = cellData.getValue().getThoiGianDung();
            return new SimpleStringProperty(formatThoiGianDung(thoiGianDung));
        });

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

    private void populateTimeComboBox(ComboBox<String> comboBox) {
        for (int h = 0; h < 24; h++) {
            for (int m = 0; m < 60; m++) {
                comboBox.getItems().add(String.format("%02d:%02d:00", h, m));
            }
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
