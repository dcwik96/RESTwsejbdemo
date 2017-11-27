package com.example.restwsdemo.rest;


import com.example.restwsdemo.domain.Person;
import com.example.restwsdemo.service.PersonManager;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("author")
@Stateless
public class PersonREST {

    @Inject
    private PersonManager pm;

    @GET
    @Path("/{authorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson(@PathParam("authorId") Long id) {
        Person p = pm.getPerson(id);
        return p;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        pm.addPerson(person);
        return Response.status(201).entity("Person").build();
    }

    @DELETE
    @Path("/usun/{id}")
    public void deletePerson(@PathParam("id") Long id) {
        pm.deletePerson(pm.getPerson(id));
    }


}
