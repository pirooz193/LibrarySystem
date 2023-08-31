package com.mycompany.librarysystem.service.impl;

import com.mycompany.librarysystem.domain.Author;
import com.mycompany.librarysystem.domain.Book;
import com.mycompany.librarysystem.domain.Constants;
import com.mycompany.librarysystem.dto.BookDTO;
import com.mycompany.librarysystem.dto.criteria.BookCriteria;
import com.mycompany.librarysystem.repository.AuthorRepository;
import com.mycompany.librarysystem.repository.BookRepository;
import com.mycompany.librarysystem.service.BookService;
import com.mycompany.librarysystem.service.mapper.BookMapper;
import com.mycompany.librarysystem.service.specifications.BookSpecifications;
import com.mycompany.librarysystem.web.error.DuplicateBookException;
import com.mycompany.librarysystem.web.error.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceIml implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public BookServiceIml(BookRepository bookRepository, AuthorRepository authorRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        book.setBorrowed(false);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    @Override
    public BookDTO getRequiredBook(Long bookId) {
        Book requiredBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException(Constants.BOOK +bookId));
        return bookMapper.toDTO(requiredBook);
    }

    public Page<Book> searchBooks(BookCriteria criteria, Pageable pageable) {
        Specification<Book> spec = Specification.where(null);

        if (criteria.getAuthorName() != null && !criteria.getAuthorName().isEmpty()) {
            spec = spec.or(BookSpecifications.hasAuthorName(criteria.getAuthorName()));
        }
        if (criteria.getTranslatorName() != null && !criteria.getTranslatorName().isEmpty()) {
            spec = spec.or(BookSpecifications.hasTranslatorName(criteria.getTranslatorName()));
        }
        if (criteria.getTitle() != null && !criteria.getTitle().isEmpty()) {
            spec = spec.or(BookSpecifications.hasBookName(criteria.getTitle()));
        }
        return bookRepository.findAll(spec, pageable);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public BookDTO addAuthorToRequiredBook(Long bookNumber, Long authorId) {
        Book requiredBook = bookRepository.findById(bookNumber)
                .orElseThrow(() -> new NotFoundException(Constants.BOOK + bookNumber));
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException(Constants.AUTHOR + authorId));
        if (requiredBook.getAuthors().contains(author)) {
            throw new DuplicateBookException(Constants.AUTHOR + authorId);
        }
        requiredBook.getAuthors().add(author);
        return bookMapper.toDTO(bookRepository.save(requiredBook));
    }
}
