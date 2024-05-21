package org.example.flightticketmanagement.Controllers.Admin;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.ChuyenBay;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.SanBay;
import org.example.flightticketmanagement.Models.Ve;

import java.net.URL;
import java.sql.*;
import java.util.*;

public class TraCuuBanVeController implements Initializable {

    @FXML
    private DatePicker datePicker;

    @FXML
    private MenuButton fromWhereMenuButton;

    @FXML
    private Label invalidInputForPriceMsg;

    @FXML
    private Label invalidInputOfPassengersMsg;

    @FXML
    private MenuButton maxArrivalTimeButton;

    @FXML
    private MenuButton maxDepartTimeButton;

    @FXML
    private TextField maxPrice;

    @FXML
    private TextField numOfPassengers;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button searchButton;

    @FXML
    private MenuButton seatClassButton;

    @FXML
    private VBox vbox;

    @FXML
    private MenuButton whereToMenuButton;

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    private boolean programStarted = false;

    private Map<String, SanBay> airports = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setScrollPane(scrollPane);
        fillMenuButtonsItems();
        loadAirports();
        searchButton.setOnAction(event -> {
            clearOldData();
            fillDataOfFlights();
        });
        fillDataOfFlights();
    }

    public static Map<String, Boolean> departureAirports = new HashMap<>();
    public static Map<String, Boolean> arrivalAirports = new HashMap<>();

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

    private void showAllFlights() {
        fillVbox();
        fillMenuButtonsItems();
    }

    private Map<ChuyenBay, List<Ve>> chuyenBayVeMap = new HashMap<>();

    private void showDesiredFlights() {
        try {
            List<ChuyenBay> flights = fetchFlightsFromDatabase();
            for (ChuyenBay flight : flights) {
                List<Ve> ves = fetchVeByMaChuyenBay(flight.getMaChuyenBay()); // Fetch VEs for this flight
                if (!ves.isEmpty()) { // Check if VEs exist for this flight
                    chuyenBayVeMap.put(flight, ves); // Associate the flight with its list of VEs
                    vbox.getChildren().add(createFlight(flight)); // Pass the VEs to the createFlight method
                }
            }
        } catch (Exception e) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private List<ChuyenBay> fetchFlightsFromDatabase() throws SQLException {
        List<ChuyenBay> flights = new ArrayList<>();
        String query = "SELECT MaChuyenBay, MaDuongBay, SoLuongGhe, SoChuyenBay, TGXP, TGKT, TrangThai, GiaVe FROM CHUYENBAY";
        connect = DatabaseDriver.getConnection();
        statement = connect.createStatement();
        result = statement.executeQuery(query);

        try {
            while (result.next()) {
                ChuyenBay flight = new ChuyenBay(
                        result.getString("MaChuyenBay"),
                        result.getString("MaDuongBay"),
                        result.getInt("SoLuongGhe"),
                        result.getInt("SoChuyenBay"),
                        result.getTimestamp("TGXP").toLocalDateTime(),
                        result.getTimestamp("TGKT").toLocalDateTime(),
                        result.getString("TrangThai"),
                        result.getFloat("GiaVe")
                );
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  // Re-throw the exception after printing the stack trace
        } finally {
            // Close resources to avoid potential leaks
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connect != null) connect.close();
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return airportName;
    }


    private void setTimeLabel(HBox hbox, ChuyenBay flight) {
        Label timeLabel = new Label(
                flight.getThoiGianXuatPhat().toLocalTime() + " - " +
                        flight.getThoiGianKetThuc().toLocalTime()
        );
        TraCuuBanVeController.setLabelStyle(timeLabel, "black");
        hbox.getChildren().add(timeLabel);
    }

    private void setDateLabel(HBox hbox, ChuyenBay flight) {
        Label dateLabel = new Label(
                flight.getThoiGianXuatPhat().toLocalDate() + ""
        );
        TraCuuBanVeController.setLabelStyle(dateLabel, "black");
        hbox.getChildren().add(dateLabel);
    }

    private void setPriceLabel(HBox hbox, ChuyenBay flight) {
        Label priceLabel = new Label(String.valueOf(flight.getGiaVe()));
        TraCuuBanVeController.setLabelStyle(priceLabel, "black");
        hbox.getChildren().add(priceLabel);
    }
    
    private Label createAirportLabels(ChuyenBay chuyenBay){
        return new Label();
    }
    
    private Label createTimeLabels(ChuyenBay chuyenBay){
        return new Label(chuyenBay.getThoiGianXuatPhat().getHour() + ":" + chuyenBay.getThoiGianXuatPhat().getMinute()
            + " " + chuyenBay.getThoiGianXuatPhat().getSecond());
    }

    private void setHboxOnAction(HBox hbox, ChuyenBay chuyenBay) {
        hbox.setOnMouseClicked(event -> {
            try {
                invalidInputForPriceMsg.setVisible(!validMaxPrice());
                invalidInputOfPassengersMsg.setVisible(!validNum(numOfPassengers));
                if (validMaxPrice() && validNum(numOfPassengers)) {
                    // Implement handle click action here
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


    private boolean validMaxPrice() {
        if (!maxPrice.getText().isEmpty()) {
            try {
                float maxPriceValue = Float.parseFloat(maxPrice.getText());
                String seatClass = getSeatClass();

                if (!seatClass.equals("Seat class")) {
                    float seatClassMultiplier = getSeatClassMultiplier(seatClass);
                    ChuyenBay flight = fetchChuyenBay(); // Assuming you have a method to fetch the flight
                    if (flight != null) {
                        // Adjust this logic based on how you retrieve the ticket price from the flight object
                        float flightPrice = flight.getGiaVe();
                        if (maxPriceValue < flightPrice * seatClassMultiplier) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private ChuyenBay fetchChuyenBay() {
        return null;
    }

    private float getSeatClassMultiplier(String seatClass) {
        // This is a placeholder method. Implement the actual database query to get the HeSo value.
        switch (seatClass) {
            case "Tiết kiệm":
                return 1.0f;
            case "Phổ thông":
                return 1.3f;
            case "Thương gia":
                return 1.8f;
            case "Hạng nhất":
                return 2.5f;
            default:
                return 1.0f; // Default multiplier in case seat class is not recognized
        }
    }

    public boolean desiredSearchData(ChuyenBay flight, Ve ve) {
        if(!desiredDepartureAirportCode(flight)) {
            return false;
        }
        if(!desiredArrivalAirportCode(flight)) {
            return false;
        }
        if(!desiredDepartureDate(flight)) {
            return false;
        }
        if(!validNumOfPassengers(flight)) {
            return false;
        }
        if(!desiredMaxDepartureTime(flight)) {
            return false;
        }
        if(!desiredMaxArrivalTime(flight)) {
            return false;
        }
        if(!maxPrice.getText().isEmpty()) {
            if(!validNum(maxPrice)) {
                invalidInputForPriceMsg.setVisible(true);
                return false;
            }
            invalidInputForPriceMsg.setVisible(false);
            if(!validMaxPrice()) {
                return false;
            }
        }
        invalidInputForPriceMsg.setVisible(false);
        return true;
    }

    private boolean validNum(TextField textField) {
        try {
            String text = textField.getText();
            if (text.isEmpty()) return true; // No input, consider valid
            int num = Integer.parseInt(text);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validTime(Time desiredMaxTime, Time flightTime) {
        String hour = desiredMaxTime.getHour();
        String period = desiredMaxTime.getPeriod();
        if (period.equals("PM")) {

            if(flightTime.getPeriod().equals("PM")) {

                if (flightTime.getHour().compareTo(hour) > 0) {
                    return false;
                }
            }
        }
        if (period.equals("AM")) {

            if (flightTime.getPeriod().equals("PM")) {
                return false;
            }

            if (flightTime.getHour().compareTo(hour) > 0) {
                return false;
            }

        }
        return true;
    }

    @FXML
    private void validNumForPrice(ActionEvent actionEvent) {
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

    private void setScrollPane(ScrollPane scrollPane) {
//        scrollPane.setFitToWidth(true);
//        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
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

    public void enableSearchButton() {
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

    @FXML
    private void setFromWhereMenuButton(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        fromWhereMenuButton.setText(menuItem.getText());
        enableSearchButton();
    }
    @FXML
    private void setWhereToMenuButton(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        whereToMenuButton.setText(menuItem.getText());
        enableSearchButton();
    }
    @FXML
    private void setMaxDepartTimeMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        maxDepartTimeButton.setText(menuItem.getText());
    }
    @FXML
    private void setMaxArrivalTimeMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        maxArrivalTimeButton.setText(menuItem.getText());
    }
    @FXML
    private void setSeatClassMenuButtonText(ActionEvent e) {
        Object source = e.getSource();
        MenuItem menuItem = (MenuItem) source;
        seatClassButton.setText(menuItem.getText());
    }

    private String getDepartureAirportCode() {
        return fromWhereMenuButton.getText();
    }
    private String getArrivalAirportCode() {
        return whereToMenuButton.getText();
    }
    private String chosenDay() {
        return Integer.toString(datePicker.getValue().getDayOfMonth());
    }
    private String chosenMonth() {
        return Integer.toString(datePicker.getValue().getMonth().getValue());
    }
    private String chosenYear() {
        return Integer.toString(datePicker.getValue().getYear());
    }
    private void prependZeroIfNeeded(String item) {
        if (Integer.parseInt(item) < 10) {
            item = "0" + item;
        }
    }

    private int getNumberOfPassengers() {
        return Integer.parseInt(numOfPassengers.getText());
    }
    private Time getMaxDepartureTime() {
        String time = maxDepartTimeButton.getText();
        return Time.parse(time);
    }


    private Time getMaxArrivalTime() {
        String time = maxArrivalTimeButton.getText();
        return Time.parse(time);
    }
    private String getSeatClass() {
        return seatClassButton.getText();
    }
    private int getMaxPrice() {
        return Integer.parseInt(maxPrice.getText());
    }


    private void fillMenuButtonsItems() {
        fillMaps();
        departureAirports.clear();
        arrivalAirports.clear();
        fillTimeButtons();
    }

    private String fetchMaSanBayDiByMaDuongBay(String maDuongBay) {
        String maSanBayDi = null;
        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(
                     "SELECT MaSanBayDi FROM DUONGBAY WHERE MaDuongBay = ?")) {
            prepare.setString(1, maDuongBay);
            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    maSanBayDi = result.getString("MaSanBayDi");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maSanBayDi;
    }

    private boolean desiredDepartureAirportCode(ChuyenBay flight) {
        String maSanBayDi = fetchMaSanBayDiByMaDuongBay(flight.getMaDuongBay());
        return getDepartureAirportCode().equals(maSanBayDi);
    }
    private String fetchMaSanBayDenByMaDuongBay(String maDuongBay) {
        String maSanBayDen = null;
        try (Connection connect = DatabaseDriver.getConnection();
             PreparedStatement prepare = connect.prepareStatement(
                     "SELECT MaSanBayDen FROM DUONGBAY WHERE MaDuongBay = ?")) {
            prepare.setString(1, maDuongBay);
            try (ResultSet result = prepare.executeQuery()) {
                if (result.next()) {
                    maSanBayDen = result.getString("MaSanBayDen");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maSanBayDen;
    }

    private boolean desiredArrivalAirportCode(ChuyenBay flight) {
        String maSanBayDen = fetchMaSanBayDenByMaDuongBay(flight.getMaDuongBay());
        return getArrivalAirportCode().equals(maSanBayDen);
    }
    private boolean desiredDepartureDate(ChuyenBay flight) {
        LocalDate desiredDate = getDepartureDate();

        LocalDate flightDepartureDate = flight.getThoiGianXuatPhat().toLocalDate();

        return desiredDate.equals(flightDepartureDate);
    }

    // Example implementation of getDepartureDate
    private LocalDate getDepartureDate() {
        // Replace with actual logic to get the desired departure date
        return LocalDate.of(2023, 5, 20); // Example date
    }

    private boolean validNumOfPassengers(ChuyenBay flight) {
        return getNumberOfPassengers() <= flight.getSoLuongGhe();
    }

    private boolean desiredMaxDepartureTime(ChuyenBay flight) {
        if(!maxDepartTimeButton.getText().equals("Max departure time")) {
            Time maxDepartureTime = getMaxDepartureTime();
            LocalTime localTime = flight.getThoiGianXuatPhat().toLocalTime();

            String hour = String.valueOf(localTime.getHour());
            String minute = String.valueOf(localTime.getMinute());
            String period = localTime.getHour() < 12 ? "AM" : "PM";
            Time flightDepartureTime = new Time(hour, minute, period);
            return validTime(maxDepartureTime, flightDepartureTime);
        }
        return true;
    }

    private boolean desiredMaxArrivalTime(ChuyenBay flight) {
        if (!maxArrivalTimeButton.getText().equals("Max arrival time")) {
            Time maxArrivalTime = getMaxArrivalTime();
            Time flightDepartureTime = new Time(
                    String.valueOf(flight.getThoiGianXuatPhat().getHour()),
                    String.valueOf(flight.getThoiGianXuatPhat().getMinute()),
                    flight.getThoiGianXuatPhat().getHour() < 12 ? "AM" : "PM"
            );
            return validTime(maxArrivalTime, flightDepartureTime);
        }
        return true;
    }


    private void fillMenuButton(MenuButton menuButton, Map<String, Boolean> items) {
        menuButton.getItems().clear();
        for (String item : items.keySet()) {
            CheckMenuItem checkMenuItem = new CheckMenuItem(item);
            checkMenuItem.setSelected(items.get(item));
            checkMenuItem.setOnAction(e -> items.put(item, checkMenuItem.isSelected()));
            menuButton.getItems().add(checkMenuItem);
        }
    }


    public static void setLabelStyle(Label label, String color) {
//        label.setStyle("-fx-text-fill: " + color + "; -fx-font-size: 14px; -fx-font-weight: bold;");
        if(color.equals("white")) {
            label.setStyle("-fx-pref-width: 400;" +
                    "-fx-padding: 25;" +
                    " -fx-border-width: 5;" +
                    " -fx-alignment: center;" +
                    " -fx-font-size: 13; " +
                    "-fx-text-fill: white");
            return;
        }
        label.setStyle("-fx-pref-width: 400;" +
                "-fx-padding: 25;" +
                " -fx-border-width: 5;" +
                " -fx-alignment: center;" +
                " -fx-font-size: 13;" +
                "-fx-text-fill: black");
    }

    private void vboxStyling(VBox vbox) {
        vbox.setSpacing(10);
        vbox.setStyle("-fx-padding: 10;");
        vbox.setStyle("-fx-background-color: white;");
    }

    private void hboxStyling(HBox hbox) {
        hbox.setSpacing(20);
        hbox.setStyle("-fx-padding: 10; -fx-border-color: black; -fx-border-width: 1;");
//       flyx
        hboxStyle(hbox);
        hboxHover(hbox);
        hboxClicked(hbox);
    }

    public void hboxStyle(HBox hbox) {
        hbox.setStyle("-fx-background-color: white;" +
                " -fx-border-width: 1;" +
                " -fx-border-color: black;");
    }
    public void hboxHover(HBox hbox) {
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
    public void hboxClicked(HBox hbox) {
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

    private void loadAirports() {
        try {
            connect = DatabaseDriver.getConnection();
            statement = connect.createStatement();
            String query = "SELECT MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai FROM SANBAY";
            result = statement.executeQuery(query);

            while (result.next()) {
                String maSanBay = result.getString("MaSanBay");
                String tenSanBay = result.getString("TenSanBay");
                String tenVietTat = result.getString("TenVietTat");
                String diaChi = result.getString("DiaChi");
                int trangThai = result.getInt("TrangThai");

                SanBay sanBay = new SanBay(maSanBay, tenSanBay, tenVietTat, diaChi, trangThai);
                airports.put(maSanBay, sanBay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add your event handlers here for menu button texts
    @FXML
    void setFromWhereMenuButtonText(ActionEvent event) {
        MenuItem source = (MenuItem) event.getSource();
        fromWhereMenuButton.setText(source.getText());
    }

    @FXML
    void setWhereToMenuButtonText(ActionEvent event) {
        MenuItem source = (MenuItem) event.getSource();
        whereToMenuButton.setText(source.getText());
    }

    private void fillMaps() {
        try {
            List<ChuyenBay> flights = fetchFlightsFromDatabase();
            for (ChuyenBay flight : flights) {
                fillDepartureAirportsMap(flight);
                fillArrivalAirportsMap(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillDepartureAirportsMap(ChuyenBay flight) {
        String departureAirportCode = getAirportNameFromFlight(flight.getMaDuongBay(), true);
        if (!departureAirports.containsKey(departureAirportCode)) {
            MenuItem departureAirportItem = new MenuItem(departureAirportCode);
            departureAirportItem.setOnAction(this::setFromWhereMenuButtonText);
            fromWhereMenuButton.getItems().add(departureAirportItem);
            departureAirports.put(departureAirportCode, true);
        }
    }

    private void fillArrivalAirportsMap(ChuyenBay flight) {
        String arrivalAirportCode = getAirportNameFromFlight(flight.getMaDuongBay(), false);
        if (!arrivalAirports.containsKey(arrivalAirportCode)) {
            MenuItem arrivalAirportItem = new MenuItem(arrivalAirportCode);
            arrivalAirportItem.setOnAction(this::setWhereToMenuButtonText);
            whereToMenuButton.getItems().add(arrivalAirportItem);
            arrivalAirports.put(arrivalAirportCode, true);
        }
    }

    private void fillTimeButtons() {
        fillMaxDepartureTimeMenuContext();
        fillMaxArrivalTimeMenuContext();
    }

    private void fillMaxDepartureTimeMenuContext() {
        setTimes("departure");
    }

    private void fillMaxArrivalTimeMenuContext() {
        setTimes("arrival");
    }

    private void setTimes(String type) {
        for (int i = 1; i <= 24; i++) {
            MenuItem time = setTime(i);
            if (i < 10) {
                time.setText("0" + i + ":00");
            } else {
                time.setText(i + ":00");
            }
            if (type.equals("departure")) {
                setDepartureTimeItemOnAction(time);
                maxDepartTimeButton.getItems().add(time);
            } else if (type.equals("arrival")) {
                setArrivalTimeItemOnAction(time);
                maxArrivalTimeButton.getItems().add(time);
            }
        }
    }

    private void setDepartureTimeItemOnAction(MenuItem time) {
        time.setOnAction(this::setMaxDepartTimeMenuButtonText);
    }

    private void setArrivalTimeItemOnAction(MenuItem time) {
        time.setOnAction(this::setMaxArrivalTimeMenuButtonText);
    }

    private MenuItem setTime(int i) {
        return new MenuItem(i < 10 ? "0" + i + ":00" : i + ":00");
    }

    private void addItemToMenu(MenuItem item, MenuButton menuButton) {
        menuButton.getItems().add(item);
    }
}
