package com.example.restwsdemo.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Entity
public class Book {

    private Long id;
    private String title;
    private List<Person> authors = new ArrayList<>();
    private double price;
    private Company company;
    private PlaceOnShelf pos;


    public Book() {
    }

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Person> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Person> authors) {
        this.authors = authors;
    }

    public void addAuthors(List<Person> persons) {

        this.setAuthors(persons);

        for (Person person : persons) {
            person.getBooks().add(this);
        }
    }


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public PlaceOnShelf getPos() {
        return pos;
    }

    public void setPos(PlaceOnShelf pos) {
        this.pos = pos;
    }
}
