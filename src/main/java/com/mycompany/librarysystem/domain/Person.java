package com.mycompany.librarysystem.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "person")
@MappedSuperclass()
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name" , nullable = false, length = 100) private String name;
    @Column(name = "last_name" , nullable = false, length = 100)
    private String lastName;

    @Column(name = "national_code", unique = true, length = 15, nullable = false)
    private String nationalCode;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(lastName, person.lastName) && Objects.equals(nationalCode, person.nationalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, nationalCode);
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
}
