package com.example.restwsdemo.rest;

import com.example.restwsdemo.domain.Book;
import com.example.restwsdemo.service.BookManager;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("book")
@Stateless
public class BookRESTService {


    @Inject
    private BookManager pm;

    @GET
    @Path("/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBook(@PathParam("bookId") Long id) {
        Book b = pm.getBook(id);
        return b;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        pm.addBook(book);

        return Response.status(201).entity("Book").build();
    }

    @DELETE
    @Path("/usun/{id}")
    public void deleteBook(@PathParam("id") Long id) {
        pm.deleteBook(pm.getBook(id));

    }

    @DELETE
    public Response clearBooks() {
        return Response.status(200).build();
    }

}
