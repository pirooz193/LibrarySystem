package com.mycompany.mapper;

import com.mycompany.librarysystem.domain.Person;
import com.mycompany.librarysystem.dto.PersonDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toEntity(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
