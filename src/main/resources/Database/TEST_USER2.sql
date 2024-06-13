ALTER SESSION SET CURRENT_SCHEMA = fly_the_end12B;


set transaction isolation level read committed;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
set AUTOCOMMIT off;
set AUTOCOMMIT on;
commit;

EXECUTE update_ticket_status('CTDV05001', 2);

SELECT *
FROM BAOCAOTHANG 
WHERE Thang = 6  
AND Nam = 2024
AND MACHUYENBAY = 'CB047';

--  Bán 1 vé 
DECLARE
    v_newMaVe VARCHAR2(10);
    v_newMaGhe NUMBER;
    v_maChuyenBay VARCHAR2(10) := 'CB021';
    v_maHangVe VARCHAR2(10) := 'HV001';
    v_giaTien NUMBER := 1100000;
    v_newMaCT_DATVE VARCHAR2(10);
BEGIN
    GENERATE_MA_VE(v_newMaVe);
    GENERATE_MA_GHE(v_newMaGhe);
    GENERATE_MA_CT_DATVE(v_newMaCT_DATVE);
    SAVE_TICKET(v_newMaVe, v_maChuyenBay, v_maHangVe, TO_CHAR(v_newMaGhe), v_giaTien);
    INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
    VALUES (
        v_newMaCT_DATVE,
        v_newMaVe,
        'KH001',
        SYSTIMESTAMP,
        SYSTIMESTAMP,
        1
    );

    COMMIT;
END;
/

----------------------------------------------------------------------------------------
-- NON Hiếu
set transaction isolation level read committed;

UPDATE CT_DATVE 
SET TrangThai = 2 WHERE MaVe = 'VE04701';

SELECT MaChuyenBay, SoVeDaBan, DoanhThu
FROM BAOCAOTHANG 
WHERE Thang = 6  
AND Nam = 2024;

commit;

-- giải pháp

-- User 2 cố gắng cập nhật dữ liệu
BEGIN
    UPDATE CT_DATVE 
    SET TrangThai = 2 
    WHERE MaVe = 'VE05001';

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE(SQLERRM);
        ROLLBACK;
END;

----------------------------------------------------------------------------------------
-- Phantom Trinh


-------------------------------------------------------------------
-- Lost update Trung
-- Thiết lập mức độ cách ly giao dịch SERIALIZABLE
set transaction isolation level read committed;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
set AUTOCOMMIT off;
commit;

Select trangthai 
from ct_datve 
where mact_datve = 'CTDV04702';

select * from baocaothang 
WHERE machuyenbay = 'CB047' AND thang = 6 AND nam = 2024;

DELETE FROM CT_DATVE WHERE MaCT_DATVE = 'CTDV04702';

EXECUTE update_ticket_status('CTDV04702', 1);

SELECT * FROM VE WHERE MAVE = 'VE05310';
SELECT * FROM CT_DATVE WHERE TRANGTHAI = 0 AND MAVE = 'VE05310';
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai) VALUES ('CTDV05330', 'VE05304', 'KH001', TO_TIMESTAMP('2024-02-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'),null, 0);


-------------------------------------------------------------------
-- TEST CHÍNH
set transaction isolation level read committed;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
set AUTOCOMMIT off;
set AUTOCOMMIT on;
commit;

Exec UPDATE_TICKET_STATUS('CTDV05002',1);

update hangve
set trangthai = 0
where mahangve
='HV002';

update hangve
set trangthai = 0
where mahangve
='HV001';

-------------------------------------------------------------------
-- TEST LOST UPDATE
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SET AUTOCOMMIT OFF;
SET AUTOCOMMIT ON;
COMMIT;

SELECT * FROM CT_DATVE 
WHERE MACT_DATVE = 'CTDV05210';


SELECT * FROM BAOCAOTHANG
WHERE THANG = 6 AND NAM = 2024
AND MACHUYENBAY = 'CB052';

EXECUTE UPDATE_TICKET_STATUS('CTDV05210', 1);
EXECUTE UPDATE_TICKET_STATUS('CTDV05211', 1);

-------------------------------------------------------------------
-- TEST NON-REPEATABLE READ
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SET AUTOCOMMIT OFF;
SET AUTOCOMMIT ON;
COMMIT;

EXECUTE UPDATE_TICKET_STATUS('CTDV05201', 2);
EXECUTE UPDATE_TICKET_STATUS('CTDV05205', 2);


-------------------------------------------------------------------
-- TEST PHANTOM READ
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SET AUTOCOMMIT OFF;
SET AUTOCOMMIT ON;
COMMIT;

SELECT *
FROM BAOCAOTHANG 
WHERE Thang = 6  
AND Nam = 2024;

--  Bán 1 vé 
DECLARE
    v_newMaVe VARCHAR2(10);
    v_newMaGhe NUMBER;
    v_maChuyenBay VARCHAR2(10) := 'CB021';
    v_maHangVe VARCHAR2(10) := 'HV001';
    v_giaTien NUMBER := 1100000;
    v_newMaCT_DATVE VARCHAR2(10);
BEGIN
    GENERATE_MA_VE(v_newMaVe);
    GENERATE_MA_GHE(v_newMaGhe);
    GENERATE_MA_CT_DATVE(v_newMaCT_DATVE);
    SAVE_TICKET(v_newMaVe, v_maChuyenBay, v_maHangVe, TO_CHAR(v_newMaGhe), v_giaTien);
    INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
    VALUES (
        v_newMaCT_DATVE,
        v_newMaVe,
        'KH001',
        SYSTIMESTAMP,
        SYSTIMESTAMP,
        1
    );

    COMMIT;
END;
/
-- CB022, 1150000

-------------------------------------------------------------------
-- TEST DEADLOCK
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SET AUTOCOMMIT OFF;
SET AUTOCOMMIT ON;
COMMIT;

update hangve
set trangthai = 0
where mahangve
='HV002';

update hangve
set trangthai = 0
where mahangve
='HV001';