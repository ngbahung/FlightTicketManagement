package org.example.flightticketmanagement.Models;

public class ChucNang {
    private String maChucNang;
    private String tenChucNang;
    private String tenManHinhDuocLoad;

    public ChucNang(String tenManHinhDuocLoad, String maChucNang, String tenChucNang) {
        this.tenManHinhDuocLoad = tenManHinhDuocLoad;
        this.maChucNang = maChucNang;
        this.tenChucNang = tenChucNang;
    }

    public String getMaChucNang() {
        return maChucNang;
    }

    public void setMaChucNang(String maChucNang) {
        this.maChucNang = maChucNang;
    }

    public String getTenChucNang() {
        return tenChucNang;
    }

    public void setTenChucNang(String tenChucNang) {
        this.tenChucNang = tenChucNang;
    }

    public String getTenManHinhDuocLoad() {
        return tenManHinhDuocLoad;
    }

    public void setTenManHinhDuocLoad(String tenManHinhDuocLoad) {
        this.tenManHinhDuocLoad = tenManHinhDuocLoad;
    }
}
