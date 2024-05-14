package org.example.flightticketmanagement.Models;

public class KhachHang {
    private String maKhachHang;
    private String hoTen;
    private String CCCD;
    private String SDT;
    private String email;
    private String diaChi;

    public KhachHang(String maKhachHang, String hoTen, String CCCD, String SDT, String email, String diaChi) {
        this.maKhachHang = maKhachHang;
        this.hoTen = hoTen;
        this.CCCD = CCCD;
        this.SDT = SDT;
        this.email = email;
        this.diaChi = diaChi;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
