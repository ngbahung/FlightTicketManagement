package org.example.flightticketmanagement.Controllers.Manager;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import io.github.palexdev.materialfx.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.Admin.LichSuDatVeController;
import org.example.flightticketmanagement.Controllers.Admin.XacNhanVeController;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.CT_HangVe;
import org.example.flightticketmanagement.Models.ChuyenBay;
import org.example.flightticketmanagement.Models.DatabaseDriver;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LichChuyenBayController implements Initializable {

    @FXML
    private TableView<ChuyenBay> chuyenBay_tableview;

    @FXML
    private TableColumn<ChuyenBay, String> diemDung_tbcl;

    @FXML
    private TableColumn<ChuyenBay, String> giaVe_tbcolumn;

    @FXML
    private TableColumn<ChuyenBay, String> gioBay_tbcolumn;

    @FXML
    private TableColumn<ChuyenBay, String> maChuyenBay_tbcolumn;

    @FXML
    private TableColumn<ChuyenBay, String> ngayBay_tbcolumn;

    @FXML
    private MFXDatePicker ngay_datepicker;


    @FXML
    private TableColumn<ChuyenBay, String> sanBayDen_tbcolumn;

    @FXML
    private TableColumn<ChuyenBay, String> sanBayDi_tbcolumn;

    @FXML
    private TableColumn<ChuyenBay, String> soGheTrong_tbcolumn;

    @FXML
    private TableColumn<ChuyenBay, String> soGhe_tbcoumn;

    @FXML
    private TableColumn<ChuyenBay, String> thoiGianBay_tbcolumn;

    @FXML
    private MenuButton sanbayden_menubtn;

    @FXML
    private MenuButton sanbaydi_menubtn;

    @FXML
    private MFXButton refresh_btn;

    @FXML
    void themLichChuyenBay() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/manager/ThemLichChuyenBay.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ThemLichChuyenBayController controller = fxmlLoader.getController();
            controller.setLichChuyenBayController(this);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
            stage.setTitle("Thêm Lịch Chuyến Bay");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            alert.errorMessage("Không thể mở trang mới.");
        }
    }

    @FXML
    void suaLichChuyenBay() {
        ChuyenBay selectedChuyenBay = chuyenBay_tableview.getSelectionModel().getSelectedItem();
        if (selectedChuyenBay == null) {
            // Thông báo cho người dùng nếu không có dòng nào được chọn
            alert.errorMessage("Vui lòng chọn một chuyến bay để sửa.");
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/manager/SuaLichChuyenBay.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            SuaLichChuyenBayController controller = fxmlLoader.getController();

            // Truyền dữ liệu của chuyến bay đã chọn tới SuaLichChuyenBayController
            controller.setData(selectedChuyenBay);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
            stage.setTitle("Sửa Lịch Chuyến Bay");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            alert.errorMessage("Không thể mở trang mới.");
        }
    }

    @FXML
    void xoaLichChuyenBay() {
        ObservableList<ChuyenBay> selectedFlights = chuyenBay_tableview.getSelectionModel().getSelectedItems();

        if (selectedFlights.isEmpty()) {
            alert.errorMessage("Vui lòng chọn ít nhất một chuyến bay để xóa.");
            return;
        }

        // Kiểm tra xem tất cả các chuyến bay được chọn có thể xóa được không
        for (ChuyenBay chuyenBay : selectedFlights) {
            if (chuyenBay.getThoiGianXuatPhat().isBefore(LocalDateTime.now())) {
                alert.errorMessage("Chuyến bay đã xuất phát, không thể xóa chuyến bay đã chọn.");
                return;
            }
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Xác nhận xóa chuyến bay");
        confirmationAlert.setHeaderText("Bạn có muốn xóa chuyến bay đã chọn?");
        confirmationAlert.setContentText("Hành động này sẽ thay đổi danh sách chuyến bay của bạn");

        Optional<ButtonType> result = confirmationAlert.showAndWait();

        String query = "DELETE FROM ChuyenBay WHERE MaChuyenBay = ?";
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                connect = DatabaseDriver.getConnection();
                PreparedStatement prepare = connect.prepareStatement(query);

                for (ChuyenBay chuyenBay : selectedFlights) {
                    prepare.setString(1, chuyenBay.getMaChuyenBay());
                    prepare.addBatch();
                }

                int[] results = prepare.executeBatch();
                if (Arrays.stream(results).allMatch(i -> i > 0)) {
                    alert.successMessage("Tất cả các chuyến bay đã được xóa thành công.");
                    eventBus.post(new Object());
                    layDuLieu(null, null, null);
                } else {
                    alert.errorMessage("Không xóa được một số chuyến bay đã chọn.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                alert.errorMessage("Lỗi khi đang xóa chuyến bay. Vui lòng kiểm tra lại.");
            }
        }
    }


    @FXML
    private void xuLyTimKiemButton() {
        // Lấy giá trị từ các thành phần giao diện
        String sanBayDi = sanbaydi_menubtn.getText().trim();
        String sanBayDen = sanbayden_menubtn.getText().trim();
        LocalDate ngayBay = ngay_datepicker.getValue();

        // Kiểm tra nếu không có tiêu chí nào được chọn
        if ("Sân bay đi".equals(sanBayDi) && "Sân bay đến".equals(sanBayDen) && ngayBay == null) {
            alert.errorMessage("Vui lòng chọn ít nhất một tiêu chí để tìm kiếm.");
            return;
        }

        // Xóa dữ liệu cũ trong bảng
        chuyenBay_tableview.getItems().clear();

        // Tạo câu truy vấn cơ bản
        String baseQuery = "SELECT * FROM CHUYENBAY WHERE 1=1";
        StringBuilder conditions = new StringBuilder();
        List<Object> parameters = new ArrayList<>();

        // Thêm điều kiện vào câu truy vấn dựa trên các tiêu chí
        if (!"Sân bay đi".equals(sanBayDi)) {
            conditions.append(" AND MaDuongBay IN (SELECT MaDuongBay FROM DUONGBAY WHERE MaSanBayDi = (SELECT MaSanBay FROM SANBAY WHERE UPPER(TenSanBay) LIKE ?))");
            parameters.add(sanBayDi.toUpperCase());
        }
        if (!"Sân bay đến".equals(sanBayDen)) {
            conditions.append(" AND MaDuongBay IN (SELECT MaDuongBay FROM DUONGBAY WHERE MaSanBayDen = (SELECT MaSanBay FROM SANBAY WHERE UPPER(TenSanBay) LIKE ?))");
            parameters.add(sanBayDen.toUpperCase());
        }
        if (ngayBay != null) {
            conditions.append(" AND TRUNC(TGXP) = ?");
            parameters.add(Date.valueOf(ngayBay));
        }

        String finalQuery = baseQuery + conditions;

        try {
            prepareAndExecuteQuery(finalQuery, parameters, chuyenBay_tableview);
            if (chuyenBay_tableview.getItems().isEmpty()) {
                alert.errorMessage("Không tìm thấy dữ liệu.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while loading data from the database.");
        }
    }

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private static final EventBus eventBus = new EventBus();

    public LichChuyenBayController() {}

    public static EventBus getEventBus() {
        return eventBus;
    }
    private final EventBus eventBusXoaGheTrong = XacNhanVeController.getEventBus();
    private final EventBus eventBusThemGheTrong = LichSuDatVeController.getEventBus();
    private final EventBus eventBusSuaChuyenBay = SuaLichChuyenBayController.getEventBus();

    private final AlertMessage alert = new AlertMessage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();
        eventBusXoaGheTrong.register(this);
        eventBusThemGheTrong.register(this);
        eventBusSuaChuyenBay.register(this);
        layDuLieu(null, null, null);
        sanbaydi_menubtn.setOnShowing(event -> updateSanBayMenuItems());
        sanbayden_menubtn.setOnShowing(event -> updateSanBayMenuItems());
        refresh_btn.setOnMouseClicked(event -> {
            sanbaydi_menubtn.setText("Sân bay đi");
            sanbayden_menubtn.setText("Sân bay đến");
            ngay_datepicker.setValue(null);
            layDuLieu(null, null, null);
        });
    }

    @Subscribe
    public void handleLoadDataEvent(Object event) {
        layDuLieu(null, null, null);
    }

    public void layDuLieu(String sanBayDi, String sanBayDen, LocalDate ngayBay) {
        chuyenBay_tableview.getItems().clear();  // Xóa kết quả tìm kiếm trước đó

        String baseQuery = "SELECT * FROM CHUYENBAY cb "
                + "JOIN DUONGBAY db ON cb.MaDuongBay = db.MaDuongBay "
                + "JOIN SANBAY sbDi ON db.MaSanBayDi = sbDi.MaSanBay "
                + "JOIN SANBAY sbDen ON db.MaSanBayDen = sbDen.MaSanBay";

        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        if (sanBayDi != null && !sanBayDi.isEmpty()) {
            conditions.add("UPPER(sbDi.TenSanBay) LIKE ?");
            parameters.add("%" + sanBayDi.toUpperCase() + "%");
        }

        if (sanBayDen != null && !sanBayDen.isEmpty()) {
            conditions.add("UPPER(sbDen.TenSanBay) LIKE ?");
            parameters.add("%" + sanBayDen.toUpperCase() + "%");
        }

        if (ngayBay != null) {
            conditions.add("TRUNC(cb.TGXP) = ?");
            parameters.add(Date.valueOf(ngayBay));
        }

        if (!conditions.isEmpty()) {
            baseQuery += " WHERE " + String.join(" AND ", conditions);
        }

        try {
            PreparedStatement prepare = connect.prepareStatement(baseQuery);
            for (int i = 0; i < parameters.size(); i++) {
                prepare.setObject(i + 1, parameters.get(i));
            }

            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                ChuyenBay chuyenBay = new ChuyenBay(
                        result.getString("MaChuyenBay"),
                        result.getString("MaDuongBay"),
                        result.getTimestamp("TGXP").toLocalDateTime(),
                        result.getTimestamp("TGKT").toLocalDateTime(),
                        result.getString("TrangThai"),
                        result.getFloat("GiaVe")
                );
                chuyenBay_tableview.getItems().add(chuyenBay);
            }

            maChuyenBay_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaChuyenBay()));
            sanBayDi_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(getSanBayDi(cellData.getValue().getMaDuongBay())));
            sanBayDen_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(getSanBayDen(cellData.getValue().getMaDuongBay())));
            ngayBay_tbcolumn.setCellValueFactory(cellData -> {
                LocalDateTime ngayBayDateTime = cellData.getValue().getThoiGianXuatPhat();
                String ngayBayFormatted = (ngayBayDateTime != null) ? ngayBayDateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "";
                return new SimpleStringProperty(ngayBayFormatted);
            });

            gioBay_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getThoiGianXuatPhat().toLocalTime().toString()));
            diemDung_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(getSoDiemDung(cellData.getValue().getMaDuongBay())));
            soGheTrong_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(getSoGheTrong(cellData.getValue().getMaChuyenBay()).toString()));
            soGhe_tbcoumn.setCellValueFactory(cellData -> new SimpleStringProperty(getSoGhe(cellData.getValue().getMaChuyenBay()).toString()));
            thoiGianBay_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(dinhDangKhoangThoiGian(Duration.between(cellData.getValue().getThoiGianXuatPhat(), cellData.getValue().getThoiGianKetThuc()))));
            giaVe_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getGiaVe())));

        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Đã xảy ra lỗi khi tải dữ liệu từ cơ sở dữ liệu.");
        }
    }

    private void prepareAndExecuteQuery(String query, List<Object> parameters, TableView<ChuyenBay> tableView) throws SQLException {
        try (PreparedStatement prepare = connect.prepareStatement(query)) {
            for (int i = 0; i < parameters.size(); i++) {
                prepare.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet result = prepare.executeQuery()) {
                while (result.next()) {
                    ChuyenBay chuyenBay = new ChuyenBay(
                            result.getString("MaChuyenBay"),
                            result.getString("MaDuongBay"),
                            result.getTimestamp("TGXP").toLocalDateTime(),
                            result.getTimestamp("TGKT").toLocalDateTime(),
                            result.getString("TrangThai"),
                            result.getFloat("GiaVe")
                    );
                    tableView.getItems().add(chuyenBay);
                }
            }
        }
    }

    private void updateSanBayMenuItems() {
        // Clear the current items in the menu
        sanbaydi_menubtn.getItems().clear();
        sanbayden_menubtn.getItems().clear();

        // Use a Set to avoid duplicates
        Set<String> sanBayDiSet = new HashSet<>();
        Set<String> sanBayDenSet = new HashSet<>();

        // Fetch the airports for the table view
        for (ChuyenBay chuyenBay : chuyenBay_tableview.getItems()) {
            String sanBayDi = getSanBayDi(chuyenBay.getMaDuongBay());
            String sanBayDen = getSanBayDen(chuyenBay.getMaDuongBay());
            if (!sanBayDi.isEmpty()) sanBayDiSet.add(sanBayDi);
            if (!sanBayDen.isEmpty()) sanBayDenSet.add(sanBayDen);
        }

        // Add the list of airports to the departure menu button
        for (String sanBay : sanBayDiSet) {
            MenuItem menuItem = new MenuItem(sanBay);
            menuItem.setOnAction(event -> sanbaydi_menubtn.setText(sanBay));
            sanbaydi_menubtn.getItems().add(menuItem);
        }

        // Add the list of airports to the arrival menu button
        for (String sanBay : sanBayDenSet) {
            MenuItem menuItem = new MenuItem(sanBay);
            menuItem.setOnAction(event -> sanbayden_menubtn.setText(sanBay));
            sanbayden_menubtn.getItems().add(menuItem);
        }
    }

    private String getSanBayDi(String maDuongBay) {
        String sanBayDi = "";
        try (CallableStatement statement = connect.prepareCall("{call GET_SANBAYDI(?, ?)}")) {
            statement.setString(1, maDuongBay);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.execute();
            return statement.getString(2);
        } catch (Exception e) {
            sanBayDi = "";
        }
        return sanBayDi;
    }

    private String getSanBayDen(String maDuongBay) {
        String sanBayDen ="";
        try (CallableStatement statement = connect.prepareCall("{call GET_SANBAYDEN(?, ?)}")) {
            statement.setString(1, maDuongBay);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.execute();
            return statement.getString(2);
        } catch (Exception e) {
            sanBayDen = "";
        }
        return sanBayDen;
    }

    private Integer getSoGheTrong(String maChuyenBay) {
        int soGheTrong = 0;
        try (CallableStatement statement = connect.prepareCall("{call GET_SOGHETRONG(?, ?)}")) {
            statement.setString(1, maChuyenBay);
            statement.registerOutParameter(2, Types.INTEGER);
            statement.execute();
            return statement.getInt(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soGheTrong;
    }

    private Integer getSoGhe(String maChuyenBay) {
        int soGhe = 0;
        try (CallableStatement statement = connect.prepareCall("{call GET_SOGHE(?, ?)}")) {
            statement.setString(1, maChuyenBay);
            statement.registerOutParameter(2, Types.INTEGER);
            statement.execute();
            return statement.getInt(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soGhe;
    }

    private String getSoDiemDung(String maDuongBay) {
        String soDiemDung = "";
        try (CallableStatement statement = connect.prepareCall("{call GET_SODIEMDUNG(?, ?)}")) {
            statement.setString(1, maDuongBay);
            statement.registerOutParameter(2, Types.INTEGER);
            statement.execute();
            return String.valueOf(statement.getInt(2));
        } catch (Exception e) {
            e.printStackTrace();
            soDiemDung = "0";
        }
        return soDiemDung;
    }

    private String dinhDangKhoangThoiGian(Duration duration) {
        long totalMinutes = duration.toMinutes();
        long days = totalMinutes / (24 * 60);
        long hours = (totalMinutes % (24 * 60)) / 60;
        long minutes = totalMinutes % 60;

        String formattedDuration = "";
        if (days > 0) {
            formattedDuration += days + " ngày ";
        }
        if (hours > 0) {
            formattedDuration += hours + " giờ ";
        }
        if (minutes > 0) {
            formattedDuration += minutes + " phút";
        }

        return formattedDuration.trim(); // Loại bỏ khoảng trắng thừa nếu có
    }

}
