package org.example.flightticketmanagement.Models;

import java.time.LocalDateTime;

public class CT_DatVe {
    private String maCT_DatVe;
    private String maVe;
    private String maKhachHang;
    private LocalDateTime thoiGianGiuGhe;
    private String trangThai;

    public CT_DatVe(String maCT_DatVe, String maVe, String maKhachHang, LocalDateTime thoiGianGiuGhe, String trangThai) {
        this.maCT_DatVe = maCT_DatVe;
        this.maVe = maVe;
        this.maKhachHang = maKhachHang;
        this.thoiGianGiuGhe = thoiGianGiuGhe;
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

    public LocalDateTime getThoiGianGiuGhe() {
        return thoiGianGiuGhe;
    }

    public void setThoiGianGiuGhe(LocalDateTime thoiGianGiuGhe) {
        this.thoiGianGiuGhe = thoiGianGiuGhe;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
