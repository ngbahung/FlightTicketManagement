package org.example.flightticketmanagement.Models;

public class DuongBay {
    private String maDuongBay;
    private String maSanBayDi;
    private String maSanBayDen;
    private String maSanBayTG;
    private String tenDuongBay;
    private Float doDaiDuongBay;

    public DuongBay(String maDuongBay, String maSanBayDi, String maSanBayDen, String maSanBayTG, String tenDuongBay, Float doDaiDuongBay) {
        this.maDuongBay = maDuongBay;
        this.maSanBayDi = maSanBayDi;
        this.maSanBayDen = maSanBayDen;
        this.maSanBayTG = maSanBayTG;
        this.tenDuongBay = tenDuongBay;
        this.doDaiDuongBay = doDaiDuongBay;
    }

    public String getMaDuongBay() {
        return maDuongBay;
    }

    public void setMaDuongBay(String maDuongBay) {
        this.maDuongBay = maDuongBay;
    }

    public String getMaSanBayDi() {
        return maSanBayDi;
    }

    public void setMaSanBayDi(String maSanBayDi) {
        this.maSanBayDi = maSanBayDi;
    }

    public String getMaSanBayDen() {
        return maSanBayDen;
    }

    public void setMaSanBayDen(String maSanBayDen) {
        this.maSanBayDen = maSanBayDen;
    }

    public String getMaSanBayTG() {
        return maSanBayTG;
    }

    public void setMaSanBayTG(String maSanBayTG) {
        this.maSanBayTG = maSanBayTG;
    }

    public String getTenDuongBay() {
        return tenDuongBay;
    }

    public void setTenDuongBay(String tenDuongBay) {
        this.tenDuongBay = tenDuongBay;
    }

    public Float getDoDaiDuongBay() {
        return doDaiDuongBay;
    }

    public void setDoDaiDuongBay(Float doDaiDuongBay) {
        this.doDaiDuongBay = doDaiDuongBay;
    }
}
