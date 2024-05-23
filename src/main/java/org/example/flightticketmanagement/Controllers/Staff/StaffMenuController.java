package org.example.flightticketmanagement.Controllers.Staff;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.Model;
import org.example.flightticketmanagement.Views.StaffMenuOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffMenuController implements Initializable {
    public Button lichChuyenBay_btn;
    public Button banVe_btn;
    public Button lichSu_btn;
    public Button taiKhoan_btn;
    public Button dangXuat_btn;

    private final AlertMessage alert = new AlertMessage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    public void addListener(){
        lichChuyenBay_btn.setOnAction(event -> moLichChuyenBay());
        banVe_btn.setOnAction(event -> moBanVe());
        lichSu_btn.setOnAction(event -> moLichSu());
        taiKhoan_btn.setOnAction(event -> moTaiKhoan());
        dangXuat_btn.setOnAction(event -> moDangNhap());
    }

    public void moLichChuyenBay(){
        Model.getInstance().getViewFactory().getStaffSelectedMenuItem().set(StaffMenuOptions.LICHCHUYENBAY);
    }

    public void moBanVe(){
        Model.getInstance().getViewFactory().getStaffSelectedMenuItem().set(StaffMenuOptions.BANVE);
    }

    public void moLichSu(){
        Model.getInstance().getViewFactory().getStaffSelectedMenuItem().set(StaffMenuOptions.LICHSU);
    }

    public void moTaiKhoan(){
        Model.getInstance().getViewFactory().getStaffSelectedMenuItem().set(StaffMenuOptions.TAIKHOAN);
    }

    private void moDangNhap() {
        boolean confirmed = alert.confirmationMessage("Bạn có chắc chắn muốn đăng xuất không?");

        if (confirmed) {
            Stage stage = (Stage) dangXuat_btn.getScene().getWindow();
            Model.getInstance().getViewFactory().dongStage(stage);
            Model.getInstance().getViewFactory().hienThiManHinhDangNhap();
        }
    }
}
