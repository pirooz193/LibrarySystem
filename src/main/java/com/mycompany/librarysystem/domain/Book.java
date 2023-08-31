package com.mycompany.librarysystem.domain;

import jakarta.persistence.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_number")
    private Long bookNumber;
    @Column(name = "published_year")
    private Year publishedYear;

    @Column(name = "is_borrowed", nullable = false, length = 5)
    private Boolean isBorrowed;

    @OneToMany
    private List<Author> authors = new ArrayList<>();

    @OneToMany
    private List<Translator> translators = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(bookNumber, book.bookNumber) && Objects.equals(publishedYear, book.publishedYear) && Objects.equals(isBorrowed, book.isBorrowed) && Objects.equals(authors, book.authors) && Objects.equals(translators, book.translators);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookNumber, publishedYear, isBorrowed, authors, translators);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookNumber=" + bookNumber +
                ", publishedYear=" + publishedYear +
                ", isBorrowed=" + isBorrowed +
                ", authors=" + authors +
                ", translators=" + translators +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Long bookNumber) {
        this.bookNumber = bookNumber;
    }

    public Year getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Year publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Boolean getBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(Boolean borrowed) {
        isBorrowed = borrowed;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Translator> getTranslators() {
        return translators;
    }

    public void setTranslators(List<Translator> translators) {
        this.translators = translators;
    }
}
