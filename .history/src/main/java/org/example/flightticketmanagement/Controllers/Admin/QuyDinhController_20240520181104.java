package org.example.flightticketmanagement.Controllers.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.HangVe;
import org.example.flightticketmanagement.Models.SanBay;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class QuyDinhController implements Initializable {

    @FXML
    private TableView<HangVe> hangve_tbv;

    @FXML
    private TableColumn<?, ?> heso_col;

    @FXML
    private TableColumn<?, ?> idsanbay_col;

    @FXML
    private TextField maxsbtg_txf;

    @FXML
    private TextField maxtgdung_tfx;

    @FXML
    private TextField mintgbay_txf;

    @FXML
    private TextField mintgdatve_tfx;

    @FXML
    private TextField mintgdung_tfx;

    @FXML
    private TextField mintghuyve_tfx;

    @FXML
    private Button refreshHangVeData_btn;

    @FXML
    private Button refreshSanBayData_btn;

    @FXML
    private TableView<SanBay> sanbay_tbv;

    @FXML
    private Button searchSanBay_btn;

    @FXML
    private TextField searchhangve_tfx;

    @FXML
    private TextField searchsanbay_tfx;

    @FXML
    private TableColumn<SanBay, Integer> status_col;

    @FXML
    private TableColumn<?, ?> stthgve_col;

    @FXML
    private TableColumn<?, ?> sttsanbay_col;

    @FXML
    private Button suahangve_btn;

    @FXML
    private Button suaquydinh_btn;

    @FXML
    private Button suasanbay_btn;

    @FXML
    private TableColumn<?, ?> tenhangve_col;

    @FXML
    private TableColumn<?, ?> tensanbay_col;

    @FXML
    private Button themhangve_btn;

    @FXML
    private Button themsanbay_btn;

    @FXML
    private TableColumn<?, ?> tinhthanh_col;

    @FXML
    private TableColumn<?, ?> vietTat_col;

    @FXML
    private Button xoahangve_btn;

    @FXML
    private Button xoasanbay_btn;


    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    private FilteredList<SanBay> filteredData;

    private ObservableList<HangVe> hangVeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showSanBayList();
        showHangVeList();
        suasanbay_btn.setOnAction(this::editSelectedAirports);
        xoasanbay_btn.setOnAction(this::deactivateSelectedAirports);
        refreshSanBayData_btn.setOnAction(event -> showSanBayList());
        searchSanBay_btn.setOnAction(this::searchSanBay);
    }

    private void showHangVeList() {
        ObservableList<HangVe> hangVeList = FXCollections.observableArrayList();

        String query = "SELECT * FROM HANGVE";

        try {
            connect = DatabaseDriver.getConnection();
            statement = connect.createStatement();
            result = statement.executeQuery(query);

            int rowNum = 1;

            while (result.next()) {
                String maHangVe = result.getString("MaHangVe");
                String tenHangVe = result.getString("TenHangVe");
                float heSo = result.getFloat("HeSo");
                int trangThai = result.getInt("TrangThai");
                int soThuTu = rowNum++;

                HangVe hangVe = new HangVe(maHangVe, tenHangVe, heSo, trangThai);
                hangVeList.add(hangVe);
            }

            tenhangve_col.setCellValueFactory(new PropertyValueFactory<>("tenSanBay"));
            heso_col.setCellValueFactory(new PropertyValueFactory<>("heSo"));
            stthgve_col.setCellValueFactory(new PropertyValueFactory<>("soThuTu"));


            hangve_tbv.setItems(hangVeList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showSanBayList() {
        ObservableList<SanBay> sanBayList = FXCollections.observableArrayList();

        String query = "SELECT * FROM SANBAY";

        try {
            connect = DatabaseDriver.getConnection();
            statement = connect.createStatement();
            result = statement.executeQuery(query);

            int rowNum = 1;

            while (result.next()) {
                String maSanBay = result.getString("MaSanBay");
                String tenSanBay = result.getString("TenSanBay");
                String tenVietTat = result.getString("TenVietTat");
                String diaChi = result.getString("DiaChi");
                int trangThai = result.getInt("TrangThai");
                int soThuTu = rowNum++;

                SanBay sanBay = new SanBay(maSanBay, tenSanBay, tenVietTat, diaChi, trangThai, soThuTu);
                sanBayList.add(sanBay);
            }

            idsanbay_col.setCellValueFactory(new PropertyValueFactory<>("maSanBay"));
            tensanbay_col.setCellValueFactory(new PropertyValueFactory<>("tenSanBay"));
            tinhthanh_col.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
            sttsanbay_col.setCellValueFactory(new PropertyValueFactory<>("soThuTu"));
            vietTat_col.setCellValueFactory(new PropertyValueFactory<>("tenVietTat"));

            status_col.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
            status_col.setCellFactory(column -> new TableCell<SanBay, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        if (item == 0) {
                            setText("Ngưng hoạt động");
                            setTextFill(javafx.scene.paint.Color.RED); // Set text color to red
                        } else {
                            setText("Hoạt động");
                            setTextFill(javafx.scene.paint.Color.GREEN); // Set text color to green
                        }
                    }
                }
            });

            sanbay_tbv.setItems(sanBayList);
            filteredData = new FilteredList<>(sanBayList, p -> true);
            sanbay_tbv.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);  // Allow multiple selection

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addAirports(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/ThemSanBay.fxml"));
            Parent root = loader.load();
            ThemSanBayController themSanBayController = loader.getController();
            themSanBayController.setParentController(this);  // Pass the instance of QuyDinhController
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Thêm Sân Bay");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @FXML
    void deactivateSelectedAirports(ActionEvent event) {
        ObservableList<SanBay> selectedAirports = sanbay_tbv.getSelectionModel().getSelectedItems();

        if (selectedAirports.isEmpty()) {
            alert.errorMessage("Không chọn sân bay nào. Vui lòng chọn ít nhất một sân bay.");
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Xác nhận cập nhật trạng thái");
        confirmationAlert.setHeaderText("Bạn có muốn thay đổi trạng thái của các sân bay đã chọn?");
        confirmationAlert.setContentText("Hành động này sẽ thay đổi trạng thái của sân bay từ 'Ngưng hoạt động' sang 'Đang hoạt động' và ngược lại.");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                connect = DatabaseDriver.getConnection();
                prepare = connect.prepareStatement("UPDATE SANBAY SET TrangThai = ? WHERE MaSanBay = ?");

                for (SanBay sanBay : selectedAirports) {
                    int newStatus = sanBay.getTrangThai() == 0 ? 1 : 0;
                    prepare.setInt(1, newStatus);
                    prepare.setString(2, sanBay.getMaSanBay());
                    prepare.addBatch();
                }

                int[] results = prepare.executeBatch();
                if (results.length > 0) {
                    alert.successMessage("Trạng thái sân bay đã được cập nhật thành công.");
                } else {
                    alert.errorMessage("Không cập nhật được trạng thái sân bay đã chọn.");
                }

                showSanBayList();  // Refresh the table

            } catch (SQLException e) {
                e.printStackTrace();
                alert.errorMessage("Lỗi khi đang cập nhật trạng thái sân bay. Vui lòng kiểm tra lại.");
            }
        }
    }

    @FXML
    void editSelectedAirports(ActionEvent event) {
        SanBay selectedAirport = sanbay_tbv.getSelectionModel().getSelectedItem();
        if (selectedAirport == null) {
            alert.errorMessage("Không chọn sân bay nào. Vui lòng chọn một sân bay để sửa.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/SuaSanBay.fxml"));
            Parent root = loader.load();
            SuaSanBayController suaSanBayController = loader.getController();
            suaSanBayController.setSanBay(selectedAirport);  // Pass the selected airport to SuaSanBayController
            suaSanBayController.setParentController(this);  // Pass the instance of QuyDinhController
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Sửa Sân Bay");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void refreshAirportsData() {
        showSanBayList();
    }

    private void searchSanBay(ActionEvent e) {
        String keyword = searchsanbay_tfx.getText();
        if (keyword == null || keyword.isEmpty()) {
            sanbay_tbv.setItems(filteredData);
            return;
        }

        String lowerCaseFilter = keyword.toLowerCase();
        filteredData.setPredicate(sanBay -> {
            if (sanBay.getMaSanBay().toLowerCase().contains(lowerCaseFilter) ||
                    sanBay.getTenSanBay().toLowerCase().contains(lowerCaseFilter) ||
                    sanBay.getDiaChi().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            return false;
        });

        SortedList<SanBay> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(sanbay_tbv.comparatorProperty());
        sanbay_tbv.setItems(sortedData);
    }
}
