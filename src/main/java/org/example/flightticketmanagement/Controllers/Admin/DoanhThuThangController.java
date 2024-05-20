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
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    private BigDecimal tongDoanhThuNam = BigDecimal.valueOf(0.0);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();
        DTThang_FillDataForComboBoxNam();
        DTThang_FillDataForComboBoxThang();
        dtThang_thongKe_btn.setOnMouseClicked(event -> DTThang_LoadData());
    }

    public void DTThang_LoadData() {
        if(dtThang_cbbox_namSelection.getSelectionModel().isEmpty()){
            alert.errorMessage("Vui lòng chọn năm cần thống kê");
        }
        if(dtThang_cbbox_thangSelection.getSelectionModel().isEmpty()){
            alert.errorMessage("Vui lòng chọn tháng cần thống kê");
        }
        try
        {
            Integer namBaoCao = dtThang_cbbox_namSelection.getSelectionModel().getSelectedItem();
            Integer thangBaoCao = dtThang_cbbox_thangSelection.getSelectionModel().getSelectedItem();

            String query = "SELECT"+
                            "MaChuyenBay"+",SoVeDaBan"+",DoanhThu"+",TyLe"+
                            "FROM"+ "BAOCAOTHANG"+
                            "WHERE Thang = (?)"+
                            "AND Nam = (?)";
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, thangBaoCao);
            prepare.setInt(2, namBaoCao);
            result = prepare.executeQuery();
            dtThang_tableView.getItems().clear();
        }
    }
    public void DTThang_FillDataForComboBoxNam() {
        int currentYear = LocalDate.now().getYear();
        for(int i = currentYear; i >= 2000; i--){
            dtThang_cbbox_namSelection.getItems().add(i);
        }
    }
    public void DTThang_FillDataForComboBoxThang() {
        for(int i = 1; i <= 12; i++){
            dtThang_cbbox_thangSelection.getItems().add(i);
        }
    }

}
