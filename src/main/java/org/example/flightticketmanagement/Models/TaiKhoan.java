package org.example.flightticketmanagement.Models;

import java.util.Date;

public class TaiKhoan {
    private String MaTaiKhoan;
    private String Ten;
    private String Sdt;
    private String Email;
    private String Password;
    private Date Created;
    private String MaQuyen;

    public TaiKhoan(String MaTaiKhoan, String Ten, String Sdt, String Email, String Password, Date Created, String MaQuyen) {
        this.MaTaiKhoan = MaTaiKhoan;
        this.Ten = Ten;
        this.Sdt = Sdt;
        this.Email = Email;
        this.Password = Password;
        this.Created = Created;
        this.MaQuyen = MaQuyen;
    }

    public String getMaQuyen() {
        return MaQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        MaQuyen = maQuyen;
    }

    public Date getCreated() {
        return Created;
    }

    public void setCreated(Date created) {
        Created = created;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getMaTaiKhoan() {
        return MaTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        MaTaiKhoan = maTaiKhoan;
    }
}
