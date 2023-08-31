package com.mycompany.librarysystem.service.mapper;

import com.mycompany.librarysystem.domain.Member;
import com.mycompany.librarysystem.dto.MemberDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member toEntity(MemberDTO memberDTO);

    MemberDTO toDTO(Member member);
}
