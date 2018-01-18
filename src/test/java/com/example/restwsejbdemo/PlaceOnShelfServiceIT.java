package com.example.restwsejbdemo;

import com.example.restwsejbdemo.domain.PlaceOnShelf;
import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static com.jayway.restassured.RestAssured.given;

public class PlaceOnShelfServiceIT {

    private static final int ROW = 10;
    private static final int COLUMN = 20;


    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restwsejbdemo";
    }

    @Test
    public void checkAddPlaceOnShelf() {
        PlaceOnShelf pos = new PlaceOnShelf(ROW, COLUMN);

        given().
                contentType(MediaType.APPLICATION_JSON).
                body(pos).
                when().post("/placeonshelf").then().assertThat().statusCode(201);
    }
}
