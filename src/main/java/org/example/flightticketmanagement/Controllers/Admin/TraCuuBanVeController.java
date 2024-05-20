package org.example.flightticketmanagement.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.ChuyenBay;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.SanBay;

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
        // Initialization logic
        setScrollPane(scrollPane);
        fillMenuButtonsItems();

        // Load airports data
        loadAirports();

        // Add event handler to search button
        searchButton.setOnAction(event -> fillDataOfFlights());

        // Initial data load
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

    private void showDesiredFlights() {
        try {
            List<ChuyenBay> flights = fetchFlightsFromDatabase();
            for (ChuyenBay flight : flights) {
                if (desiredSearchData(flight)) {
                    vbox.getChildren().add(createFlight(flight));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void setHboxOnAction(HBox hbox, ChuyenBay flight) {
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
        try {
            String maxPriceText = maxPrice.getText();
            if (maxPriceText.isEmpty()) return true; // No max price set
            float price = Float.parseFloat(maxPriceText);
            return price > 0;
        } catch (NumberFormatException e) {
            return false;
        }
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

    private void setScrollPane(ScrollPane scrollPane) {
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
    }

    private void fillMenuButtonsItems() {
        fillMenuButton(fromWhereMenuButton, new HashMap<>());
        fillMenuButton(whereToMenuButton, new HashMap<>());
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
        label.setStyle("-fx-text-fill: " + color + "; -fx-font-size: 14px; -fx-font-weight: bold;");
    }

    private void vboxStyling(VBox vbox) {
        vbox.setSpacing(10);
        vbox.setStyle("-fx-padding: 10;");
    }

    private void hboxStyling(HBox hbox) {
        hbox.setSpacing(20);
        hbox.setStyle("-fx-padding: 10; -fx-border-color: black; -fx-border-width: 1;");
    }

    private boolean desiredSearchData(ChuyenBay flight) {
        boolean matches = true;

        // Check max price
        if (!maxPrice.getText().isEmpty()) {
            try {
                float maxPriceValue = Float.parseFloat(maxPrice.getText());
                if (flight.getGiaVe() > maxPriceValue) {
                    matches = false;
                }
            } catch (NumberFormatException e) {
                invalidInputForPriceMsg.setVisible(true);
                matches = false;
            }
        }

        // Check number of passengers
        if (!numOfPassengers.getText().isEmpty()) {
            try {
                int passengerCount = Integer.parseInt(numOfPassengers.getText());
                if (flight.getSoLuongGhe() < passengerCount) {
                    matches = false;
                }
            } catch (NumberFormatException e) {
                invalidInputOfPassengersMsg.setVisible(true);
                matches = false;
            }
        }

        // Additional filtering logic (e.g., date, time, airports) can be added here

        return matches;
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
    private void setFromWhereMenuButtonText(ActionEvent event) {
        MenuItem source = (MenuItem) event.getSource();
        fromWhereMenuButton.setText(source.getText());
    }

    private void setWhereToMenuButtonText(ActionEvent event) {
        MenuItem source = (MenuItem) event.getSource();
        whereToMenuButton.setText(source.getText());
    }

    private void setMaxDepartTimeMenuButtonText(ActionEvent event) {
        MenuItem source = (MenuItem) event.getSource();
        maxDepartTimeButton.setText(source.getText());
    }

    private void setMaxArrivalTimeMenuButtonText(ActionEvent event) {
        MenuItem source = (MenuItem) event.getSource();
        maxArrivalTimeButton.setText(source.getText());
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
