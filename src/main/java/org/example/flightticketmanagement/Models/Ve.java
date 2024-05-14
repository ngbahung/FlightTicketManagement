package org.example.flightticketmanagement.Models;

import java.time.LocalDateTime;

public class Ve {
    private String maVe;
    private String maChuyenBay;
    private String maHangVe;
    private String maKhachHang;
    private String maGhe;
    private LocalDateTime ngayMuaVe;
    private LocalDateTime ngayThanhToan;
    private Float giaTien;

    public Ve(String maVe, String maChuyenBay, String maHangVe, String maKhachHang, String maGhe, LocalDateTime ngayMuaVe, LocalDateTime ngayThanhToan, Float giaTien) {
        this.maVe = maVe;
        this.maChuyenBay = maChuyenBay;
        this.maHangVe = maHangVe;
        this.maKhachHang = maKhachHang;
        this.maGhe = maGhe;
        this.ngayMuaVe = ngayMuaVe;
        this.ngayThanhToan = ngayThanhToan;
        this.giaTien = giaTien;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getMaChuyenBay() {
        return maChuyenBay;
    }

    public void setMaChuyenBay(String maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    public String getMaHangVe() {
        return maHangVe;
    }

    public void setMaHangVe(String maHangVe) {
        this.maHangVe = maHangVe;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMaGhe() {
        return maGhe;
    }

    public void setMaGhe(String maGhe) {
        this.maGhe = maGhe;
    }

    public LocalDateTime getNgayMuaVe() {
        return ngayMuaVe;
    }

    public void setNgayMuaVe(LocalDateTime ngayMuaVe) {
        this.ngayMuaVe = ngayMuaVe;
    }

    public LocalDateTime getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(LocalDateTime ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public Float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Float giaTien) {
        this.giaTien = giaTien;
    }
}
