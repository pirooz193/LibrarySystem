package com.mycompany.librarysystem.domain;

import com.mycompany.librarysystem.domain.enums.GenderType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "members")
@Entity
public class Member extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "membership_number", unique = true, length = 50, nullable = false)
    private Long membershipNumber;

    @Column(name = "father_name", length = 100)
    private String fatherName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "membership_date", nullable = false)
    private LocalDateTime membershipDate;
    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 15, nullable = false)
    private GenderType gender;

    @OneToMany
    private List<Book> borrowedBooks = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) && Objects.equals(membershipNumber, member.membershipNumber) && Objects.equals(fatherName, member.fatherName) && Objects.equals(birthDate, member.birthDate) && Objects.equals(membershipDate, member.membershipDate) && Objects.equals(isActive, member.isActive) && gender == member.gender && Objects.equals(borrowedBooks, member.borrowedBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, membershipNumber, fatherName, birthDate, membershipDate, isActive, gender, borrowedBooks);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", membershipNumber=" + membershipNumber +
                ", fatherName='" + fatherName + '\'' +
                ", birthDate=" + birthDate +
                ", membershipDate=" + membershipDate +
                ", isActive=" + isActive +
                ", gender=" + gender +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
