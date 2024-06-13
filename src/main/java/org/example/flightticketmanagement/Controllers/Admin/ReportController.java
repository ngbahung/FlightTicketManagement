package org.example.flightticketmanagement.Controllers.Admin;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.BaoCaoNam;
import org.example.flightticketmanagement.Models.BaoCaoThang;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportController {
    private final AlertMessage alert = new AlertMessage();
    public void PrintReportBaoCaoNam(Integer namBaoCao, List<BaoCaoNam> listBaoCaoNam, BigDecimal tongDoanhThuNam) {
        try {
            String fileJRXMLPath = "src/main/resources/Report/DT_Nam_Report.jrxml";
            String ngayLapBaoCao = LocalDateTime.now()+"";
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("namBaoCao", namBaoCao);
            parameters.put("ngayLapBaoCao", ngayLapBaoCao);
            parameters.put("tongDoanhThuNam", tongDoanhThuNam);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listBaoCaoNam);

            JasperReport report = JasperCompileManager.compileReport(fileJRXMLPath);

            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

            JasperExportManager.exportReportToPdfFile(print, ChonDuongDanLuuFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void PrintReportBaoCaoThang(Integer namBaoCao,Integer thangBaoCao, List<BaoCaoThang> listBaoCaoThang, BigDecimal tongDoanhThuThang){
        try {
            String fileJRXMLPath = "src/main/resources/Report/DT_Thang_Report.jrxml";
            String ngayLapBaoCao = LocalDateTime.now()+"";
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("namBaoCao", namBaoCao);
            parameters.put("thangBaoCao", thangBaoCao);
            parameters.put("ngayLapBaoCao", ngayLapBaoCao);
            parameters.put("tongDoanhThuThang", tongDoanhThuThang);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listBaoCaoThang);

            JasperReport report = JasperCompileManager.compileReport(fileJRXMLPath);

            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

            JasperExportManager.exportReportToPdfFile(print, ChonDuongDanLuuFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void PrintVe(String maVe, String tenKhachHang, String SDT, String maGhe, String hangVe, String giaVe, String sanBayDi, String sanBayDen, String ngayBay, String gioBay){
        try {
            String fileJRXMLPath = "src/main/resources/Report/XuatVe.jrxml";
            String ngayLapBaoCao = LocalDateTime.now()+"";
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("maVe", maVe);
            parameters.put("hangVe", hangVe);
            parameters.put("ngayLapBaoCao", ngayLapBaoCao);
            parameters.put("maGhe", maGhe);
            parameters.put("gioBay", gioBay);
            parameters.put("ngayBay",ngayBay);
            parameters.put("sanBayDi", sanBayDi);
            parameters.put("sanBayDen", sanBayDen);
            parameters.put("hoTen", tenKhachHang);
            parameters.put("sdt", SDT);
            DecimalFormat df = new DecimalFormat("#,###");
            Double numericGiaVe = Double.parseDouble(giaVe);
            String formattedGiaVe = df.format(numericGiaVe);
            parameters.put("giaVe", formattedGiaVe);


            JasperReport report = JasperCompileManager.compileReport(fileJRXMLPath);

            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

            JasperExportManager.exportReportToPdfFile(print, ChonDuongDanLuuFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String ChonDuongDanLuuFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        // Hiển thị hộp thoại lưu file
        java.io.File file = fileChooser.showSaveDialog(new Stage());
        return file.getAbsolutePath();
    }
}
