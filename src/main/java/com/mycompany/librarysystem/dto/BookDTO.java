package com.mycompany.librarysystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Year;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {
    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "bookNumber")
    private Long bookNumber;

    @JsonProperty(value = "title")
    private String title;
    @JsonProperty(value = "publishedYear")
    private Year publishedYear;
    @JsonProperty(value = "isBorrowed")
    private boolean isBorrowed;
    @JsonProperty(value = "authors")
    private Set<AuthorDTO> authors = new HashSet<>();
    @JsonProperty(value = "translators")
    private Set<TranslatorDTO> translators = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id) && Objects.equals(bookNumber, bookDTO.bookNumber) && Objects.equals(title, bookDTO.title) && Objects.equals(publishedYear, bookDTO.publishedYear) && Objects.equals(isBorrowed, bookDTO.isBorrowed) && Objects.equals(authors, bookDTO.authors) && Objects.equals(translators, bookDTO.translators);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookNumber, title, publishedYear, isBorrowed, authors, translators);
    }

    @Override
    public String toString() {
        return "BookDTO{" +
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

    public Set<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDTO> authors) {
        this.authors = authors;
    }

    public Set<TranslatorDTO> getTranslators() {
        return translators;
    }

    public void setTranslators(Set<TranslatorDTO> translators) {
        this.translators = translators;
    }
}
