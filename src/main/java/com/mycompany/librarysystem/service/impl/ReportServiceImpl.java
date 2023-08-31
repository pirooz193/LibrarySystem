package com.mycompany.librarysystem.service.impl;

import com.mycompany.librarysystem.domain.Report;
import com.mycompany.librarysystem.repository.ReportRepository;
import com.mycompany.librarysystem.service.ReportService;
import org.springframework.stereotype.Service;

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
}
