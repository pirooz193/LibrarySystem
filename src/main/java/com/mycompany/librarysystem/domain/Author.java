package com.mycompany.librarysystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author extends Person {
    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();
}
