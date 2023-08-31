package com.mycompany.mapper;

import com.mycompany.librarysystem.domain.Translator;
import com.mycompany.librarysystem.dto.TranslatorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = PersonMapper.class)
public interface TranslatorMapper {

    Translator toEntity(TranslatorDTO translatorDTO);

    TranslatorDTO toDTO(Translator translator);
}
