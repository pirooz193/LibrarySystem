package com.mycompany.librarysystem.dto.criteria;

import java.time.LocalDate;
import java.util.Objects;

public class MemberCriteria {

    private String name;
    private String lastName;
    private LocalDate membershipDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberCriteria that = (MemberCriteria) o;
        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(membershipDate, that.membershipDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, membershipDate);
    }

    @Override
    public String toString() {
        return "MemberCriteria{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", membershipDate=" + membershipDate +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }
}
