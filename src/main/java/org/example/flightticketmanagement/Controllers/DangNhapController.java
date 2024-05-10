package org.example.flightticketmanagement.Controllers;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Models.Model;
import org.example.flightticketmanagement.Views.AccountType;

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
    public ChoiceBox<AccountType> account_selector;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        account_selector.setItems(FXCollections.observableArrayList(AccountType.ADMIN, AccountType.MANAGER, AccountType.STAFF));
        account_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        account_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(account_selector.getValue()));
        dangNhap_btn.setOnAction(actionEvent -> moDangNhap());
    }

    private void moDangNhap(){
        Stage stage = (Stage) loi_lbl.getScene().getWindow();
        Model.getInstance().getViewFactory().dongStage(stage);
        if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.ADMIN) {
            Model.getInstance().getViewFactory().hienThiManHinhAdmin();
        } else if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.MANAGER) {
            Model.getInstance().getViewFactory().hienThiManHinhManager();
        }
    }
}
