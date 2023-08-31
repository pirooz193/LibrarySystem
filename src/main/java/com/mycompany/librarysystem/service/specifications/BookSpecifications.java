package com.mycompany.librarysystem.service.specifications;

import com.mycompany.librarysystem.domain.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {
    public static Specification<Book> hasAuthorName(String authorName) {
        return (root, query, builder) ->
                builder.like(root.join("authors").get("name"), "%" + authorName + "%");
    }

    public static Specification<Book> hasTranslatorName(String translatorName) {
        return (root, query, builder) ->
                builder.like(root.join("translators").get("name"), "%" + translatorName + "%");
    }

    public static Specification<Book> hasBookName(String bookName) {
        return (root, query, builder) ->
                builder.like(root.get("title"), "%" + bookName + "%");
    }
}
