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
    private AnchorPane bangDieuKhienView;
    private AnchorPane trangChuLichView;

    public ViewFactory(){
        this.adminSelectedMenuItem = new SimpleStringProperty("");
    }

    public StringProperty getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public AnchorPane getBangDieuKhienView(){
        if (bangDieuKhienView == null) {
            try {
                bangDieuKhienView = new FXMLLoader(getClass().getResource("/Fxml/Admin/BangDieuKhien.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return bangDieuKhienView;
    }

    public AnchorPane getTrangChuLichView() {
        if (trangChuLichView == null){
            try {
                trangChuLichView = new FXMLLoader(getClass().getResource("/Fxml/Admin/TrangChu.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return trangChuLichView;
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
