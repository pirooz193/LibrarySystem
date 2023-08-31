package com.mycompany.librarysystem.service;

import com.mycompany.librarysystem.domain.Book;
import com.mycompany.librarysystem.dto.BookDTO;
import com.mycompany.librarysystem.dto.criteria.BookCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    BookDTO createBook(BookDTO bookDTO);

    BookDTO getRequiredBook(Long bookId);

    Page<Book> searchBooks(BookCriteria criteria, Pageable pageable);

    List<Book> getAllBooks();

    BookDTO addAuthorToRequiredBook(Long bookNumber,Long authorId);
}
