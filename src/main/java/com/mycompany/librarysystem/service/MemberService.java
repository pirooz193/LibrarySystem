package com.mycompany.librarysystem.service;

import com.mycompany.librarysystem.dto.MemberDTO;

public interface MemberService {
    MemberDTO createMember(MemberDTO memberDTO);

    MemberDTO borrowBookByMember(Long memberId, Long bookNumber);
}
