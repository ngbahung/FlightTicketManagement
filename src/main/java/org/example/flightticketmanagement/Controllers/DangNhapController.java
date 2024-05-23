package org.example.flightticketmanagement.Controllers;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.Model;
import org.example.flightticketmanagement.Views.AccountType;
import org.example.flightticketmanagement.Views.ViewFactory;

import java.net.URL;
import java.sql.*;
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

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        account_selector.setItems(FXCollections.observableArrayList(AccountType.ADMIN, AccountType.MANAGER, AccountType.STAFF));
        account_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        account_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(account_selector.getValue()));
        dangNhap_btn.setOnAction(actionEvent -> moDangNhap());
    }

    private void moDangNhap() {
        String tenTK = tenTK_mfxfld.getText().trim();
        String matKhau = matKhau_mfxpassfld.getText().trim();
        AccountType selectedAccountType = account_selector.getValue();
        tenTK_mfxfld.setText("hungtd@gmail.com");
        matKhau_mfxpassfld.setText("hungtd");
        account_selector.setValue(AccountType.MANAGER);

        if (tenTK.isEmpty() || matKhau.isEmpty() || selectedAccountType == null) {
            loi_lbl.setText("Please fill in all fields.");
            return;
        }

        String sql = "SELECT * FROM TaiKhoan WHERE Email = ? AND Password = ?";

        try {
            connect = DatabaseDriver.getConnection();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, tenTK);
            prepare.setString(2, matKhau);
            result = prepare.executeQuery();

            if (result.next()) {
                String maTaiKhoan = result.getString("MaTaiKhoan");
                String maQuyen = result.getString("MaQuyen");
                AccountType accountType = getAccountType(maQuyen);

                if (accountType == selectedAccountType) {
                    Model.getInstance().getViewFactory().setLoginAccountType(accountType);
                    Model.getInstance().setLoggedInUserId(maTaiKhoan);  // Store the logged-in user ID
                    openMainScreen(accountType);
                } else {
                    loi_lbl.setText("Account type does not match.");
                }
            } else {
                loi_lbl.setText("Invalid email or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            loi_lbl.setText("Database connection error.");
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private AccountType getAccountType(String maQuyen) {
        switch (maQuyen) {
            case "RL0001":
                return AccountType.ADMIN;
            case "RL0002":
                return AccountType.MANAGER;
            case "RL0003":
                return AccountType.STAFF;
            default:
                return null;
        }
    }

    private void openMainScreen(AccountType accountType) {
        ViewFactory viewFactory = Model.getInstance().getViewFactory();
        Stage stage = (Stage) dangNhap_btn.getScene().getWindow();
        viewFactory.dongStage(stage);

        switch (accountType) {
            case ADMIN:
                viewFactory.hienThiManHinhAdmin();
                break;
            case MANAGER:
                viewFactory.hienThiManHinhManager();
                break;
            case STAFF:
                viewFactory.hienThiManHinhStaff();
                break;
        }
    }
}
