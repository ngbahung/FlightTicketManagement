package org.example.flightticketmanagement.Models;

public class SanBay {
    private String maSanBay;
    private String maQuocGia;
    private String tenSanBay;
    private String tenVietTat;
    private String diaChi;
    private String trangThai;

    public SanBay(String maSanBay, String maQuocGia, String tenSanBay, String diaChi, String tenVietTat, String trangThai) {
        this.maSanBay = maSanBay;
        this.maQuocGia = maQuocGia;
        this.tenSanBay = tenSanBay;
        this.diaChi = diaChi;
        this.tenVietTat = tenVietTat;
        this.trangThai = trangThai;
    }

    public String getMaSanBay() {
        return maSanBay;
    }

    public void setMaSanBay(String maSanBay) {
        this.maSanBay = maSanBay;
    }

    public String getMaQuocGia() {
        return maQuocGia;
    }

    public void setMaQuocGia(String maQuocGia) {
        this.maQuocGia = maQuocGia;
    }

    public String getTenSanBay() {
        return tenSanBay;
    }

    public void setTenSanBay(String tenSanBay) {
        this.tenSanBay = tenSanBay;
    }

    public String getTenVietTat() {
        return tenVietTat;
    }

    public void setTenVietTat(String tenVietTat) {
        this.tenVietTat = tenVietTat;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
