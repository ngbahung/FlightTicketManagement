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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.ChuyenBay;
import org.example.flightticketmanagement.Models.DatabaseDriver;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

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
        sanbaydi_menubtn.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleSanBayDiMenuButtonClick);
        sanbayden_menubtn.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleSanBayDenMenuButtonClick);
        timkiem_btn.setOnAction(this::handleSearch);
        refresh_btn.setOnAction(this::handleRefresh);
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
                        result.getInt("SoLuongGhe"),
                        result.getInt("SoChuyenBay"),
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
            soGhe_tbcoumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSoLuongGhe().toString()));
            soGheTrong_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(getSoGheTrong(cellData.getValue().getMaChuyenBay()).toString()));
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
        String sql = "SELECT cb.SoLuongGhe - COUNT(v.MaVe) AS SoGheTrong FROM CHUYENBAY cb " +
                "LEFT JOIN VE v ON cb.MaChuyenBay = v.MaChuyenBay " +
                "WHERE cb.MaChuyenBay = ? " +
                "GROUP BY cb.SoLuongGhe";
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
        return 0; // Placeholder if not found
    }

    private void handleSanBayDiMenuButtonClick(MouseEvent event) {
        sanbaydi_menubtn.getItems().clear();
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


    private void handleSearch(ActionEvent event) {
        String sanBayDi = sanbaydi_menubtn.getText().trim();
        String sanBayDen = sanbayden_menubtn.getText().trim();
        LocalDate ngayBay = ngay_datepicker.getValue();

        if (!sanBayDi.equals("Chọn sân bay đi") || !sanBayDen.equals("Chọn sân bay đến") || ngayBay != null) {
            loadData(
                    sanBayDi.equals("Chọn sân bay đi") ? null : sanBayDi,
                    sanBayDen.equals("Chọn sân bay đến") ? null : sanBayDen,
                    ngayBay
            );
        } else {
            alert.errorMessage("Vui lòng chọn ít nhất một tiêu chí để tìm kiếm.");
        }
    }

    private void handleRefresh(ActionEvent event) {
        ngay_datepicker.setValue(null);
        sanbaydi_menubtn.setText("Chọn sân bay đi");
        sanbayden_menubtn.setText("Chọn sân bay đến");
        loadData(null, null, null);
    }

    private void validateSearchButton() {
        timkiem_btn.setDisable(
                sanbaydi_menubtn.getText().equals("Chọn sân bay đi") &&
                        sanbayden_menubtn.getText().equals("Chọn sân bay đến") &&
                        ngay_datepicker.getValue() == null
        );
    }

    private void handleSanBayDenMenuButtonClick(MouseEvent event) {
        sanbayden_menubtn.getItems().clear();
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
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while opening the detail window.");
        }
    }

}
