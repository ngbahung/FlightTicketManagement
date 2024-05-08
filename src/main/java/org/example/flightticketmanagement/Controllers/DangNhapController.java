package org.example.flightticketmanagement.Controllers;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DangNhapController implements Initializable {
    public Label tenDangNhap_lbl;
    public Label tenTaiKhoan_lbl;
    public Label matKhau_lbl;
    public Button dangNhap_btn;
    public Label loi_lbl;

    public MFXTextField tenTK_mfxfld;
    public MFXPasswordField matKhau_mfxpassfld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dangNhap_btn.setOnAction(actionEvent -> moDangNhap());
    }

    private void moDangNhap(){
        Stage stage = (Stage) loi_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().dongStage(stage);
        Model.getInstance().getViewFactory().hienThiManHinhAdmin();
    }
}
