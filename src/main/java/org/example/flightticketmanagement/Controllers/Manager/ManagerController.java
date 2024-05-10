package org.example.flightticketmanagement.Controllers.Manager;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import org.example.flightticketmanagement.Models.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {
    public BorderPane manager_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getManagerSelectedMenuItem().addListener((observableValue, oldVal, newVal)-> {
//          Add switch statement
            switch (newVal){
                case BANVE -> manager_parent.setCenter(Model.getInstance().getViewFactory().getBanVeManaView());
                case DATCHO -> manager_parent.setCenter(Model.getInstance().getViewFactory().getDatChoManaView());
                case DOANHTHU -> manager_parent.setCenter(Model.getInstance().getViewFactory().getDoanhThuManaView());
                default -> manager_parent.setCenter(Model.getInstance().getViewFactory().getLichCBManaView());
            }
        });
    }
}
