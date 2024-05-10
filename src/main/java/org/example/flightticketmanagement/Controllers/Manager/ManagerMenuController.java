package org.example.flightticketmanagement.Controllers.Manager;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.example.flightticketmanagement.Models.Model;
import org.example.flightticketmanagement.Views.ManagerMenuOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerMenuController implements Initializable {
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
        lichChuyenBay_btn.setOnAction(event -> moLichChuyenBay());
    }

    public void moLichChuyenBay(){
        Model.getInstance().getViewFactory().getManagerSelectedMenuItem().set(ManagerMenuOptions.LICHCHUYENBAY);
    }
}
