package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SuaPhanQuyenController implements Initializable {
    @FXML
    private MFXTextField email_txtfld;

    @FXML
    private MFXPasswordField matkhau_txtfld;

    @FXML
    private MFXDatePicker ngaytao_datepicker;

    @FXML
    private MFXComboBox<String> phanQuyen_combobox;

    @FXML
    private MFXTextField sdt_txtfld;

    @FXML
    private MFXTextField ten_txtfld;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        }
    }

