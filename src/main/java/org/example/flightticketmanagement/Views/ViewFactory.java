package org.example.flightticketmanagement.Views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.flightticketmanagement.Controllers.Admin.AdminController;
import org.example.flightticketmanagement.Controllers.Manager.ManagerController;
import org.example.flightticketmanagement.Controllers.Staff.StaffController;

public class ViewFactory {
    private AccountType loginAccountType;

//  Admin Views
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane phanQuyenView;
    private AnchorPane lichChuyenBayView;
    private AnchorPane banVeView;
    private AnchorPane datChoView;
    private AnchorPane doanhThuView;
    private AnchorPane suaQuyDinhView;

//    Manager Views
    private final ObjectProperty<ManagerMenuOptions> managerSelectedMenuItem;
    private AnchorPane lichCBManaView;
    private AnchorPane banVeManaView;
    private AnchorPane datChoManaView;
    private AnchorPane doanhThuManaView;


//    Staff Views
    private final ObjectProperty<StaffMenuOptions> staffSelectedMenuItem;
    private AnchorPane lichCBStaView;
    private AnchorPane banVeStaView;
    private AnchorPane datChoStaView;

    public ViewFactory(){
        this.loginAccountType = AccountType.ADMIN;
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
        this.managerSelectedMenuItem = new SimpleObjectProperty<>();
        this.staffSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    /* Admin View Section
* */
    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
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

    public void hienThiManHinhAdmin(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
        createStage(loader);
    }

    /*
    * Manager Views Section
    * */

    public ObjectProperty<ManagerMenuOptions> getManagerSelectedMenuItem() {
        return managerSelectedMenuItem;
    }

    public AnchorPane getLichCBManaView() {
        if (lichCBManaView == null){
            try {
                lichCBManaView = new FXMLLoader(getClass().getResource("/Fxml/Manager/LichChuyenBay.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return lichCBManaView;
    }

    public AnchorPane getBanVeManaView() {
        if (banVeManaView == null){
            try {
                banVeManaView = new FXMLLoader(getClass().getResource("/Fxml/Manager/BanVe.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return banVeManaView;
    }

    public AnchorPane getDatChoManaView() {
        if (datChoManaView == null){
            try {
                datChoManaView = new FXMLLoader(getClass().getResource("/Fxml/Manager/DatCho.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return datChoManaView;
    }

    public AnchorPane getDoanhThuManaView() {
        if (doanhThuManaView == null){
            try {
                doanhThuManaView = new FXMLLoader(getClass().getResource("/Fxml/manager/DoanhThu.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return doanhThuManaView;
    }

    public void hienThiManHinhManager(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Manager/Manager.fxml"));
        ManagerController managerController = new ManagerController();
        loader.setController(managerController);
        createStage(loader);
    }

    /*
    * Staff Views Section
    * */

    public ObjectProperty<StaffMenuOptions> getStaffSelectedMenuItem() {
        return staffSelectedMenuItem;
    }

    public AnchorPane getLichCBStaView() {
        if (lichCBStaView == null){
            try {
                lichCBStaView = new FXMLLoader(getClass().getResource("/Fxml/Staff/LichChuyenBayStaff.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return lichCBStaView;
    }

    public AnchorPane getBanVeStaView() {
        if (banVeStaView == null) {
            try {
                banVeStaView = new FXMLLoader(getClass().getResource("/Fxml/Staff/BanVe.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return banVeStaView;
    }

    public AnchorPane getDatChoStaView() {
        if (datChoStaView == null) {
            try {
                datChoStaView = new FXMLLoader(getClass().getResource("/Fxml/Staff/DatCho.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return datChoStaView;
    }

    public void hienThiManHinhStaff(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Staff/Staff.fxml"));
        StaffController staffController = new StaffController();
        loader.setController(staffController);
        createStage(loader);
    }

    /*
    * DangNhap View Section
    * */
    public void hienThiManHinhDangNhap(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/DangNhap.fxml"));
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
