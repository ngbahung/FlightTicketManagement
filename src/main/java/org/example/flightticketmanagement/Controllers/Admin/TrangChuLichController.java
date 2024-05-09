package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TrangChuLichController implements Initializable {
    public MFXButton themNhieu_btn;
    public MFXButton xoaSua_btn;
    public MFXButton taiFileExel_btn;
    public MFXButton luu_btn;
    public Label maChuyenBay_lbl;
    public Label sanBayDi_lbl;
    public Label NgayGio_lbl;
    public Label soLuongHangGhe1_lbl;
    public Label giaVe_lbl;
    public Label sanBayDen_lbl;
    public Label thoiGianBay_lbl;
    public Label soLuongHangGhe2_lbl;
    public TextField maCB_txtfld;
    public TextField sanBayDi_txtfld;
    public TextField ngayGio_txtfld;
    public TextField soLuongHangGhe1_txtfld;
    public TextField giaVe_txtfld;
    public TextField sanBayDen_txtfld;
    public TextField thoiGianBay_fld;
    public TextField soLuongHangGhe2_txtfld;
    public CheckBox sanBayTrungGian_chkbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
