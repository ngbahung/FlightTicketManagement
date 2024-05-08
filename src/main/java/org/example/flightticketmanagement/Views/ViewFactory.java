package org.example.flightticketmanagement.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.Admin.AdminController;

public class ViewFactory {
    private AnchorPane bangDieuKhien;

    public ViewFactory(){}

    public AnchorPane getBangDieuKhien(){
        if (bangDieuKhien == null) {
            try {
                bangDieuKhien = new FXMLLoader(getClass().getResource("/Fxml/Admin/BangDieuKhien.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return bangDieuKhien;
    }

    public void hienThiManHinhDangNhap(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
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

    public void hienThiManHinhAdmin(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
        createStage(loader);
    }

    public void dongStage(Stage stage){
        stage.close();
    }
}
