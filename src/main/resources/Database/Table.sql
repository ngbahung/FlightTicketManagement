-- Tạo Schema

alter session set "_ORACLE_SCRIPT"=true;

CREATE USER fly_the_end11 IDENTIFIED BY password;
GRANT CONNECT, RESOURCE, DBA TO fly_the_end11;


alter session set "_ORACLE_SCRIPT"=true;
CREATE USER user1 IDENTIFIED BY user1;
GRANT CONNECT, RESOURCE, DBA TO user1;

alter session set "_ORACLE_SCRIPT"=true;
CREATE USER user2 IDENTIFIED BY user2;
GRANT CONNECT, RESOURCE, DBA TO user2;

-- Sử dụng Schema

ALTER SESSION SET CURRENT_SCHEMA = fly_the_end11;


-- create sequence

CREATE SEQUENCE MAVE
    MINVALUE 1
    MAXVALUE 9999999
    INCREMENT BY 1
    START WITH 216
    NOCACHE
    NOORDER
    NOCYCLE;
CREATE SEQUENCE MAKHACHHANG
    MINVALUE 1
    MAXVALUE 9999999
    INCREMENT BY 1
    START WITH 51
    NOCACHE
    NOORDER
    NOCYCLE;
CREATE SEQUENCE MAHANGVE
    MINVALUE 1
    MAXVALUE 9999999
    INCREMENT BY 1
    START WITH 26
    NOCACHE
    NOORDER
    NOCYCLE;
CREATE SEQUENCE MATHUOCTINH
    MINVALUE 1
    MAXVALUE 9999999
    INCREMENT BY 1
    START WITH 1981
    NOCACHE
    NOORDER
    NOCYCLE;
CREATE SEQUENCE MaTK
    MINVALUE 1
    MAXVALUE 9999999
    INCREMENT BY 1
    START WITH 1369
    NOCACHE
    NOORDER
    NOCYCLE;



-- create table


/* TABLE 1: SANBAY */

CREATE TABLE SANBAY (
                        MaSanBay VARCHAR2(10) NOT null,
                        TenSanBay VARCHAR2(200) NOT NULL ,
                        TenVietTat VARCHAR2(30),
                        DiaChi VARCHAR2(30),
                        TrangThai SMALLINT,
                        CONSTRAINT PK_SANBAY PRIMARY KEY (MaSanBay)
);


/* TABLE 2: KHACHHANG */

CREATE TABLE KHACHHANG (
                           MaKhachHang VARCHAR2(10) NOT NULL,
                           HoTen VARCHAR2(50),
                           CCCD VARCHAR2(20),
                           SDT VARCHAR2(20),
                           Email VARCHAR2(50),
                           DiaChi VARCHAR2(50),
                           CONSTRAINT PK_KHACHHANG PRIMARY KEY (MaKhachHang)
);




/* TABLE 3: DUONGBAY */

CREATE TABLE DUONGBAY (
                          MaDuongBay VARCHAR2(10) NOT NULL,
                          MaSanBayDi VARCHAR2(10) NOT NULL,
                          MaSanBayDen VARCHAR2(10) NOT NULL,
                          TenDuongBay VARCHAR2(30) NOT NULL,
                          DoDaiDuongBay NUMBER,
                          trangthai NUMBER,
                          CONSTRAINT PK_DUONGBAY PRIMARY KEY (MaDuongBay)
);


/* TABLE 4 : CHUYENBAY */

CREATE TABLE CHUYENBAY (
                           MaChuyenBay VARCHAR2(10) NOT NULL ,
                           MaDuongBay VARCHAR2(10) NOT null ,
                           TGXP TIMESTAMP,
                           TGKT TIMESTAMP,
                           TrangThai number,
                           GiaVe NUMBER(10,2),
                           CONSTRAINT PK_CHUYENBAY PRIMARY KEY (MaChuyenBay)
);


/* TABLE 5: HANGVE */

CREATE TABLE HANGVE (
                        MaHangVe VARCHAR2(10) NOT NULL,
                        TenHangVe VARCHAR2(30) NOT NULL,
                        HeSo NUMBER,
                        TrangThai int,
                        CONSTRAINT PK_HANGVE PRIMARY KEY (MaHangVe)
);



/* TABLE 6: VE */

CREATE TABLE VE (
                    MaVe VARCHAR2(10) NOT NULL,
                    MaChuyenBay VARCHAR2(10) NOT NULL,
                    MaHangVe VARCHAR2(10) NOT NULL,
                    MaGhe NUMBER  NOT NULL,
                    GiaTien NUMBER,
                    CONSTRAINT PK_VE PRIMARY KEY (MaVe)
);




/* TABLE 7: CT_HANGVE */

CREATE TABLE CT_HANGVE (
                           MaChuyenBay VARCHAR2(10) NOT NULL,
                           MaHangVe VARCHAR2(10) NOT NULL,
                           SoGheTrong SMALLINT,
                           SoGheDat SMALLINT,
                           CONSTRAINT PK_CT_HANGVE PRIMARY KEY (MaChuyenBay,MaHangVe)
);



/* TABLE 8: CT_DATVE */

CREATE TABLE CT_DATVE (
                          MaCT_DATVE VARCHAR2(10) NOT NULL,
                          MaVe VARCHAR2(10) NOT NULL,
                          MaKhachHang VARCHAR2(10) NOT NULL,
                          NgayMuaVe TIMESTAMP,
                          NgayThanhToan TIMESTAMP,
                          TrangThai int,
                          CONSTRAINT PK_CT_DATVE PRIMARY KEY (MaCT_DATVE)
);

/* TABLE 9: baocaonam */

CREATE TABLE BAOCAONAM (
                           Thang NUMBER NOT NULL,
                           Nam number NOT NULL,
                           SoChuyenBay NUMBER,
                           DoanhThu NUMBER(10,2) NULL,
                           CONSTRAINT pk_bcn PRIMARY KEY (Nam,thang)
);

/* TABLE 10: baocaothang */

CREATE TABLE BAOCAOTHANG (
                             MaChuyenBay VARCHAR2(50) NOT NULL,
                             SoVeDaBan NUMBER NULL,
                             DoanhThu NUMBER(10,2) NULL,
                             Thang NUMBER,
                             Nam NUMBER,
                             CONSTRAINT pk_BCThang PRIMARY KEY (MaChuyenBay,Thang,Nam)
);


/* TABLE 11:   QUYEN */

CREATE TABLE QUYEN (
                       MaQuyen VARCHAR2(10),
                       TenQuyen NVARCHAR2(50),
                       CONSTRAINT PK_QUYEN PRIMARY KEY(MaQuyen)
);



/* TABLE 12: TAIKHOAN */

CREATE TABLE TAIKHOAN (
                          MaTaiKhoan VARCHAR2(10),
                          Ten NVARCHAR2(50),
                          Sdt VARCHAR2(15),
                          Email VARCHAR2(50),
                          Password VARCHAR2(10),
                          Created DATE,
                          MaQuyen VARCHAR2(10),
                          CONSTRAINT PK_TAIKHOAN PRIMARY KEY (MaTaiKhoan)
);


/* TABLE 13: THAMSO */

CREATE TABLE THAMSO (
                        MaThuocTinh VARCHAR2(10) NOT NULL,
                        TenThuocTinh VARCHAR2(30) NOT NULL,
                        GiaTri NUMBER,
                        CONSTRAINT PK_THAMSO PRIMARY KEY (MaThuocTinh)
);



/* KHOA NGOAI */

ALTER TABLE DUONGBAY
    ADD CONSTRAINT FK_DUONGBAY_SANBAY1 FOREIGN KEY (MaSanBayDi) REFERENCEs SANBAY(MaSanBay);

ALTER TABLE DUONGBAY
    ADD CONSTRAINT FK_DUONGBAY_SANBAY2 FOREIGN KEY (MaSanBayDen) REFERENCEs SANBAY(MaSanBay);

ALTER TABLE VE
    ADD CONSTRAINT FK_VE_CHUYENBAY FOREIGN KEY (MaChuyenBay) REFERENCES CHUYENBAY(MaChuyenBay);

ALTER TABLE VE
    ADD CONSTRAINT FK_VE_HANGVE  FOREIGN KEY (MaHangVe) REFERENCES HANGVE(MaHangVe);

ALTER TABLE CT_DATVE
    ADD CONSTRAINT FK_CT_DATVE_KHACHHANG FOREIGN KEY (MaKhachHang) REFERENCES KHACHHANG(MaKhachHang);


ALTER TABLE CT_HANGVE
    ADD CONSTRAINT FK_CT_HANGVE_CHUYENBAY  FOREIGN KEY (MaChuyenBay) REFERENCES CHUYENBAY(MaChuyenBay);

ALTER TABLE CT_HANGVE
    ADD CONSTRAINT FK_CT_HANGVE_HANGVE FOREIGN KEY (MaHangVe) REFERENCES HANGVE(MaHangVe);


ALTER TABLE CT_DATVE
    ADD CONSTRAINT FK_CT_DATVE_VE FOREIGN KEY (MaVe) REFERENCES VE(MaVe);


ALTER TABLE BAOCAOTHANG
    ADD CONSTRAINT FK_BAOCAOTHANG_CHUYENBAY FOREIGN KEY (MaChuyenBay) REFERENCES CHUYENBAY(MaChuyenBay);


ALTER TABLE TAIKHOAN
    ADD CONSTRAINT FK_TAIKHOAN_QUYEN
        FOREIGN KEY (MaQuyen) REFERENCES QUYEN(MaQuyen);



ALTER TABLE chuyenbay
    ADD CONSTRAINT FK_CB_DB FOREIGN KEY (MADUONGBAY) REFERENCES DUONGBAY(MADUONGBAY);

