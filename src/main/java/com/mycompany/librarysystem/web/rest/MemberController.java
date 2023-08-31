package com.mycompany.librarysystem.web.rest;

import com.mycompany.librarysystem.dto.MemberDTO;
import com.mycompany.librarysystem.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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

    @PostMapping("/{memberId}/borrow/{bookNumber}")
    public ResponseEntity<MemberDTO> borrowBook(@PathVariable Long memberId, @PathVariable Long bookNumber) {
        MemberDTO memberDTO = memberService.borrowBookByMember(memberId, bookNumber);
        return ResponseEntity.ok(memberDTO);
    }
}
