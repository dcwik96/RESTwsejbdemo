package com.example.restwsdemo.rest;


import com.example.restwsdemo.domain.Company;
import com.example.restwsdemo.service.CompanyManager;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("company")
@Stateless
public class CompanyREST {

    @Inject
    private CompanyManager companyManager;

    @GET
    @Path("/{companyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Company getCompany(@PathParam("companyId") Long id) {
        Company c = companyManager.getCompany(id);
        return c;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCompany(Company company) {
        companyManager.addCompany(company);
        return Response.status(201).entity("Company").build();
    }

    @DELETE
    @Path("/usun/{id}")
    public void deleteBook(@PathParam("id") Long id) {
        companyManager.deleteCompany(companyManager.getCompany(id));
    }


}
