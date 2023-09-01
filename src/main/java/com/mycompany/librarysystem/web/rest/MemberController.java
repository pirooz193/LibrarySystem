package com.mycompany.librarysystem.web.rest;

import com.mycompany.librarysystem.domain.Member;
import com.mycompany.librarysystem.dto.MemberDTO;
import com.mycompany.librarysystem.dto.criteria.MemberCriteria;
import com.mycompany.librarysystem.dto.model.BorrowRequestModel;
import com.mycompany.librarysystem.service.MemberService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberController {


    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        MemberDTO createdMember = memberService.createMember(memberDTO);
        return ResponseEntity.created(URI.create("/api/member")).body(createdMember);
    }

    @PostMapping("/borrow")
    public ResponseEntity<MemberDTO> borrowBook(@RequestBody BorrowRequestModel borrowRequestModel) {
        MemberDTO memberDTO = memberService.borrowBookByMember(borrowRequestModel);
        return ResponseEntity.ok(memberDTO);
    }

    @PatchMapping("/return")
    public ResponseEntity<MemberDTO> returnBook(@RequestBody BorrowRequestModel borrowRequestModel) {
        MemberDTO memberDTO = memberService.returnBook(borrowRequestModel);
        return ResponseEntity.ok(memberDTO);
    }


    @GetMapping
    public ResponseEntity<List<Member>> searchMembers(@ModelAttribute MemberCriteria memberCriteria, Pageable pageable) {
        List<Member> members = memberService.searchMembers(memberCriteria, pageable);
        return ResponseEntity.ok(members);
    }
}
