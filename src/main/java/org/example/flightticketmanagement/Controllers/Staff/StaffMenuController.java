package org.example.flightticketmanagement.Controllers.Staff;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.example.flightticketmanagement.Models.Model;
import org.example.flightticketmanagement.Views.StaffMenuOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffMenuController implements Initializable {
    public Button lichChuyenBay_btn;
    public Button banVe_btn;
    public Button datCho_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    public void addListener(){
        lichChuyenBay_btn.setOnAction(event -> moLichChuyenBay());
        banVe_btn.setOnAction(event -> moBanVe());
        datCho_btn.setOnAction(event -> moDatCho());
    }

    public void moLichChuyenBay(){
        Model.getInstance().getViewFactory().getStaffSelectedMenuItem().set(StaffMenuOptions.LICHCHUYENBAY);
    }

    public void moBanVe(){
        Model.getInstance().getViewFactory().getStaffSelectedMenuItem().set(StaffMenuOptions.BANVE);
    }

    public void moDatCho(){
        Model.getInstance().getViewFactory().getStaffSelectedMenuItem().set(StaffMenuOptions.DATCHO);
    }
}
