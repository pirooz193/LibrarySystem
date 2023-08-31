package com.mycompany.librarysystem.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     @Column(name = "national_code" , nullable = false, length = 50)
    private String nationalCode;
    @Column(name = "book_number" , nullable = false, length = 50)
    private Long bookNumber;
    @Column(name = "borrowed_start_date", nullable = false,length = 50)
    private LocalDateTime borrowedStartDate;
    @Column(name = "borrowed_end_date",length = 50)
    private LocalDateTime borrowedEndDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.equals(nationalCode, report.nationalCode) && Objects.equals(bookNumber, report.bookNumber) && Objects.equals(borrowedStartDate, report.borrowedStartDate) && Objects.equals(borrowedEndDate, report.borrowedEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nationalCode, bookNumber, borrowedStartDate, borrowedEndDate);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", nationalCode='" + nationalCode + '\'' +
                ", bookNumber=" + bookNumber +
                ", borrowedStartDate=" + borrowedStartDate +
                ", borrowedEndDate=" + borrowedEndDate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Long getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Long bookNumber) {
        this.bookNumber = bookNumber;
    }

    public LocalDateTime getBorrowedStartDate() {
        return borrowedStartDate;
    }

    public void setBorrowedStartDate(LocalDateTime borrowedStartDate) {
        this.borrowedStartDate = borrowedStartDate;
    }

    public LocalDateTime getBorrowedEndDate() {
        return borrowedEndDate;
    }

    public void setBorrowedEndDate(LocalDateTime borrowedEndDate) {
        this.borrowedEndDate = borrowedEndDate;
    }
}
