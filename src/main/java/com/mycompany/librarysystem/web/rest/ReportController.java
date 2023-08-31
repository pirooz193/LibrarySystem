package com.mycompany.librarysystem.web.rest;

import com.mycompany.librarysystem.domain.Report;
import com.mycompany.librarysystem.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<List<Report>> getReportBetweenTwoDates(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        List<Report> reports = reportService.getReportsBetweenDates(start, end);
        return ResponseEntity.ok(reports);
    }
}
