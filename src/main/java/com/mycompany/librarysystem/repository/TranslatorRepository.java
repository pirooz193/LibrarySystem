package com.mycompany.librarysystem.repository;

import com.mycompany.librarysystem.domain.Translator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslatorRepository extends JpaRepository<Translator, Long> {
}
