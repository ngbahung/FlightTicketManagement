package org.example.flightticketmanagement.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import org.example.flightticketmanagement.Models.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal){
                case "TrangChuLich" -> admin_parent.setCenter(Model.getInstance().getViewFactory().getTrangChuLichView());
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getBangDieuKhienView());
            }
        });
    }
}
