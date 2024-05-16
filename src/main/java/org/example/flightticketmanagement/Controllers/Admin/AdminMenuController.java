package org.example.flightticketmanagement.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.Model;
import org.example.flightticketmanagement.Views.AdminMenuOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button phanQuyen_btn;
    public Button lichChuyenBay_btn;
    public Button banVe_btn;
    public Button datCho_btn;
    public Button doanhThu_btn;
    public Button suaQuyDinh_btn;
    public Button dangXuat_btn;

    private AlertMessage alert = new AlertMessage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    public void addListener(){
        phanQuyen_btn.setOnAction(event -> moPhanQuyen());
        lichChuyenBay_btn.setOnAction(event -> moTrangChuLich());
        banVe_btn.setOnAction(event -> moBanVe());
        datCho_btn.setOnAction(event -> moDatCho());
        doanhThu_btn.setOnAction(event -> moDoanhThu());
        suaQuyDinh_btn.setOnAction(event -> moSuaQuyDinh());
        dangXuat_btn.setOnAction(event -> moDangNhap());
    }

    private void moPhanQuyen(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.PHANQUYEN);
    }

    private void moTrangChuLich() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.LICHCHUYENBAY);
    }

    private void moBanVe(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.BANVE);
    }

    private void  moDatCho(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.DATCHO);
    }

    private void moDoanhThu(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.DOANHTHU);
    }

    private void moSuaQuyDinh(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.SUAQUYDINH);
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