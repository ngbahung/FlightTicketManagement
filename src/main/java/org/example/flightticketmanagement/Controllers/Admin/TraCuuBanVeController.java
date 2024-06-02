package org.example.flightticketmanagement.Controllers.Admin;

<<<<<<< Updated upstream
import io.github.palexdev.materialfx.controls.*;
import javafx.beans.property.SimpleStringProperty;
=======
import java.time.LocalDate;

import io.github.palexdev.materialfx.controls.MFXButton;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
=======
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
>>>>>>> Stashed changes
import java.util.*;

public class TraCuuBanVeController implements Initializable {
    @FXML
<<<<<<< Updated upstream
    private TableColumn<ChuyenBay, Void> chiTiet_tbcl;
=======
    private MFXButton refresh_button;
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
=======
    @FXML
    void enableSearchButton() {
        if(!fromWhereUsed()) {
            searchButton.setDisable(true);
            return;
        }
        if(!whereToUsed()) {
            searchButton.setDisable(true);
            return;
        }
        if(!datePickerUsed()) {
            searchButton.setDisable(true);
            return;
        }
        if(!validNum(numOfPassengers)) {
            searchButton.setDisable(true);
            return;
        }
        if(!validMaxPrice()) {
            searchButton.setDisable(true);
            return;
        }
        searchButton.setDisable(false);
    }

    private boolean datePickerUsed() {
        return datePicker.getValue() != null;
    }
    private boolean fromWhereUsed() {
        return !fromWhereMenuButton.getText().equals("From Where ?");
    }
    private boolean whereToUsed() {
        return !whereToMenuButton.getText().equals("Where to ?");
    }
    private boolean validMaxPrice() {
        return maxPrice.getText().isEmpty() || validNum(maxPrice);
    }
    private boolean validNum(String text) {
        try {
            int value = Integer.parseInt(text);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean validNum(TextField numOfPassengers) {
        String text = numOfPassengers.getText().trim();
        if (!text.isEmpty()) {
            return validNum(text);
        }
        return false;
    }

    @FXML
    public void fillDataOfFlights() {
        setScrollPane(scrollPane);
        if (!programStarted) {
            programStarted = true;
            showAllFlights();
        } else {
            clearOldData();
            showDesiredFlights();
        }
        vboxStyling(vbox);
    }

    @FXML
    void setFromWhereMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        fromWhereMenuButton.setText(menuItem.getText());
        enableSearchButton();
    }

    @FXML
    void setMaxArrivalTimeMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        maxArrivalTimeButton.setText(menuItem.getText());
    }

    @FXML
    void setMaxDepartTimeMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        maxDepartTimeButton.setText(menuItem.getText());
    }

    @FXML
    void setSeatClassMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        seatClassButton.setText(menuItem.getText());
    }

    @FXML
    void setWhereToMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        whereToMenuButton.setText(menuItem.getText());
        enableSearchButton();
    }

    @FXML
    void validNumForPrice(ActionEvent event) {
        String text = maxPrice.getText().trim();
        if (!text.isEmpty()) {
            try {
                int value = Integer.parseInt(text);
                invalidInputForPriceMsg.setVisible(false);
                enableSearchButton();
            } catch (NumberFormatException e) {
                invalidInputForPriceMsg.setVisible(true);
                searchButton.setDisable(true);
            }
        }
        if(text.isEmpty()){
            invalidInputForPriceMsg.setVisible(false);
            enableSearchButton();
        }
    }

    private String getDepartureAirportCode() {
        return fromWhereMenuButton.getText();
    }
    private String getArrivalAirportCode() {
        return whereToMenuButton.getText();
    }

    public LocalDate getDepartureDate() {
        return datePicker.getValue();
    }
    private int getNumberOfPassengers() {
        return Integer.parseInt(numOfPassengers.getText());
    }

    private LocalDate getMaxDepartureTime() {
        return LocalDate.from(LocalDate.parse(maxDepartTimeButton.getText()));
    }

    private LocalDate getMaxArrivalTime() {
        return LocalDate.from(LocalDate.parse(maxArrivalTimeButton.getText()));
    }

    private String getSeatClass() {
        return seatClassButton.getText();
    }

    private int getMaxPrice(){
        return Integer.parseInt(maxPrice.getText());
    }
>>>>>>> Stashed changes
    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

<<<<<<< Updated upstream
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
=======
    private boolean programStarted = false;

    private final Map<String, SanBay> sanBayMap = new HashMap<>();
    private final Map<ChuyenBay, List<Ve>> chuyenBayVeMap = new HashMap<>();
// MAIN
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();
        setScrollPane(scrollPane);
        fillMenuButtonsItems();
        loadAirports();

        searchButton.setOnAction(event -> {
            clearOldData();
            fillDataOfFlights();
        });

        refresh_button.setOnAction(event -> {
            clearInputFields();
            clearOldData();
            showAllFlights();
        });

        fillDataOfFlights();
    }

    private void clearInputFields() {
        datePicker.setValue(null);
        fromWhereMenuButton.setText("From Where ?");
        whereToMenuButton.setText("Where to ?");
        maxPrice.clear();
        numOfPassengers.clear();
        maxArrivalTimeButton.setText("Max arrival time");
        maxDepartTimeButton.setText("Max departure time");
        seatClassButton.setText("Seat Class");
    }


    private void showAllFlights() {
        fillVbox();
        fillMenuButtonsItems();
    }


    private void showDesiredFlights() {
        try {
            List<ChuyenBay> flights = fetchFlightsFromDatabase();
            for (ChuyenBay flight : flights) {
                List<Ve> ves = fetchVeByMaChuyenBay(flight.getMaChuyenBay()); // Fetch VEs for this flight
                if (desiredSearchData(flight, getAirportCodes())){
                    if (!ves.isEmpty()) { // Check if VEs exist for this flight
                        chuyenBayVeMap.put(flight, ves); // Associate the flight with its list of VEs
                        vbox.getChildren().add(createFlight(flight)); // Pass the VEs to the createFlight method
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Ve> fetchVeByMaChuyenBay(String maChuyenBay) {
        List<Ve> veList = new ArrayList<>();
        try {
            connect = DatabaseDriver.getConnection();
            prepare = connect.prepareStatement("SELECT * FROM VE WHERE MaChuyenBay = ?");
            prepare.setString(1, maChuyenBay);
            result = prepare.executeQuery();
            while (result.next()) {
                Ve ve = new Ve();
                ve.setMaVe(result.getString("MaVe"));
                ve.setMaChuyenBay(result.getString("MaChuyenBay"));
                ve.setMaHangVe(result.getString("MaHangVe"));
                ve.setMaGhe(result.getInt("MaGhe"));
                ve.setGiaTien(result.getFloat("GiaTien"));
                veList.add(ve);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veList;
    }

    private void clearOldData() {
        vbox.getChildren().clear();
    }

    private void fillVbox() {
        try {
            List<ChuyenBay> flights = fetchFlightsFromDatabase();
            for (ChuyenBay flight : flights) {
                vbox.getChildren().add(createFlight(flight));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<ChuyenBay> fetchFlightsFromDatabase() throws SQLException {
        List<ChuyenBay> flights = new ArrayList<>();
        String query = "SELECT MaChuyenBay, MaDuongBay, SoLuongGhe, SoChuyenBay, TGXP, TGKT, TrangThai, GiaVe FROM CHUYENBAY";
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
                chuyenBay_tableview.getItems().add(chuyenBay);
=======
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  // Re-throw the exception after printing the stack trace
        }
        return flights;
    }

    private HBox createFlight(ChuyenBay flight) {
        HBox hbox = new HBox();
        setHboxLabels(hbox, flight);
        hboxStyling(hbox);
        setHboxOnAction(hbox, flight);
        return hbox;
    }

    private void setHboxLabels(HBox hbox, ChuyenBay flight) {
        setAirportLabels(hbox, flight);
        setTimeLabel(hbox, flight);
        setDateLabel(hbox, flight);
        setPriceLabel(hbox, flight);
    }

    private void setAirportLabels(HBox hbox, ChuyenBay flight) {
        String departureAirportName = getAirportNameFromFlight(flight.getMaDuongBay(), true);
        String arrivalAirportName = getAirportNameFromFlight(flight.getMaDuongBay(), false);

        Label departureLabel = new Label("From: " + departureAirportName);
        Label arrivalLabel = new Label("To: " + arrivalAirportName);

        setLabelStyle(departureLabel, "black");
        setLabelStyle(arrivalLabel, "black");

        hbox.getChildren().addAll(departureLabel, arrivalLabel);
    }

    private String getAirportNameFromFlight(String flightCode, boolean isDeparture) {
        String airportName = "Unknown Airport";

        try {
            connect = DatabaseDriver.getConnection();
            String query;
            if (isDeparture) {
                query = "SELECT SB.TenSanBay FROM DUONGBAY DB " +
                        "INNER JOIN SANBAY SB ON DB.MaSanBayDi = SB.MaSanBay " +
                        "WHERE DB.MaDuongBay = ?";
            } else {
                query = "SELECT SB.TenSanBay FROM DUONGBAY DB " +
                        "INNER JOIN SANBAY SB ON DB.MaSanBayDen = SB.MaSanBay " +
                        "WHERE DB.MaDuongBay = ?";
            }

            prepare = connect.prepareStatement(query);
            prepare.setString(1, flightCode);
            result = prepare.executeQuery();

            if (result.next()) {
                airportName = result.getString("TenSanBay");
>>>>>>> Stashed changes
            }

            maChuyenBay_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMaChuyenBay()));
            sanBayDi_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(getSanBayDi(cellData.getValue().getMaDuongBay())));
            sanBayDen_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(getSanBayDen(cellData.getValue().getMaDuongBay())));
            ngayBay_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getThoiGianXuatPhat().toLocalDate().toString()));
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
<<<<<<< Updated upstream
            alert.errorMessage("Error occurred while loading data from the database.");
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
=======
>>>>>>> Stashed changes
        }
    }

<<<<<<< Updated upstream
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
        String sql = "SELECT SUM(SoGheTrong) AS SoGheTrong FROM CT_HANGVE WHERE MaChuyenBay = ?";
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
        String sql = "SELECT SUM(SoGheTrong + SoGheDat) AS SoGhe FROM CT_HANGVE WHERE MaChuyenBay = ?";
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
        return 0;
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
                giaVe_tbcolumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getGiaVe())));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while loading data from the database.");
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
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
            stage.show();
        } catch (IOException e) {
=======
    private void setTimeLabel(HBox hbox, ChuyenBay flight) {
        Label timeLabel = createTimeLabels(flight);
        TraCuuBanVeController.setLabelStyle(timeLabel, "black");
        hbox.getChildren().add(timeLabel);
    }

    private void setDateLabel(HBox hbox, ChuyenBay flight) {
        Label dateLabel = new Label(flight.getThoiGianXuatPhat().toLocalDate().toString());
        TraCuuBanVeController.setLabelStyle(dateLabel, "black");
        hbox.getChildren().add(dateLabel);
    }

    private void setPriceLabel(HBox hbox, ChuyenBay flight) {
        Label priceLabel = new Label(String.valueOf(flight.getGiaVe()));
        TraCuuBanVeController.setLabelStyle(priceLabel, "black");
        hbox.getChildren().add(priceLabel);
    }

    private Label createTimeLabels(ChuyenBay chuyenBay){
        return new Label(chuyenBay.getThoiGianXuatPhat().getHour() + ":"
                + chuyenBay.getThoiGianXuatPhat().getMinute()
                + ":" + chuyenBay.getThoiGianXuatPhat().getSecond()
                + " - " + chuyenBay.getThoiGianKetThuc().getHour() + ":"
                + chuyenBay.getThoiGianKetThuc().getMinute() + ":"
                + chuyenBay.getThoiGianKetThuc().getSecond());
    }

    private void setHboxOnAction(HBox hbox, ChuyenBay chuyenBay) {
        hbox.setOnMouseClicked(event -> {
            try {
                invalidInputForPriceMsg.setVisible(!validMaxPrice());
                invalidInputOfPassengersMsg.setVisible(!validNum(numOfPassengers));
                if (validMaxPrice() && validNum(numOfPassengers)) {
                    PopUpXacNhanBanVeController.handleHBoxClick(chuyenBay, getNumberOfPassengers());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void loadAirports() {
        try {
            connect = DatabaseDriver.getConnection();
            String query = "SELECT MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai FROM SANBAY";
            statement = connect.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                String maSanBay = result.getString("MaSanBay");
                String tenSanBay = result.getString("TenSanBay");
                String tenVietTat = result.getString("TenVietTat");
                String diaChi = result.getString("DiaChi");
                Integer trangThai = result.getInt("TrangThai");
                SanBay sanBay = new SanBay(maSanBay, tenSanBay, tenVietTat, diaChi, trangThai);
                sanBayMap.put(maSanBay, sanBay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setLabelStyle(Label label, String color)  {
        if(color.equals("white")) {
            label.setStyle("-fx-pref-width: 250;" +
                    "-fx-padding: 25;" +
                    " -fx-border-width: 5;" +
                    " -fx-alignment: center;" +
                    " -fx-font-size: 14; " +
                    "-fx-text-fill: white");
            return;
        }
        label.setStyle("-fx-pref-width: 250;" +
                "-fx-padding: 25;" +
                " -fx-border-width: 5;" +
                " -fx-alignment: center;" +
                " -fx-font-size: 14;" +
                "-fx-text-fill: black");
    }

    private void setScrollPane(ScrollPane scrollPane) {
        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    private void vboxStyling(VBox vbox) {
        vbox.setSpacing(10);
        vbox.setStyle("-fx-padding: 10;");
    }

    private void hboxStyling(HBox hbox) {
        hboxStyle(hbox);
        hboxHover(hbox);
        hboxClicked(hbox);
    }

    public static void hboxStyle(HBox hbox) {
        hbox.setStyle("-fx-background-color: white;" +
                " -fx-border-width: 1;" +
                " -fx-border-color: black;");
    }
    public static void hboxHover(HBox hbox) {
        hbox.setOnMouseEntered(event ->{
            hbox.setStyle("-fx-background-color: #605DEC; -fx-border-color: black;");
            hbox.getChildren().forEach(node -> {
                if (node instanceof Label) {
                    setLabelStyle((Label) node, "white");
                }
            });
        });

        hbox.setOnMouseExited(event ->{
            hbox.setStyle("-fx-background-color: white; -fx-border-color: black;");
            hbox.getChildren().forEach(node -> {
                if (node instanceof Label) {
                    setLabelStyle((Label) node, "black");
                }
            });
        });
    }
    public static void hboxClicked(HBox hbox) {
        hbox.setOnMousePressed(event ->{
            hbox.setStyle("-fx-background-color: #4422ac; -fx-border-color: white;");
            hbox.getChildren().forEach(node -> {
                if (node instanceof Label) {
                    setLabelStyle((Label) node, "white");
                }
            });
        });
        hbox.setOnMouseReleased(event ->{
            hbox.setStyle("-fx-background-color: #605DEC; -fx-border-color: black;");
            hbox.getChildren().forEach(node -> {
                if (node instanceof Label) {
                    setLabelStyle((Label) node, "white");
                }
            });
        });
    }

    public static Map<String, Boolean> departureAirports = new HashMap<>();
    public static Map<String, Boolean> arrivalAirports = new HashMap<>();

//    Xử lý lọc dữ lieeuj
private void fillMenuButtonsItems() {
    fillMaps();
    departureAirports.clear();
    arrivalAirports.clear();
    fillTimeButtons();
}

    private void fillMaps() {
        fillDepartureAirportsMap();
        fillArrivalAirportsMap();
    }

    private void fillDepartureAirportsMap() {
        Set<String> addedAirportCodes = new HashSet<>();

        String query = "SELECT DISTINCT sb.MaSanBay, sb.TenSanBay " +
                "FROM CHUYENBAY cb " +
                "JOIN DUONGBAY db ON cb.MaDuongBay = db.MaDuongBay " +
                "JOIN SANBAY sb ON db.MaSanBayDi = sb.MaSanBay";

        try {
            statement = connect.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                String departureAirportCode = result.getString("MaSanBay");
                String departureAirportName = result.getString("TenSanBay");
                if (!addedAirportCodes.contains(departureAirportCode)) {
                    MenuItem menuItem = new MenuItem(departureAirportName);
                    menuItem.setOnAction(this::setFromWhereMenuButtonText);
                    fromWhereMenuButton.getItems().add(menuItem);
                    addedAirportCodes.add(departureAirportCode);
                }
            }

        } catch (SQLException e) {
>>>>>>> Stashed changes
            e.printStackTrace();
            alert.errorMessage("Error occurred while opening the detail window.");
        }
    }

<<<<<<< Updated upstream
=======
    private void fillArrivalAirportsMap() {
        Set<String> addedAirportCodes = new HashSet<>();

        String query = "SELECT DISTINCT sb.MaSanBay, sb.TenSanBay " +
                "FROM CHUYENBAY cb " +
                "JOIN DUONGBAY db ON cb.MaDuongBay = db.MaDuongBay " +
                "JOIN SANBAY sb ON db.MaSanBayDen =sb.MaSanBay";

        try {
            statement = connect.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                String arrivalAirportCode = result.getString("MaSanBay");
                String arrivalAirportName = result.getString("TenSanBay");
                if (!addedAirportCodes.contains(arrivalAirportCode)) {
                    MenuItem menuItem = new MenuItem(arrivalAirportName);
                    menuItem.setOnAction(this::setWhereToMenuButtonText);
                    whereToMenuButton.getItems().add(menuItem);
                    addedAirportCodes.add(arrivalAirportCode);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillTimeButtons() {
        fillMaxArrivalTimeMenuContext();
        fillMaxDepartureTimeMenuContext();
    }

    private void fillMaxDepartureTimeMenuContext() {
        setTimes("departure");
    }

    private void fillMaxArrivalTimeMenuContext() {
        setTimes("arrival");
    }

    private void setTimes(String type) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        for (int hour = 0; hour < 24; hour++) {
            LocalTime time = LocalTime.of(hour, 0); // Using LocalTime for time formatting
            MenuItem timeItem = new MenuItem(time.format(formatter));
            switch (type) {
                case "departure":
                    setDepartureTimeItemOnAction(timeItem);
                    addItemToMenu(timeItem, maxDepartTimeButton);
                    break;
                case "arrival":
                    setArrivalTimeItemOnAction(timeItem);
                    addItemToMenu(timeItem, maxArrivalTimeButton);
                    break;
            }
        }
    }

    private void setDepartureTimeItemOnAction(MenuItem time) {
        time.setOnAction(this::setMaxDepartTimeMenuButtonText);
    }

    private void setArrivalTimeItemOnAction(MenuItem time) {
        time.setOnAction(this::setMaxArrivalTimeMenuButtonText);
    }

    private void addItemToMenu(MenuItem item, MenuButton menuButton) {
        menuButton.getItems().add(item);
    }


    //    Xử lý tìm dữ liệu theo mong mốn

    public Map<String, String[]> getAirportCodes() {
        Map<String, String[]> airportCodes = new HashMap<>();
        String query = "SELECT cb.MaChuyenBay, db.MaSanBayDi, db.MaSanBayDen " +
                "FROM CHUYENBAY cb " +
                "JOIN DUONGBAY db ON cb.MaDuongBay = db.MaDuongBay";

        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();

            while (result.next()) {
                String flightCode = result.getString("MaChuyenBay");
                String departureAirportCode = result.getString("MaSanBayDi");
                String arrivalAirportCode = result.getString("MaSanBayDen");
                airportCodes.put(flightCode, new String[]{departureAirportCode, arrivalAirportCode});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airportCodes;
    }

    public boolean desiredSearchData(ChuyenBay flight, Map<String, String[]> airportCodes) {
        String flightCode = flight.getMaChuyenBay();
        if (!airportCodes.containsKey(flightCode)) {
            return false;
        }

        String[] codes = airportCodes.get(flightCode);
        String departureAirportCode = codes[0];
        String arrivalAirportCode = codes[1];

        if (!desiredDepartureAirportCode(departureAirportCode)) {
            return false;
        }
        if (!desiredArrivalAirportCode(arrivalAirportCode)) {
            return false;
        }
        if (!desiredDepartureDate(flight)) {
            return false;
        }
        if (!validNumOfPassengers(flight)) {
            return false;
        }
        if (!desiredMaxDepartureTime(flight)) {
            return false;
        }
        if (!desiredMaxArrivalTime(flight)) {
            return false;
        }
        if (!maxPrice.getText().isEmpty()) {
            if (!validNum(maxPrice.getText())) {
                invalidInputForPriceMsg.setVisible(true);
                return false;
            }
            invalidInputForPriceMsg.setVisible(false);
            if (!validMaxPrice(flight)) {
                return false;
            }
        }
        invalidInputForPriceMsg.setVisible(false);
        return true;
    }

    private boolean desiredDepartureAirportCode(String departureAirportCode) {
        return getDepartureAirportCode().equals(departureAirportCode);
    }

    private boolean desiredArrivalAirportCode(String arrivalAirportCode) {
        return getArrivalAirportCode().equals(arrivalAirportCode);
    }

    private boolean desiredDepartureDate(ChuyenBay flight) {
        LocalDate desiredDate = getDepartureDate();
        LocalDate flightDate = LocalDate.from(flight.getThoiGianXuatPhat());

        // Compare the whole date directly
        return desiredDate.equals(flightDate);
    }

    private boolean validNumOfPassengers(ChuyenBay flight) {
        return getNumberOfPassengers() <= flight.getSoLuongGhe();
    }

    private boolean validTime(LocalDate maxTime, LocalDate flightTime) {
        return flightTime.isBefore(maxTime) || flightTime.equals(maxTime);
    }

    private boolean desiredMaxDepartureTime(ChuyenBay flight) {
        if (!maxDepartTimeButton.getText().equals("Max departure time")) {
            LocalDate maxDepartureTime = getMaxDepartureTime();
            LocalDate flightDepartureTime = LocalDate.from(flight.getThoiGianXuatPhat());
            return validTime(maxDepartureTime, flightDepartureTime);
        }
        return true;
    }

    private boolean desiredMaxArrivalTime(ChuyenBay flight) {
        if (!maxArrivalTimeButton.getText().equals("Max arrival time")) {
            LocalDate maxArrivalTime = getMaxArrivalTime();
            LocalDate flightArrivalTime = LocalDate.from(flight.getThoiGianKetThuc());
            return validTime(maxArrivalTime, flightArrivalTime);
        }
        return true;
    }

    private boolean validMaxPrice(ChuyenBay flight) {
        if (!maxPrice.getText().isEmpty()) {
            double maxPriceValue = getMaxPrice();
            String seatClass = getSeatClass();

            if (!seatClass.equals("Seat class")) {
                double flightPrice = getFlightPrice(flight.getMaChuyenBay(), seatClass);
                if (maxPriceValue < flightPrice) {
                    return false;
                }
            } else {
                // Default to Economy class if no specific seat class is selected
                double flightPrice = getFlightPrice(flight.getMaChuyenBay(), "Tiết kiệm");
                if (maxPriceValue < flightPrice) {
                    return false;
                }
            }
        }
        return true;
    }

    private double getFlightPrice(String maChuyenBay, String tenHangVe) {
        String sql = "SELECT v.GiaTien * h.HeSo AS FinalPrice " +
                "FROM VE v " +
                "JOIN HANGVE h ON v.MaHangVe = h.MaHangVe " +
                "WHERE v.MaChuyenBay = ? AND h.TenHangVe = ?";

        try (Connection conn = DatabaseDriver.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, maChuyenBay);
            pstmt.setString(2, tenHangVe);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("FinalPrice");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0.0;
    }

>>>>>>> Stashed changes
}
