package com.mycompany.librarysystem.service.specifications;

import com.mycompany.librarysystem.domain.Member;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class MemberSpecification {
    public static Specification<Member> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Member> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%");
    }

    public static Specification<Member> hasMembershipDate(LocalDate membershipDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("membershipDate"), "%" + membershipDate + "%");
    }
}
