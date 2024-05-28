package org.example.flightticketmanagement.Controllers.Manager;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.github.palexdev.materialfx.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.ChuyenBay;
import org.example.flightticketmanagement.Models.DatabaseDriver;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class LichChuyenBayController implements Initializable {

    @FXML
    private TableView<ChuyenBay> chuyenBay_tableview;

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
    private MFXButton sua_btn;

    @FXML
    private MFXButton them_btn;

    @FXML
    private TableColumn<ChuyenBay, String> thoiGianBay_tbcolumn;

    @FXML
    private MFXButton timkiem_btn;

    @FXML
    private MFXButton xoa_btn;

    @FXML
    private MenuButton sanbayden_menubtn;

    @FXML
    private MenuButton sanbaydi_menubtn;

    @FXML
    private MFXButton refresh_btn;

    @FXML
    void themLichChuyenBay(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/manager/ThemLichChuyenBay.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Thêm Lịch Chuyến Bay");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            alert.errorMessage("Unable to open the new page.");
        }
    }

    @FXML
    void suaLichChuyenBay(ActionEvent event) {
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
            stage.setTitle("Sửa Lịch Chuyến Bay");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            alert.errorMessage("Không thể mở trang mới.");
        }
    }

    @FXML
    void thongBaoRangBuocDuLieu(ActionEvent event) {
        ObservableList<ChuyenBay> selectedFlights = chuyenBay_tableview.getSelectionModel().getSelectedItems();

        if (selectedFlights.isEmpty()) {
            alert.errorMessage("Vui lòng chọn ít nhất một chuyến bay để xóa.");
            return;
        }

        for (ChuyenBay flight : selectedFlights) {
            alert.errorMessage("Không thể xóa vì ràng buộc dữ liệu.");
        }
    }


    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();
        loadData(null, null, null);
        sanbaydi_menubtn.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleSanBayDiMenuButtonClick);
        sanbayden_menubtn.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleSanBayDenMenuButtonClick);
        timkiem_btn.setOnAction(this::handleSearch);
        refresh_btn.setOnMouseClicked(event -> {
            sanbaydi_menubtn.setText("Sân bay đi");
            sanbayden_menubtn.setText("Sân bay đến");
            ngay_datepicker.setValue(null);
            loadData(null, null, null);
        });
        validateSearchButton();
    }

    private void loadData(String sanBayDi, String sanBayDen, LocalDate ngayBay) {
        chuyenBay_tableview.getItems().clear();  // Clear previous search results

        try {
            StringBuilder query = new StringBuilder("SELECT * FROM CHUYENBAY");
            boolean hasCondition = false;

            // Check if there are conditions to add to the WHERE clause
            if ((sanBayDi != null && !sanBayDi.isEmpty()) || (sanBayDen != null && !sanBayDen.isEmpty()) || ngayBay != null) {
                query.append(" WHERE");
            }

            if (sanBayDi != null && !sanBayDi.isEmpty()) {
                query.append(" MaDuongBay IN (SELECT MaDuongBay FROM DUONGBAY WHERE MaSanBayDi = (SELECT MaSanBay FROM SANBAY WHERE UPPER(TenSanBay) LIKE ?))");
                hasCondition = true;
            }

            if (sanBayDen != null && !sanBayDen.isEmpty()) {
                if (hasCondition) {
                    query.append(" AND");
                }
                query.append(" MaDuongBay IN (SELECT MaDuongBay FROM DUONGBAY WHERE MaSanBayDen = (SELECT MaSanBay FROM SANBAY WHERE UPPER(TenSanBay) LIKE ?))");
                hasCondition = true;
            }

            if (ngayBay != null) {
                if (hasCondition) {
                    query.append(" AND");
                }
                query.append(" TRUNC(TGXP) = ?");
            }

            prepare = connect.prepareStatement(query.toString());

            int index = 1;
            if (sanBayDi != null && !sanBayDi.isEmpty()) {
                prepare.setString(index++, "%" + sanBayDi.toUpperCase() + "%");
            }
            if (sanBayDen != null && !sanBayDen.isEmpty()) {
                prepare.setString(index++, "%" + sanBayDen.toUpperCase() + "%");
            }
            if (ngayBay != null) {
                prepare.setDate(index, Date.valueOf(ngayBay));
            }

            result = prepare.executeQuery();

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
            ngayBay_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getThoiGianXuatPhat().toLocalDate().toString()));
            gioBay_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getThoiGianXuatPhat().toLocalTime().toString()));
            soGheTrong_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(getSoGheTrong(cellData.getValue().getMaChuyenBay()).toString()));
            soGhe_tbcoumn.setCellValueFactory(cellData -> new SimpleStringProperty(getSoGhe(cellData.getValue().getMaChuyenBay()).toString()));
            thoiGianBay_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(formatDuration(Duration.between(cellData.getValue().getThoiGianXuatPhat(), cellData.getValue().getThoiGianKetThuc()))));
            giaVe_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getGiaVe())));

        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while loading data from the database.");
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    private void handleSanBayDiMenuButtonClick(MouseEvent event) {
        sanbaydi_menubtn.getItems().clear();

        // Add "Null" option
        MenuItem nullItem = new MenuItem("Null");
        nullItem.setOnAction(e -> sanbaydi_menubtn.setText(null));
        sanbaydi_menubtn.getItems().add(nullItem);

        String sql = "SELECT DISTINCT sb.TenSanBay FROM SANBAY sb JOIN DUONGBAY db ON sb.MaSanBay = db.MaSanBayDi";

        try (Connection conn = DatabaseDriver.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Set<String> uniqueAirports = new HashSet<>();
            while (rs.next()) {
                uniqueAirports.add(rs.getString("TenSanBay"));
            }

            for (String airport : uniqueAirports) {
                MenuItem item = new MenuItem(airport);
                item.setOnAction(e -> sanbaydi_menubtn.setText(airport));
                sanbaydi_menubtn.getItems().add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while loading departure airports.");
        }
    }


    private void handleSanBayDenMenuButtonClick(MouseEvent event) {
        sanbayden_menubtn.getItems().clear();

        // Add "Null" option
        MenuItem nullItem = new MenuItem("Null");
        nullItem.setOnAction(e -> sanbayden_menubtn.setText(null));
        sanbayden_menubtn.getItems().add(nullItem);

        String sql = "SELECT DISTINCT sb.TenSanBay FROM SANBAY sb JOIN DUONGBAY db ON sb.MaSanBay = db.MaSanBayDen";

        try (Connection conn = DatabaseDriver.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Set<String> uniqueAirports = new HashSet<>();
            while (rs.next()) {
                uniqueAirports.add(rs.getString("TenSanBay"));
            }

            for (String airport : uniqueAirports) {
                MenuItem item = new MenuItem(airport);
                item.setOnAction(e -> {
                    sanbayden_menubtn.setText(airport);
                    validateSearchButton();
                });
                sanbayden_menubtn.getItems().add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while loading destination airports.");
        }
    }

    private void validateSearchButton() {
        timkiem_btn.setDisable(
                sanbaydi_menubtn.getText().equals("Chọn sân bay đi") &&
                        sanbayden_menubtn.getText().equals("Chọn sân bay đến") &&
                        ngay_datepicker.getValue() == null
        );
    }




    private void handleSearch(ActionEvent event) {
        // Lấy giá trị từ các thành phần giao diện
        String sanBayDi = sanbaydi_menubtn.getText().trim();
        String sanBayDen = sanbayden_menubtn.getText().trim();
        LocalDate ngayBay = ngay_datepicker.getValue();

        // Kiểm tra nếu không có tiêu chí nào được chọn
        if ("Chọn sân bay đi".equals(sanBayDi) && "Chọn sân bay đến".equals(sanBayDen) && ngayBay == null) {
            alert.errorMessage("Vui lòng chọn ít nhất một tiêu chí để tìm kiếm.");
            return;
        }

        // Xóa dữ liệu cũ trong bảng
        chuyenBay_tableview.getItems().clear();

        // Tạo câu truy vấn cơ bản
        StringBuilder query = new StringBuilder("SELECT * FROM CHUYENBAY WHERE 1=1");
        List<Object> parameters = new ArrayList<>();

        // Thêm điều kiện vào câu truy vấn dựa trên các tiêu chí
        if (!"Chọn sân bay đi".equals(sanBayDi)) {
            query.append(" AND MaDuongBay IN (SELECT MaDuongBay FROM DUONGBAY WHERE MaSanBayDi = (SELECT MaSanBay FROM SANBAY WHERE UPPER(TenSanBay) LIKE ?))");
            parameters.add(sanBayDi.toUpperCase());
        }
        if (!"Chọn sân bay đến".equals(sanBayDen)) {
            query.append(" AND MaDuongBay IN (SELECT MaDuongBay FROM DUONGBAY WHERE MaSanBayDen = (SELECT MaSanBay FROM SANBAY WHERE UPPER(TenSanBay) LIKE ?))");
            parameters.add(sanBayDen.toUpperCase());
        }
        if (ngayBay != null) {
            query.append(" AND TRUNC(TGXP) = ?");
            parameters.add(Date.valueOf(ngayBay));
        }

        // Thực thi truy vấn và hiển thị kết quả
        try (PreparedStatement prepare = connect.prepareStatement(query.toString())) {
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
                    chuyenBay_tableview.getItems().add(chuyenBay);
                }

                maChuyenBay_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaChuyenBay()));
                sanBayDi_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(getSanBayDi(cellData.getValue().getMaDuongBay())));
                sanBayDen_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(getSanBayDen(cellData.getValue().getMaDuongBay())));
                ngayBay_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getThoiGianXuatPhat().toLocalDate().toString()));
                gioBay_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getThoiGianXuatPhat().toLocalTime().toString()));
                soGheTrong_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(getSoGheTrong(cellData.getValue().getMaChuyenBay()).toString()));
                soGhe_tbcoumn.setCellValueFactory(cellData -> new SimpleStringProperty(getSoGhe(cellData.getValue().getMaChuyenBay()).toString()));
                thoiGianBay_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(formatDuration(Duration.between(cellData.getValue().getThoiGianXuatPhat(), cellData.getValue().getThoiGianKetThuc()))));
                giaVe_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getGiaVe())));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while loading data from the database.");
        }
    }


    private String getSanBayDi(String maDuongBay) {
        String sql = "SELECT sb.TenSanBay FROM DUONGBAY db " +
                "JOIN SANBAY sb ON db.MaSanBayDi = sb.MaSanBay " +
                "WHERE db.MaDuongBay = ?";
        try (Connection conn = DatabaseDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
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
        try (Connection conn = DatabaseDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
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

    private Integer getSoGheTrong(String maChuyenBay) {
        String sql = "SELECT COUNT(VE.MAVE) AS SoGheTrong " +
                "FROM VE " +
                "LEFT JOIN CT_DATVE ON VE.MAVE = CT_DATVE.MAVE " +
                "WHERE VE.MACHUYENBAY = ? " +
                "AND (CT_DATVE.TRANGTHAI NOT IN (0, 1, 2) OR CT_DATVE.TRANGTHAI IS NULL)";
        try (Connection conn = DatabaseDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maChuyenBay);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("SoGheTrong");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    private Integer getSoGhe(String maChuyenBay) {
        String sql = "SELECT COUNT(VE.MAVE) AS SoGhe " +
                "FROM VE " +
                "LEFT JOIN CT_DATVE ON VE.MAVE = CT_DATVE.MAVE " +
                "WHERE VE.MACHUYENBAY = ? " +
                "AND (CT_DATVE.TRANGTHAI IN (0, 1) OR CT_DATVE.TRANGTHAI IS NULL)";
        try (Connection conn = DatabaseDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maChuyenBay);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("SoGhe");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Placeholder if not found
    }


    private String formatDuration(Duration duration) {
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
