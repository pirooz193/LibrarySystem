package com.mycompany.librarysystem.dto.criteria;

import java.io.Serializable;
import java.util.Objects;

public class BookCriteria implements Serializable {

    private String authorName;
    private String translatorName;
    private String title;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTranslatorName() {
        return translatorName;
    }

    public void setTranslatorName(String translatorName) {
        this.translatorName = translatorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // You might want to override hashCode() and equals() here if needed

    @Override
    public int hashCode() {
        return Objects.hash(authorName, translatorName, title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookCriteria that = (BookCriteria) obj;
        return Objects.equals(authorName, that.authorName) &&
                Objects.equals(translatorName, that.translatorName) &&
                Objects.equals(title, that.title);
    }

    @Override
    public String toString() {
        return "BookCriteria{" +
                "authorName='" + authorName + '\'' +
                ", translatorName='" + translatorName + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
