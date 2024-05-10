package org.example.flightticketmanagement.Controllers.Staff;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import org.example.flightticketmanagement.Models.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffController implements Initializable {
    public BorderPane staff_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getStaffSelectedMenuItem().addListener((observableValue, oldVal, newVal)-> {
            switch (newVal){
//                case
                default -> staff_parent.setCenter(Model.getInstance().getViewFactory().getLichCBStaView());
            }
        });
    }

}
