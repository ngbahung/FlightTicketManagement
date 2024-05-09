package org.example.flightticketmanagement.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.example.flightticketmanagement.Models.Model;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    public void addListener(){
        phanQuyen_btn.setOnAction(event -> moPhanQuyen());
        lichChuyenBay_btn.setOnAction(event -> moTrangChuLich());
        banVe_btn.setOnAction(event -> moBanVe());
        datCho_btn.setOnAction(event -> moDatCho());
    }

    private void moPhanQuyen(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("PhanQuyen");
    }

    private void moTrangChuLich() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("TrangChuLich");
    }

    private void moBanVe(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("BanVe");
    }

    private void  moDatCho(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("DatCho");
    }
}
