package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TraCuuBanVeController implements Initializable {
    @FXML
    private TableColumn<ChuyenBay, Void> chiTiet_tbcl;

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
    private MFXButton refresh_btn;

    @FXML
    private MFXDatePicker ngay_datepicker;

    @FXML
    private TableColumn<ChuyenBay, String> sanBayDen_tbcolumn;

    @FXML
    private TableColumn<ChuyenBay, String> sanBayDi_tbcolumn;

    @FXML
    private MenuButton sanbayden_menubtn;

    @FXML
    private MenuButton sanbaydi_menubtn;


    @FXML
    private TableColumn<ChuyenBay, String> soGheTrong_tbcolumn;

    @FXML
    private TableColumn<ChuyenBay, String> soGhe_tbcoumn;

    @FXML
    private MFXButton timkiem_btn;

    @FXML
    private void handleSearch(ActionEvent event) {
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
        String baseQuery = "SELECT * FROM CHUYENBAY WHERE TrangThai = 0";
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

        String finalQuery = baseQuery + conditions.toString();

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

    @FXML
    private void handleRefresh(ActionEvent event) {
        ngay_datepicker.setValue(null);
        sanbaydi_menubtn.setText("Chọn sân bay đi");
        sanbayden_menubtn.setText("Chọn sân bay đến");
        loadData(null, null, null);
    }

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();
        loadData(null, null, null);
        sanbaydi_menubtn.setOnShowing(event -> updateSanBayMenuItems());
        sanbayden_menubtn.setOnShowing(event -> updateSanBayMenuItems());
    }

    private void loadData(String sanBayDi, String sanBayDen, LocalDate ngayBay) {
        chuyenBay_tableview.getItems().clear();  // Clear previous search results

        try {
            StringBuilder query = new StringBuilder("SELECT * FROM CHUYENBAY WHERE TrangThai = 0");
            boolean hasCondition = false;

            // Check if there are conditions to add to the WHERE clause
            if ((sanBayDi != null && !sanBayDi.isEmpty()) || (sanBayDen != null && !sanBayDen.isEmpty()) || ngayBay != null) {
                query.append(" WHERE");
            }

            if (sanBayDi != null && !sanBayDi.isEmpty()) {
                query.append(" MaDuongBay IN (SELECT MaDuongBay FROM DUONGBAY WHERE MaSanBayDi = (SELECT MaSanBay FROM SANBAY WHERE UPPER(TenSanBay) LIKE ? AND ROWNUM = 1))");
                hasCondition = true;
            }

            if (sanBayDen != null && !sanBayDen.isEmpty()) {
                if (hasCondition) {
                    query.append(" AND");
                }
                query.append(" MaDuongBay IN (SELECT MaDuongBay FROM DUONGBAY WHERE MaSanBayDen = (SELECT MaSanBay FROM SANBAY WHERE UPPER(TenSanBay) LIKE ? AND ROWNUM = 1))");
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

            chuyenBay_tableview.getItems().clear();

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
                String ngayBayFormatted = "";
                LocalDateTime ngayBayDateTime = cellData.getValue().getThoiGianXuatPhat();
                if (ngayBayDateTime != null) {
                    ngayBayFormatted = ngayBayDateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                }
                return new SimpleStringProperty(ngayBayFormatted);
            });

            gioBay_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getThoiGianXuatPhat().toLocalTime().toString()));
            soGheTrong_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(getSoGheTrong(cellData.getValue().getMaChuyenBay()).toString()));
            soGhe_tbcoumn.setCellValueFactory(cellData -> new SimpleStringProperty(getSoGhe(cellData.getValue().getMaChuyenBay()).toString()));
            giaVe_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGiaVe().toString()));
            chiTiet_tbcl.setCellFactory(param -> new TableCell<>() {
                private final Button detailButton = new Button("Xem chi tiết");

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        detailButton.setOnAction(event -> {
                            ChuyenBay flight = getTableView().getItems().get(getIndex());
                            showDetailWindow(flight);
                        });
                        setGraphic(detailButton);
                    }
                }
            });

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
        try (Connection conn = DatabaseDriver.getConnection();
             CallableStatement cs = conn.prepareCall("{call GET_SANBAYDI(?, ?)}")) {
            cs.setString(1, maDuongBay);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            return cs.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
            return "N/A";
        }
    }

    private String getSanBayDen(String maDuongBay) {
        try (Connection conn = DatabaseDriver.getConnection();
             CallableStatement cs = conn.prepareCall("{call GET_SANBAYDEN(?, ?)}")) {
            cs.setString(1, maDuongBay);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            return cs.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
            return "N/A";
        }
    }

    private Integer getSoGheTrong(String maChuyenBay) {
        try (Connection conn = DatabaseDriver.getConnection();
             CallableStatement cs = conn.prepareCall("{call GET_SOGHETRONG(?, ?)}")) {
            cs.setString(1, maChuyenBay);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            return cs.getInt(2);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private Integer getSoGhe(String maChuyenBay) {
        try (Connection conn = DatabaseDriver.getConnection();
             CallableStatement cs = conn.prepareCall("{call GET_SOGHE(?, ?)}")) {
            cs.setString(1, maChuyenBay);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            return cs.getInt(2);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void showDetailWindow(ChuyenBay flight) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/ManHinhDatVe.fxml"));
            Parent root = loader.load();

            // Pass the selected flight details to the new controller if needed
            ManHinhDatVeController controller = loader.getController();
            controller.setFlightDetails(flight);

            Stage stage = new Stage();
            stage.setTitle("Chi Tiết Chuyến Bay");
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while opening the detail window.");
        }
    }

}
