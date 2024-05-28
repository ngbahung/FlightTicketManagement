package org.example.flightticketmanagement.Controllers.Manager;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ThemLichChuyenBayController implements Initializable {
    @FXML
    private TextField gia_txtfld;

    @FXML
    private TextField gioBay_txtfld;

    @FXML
    private TextField gioHaCanh_txtfld;

    @FXML
    private TableView<?> hangVe_tableview;

    @FXML
    private MFXButton luu_btn;

    @FXML
    private TextField maChuyenBay_txtfld;

    @FXML
    private TextField maDuongBay_txtfld;

    @FXML
    private DatePicker ngayBay_datepicker;

    @FXML
    private DatePicker ngayHaCanh_datepicker;

    @FXML
    private TableColumn<?, ?> soLuongGhe_tbcl;

    @FXML
    private ComboBox<?> tenDuongBay_combobox;

    @FXML
    private TableColumn<?, ?> tenHangVe_tbcl;

    @FXML
    private MFXButton themHangVe_btn;

    @FXML
    private TextField thoiGianBay_txtfld;

    @FXML
    private MFXButton xoaHangVe_btn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
