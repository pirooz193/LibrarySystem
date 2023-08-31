package com.mycompany.librarysystem.service;

import com.mycompany.librarysystem.domain.Author;
import com.mycompany.librarysystem.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    AuthorDTO createAuthor(AuthorDTO authorDTO);

    List<Author> getAllAuthors();
}
