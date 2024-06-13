ALTER SESSION SET CURRENT_SCHEMA = fly_the_end12B;

set transaction isolation level read committed;

SELECT * 
FROM BAOCAOTHANG
WHERE THANG = 6
AND NAM = 2024;
-------------------------------------------------------------------
-- NON HIẾU

set transaction isolation level read committed;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
set AUTOCOMMIT on;
commit;

SELECT *
FROM BAOCAOTHANG 
WHERE Thang = 6  
AND Nam = 2024;


SELECT * FROM VE WHERE MACHUYENBAY = 'CB052';
commit;

-- giải pháp

-- Thiết lập mức độ cách ly giao dịch SERIALIZABLE
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
set AUTOCOMMIT off;

BEGIN
    -- Đọc dữ liệu và khóa các hàng để ngăn chặn các giao dịch khác thay đổi chúng
    FOR rec IN (SELECT *
                FROM BAOCAOTHANG
                WHERE Thang = 6
                AND Nam = 2024
                FOR UPDATE) LOOP
        DBMS_OUTPUT.PUT_LINE('MaChuyenBay: ' || rec.MaChuyenBay || ', SoVeDaBan: ' || rec.SoVeDaBan || ', DoanhThu: ' || rec.DoanhThu);
    END LOOP;
    
    -- Giữ giao dịch mở trong 10s để mô phỏng việc đọc dữ liệu trong thời gian dài
    DBMS_LOCK.SLEEP(10);
    
    -- Hoàn tất giao dịch
    COMMIT;
END;

-------------------------------------------------------------------
-- Phantom Trinh
-- Y như trên


-------------------------------------------------------------------
-- Lost update Trung
-- Thiết lập mức độ cách ly giao dịch SERIALIZABLE
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SET AUTOCOMMIT OFF;
SET AUTOCOMMIT ON;


SELECT * FROM CT_DATVE WHERE MACT_DATVE = 'CTDV05302';
select * from baocaothang 
WHERE machuyenbay = 'CB047' AND thang = 6 AND nam = 2024;


EXECUTE update_ticket_status('CTDV05302', 2);


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

Select trangthai
from ct_datve
where mact_datve ='CTDV05002';

select * from baocaothang WHERE thang = 6 AND nam = 2024
AND machuyenbay = 'CB052';

Exec UPDATE_TICKET_STATUS('CTDV05002',2);

update hangve
set trangthai = 0
where mahangve
='HV001';

update hangve
set trangthai = 0
where mahangve
='HV002';

commit;


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

EXECUTE UPDATE_TICKET_STATUS('CTDV05210', 2);
EXECUTE UPDATE_TICKET_STATUS('CTDV05211', 2);


-------------------------------------------------------------------
-- TEST NON-REPEATABLE READ
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SET AUTOCOMMIT OFF;
SET AUTOCOMMIT ON;
COMMIT;

SELECT *
FROM BAOCAOTHANG 
WHERE Thang = 6  
AND Nam = 2024;

SELECT * FROM CT_DATVE 
     JOIN VE
     ON VE.mAvE = CT_DATVE.MAVE
WHERE EXTRACT(YEAR FROM NGAYTHANHTOAN) = 2024 AND EXTRACT(MONTH FROM NGAYTHANHTOAN) = 6;

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

SELECT * FROM CHUYENBAY WHERE MACHUYENBAY = 'CB022';
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
='HV001';

update hangve
set trangthai = 0
where mahangve
='HV002';