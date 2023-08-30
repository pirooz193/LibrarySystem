package com.mycompany.librarysystem.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "author")
public class Author extends Person{

    private String authorCode;

}
