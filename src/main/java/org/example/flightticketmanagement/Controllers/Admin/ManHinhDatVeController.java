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
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.ChuyenBay;
import org.example.flightticketmanagement.Models.DatabaseDriver;
import org.example.flightticketmanagement.Models.Ve;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
    private MFXTextField maGhe_txtfld;

    @FXML
    private MFXTextField maVe_txtfld;

    @FXML
    private MFXTextField ngayBay_txtfld;

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

    private final AlertMessage alert = new AlertMessage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = DatabaseDriver.getConnection();

        // Thiết lập các cột của TableView
        hangVe_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(getTenHangVe(cellData.getValue().getMaHangVe())));
        giaTien_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getGiaTien())));

        // Add listener to the table view selection
        ve_tableview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                generateTicketDetails(newValue);
            }
        });

        // Set text fields to be non-editable
        maKH_txtfld.setEditable(false);
        maGhe_txtfld.setEditable(false);
        maVe_txtfld.setEditable(false);
        thanhTien_txtfld.setEditable(false);

        xemTruoc_btn.setOnAction(e -> {
            if (validateInputs()) {
                saveTicketToDatabase();
                showConfirmationDialog();
            }
        });
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
        String sanBayDi;
        try (CallableStatement statement = connect.prepareCall("{call GET_SANBAYDI(?, ?)}")) {
            statement.setString(1, maDuongBay);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.execute();
            return statement.getString(2);
        } catch (Exception e) {
            sanBayDi = "";
        }
        return sanBayDi;
    }

    private String getSanBayDen(String maDuongBay) {
        String sanBayDen;
        try (CallableStatement statement = connect.prepareCall("{call GET_SANBAYDEN(?, ?)}")) {
            statement.setString(1, maDuongBay);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.execute();
            return statement.getString(2);
        } catch (Exception e) {
            sanBayDen = "";
        }
        return sanBayDen;
    }

    private void loadVeForFlight(String maChuyenBay) {
        try (CallableStatement cs = connect.prepareCall("{call GET_VE_FOR_FLIGHT(?, ?)}")) {
            cs.setString(1, maChuyenBay);
            cs.registerOutParameter(2, java.sql.Types.REF_CURSOR);

            cs.execute();

            try (ResultSet result = (ResultSet) cs.getObject(2)) {
                ve_tableview.getItems().clear();

                while (result.next()) {
                    Ve ve = new Ve(
                            null, // MaVe will be generated
                            maChuyenBay,
                            result.getString("MAHANGVE"),
                            0, // MaGhe will be generated
                            result.getFloat("GiaTien") // Calculated GiaTien
                    );
                    ve_tableview.getItems().add(ve);
                }

                hangVe_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(getTenHangVe(cellData.getValue().getMaHangVe())));
                giaTien_tbcl.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getGiaTien())));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error occurred while loading ticket classes for the flight.");
        }
    }

    private String getTenHangVe(String maHangVe) {
        String tenHangVe;
        String sql = "{call GET_TENHANGVE(?, ?)}";

        try (CallableStatement cs = connect.prepareCall(sql)) {
            cs.setString(1, maHangVe);
            cs.registerOutParameter(2, Types.VARCHAR);

            cs.execute();
            tenHangVe = cs.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
            tenHangVe = "Lỗi khi lấy tên hạng vé";
        }
        return tenHangVe;
    }

    private void generateTicketDetails(Ve ve) {
        maVe_txtfld.setText(generateMaVe());
        maGhe_txtfld.setText(generateMaGhe());
        thanhTien_txtfld.setText(String.valueOf(ve.getGiaTien()));
    }

    public String generateMaVe() {
        String maVe = "VE001"; // Giá trị mặc định
        String sql = "{call GENERATE_MA_VE(?)}";

        try (CallableStatement callableStatement = connect.prepareCall(sql)) {
            callableStatement.registerOutParameter(1, Types.VARCHAR);

            callableStatement.execute();

            maVe = callableStatement.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maVe;
    }

    public String generateMaGhe() {
        String maGhe = "1"; // Giá trị mặc định
        String sql = "{call GENERATE_MA_GHE(?)}";

        try (CallableStatement callableStatement = connect.prepareCall(sql)) {
            callableStatement.registerOutParameter(1, Types.INTEGER);

            callableStatement.execute();

            maGhe = String.valueOf(callableStatement.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maGhe;
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

    private void saveTicketToDatabase() {
        Ve selectedVe = ve_tableview.getSelectionModel().getSelectedItem();
        String maChuyenBay = maCB_txtfld.getText();
        String maHangVe = selectedVe.getMaHangVe();
        String maGhe = generateMaGhe();  // Hàm này sẽ gọi stored procedure để tạo mã ghế
        float giaTien = selectedVe.getGiaTien();

        try {
            // Đặt transaction isolation level ở đây
            connect.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            String sql = "{call SAVE_TICKET(?, ?, ?, ?, ?)}";

            try (CallableStatement cs = connect.prepareCall(sql)) {
                cs.setString(1, maVe_txtfld.getText());
                cs.setString(2, maChuyenBay);
                cs.setString(3, maHangVe);
                cs.setString(4, maGhe); // Lưu mã ghế tự tạo
                cs.setFloat(5, giaTien);

                cs.execute();
            } catch (SQLException e) {
                e.printStackTrace();
                alert.errorMessage("Error occurred while saving ticket to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert.errorMessage("Error setting transaction isolation level.");
        }
    }


    private void showConfirmationDialog() {
        if (validateInputs()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/XacNhanVe.fxml"));
                Parent root = loader.load();
                XacNhanVeController controller = loader.getController();

                // Pass the reference of this controller
                controller.setManHinhDatVeController(this);

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
                controller.initData(maKH, hoten, cccd, email, sdt, diaChi, maVe, maGhe, thanhTien, maChuyenBay, sanBayDi, sanBayDen, ngayBay, gioBay);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/Admin/logo.png"))));
                stage.setTitle("Xác nhận thông tin");
                stage.setOnHidden(event -> reloadFlightDetails()); // Reload flight details after closing
                stage.showAndWait();
                stage.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void reloadFlightDetails() {
        String maChuyenBay = maCB_txtfld.getText();
        loadVeForFlight(maChuyenBay);
    }

    public void closeStage() {
        Stage stage = (Stage) maKH_txtfld.getScene().getWindow();
        stage.close();
    }

}
