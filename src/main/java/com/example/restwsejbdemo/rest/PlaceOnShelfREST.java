package com.example.restwsejbdemo.rest;

import com.example.restwsejbdemo.domain.PlaceOnShelf;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("placeonshelf")
@Stateless
public class PlaceOnShelfREST {

    @PersistenceContext
    EntityManager entityManager;

    @GET
    @Path("/{companyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PlaceOnShelf getPlaceOnShelf(@PathParam("companyId") Long id) {
        return entityManager.find(PlaceOnShelf.class, id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaceOnShelf(PlaceOnShelf placeOnShelf) {
        entityManager.persist(placeOnShelf);
        return Response.status(201).entity("PlaceOnShelf").build();
    }

    @DELETE
    @Path("/usun/{id}")
    public void deleteBook(@PathParam("id") Long id) {
        PlaceOnShelf pos = entityManager.find(PlaceOnShelf.class, id);
        entityManager.remove(pos);

    }


}
