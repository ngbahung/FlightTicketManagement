package org.example.flightticketmanagement.Models;

public class DuongBay {
    private String maDuongBay;
    private String tenSanBayDi;
    private String tenSanBayDen;
    private String tenDuongBay;
    private Integer soThuTu;
    private Integer trangThai;

    public DuongBay(String maDuongBay, String tenSanBayDi, String tenSanBayDen, String tenDuongBay, Integer soThuTu, Integer trangThai) {
        this.maDuongBay = maDuongBay;
        this.tenSanBayDi = tenSanBayDi;
        this.tenSanBayDen = tenSanBayDen;
        this.tenDuongBay = tenDuongBay;
        this.soThuTu = soThuTu;
        this.trangThai = trangThai;
    }

    public String getMaDuongBay() {
        return maDuongBay;
    }

    public void setMaDuongBay(String maDuongBay) {
        this.maDuongBay = maDuongBay;
    }

    public String getTenSanBayDi() {
        return tenSanBayDi;
    }

    public void setTenSanBayDi(String tenSanBayDi) {
        this.tenSanBayDi = tenSanBayDi;
    }

    public String getTenSanBayDen() {
        return tenSanBayDen;
    }

    public void setTenSanBayDen(String tenSanBayDen) {
        this.tenSanBayDen = tenSanBayDen;
    }

    public String getTenDuongBay() {
        return tenDuongBay;
    }

    public void setTenDuongBay(String tenDuongBay) {
        this.tenDuongBay = tenDuongBay;
    }

    public Integer getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(Integer soThuTu) {
        this.soThuTu = soThuTu;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
}
