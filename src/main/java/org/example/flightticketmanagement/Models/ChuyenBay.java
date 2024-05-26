package org.example.flightticketmanagement.Models;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChuyenBay {
    private String maChuyenBay;
    private String maDuongBay;
    private Integer soChuyenBay;
    private LocalDateTime thoiGianXuatPhat;
    private LocalDateTime thoiGianKetThuc ;
    private String trangThai;
    private Float giaVe;

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public ChuyenBay(String maChuyenBay, String maDuongBay, LocalDateTime thoiGianXuatPhat, LocalDateTime thoiGianKetThuc, String trangThai, Float giaVe) {
        this.maChuyenBay = maChuyenBay;
        this.maDuongBay = maDuongBay;
        this.thoiGianXuatPhat = thoiGianXuatPhat;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.trangThai = trangThai;
        this.giaVe = giaVe;
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

    public Float getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(Float giaVe) {
        this.giaVe = giaVe;
    }

}
