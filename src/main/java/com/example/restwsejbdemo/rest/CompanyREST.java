package com.example.restwsejbdemo.rest;


import com.example.restwsejbdemo.domain.Company;
import com.example.restwsejbdemo.service.CompanyManager;
import com.google.gson.Gson;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;

@Path("company")
@Stateless
public class CompanyREST {

    @Inject
    private CompanyManager companyManager;

    @GET
    @Path("/{companyId}")
//    @Produces(MediaType.APPLICATION_JSON)
    public String getCompany(@PathParam("companyId") Long id) {
        Company c = companyManager.getCompany(id);

        Gson gson = new Gson();

        return gson.toJson(c);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCompany(Company company) {
        System.out.println("@@@@@@@@@@@@@@@@@@@" + company);
        companyManager.addCompany(company);
        return Response.status(201).entity("Company").build();
    }

    @DELETE
    @Path("/usun/{id}")
    public void deleteBook(@PathParam("id") Long id) {
        companyManager.deleteCompany(companyManager.getCompany(id));
    }

    @POST
    @Path("/jsonReader")
    public Response addCompanyWithJsonReader(String company) {
        JsonReader reader = Json.createReader(new StringReader(company));
        JsonObject jsonObject = reader.readObject();

        Company plainCompany = new Company();
        plainCompany.setName(jsonObject.getString("name"));

        companyManager.addCompany(plainCompany);
        return Response.status(201).entity("Company").build();
    }

}
