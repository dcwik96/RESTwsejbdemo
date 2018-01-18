package com.example.restwsejbdemo.rest;

import com.example.restwsejbdemo.domain.PlaceOnShelf;
import com.example.restwsejbdemo.service.PlaceOnShelfManager;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("placeonshelf")
@Stateless
public class PlaceOnShelfREST {

    @Inject
    private PlaceOnShelfManager placeOnShelfManager;

    @GET
    @Path("/{companyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PlaceOnShelf getPlaceOnShelf(@PathParam("companyId") Long id) {
        PlaceOnShelf c = placeOnShelfManager.getPlaceOnShelf(id);
        return c;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaceOnShelf(PlaceOnShelf company) {
        placeOnShelfManager.addPlaceOnShelf(company);
        return Response.status(201).entity("PlaceOnShelf").build();
    }

    @DELETE
    @Path("/usun/{id}")
    public void deleteBook(@PathParam("id") Long id) {
        placeOnShelfManager.deletePlaceOnShelf(placeOnShelfManager.getPlaceOnShelf(id));
    }


}
