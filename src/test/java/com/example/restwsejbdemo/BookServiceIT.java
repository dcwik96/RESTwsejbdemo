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

public class BookServiceIT {

    private static final String BOOK_TITLE = "Tytul";
    private static final double BOOK_PRICE = 19.99;

    private static final String FIRST_PERSON_FIRST_NAME = "Imie";
    private static final String FIRST_PERSON_LAST_NAME = "Nazwisko";

    private static final String SECOND_PERSON_FIRST_NAME = "Imie";
    private static final String SECOND_PERSON_LAST_NAME = "Nazwisko";

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restwsejbdemo";
    }

    @Test
    public void checkAddBookWithTitleAndPrice() {
        Book book = new Book(BOOK_TITLE, BOOK_PRICE);

        given().
                contentType(MediaType.APPLICATION_JSON).
                body(book).
                when().post("/book").then().assertThat().statusCode(201);
    }

    @Test
    public void checkAddWholeBook() {
        Person person1 = new Person(FIRST_PERSON_FIRST_NAME, FIRST_PERSON_LAST_NAME);
        Person person2 = new Person(SECOND_PERSON_FIRST_NAME, SECOND_PERSON_LAST_NAME);
        List<Person> authors = new ArrayList<>();
        authors.add(person1);
        authors.add(person2);

        Book book = new Book();




    }
}
