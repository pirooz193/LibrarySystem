package com.mycompany.librarysystem.repository;

import com.mycompany.librarysystem.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> , JpaSpecificationExecutor<Book> {

    Book findBookByBookNumber(Long bookNumber);
}
