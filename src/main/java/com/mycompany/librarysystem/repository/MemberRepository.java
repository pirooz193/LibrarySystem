package com.mycompany.librarysystem.repository;

import com.mycompany.librarysystem.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> , JpaSpecificationExecutor<Member> {
}
