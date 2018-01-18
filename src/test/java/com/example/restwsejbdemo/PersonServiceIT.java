package com.example.restwsejbdemo;

import com.example.restwsejbdemo.domain.Book;
import com.example.restwsejbdemo.domain.Person;
import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;

public class PersonServiceIT {

    private static final String PERSON_FIRST_NAME = "Imie";
    private static final String PERSON_LAST_NAME = "Nazwisko";

    private static final String BOOK_TITLE = "Tytul";
    private static final double BOOK_PRICE = 19.99;


    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restwsejbdemo";
    }

    @Test
    public void checkClearPersons() {
        when().
                delete("/author").
                then().
                assertThat().statusCode(200);
    }

    @Test
    public void checkAddPerson() {
        Person person = new Person(PERSON_FIRST_NAME, PERSON_LAST_NAME);

        given().
                contentType(MediaType.APPLICATION_JSON).
                body(person).
                when().
                post("/author/").then().assertThat().statusCode(201);
    }

    @Test
    public void checkAddPersonWithBooks() {
        List<Book> books = new ArrayList<>();
        Book book = new Book(BOOK_TITLE, BOOK_PRICE);
        books.add(book);

        Person person = new Person();
        person.setFirstName(PERSON_FIRST_NAME);
        person.setLastName(PERSON_LAST_NAME);
        person.setBooks(books);

        given().
                contentType(MediaType.APPLICATION_JSON).body(person).
                when().
                post("/author").then().assertThat().statusCode(201);
    }
}
