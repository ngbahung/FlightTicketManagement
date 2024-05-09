package org.example.flightticketmanagement.Views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.Admin.AdminController;

public class ViewFactory {
//  Admin Views
    private final StringProperty adminSelectedMenuItem;
    private AnchorPane phanQuyenView;
    private AnchorPane lichChuyenBayView;
    private AnchorPane banVeView;
    private AnchorPane datChoView;
    private AnchorPane doanhThuView;
    private AnchorPane suaQuyDinhView;


    public ViewFactory(){
        this.adminSelectedMenuItem = new SimpleStringProperty("");
    }
/* Admin View Section
* */
    public StringProperty getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public AnchorPane getPhanQuyenView(){
        if (phanQuyenView == null) {
            try {
                phanQuyenView = new FXMLLoader(getClass().getResource("/Fxml/Admin/BangDieuKhien.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return phanQuyenView;
    }

    public AnchorPane getLichChuyenBayView() {
        if (lichChuyenBayView == null){
            try {
                lichChuyenBayView = new FXMLLoader(getClass().getResource("/Fxml/Admin/LichChuyenBay.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return lichChuyenBayView;
    }

    public AnchorPane getBanVeView() {
        if (banVeView == null){
            try {
                banVeView = new FXMLLoader(getClass().getResource("/Fxml/Admin/BanVe.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return banVeView;
    }

    public AnchorPane getDatChoView() {
        if (datChoView == null){
            try {
                datChoView = new FXMLLoader(getClass().getResource("/Fxml/Admin/DatCho.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return datChoView;
    }

    public AnchorPane getDoanhThuView() {
        if (doanhThuView == null){
            try {
                doanhThuView = new FXMLLoader(getClass().getResource("/Fxml/Admin/DoanhThu.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return doanhThuView;
    }

    public AnchorPane getSuaQuyDinhView() {
        if (suaQuyDinhView == null){
            try {
                suaQuyDinhView = new FXMLLoader(getClass().getResource("/Fxml/Admin/SuaQuyDinh.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return suaQuyDinhView;
    }

    public void hienThiManHinhDangNhap(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/DangNhap.fxml"));
        createStage(loader);
    }



    public void hienThiManHinhAdmin(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
        createStage(loader);
    }

    public void dongStage(Stage stage){
        stage.close();
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("QUAN LY BAN VE CHUYEN BAY");
        stage.show();
    }
}
