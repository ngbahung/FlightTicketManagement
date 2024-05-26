package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.ChuyenBay;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.Ve;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ManHinhDatVeController implements Initializable {
    @FXML
    private MFXTextField maKH_txtfld;

    @FXML
    private MFXTextField diaChi_txtfld;

    @FXML
    private MFXTextField cccd_txtfld;

    @FXML
    private MFXButton xemTruoc_btn;

    @FXML
    private MFXTextField email_txtfld;

    @FXML
    private TableColumn<Ve, String> giaTien_tbcl;

    @FXML
    private MFXTextField gioBay_txtfld;

    @FXML
    private TableColumn<Ve, String> hangVe_tbcl;

    @FXML
    private MFXTextField hoten_txtfld;

    @FXML
    private MFXTextField maCB_txtfld;

    @FXML
    private TableColumn<Ve, Integer> maGhe_tbcl;

    @FXML
    private MFXTextField maGhe_txtfld;

    @FXML
    private MFXTextField maVe_txtfld;

    @FXML
    private TableColumn<Ve, String> mave_tbcl;

    @FXML
    private MFXTextField ngayBay_txtfld;

    @FXML
    private MFXButton quayLai_btn;

    @FXML
    private MFXTextField sanBayDen_txtfld;

    @FXML
    private MFXTextField sanBayDi_txtfld;

    @FXML
    private MFXTextField sdt_txtfld;

    @FXML
    private MFXTextField thanhTien_txtfld;

    @FXML
    private TableView<Ve> ve_tableview;

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage alert = new AlertMessage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();

        // Thiết lập các cột của TableView
        mave_tbcl.setCellValueFactory(new PropertyValueFactory<>("maVe"));
        hangVe_tbcl.setCellValueFactory(new PropertyValueFactory<>("maHangVe"));
        maGhe_tbcl.setCellValueFactory(new PropertyValueFactory<>("maGhe"));
        giaTien_tbcl.setCellValueFactory(new PropertyValueFactory<>("giaTien"));

        // Add listener to the table view selection
        ve_tableview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showTicketDetails(newValue);
            }
        });

        // Set text fields to be non-editable
        maKH_txtfld.setEditable(false);
        maGhe_txtfld.setEditable(false);
        maVe_txtfld.setEditable(false);
        thanhTien_txtfld.setEditable(false);

        xemTruoc_btn.setOnAction(e -> showConfirmationDialog(true));
    }

    public void setFlightDetails(ChuyenBay flight) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        maCB_txtfld.setText(flight.getMaChuyenBay());
        sanBayDi_txtfld.setText(getSanBayDi(flight.getMaDuongBay()));
        sanBayDen_txtfld.setText(getSanBayDen(flight.getMaDuongBay()));
        ngayBay_txtfld.setText(flight.getThoiGianXuatPhat().format(dateFormatter));
        gioBay_txtfld.setText(flight.getThoiGianXuatPhat().format(timeFormatter));

        // Gọi phương thức để tải các vé của chuyến bay
        loadVeForFlight(flight.getMaChuyenBay());
    }

    private String getSanBayDi(String maDuongBay) {
        String sql = "SELECT sb.TenSanBay FROM DUONGBAY db " +
                "JOIN SANBAY sb ON db.MaSanBayDi = sb.MaSanBay " +
                "WHERE db.MaDuongBay = ?";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, maDuongBay);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("TenSanBay");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "San Bay Di"; // Placeholder if not found
    }

    private String getSanBayDen(String maDuongBay) {
        String sql = "SELECT sb.TenSanBay FROM DUONGBAY db " +
                "JOIN SANBAY sb ON db.MaSanBayDen = sb.MaSanBay " +
                "WHERE db.MaDuongBay = ?";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, maDuongBay);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("TenSanBay");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "San Bay Den"; // Placeholder if not found
    }

    private void loadVeForFlight(String maChuyenBay) {
        String sql = "SELECT VE.MAVE, VE.MACHUYENBAY, VE.MAHANGVE, VE.MAGHE, VE.GIATIEN \n" +
                "FROM VE \n" +
                "LEFT JOIN CT_DATVE ON VE.MAVE = CT_DATVE.MAVE \n" +
                "WHERE VE.MACHUYENBAY = ? \n" +
                "AND (CT_DATVE.TRANGTHAI NOT IN (0, 1) OR CT_DATVE.MAVE IS NULL)";

        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, maChuyenBay);
            result = ps.executeQuery();

            ve_tableview.getItems().clear();

            while (result.next()) {
                Ve ve = new Ve(
                        result.getString("MAVE"),
                        result.getString("MACHUYENBAY"),
                        result.getString("MAHANGVE"),
                        result.getInt("MAGHE"),
                        result.getFloat("GIATIEN")
                );
                ve_tableview.getItems().add(ve);
            }

            hangVe_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(getTenHangVe(cellData.getValue().getMaHangVe())));

        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while loading tickets for the flight.");
        }
    }

    private String getTenHangVe(String maHangVe) {
        String sql = "SELECT HV.TENHANGVE FROM VE V " +
                "JOIN HANGVE HV ON HV.MAHANGVE = V.MAHANGVE " +
                "WHERE V.MAHANGVE = ?";
        try (Connection conn = DatabaseDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHangVe);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("TENHANGVE");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Tên hạng vé";
    }

    private void showTicketDetails(Ve ve) {
        maGhe_txtfld.setText(String.valueOf(ve.getMaGhe()));
        maVe_txtfld.setText(ve.getMaVe());
        thanhTien_txtfld.setText(String.valueOf(ve.getGiaTien()));
    }

    private boolean validateInputs() {
        // Kiểm tra xem có vé nào được chọn hay không
        if (ve_tableview.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Vui lòng chọn vé trước khi tiếp tục.");
            return false;
        }

        String hoten = hoten_txtfld.getText().trim();
        String cccd = cccd_txtfld.getText().trim();
        String email = email_txtfld.getText().trim();
        String sdt = sdt_txtfld.getText().trim();
        String diaChi = diaChi_txtfld.getText().trim();

        if (hoten.isEmpty() || cccd.isEmpty() || email.isEmpty() || sdt.isEmpty() || diaChi.isEmpty()) {
            alert.errorMessage("Vui lòng nhập đầy đủ thông tin.");
            return false;
        }

        if (cccd.length() != 12 || !cccd.matches("\\d+")) {
            alert.errorMessage("CCCD phải gồm 12 số.");
            return false;
        }

        if (!email.endsWith("@gmail.com")) {
            alert.errorMessage("Email phải có đuôi @gmail.com.");
            return false;
        }

        if (sdt.length() != 10 || !sdt.matches("\\d+")) {
            alert.errorMessage("Số điện thoại phải gồm 10 số.");
            return false;
        }

        return true;
    }

    // Phương thức hiển thị cửa sổ xác nhận
    private void showConfirmationDialog(boolean isDatVe) {
        if (validateInputs()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/XacNhanVe.fxml"));
                Parent root = loader.load();
                XacNhanVeController controller = loader.getController();

                // Get the data from text fields
                String maKH = maKH_txtfld.getText();
                String hoten = hoten_txtfld.getText();
                String cccd = cccd_txtfld.getText();
                String email = email_txtfld.getText();
                String sdt = sdt_txtfld.getText();
                String diaChi = diaChi_txtfld.getText();
                String maVe = maVe_txtfld.getText();
                String maGhe = maGhe_txtfld.getText();
                String thanhTien = thanhTien_txtfld.getText();
                String maChuyenBay = maCB_txtfld.getText();
                String sanBayDi = sanBayDi_txtfld.getText();
                String sanBayDen = sanBayDen_txtfld.getText();
                String ngayBay = ngayBay_txtfld.getText();
                String gioBay = gioBay_txtfld.getText();

                // Pass the data to XacNhanVeController
                controller.initData(isDatVe, maKH, hoten, cccd, email, sdt, diaChi, maVe, maGhe, thanhTien, maChuyenBay, sanBayDi, sanBayDen, ngayBay, gioBay);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.setTitle("Xác nhận thông tin");
                stage.setOnHidden(event -> reloadFlightDetails()); // Reload flight details after closing
                stage.showAndWait();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void reloadFlightDetails() {
        // Reload the data for the flight details and ticket list
        String maChuyenBay = maCB_txtfld.getText();
        loadVeForFlight(maChuyenBay);
    }
}
