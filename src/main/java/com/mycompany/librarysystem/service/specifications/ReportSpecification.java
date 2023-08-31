package com.mycompany.librarysystem.service.specifications;

import com.mycompany.librarysystem.domain.Book;
import org.springframework.data.jpa.domain.Specification;

public class ReportSpecification {

    public static Specification<Book> hasMemberNationalCode(String nationalCode) {
        return (root, query, builder) ->
                builder.like(root.get("nationalCode"), "%" + nationalCode + "%");
    }

    public static Specification<Book> hasBookNumber(String bookNumber) {
        return (root, query, builder) ->
                builder.like(root.get("bookNumber"), "%" + bookNumber + "%");
    }
}
