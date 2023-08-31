package com.mycompany.librarysystem.service.mapper;

import com.mycompany.librarysystem.domain.Author;
import com.mycompany.librarysystem.dto.AuthorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = PersonMapper.class)
public interface AuthorMapper {

    Author toEntity(AuthorDTO authorDTO);

    AuthorDTO toDTO(Author author);
}
