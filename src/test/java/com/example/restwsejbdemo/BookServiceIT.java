package com.example.restwsejbdemo;

import com.example.restwsejbdemo.domain.Book;
import com.example.restwsejbdemo.domain.Company;
import com.example.restwsejbdemo.domain.Person;
import com.example.restwsejbdemo.domain.PlaceOnShelf;
import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class BookServiceIT {

    private static final String BOOK_TITLE = "Tytul";
    private static final double BOOK_PRICE = 19.99;

    private static final String FIRST_PERSON_FIRST_NAME = "Imie";
    private static final String FIRST_PERSON_LAST_NAME = "Nazwisko";

    private static final String SECOND_PERSON_FIRST_NAME = "ImieDwa";
    private static final String SECOND_PERSON_LAST_NAME = "NazwiskoDwa";

    private static final String COMPANY_NAME = "Wydawnictwo";

    private static final int POS_ROW = 10;
    private static final int POS_COLUMN = 20;

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

        Company company = new Company(COMPANY_NAME);

        PlaceOnShelf pos = new PlaceOnShelf(POS_ROW, POS_COLUMN);

        Book book = new Book(BOOK_TITLE, authors, BOOK_PRICE, company, pos);

        given().
                contentType(MediaType.APPLICATION_JSON).
                body(book).
                when().post("/book").then().assertThat().statusCode(201);
    }

    @Test
    public void checkLazyInitializationException() {
        given().when().get("/book/lazy").then().assertThat().statusCode(200);
    }

//    DODAWANIE KSIAZI PRZED???
    @Test
    public void checkGetBooksByPrice() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/book/cena/{cena}", 10.00).
                then().
                statusCode(200).
                body("result.size()", greaterThan(0));
    }
}
