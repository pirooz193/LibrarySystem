package com.mycompany.librarysystem.service;

import com.mycompany.librarysystem.domain.Report;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    List<Report> getReportsBetweenDates(LocalDateTime start, LocalDateTime end);
}
