package com.mycompany.librarysystem.service.impl;

import com.mycompany.librarysystem.domain.Report;
import com.mycompany.librarysystem.repository.ReportRepository;
import com.mycompany.librarysystem.service.ReportService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public List<Report> getReportsBetweenDates(LocalDateTime start, LocalDateTime end) {
        return reportRepository.findAllByBorrowedStartDateBetween(start, end);
    }

    @Override
    public byte[] getReportsExcelFile(LocalDateTime start, LocalDateTime end) {
        List<Report> reports = reportRepository.findAllByBorrowedStartDateBetween(start, end);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Report");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("National-Code");
        headerRow.createCell(1).setCellValue("Book Number");
        headerRow.createCell(2).setCellValue("Borrow Date");
        headerRow.createCell(3).setCellValue("Return Date");
        int rowNum = 1;
        for (Report report : reports) {
            Row dataRow = sheet.createRow(rowNum++);
            dataRow.createCell(0).setCellValue(report.getNationalCode());
            dataRow.createCell(1).setCellValue(report.getBookNumber());
            dataRow.createCell(2).setCellValue(report.getBorrowedStartDate() != null ? report.getBorrowedStartDate().toString() : "Not borrowed yet");
            dataRow.createCell(3).setCellValue(report.getBorrowedEndDate() != null ? report.getBorrowedEndDate().toString() : "Not returned yet");
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  outputStream.toByteArray();
    }
}
