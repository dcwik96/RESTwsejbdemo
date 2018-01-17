package com.example.restwsejbdemo;

import com.example.restwsdemo.domain.Book;
import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static com.jayway.restassured.RestAssured.given;

public class BookServiceIT {

    private static final String BOOK_TITLE = "Tytul";
    private static final double BOOK_PRICE = 19.99;

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
}
