package com.mycompany.librarysystem.repository;

import com.mycompany.librarysystem.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    Report findReportByBookNumberAndNationalCode(Long bookNumber, String nationalCode);

    List<Report> findAllByBorrowedStartDateBetween(LocalDateTime start, LocalDateTime end);
}
