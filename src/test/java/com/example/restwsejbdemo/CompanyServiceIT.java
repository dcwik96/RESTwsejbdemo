package com.example.restwsejbdemo;

import com.example.restwsejbdemo.domain.Book;
import com.example.restwsejbdemo.domain.Company;
import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class CompanyServiceIT {

    private static final String COMPANY_NAME = "Wydawnictwo";

    private static final String BOOK_TITLE = "Tytul";
    private static final double BOOK_PRICE = 19.99;


    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restwsejbdemo";
    }

    @Test
    public void checkAddCompanyWithName() {
        Company company = new Company(COMPANY_NAME);

        given().
                contentType(MediaType.APPLICATION_JSON).
                body(company).
                when().post("/company").then().assertThat().statusCode(201);
    }

    @Test
    public void checkAddCompanyWithNameAndBooks() {
        Book book = new Book(BOOK_TITLE, BOOK_PRICE);
        List<Book> listOfBooks = new ArrayList<>();
        listOfBooks.add(book);

        Company company = new Company(COMPANY_NAME,listOfBooks);

        given().
                contentType(MediaType.APPLICATION_JSON).
                body(company).
                when().post("/company").then().assertThat().statusCode(201);

    }

    @Test
    public void checkAddCompanyWithoutName() {
        Company company = new Company();

        given().
                contentType(MediaType.APPLICATION_JSON).
                body(company).
                when().post("/company").then().assertThat().statusCode(500);
    }
}
