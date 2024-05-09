package org.example.flightticketmanagement.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.example.flightticketmanagement.Models.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button phanQuyen_btn;
    public Button trangChu_btn;
    public Button traCuu_btn;
    public Button doanhThu_btn;
    public Button suaQuyDinh_btn;
    public Button dangXuat_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    public void addListener(){
        phanQuyen_btn.setOnAction(event -> moPhanQuyen());
        trangChu_btn.setOnAction(event -> moTrangChuLich());
    }

    private void moPhanQuyen(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("PhanQuyen");
    }

    private void moTrangChuLich() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("TrangChuLich");
    }
}
