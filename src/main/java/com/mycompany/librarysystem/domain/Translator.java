package com.mycompany.librarysystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "translator")
public class Translator extends Person{

    @ManyToMany(mappedBy = "translators")
    private List<Book> books = new ArrayList<>();
}
