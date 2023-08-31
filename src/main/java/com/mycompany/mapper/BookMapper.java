package com.mycompany.mapper;

import com.mycompany.librarysystem.domain.Book;
import com.mycompany.librarysystem.dto.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toDTO(Book book);

    Book toEntity(BookDTO bookDTO);
}
