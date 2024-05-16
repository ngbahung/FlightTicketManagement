package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.TaiKhoan;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class PhanQuyenController implements Initializable {
    @FXML
    private MFXButton them_btn;
    @FXML
    private MFXButton xoa_btn;
    @FXML
    private MFXButton sua_btn;
    @FXML
    private MFXTextField tuKhoa_txtfld;

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

    private AlertMessage alert = new AlertMessage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ketnoiPhanQuyen();
        refresh_btn.setOnAction(this::refreshData);
        xoa_btn.setOnAction(this::deleteSelectedAccounts);
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
            alert.errorMessage("No accounts selected. Please select at least one account to delete.");
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Deletion");
        confirmationAlert.setHeaderText("Are you sure you want to delete the selected accounts?");
        confirmationAlert.setContentText("This action cannot be undone.");

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
                    alert.successMessage("Selected accounts have been successfully deleted.");
                } else {
                    alert.errorMessage("Failed to delete the selected accounts.");
                }

                ketnoiPhanQuyen();  // Refresh the table

            } catch (SQLException e) {
                e.printStackTrace();
                alert.errorMessage("Error occurred while deleting accounts. Please check the logs for more details.");
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

    // Connect to the database TaiKhoan and Quyen to display in the table
    public void ketnoiPhanQuyen() {
        ObservableList<TaiKhoan> taiKhoanList = FXCollections.observableArrayList();

        String sql = "SELECT TaiKhoan.maTaiKhoan, TaiKhoan.ten, TaiKhoan.email, TaiKhoan.password, Quyen.tenQuyen " +
                "FROM TaiKhoan " +
                "JOIN Quyen ON TaiKhoan.maQuyen = Quyen.maQuyen";

        try {
            connect = DatabaseDriver.getConnection();
            statement = connect.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                String maTaiKhoan = result.getString("maTaiKhoan");
                String ten = result.getString("ten");
                String email = result.getString("email");
                String password = result.getString("password");
                String loaiTaiKhoan = result.getString("tenQuyen");

                TaiKhoan taiKhoan = new TaiKhoan(maTaiKhoan, ten, "", email, password, null, loaiTaiKhoan);
                taiKhoanList.add(taiKhoan);
            }

            // Thiết lập giá trị của các cột trong bảng
            id_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("maTaiKhoan"));
            ten_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("ten"));
            email_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            matkhau_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("password"));
            loaitaikhoan_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("maQuyen"));

            phanQuyen_table.setItems(taiKhoanList);
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


}
