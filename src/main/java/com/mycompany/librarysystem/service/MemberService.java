package com.mycompany.librarysystem.service;

import com.mycompany.librarysystem.domain.Member;
import com.mycompany.librarysystem.dto.MemberDTO;
import com.mycompany.librarysystem.dto.criteria.MemberCriteria;
import com.mycompany.librarysystem.dto.model.BorrowRequestModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {
    MemberDTO createMember(MemberDTO memberDTO);

    MemberDTO borrowBookByMember(BorrowRequestModel borrowRequestModel);

    List<Member> searchMembers(MemberCriteria memberCriteria, Pageable pageable);

    MemberDTO returnBook(BorrowRequestModel borrowRequestModel);
}
