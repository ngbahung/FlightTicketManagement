package org.example.flightticketmanagement.Models;

import java.sql.Date;
import java.time.LocalDateTime;

public class TaiKhoan {
    private String maTaiKhoan;
    private String ten;
    private String SDT;
    private String email;
    private String password;
    private LocalDateTime created;
    private String maQuyen;

    public TaiKhoan(String maTaiKhoan, String ten, String SDT, String email, String password, LocalDateTime created, String maQuyen) {
        this.maTaiKhoan = maTaiKhoan;
        this.ten = ten;
        this.SDT = SDT;
        this.email = email;
        this.password = password;
        this.created = created;
        this.maQuyen = maQuyen;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }
}
