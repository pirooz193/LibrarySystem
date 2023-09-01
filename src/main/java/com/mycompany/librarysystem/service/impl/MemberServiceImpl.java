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


    /**
     * Creates a new member based on the provided member data.
     *
     * @param memberDTO The {@link MemberDTO} containing the member information to be created.
     * @return A {@link MemberDTO} representing the newly created member, including membership date.
     */
    @Override
    public MemberDTO createMember(MemberDTO memberDTO) {
        Member member = memberMapper.toEntity(memberDTO);
        member.setMembershipDate(LocalDateTime.now());
        return memberMapper.toDTO(memberRepository.save(member));
    }

    /**
     * Performs the process of borrowing a book by a member.
     *
     * @param borrowRequestModel The request model containing information about the book to be borrowed, including book number and member ID.
     * @return A {@link MemberDTO} representing the updated member after borrowing the book.
     * @throws NotFoundException      if the specified member is not found.
     * @throws DuplicateBookException if the member has already borrowed the same book.
     * @throws BorrowingLimitException if the member has reached their borrowing limit.
     * @throws BookIsBorrowedException if the book is already borrowed by another member.
     */
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


    /**
     * Creates and saves a report entry for a book borrowing transaction.
     *
     * @param bookNumber   The unique number of the borrowed book.
     * @param nationalCode The national code of the member borrowing the book.
     */
    private void createReport(Long bookNumber, String nationalCode) {
        Report report = new Report();
        report.setNationalCode(nationalCode);
        report.setBookNumber(bookNumber);
        report.setBorrowedStartDate(LocalDateTime.now());
        reportRepository.save(report);
    }


    /**
     * Searches for members based on specified criteria and pagination options.
     *
     * @param memberCriteria The criteria for filtering members, including name, last name, and membership date.
     * @param pageable       Pagination information, including page number, page size, and sorting.
     * @return A list of {@link Member} objects that match the given criteria and pagination settings.
     */
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


    /**
     * Performs the process of returning a borrowed book by a member.
     *
     * @param borrowRequestModel The request model containing information about the book return, including book number and member ID.
     * @return A {@link MemberDTO} representing the updated member after returning the book.
     * @throws NotFoundException if the specified member or book is not found.
     */
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
