package com.example.restwsdemo.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Entity
@NamedQueries({
        @NamedQuery(name = "book.all", query = "Select b from Book b"),
        @NamedQuery(name = "book.delete.all", query = "Delete from Book "),
        @NamedQuery(name = "bookAuthor.findByAuthorFirstName", query = "Select a.firstName, a.lastName, b.title from Book b JOIN b.author a where a.firstName = :firstName")
})
public class Book {

    private Long id;
    private String title;
    private List<Person> author = new ArrayList<>();
    private double price;
    private Company company;
    private PlaceOnShelf pos;


    public Book() {
    }

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public Book(String title, List<Person> author, double price, Company company, PlaceOnShelf pos) {
        this.title = title;
        this.author = author;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Person> getAuthor() {
        return author;
    }

    public void setAuthor(List<Person> authors) {
        this.author = authors;
    }

    public void addAuthors(List<Person> persons) {

        this.setAuthor(persons);

        for (Person person : persons) {
            person.getBooks().add(this);
        }
    }


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
