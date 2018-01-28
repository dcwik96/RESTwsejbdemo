package com.example.restwsejbdemo;

import com.example.restwsejbdemo.domain.Company;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static com.jayway.restassured.RestAssured.given;

public class CompanyServiceIT {

    private static final String COMPANY_NAME = "Wydawnictwo";


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
    public void checkAddCompanyWithoutName() {
        Company company = new Company();

        given().
                contentType(MediaType.APPLICATION_JSON).
                body(company).
                when().post("/company").then().assertThat().statusCode(500);
    }

    @Test
    public void checkGsonPostCompany() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        given().contentType(MediaType.APPLICATION_JSON).
                body(gson.toJson(new Company(COMPANY_NAME)))
                .when().post("/company").then().assertThat().statusCode(201);
    }

    @Test
    public void checkAddCompanyWithJsonReader() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        given().
                body(gson.toJson(new Company(COMPANY_NAME))).
                when().post("/company/jsonReader").then().assertThat().statusCode(201);
    }
}
