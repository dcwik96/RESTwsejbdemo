package com.example.restwsejbdemo.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
        @NamedQuery(name = "company.deleteAll", query = "Delete from Company "),
        @NamedQuery(name = "company.getAll", query = "Select c from Company c")
})
public class Company {

    private Long id;
    @NotNull
    private String name;

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
