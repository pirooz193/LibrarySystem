package com.mycompany.librarysystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "author")
public class Author extends Person {

}
