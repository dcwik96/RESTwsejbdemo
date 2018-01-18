package com.example.restwsejbdemo.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.List;

@StaticMetamodel(Book.class)
public class Book_ {

    public static volatile SingularAttribute<Book, String> title;
    public static volatile SingularAttribute<Book, List<Person>> authors;
    public static volatile SingularAttribute<Book, Double> price;
    public static volatile SingularAttribute<Book, Company> company;
    public static volatile SingularAttribute<Book, PlaceOnShelf> pos;

}
