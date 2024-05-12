package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.PhanQuyen;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhanQuyenController implements Initializable {
    public MFXButton them_btn;
    public MFXButton xoa_btn;
    public MFXButton sua_btn;
    public MFXTextField tuKhoa_txtfld;

    @FXML
    private TableView<PhanQuyen> phanQuyen_table;

    @FXML
    private TableColumn<PhanQuyen, String> ten_tbcolumn;

    @FXML
    private TableColumn<PhanQuyen, String> matKhau_tbcolumn;

    @FXML
    private TableColumn<PhanQuyen, String> nhom_tbcolumn;

    @FXML
    void newPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/ThemPhanQuyen.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Thêm Phân Quyền");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ObservableList<PhanQuyen> phanquyen_data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        DatabaseDriver driver = new DatabaseDriver();
        Connection connectDB = driver.getDBDriver();

        String phanQuyenViewQuery = "SELECT * FROM PHANQUYEN";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(phanQuyenViewQuery);

            while (resultSet.next()) {

                String ten = resultSet.getString("ten");
                String matKhau = resultSet.getString("matKhau");
                String nhom = resultSet.getString("nhom");

                phanquyen_data.add(new PhanQuyen(ten, matKhau, nhom));

                ten_tbcolumn.setCellValueFactory(new PropertyValueFactory<>("ten"));
                matKhau_tbcolumn.setCellValueFactory(new PropertyValueFactory<>("matKhau"));
                nhom_tbcolumn.setCellValueFactory(new PropertyValueFactory<>("nhom"));

                phanQuyen_table.setItems(phanquyen_data);

//               Tìm kiếm
                FilteredList<PhanQuyen> filteredData = new FilteredList<>(phanquyen_data, p -> true);
                tuKhoa_txtfld.textProperty().addListener((observable, oldvalue, newvalue) -> {
                    filteredData.setPredicate(PhanQuyen -> {

//                        Nếu không tìm thấy dữ liệu
                        if (newvalue.isBlank() || newvalue.isEmpty()) {
                            return true;
                        }

                        String tuTimKiem = newvalue.toLowerCase();

                        if (PhanQuyen.getTen().toLowerCase().contains(tuTimKiem)) {
                            return true;
                        } else if (PhanQuyen.getMatKhau().toLowerCase().contains(tuTimKiem)) {
                            return true;
                        } else return PhanQuyen.getNhom().toLowerCase().contains(tuTimKiem);
                    });
                });

                SortedList<PhanQuyen> sortedData = new SortedList<>(filteredData);

                sortedData.comparatorProperty().bind(phanQuyen_table.comparatorProperty());

                phanQuyen_table.setItems(sortedData);
            }
        } catch (SQLException e) {
            Logger.getLogger(PhanQuyenController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
}

