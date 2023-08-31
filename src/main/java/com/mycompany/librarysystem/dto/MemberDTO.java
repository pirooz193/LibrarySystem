package com.mycompany.librarysystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.librarysystem.domain.Book;
import com.mycompany.librarysystem.domain.enums.GenderType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDTO extends PersonDTO {
    @JsonProperty(value = "membershipNumber")
    private Long membershipNumber;
    @JsonProperty(value = "fatherName")
    private String fatherName;
    @JsonProperty(value = "birthDate")
    private LocalDate birthDate;
    @JsonProperty(value = "membershipDate")
    private LocalDateTime membershipDate;
    @JsonProperty(value = "isActive")
    private Boolean isActive;
    @JsonProperty(value = "gender")
    private GenderType gender;
    @JsonProperty(value = "borrowedBooks")
    private List<Book> borrowedBooks = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDTO memberDTO = (MemberDTO) o;
        return Objects.equals(membershipNumber, memberDTO.membershipNumber) && Objects.equals(fatherName, memberDTO.fatherName) && Objects.equals(birthDate, memberDTO.birthDate) && Objects.equals(membershipDate, memberDTO.membershipDate) && Objects.equals(isActive, memberDTO.isActive) && gender == memberDTO.gender && Objects.equals(borrowedBooks, memberDTO.borrowedBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(membershipNumber, fatherName, birthDate, membershipDate, isActive, gender, borrowedBooks);
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "membershipNumber=" + membershipNumber +
                ", fatherName='" + fatherName + '\'' +
                ", birthDate=" + birthDate +
                ", membershipDate=" + membershipDate +
                ", isActive=" + isActive +
                ", gender=" + gender +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }

    public Long getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(Long membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDateTime membershipDate) {
        this.membershipDate = membershipDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
