package com.example.restwsejbdemo;

import com.example.restwsdemo.domain.Person;
import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;

public class PersonServiceIT {

    private static final String PERSON_FIRST_NAME = "Jasiu";
    private static final String PERSON_LAST_NAME = "Przyslowiowy-Kowalski";

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restwsejbdemo/api";
    }


    @Test
    public void addPerson() {
        delete("/author/").then().assertThat().statusCode(200);

        Person person = new Person(PERSON_FIRST_NAME, PERSON_LAST_NAME);

        given().
                contentType(MediaType.APPLICATION_JSON).
                body(person).
                when().
                post("/author/").then().assertThat().statusCode(201);
    }

}
