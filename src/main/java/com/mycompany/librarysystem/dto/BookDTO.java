package com.mycompany.librarysystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {
    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "bookNumber")
    private Long bookNumber;
    @JsonProperty(value = "publishedYear")
    private Year publishedYear;
    @JsonProperty(value = "isBorrowed")
    private Boolean isBorrowed;
    @JsonProperty(value = "authors")
    private List<AuthorDTO> authors = new ArrayList<>();
    @JsonProperty(value = "translators")
    private List<TranslatorDTO> translators = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id) && Objects.equals(bookNumber, bookDTO.bookNumber) && Objects.equals(publishedYear, bookDTO.publishedYear) && Objects.equals(isBorrowed, bookDTO.isBorrowed) && Objects.equals(authors, bookDTO.authors) && Objects.equals(translators, bookDTO.translators);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookNumber, publishedYear, isBorrowed, authors, translators);
    }

    @Override
    public String toString() {
        return "BookDTO{" +
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

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }

    public List<TranslatorDTO> getTranslators() {
        return translators;
    }

    public void setTranslators(List<TranslatorDTO> translators) {
        this.translators = translators;
    }
}
