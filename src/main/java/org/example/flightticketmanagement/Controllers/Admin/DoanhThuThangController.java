package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.BaoCaoThang;
import org.example.flightticketmanagement.Models.DatabaseDriver;

public class DoanhThuThangController implements Initializable {
    @FXML
    private ComboBox<Integer> dtThang_cbbox_namSelection;

    @FXML
    private ComboBox<Integer> dtThang_cbbox_thangSelection;

    @FXML
    private TableColumn<BaoCaoThang, String> dtThang_doanhThu_tbcolumn;

    @FXML
    private TableColumn<BaoCaoThang, String> dtThang_macb_tbcolumn;

    @FXML
    private TableColumn<BaoCaoThang, String> dtThang_soVe_tbcolumn;

    @FXML
    private TableColumn<BaoCaoThang, String> dtThang_stt_tbcolumn;

    @FXML
    private TableColumn<BaoCaoThang, String> dtThang_tyLe_tbcolumn;

    @FXML
    private TableView<BaoCaoThang> dtThang_tableView;

    @FXML
    private MFXButton dtThang_thongKe_btn;

    @FXML
    private TextField dtThang_tongdt_txfl;

    //tao connection
    private Connection connection;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    private BigDecimal tongDoanhThuNam = BigDecimal.valueOf(0.0);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DatabaseDriver.getConnection();
    }

}
