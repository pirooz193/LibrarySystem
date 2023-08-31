package com.mycompany.librarysystem.dto.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.Objects;

public class BorrowRequestModel {

    @NonNull
    @JsonProperty("memberId")
    private Long memberId;
    @NonNull
    @JsonProperty("bookNumber")
    private Long bookNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowRequestModel that = (BorrowRequestModel) o;
        return Objects.equals(memberId, that.memberId) && Objects.equals(bookNumber, that.bookNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, bookNumber);
    }

    @Override
    public String toString() {
        return "BorrowRequestModel{" +
                "memberId=" + memberId +
                ", bookNumber=" + bookNumber +
                '}';
    }

    @NonNull
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(@NonNull Long memberId) {
        this.memberId = memberId;
    }

    @NonNull
    public Long getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(@NonNull Long bookNumber) {
        this.bookNumber = bookNumber;
    }
}
