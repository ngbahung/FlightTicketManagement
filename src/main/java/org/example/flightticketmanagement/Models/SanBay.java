package org.example.flightticketmanagement.Models;

public class SanBay {
    private String maSanBay;
    private String tenSanBay;
    private String tenVietTat;
    private String diaChi;
    private Integer trangThai;

    public SanBay(String maSanBay, String tenSanBay, String tenVietTat, String diaChi, Integer trangThai) {
        this.maSanBay = maSanBay;
        this.tenSanBay = tenSanBay;
        this.tenVietTat = tenVietTat;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }

    public String getMaSanBay() {
        return maSanBay;
    }

    public void setMaSanBay(String maSanBay) {
        this.maSanBay = maSanBay;
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

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
}
