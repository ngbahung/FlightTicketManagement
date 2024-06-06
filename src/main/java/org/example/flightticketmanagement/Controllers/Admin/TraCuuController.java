package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TraCuuController {

    @FXML
    private Button congEmBe_btn;

    @FXML
    private Button congNguoiLon_btn;

    @FXML
    private Button congTreEm_btn;

    @FXML
    private MFXComboBox<?> hangve_combb;

    @FXML
    private Label hanhkhach_label;

    @FXML
    private MFXDatePicker ngaydi_datepicker;

    @FXML
    private MFXFilterComboBox<?> sanbayden_combobox;

    @FXML
    private MFXFilterComboBox<?> sanbaydi_combobox;

    @FXML
    private Label soEmBe_lbl;

    @FXML
    private AnchorPane soHanhKhach_anchorpane;

    @FXML
    private Label soNguoiLon_lbl;

    @FXML
    private Label soTreEm_lbl;

    @FXML
    private MFXButton timchuyenbay_btn;

    @FXML
    private Button truEmBe_btn;

    @FXML
    private Button truNguoiLon_btn;

    @FXML
    private Button truTreEm_btn;


}
