 -- Tạo Schema

alter session set "_ORACLE_SCRIPT"=true;

CREATE USER fly_the_end8 IDENTIFIED BY password;
GRANT CONNECT, RESOURCE, DBA TO fly_the_end8;


alter session set "_ORACLE_SCRIPT"=true;
CREATE USER user1 IDENTIFIED BY user1;
GRANT CONNECT, RESOURCE, DBA TO user1;

alter session set "_ORACLE_SCRIPT"=true;
CREATE USER user2 IDENTIFIED BY user2;
GRANT CONNECT, RESOURCE, DBA TO user2;

-- Sử dụng Schema

ALTER SESSION SET CURRENT_SCHEMA = fly_the_end8;


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
    CONSTRAINT PK_DUONGBAY PRIMARY KEY (MaDuongBay)
);


/* TABLE 4 : CHUYENBAY */ 

CREATE TABLE CHUYENBAY (
    MaChuyenBay VARCHAR2(10) NOT NULL ,
    MaDuongBay VARCHAR2(10) NOT null ,     
    TGXP TIMESTAMP,
    TGKT TIMESTAMP,
    TrangThai VARCHAR2(50),
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

/* TABLE 9: BCDT_NAM */ 

CREATE TABLE BAOCAONAM (
    Nam number NOT NULL,    
    DoanhThu NUMBER(10,2) NULL,    
    CONSTRAINT pk_bcn PRIMARY KEY (Nam)
);

/* TABLE 10: BCDT_THANG */ 

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




/* R1: Thời gian xuất phát của một chuyến bay phải nhỏ hơn thời gian kết thúc */ 

ALTER TABLE CHUYENBAY
ADD CONSTRAINT Check_ThoiGian_CB CHECK (TGXP < TGKT);


/* R2: Thứ tự của mỗi chuyến bay không trùng nhau 

DROP TRIGGER Check_ThuTu_BD

CREATE OR REPLACE TRIGGER Check_ThuTu_BD
BEFORE INSERT OR UPDATE ON SANBAYTRUNGGIAN
FOR EACH ROW
DECLARE
    count NUMBER;
BEGIN
    SELECT COUNT(*) INTO count
    FROM SANBAYTRUNGGIAN
    WHERE MaSanBay = :NEW.MaSanBay
        AND MaDuongBay = :NEW.MaDuongBay
        AND ThuTu = :NEW.ThuTu;

    IF count > 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Thứ tự của mỗi chuyến bay không được trùng nhau.');
    END IF;
END;
/

*/

/* R3: Ngày thanh toán vé phải sau ngày mua vé */

ALTER TABLE CT_DATVE
ADD CONSTRAINT Check_NgayThanhToan_VE CHECK (NgayMuaVe <= NgayThanhToan);


/* R4:Tổng giá tiền thanh toán = giá vé x hệ số */ 

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

/* R5: Cập nhật doanh thu  tháng khi có thêm doanh thu chuyến bay.*/

DROP TRIGGER Update_DoanhThu_Thang;

CREATE OR REPLACE TRIGGER Update_DoanhThu_Thang
FOR INSERT OR update ON CT_DATVE
COMPOUND TRIGGER
     v_nam NUMBER;
     v_thang NUMBER; 
     giave NUMBER;
     v_dem NUMBER;
     v_dem_nam NUMBER;
     v_machuyenbay VARCHAR(10);
     

     BEFORE STATEMENT IS
     BEGIN
       NULL;
     END BEFORE STATEMENT;
   
     BEFORE EACH ROW IS
     BEGIN
          SELECT EXTRACT(YEAR FROM :NEW.NgayThanhToan), EXTRACT(MONTH FROM :NEW.NgayThanhToan) INTO v_nam,v_thang
          FROM dual;

          SELECT Giatien INTO giave 
          FROM VE v
          WHERE V.MaVE = :NEW.MaVE;

          SELECT Machuyenbay INTO v_machuyenbay 
          FROM ve 
          WHERE Ve.MaVE = :NEW.MAVE;

     END BEFORE EACH ROW;
   
     
     AFTER EACH ROW IS
     BEGIN
       NULL;
     END AFTER EACH ROW;
   
    
     AFTER STATEMENT IS
     BEGIN      
        SELECT COUNT(*) INTO v_dem
        FROM baocaothang 
        WHERE thang = v_thang AND nam = v_nam;
        
        SELECT COUNT(*) INTO v_dem_nam
        FROM baocaonam 
        WHERE nam = v_nam;
        
        IF (v_dem > 0) THEN     
           begin     
             UPDATE BAOCAOTHANG
             SET DoanhThu = DoanhThu + GiaVe,
                 SoVeDaBan = SoVeDaBan + 1
             WHERE nam = v_Nam AND thang = v_Thang;            
           END;
        ELSE  
            BEGIN                
              INSERT INTO BAOCAOTHANG ( MaChuyenBay, SoVeDaBan, DoanhThu, Thang, Nam)
              VALUES (v_machuyenbay, 1, giave, v_thang, v_nam);             
            END;
        END IF;

        IF (v_dem_nam > 0 )  THEN
              BEGIN 
                  UPDATE baocaonam 
                  SET doanhthu = doanhthu + GIAVE
                  WHERE nam = v_nam;
              END;
        ELSE 
              BEGIN 
                  INSERT INTO baocaonam(nam,doanhthu) VALUES (v_nam,giave);
              END;
        END IF;
       
     END AFTER STATEMENT;

END Update_DoanhThu_Thang;


DROP TRIGGER Update_DoanhThu_Thang_delete;

CREATE OR REPLACE TRIGGER Update_DoanhThu_Thang_delete
FOR delete ON CT_DATVE
COMPOUND TRIGGER
     v_nam NUMBER;
     v_thang NUMBER; 
     giave NUMBER;
     v_dem NUMBER;

     BEFORE STATEMENT IS
     BEGIN
       NULL;
     END BEFORE STATEMENT;
   
     BEFORE EACH ROW IS
     BEGIN
          SELECT EXTRACT(YEAR FROM :OLD.NgayThanhToan), EXTRACT(MONTH FROM :OLD.NgayThanhToan) INTO v_nam,v_thang
          FROM dual;
          SELECT Giatien INTO giave 
          FROM VE v
          WHERE V.MaVE = :OLD.MaVE;
     END BEFORE EACH ROW;
   
     AFTER EACH ROW IS
     BEGIN
       NULL;
     END AFTER EACH ROW;
   
     AFTER STATEMENT IS
     BEGIN                           
             UPDATE BAOCAOTHANG
             SET DoanhThu = DoanhThu - GiaVe,
                 SoVeDaBan = SoVeDaBan - 1
             WHERE nam = v_Nam AND thang = v_Thang;       
             
             UPDATE BAOCAONAM
             SET doanhthu = doanhthu - giave
             WHERE nam = v_nam;                      
     END AFTER STATEMENT;

END Update_DoanhThu_Thang_delete;


/* R8: Mỗi vé phải thuộc một hạng vé. */ 

-- không cần trigger insert vì đã cho khóa ngoại MaHangVe

CREATE OR REPLACE TRIGGER trigger_delete_hangve
BEFORE DELETE ON HANGVE
FOR EACH ROW
DECLARE
    v_Ve_Count INTEGER;
BEGIN   
    SELECT COUNT(*) INTO v_Ve_Count FROM VE WHERE MaHangVe = :OLD.MaHangVe;
    
    IF v_Ve_Count > 0 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Không thể xóa hạng vé này vì có vé thuộc vào.');
    END IF;
END;
/


/* R9: Mỗi vé phải đặt trước một khoảng thời gian nhất định trước khi máy bay xuất phát. */ 

DROP TRIGGER trigger_CT_DATVE

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
 





/* R15:  Giá tiền của Vé là một số không âm. */

ALTER TABLE VE
ADD CONSTRAINT Check_GiaTien_ CHECK (GiaTien >= 0);


/* R16: Trạng thái là chưa đổi hoặc đã đổi */

ALTER TABLE HANGVE
ADD CONSTRAINT Check_TrangThai_HV CHECK (TrangThai IN (0,1));


/* R17.1: Trạng thái mặc định ban đầu là chưa đổi */ 

ALTER TABLE HANGVE
MODIFY TrangThai DEFAULT 1;


/* R17.2: Trạng thái mặc định ban đầu là chưa đổi */ 

ALTER TABLE CT_DATVE
MODIFY TrangThai DEFAULT 1;



/* R19: Số vé là một số không âm. */

ALTER TABLE baocaothang
ADD CONSTRAINT Check_SoVe CHECK (SoVeDaBan >= 0);


/* R20: Doanh thu là một số không âm. */ 

ALTER TABLE baocaothang
ADD CONSTRAINT Check_DoanhThu CHECK (DoanhThu >= 0);


/* R21: Doanh thu mặc định là 0. */

ALTER TABLE BAOCAONAM
MODIFY DoanhThu DEFAULT 0;


/* R22: So Ve mặc định là 0.  */

ALTER TABLE baocaothang
MODIFY SoVeDaBan DEFAULT 0;


/* R24: Thời gian bay phải không nhỏ hơn thời gian bay tối thiểu. */

DROP TRIGGER Check_ThoiGianXuatPhat

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


INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, SoChuyenBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB009', 'DB001', 1, TO_TIMESTAMP('2024-07-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-07-03 12:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'ChuaDoi', 1000000);


/* R25: sân bay đến và sân bay đi của đường bay không trùng nhau */

DROP TRIGGER Check_DuongBay

CREATE OR REPLACE TRIGGER Check_DuongBay
BEFORE INSERT OR UPDATE ON DUONGBAY
FOR EACH ROW
BEGIN
    IF :NEW.MaSanBayDi = :NEW.MaSanBayDen THEN
        RAISE_APPLICATION_ERROR(-20009, 'Sân bay đi và sân bay đến không được trùng nhau.');
    END IF;
END;
/




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




-- TABLE: DUONGBAY

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay)
VALUES ('DB001', 'SBD002', 'SBD001', 'HAN-SGN', 1200);

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay)
VALUES ('DB002', 'SBD002', 'SBD003', 'HAN-VCL', 9800);

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay)
VALUES ('DB003', 'SBD003', 'SBD002', 'VCL-HAN', 9800);

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay)
VALUES ('DB004', 'SBD002', 'SBD005','HAN-DAD', 6700);

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay)
VALUES ('DB005', 'SBD002', 'SBD006','HAN-UIH', 9800);

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay)
VALUES ('DB006', 'SBD006', 'SBD002', 'UIH-HAN', 9800);

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay)
VALUES ('DB007', 'SBD003', 'SBD004','VCL-PQC', 9800);



-- TABLE : CHUYENBAY

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB001', 'DB001', TO_TIMESTAMP('2024-07-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-07-02 12:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'ChuaDoi', 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB002', 'DB002', TO_TIMESTAMP('2024-08-15 14:15:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-08-16 16:45:00', 'YYYY-MM-DD HH24:MI:SS'), 'ChuaDoi', 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB003', 'DB003', TO_TIMESTAMP('2024-09-20 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-09-21 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'ChuaDoi', 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB004', 'DB001', TO_TIMESTAMP('2024-10-10 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-10-11 20:45:00', 'YYYY-MM-DD HH24:MI:SS'), 'ChuaDoi', 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB005', 'DB002', TO_TIMESTAMP('2024-11-25 06:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-11-26 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'ChuaDoi', 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB006', 'DB003', TO_TIMESTAMP('2023-12-30 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-12-31 01:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'ChuaDoi', 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB007', 'DB001', TO_TIMESTAMP('2024-06-15 07:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-06-16 10:15:00', 'YYYY-MM-DD HH24:MI:SS'), 'ChuaDoi', 1000000);



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

INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien)
VALUES ('VE001', 'CB001', 'HV001', 1, 1000000);

INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien)
VALUES ('VE002', 'CB001', 'HV002', 2, 1500000);

INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien)
VALUES ('VE003', 'CB002', 'HV001', 3, 1200000);

INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien)
VALUES ('VE004', 'CB002', 'HV002', 4, 1800000);


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
VALUES ('CB001', 'HV001', 40, 10);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB001', 'HV002', 30, 20);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB002', 'HV002', 50, 10);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB003', 'HV001', 60, 10);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB003', 'HV002', 55, 15);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB004', 'HV001', 40, 20);



-- TABLE : CT_DATVE

INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV001', 'VE001', 'KH001', TO_TIMESTAMP('2024-05-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-05-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);

INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV002', 'VE002', 'KH001', TO_TIMESTAMP('2024-05-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-05-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);

INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV003', 'VE003', 'KH002', TO_TIMESTAMP('2024-05-03 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-05-04 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);

INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV004', 'VE004', 'KH002', TO_TIMESTAMP('2024-05-03 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-05-04 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);



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

/* 
-- Xóa dữ liệu trong bảng CT_DATVE
DELETE FROM CT_DATVE;

-- Xóa dữ liệu trong bảng CT_HANGVE
DELETE FROM CT_HANGVE;

-- Xóa dữ liệu trong bảng SANBAYTRUNGGIAN
DELETE FROM SANBAYTRUNGGIAN;

-- Xóa dữ liệu trong bảng KHACHHANG
DELETE FROM KHACHHANG;
s
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

*/
