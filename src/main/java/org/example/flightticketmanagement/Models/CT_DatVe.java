package org.example.flightticketmanagement.Models;

import java.time.LocalDateTime;

public class CT_DatVe {
    private String maCT_DatVe;
    private String maVe;
    private String maKhachHang;
    private LocalDateTime ngayMuaVe;
    private LocalDateTime ngayThanhToan;
    private String trangThai;

    public CT_DatVe(String maCT_DatVe, String maVe, String maKhachHang, LocalDateTime ngayMuaVe, LocalDateTime ngayThanhToan, String trangThai) {
        this.maCT_DatVe = maCT_DatVe;
        this.maVe = maVe;
        this.maKhachHang = maKhachHang;
        this.ngayMuaVe = ngayMuaVe;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThai = trangThai;
    }

    public String getMaCT_DatVe() {
        return maCT_DatVe;
    }

    public void setMaCT_DatVe(String maCT_DatVe) {
        this.maCT_DatVe = maCT_DatVe;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public LocalDateTime getNgayMuaVe() {
        return ngayMuaVe;
    }

    public void setNgayMuaVe(LocalDateTime ngayMuaVe) {
        this.ngayMuaVe = ngayMuaVe;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDateTime getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(LocalDateTime ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }
}
