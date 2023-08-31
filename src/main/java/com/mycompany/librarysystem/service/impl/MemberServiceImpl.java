package com.mycompany.librarysystem.service.impl;

import com.mycompany.librarysystem.domain.Book;
import com.mycompany.librarysystem.domain.Constants;
import com.mycompany.librarysystem.domain.Member;
import com.mycompany.librarysystem.domain.Report;
import com.mycompany.librarysystem.dto.MemberDTO;
import com.mycompany.librarysystem.dto.criteria.MemberCriteria;
import com.mycompany.librarysystem.dto.model.BorrowRequestModel;
import com.mycompany.librarysystem.repository.BookRepository;
import com.mycompany.librarysystem.repository.MemberRepository;
import com.mycompany.librarysystem.repository.ReportRepository;
import com.mycompany.librarysystem.service.MemberService;
import com.mycompany.librarysystem.service.mapper.MemberMapper;
import com.mycompany.librarysystem.service.specifications.MemberSpecification;
import com.mycompany.librarysystem.web.error.BookIsBorrowedException;
import com.mycompany.librarysystem.web.error.BorrowingLimitException;
import com.mycompany.librarysystem.web.error.DuplicateBookException;
import com.mycompany.librarysystem.web.error.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final MemberMapper memberMapper;
    private final ReportRepository reportRepository;

    @Value(value = "${application.book.borrowing.limit}")
    private Integer BORROWING_LIMIT;

    public MemberServiceImpl(MemberRepository memberRepository, BookRepository bookRepository, MemberMapper memberMapper, ReportRepository reportRepository) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.memberMapper = memberMapper;
        this.reportRepository = reportRepository;
    }

    @Override
    public MemberDTO createMember(MemberDTO memberDTO) {
        Member member = memberMapper.toEntity(memberDTO);
        member.setMembershipDate(LocalDateTime.now());
        return memberMapper.toDTO(memberRepository.save(member));
    }

    @Transactional
    @Override
    public MemberDTO borrowBookByMember(BorrowRequestModel borrowRequestModel) {
        long bookNumber = borrowRequestModel.getBookNumber();
        long memberId = borrowRequestModel.getMemberId();
        Book requiredBook = bookRepository.findBookByBookNumber(bookNumber);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(Constants.MEMBER + memberId));
        if (!requiredBook.getBorrowed()) {
            if (member.getBorrowedBooks().contains(requiredBook))
                throw new DuplicateBookException(Constants.BOOK + bookNumber);
            if (member.getBorrowedBooks().size() < BORROWING_LIMIT) {
                member.getBorrowedBooks().add(requiredBook);
                requiredBook.setBorrowed(true);
            } else throw new BorrowingLimitException(Constants.MEMBER + memberId);
            MemberDTO savedMember = memberMapper.toDTO(memberRepository.save(member));
            bookRepository.save(requiredBook);
            createReport(bookNumber, member.getNationalCode());
            return savedMember;
        } else throw new BookIsBorrowedException(Constants.BOOK + bookNumber);
    }

    private void createReport(Long bookNumber, String nationalCode) {
        Report report = new Report();
        report.setNationalCode(nationalCode);
        report.setBookNumber(bookNumber);
        report.setBorrowedStartDate(LocalDateTime.now());
        reportRepository.save(report);
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
        if (memberCriteria.getMembershipDate() != null) {
            spec = spec.and(MemberSpecification.hasMembershipDate(memberCriteria.getMembershipDate()));
        }

        return memberRepository.findAll(spec, pageable).getContent();
    }

    @Transactional
    @Override
    public MemberDTO returnBook(BorrowRequestModel borrowRequestModel) {
        long bookNumber = borrowRequestModel.getBookNumber();
        long memberId = borrowRequestModel.getMemberId();
        Book requiredBook = bookRepository.findBookByBookNumber(bookNumber);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(Constants.MEMBER + memberId));
        member.getBorrowedBooks().remove(requiredBook);
        requiredBook.setBorrowed(false);
        Report report = reportRepository.findReportByBookNumberAndNationalCode(bookNumber, member.getNationalCode());
        report.setBorrowedEndDate(LocalDateTime.now());
        Member savedMember = memberRepository.save(member);
        bookRepository.save(requiredBook);
        reportRepository.save(report);
        return memberMapper.toDTO(savedMember);
    }
}
