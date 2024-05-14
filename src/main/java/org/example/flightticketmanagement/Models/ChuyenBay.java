package org.example.flightticketmanagement.Models;

import java.time.LocalDateTime;

public class ChuyenBay {
    private String maChuyenBay;
    private String maDuongBay;
    private Integer soLuongGhe;
    private LocalDateTime thoiGianXuatPhat;
    private LocalDateTime thoiGianKetThuc ;
    private String trangThai;

    public ChuyenBay(String maChuyenBay, String maDuongBay, Integer soLuongGhe, LocalDateTime thoiGianXuatPhat, LocalDateTime thoiGianKetThuc, String trangThai) {
        this.maChuyenBay = maChuyenBay;
        this.maDuongBay = maDuongBay;
        this.soLuongGhe = soLuongGhe;
        this.thoiGianXuatPhat = thoiGianXuatPhat;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.trangThai = trangThai;
    }

    public String getMaChuyenBay() {
        return maChuyenBay;
    }

    public void setMaChuyenBay(String maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    public String getMaDuongBay() {
        return maDuongBay;
    }

    public void setMaDuongBay(String maDuongBay) {
        this.maDuongBay = maDuongBay;
    }

    public Integer getSoLuongGhe() {
        return soLuongGhe;
    }

    public void setSoLuongGhe(Integer soLuongGhe) {
        this.soLuongGhe = soLuongGhe;
    }

    public LocalDateTime getThoiGianXuatPhat() {
        return thoiGianXuatPhat;
    }

    public void setThoiGianXuatPhat(LocalDateTime thoiGianXuatPhat) {
        this.thoiGianXuatPhat = thoiGianXuatPhat;
    }

    public LocalDateTime getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(LocalDateTime thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
