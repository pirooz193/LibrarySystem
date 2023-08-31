package com.mycompany.librarysystem.web.rest;

import com.mycompany.librarysystem.domain.Book;
import com.mycompany.librarysystem.dto.BookDTO;
import com.mycompany.librarysystem.dto.criteria.BookCriteria;
import com.mycompany.librarysystem.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        BookDTO createdBook = bookService.createBook(bookDTO);
        return ResponseEntity.created(URI.create("/api/book")).body(createdBook);
    }

    @PatchMapping("{bookId}/add-author/{authorId}")
    public ResponseEntity<BookDTO> addAuthorToRequiredBook(@PathVariable Long bookId,@PathVariable Long authorId) {
        BookDTO requiredBook = bookService.addAuthorToRequiredBook(bookId, authorId);
        return ResponseEntity.ok(requiredBook);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getRequiredBook(@PathVariable Long bookId) {
        BookDTO requiredBook = bookService.getRequiredBook(bookId);
        return ResponseEntity.ok(requiredBook);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(@ModelAttribute BookCriteria criteria, Pageable pageable) {
        if (criteria == null) {
            return ResponseEntity.ok(bookService.getAllBooks());
        }
        Page<Book> books = bookService.searchBooks(criteria, pageable);
        return ResponseEntity.ok(books.getContent());
    }
}
