package com.example.restwsdemo.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@XmlRootElement
@Entity
public class Book {

    private Long id;
    private String title;
    private Collection<Person> authors = new ArrayList<>();
    private double price;
    private Company company;
    private PlaceOnShelf pos;


    public Book() {
    }

    public Book(String title, Collection<Person> authors, double price, Company company, PlaceOnShelf pos) {
        this.title = title;
        this.authors = authors;
        this.price = price;
        this.company = company;
        this.pos = pos;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToMany
    public Collection<Person> getAuthors() {
        return authors;
    }

    public void setAuthors(Collection<Person> authors) {
        this.authors = authors;
    }

    @ManyToOne
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @OneToOne
    public PlaceOnShelf getPos() {
        return pos;
    }

    public void setPos(PlaceOnShelf pos) {
        this.pos = pos;
    }
}
