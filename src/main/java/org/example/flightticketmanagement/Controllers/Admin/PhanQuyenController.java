package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.*;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.Admin.SuaPhanQuyenController;
import org.example.flightticketmanagement.Controllers.Admin.ThemPhanQuyenController;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.TaiKhoan;

import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class PhanQuyenController implements Initializable {
    @FXML
    private MFXButton them_btn;

    @FXML
    private MFXButton timkiem_btn;

    @FXML
    private MFXButton xoa_btn;

    @FXML
    private MFXButton sua_btn;

    @FXML
    private MFXTextField timKiem_txtfld;

    @FXML
    private MFXButton refresh_btn;

    @FXML
    private TableView<TaiKhoan> phanQuyen_table;

    @FXML
    private TableColumn<?, ?> email_tablecolumn;

    @FXML
    private TableColumn<?, ?> id_tablecolumn;

    @FXML
    private TableColumn<?, ?> loaitaikhoan_tablecolumn;

    @FXML
    private TableColumn<?, ?> matkhau_tablecolumn;

    @FXML
    private TableColumn<?, ?> ten_tablecolumn;

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();
    private FilteredList<TaiKhoan> filteredData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ketnoiPhanQuyen();
        refresh_btn.setOnAction(this::refreshData);
        xoa_btn.setOnAction(this::deleteSelectedAccounts);
        sua_btn.setOnAction(this::openEditForm);
        timkiem_btn.setOnAction(this::searchPhanQuyen);
        them_btn.setOnAction(this::newPage);
    }

    private void searchPhanQuyen(ActionEvent event) {
        String filterText = timKiem_txtfld.getText();
        if (filterText == null || filterText.isEmpty()) {
            phanQuyen_table.setItems(filteredData);
            return;
        }

        String lowerCaseFilter = filterText.toLowerCase();
        filteredData.setPredicate(taiKhoan -> {
            if (taiKhoan.getTen().toLowerCase().contains(lowerCaseFilter) ||
                    taiKhoan.getEmail().toLowerCase().contains(lowerCaseFilter) ||
                    taiKhoan.getMaTaiKhoan().toLowerCase().contains(lowerCaseFilter) ||
                    taiKhoan.getPassword().toLowerCase().contains(lowerCaseFilter) ||
                    taiKhoan.getMaQuyen().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            return false;
        });

        SortedList<TaiKhoan> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(phanQuyen_table.comparatorProperty());
        phanQuyen_table.setItems(sortedData);
    }

    @FXML
    void newPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/ThemPhanQuyen.fxml"));
            Parent root = loader.load();
            ThemPhanQuyenController controller = loader.getController();
            controller.setParentController(this);  // Pass the instance of PhanQuyenController
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
            stage.setTitle("Thêm Phân Quyền");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void refreshData(ActionEvent event) {
        ketnoiPhanQuyen();
    }

    @FXML
    void deleteSelectedAccounts(ActionEvent event) {
        ObservableList<TaiKhoan> selectedAccounts = phanQuyen_table.getSelectionModel().getSelectedItems();

        if (selectedAccounts.isEmpty()) {
            alert.errorMessage("Không chọn tài khoản nào. Vui lòng chọn một tài khoản để xóa.");
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Xác nhận xóa");
        confirmationAlert.setHeaderText("Bạn có muốn xóa những tài khoản đã chọn?");
        confirmationAlert.setContentText("Hành động này không thể quay lại.");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                connect = DatabaseDriver.getConnection();
                prepare = connect.prepareStatement("DELETE FROM TaiKhoan WHERE maTaiKhoan = ?");

                for (TaiKhoan taiKhoan : selectedAccounts) {
                    prepare.setString(1, taiKhoan.getMaTaiKhoan());
                    prepare.addBatch();
                }

                int[] results = prepare.executeBatch();
                if (results.length > 0) {
                    alert.successMessage("Tài khoản đã được xóa thành công.");
                } else {
                    alert.errorMessage("Không xóa được tài khoản đã chọn.");
                }

                ketnoiPhanQuyen();  // Refresh the table

            } catch (SQLException e) {
                e.printStackTrace();
                alert.errorMessage("Lỗi khi đang xóa tài khoản. Vui lòng kiểm tra lại.");
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

    public void ketnoiPhanQuyen() {
        ObservableList<TaiKhoan> taiKhoanList = FXCollections.observableArrayList();

        String sql = "SELECT TaiKhoan.maTaiKhoan, TaiKhoan.ten, TaiKhoan.sdt, TaiKhoan.email, TaiKhoan.password, TaiKhoan.created, Quyen.tenQuyen " +
                "FROM TaiKhoan " +
                "JOIN Quyen ON TaiKhoan.maQuyen = Quyen.maQuyen";

        try {
            connect = DatabaseDriver.getConnection();
            statement = connect.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                String maTaiKhoan = result.getString("maTaiKhoan");
                String ten = result.getString("ten");
                String sdt = result.getString("sdt");
                String email = result.getString("email");
                String password = result.getString("password");
                LocalDateTime created = result.getTimestamp("created") != null ? result.getTimestamp("created").toLocalDateTime() : null;
                String loaiTaiKhoan = result.getString("tenQuyen");

                TaiKhoan taiKhoan = new TaiKhoan(maTaiKhoan, ten, sdt, email, password, created, loaiTaiKhoan);
                taiKhoanList.add(taiKhoan);
            }

            // Thiết lập giá trị của các cột trong bảng
            id_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("maTaiKhoan"));
            ten_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("ten"));
            email_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            matkhau_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("password"));
            loaitaikhoan_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("maQuyen"));

            phanQuyen_table.setItems(taiKhoanList);
            filteredData = new FilteredList<>(taiKhoanList, p -> true);
            phanQuyen_table.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);  // Allow multiple selection

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

    public void refreshTable() {
        ketnoiPhanQuyen();
    }

    @FXML
    void openEditForm(ActionEvent event) {
        TaiKhoan selectedAccount = phanQuyen_table.getSelectionModel().getSelectedItem();
        if (selectedAccount == null) {
            alert.errorMessage("Không chọn tài khoản nào. Vui lòng chọn một tài khoản để sửa.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/SuaPhanQuyen.fxml"));
            Parent root = loader.load();
            SuaPhanQuyenController controller = loader.getController();
            controller.setSelectedTaiKhoan(selectedAccount);
            controller.setParentController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
            stage.setTitle("Sửa Phân Quyền");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}