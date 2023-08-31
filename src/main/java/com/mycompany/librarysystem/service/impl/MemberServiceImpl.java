package com.mycompany.librarysystem.service.impl;

import com.mycompany.librarysystem.domain.Book;
import com.mycompany.librarysystem.domain.Constants;
import com.mycompany.librarysystem.domain.Member;
import com.mycompany.librarysystem.dto.MemberDTO;
import com.mycompany.librarysystem.dto.criteria.MemberCriteria;
import com.mycompany.librarysystem.repository.BookRepository;
import com.mycompany.librarysystem.repository.MemberRepository;
import com.mycompany.librarysystem.service.MemberService;
import com.mycompany.librarysystem.service.mapper.MemberMapper;
import com.mycompany.librarysystem.service.specifications.MemberSpecification;
import com.mycompany.librarysystem.web.error.BorrowingLimitException;
import com.mycompany.librarysystem.web.error.DuplicateBookException;
import com.mycompany.librarysystem.web.error.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final MemberMapper memberMapper;

    @Value(value = "${application.book.borrowing.limit}")
    private Integer BORROWING_LIMIT ;

    public MemberServiceImpl(MemberRepository memberRepository, BookRepository bookRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.memberMapper = memberMapper;
    }

    @Override
    public MemberDTO createMember(MemberDTO memberDTO) {
        Member savedMember = memberRepository.save(memberMapper.toEntity(memberDTO));
        return memberMapper.toDTO(savedMember);
    }

    @Override
    public MemberDTO borrowBookByMember(Long memberId, Long bookNumber) {
        Book requiredBook = bookRepository.findBookByBookNumber(bookNumber);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(Constants.MEMBER + memberId));
        if (member.getBorrowedBooks().contains(requiredBook))throw new DuplicateBookException(Constants.BOOK +bookNumber);
        if (member.getBorrowedBooks().size() < BORROWING_LIMIT ) {
            member.getBorrowedBooks().add(requiredBook);
        }else throw new BorrowingLimitException(Constants.MEMBER +memberId);
        member.setMembershipDate(LocalDateTime.now());
        return memberMapper.toDTO(memberRepository.save(member));
    }

    @Override
    public List<Member> searchMembers(MemberCriteria memberCriteria, Pageable pageable) {
        Specification<Member> spec = Specification.where(null);
        if (memberCriteria.getName() != null && !memberCriteria.getName().isEmpty()) {
            spec = spec.and(MemberSpecification.hasName(memberCriteria.getName()));
        }
        if (memberCriteria.getLastName() != null && !memberCriteria.getLastName().isEmpty()) {
            spec = spec.and(MemberSpecification.hasLastName(memberCriteria.getLastName()));
        }
        if (memberCriteria.getMembershipDate() != null ) {
            spec = spec.and(MemberSpecification.hasMembershipDate(memberCriteria.getMembershipDate()));
        }

        return memberRepository.findAll(spec, pageable).getContent();
    }
}
