package com.mycompany.librarysystem.domain;

import jakarta.persistence.*;

import java.time.Year;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_number", unique = true)
    private Long bookNumber;

    @Column(name = "title" , nullable = false , length = 400)
    private String title;
    @Column(name = "published_year")
    private Year publishedYear;

    @Column(name = "is_borrowed",columnDefinition = "boolean default false", length = 5)
    private boolean isBorrowed;

    @OneToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "book_translator",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "translator_id"))
    private Set<Translator> translators = new HashSet<>();

    public Book() {
        this.isBorrowed = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(bookNumber, book.bookNumber) && Objects.equals(title, book.title) && Objects.equals(publishedYear, book.publishedYear) && Objects.equals(isBorrowed, book.isBorrowed) && Objects.equals(authors, book.authors) && Objects.equals(translators, book.translators);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookNumber, title, publishedYear, isBorrowed, authors, translators);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookNumber=" + bookNumber +
                ", title='" + title + '\'' +
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Year getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Year publishedYear) {
        this.publishedYear = publishedYear;
    }

    public boolean getBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Translator> getTranslators() {
        return translators;
    }

    public void setTranslators(Set<Translator> translators) {
        this.translators = translators;
    }
}
