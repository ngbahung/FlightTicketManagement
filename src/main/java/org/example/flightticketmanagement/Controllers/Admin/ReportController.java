package org.example.flightticketmanagement.Controllers.Admin;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.example.flightticketmanagement.Controllers.AlertMessage;
import org.example.flightticketmanagement.Models.BaoCaoNam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportController {
    private final AlertMessage alert = new AlertMessage();
    public void PrintReportBaoCaoNam(Integer namBaoCao, List<BaoCaoNam> listBaoCaoNam) {
        try {
            String fileJRXMLPath = "src/main/resources/Report/DT_Nam_Report.jrxml";

            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("namBaoCao", namBaoCao);
            parameters.put("ngayLapBaoCao", "Thứ 4 22/05/2024");

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listBaoCaoNam);

            JasperReport report = JasperCompileManager.compileReport(fileJRXMLPath);

            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

            JasperExportManager.exportReportToPdfFile(print, ChonDuongDanLuuFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void PrintReportBaoCaoThang(Integer namBaoCao, List<BaoCaoNam> listBaoCaoNam) {
        try {
            String fileJRXMLPath = "src/main/resources/Report/DT_Thang_Report.jrxml";

            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("namBaoCao", namBaoCao);
            parameters.put("ngayLapBaoCao", "Thứ 4 22/05/2024");

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listBaoCaoNam);

            JasperReport report = JasperCompileManager.compileReport(fileJRXMLPath);

            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

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
