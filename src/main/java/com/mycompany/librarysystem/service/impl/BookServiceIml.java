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

    /**
     * Creates a new book based on the provided book data.
     *
     * @param bookDTO The {@link BookDTO} containing the book information to be created.
     * @return A {@link BookDTO} representing the newly created book, with the borrowed status set to false.
     */
    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        book.setBorrowed(false);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }


    /**
     * Retrieves a specific book based on the provided book ID.
     *
     * @param bookId The unique identifier of the book to be retrieved.
     * @return A {@link BookDTO} representing the book with the specified ID.
     * @throws NotFoundException if the book with the given ID is not found.
     */
    @Override
    public BookDTO getRequiredBook(Long bookId) {
        Book requiredBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException(Constants.BOOK + bookId));
        return bookMapper.toDTO(requiredBook);
    }


    /**
     * Searches for books based on specified criteria and pagination options.
     *
     * @param criteria The criteria for filtering books, including author name, translator name, and book title.
     * @param pageable Pagination information, including page number, page size, and sorting.
     * @return A {@link Page} containing a list of {@link Book} objects that match the given criteria and pagination settings.
     */
    public Page<Book> searchBooks(BookCriteria criteria, Pageable pageable) {
        Specification<Book> spec = Specification.where(null);

        if (criteria.getAuthorName() != null && !criteria.getAuthorName().isEmpty()) {
            spec = spec.and(BookSpecifications.hasAuthorName(criteria.getAuthorName()));
        }
        if (criteria.getTranslatorName() != null && !criteria.getTranslatorName().isEmpty()) {
            spec = spec.and(BookSpecifications.hasTranslatorName(criteria.getTranslatorName()));
        }
        if (criteria.getTitle() != null && !criteria.getTitle().isEmpty()) {
            spec = spec.and(BookSpecifications.hasBookName(criteria.getTitle()));
        }
        return bookRepository.findAll(spec, pageable);
    }

    /**
     * Retrieves a list of all books available in the library.
     *
     * @return A list of {@link Book} objects representing all the books in the library.
     */
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Adds an author to a specific book by their IDs.
     *
     * @param bookNumber The unique number of the book to which the author will be added.
     * @param authorId   The unique identifier of the author to be added.
     * @return A {@link BookDTO} representing the updated book after adding the author.
     * @throws NotFoundException      if the book with the given number or the author with the given ID is not found.
     * @throws DuplicateBookException if the author is already associated with the book.
     */
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
