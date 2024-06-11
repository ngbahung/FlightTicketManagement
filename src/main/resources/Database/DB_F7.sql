/*-- DROP ALL TABLE
BEGIN
   FOR t IN (SELECT table_name FROM user_tables) LOOP
      EXECUTE IMMEDIATE 'DROP TABLE ' || t.table_name || ' CASCADE CONSTRAINTS';
   END LOOP;
END;
*/

-- Tạo Schema

alter session set "_ORACLE_SCRIPT"=true;

CREATE USER fly_the_end12A IDENTIFIED BY password;
GRANT CONNECT, RESOURCE, DBA TO fly_the_end12A;


alter session set "_ORACLE_SCRIPT"=true;
CREATE USER user1 IDENTIFIED BY user1;
GRANT CONNECT, RESOURCE, DBA TO user1;

alter session set "_ORACLE_SCRIPT"=true;
CREATE USER user2 IDENTIFIED BY user2;
GRANT CONNECT, RESOURCE, DBA TO user2;

-- Sử dụng Schema

ALTER SESSION SET CURRENT_SCHEMA = fly_the_end12A;


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
                          ThoiGianBay INTERVAL DAY(2) TO SECOND,
                          CONSTRAINT PK_DUONGBAY PRIMARY KEY (MaDuongBay)
);

/* TABLE 14: SANBAYTG */
CREATE TABLE SANBAYTG (
                          MaDuongBay VARCHAR2(10) NOT NULL,
                          MaSanBay VARCHAR2(10) NOT NULL,
                          ThuTu NUMBER,
                          ThoiGianDung INTERVAL DAY TO SECOND,
                          CONSTRAINT PK_SANBAYTG PRIMARY KEY (MaDuongBay, MaSanBay)
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
    ADD CONSTRAINT FK_TAIKHOAN_QUYEN FOREIGN KEY (MaQuyen) REFERENCES QUYEN(MaQuyen);

ALTER TABLE chuyenbay
    ADD CONSTRAINT FK_CB_DB FOREIGN KEY (MADUONGBAY) REFERENCES DUONGBAY(MADUONGBAY);

--- TRUNGHIEU thêm
ALTER TABLE SANBAYTG
    ADD CONSTRAINT FK_SANBAYTG_DUONGBAY FOREIGN KEY (MaDuongBay) REFERENCES DUONGBAY(MaDuongBay);

ALTER TABLE SANBAYTG
    ADD CONSTRAINT FK_SANBAYTG_SANBAY FOREIGN KEY (MaSanBay) REFERENCES SANBAY(MaSanBay);


---------------------------------------------------------------------------------------------------------------
-- INSERT DATA

--insert dữ liệu


-- TABLE : SANBAY

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD001', 'Tân Sơn Nhất', 'SGN', 'Ho Chi Minh City', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD002', 'Nội Bài', 'HAN', 'Ha Noi', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD003', 'Chu Lai', 'VCL', 'Quang Nam', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD004', 'Phú Quốc', 'PQC', 'Kien Giang', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD005', 'Đà Nẵng', 'DAD', 'Da Nang', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD006', 'Phù Cát', 'UIH', 'Bình Dinh', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD007', 'Pleiku', 'PXU', 'Gia Lai', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD008', 'Vân Đồn', 'VDO', 'Quang Ninh', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD009', 'Cát Bi', 'HPH', 'Hai Phong', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD010', 'Vinh', 'VII', 'Nghe An', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD011', 'Phú Bài', 'HUI', 'Huế', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD012', 'Cam Ranh', 'CXR', 'Khách Hòa', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD013', 'Liên Khương', 'DLI', 'Lâm Đồng', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD014', 'Cần Thơ', 'VCA', 'Cần Thơ', 1);

-- Thêm sân bay quốc tế
INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD050', 'Seletar', 'XSP', 'Singapore', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD051', 'Changi', 'SIN', 'Singapore', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD052', 'Phuket', 'PKT', 'Thai Lan', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD053', 'Chiang Mai', 'CMT', 'Thai Lan', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD054', 'Incheon', 'ICN', 'Han Quoc', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD055', 'Gimhae', 'PUS', 'Han Quoc', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD056', 'Chicago', 'ORD', 'Hoa Ky', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD057', 'Dallas', 'DFW', 'Hoa Ky', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD058', 'Abingdon', 'ABB', 'Anh', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD059', 'Bedford', 'BFC', 'Anh', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD060', 'Berlin', 'TXL', 'Duc', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD061', 'Frankfurt am Main', 'FRA', 'Duc', 1);


-- TABLE: DUONGBAY

-- Chèn đường bay với thời gian bay

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB001', 'SBD003', 'SBD002', 'VCL-HAN', 9800, 1, INTERVAL '09:45:00' HOUR TO SECOND);

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB002', 'SBD003', 'SBD004', 'VCL-PQC', 9800, 1, INTERVAL '01:20:00' HOUR TO SECOND);

-- Routes from Binh Dinh (Phu Cat - UIH)

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB003', 'SBD006', 'SBD002', 'UIH-HAN', 9800, 1, INTERVAL '01:50:00' HOUR TO SECOND);

-- Routes from Hanoi (Noi Bai - HAN)

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB004', 'SBD002', 'SBD001', 'HAN-SGN', 1200, 1, INTERVAL '02:10:00' HOUR TO SECOND);

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB005', 'SBD002', 'SBD003', 'HAN-VCL', 9800, 1, INTERVAL '01:30:00' HOUR TO SECOND);

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB006', 'SBD002', 'SBD005', 'HAN-DAD', 6700, 1, INTERVAL '01:20:00' HOUR TO SECOND);

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB007', 'SBD002', 'SBD006', 'HAN-UIH', 9800, 1, INTERVAL '01:50:00' HOUR TO SECOND);

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB008', 'SBD002', 'SBD004', 'HAN-PQC', 1250, 1, INTERVAL '02:05:00' HOUR TO SECOND); -- Hanoi to Phu Quoc

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB009', 'SBD002', 'SBD007', 'HAN-PXU', 760, 1, INTERVAL '01:30:00' HOUR TO SECOND); -- Hanoi to Pleiku

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB010', 'SBD002', 'SBD010', 'HAN-VII', 290, 1, INTERVAL '01:20:00' HOUR TO SECOND); -- Hanoi to Vinh

-- Routes from Ho Chi Minh City (Tan Son Nhat - SGN)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB011', 'SBD001', 'SBD002', 'SGN-HAN', 1150, 1, INTERVAL '01:55:00' HOUR TO SECOND); -- Ho Chi Minh City to Hanoi

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB012', 'SBD001', 'SBD005', 'SGN-DAD', 610, 1, INTERVAL '01:25:00' HOUR TO SECOND); -- Ho Chi Minh City to Da Nang

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB013', 'SBD001', 'SBD004', 'SGN-PQC', 300, 1, INTERVAL '01:30:00' HOUR TO SECOND); -- Ho Chi Minh City to Phu Quoc

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB014', 'SBD001', 'SBD006', 'SGN-UIH', 440, 1, INTERVAL '01:35:00' HOUR TO SECOND); -- Ho Chi Minh City to Quy Nhon

-- Routes from Da Nang (Da Nang - DAD)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB015', 'SBD005', 'SBD002', 'DAD-HAN', 630, 1, INTERVAL '01:35:00' HOUR TO SECOND); -- Da Nang to Hanoi

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB016', 'SBD005', 'SBD001', 'DAD-SGN', 610, 1, INTERVAL '01:30:00' HOUR TO SECOND); -- Da Nang to Ho Chi Minh City

-- Routes from Phu Quoc (Phu Quoc - PQC)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB017', 'SBD004', 'SBD002', 'PQC-HAN', 1250, 1, INTERVAL '02:05:00' HOUR TO SECOND); -- Phu Quoc to Hanoi

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB018', 'SBD004', 'SBD001', 'PQC-SGN', 300, 1, INTERVAL '01:10:00' HOUR TO SECOND); -- Phu Quoc to Ho Chi Minh City

-- Routes from Vinh (Vinh - VII)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB019', 'SBD010', 'SBD002', 'VII-HAN', 290, 1, INTERVAL '00:50:00' HOUR TO SECOND); -- Vinh to Hanoi

-- Routes from Can Tho (Can Tho - VCA)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB020', 'SBD014', 'SBD002', 'VCA-HAN', 1220, 1, INTERVAL '02:00:00' HOUR TO SECOND); -- Can Tho to Hanoi

-- Routes from Van Don (Van Don - VDO)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB021', 'SBD008', 'SBD001', 'VDO-SGN', 1270, 1, INTERVAL '02:05:00' HOUR TO SECOND); -- Van Don to Ho Chi Minh City

-- Routes from Hai Phong (Cat Bi - HPH)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB022', 'SBD009', 'SBD001', 'HPH-SGN', 1120, 1, INTERVAL '01:55:00' HOUR TO SECOND); -- Cat Bi to Ho Chi Minh

-- Routes from Hue (Phu Bai - HUI)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB023', 'SBD011', 'SBD002', 'HUI-HAN', 540, 1, INTERVAL '01:25:00' HOUR TO SECOND); -- Hue to Hanoi

-- Routes from Cam Ranh (Cam Ranh - CXR)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB024', 'SBD012', 'SBD001', 'CXR-SGN', 350, 1, INTERVAL '01:10:00' HOUR TO SECOND); -- Cam Ranh to Ho Chi Minh City

-- Routes from Lien Khuong (Lien Khuong - DLI)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB025', 'SBD013', 'SBD001', 'DLI-SGN', 250, 1, INTERVAL '01:00:00' HOUR TO SECOND); -- Lien Khuong to Ho Chi Minh City

-- Routes from Pleiku (Pleiku - PXU)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB026', 'SBD007', 'SBD001', 'PXU-SGN', 370, 1, INTERVAL '01:20:00' HOUR TO SECOND); -- Pleiku to Ho Chi Minh City

-- Thêm Đường bay quốc tế
-- Ho Chi Minh City (SGN) to Dallas-Fort Worth (DFW)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB050', 'SBD001', 'SBD057', 'SGN-DFW', 9683, 1, INTERVAL '16:20:00' HOUR TO SECOND); -- Ho Chi Minh City to Hoa Ky

-- Quang Nam (VCL) to Pusan (PUS)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB051', 'SBD003', 'SBD055', 'VCL-PUS', 2755, 1, INTERVAL '04:00:00' HOUR TO SECOND); -- Quang Nam to Han Quoc

-- Khanh Hoa (CXR) to Singapore (SIN)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB052', 'SBD012', 'SBD051', 'CXR-SIN', 3475, 1, INTERVAL '04:45:00' HOUR TO SECOND); -- Khanh Hoa to Singapore

-- Da Nang (DAD) to London (ABB)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB053', 'SBD005', 'SBD058', 'DAD-ABB', 6542, 1, INTERVAL '11:30:00' HOUR TO SECOND); -- Da Nang to Anh

-- Can Tho (VCA) to Bangkok (PKT)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB054', 'SBD014', 'SBD052', 'VCA-PKT', 1074, 1, INTERVAL '02:00:00' HOUR TO SECOND); -- Can Tho to Thai Lan

-- Hue (HUI) to Frankfurt (FRA)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB055', 'SBD011', 'SBD061', 'HUI-FRA', 4556, 1, INTERVAL '10:30:00' HOUR TO SECOND); -- Hue to Duc

-- Lam Dong (DLI) to Singapore (XSP)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay, trangthai, ThoiGianBay)
VALUES ('DB056', 'SBD013', 'SBD050', 'DLI-XSP', 20331, 1, INTERVAL '12:00:00' HOUR TO SECOND); -- Lam Dong to Singapore

--TABLE: SANBAYTRUNGGIAN
-- Thêm sân bay trung gian
INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung)
VALUES ('DB050', 'SBD059', 1, INTERVAL '0 1:30:00' DAY TO SECOND);

INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung)
VALUES ('DB051', 'SBD005', 1, INTERVAL '0 1:00:00' DAY TO SECOND);

INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung)
VALUES ('DB052', 'SBD001', 1, INTERVAL '0 00:30:00' DAY TO SECOND);

INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung)
VALUES ('DB054', 'SBD001', 1, INTERVAL '0 00:30:00' DAY TO SECOND);

INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung)
VALUES ('DB055', 'SBD002', 1, INTERVAL '0 1:00:00' DAY TO SECOND);

INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung)
VALUES ('DB055', 'SBD058', 2, INTERVAL '0 2:45:00' DAY TO SECOND);

INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung)
VALUES ('DB056', 'SBD001', 1, INTERVAL '0 0:50:00' DAY TO SECOND);

-- TABLE : CHUYENBAY

-- Past flights in 2023

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB001', 'DB001', TO_TIMESTAMP('2023-01-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB002', 'DB002', TO_TIMESTAMP('2023-02-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB003', 'DB003', TO_TIMESTAMP('2023-03-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB004', 'DB004', TO_TIMESTAMP('2023-04-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB005', 'DB005', TO_TIMESTAMP('2023-05-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-05-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB006', 'DB006', TO_TIMESTAMP('2023-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB007', 'DB007', TO_TIMESTAMP('2023-07-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB008', 'DB008', TO_TIMESTAMP('2023-08-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB009', 'DB009', TO_TIMESTAMP('2023-09-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB010', 'DB010', TO_TIMESTAMP('2023-10-10 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-11 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 900000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB011', 'DB011', TO_TIMESTAMP('2023-11-10 11:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-11 14:15:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 950000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB012', 'DB012', TO_TIMESTAMP('2024-12-10 16:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-12-11 19:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 1000000);

-- Future flights in 2024


INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB013', 'DB001', TO_TIMESTAMP('2024-07-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-07-02 12:30:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB014', 'DB002', TO_TIMESTAMP('2024-08-15 14:15:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-08-16 16:45:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB015', 'DB003', TO_TIMESTAMP('2024-09-20 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-09-21 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB016', 'DB001', TO_TIMESTAMP('2024-10-10 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-10-11 20:45:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB017', 'DB002', TO_TIMESTAMP('2024-11-25 06:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-11-26 09:00:00', 'YYYY-MM-DD HH24:MI:SS'),0, 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB018', 'DB003', TO_TIMESTAMP('2024-12-30 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-12-31 01:30:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB019', 'DB001', TO_TIMESTAMP('2024-06-15 07:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-06-16 10:15:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB020', 'DB008', TO_TIMESTAMP('2024-08-10 06:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-08-10 08:30:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1050000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB021', 'DB009', TO_TIMESTAMP('2024-09-15 13:15:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-09-15 15:45:00', 'YYYY-MM-DD HH24:MI:SS'),0, 1100000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB022', 'DB010', TO_TIMESTAMP('2024-10-20 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-10-20 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1150000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB023', 'DB011', TO_TIMESTAMP('2024-11-25 07:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-11-25 10:15:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1200000);



-- Thêm chuyến bay quốc tế
-- ĐÃ BAY
INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB040', 'DB050', TO_TIMESTAMP('2023-01-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-12 22:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 9500000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB041', 'DB051', TO_TIMESTAMP('2023-05-15 19:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-5-16 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 5000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB042', 'DB052', TO_TIMESTAMP('2023-07-31 7:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 4300000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB043', 'DB053', TO_TIMESTAMP('2024-01-10 9:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-01-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 8760000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB044', 'DB054', TO_TIMESTAMP('2023-12-12 22:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-12-13 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 2360000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB045', 'DB055', TO_TIMESTAMP('2024-04-23 18:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-04-24 6:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 7760000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB046', 'DB056', TO_TIMESTAMP('2023-05-17 23:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-05-18 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 3530000);

-- CHƯA BAY

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB047', 'DB050', TO_TIMESTAMP('2024-07-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-07-10 22:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 9500000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB048', 'DB051', TO_TIMESTAMP('2024-09-15 19:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-09-16 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 5000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB049', 'DB052', TO_TIMESTAMP('2024-07-31 7:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 4300000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB050', 'DB053', TO_TIMESTAMP('2024-08-10 9:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-08-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 8760000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB051', 'DB054', TO_TIMESTAMP('2024-12-12 22:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-12-13 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 2360000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB052', 'DB055', TO_TIMESTAMP('2024-07-23 18:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-07-24 6:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 7760000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB053', 'DB056', TO_TIMESTAMP('2024-10-17 23:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-10-18 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 3530000);



-- TABLE : HANGVE

INSERT INTO HANGVE (MaHangVe, TenHangVe, HeSo, TrangThai)
VALUES ('HV001', 'Phổ Thông', 1, 1);

INSERT INTO HANGVE (MaHangVe, TenHangVe, HeSo, TrangThai)
VALUES ('HV002', 'Phòng Đặc Biệt', 1.5, 1);

INSERT INTO HANGVE (MaHangVe, TenHangVe, HeSo, TrangThai)
VALUES ('HV003', 'Thương Gia', 2.0, 1);

INSERT INTO HANGVE (MaHangVe, TenHangVe, HeSo, TrangThai)
VALUES ('HV004', 'Hạng Nhất', 2.5,  1);

INSERT INTO HANGVE (MaHangVe, TenHangVe, HeSo, TrangThai)
VALUES ('HV005', 'Ghế Trẻ Em', 0.5,  1);

INSERT INTO HANGVE (MaHangVe, TenHangVe, HeSo, TrangThai)
VALUES ('HV006', 'Ghế Phụ', 0.2,  1);



-- TABLE : VE

--Past
-- Tickets for flight CB001
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0001', 'CB001', 'HV001', 1, 850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0002', 'CB001', 'HV002', 2, 1275000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0003', 'CB001', 'HV001', 3, 850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0004', 'CB001', 'HV003', 4, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0005', 'CB001', 'HV001', 5, 850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0006', 'CB001', 'HV004', 6, 2125000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0007', 'CB003', 'HV001', 7, 850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0008', 'CB003', 'HV005', 8, 425000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0009', 'CB003', 'HV001', 9, 850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0010', 'CB003', 'HV006', 10, 170000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0011', 'CB002', 'HV001', 11, 850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0012', 'CB002', 'HV002', 12, 1275000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0013', 'CB002', 'HV001', 13, 850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0014', 'CB002', 'HV003', 14, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0015', 'CB002', 'HV001', 15, 850000);

-- Tickets for flight CB002
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0016', 'CB002', 'HV004', 6, 2250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0017', 'CB002', 'HV001', 7, 900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0018', 'CB002', 'HV005', 8, 450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0019', 'CB003', 'HV001', 9, 900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0020', 'CB003', 'HV006', 10, 180000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0021', 'CB003', 'HV001', 11, 900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0022', 'CB005', 'HV002', 12, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0023', 'CB005', 'HV001', 13, 900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0024', 'CB005', 'HV003', 14, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0025', 'CB007', 'HV001', 15, 900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0026', 'CB007', 'HV001', 1, 900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0027', 'CB007', 'HV002', 2, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0028', 'CB009', 'HV001', 3, 900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0029', 'CB009', 'HV003', 4, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0030', 'CB009', 'HV001', 5, 900000);


-- Tickets for flight CB003
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0031', 'CB003', 'HV001', 1, 950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0032', 'CB003', 'HV002', 2, 1425000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0033', 'CB003', 'HV001', 3, 950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0034', 'CB010', 'HV003', 4, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0035', 'CB010', 'HV001', 5, 950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0036', 'CB010', 'HV004', 6, 2375000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0037', 'CB010', 'HV001', 7, 950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0038', 'CB010', 'HV005', 8, 475000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0039', 'CB004', 'HV001', 9, 950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0040', 'CB004', 'HV006', 10, 190000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0041', 'CB004', 'HV001', 11, 950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0042', 'CB004', 'HV002', 12, 1425000);


-- Tickets for flight CB004
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0043', 'CB004', 'HV001', 1, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0044', 'CB004', 'HV002', 2, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0045', 'CB004', 'HV001', 3, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0046', 'CB012', 'HV003', 4, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0047', 'CB012', 'HV001', 5, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0048', 'CB012', 'HV004', 6, 2500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0049', 'CB012', 'HV001', 7, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0050', 'CB012', 'HV005', 8, 500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0051', 'CB011', 'HV001', 9, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0052', 'CB011', 'HV006', 10, 200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0053', 'CB011', 'HV001', 11, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0054', 'CB011', 'HV002', 12, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0055', 'CB011', 'HV001', 13, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0056', 'CB011', 'HV003', 14, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0057', 'CB011', 'HV001', 15, 1000000);



-- Tickets for flight CB005
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0058', 'CB005', 'HV001', 1, 1050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0059', 'CB005', 'HV002', 2, 1575000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0060', 'CB005', 'HV001', 3, 1050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0061', 'CB005', 'HV003', 4, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0062', 'CB005', 'HV001', 5, 1050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0063', 'CB007', 'HV004', 6, 2625000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0064', 'CB007', 'HV001', 7, 1050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0065', 'CB007', 'HV005', 8, 525000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0066', 'CB007', 'HV001', 9, 1050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0067', 'CB009', 'HV006', 10, 210000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0068', 'CB009', 'HV001', 11, 1050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0069', 'CB009', 'HV002', 12, 1575000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0070', 'CB009', 'HV001', 13, 1050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0071', 'CB009', 'HV003', 14, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0072', 'CB009', 'HV001', 15, 1050000);


-- Tickets for flight CB006
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0073', 'CB006', 'HV001', 1, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0074', 'CB006', 'HV002', 2, 1650000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0075', 'CB006', 'HV001', 3, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0076', 'CB006', 'HV003', 4, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0077', 'CB006', 'HV001', 5, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0078', 'CB006', 'HV004', 6, 2750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0079', 'CB006', 'HV001', 7, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0080', 'CB006', 'HV005', 8, 550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0081', 'CB006', 'HV001', 9, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0082', 'CB006', 'HV006', 10, 220000);


-- Tickets for flight CB007
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0083', 'CB007', 'HV003', 1, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0084', 'CB007', 'HV002', 2, 2400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0085', 'CB007', 'HV005', 3, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0086', 'CB007', 'HV001', 4, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0087', 'CB008', 'HV004', 5, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0088', 'CB008', 'HV006', 6, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0089', 'CB008', 'HV002', 7, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0090', 'CB008', 'HV003', 8, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0091', 'CB009', 'HV001', 9, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0092', 'CB009', 'HV005', 10, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0093', 'CB009', 'HV006', 11, 2500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0094', 'CB009', 'HV004', 12, 2300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0095', 'CB011', 'HV003', 13, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0096', 'CB011', 'HV002', 14, 1750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0097', 'CB011', 'HV001', 15, 2100000);


-- Tickets for flight CB008
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0098', 'CB008', 'HV002', 1, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0099', 'CB008', 'HV004', 2, 2250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0100', 'CB008', 'HV006', 3, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0101', 'CB008', 'HV003', 4, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0102', 'CB008', 'HV005', 5, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0103', 'CB011', 'HV001', 6, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0104', 'CB011', 'HV002', 7, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0105', 'CB011', 'HV004', 8, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0106', 'CB011', 'HV001', 9, 1150000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0107', 'CB012', 'HV006', 10, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0108', 'CB012', 'HV003', 11, 1850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0109', 'CB012', 'HV002', 12, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0110', 'CB012', 'HV005', 13, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0111', 'CB012', 'HV004', 14, 2350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0112', 'CB012', 'HV001', 15, 2000000);


-- Tickets for flight CB009
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0113', 'CB009', 'HV004', 1, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0114', 'CB009', 'HV002', 2, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0115', 'CB009', 'HV003', 3, 2300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0116', 'CB009', 'HV001', 4, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0117', 'CB011', 'HV005', 5, 1200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0118', 'CB011', 'HV006', 6, 1750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0119', 'CB011', 'HV001', 7, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0120', 'CB011', 'HV004', 8, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0121', 'CB011', 'HV002', 9, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0122', 'CB011', 'HV003', 10, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0123', 'CB011', 'HV005', 11, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0124', 'CB011', 'HV006', 12, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0125', 'CB012', 'HV001', 13, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0126', 'CB012', 'HV002', 14, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0127', 'CB012', 'HV004', 15, 2200000);

-- Tickets for flight CB010
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0128', 'CB010', 'HV005', 1, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0129', 'CB010', 'HV001', 2, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0130', 'CB010', 'HV003', 3, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0131', 'CB010', 'HV002', 4, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0132', 'CB010', 'HV006', 5, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0133', 'CB010', 'HV004', 6, 2050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0134', 'CB010', 'HV005', 7, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0135', 'CB010', 'HV001', 8, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0136', 'CB010', 'HV003', 9, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0137', 'CB010', 'HV004', 10, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0138', 'CB010', 'HV002', 11, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0139', 'CB010', 'HV006', 12, 1750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0140', 'CB010', 'HV005', 13, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0141', 'CB010', 'HV001', 14, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0142', 'CB010', 'HV003', 15, 2000000);



-- Tickets for flight CB011
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0143', 'CB011', 'HV002', 1, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0144', 'CB011', 'HV005', 2, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0145', 'CB011', 'HV003', 3, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0146', 'CB011', 'HV004', 4, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0147', 'CB011', 'HV006', 5, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0148', 'CB011', 'HV001', 6, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0149', 'CB011', 'HV002', 7, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0150', 'CB011', 'HV005', 8, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0151', 'CB011', 'HV003', 9, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0152', 'CB011', 'HV001', 10, 1200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0153', 'CB011', 'HV004', 11, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0154', 'CB011', 'HV006', 12, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0155', 'CB011', 'HV002', 13, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0156', 'CB011', 'HV003', 14, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0157', 'CB011', 'HV001', 15, 1100000);


-- Tickets for flight CB012

INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0158', 'CB012', 'HV002', 1, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0159', 'CB012', 'HV004', 2, 2250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0160', 'CB012', 'HV003', 3, 2050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0161', 'CB012', 'HV005', 4, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0162', 'CB012', 'HV001', 5, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0163', 'CB012', 'HV002', 6, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0164', 'CB012', 'HV006', 7, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0165', 'CB012', 'HV003', 8, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0166', 'CB012', 'HV005', 9, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0167', 'CB012', 'HV001', 10, 1050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0168', 'CB012', 'HV002', 11, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0169', 'CB012', 'HV004', 12, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0170', 'CB012', 'HV006', 13, 1850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0171', 'CB012', 'HV003', 14, 2050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0172', 'CB012', 'HV001', 15, 1200000);


--Future
-- Tickets for flight CB013
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0173', 'CB013', 'HV001', 1, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0174', 'CB013', 'HV002', 2, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0175', 'CB013', 'HV003', 3, 2250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0176', 'CB013', 'HV004', 4, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0177', 'CB013', 'HV005', 5, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0178', 'CB013', 'HV006', 6, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0179', 'CB013', 'HV001', 7, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0180', 'CB013', 'HV002', 8, 2050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0181', 'CB013', 'HV003', 9, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0182', 'CB013', 'HV004', 10, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0183', 'CB013', 'HV005', 11, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0184', 'CB013', 'HV006', 12, 1750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0185', 'CB013', 'HV001', 13, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0186', 'CB013', 'HV002', 14, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0187', 'CB013', 'HV003', 15, 1950000);


-- Tickets for flight CB014

INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0188', 'CB014', 'HV001', 1, 1200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0189', 'CB014', 'HV002', 2, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0190', 'CB014', 'HV003', 3, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0191', 'CB014', 'HV004', 4, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0192', 'CB014', 'HV005', 5, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0193', 'CB014', 'HV006', 6, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0194', 'CB014', 'HV001', 7, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0195', 'CB014', 'HV002', 8, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0196', 'CB014', 'HV003', 9, 1850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0197', 'CB014', 'HV004', 10, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0198', 'CB014', 'HV005', 11, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0199', 'CB014', 'HV006', 12, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0200', 'CB014', 'HV001', 13, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0201', 'CB014', 'HV002', 14, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0202', 'CB014', 'HV003', 15, 1350000);


-- Tickets for flight CB015
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0203', 'CB015', 'HV001', 1, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0204', 'CB015', 'HV002', 2, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0205', 'CB015', 'HV003', 3, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0206', 'CB015', 'HV004', 4, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0207', 'CB015', 'HV005', 5, 1750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0208', 'CB015', 'HV006', 6, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0209', 'CB015', 'HV001', 7, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0210', 'CB015', 'HV002', 8, 2050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0211', 'CB015', 'HV003', 9, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0212', 'CB015', 'HV004', 10, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0213', 'CB015', 'HV005', 11, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0214', 'CB015', 'HV006', 12, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0215', 'CB015', 'HV001', 13, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0216', 'CB015', 'HV002', 14, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0217', 'CB015', 'HV003', 15, 1500000);


-- Tickets for flight CB016

INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0218', 'CB016', 'HV001', 1, 2150000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0219', 'CB016', 'HV002', 2, 1480000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0220', 'CB016', 'HV003', 3, 2300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0221', 'CB016', 'HV004', 4, 1320000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0222', 'CB016', 'HV005', 5, 1980000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0223', 'CB016', 'HV006', 6, 1830000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0224', 'CB016', 'HV001', 7, 1520000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0225', 'CB016', 'HV002', 8, 2080000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0226', 'CB016', 'HV003', 9, 1380000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0227', 'CB016', 'HV004', 10, 2230000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0228', 'CB016', 'HV005', 11, 1930000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0229', 'CB016', 'HV006', 12, 1780000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0230', 'CB016', 'HV001', 13, 1130000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0231', 'CB016', 'HV002', 14, 1630000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0232', 'CB016', 'HV003', 15, 1980000);



-- Tickets for flight CB017
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0233', 'CB017', 'HV004', 1, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0234', 'CB017', 'HV003', 2, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0235', 'CB017', 'HV006', 3, 1780000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0236', 'CB017', 'HV002', 4, 1490000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0237', 'CB017', 'HV005', 5, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0238', 'CB017', 'HV001', 6, 1880000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0239', 'CB017', 'HV004', 7, 1420000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0240', 'CB017', 'HV002', 8, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0241', 'CB017', 'HV006', 9, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0242', 'CB017', 'HV005', 10, 1750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0243', 'CB017', 'HV001', 11, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0244', 'CB017', 'HV003', 12, 2300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0245', 'CB017', 'HV002', 13, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0246', 'CB017', 'HV004', 14, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0247', 'CB017', 'HV003', 15, 1600000);


-- Tickets for flight CB018
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0248', 'CB018', 'HV001', 1, 1850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0249', 'CB018', 'HV002', 2, 1470000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0250', 'CB018', 'HV003', 3, 1650000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0251', 'CB018', 'HV004', 4, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0252', 'CB018', 'HV005', 5, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0253', 'CB018', 'HV006', 6, 1750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0254', 'CB018', 'HV001', 7, 2150000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0255', 'CB018', 'HV002', 8, 1380000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0256', 'CB018', 'HV003', 9, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0257', 'CB018', 'HV004', 10, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0258', 'CB018', 'HV005', 11, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0259', 'CB018', 'HV006', 12, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0260', 'CB018', 'HV001', 13, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0261', 'CB018', 'HV002', 14, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0262', 'CB018', 'HV003', 15, 1900000);


-- Tickets for flight CB019
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0263', 'CB019', 'HV001', 1, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0264', 'CB019', 'HV002', 2, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0265', 'CB019', 'HV003', 3, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0266', 'CB019', 'HV004', 4, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0267', 'CB019', 'HV005', 5, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0268', 'CB019', 'HV006', 6, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0269', 'CB019', 'HV001', 7, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0270', 'CB019', 'HV002', 8, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0271', 'CB019', 'HV003', 9, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0272', 'CB019', 'HV004', 10, 2300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0273', 'CB019', 'HV005', 11, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0274', 'CB019', 'HV006', 12, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0275', 'CB019', 'HV001', 13, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0276', 'CB019', 'HV002', 14, 2150000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0277', 'CB019', 'HV003', 15, 1800000);

-- Tickets for flight CB020
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0278', 'CB020', 'HV001', 1, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0279', 'CB020', 'HV002', 2, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0280', 'CB020', 'HV003', 3, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0281', 'CB020', 'HV004', 4, 2300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0282', 'CB020', 'HV005', 5, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0283', 'CB020', 'HV006', 6, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0284', 'CB020', 'HV001', 7, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0285', 'CB020', 'HV002', 8, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0286', 'CB020', 'HV003', 9, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0287', 'CB020', 'HV004', 10, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0288', 'CB020', 'HV005', 11, 1850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0289', 'CB020', 'HV006', 12, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0290', 'CB020', 'HV001', 13, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0291', 'CB020', 'HV002', 14, 1650000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0292', 'CB020', 'HV003', 15, 1750000);

-- Tickets for flight CB021
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0293', 'CB021', 'HV004', 1, 1750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0294', 'CB021', 'HV002', 2, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0295', 'CB021', 'HV006', 3, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0296', 'CB021', 'HV001', 4, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0297', 'CB021', 'HV005', 5, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0298', 'CB021', 'HV003', 6, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0299', 'CB021', 'HV004', 7, 1650000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0300', 'CB021', 'HV002', 8, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0301', 'CB021', 'HV001', 9, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0302', 'CB021', 'HV006', 10, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0303', 'CB021', 'HV005', 11, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0304', 'CB021', 'HV003', 12, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0305', 'CB021', 'HV004', 13, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0306', 'CB021', 'HV002', 14, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0307', 'CB021', 'HV001', 15, 1850000);

-- Tickets for flight CB022
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0308', 'CB022', 'HV003', 1, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0309', 'CB022', 'HV005', 2, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0310', 'CB022', 'HV002', 3, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0311', 'CB022', 'HV006', 4, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0312', 'CB022', 'HV001', 5, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0313', 'CB022', 'HV004', 6, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0314', 'CB022', 'HV003', 7, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0315', 'CB022', 'HV005', 8, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0316', 'CB022', 'HV002', 9, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0317', 'CB022', 'HV001', 10, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0318', 'CB022', 'HV006', 11, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0319', 'CB022', 'HV004', 12, 1850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0320', 'CB022', 'HV003', 13, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0321', 'CB022', 'HV005', 14, 2300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0322', 'CB022', 'HV002', 15, 1750000);

-- Tickets for flight CB023
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0323', 'CB023', 'HV005', 1, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0324', 'CB023', 'HV003', 2, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0325', 'CB023', 'HV002', 3, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0326', 'CB023', 'HV001', 4, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0327', 'CB023', 'HV006', 5, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0328', 'CB023', 'HV004', 6, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0329', 'CB023', 'HV003', 7, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0330', 'CB023', 'HV005', 8, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0331', 'CB023', 'HV002', 9, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0332', 'CB023', 'HV001', 10, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0333', 'CB023', 'HV006', 11, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0334', 'CB023', 'HV004', 12, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0335', 'CB023', 'HV003', 13, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0336', 'CB023', 'HV005', 14, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0337', 'CB023', 'HV002', 15, 1850000);


--TRINH Thêm
-- Chuyến bay CB047
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE04701', 'CB047', 'HV001', 1, 9500000 * 1);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE04702', 'CB047', 'HV001', 2, 9500000 * 1);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE04703', 'CB047', 'HV002', 3, 9500000 * 1.5);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE04704', 'CB047', 'HV003', 4, 9500000 * 2);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE04705', 'CB047', 'HV004', 5, 9500000 * 2.5);

-- Chuyến bay CB048
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE04801', 'CB048', 'HV001', 1, 5000000 * 1);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE04802', 'CB048', 'HV002', 2, 5000000 * 1.5);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE04803', 'CB048', 'HV003', 3, 5000000 * 2);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE04804', 'CB048', 'HV004', 4, 5000000 * 2.5);

-- Chuyến bay CB049
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE04901', 'CB049', 'HV001', 1, 4300000 * 1);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE04902', 'CB049', 'HV002', 2, 4300000 * 1.5);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE04903', 'CB049', 'HV003', 3, 4300000 * 2);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE04904', 'CB049', 'HV004', 4, 4300000 * 2.5);

-- Chuyến bay CB050
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE05001', 'CB050', 'HV001', 1, 8760000 * 1);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE05002', 'CB050', 'HV002', 2, 8760000 * 1.5);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE05003', 'CB050', 'HV003', 3, 8760000 * 2);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE05004', 'CB050', 'HV004', 4, 8760000 * 2.5);

-- Chuyến bay CB051
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE05101', 'CB051', 'HV001', 1, 2360000 * 1);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE05102', 'CB051', 'HV002', 2, 2360000 * 1.5);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE05103', 'CB051', 'HV003', 3, 2360000 * 2);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE05104', 'CB051', 'HV004', 4, 2360000 * 2.5);

-- Chuyến bay CB052
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE05201', 'CB052', 'HV001', 1, 7760000 * 1);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE05202', 'CB052', 'HV002', 2, 7760000 * 1.5);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE05203', 'CB052', 'HV003', 3, 7760000 * 2);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE05204', 'CB052', 'HV004', 4, 7760000 * 2.5);

-- Chuyến bay CB053
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE05301', 'CB053', 'HV001', 1, 3530000 * 1);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE05302', 'CB053', 'HV002', 2, 3530000 * 1.5);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE05303', 'CB053', 'HV003', 3, 3530000 * 2);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE05304', 'CB053', 'HV004', 4, 3530000 * 2.5);


-- TABLE : KHACHHANG

INSERT INTO KHACHHANG (MaKhachHang, HoTen, CCCD, SDT, Email, DiaChi)
VALUES ('KH001', 'Nguyễn Văn A', 123456789, 987654321, 'nguyenvana@example.com', '123 Đường ABC, Quận 1, TP.HCM');

INSERT INTO KHACHHANG (MaKhachHang, HoTen, CCCD, SDT, Email, DiaChi)
VALUES ('KH002', 'Trần Thị B', 987654321, 123456789, 'tranthib@example.com', '456 Đường XYZ, Quận 2, TP.HCM');

INSERT INTO KHACHHANG (MaKhachHang, HoTen, CCCD, SDT, Email, DiaChi)
VALUES ('KH003', 'Lê Văn C', 654321987, 789123456, 'levanc@example.com', '789 Đường KLM, Quận 3, TP.HCM');

INSERT INTO KHACHHANG (MaKhachHang, HoTen, CCCD, SDT, Email, DiaChi)
VALUES ('KH004', 'Phạm Thị D', 321987654, 321789456, 'phamthid@example.com', '987 Đường XYZ, Quận 4, TP.HCM');

INSERT INTO KHACHHANG (MaKhachHang, HoTen, CCCD, SDT, Email, DiaChi)
VALUES ('KH005', 'Hoàng Văn E', 987123654, 456789123, 'hoangvane@example.com', '456 Đường ABC, Quận 5, TP.HCM');

INSERT INTO KHACHHANG (MaKhachHang, HoTen, CCCD, SDT, Email, DiaChi)
VALUES ('KH006', 'Trần Văn F', 456321789, 987123654, 'tranvanf@example.com', '789 Đường KLM, Quận 6, TP.HCM');

INSERT INTO KHACHHANG (MaKhachHang, HoTen, CCCD, SDT, Email, DiaChi)
VALUES ('KH007', 'Nguyễn Thị G', 789654123, 321987654, 'nguyenthig@example.com', '123 Đường XYZ, Quận 7, TP.HCM');


-- TABLE : CT_HANGVE

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB001', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB001', 'HV002', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB001', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB001', 'HV004', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB001', 'HV005', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB001', 'HV006', 15, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB002', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB002', 'HV002', 11, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB002', 'HV003', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB002', 'HV004', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB002', 'HV005', 14, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB002', 'HV006', 20, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB003', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB003', 'HV002', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB003', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB003', 'HV004', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB003', 'HV005', 14, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB003', 'HV006', 20, 0);


INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB004', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB004', 'HV002', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB004', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB004', 'HV004', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB004', 'HV005', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB004', 'HV006', 11, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB005', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB005', 'HV002', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB005', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB005', 'HV004', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB005', 'HV005', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB005', 'HV006', 20, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB006', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB006', 'HV002', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB006', 'HV003', 11, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB006', 'HV004', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB006', 'HV005', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB006', 'HV006', 15, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB007', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB007', 'HV002', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB007', 'HV003', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB007', 'HV004', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB007', 'HV005', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB007', 'HV006', 20, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB008', 'HV001', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB008', 'HV002', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB008', 'HV003', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB008', 'HV004', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB008', 'HV005', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB008', 'HV006', 20, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB009', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB009', 'HV002', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB009', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB009', 'HV004', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB009', 'HV005', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB009', 'HV006', 20, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB010', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB010', 'HV002', 14, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB010', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB010', 'HV004', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB010', 'HV005', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB010', 'HV006', 20, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB011', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB011', 'HV002', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB011', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB011', 'HV004', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB011', 'HV005', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB011', 'HV006', 15, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB012', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB012', 'HV002', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB012', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB012', 'HV004', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB012', 'HV005', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB012', 'HV006', 15, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB013', 'HV001', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB013', 'HV002', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB013', 'HV003', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB013', 'HV004', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB013', 'HV005', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB013', 'HV006', 20, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB014', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB014', 'HV002', 14, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB014', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB014', 'HV004', 14, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB014', 'HV005', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB014', 'HV006', 14, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB015', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB015', 'HV002', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB015', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB015', 'HV004', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB015', 'HV005', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB015', 'HV006', 15, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB016', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB016', 'HV002', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB016', 'HV003', 11, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB016', 'HV004', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB016', 'HV005', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB016', 'HV006', 20, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB017', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB017', 'HV002', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB017', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB017', 'HV004', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB017', 'HV005', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB017', 'HV006', 14, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB018', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB018', 'HV002', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB018', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB018', 'HV004', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB018', 'HV005', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB018', 'HV006', 15, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB019', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB019', 'HV002', 11, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB019', 'HV003', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB019', 'HV004', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB019', 'HV005', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB019', 'HV006', 15, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB020', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB020', 'HV002', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB020', 'HV003', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB020', 'HV004', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB020', 'HV005', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB020', 'HV006', 11, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB021', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB021', 'HV002', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB021', 'HV003', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB021', 'HV004', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB021', 'HV005', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB021', 'HV006', 15, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB022', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB022', 'HV002', 11, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB022', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB022', 'HV004', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB022', 'HV005', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB022', 'HV006', 15, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB023', 'HV001', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB023', 'HV002', 14, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB023', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB023', 'HV004', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB023', 'HV005', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB023', 'HV006', 15, 0);

-- TRINH Thêm
-- Chuyến bay CB047
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB047', 'HV001', 30, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB047', 'HV002', 25, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB047', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB047', 'HV004', 20, 0);

-- Chuyến bay CB048
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB048', 'HV001', 30, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB048', 'HV002', 25, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB048', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB048', 'HV004', 20, 0);

-- Chuyến bay CB049
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB049', 'HV001', 30, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB049', 'HV002', 25, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB049', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB049', 'HV004', 20, 0);

-- Chuyến bay CB050
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB050', 'HV001', 30, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB050', 'HV002', 25, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB050', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB050', 'HV004', 20, 0);

-- Chuyến bay CB051
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB051', 'HV001', 30, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB051', 'HV002', 25, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB051', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB051', 'HV004', 20, 0);

-- Chuyến bay CB052
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB052', 'HV001', 30, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB052', 'HV002', 25, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB052', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB052', 'HV004', 20, 0);

-- Chuyến bay CB053
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB053', 'HV001', 30, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB053', 'HV002', 25, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB053', 'HV003', 20, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat) VALUES ('CB053', 'HV004', 20, 0);

INSERT INTO QUYEN VALUES ('RL0001', N'Admin');
INSERT INTO QUYEN VALUES ('RL0002', N'Manager');
INSERT INTO QUYEN VALUES ('RL0003', N'Staff');


INSERT INTO TAIKHOAN VALUES ('1', N'Nguyễn Thị Trinh', '023455678', 'trinhnt@gmail.com', 'trinhnt', TO_DATE('1/1/2024', 'DD/MM/YYYY'), 'RL0001');
INSERT INTO TAIKHOAN VALUES('2', N'Nguyễn Bá Hưng', '087654321', 'hungnb@gmail.com', 'hungnb', TO_DATE('2/1/2024', 'DD/MM/YYYY'), 'RL0001');
INSERT INTO TAIKHOAN VALUES('3', N'Đào Trung Hiếu', '034669633', 'hieudt@gmail.com', 'hieudt', TO_DATE('3/1/2024', 'DD/MM/YYYY'), 'RL0002');
INSERT INTO TAIKHOAN VALUES ('4', N'Trần Đức Hùng', '0378872312', 'hungtd@gmail.com', 'hungtd', TO_DATE('30/04/2024', 'DD/MM/YYYY'), 'RL0002');
INSERT INTO TAIKHOAN VALUES ('5', N'Mai Văn Tân', '0378872334', 'tanmv@gmail.com', 'tanmv', TO_DATE('1/05/2024', 'DD/MM/YYYY'), 'RL0003');
INSERT INTO TAIKHOAN VALUES ('6', N'Huỳnh Trung', '0378872356', 'trungh@gmail.com', 'trungh', TO_DATE('2/05/2024', 'DD/MM/YYYY'), 'RL0003');


-- TABLE : THAMSO

INSERT INTO THAMSO (MaThuocTinh, TenThuocTinh, GiaTri) VALUES ('TGBTT', 'ThoiGianBayToiThieu', 1);
INSERT INTO THAMSO (MaThuocTinh, TenThuocTinh, GiaTri) VALUES ('TGDTT', 'ThoiGianDungToiThieu', 10);
INSERT INTO THAMSO (MaThuocTinh, TenThuocTinh, GiaTri) VALUES ('TGDTD', 'ThoiGianDungToiDa', 20);
INSERT INTO THAMSO (MaThuocTinh, TenThuocTinh, GiaTri) VALUES ('TGTTDV', 'ThoiGianToiThieuDatVe', 24);
INSERT INTO THAMSO (MaThuocTinh, TenThuocTinh, GiaTri) VALUES ('TGTT_HV', 'ThoiGianToiThieuHuyVe', 24);

-- ----------------------------------------------------------------------------------------------------------------------------------------------------------
--CHẠY TRIGGER VÀ PROC RỒI MỚI CHẠY CT_DATVE

-- TABLE : CT_DATVE
--past



INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV001', 'VE0001', 'KH001',TO_TIMESTAMP('2023-01-01 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-02 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV002', 'VE0002', 'KH001',TO_TIMESTAMP('2023-01-02 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-03 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV003', 'VE0003', 'KH001',TO_TIMESTAMP('2023-01-03 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-04 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV004', 'VE0004', 'KH001',TO_TIMESTAMP('2023-01-04 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-05 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV005', 'VE0005', 'KH001',TO_TIMESTAMP('2023-01-05 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-06 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV006', 'VE0006', 'KH001',TO_TIMESTAMP('2023-01-06 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-07 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV007', 'VE0007', 'KH001',TO_TIMESTAMP('2023-01-07 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-08 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV008', 'VE0008', 'KH001',TO_TIMESTAMP('2023-01-08 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-09 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV009', 'VE0009', 'KH001',TO_TIMESTAMP('2023-01-08 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-09 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV0010', 'VE0010', 'KH001',TO_TIMESTAMP('2023-01-08 3:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-09 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV011', 'VE0011', 'KH001',TO_TIMESTAMP('2023-01-02 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-03 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV012', 'VE0012', 'KH001',TO_TIMESTAMP('2023-01-03 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-04 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV013', 'VE0013', 'KH001',TO_TIMESTAMP('2023-01-04 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-05 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV014', 'VE0014', 'KH001',TO_TIMESTAMP('2023-01-06 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-07 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV015', 'VE0015', 'KH001',TO_TIMESTAMP('2023-01-07 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-08 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);


INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV016', 'VE0016', 'KH002', TO_TIMESTAMP('2023-02-01 14:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-02 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV017', 'VE0017', 'KH002', TO_TIMESTAMP('2023-02-02 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-03 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV018', 'VE0018', 'KH002', TO_TIMESTAMP('2023-02-03 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-04 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV019', 'VE0019', 'KH002', TO_TIMESTAMP('2023-02-04 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-05 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV020', 'VE0020', 'KH002', TO_TIMESTAMP('2023-02-05 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-06 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV021', 'VE0021', 'KH002', TO_TIMESTAMP('2023-02-06 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-07 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV022', 'VE0022', 'KH002', TO_TIMESTAMP('2023-02-07 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-08 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV023', 'VE0023', 'KH002', TO_TIMESTAMP('2023-02-08 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-09 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV024', 'VE0024', 'KH002', TO_TIMESTAMP('2023-02-08 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-09 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV025', 'VE0025', 'KH002', TO_TIMESTAMP('2023-02-01 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-02 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV026', 'VE0026', 'KH002', TO_TIMESTAMP('2023-02-03 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-04 16:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV027', 'VE0027', 'KH002', TO_TIMESTAMP('2023-02-05 14:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-06 13:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV028', 'VE0028', 'KH002', TO_TIMESTAMP('2023-02-07 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-08 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV029', 'VE0029', 'KH002', TO_TIMESTAMP('2023-02-09 7:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-09 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV030', 'VE0030', 'KH002', TO_TIMESTAMP('2023-02-08 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-09 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);



INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV031', 'VE0031', 'KH002', TO_TIMESTAMP('2023-02-01 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-03 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV032', 'VE0032', 'KH002', TO_TIMESTAMP('2023-02-03 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-05 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV033', 'VE0033', 'KH002', TO_TIMESTAMP('2023-02-05 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-07 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV034', 'VE0034', 'KH002', TO_TIMESTAMP('2023-02-07 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-09 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV035', 'VE0035', 'KH002', TO_TIMESTAMP('2023-02-02 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-02 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV036', 'VE0036', 'KH002', TO_TIMESTAMP('2023-02-02 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-02 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV037', 'VE0037', 'KH002', TO_TIMESTAMP('2023-02-04 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-05 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV038', 'VE0038', 'KH002', TO_TIMESTAMP('2023-02-05 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-06 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV039', 'VE0039', 'KH002', TO_TIMESTAMP('2023-02-06 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-07 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV040', 'VE0040', 'KH002', TO_TIMESTAMP('2023-02-07 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-08 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV041', 'VE0041', 'KH002', TO_TIMESTAMP('2023-02-08 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-09 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV042', 'VE0041', 'KH002', TO_TIMESTAMP('2023-02-09 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-09 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);




INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV043', 'VE0043', 'KH002', TO_TIMESTAMP('2023-03-01 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-03 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV044', 'VE0044', 'KH002', TO_TIMESTAMP('2023-03-03 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-05 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV045', 'VE0045', 'KH002', TO_TIMESTAMP('2023-03-05 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-07 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV046', 'VE0046', 'KH002', TO_TIMESTAMP('2023-03-07 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-09 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV047', 'VE0047', 'KH002', TO_TIMESTAMP('2023-03-02 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-02 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV048', 'VE0048', 'KH002', TO_TIMESTAMP('2023-03-02 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-02 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV049', 'VE0049', 'KH002', TO_TIMESTAMP('2023-03-04 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-05 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV050', 'VE0050', 'KH002', TO_TIMESTAMP('2023-03-05 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-06 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV051', 'VE0051', 'KH002', TO_TIMESTAMP('2023-03-06 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-07 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV052', 'VE0052', 'KH002', TO_TIMESTAMP('2023-03-07 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-08 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV053', 'VE0053', 'KH002', TO_TIMESTAMP('2023-03-08 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-09 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV054', 'VE0054', 'KH002', TO_TIMESTAMP('2023-03-09 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-10 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV055', 'VE0055', 'KH002', TO_TIMESTAMP('2023-03-01 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-03 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV056', 'VE0056', 'KH002', TO_TIMESTAMP('2023-03-03 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-05 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV057', 'VE0057', 'KH002', TO_TIMESTAMP('2023-03-05 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-07 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);




INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV058', 'VE0058', 'KH002', TO_TIMESTAMP('2023-04-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-16 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV059', 'VE0059', 'KH002', TO_TIMESTAMP('2023-04-16 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-17 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV060', 'VE0060', 'KH002', TO_TIMESTAMP('2023-04-17 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-18 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV061', 'VE0061', 'KH002', TO_TIMESTAMP('2023-04-18 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-20 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV062', 'VE0062', 'KH002', TO_TIMESTAMP('2023-04-20 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-22 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV063', 'VE0063', 'KH002', TO_TIMESTAMP('2023-04-22 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-24 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV064', 'VE0064', 'KH002', TO_TIMESTAMP('2023-04-23 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-25 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV065', 'VE0065', 'KH002', TO_TIMESTAMP('2023-04-24 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-26 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV066', 'VE0066', 'KH002', TO_TIMESTAMP('2023-04-25 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-27 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV067', 'VE0067', 'KH002', TO_TIMESTAMP('2023-04-26 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-28 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV068', 'VE0068', 'KH002', TO_TIMESTAMP('2023-04-27 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-29 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV069', 'VE0069', 'KH002', TO_TIMESTAMP('2023-04-28 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-30 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV070', 'VE0070', 'KH002', TO_TIMESTAMP('2023-04-29 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-05-01 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV071', 'VE0071', 'KH002', TO_TIMESTAMP('2023-05-01 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-05-03 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV072', 'VE0072', 'KH002', TO_TIMESTAMP('2023-05-02 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-05-04 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);




INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV073', 'VE0073', 'KH002', TO_TIMESTAMP('2023-05-27 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-05-29 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV074', 'VE0074', 'KH002', TO_TIMESTAMP('2023-05-28 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-05-30 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV075', 'VE0075', 'KH002', TO_TIMESTAMP('2023-05-29 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-05-31 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV076', 'VE0076', 'KH002', TO_TIMESTAMP('2023-05-30 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-01 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV077', 'VE0077', 'KH002', TO_TIMESTAMP('2023-06-01 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-03 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV078', 'VE0078', 'KH002', TO_TIMESTAMP('2023-06-02 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-04 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV079', 'VE0079', 'KH002', TO_TIMESTAMP('2023-06-03 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-05 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV080', 'VE0080', 'KH002', TO_TIMESTAMP('2023-06-04 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-06 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV081', 'VE0081', 'KH002', TO_TIMESTAMP('2023-06-05 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-07 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV082', 'VE0082', 'KH002', TO_TIMESTAMP('2023-06-06 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-08 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);


INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV083', 'VE0083', 'KH002', TO_TIMESTAMP('2023-06-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-03 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV084', 'VE0084', 'KH002', TO_TIMESTAMP('2023-06-02 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-04 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV085', 'VE0085', 'KH002', TO_TIMESTAMP('2023-06-03 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-05 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV086', 'VE0086', 'KH002', TO_TIMESTAMP('2023-06-04 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-06 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV087', 'VE0087', 'KH002', TO_TIMESTAMP('2023-06-05 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-07 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV088', 'VE0088', 'KH002', TO_TIMESTAMP('2023-06-06 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-08 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV089', 'VE0089', 'KH002', TO_TIMESTAMP('2023-06-07 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-09 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV090', 'VE0090', 'KH002', TO_TIMESTAMP('2023-06-08 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-10 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV091', 'VE0091', 'KH002', TO_TIMESTAMP('2023-06-09 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-11 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV092', 'VE0092', 'KH002', TO_TIMESTAMP('2023-06-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-12 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV093', 'VE0093', 'KH002', TO_TIMESTAMP('2023-06-11 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-13 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV094', 'VE0094', 'KH002', TO_TIMESTAMP('2023-06-12 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-14 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV095', 'VE0095', 'KH002', TO_TIMESTAMP('2023-06-13 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-15 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV096', 'VE0096', 'KH002', TO_TIMESTAMP('2023-06-14 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-16 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV097', 'VE0097', 'KH002', TO_TIMESTAMP('2023-06-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-17 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);





INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV098', 'VE0098', 'KH002', TO_TIMESTAMP('2023-07-05 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-07 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV099', 'VE0099', 'KH002', TO_TIMESTAMP('2023-07-06 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-08 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV100', 'VE0100', 'KH002', TO_TIMESTAMP('2023-07-07 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-09 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV101', 'VE0101', 'KH002', TO_TIMESTAMP('2023-07-08 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-10 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV102', 'VE0102', 'KH002', TO_TIMESTAMP('2023-07-09 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-11 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV103', 'VE0103', 'KH002', TO_TIMESTAMP('2023-07-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-12 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV104', 'VE0104', 'KH002', TO_TIMESTAMP('2023-07-11 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-13 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV105', 'VE0105', 'KH002', TO_TIMESTAMP('2023-07-12 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-14 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV106', 'VE0106', 'KH002', TO_TIMESTAMP('2023-07-13 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-15 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV107', 'VE0107', 'KH002', TO_TIMESTAMP('2023-07-14 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-16 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV108', 'VE0108', 'KH002', TO_TIMESTAMP('2023-07-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-17 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV109', 'VE0109', 'KH002', TO_TIMESTAMP('2023-07-16 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-18 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV110', 'VE0110', 'KH002', TO_TIMESTAMP('2023-07-17 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-19 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV111', 'VE0111', 'KH002', TO_TIMESTAMP('2023-07-18 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-20 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV112', 'VE0112', 'KH002', TO_TIMESTAMP('2023-07-19 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-21 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);





INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV113', 'VE0113', 'KH002', TO_TIMESTAMP('2023-08-09 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-11 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV114', 'VE0114', 'KH002', TO_TIMESTAMP('2023-08-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-12 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV115', 'VE0115', 'KH002', TO_TIMESTAMP('2023-08-11 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-13 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV116', 'VE0116', 'KH002', TO_TIMESTAMP('2023-08-12 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-14 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV117', 'VE0117', 'KH002', TO_TIMESTAMP('2023-08-13 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-15 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV118', 'VE0118', 'KH002', TO_TIMESTAMP('2023-08-14 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-16 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV119', 'VE0119', 'KH002', TO_TIMESTAMP('2023-08-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-17 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV120', 'VE0120', 'KH002', TO_TIMESTAMP('2023-08-16 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-18 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV121', 'VE0121', 'KH002', TO_TIMESTAMP('2023-08-17 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-19 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV122', 'VE0122', 'KH002', TO_TIMESTAMP('2023-08-18 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-20 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV123', 'VE0123', 'KH002', TO_TIMESTAMP('2023-08-19 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-21 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV124', 'VE0124', 'KH002', TO_TIMESTAMP('2023-08-20 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-22 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV125', 'VE0125', 'KH002', TO_TIMESTAMP('2023-08-21 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-23 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV126', 'VE0126', 'KH002', TO_TIMESTAMP('2023-08-22 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-24 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV127', 'VE0127', 'KH002', TO_TIMESTAMP('2023-08-23 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-25 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);



INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV128', 'VE0128', 'KH002', TO_TIMESTAMP('2023-09-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-02 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV129', 'VE0129', 'KH002', TO_TIMESTAMP('2023-09-02 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-03 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV130', 'VE0130', 'KH002', TO_TIMESTAMP('2023-09-03 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-04 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV131', 'VE0131', 'KH002', TO_TIMESTAMP('2023-09-04 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-05 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV132', 'VE0132', 'KH002', TO_TIMESTAMP('2023-09-05 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-06 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV133', 'VE0133', 'KH002', TO_TIMESTAMP('2023-09-06 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-07 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV134', 'VE0134', 'KH002', TO_TIMESTAMP('2023-09-07 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-08 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV135', 'VE0135', 'KH002', TO_TIMESTAMP('2023-09-08 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-09 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV136', 'VE0136', 'KH002', TO_TIMESTAMP('2023-09-09 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-10 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV137', 'VE0137', 'KH002', TO_TIMESTAMP('2023-09-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-11 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV138', 'VE0138', 'KH002', TO_TIMESTAMP('2023-09-11 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-12 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV139', 'VE0139', 'KH002', TO_TIMESTAMP('2023-09-12 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-13 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV140', 'VE0140', 'KH002', TO_TIMESTAMP('2023-09-13 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-14 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV141', 'VE0141', 'KH002', TO_TIMESTAMP('2023-09-14 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-15 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV142', 'VE0142', 'KH002', TO_TIMESTAMP('2023-09-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-16 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);




INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV143', 'VE0143', 'KH002', TO_TIMESTAMP('2023-10-11 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-12 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV144', 'VE0144', 'KH002', TO_TIMESTAMP('2023-10-12 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-13 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV145', 'VE0145', 'KH002', TO_TIMESTAMP('2023-10-13 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-14 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV146', 'VE0146', 'KH002', TO_TIMESTAMP('2023-10-14 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-15 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV147', 'VE0147', 'KH002', TO_TIMESTAMP('2023-10-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-16 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV148', 'VE0148', 'KH002', TO_TIMESTAMP('2023-10-16 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-17 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV149', 'VE0149', 'KH002', TO_TIMESTAMP('2023-10-17 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-18 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV150', 'VE0150', 'KH002', TO_TIMESTAMP('2023-10-18 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-19 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV151', 'VE0151', 'KH002', TO_TIMESTAMP('2023-10-19 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-20 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV152', 'VE0152', 'KH002', TO_TIMESTAMP('2023-10-20 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-21 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV153', 'VE0153', 'KH002', TO_TIMESTAMP('2023-10-21 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-22 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV154', 'VE0154', 'KH002', TO_TIMESTAMP('2023-10-23 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-24 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV155', 'VE0155', 'KH002', TO_TIMESTAMP('2023-10-24 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-25 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV156', 'VE0156', 'KH002', TO_TIMESTAMP('2023-10-25 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-26 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV157', 'VE0157', 'KH002', TO_TIMESTAMP('2023-10-26 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-27 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);




INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV158', 'VE0158', 'KH002', TO_TIMESTAMP('2023-11-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-02 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV159', 'VE0159', 'KH002', TO_TIMESTAMP('2023-11-02 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-03 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV160', 'VE0160', 'KH002', TO_TIMESTAMP('2023-11-03 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-04 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV161', 'VE0161', 'KH002', TO_TIMESTAMP('2023-11-04 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-05 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV162', 'VE0162', 'KH002', TO_TIMESTAMP('2023-11-05 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-06 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV163', 'VE0163', 'KH002', TO_TIMESTAMP('2023-11-06 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-07 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV164', 'VE0164', 'KH002', TO_TIMESTAMP('2023-11-07 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-08 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV165', 'VE0165', 'KH002', TO_TIMESTAMP('2023-11-08 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-09 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV166', 'VE0166', 'KH002', TO_TIMESTAMP('2023-11-09 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-10 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV167', 'VE0167', 'KH002', TO_TIMESTAMP('2023-11-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-11 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV168', 'VE0168', 'KH002', TO_TIMESTAMP('2023-11-11 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-12 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV169', 'VE0169', 'KH002', TO_TIMESTAMP('2023-11-12 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-13 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV170', 'VE0170', 'KH002', TO_TIMESTAMP('2023-11-13 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-14 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV171', 'VE0171', 'KH002', TO_TIMESTAMP('2023-11-14 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-15 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV172', 'VE0172', 'KH002', TO_TIMESTAMP('2023-11-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-16 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);


-- TRINH Thêm
-- Chuyến bay CB047
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV04701', 'VE04701', 'KH001', TO_TIMESTAMP('2024-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV04702', 'VE04702', 'KH002', TO_TIMESTAMP('2024-05-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), NULL, 0);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV04703', 'VE04703', 'KH003', TO_TIMESTAMP('2024-04-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-04-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV04704', 'VE04704', 'KH004', TO_TIMESTAMP('2024-01-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-01-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);


-- Chuyến bay CB048
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV04801', 'VE04801', 'KH005', TO_TIMESTAMP('2024-02-05 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-05 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV04802', 'VE04802', 'KH005', TO_TIMESTAMP('2024-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), NULL, 0);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV04803', 'VE04803', 'KH006', TO_TIMESTAMP('2024-04-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-04-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV04804', 'VE04804', 'KH005', TO_TIMESTAMP('2024-03-15 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), NULL, 0);

-- Chuyến bay CB049
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV04901', 'VE04901', 'KH007', TO_TIMESTAMP('2024-05-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-05-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV04902', 'VE04902', 'KH003', TO_TIMESTAMP('2024-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), NULL, 0);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV04903', 'VE04903', 'KH006', TO_TIMESTAMP('2024-03-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-03-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV04904', 'VE04904', 'KH005', TO_TIMESTAMP('2024-04-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), NULL, 0);

-- Chuyến bay CB050
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05001', 'VE05001', 'KH001', TO_TIMESTAMP('2024-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05002', 'VE05002', 'KH002', TO_TIMESTAMP('2024-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), NULL, 0);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05003', 'VE05003', 'KH004', TO_TIMESTAMP('2024-03-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-03-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05004', 'VE05004', 'KH005', TO_TIMESTAMP('2024-05-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-05-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);

-- Chuyến bay CB051
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05101', 'VE05101', 'KH005', TO_TIMESTAMP('2024-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05102', 'VE05102', 'KH007', TO_TIMESTAMP('2024-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), NULL, 0);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05103', 'VE05103', 'KH003', TO_TIMESTAMP('2024-02-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05104', 'VE05104', 'KH003', TO_TIMESTAMP('2024-03-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-03-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);

-- Chuyến bay CB052
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05201', 'VE05201', 'KH005', TO_TIMESTAMP('2024-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05202', 'VE05202', 'KH007', TO_TIMESTAMP('2024-02-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), NULL, 0);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05203', 'VE05203', 'KH004', TO_TIMESTAMP('2024-01-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-01-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05204', 'VE05204', 'KH006', TO_TIMESTAMP('2024-02-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), NULL, 0);

-- Chuyến bay CB053 05
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05301', 'VE05301', 'KH005', TO_TIMESTAMP('2024-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05302', 'VE05302', 'KH007', TO_TIMESTAMP('2024-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), NULL, 0);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05303', 'VE05303', 'KH003', TO_TIMESTAMP('2024-01-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-01-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05304', 'VE05304', 'KH001', TO_TIMESTAMP('2024-02-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);


/*
-- Xóa dữ liệu trong bảng CT_DATVE
DELETE FROM CT_DATVE;

-- Xóa dữ liệu trong bảng CT_HANGVE
DELETE FROM CT_HANGVE;

-- Xóa dữ liệu trong bảng KHACHHANG
DELETE FROM KHACHHANG;

-- Xóa dữ liệu trong bảng VE
DELETE FROM VE;

-- Xóa dữ liệu trong bảng HANGVE
DELETE FROM HANGVE;

-- Xóa dữ liệu trong bảng DUONGBAY
DELETE FROM DUONGBAY;

-- Xóa dữ liệu trong bảng SANBAY
DELETE FROM SANBAY;

-- Xóa dữ liệu trong bảng CHUYENBAY
DELETE FROM CHUYENBAY;

-- Xóa dữ liệu trong bảng baocaonam
DELETE FROM baocaonam;

-- Xóa dữ liệu trong bảng BAOCAOTHANG
DELETE FROM BAOCAOTHANG;

-- Xóa dữ liệu trong bảng TAIKHOAN
DELETE FROM TAIKHOAN;

-- Xóa dữ liệu trong bảng QUYEN
DELETE FROM QUYEN;

-- Xóa dữ liệu trong bảng THAMSO
DELETE FROM THAMSO;

-- Xóa dữ liệu trong bảng SANBAYTG
DELETE FRROM SANBAYTG;
*/

-----------------------------------------------------------------------------------------------------------------------------------------
--PROCEDURE
-- LỊCH CHUYẾN BAY CONTROLLER
-- GET SAN BAY DI
CREATE OR REPLACE PROCEDURE GET_SANBAYDI (
    p_MaDuongBay IN VARCHAR2,
    p_TenSanBay OUT VARCHAR2
)
    IS
BEGIN
    SELECT sb.TenSanBay INTO p_TenSanBay
    FROM SANBAY sb
             JOIN DUONGBAY db ON sb.MaSanBay = db.MaSanBayDi
    WHERE db.MaDuongBay = p_MaDuongBay;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_TenSanBay := 'N/A'; -- Trả về giá trị mặc định nếu không tìm thấy dữ liệu
END GET_SANBAYDI;
/
--GET SAN BAY ĐẾN
CREATE OR REPLACE PROCEDURE GET_SANBAYDEN (
    p_MaDuongBay IN VARCHAR2,
    p_TenSanBay OUT VARCHAR2
)
    IS
BEGIN
    SELECT sb.TenSanBay INTO p_TenSanBay
    FROM SANBAY sb
             JOIN DUONGBAY db ON sb.MaSanBay = db.MaSanBayDen
    WHERE db.MaDuongBay = p_MaDuongBay;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_TenSanBay := 'N/A'; -- Trả về giá trị mặc định nếu không tìm thấy dữ liệu
END GET_SANBAYDEN;
/
--GET SỐ GHẾ TRỐNG
CREATE OR REPLACE PROCEDURE GET_SOGHETRONG (
    p_MaChuyenBay IN VARCHAR2,
    p_SoGheTrong OUT NUMBER
)
    IS
BEGIN
    SELECT SUM(SoGheTrong) INTO p_SoGheTrong
    FROM CT_HANGVE
    WHERE MaChuyenBay = p_MaChuyenBay;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_SoGheTrong := 0; -- Trả về giá trị mặc định nếu không tìm thấy dữ liệu
END GET_SOGHETRONG;
/
--GET SỐ Ghế
CREATE OR REPLACE PROCEDURE GET_SOGHE (
    p_MaChuyenBay IN VARCHAR2,
    p_SoGhe OUT NUMBER
)
    IS
BEGIN
    SELECT SUM(SoGheTrong + SoGheDat) INTO p_SoGhe
    FROM CT_HANGVE
    WHERE MaChuyenBay = p_MaChuyenBay;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_SoGhe := 0; -- Trả về giá trị mặc định nếu không tìm thấy dữ liệu
END GET_SOGHE;
/

--GET SỐ ĐIỂM DỪNG
CREATE OR REPLACE PROCEDURE GET_SODIEMDUNG (
    p_MaDuongBay IN VARCHAR2,
    p_SoDiemDung OUT NUMBER
) AS
BEGIN
    -- Đếm số lượng điểm dừng (sân bay trung gian) của đường bay
    SELECT COUNT(*)
    INTO p_SoDiemDung
    FROM SANBAYTG
    WHERE MaDuongBay = p_MaDuongBay;

    -- Nếu không có kết quả, gán giá trị 0 cho p_SoDiemDung
    IF p_SoDiemDung IS NULL THEN
        p_SoDiemDung := 0;
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_SoDiemDung := 0;
    WHEN OTHERS THEN
        p_SoDiemDung := 0;
END GET_SODIEMDUNG;
/


--------------------------------------------------------------------------------------
-- TRA CỨU DẶT VÉ CONTROLLER
-- PROCEDURE 1: Lấy tên khách hàng từ mã khách hàng
CREATE OR REPLACE PROCEDURE GET_TEN_KHACH_HANG(
    p_MaKhachHang IN VARCHAR2,
    p_HoTen OUT VARCHAR2
) AS
BEGIN
    SELECT HoTen INTO p_HoTen
    FROM KHACHHANG
    WHERE MaKhachHang = p_MaKhachHang;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_HoTen := NULL;
    WHEN OTHERS THEN
        -- Xử lí các trường hợp ngoại lệ khác ở đây
        NULL;
END GET_TEN_KHACH_HANG;
/

-- PROCEDURE 2: Lấy sân bay đi từ mã vé
CREATE OR REPLACE PROCEDURE GET_SAN_BAY_DI(
    p_MaVe IN VARCHAR2,
    p_SanBayDi OUT VARCHAR2
) AS
BEGIN
    SELECT SBDi.TenSanBay INTO p_SanBayDi
    FROM Ve V
             JOIN CHUYENBAY CB ON V.MaChuyenBay = CB.MaChuyenBay
             JOIN DUONGBAY DB ON CB.MaDuongBay = DB.MaDuongBay
             JOIN SANBAY SBDi ON DB.MaSanBayDi = SBDi.MaSanBay
    WHERE V.MAVE = p_MaVe;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_SanBayDi := NULL;
    WHEN OTHERS THEN
        NULL;
END GET_SAN_BAY_DI;
/

-- PROCEDURE 3: Lấy sân bay đến từ mã vé
CREATE OR REPLACE PROCEDURE GET_SAN_BAY_DEN(
    p_MaVe IN VARCHAR2,
    p_SanBayDen OUT VARCHAR2
) AS
BEGIN
    SELECT SBDen.TenSanBay INTO p_SanBayDen
    FROM Ve V
             JOIN CHUYENBAY CB ON V.MaChuyenBay = CB.MaChuyenBay
             JOIN DUONGBAY DB ON CB.MaDuongBay = DB.MaDuongBay
             JOIN SANBAY SBDen ON DB.MaSanBayDen = SBDen.MaSanBay
    WHERE V.MAVE = p_MaVe;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_SanBayDen := NULL;
    WHEN OTHERS THEN
        NULL;
END GET_SAN_BAY_DEN;
/

-- PROCEDURE 4: Lấy ngày bay từ mã vé
CREATE OR REPLACE PROCEDURE GET_NGAY_BAY(
    p_MaVe IN VARCHAR2,
    p_NgayBay OUT TIMESTAMP
) AS
BEGIN
    SELECT CB.TGXP INTO p_NgayBay
    FROM CT_DATVE CDV
             JOIN Ve V ON CDV.MaVe = V.MaVe
             JOIN CHUYENBAY CB ON V.MaChuyenBay = CB.MaChuyenBay
    WHERE CDV.MaVe = p_MaVe;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_NgayBay := NULL;
    WHEN OTHERS THEN
        NULL;
END GET_NGAY_BAY;
/

-- PROCEDURE 5: Lấy giờ bay từ mã vé
CREATE OR REPLACE PROCEDURE GET_GIO_BAY(
    p_MaVe IN VARCHAR2,
    p_GioBay OUT TIMESTAMP
) AS
BEGIN
    SELECT CB.TGXP INTO p_GioBay
    FROM CT_DATVE CDV
             JOIN Ve V ON CDV.MaVe = V.MaVe
             JOIN CHUYENBAY CB ON V.MaChuyenBay = CB.MaChuyenBay
    WHERE CDV.MaVe = p_MaVe;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_GioBay := NULL;
    WHEN OTHERS THEN
        NULL;
END GET_GIO_BAY;
/

-- PROCEDURE 6: Lấy số điện thoại từ mã khách hàng
CREATE OR REPLACE PROCEDURE GET_SDT(
    p_MaKhachHang IN VARCHAR2,
    p_SDT OUT VARCHAR2
) AS
BEGIN
    SELECT SDT INTO p_SDT
    FROM KHACHHANG
    WHERE MaKhachHang = p_MaKhachHang;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_SDT := NULL;
    WHEN OTHERS THEN
        NULL;
END GET_SDT;
/

-- PROCEDURE 7: Lấy mã ghế từ mã vé
CREATE OR REPLACE PROCEDURE GET_MA_GHE(
    p_MaVe IN VARCHAR2,
    p_MaGhe OUT NUMBER
) AS
BEGIN
    SELECT MaGhe INTO p_MaGhe
    FROM VE
    WHERE MAVE = p_MaVe;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_MaGhe := NULL;
    WHEN OTHERS THEN
        NULL;
END GET_MA_GHE;
/

-- PROCEDURE 8: Lấy tên hạng vé từ mã vé
CREATE OR REPLACE PROCEDURE GET_TEN_HANG_VE(
    p_MaVe IN VARCHAR2,
    p_TenHangVe OUT VARCHAR2
) AS
BEGIN
    SELECT HV.TenHangVe INTO p_TenHangVe
    FROM HANGVE HV
             JOIN VE V ON HV.MaHangVe = V.MaHangVe
    WHERE V.MAVE = p_MaVe;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_TenHangVe := NULL;
    WHEN OTHERS THEN
        NULL;
END GET_TEN_HANG_VE;
/

-- PROCEDURE 9: Lấy giá tiền từ mã vé
CREATE OR REPLACE PROCEDURE GET_GIA_TIEN(
    p_MaVe IN VARCHAR2,
    p_GiaTien OUT NUMBER
) AS
BEGIN
    SELECT GiaTien INTO p_GiaTien
    FROM VE
    WHERE MAVE = p_MaVe;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_GiaTien := NULL;
    WHEN OTHERS THEN
        NULL;
END GET_GIA_TIEN;
/

-- PROCEDURE 9: update lại số ghế trống, số ghế đặt khi thay đổi trạng thái của ct_datve */
--UPDATE TRANGTHAI
--DROP PROCEDURE update_ticket_status;

CREATE OR REPLACE PROCEDURE update_ticket_status(
    p_maCT_DATVE IN VARCHAR2,
    p_trangThai IN number
) AS
    v_maVe VARCHAR2(10);
    v_maChuyenBay VARCHAR2(10);
    v_maHangVe VARCHAR2(10);
    v_oldTrangThai number;
    v_NgayThanhToan TIMESTAMP;
BEGIN
    -- Fetch the current status of the ticket
    SELECT TrangThai INTO v_oldTrangThai
    FROM CT_DATVE
    WHERE MaCT_DATVE = p_maCT_DATVE;

    SELECT mave,ngaythanhtoan INTO v_maVe,v_NgayThanhToan
    FROM CT_DATVE cd
    WHERE CD.MACT_DATVE = P_MACT_DATVE;

-- Update the status of the ticket in CT_DATVE
    UPDATE CT_DATVE
    SET
        TrangThai = p_trangThai,
        NgayThanhToan =  CASE
                             WHEN p_trangThai = 1 THEN SYSDATE
                             ELSE v_NgayThanhToan
            END
    WHERE
        MaCT_DATVE = p_maCT_DATVE;
-- Fetch related MaChuyenBay and MaHangVe
    SELECT V.MaChuyenBay, V.MaHangVe INTO v_maChuyenBay, v_maHangVe
    FROM VE V
    WHERE v.MaVe = v_maVe;

-- Adjust the available seats in CT_HANGVE

    IF (p_trangThai = 1 AND v_oldTrangThai = 0 )THEN
        UPDATE CT_HANGVE
        SET SoGheTrong = SoGheTrong + 1,
            SoGheDat = SoGheDat - 1
        WHERE MaChuyenBay = v_maChuyenBay AND MaHangVe = v_maHangVe;
    END IF;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- Handle exceptions
        ROLLBACK;
        RAISE;
END;

--XÁC NHẬN ĐẶT VÉ CONTROLLER
--PROCEDURE 1: SELL ticket
CREATE OR REPLACE PROCEDURE SellTicket(
    p_maKH IN VARCHAR2,
    p_hoten IN VARCHAR2,
    p_cccd IN VARCHAR2,
    p_email IN VARCHAR2,
    p_sdt IN VARCHAR2,
    p_diaChi IN VARCHAR2,
    p_maVe IN VARCHAR2,
    p_ngayMuaVe IN TIMESTAMP,
    p_ngayThanhToan IN TIMESTAMP,
    p_trangThai IN INTEGER
) AS
    v_customerExists INTEGER;
    v_newMaCT_DATVE VARCHAR2(10);
BEGIN
    -- Check if customer exists
    SELECT COUNT(*) INTO v_customerExists
    FROM KHACHHANG
    WHERE MAKHACHHANG = p_maKH;

-- If customer does not exist, insert into KHACHHANG
    IF v_customerExists = 0 THEN
        INSERT INTO KHACHHANG (MAKHACHHANG, HOTEN, CCCD, EMAIL, SDT, DIACHI)
        VALUES (p_maKH, p_hoten, p_cccd, p_email, p_sdt, p_diaChi);
    END IF;

    -- Generate new MaCT_DATVE
    SELECT 'CTDV' || LPAD(NVL(MAX(TO_NUMBER(SUBSTR(MaCT_DATVE, 5))), 0) + 1, 3, '0') INTO v_newMaCT_DATVE
    FROM CT_DATVE;

-- Insert booking into CT_DATVE
    INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
    VALUES (v_newMaCT_DATVE, p_maVe, p_maKH, p_ngayMuaVe, p_ngayThanhToan, p_trangThai);

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END SellTicket;

--PROCEDURE 3: TẠO MÃ KHÁCH hàng
CREATE OR REPLACE PROCEDURE GenerateCustomerId(
    p_cccd IN VARCHAR2,
    p_maKH OUT VARCHAR2
) AS
    v_maxMaKH VARCHAR2(10);
BEGIN
    -- Kiểm tra xem khách hàng đã tồn tại hay chưa
    SELECT MAKHACHHANG
    INTO p_maKH
    FROM KHACHHANG
    WHERE CCCD = p_cccd;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Lấy mã khách hàng lớn nhất
        SELECT MAX(MAKHACHHANG)
        INTO v_maxMaKH
        FROM KHACHHANG;

        IF v_maxMaKH IS NOT NULL THEN
            -- Tăng mã khách hàng lên 1
            p_maKH := 'KH' || TO_CHAR(TO_NUMBER(SUBSTR(v_maxMaKH, 3)) + 1, 'FM000');
        ELSE
            -- Nếu không có khách hàng nào, bắt đầu từ KH001
            p_maKH := 'KH001';
        END IF;
END GenerateCustomerId;

----------------------------------------------------------------------------------
-- MÀN HÌNH ĐẶT VÉ CONTROLLER

CREATE OR REPLACE PROCEDURE GET_VE_FOR_FLIGHT (
    p_maChuyenBay IN VARCHAR2,
    p_result OUT SYS_REFCURSOR
)
    IS
BEGIN
    OPEN p_result FOR
        SELECT DISTINCT
            hv.TENHANGVE,
            hv.MAHANGVE,
            (cb.GiaVe * hv.HeSo) AS GiaTien
        FROM
            CT_HANGVE ct
                JOIN
            HANGVE hv ON ct.MaHangVe = hv.MaHangVe
                JOIN
            CHUYENBAY cb ON ct.MaChuyenBay = cb.MaChuyenBay
        WHERE
            ct.MaChuyenBay = p_maChuyenBay
          AND ct.SoGheTrong > 0;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No tickets found for the specified flight.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
END GET_VE_FOR_FLIGHT;
/

CREATE OR REPLACE PROCEDURE GET_TENHANGVE (
    p_maHangVe IN VARCHAR2,
    p_tenHangVe OUT VARCHAR2
)
    IS
BEGIN
    SELECT HV.TENHANGVE
    INTO p_tenHangVe
    FROM HANGVE HV
    WHERE HV.MAHANGVE = p_maHangVe;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_tenHangVe := 'Không tìm thấy';
    WHEN OTHERS THEN
        p_tenHangVe := 'Lỗi: ' || SQLERRM;
END GET_TENHANGVE;
/


CREATE OR REPLACE PROCEDURE GENERATE_MA_VE (
    p_newMaVe OUT VARCHAR2
)
    IS
    v_maxMaVe VE.MAVE%TYPE;
    v_newMaVeNumber NUMBER;
BEGIN
    -- Lấy giá trị lớn nhất của MAVE từ bảng VE
    SELECT MAX(MAVE) INTO v_maxMaVe FROM VE;

    -- Nếu không có MAVE nào trong bảng VE, bắt đầu với VE001
    IF v_maxMaVe IS NULL THEN
        p_newMaVe := 'VE00001';
    ELSE
        -- Tăng giá trị của MAVE lớn nhất thêm 1
        v_newMaVeNumber := TO_NUMBER(SUBSTR(v_maxMaVe, 5)) + 1;
        -- Tạo MAVE mới với định dạng VExxx
        p_newMaVe := 'VE' || TO_CHAR(v_newMaVeNumber, 'FM00000');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_newMaVe := 'VE00001'; -- Trả về giá trị mặc định nếu có lỗi
END GENERATE_MA_VE;
/


CREATE OR REPLACE PROCEDURE GENERATE_MA_GHE (
    p_newMaGhe OUT NUMBER
)
    IS
    v_maxMaGhe VE.MAGHE%TYPE;
BEGIN
    -- Lấy giá trị lớn nhất của MAGHE từ bảng VE
    SELECT MAX(MAGHE) INTO v_maxMaGhe FROM VE;

    -- Nếu không có MAGHE nào trong bảng VE, bắt đầu với 1
    IF v_maxMaGhe IS NULL THEN
        p_newMaGhe := 1;
    ELSE
        -- Tăng giá trị của MAGHE lớn nhất thêm 1
        p_newMaGhe := v_maxMaGhe + 1;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_newMaGhe := 1; -- Trả về giá trị mặc định nếu có lỗi
END GENERATE_MA_GHE;
/


CREATE OR REPLACE PROCEDURE SAVE_TICKET (
    p_maVe IN VARCHAR2,
    p_maChuyenBay IN VARCHAR2,
    p_maHangVe IN VARCHAR2,
    p_maGhe IN VARCHAR2,
    p_giaTien IN NUMBER
)
    IS
BEGIN
    INSERT INTO VE (MAVE, MACHUYENBAY, MAHANGVE, MAGHE, GIATIEN)
    VALUES (p_maVe, p_maChuyenBay, p_maHangVe, p_maGhe, p_giaTien);
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while saving ticket: ' || SQLERRM);
END SAVE_TICKET;
/


----------------------------------------------------------------------------
-- PHÂN QUYÊN CONTROLLER
CREATE OR REPLACE PROCEDURE GET_TAIKHOAN_PHANQUYEN (
    p_result OUT SYS_REFCURSOR
)
    IS
BEGIN
    OPEN p_result FOR
        SELECT
            TaiKhoan.maTaiKhoan,
            TaiKhoan.ten,
            TaiKhoan.sdt,
            TaiKhoan.email,
            TaiKhoan.password,
            TaiKhoan.created,
            Quyen.tenQuyen
        FROM
            TaiKhoan
                JOIN
            Quyen ON TaiKhoan.maQuyen = Quyen.maQuyen;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error occurred: ' || SQLERRM);
        RAISE;
END GET_TAIKHOAN_PHANQUYEN;
/



------------------------------------------------------------------
-- QUY ĐỊNH CONTROLLER
-- PROCEDURE 1: TẠO MÃ ĐƯỜNG BAY
CREATE OR REPLACE PROCEDURE GENERATE_MA_DUONGBAY (
    p_newMaDB OUT VARCHAR2
)
    IS
    v_maxMaDB VARCHAR2(6);
    v_maxNumber NUMBER;
    v_newMaDBNumber NUMBER;
BEGIN
    -- Lấy giá trị lớn nhất của phần số trong MADUONGBAY
    SELECT MAX(TO_NUMBER(SUBSTR(MADUONGBAY, 3))) INTO v_maxNumber FROM DUONGBAY;

    -- Nếu không có MADUONGBAY nào trong bảng DUONGBAY, bắt đầu với DB001
    IF v_maxNumber IS NULL THEN
        p_newMaDB := 'DB001';
    ELSE
        -- Tăng giá trị của phần số lớn nhất thêm 1
        v_newMaDBNumber := v_maxNumber + 1;
        -- Tạo MADUONGBAY mới với định dạng DBxxx
        p_newMaDB := 'DB' || TO_CHAR(v_newMaDBNumber, 'FM000');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_newMaDB := 'DB001'; -- Trả về giá trị mặc định nếu có lỗi
END GENERATE_MA_DUONGBAY;
-----------------------------------------------------------------------------
-- XÓA CT_DATVE LIÊN QUAN ĐẾN VE ĐÃ XÓA
CREATE OR REPLACE PROCEDURE delete_CT_DATVE_by_VE(
    p_MaVe IN VARCHAR2
) AS
BEGIN
    DELETE FROM CT_DATVE
    WHERE MaVe = p_MaVe;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        NULL; -- Nếu không tìm thấy MaVe trong CT_DATVE thì bỏ qua
    WHEN OTHERS THEN
        -- Xử lý các ngoại lệ khác nếu cần thiết
        NULL;
END delete_CT_DATVE_by_VE;



-------------------------------------------------------------------



-----------------------------------------------------------------------------------------------
--TRIGGER



/* R1: Thời gian xuất phát của một chuyến bay phải nhỏ hơn thời gian kết thúc */

ALTER TABLE CHUYENBAY
    ADD CONSTRAINT Check_ThoiGian_CB CHECK (TGXP < TGKT);


/* R2: Ngày thanh toán vé phải sau ngày mua vé */

ALTER TABLE CT_DATVE
    ADD CONSTRAINT Check_NgayThanhToan_VE CHECK (NgayMuaVe <= NgayThanhToan);


/* R3:Tổng giá tiền thanh toán = giá vé x hệ số */

CREATE OR REPLACE TRIGGER Tienthanhtoan_VE
    AFTER INSERT OR UPDATE ON VE
    FOR EACH ROW
DECLARE
    v_GiaVe CHUYENBAY.GiaVe%TYPE;
    v_HeSo HANGVE.HeSo%TYPE;
BEGIN

    SELECT cb.GiaVe, hv.HeSo
    INTO v_GiaVe, v_HeSo
    FROM CHUYENBAY cb, HANGVE hv
    WHERE cb.MaChuyenBay = :NEW.MaChuyenBay
      AND hv.MaHangVe = :NEW.MaHangVe;


    IF :NEW.GiaTien <> v_HeSo * v_GiaVe THEN
        RAISE_APPLICATION_ERROR(-20002, 'Tổng giá tiền thanh toán không hợp lệ.');
    END IF;
END;
/

/* R4: Cập nhật doanh thu  tháng khi có thêm doanh thu chuyến bay.*/

CREATE OR REPLACE TRIGGER Update_DoanhThu_Thang
    FOR INSERT OR update ON CT_DATVE
    COMPOUND TRIGGER
    v_nam NUMBER;
    v_thang NUMBER;
    giave NUMBER;
    v_dem NUMBER;
    v_dem_nam NUMBER;
    v_machuyenbay VARCHAR2(10);
    v_countMaChuyenBay NUMBER;
    CheckExist NUMBER := 0 ;
    v_count NUMBER;
    p_trangThai NUMBER;
    v_maVe VARCHAR2(10);
    v_maHangVe VARCHAR2(10);

BEFORE STATEMENT IS
BEGIN
    NULL;
END BEFORE STATEMENT;

    BEFORE EACH ROW IS
    BEGIN

        IF (:NEW.NgayThanhToan IS NULL) THEN
            BEGIN
                SELECT 0,0 INTO v_nam,V_THANG
                FROM dual;
            END;
        ELSE
            SELECT EXTRACT(YEAR FROM :NEW.NgayThanhToan), EXTRACT(MONTH FROM :NEW.NgayThanhToan) INTO v_nam,v_thang
            FROM dual;
        END IF;

        SELECT :NEW.TrangThai INTO p_trangThai
        FROM dual;

        SELECT MAHANGVE INTO v_maHangVe
        FROM VE
        WHERE Ve.MaVE = :NEW.MAVE;

        SELECT Giatien INTO giave
        FROM VE v
        WHERE V.MaVE = :NEW.MaVE;

        SELECT Machuyenbay INTO v_machuyenbay
        FROM ve
        WHERE Ve.MaVE = :NEW.MAVE;



        IF (:NEW.TrangThai = 2) THEN
            BEGIN
                CheckExist := 1;
            END;
        END IF;

    END BEFORE EACH ROW;


    AFTER EACH ROW IS
    BEGIN
        NULL;
    END AFTER EACH ROW;


    AFTER STATEMENT IS
    BEGIN

        IF (( p_trangThai = 1) OR (p_trangThai = 0) ) THEN
            UPDATE CT_HANGVE
            SET SoGheTrong = SoGheTrong - 1,
                SoGheDat = SoGheDat + 1
            WHERE MaChuyenBay = v_maChuyenBay AND MaHangVe = v_maHangVe;
        ELSIF p_trangThai = 2 THEN
            UPDATE CT_HANGVE
            SET SoGheTrong = SoGheTrong + 1,
                SoGheDat = SoGheDat - 1
            WHERE MaChuyenBay = v_maChuyenBay AND MaHangVe = v_maHangVe;
        END IF;


        IF (v_nam > 0 AND v_thang > 0 AND CheckExist = 0) THEN
            BEGIN
                SELECT COUNT(*) INTO v_dem
                FROM baocaothang
                WHERE thang = v_thang AND nam = v_nam;

                SELECT COUNT(*) INTO v_dem_nam
                FROM baocaonam
                WHERE nam = v_nam;

                SELECT COUNT(*) INTO v_countMaChuyenBay
                FROM BAOCAOTHANG
                WHERE machuyenbay = v_machuyenbay;

                IF (v_dem > 0) THEN
                    begin
                        IF (v_countMaChuyenBay > 0 ) THEN
                            begin
                                UPDATE BAOCAOTHANG
                                SET DoanhThu = DoanhThu + GiaVe,
                                    SoVeDaBan = SoVeDaBan + 1
                                WHERE nam = v_Nam AND thang = v_Thang AND machuyenbay = v_machuyenbay;
                            END;
                        ELSE
                            BEGIN
                                INSERT INTO BAOCAOTHANG ( MaChuyenBay, SoVeDaBan, DoanhThu, Thang, Nam)
                                VALUES (v_machuyenbay, 1, giave, v_thang, v_nam);
                            END;
                        END IF;
                    END;
                ELSE
                    BEGIN
                        INSERT INTO BAOCAOTHANG ( MaChuyenBay, SoVeDaBan, DoanhThu, Thang, Nam)
                        VALUES (v_machuyenbay, 1, giave, v_thang, v_nam);
                    END;
                END IF;

                UPDATE baocaonam
                SET doanhthu = doanhthu + GIAVE
                WHERE nam = v_nam AND thang = v_thang;

            END;
        END IF;

        ----
        IF (CheckExist = 1 ) THEN
            BEGIN
                SELECT sovedaban INTO v_count
                FROM BAOCAOTHANG
                WHERE thang = v_thang AND nam = v_nam AND machuyenbay = v_machuyenbay ;

                IF (v_count > 1 ) THEN
                    begin
                        UPDATE BAOCAOTHANG
                        SET DoanhThu = DoanhThu - GiaVe,
                            SoVeDaBan = SoVeDaBan - 1
                        WHERE nam = v_Nam AND thang = v_Thang AND machuyenbay = v_machuyenbay;

                        UPDATE BAOCAONAM
                        SET doanhthu = doanhthu - giave
                        WHERE nam = v_nam AND thang = v_thang;
                    END;
                ELSE
                    BEGIN
                        UPDATE BAOCAONAM
                        SET doanhthu = doanhthu - giave, SOCHUYENBAY = SOCHUYENBAY - 1
                        WHERE nam = v_nam AND thang = v_thang;

                        DELETE FROM baocaothang
                        WHERE nam = v_Nam AND thang = v_Thang AND machuyenbay = v_machuyenbay;

                    END;
                END IF;
            END;
        END IF;

    END AFTER STATEMENT;
    END Update_DoanhThu_Thang;

--trigger cho delete
CREATE OR REPLACE TRIGGER Update_DoanhThu_Thang_delete
    FOR delete ON CT_DATVE
    COMPOUND TRIGGER
    v_nam NUMBER;
    v_thang NUMBER;
    giave NUMBER;
    v_dem NUMBER;
    v_machuyenbay VARCHAR2(10);
    v_count NUMBER;
    v_maHangVe VARCHAR2(10);


BEFORE STATEMENT IS
BEGIN
    NULL;
END BEFORE STATEMENT;

    BEFORE EACH ROW IS
    BEGIN
        IF (:OLD.NgayThanhToan IS NULL) THEN
            BEGIN
                SELECT 0,0 INTO v_nam,V_THANG
                FROM dual;
            END;
        ELSE
            BEGIN
                SELECT EXTRACT(YEAR FROM :OLD.NgayThanhToan), EXTRACT(MONTH FROM :OLD.NgayThanhToan) INTO v_nam,v_thang
                FROM dual;
            END;
        END IF;

        SELECT Giatien INTO giave
        FROM VE v
        WHERE V.MaVE = :OLD.MaVE;

        SELECT machuyenbay INTO v_machuyenbay
        FROM VE
        WHERE mave = :OLD.mave;

        SELECT MAHANGVE INTO v_maHangVe
        FROM VE
        WHERE Ve.MaVE = :OLD.MAVE;

    END BEFORE EACH ROW;

    AFTER EACH ROW IS
    BEGIN
        NULL;
    END AFTER EACH ROW;

    AFTER STATEMENT IS
    BEGIN

        UPDATE ct_hangve
        SET soghetrong = soghetrong + 1 ,
            soghedat = soghedat - 1
        WHERE machuyenbay = v_machuyenbay  AND mahangve = V_MAHANGVE;

        IF (v_nam > 0 AND v_thang > 0 ) THEN
            BEGIN
                SELECT sovedaban INTO v_count
                FROM BAOCAOTHANG
                WHERE thang = v_thang AND nam = v_nam AND machuyenbay = v_machuyenbay ;

                IF (v_count > 1 ) THEN
                    begin
                        UPDATE BAOCAOTHANG
                        SET DoanhThu = DoanhThu - GiaVe,
                            SoVeDaBan = SoVeDaBan - 1
                        WHERE nam = v_Nam AND thang = v_Thang AND machuyenbay = v_machuyenbay;

                        UPDATE BAOCAONAM
                        SET doanhthu = doanhthu - giave
                        WHERE nam = v_nam AND thang = v_thang;
                    END;
                ELSE
                    BEGIN
                        UPDATE BAOCAOTHANG
                        SET DoanhThu = DoanhThu - GiaVe,
                            SoVeDaBan = SoVeDaBan - 1
                        WHERE nam = v_Nam AND thang = v_Thang AND machuyenbay = v_machuyenbay;

                        UPDATE BAOCAONAM
                        SET doanhthu = doanhthu - giave, SOCHUYENBAY = SOCHUYENBAY - 1
                        WHERE nam = v_nam AND thang = v_thang;

                        DELETE FROM baocaothang
                        WHERE nam = v_Nam AND thang = v_Thang AND machuyenbay = v_machuyenbay;

                    END;
                END IF;
            END;
        END IF;
    END AFTER STATEMENT;

    END Update_DoanhThu_Thang_delete;

/* R4: Mỗi vé phải đặt trước một khoảng thời gian nhất định trước khi máy bay xuất phát. */
--DROP TRIGGER trigger_CT_DATVE

CREATE OR REPLACE TRIGGER trigger_CT_DATVE
    BEFORE INSERT OR update ON CT_DATVE
    FOR EACH ROW
DECLARE
    v_MinBookingTime NUMBER;
    v_Interval INTERVAL DAY(4) TO SECOND;
    v_TimeMin INTERVAL DAY(4) TO SECOND;
BEGIN
    SELECT GiaTri into v_MinBookingTime
    FROM THAMSO
    WHERE TenThuocTinh = 'ThoiGianToiThieuDatVe';

    v_Interval := NUMTODSINTERVAL(v_MinBookingTime, 'HOUR');

    SELECT (TGXP - :NEW.NgayMuaVe  ) INTO v_TimeMin
    FROM chuyenbay cb, ve v
    WHERE V.MAVE = :NEW.MaVE  AND CB.MACHUYENBAY = V.MACHUYENBAY;


    IF v_TimeMin < v_Interval THEN
        RAISE_APPLICATION_ERROR(-20004, 'Mỗi vé phải được đặt trước ít nhất ' || v_MinBookingTime || ' giờ trước khi máy bay xuất phát.');
    END IF;

END;
/



/* R5: Cập nhật Số chuyến bay báo cáo năm khi có chuyến bay mới được update vào baocaothang.*/
--DROP TRIGGER Update_DoanhThu_Nam;

CREATE OR REPLACE TRIGGER Update_DoanhThu_Nam
    BEFORE INSERT ON baocaothang
    FOR EACH ROW
DECLARE
    v_checkExist NUMBER;
BEGIN

    SELECT COUNT(*) INTO V_CHECKEXIST
    FROM baocaonam
    WHERE nam = :NEW.Nam AND thang = :NEW.thang;

    IF (V_CHECKEXIST > 0) THEN
        BEGIN
            UPDATE BAOCAONAM
            SET sochuyenbay = sochuyenbay + 1
            WHERE thang = :NEW.thang AND nam = :NEW.nam;
        END;
    ELSE
        BEGIN
            INSERT INTO baocaonam(thang,nam,sochuyenbay,doanhthu) VALUES (:NEW.thang,:NEW.nam,1,0);
        END;
    END IF;
END;
/





/* R6:  Giá tiền của Vé là một số không âm. */

ALTER TABLE VE
    ADD CONSTRAINT Check_GiaTien_ CHECK (GiaTien >= 0);


/* R7: Trạng thái là chưa đổi hoặc đã đổi */

ALTER TABLE HANGVE
    ADD CONSTRAINT Check_TrangThai_HV CHECK (TrangThai IN (0,1));


/* R8.1: Trạng thái mặc định ban đầu là chưa đổi */

ALTER TABLE HANGVE
    MODIFY TrangThai DEFAULT 1;


/* R18.2: Trạng thái mặc định ban đầu là chưa đổi */

ALTER TABLE CT_DATVE
    MODIFY TrangThai DEFAULT 1;



/* R9: Số vé là một số không âm. */

ALTER TABLE baocaothang
    ADD CONSTRAINT Check_SoVe CHECK (SoVeDaBan >= 0);


/* R10: Doanh thu là một số không âm. */

ALTER TABLE baocaothang
    ADD CONSTRAINT Check_DoanhThu CHECK (DoanhThu >= 0);


/* R11: Doanh thu mặc định là 0. */

ALTER TABLE BAOCAONAM
    MODIFY DoanhThu DEFAULT 0;


/* R12: So Ve mặc định là 0.  */

ALTER TABLE baocaothang
    MODIFY SoVeDaBan DEFAULT 0;


/* R13: Thời gian bay phải không nhỏ hơn thời gian bay tối thiểu. */
--DROP TRIGGER Check_ThoiGianXuatPhat

CREATE OR REPLACE TRIGGER Check_ThoiGianXuatPhat
    BEFORE INSERT OR UPDATE ON CHUYENBAY
    FOR EACH ROW
DECLARE
    v_ThoiGianToiThieu THAMSO.GiaTri%TYPE;
    thoigianbay NUMBER;
    distance INTERVAL DAY TO SECOND;
BEGIN
    SELECT GiaTri INTO v_ThoiGianToiThieu
    FROM THAMSO
    WHERE TenThuocTinh = 'ThoiGianBayToiThieu';

    distance := :NEW.TGKT - :NEW.TGXP;

    SELECT EXTRACT(DAY FROM (distance)) * 24 +
           EXTRACT(HOUR FROM (distance)) +
           EXTRACT(MINUTE FROM (distance)) / 60 +
           EXTRACT(SECOND FROM (distance)) / 3600 into thoigianbay
    FROM dual;

    IF thoigianbay  < v_ThoiGianToiThieu THEN
        RAISE_APPLICATION_ERROR(-20008, 'Thời gian bay phải lớn hơn hoặc bằng thời gian bay tối thiểu.');
    END IF;
END;
/


/* R14: sân bay đến và sân bay đi của đường bay không trùng nhau */
--DROP TRIGGER Check_DuongBay

CREATE OR REPLACE TRIGGER Check_DuongBay
    BEFORE INSERT OR UPDATE ON DUONGBAY
    FOR EACH ROW
BEGIN
    IF :NEW.MaSanBayDi = :NEW.MaSanBayDen THEN
        RAISE_APPLICATION_ERROR(-20009, 'Sân bay đi và sân bay đến không được trùng nhau.');
    END IF;
END;
/

/* R15: nếu số lượng ghế trống = 0 thì không cho đặt vé */
--DROP TRIGGER check_SL_GheTrong;

CREATE OR REPLACE TRIGGER check_SL_GheTrong
    BEFORE INSERT ON CT_DATVE
    FOR EACH ROW
DECLARE
    v_soghetrong CT_HANGVE.SOGHETRONG%TYPE;
BEGIN

    SELECT soghetrong INTO v_soghetrong
    FROM CT_HANGVE cthv, ve v
    WHERE v.MaVE = :NEW.MaVe AND v.machuyenbay = cthv.machuyenbay AND v.MAHANGVE = cthv.MAHANGVE;


    IF (v_SoGheTrong = 0) THEN
        RAISE_APPLICATION_ERROR(-20009, 'Hết vé trống');
    END IF;
END;
/


/* R16: xóa VE và CT_HANGVE liên quan đến chuyến bay đã xóa */

--CHẠY PROCEDURE XONG MỚI CHẠY LẠI TRIGGER NÀY

CREATE OR REPLACE TRIGGER rf_VE_CT_HANGVE_delete_CHUYENBAY
    before DELETE ON CHUYENBAY
    FOR EACH ROW
BEGIN
    -- Xóa các bản ghi liên quan từ bảng VE
    DELETE CT_HANGVE
    WHERE machuyenbay = :OLD.machuyenbay;

    FOR rec IN (SELECT MaVe FROM VE WHERE MaChuyenBay = :OLD.MaChuyenBay)
        LOOP
            BEGIN
                -- Gọi procedure delete_CT_DATVE_by_VE để xóa các bản ghi trong CT_DATVE, nếu không tìm thấy thì bỏ qua
                delete_CT_DATVE_by_VE(rec.MaVe);
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    -- Nếu không tìm thấy MaVe trong CT_DATVE thì bỏ qua
                    NULL;
                WHEN OTHERS THEN
                    -- Xử lý các ngoại lệ khác nếu cần thiết
                    NULL;
            END;
        END LOOP;

    -- Xóa các bản ghi trong bảng VE liên quan đến MaChuyenBay vừa xóa
    DELETE FROM VE
    WHERE MaChuyenBay = :OLD.MaChuyenBay;
END rf_VE_CT_HANGVE_delete_CHUYENBAY;
/

/* R17: cập nhật trạng thái đường bay khi cập nhật trạng thái sân bay ngưng hoạt động */
--DROP TRIGGER trg_update_trangthai_duongbay;
CREATE OR REPLACE TRIGGER trg_update_trangthai_duongbay
    AFTER UPDATE OF TrangThai ON SANBAY
    FOR EACH ROW
BEGIN
    IF :NEW.TrangThai = 0 THEN
        -- Update DUONGBAY where MaSanBayDen matches the updated MaSanBay
        UPDATE DUONGBAY
        SET TrangThai = 0
        WHERE MaSanBayDen = :OLD.MaSanBay;

        -- Update DUONGBAY where MaSanBayDi matches the updated MaSanBay
        UPDATE DUONGBAY
        SET TrangThai = 0
        WHERE MaSanBayDi = :OLD.MaSanBay;

        -- Update DUONGBAY where MaDuongBay matches MaDuongBay in SANBAYTG and MaSanBay in SANBAYTG matches the updated MaSanBay
        UPDATE DUONGBAY
        SET TrangThai = 0
        WHERE MaDuongBay IN (
            SELECT MaDuongBay
            FROM SANBAYTG
            WHERE MaSanBay = :OLD.MaSanBay
        );
    END IF;
END; ----------------------------- trigger mục 3

/* R18: kiểm tra trước khi cập nhật trạng thái đường bay hoạt động */
--DROP TRIGGER trg_check_duongbay_status;
CREATE OR REPLACE TRIGGER trg_check_duongbay_status
    BEFORE UPDATE OF TrangThai ON DUONGBAY
    FOR EACH ROW
DECLARE
    cnt NUMBER;
BEGIN
    IF :NEW.TrangThai = 1 THEN
        -- Kiểm tra số lượng sân bay có TrangThai = 0 bằng cách sử dụng UNION
        SELECT COUNT(*)
        INTO cnt
        FROM (
                 SELECT 1
                 FROM SANBAY sb
                 WHERE sb.MaSanBay = :OLD.MaSanBayDi AND sb.TrangThai = 0
                 UNION
                 SELECT 1
                 FROM SANBAY sb
                 WHERE sb.MaSanBay = :OLD.MaSanBayDen AND sb.TrangThai = 0
                 UNION
                 SELECT 1
                 FROM SANBAY sb
                 WHERE sb.TrangThai = 0 AND sb.MaSanBay IN (
                     SELECT sbtg.MaSanBay
                     FROM SANBAYTG sbtg
                     WHERE sbtg.MaDuongBay = :OLD.MaDuongBay
                 )
             );
        -- Nếu số lượng sân bay có TrangThai = 0 lớn hơn 0 thì raise lỗi
        IF cnt > 0 THEN
            RAISE_APPLICATION_ERROR(-20001, 'Có sân bay thuộc đường bay đang hoạt động nên không thể thay đổi trạng thái');
        END IF;
    END IF;
END;------------------------------------------------ trigger mục 2

