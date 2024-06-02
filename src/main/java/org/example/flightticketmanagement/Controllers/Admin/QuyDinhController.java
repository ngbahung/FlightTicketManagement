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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.DuongBay;
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
    private TextField searchhangve_tfx;

    @FXML
    private TextField searchsanbay_tfx;

    @FXML
    private TableColumn<HangVe, Integer> statusHangVe_col;

    @FXML
    private TableColumn<SanBay, Integer> status_col;

    @FXML
    private TableColumn<?, ?> sttsanbay_col;

    @FXML
    private TableColumn<?, ?> tenhangve_col;

    @FXML
    private TableColumn<?, ?> tensanbay_col;

    @FXML
    private TableColumn<?, ?> tinhthanh_col;

    @FXML
    private TableColumn<?, ?> vietTat_col;

    @FXML
    private TableColumn<?, ?> sttDuongBay_col;

    @FXML
    private TextField searchDuongBay_textfield;

    @FXML
    private TableColumn<DuongBay, Integer> statusDuongBay_col;

    @FXML
    private TableColumn<?, ?> sanBayDen_col;

    @FXML
    private TableColumn<?, ?> sanBayDi_col;

    @FXML
    private Button refreshDuongBayData_btn;

    @FXML
    private TableColumn<?, ?> idDuongBay_col;

    @FXML
    private TableColumn<?, ?> tenDuongBay_col;

    @FXML
    private TableView<DuongBay> duongBay_tbv;

    @FXML
    private Button changeDuongBayStatus_btn;


    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    private FilteredList<SanBay> filteredSanBayData;
    private FilteredList<HangVe> filteredHangVeData;
    private FilteredList<DuongBay> filteredDuongBayData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showSanBayList();
        showHangVeList();
        loadThamSoData();
        loadDuongBayData();
        refreshSanBayData_btn.setOnAction(e -> showSanBayList());
        refreshHangVeData_btn.setOnAction(e -> showHangVeList());
        refreshDuongBayData_btn.setOnAction(e -> loadDuongBayData());
    }

    private void showHangVeList() {
        ObservableList<HangVe> hangVeList = FXCollections.observableArrayList();

        String query = "SELECT * FROM HANGVE";

        try {
            connect = DatabaseDriver.getConnection();
            statement = connect.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                String maHangVe = result.getString("MaHangVe");
                String tenHangVe = result.getString("TenHangVe");
                float heSo = result.getFloat("HeSo");
                int trangThai = result.getInt("TrangThai");

                HangVe hangVe = new HangVe(maHangVe, tenHangVe, heSo, trangThai);
                hangVeList.add(hangVe);
            }

            tenhangve_col.setCellValueFactory(new PropertyValueFactory<>("tenHangVe"));
            heso_col.setCellValueFactory(new PropertyValueFactory<>("heSo"));
            statusHangVe_col.setCellValueFactory(new PropertyValueFactory<>("trangThai"));

            statusHangVe_col.setCellFactory(column -> new TableCell<>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        if (item == 0) {
                            setText("Ngưng hoạt động");
                            setTextFill(javafx.scene.paint.Color.RED);
                        } else {
                            setText("Hoạt động");
                            setTextFill(javafx.scene.paint.Color.GREEN);
                        }
                    }
                }
            });

            hangve_tbv.setItems(hangVeList);
            filteredHangVeData = new FilteredList<>(hangVeList, p -> true);
            hangve_tbv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            status_col.setCellFactory(column -> new TableCell<>() {
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
            filteredSanBayData = new FilteredList<>(sanBayList, p -> true);
            sanbay_tbv.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);  // Allow multiple selection

        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    @FXML
    void addAirports() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/ThemSanBay.fxml"));
            Parent root = loader.load();
            ThemSanBayController themSanBayController = loader.getController();
            themSanBayController.setParentController(this);  // Pass the instance of QuyDinhController
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
            stage.setTitle("Thêm Sân Bay");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @FXML
    void addHangVe() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/ThemHangVe.fxml"));
            Parent root = loader.load();
            ThemHangVeController themHangVeController = loader.getController();
            themHangVeController.setParentController(this);  // Pass the instance of QuyDinhController
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
            stage.setTitle("Thêm Hạng Vé");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deactivateSelectedAirports() {
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
            } finally {
                try {
                    if (prepare != null) prepare.close();
                    if (connect != null) connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void editSelectedAirports() {
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
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
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

    @FXML
    void refreshHangVeData() {
        showHangVeList();
    }

    @FXML
    void refreshDuongBayData() {
        loadDuongBayData();
    }

    @FXML
    void searchSanBay() {
        String keyword = searchsanbay_tfx.getText();
        if (keyword == null || keyword.isEmpty()) {
            sanbay_tbv.setItems(filteredSanBayData);
            return;
        }

        String lowerCaseFilter = keyword.toLowerCase();
        filteredSanBayData.setPredicate(sanBay -> sanBay.getMaSanBay().toLowerCase().contains(lowerCaseFilter) ||
                sanBay.getTenSanBay().toLowerCase().contains(lowerCaseFilter) ||
                sanBay.getDiaChi().toLowerCase().contains(lowerCaseFilter));

        SortedList<SanBay> sortedData = new SortedList<>(filteredSanBayData);
        sortedData.comparatorProperty().bind(sanbay_tbv.comparatorProperty());
        sanbay_tbv.setItems(sortedData);
    }

    @FXML
    void searchHangVe() {
        String keyword = searchhangve_tfx.getText();
        if (keyword == null || keyword.isEmpty()) {
            hangve_tbv.setItems(filteredHangVeData);
            return;
        }

        String lowerCaseFilter = keyword.toLowerCase();
        filteredHangVeData.setPredicate(hangVe -> hangVe.getMaHangVe().toLowerCase().contains(lowerCaseFilter) ||
                hangVe.getTenHangVe().toLowerCase().contains(lowerCaseFilter));

        SortedList<HangVe> sortedData = new SortedList<>(filteredHangVeData);
        sortedData.comparatorProperty().bind(hangve_tbv.comparatorProperty());
        hangve_tbv.setItems(sortedData);
    }

    @FXML
    void searchDuongBay() {
        String keyword = searchDuongBay_textfield.getText();
        if (keyword == null || keyword.isEmpty()) {
            duongBay_tbv.setItems(filteredDuongBayData);
            return;
        }

        String lowerCaseFilter = keyword.toLowerCase();
        filteredDuongBayData.setPredicate(duongBay -> duongBay.getMaDuongBay().toLowerCase().contains(lowerCaseFilter) ||
                duongBay.getTenDuongBay().toLowerCase().contains(lowerCaseFilter) ||
                duongBay.getTenSanBayDi().toLowerCase().contains(lowerCaseFilter) ||
                duongBay.getTenSanBayDen().toLowerCase().contains(lowerCaseFilter));

        SortedList<DuongBay> sortedData = new SortedList<>(filteredDuongBayData);
        sortedData.comparatorProperty().bind(duongBay_tbv.comparatorProperty());
        duongBay_tbv.setItems(sortedData);
    }

    @FXML
    void deactiveSelectedTicketClass(ActionEvent event) {
        ObservableList<HangVe> selectedTicketClasses = hangve_tbv.getSelectionModel().getSelectedItems();

        if (selectedTicketClasses.isEmpty()) {
            alert.errorMessage("Không chọn hạng vé nào. Vui lòng chọn ít nhất một hạng vé.");
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Xác nhận xóa hạng vé");
        confirmationAlert.setHeaderText("Bạn có muốn xóa hạng vé đã chọn?");
        confirmationAlert.setContentText("Hành động này sẽ thay đổi danh sách hạng vé của bạn");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        String query = "DELETE FROM HangVe WHERE MaHangVe = ?";
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                connect = DatabaseDriver.getConnection();
                prepare = connect.prepareStatement(query);

                for ( HangVe hangVe : selectedTicketClasses) {
                    prepare.setString(1, hangVe.getMaHangVe());
                    prepare.addBatch();
                }

                int[] results = prepare.executeBatch();
                if (results.length > 0) {
                    alert.successMessage("Hạng vé đã được xóa thành công.");
                } else {
                    alert.errorMessage("Không xóa được hạng vé đã chọn.");
                }

                showHangVeList();  // Refresh the table

            } catch (SQLException e) {
                e.printStackTrace();
                alert.errorMessage("Lỗi khi đang xóa hạng vé. Vui lòng kiểm tra lại.");
            } finally {
                try {
                    if (prepare != null) prepare.close();
                    if (connect != null) connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void editSelectedTicketClass() {
        HangVe selectedTicketClass = hangve_tbv.getSelectionModel().getSelectedItem();
        if (selectedTicketClass == null) {
            alert.errorMessage("Không chọn hạng vé nào. Vui lòng chọn một hạng vé để sửa.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/SuaHangVe.fxml"));
            Parent root = loader.load();
            SuaHangVeController suaHangVeController = loader.getController();
            suaHangVeController.setHangVe(selectedTicketClass);  // Pass the selected ticket class to SuaHangVeController
            suaHangVeController.setParentController(this);  // Pass the instance of QuyDinhController
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
            stage.setTitle("Sửa Hạng Vé");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadThamSoData() {
        String query = "SELECT * FROM THAMSO";
        try {
            connect = DatabaseDriver.getConnection();
            statement = connect.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                String maThuocTinh = result.getString("MaThuocTinh");
                int giaTri = result.getInt("GiaTri");

                switch (maThuocTinh) {
                    case "TGBTT":
                        mintgbay_txf.setText(String.valueOf(giaTri));
                        break;
                    case "SSBTGTMD":
                        maxsbtg_txf.setText(String.valueOf(giaTri));
                        break;
                    case "TGDTT":
                        mintgdung_tfx.setText(String.valueOf(giaTri));
                        break;
                    case "TGDTD":
                        maxtgdung_tfx.setText(String.valueOf(giaTri));
                        break;
                    case "TGTTDV":
                        mintgdatve_tfx.setText(String.valueOf(giaTri));
                        break;
                    case "TGTT_HV":
                        mintghuyve_tfx.setText(String.valueOf(giaTri));
                        break;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    @FXML
    void editParameter() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/SuaThamSo.fxml"));
            Parent root = loader.load();
            SuaThamSoQuyDinhController suaThamSoQuyDinhController = loader.getController();
            suaThamSoQuyDinhController.setParentController(this);  // Pass the instance of QuyDinhController
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
            stage.setTitle("Sửa Tham Số Quy Định");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDuongBayData() {
        ObservableList<DuongBay> duongBayList = FXCollections.observableArrayList();

        String query = "SELECT DUONGBAY.MaDuongBay, "
                + "S1.TenSanBay AS SanBayDi, "
                + "S2.TenSanBay AS SanBayDen, "
                + "DUONGBAY.TenDuongBay, DUONGBAY.TrangThai "
                + "FROM DUONGBAY "
                + "JOIN SANBAY S1 ON DUONGBAY.MaSanBayDi = S1.MaSanBay "
                + "JOIN SANBAY S2 ON DUONGBAY.MaSanBayDen = S2.MaSanBay";

        try {
            connect = DatabaseDriver.getConnection();
            statement = connect.createStatement();
            result = statement.executeQuery(query);

            int rowNum = 1;

            while (result.next()) {
                String maDuongBay = result.getString("MaDuongBay");
                String tenSanBayDi = result.getString("SanBayDi");
                String tenSanBayDen = result.getString("SanBayDen");
                String tenDuongBay = result.getString("TenDuongBay");
                int trangThai = result.getInt("TrangThai");

                int soThuTu = rowNum++;

                DuongBay duongBay = new DuongBay(maDuongBay, tenSanBayDi, tenSanBayDen, tenDuongBay, soThuTu, trangThai);
                duongBayList.add(duongBay);
            }

            idDuongBay_col.setCellValueFactory(new PropertyValueFactory<>("maDuongBay"));
            sanBayDi_col.setCellValueFactory(new PropertyValueFactory<>("tenSanBayDi"));
            sanBayDen_col.setCellValueFactory(new PropertyValueFactory<>("tenSanBayDen"));
            sttDuongBay_col.setCellValueFactory(new PropertyValueFactory<>("soThuTu"));
            tenDuongBay_col.setCellValueFactory(new PropertyValueFactory<>("tenDuongBay"));

            statusDuongBay_col.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
            statusDuongBay_col.setCellFactory(column -> new TableCell<>() {
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

            duongBay_tbv.setItems(duongBayList);
            filteredDuongBayData = new FilteredList<>(duongBayList, p -> true);
            duongBay_tbv.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);  // Allow multiple selection

        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    @FXML
    void themDuongBay () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/ThemDuongBay.fxml"));
            Parent root = loader.load();
            ThemDuongBayController themDuongBayController = loader.getController();
            themDuongBayController.setParentController(this);  // Pass the instance of QuyDinhController
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
            stage.setTitle("Thêm Đường Bay");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void suaDuongBay () {
        DuongBay selectedDuongBay = duongBay_tbv.getSelectionModel().getSelectedItem();
        if (selectedDuongBay == null) {
            alert.errorMessage("Không chọn đường bay nào. Vui lòng chọn một đường bay để sửa.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Admin/SuaDuongBay.fxml"));
            Parent root = loader.load();
            SuaDuongBayController suaDuongBayController = loader.getController();
            suaDuongBayController.setDuongBay(selectedDuongBay);  // Pass the selected ticket class to SuaHangVeController
            suaDuongBayController.setParentController(this);  // Pass the instance of QuyDinhController
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
            stage.setTitle("Sửa Đường Bay");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changeDuongBayStatus (){
        ObservableList<DuongBay> selectedDuongBay = duongBay_tbv.getSelectionModel().getSelectedItems();

        if (selectedDuongBay.isEmpty()) {
            alert.errorMessage("Không chọn đường bay nào. Vui lòng chọn ít nhất một đường bay.");
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Xác nhận cập nhật trạng thái");
        confirmationAlert.setHeaderText("Bạn có muốn thay đổi trạng thái của các đường bay đã chọn?");
        confirmationAlert.setContentText("Hành động này sẽ thay đổi trạng thái của đường bay từ 'Ngưng hoạt động' sang 'Đang hoạt động' và ngược lại.");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                connect = DatabaseDriver.getConnection();
                prepare = connect.prepareStatement("UPDATE DUONGBAY SET TrangThai = ? WHERE MaDuongBay = ?");

                for (DuongBay duongBay : selectedDuongBay) {
                    int newStatus = duongBay.getTrangThai() == 0 ? 1 : 0;
                    prepare.setInt(1, newStatus);
                    prepare.setString(2, duongBay.getMaDuongBay());
                    prepare.addBatch();
                }

                int[] results = prepare.executeBatch();
                if (results.length > 0) {
                    alert.successMessage("Trạng thái đường bay đã được cập nhật thành công.");
                } else {
                    alert.errorMessage("Không cập nhật được trạng thái đường bay đã chọn.");
                }

                loadDuongBayData();  // Refresh the table

            } catch (SQLException ex) {
                ex.printStackTrace();
                alert.errorMessage("Lỗi khi đang cập nhật trạng thái đường bay. Vui lòng kiểm tra lại.");
            } finally {
                try {
                    if (prepare != null) prepare.close();
                    if (connect != null) connect.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
