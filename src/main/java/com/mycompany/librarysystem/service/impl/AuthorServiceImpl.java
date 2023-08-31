package com.mycompany.librarysystem.service.impl;

import com.mycompany.librarysystem.domain.Author;
import com.mycompany.librarysystem.dto.AuthorDTO;
import com.mycompany.librarysystem.repository.AuthorRepository;
import com.mycompany.librarysystem.service.AuthorService;
import com.mycompany.librarysystem.service.mapper.AuthorMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = authorMapper.toEntity(authorDTO);
        return authorMapper.toDTO(authorRepository.save(author));
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
