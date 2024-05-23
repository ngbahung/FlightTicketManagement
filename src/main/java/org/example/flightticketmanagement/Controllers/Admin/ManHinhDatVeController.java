package org.example.flightticketmanagement.Controllers.Admin;

import io.github.palexdev.materialfx.controls.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.ChuyenBay;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.Ve;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ManHinhDatVeController implements Initializable {
    @FXML
    private MFXTextField cccd_txtfld;

    @FXML
    private MFXButton datCho_btn;

    @FXML
    private MFXButton datVe_btn;

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
        String sql = "SELECT VE.MAVE, VE.MACHUYENBAY, VE.MAHANGVE, VE.MAGHE, VE.GIATIEN FROM VE, CT_DATVE WHERE VE.MAVE = CT_DATVE.MAVE AND VE.MACHUYENBAY = ? AND CT_DATVE.TRANGTHAI = 0";

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
//        alert.errorMessage("Error occurred while loading tickets for the flight.");
        }
    }

    private String getTenHangVe(String maHangVe){
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
}
