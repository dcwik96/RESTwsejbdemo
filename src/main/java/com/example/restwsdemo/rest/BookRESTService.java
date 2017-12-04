package com.example.restwsdemo.rest;

import com.example.restwsdemo.domain.Book;
import com.example.restwsdemo.domain.Person;
import com.example.restwsdemo.service.BookManager;
import com.example.restwsdemo.service.PersonManager;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("book")
@Stateless
public class BookRESTService {


    @Inject
    private BookManager bookManager;
    @Inject
    private PersonManager personManager;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testRelation() {
        Person person1 = new Person("Person", "Person");
        Person person2 = new Person("Person2", "Person2");

        Book book1 = new Book("Tytul1", 19.99);
        Book book2 = new Book("Tytul2", 11.99);

        bookManager.addBook(book1);

        List<Person> authors = new ArrayList<>();
        authors.add(person1);
        authors.add(person2);


        book1.addAuthors(authors);

        bookManager.updateBook(book1);

        return "ManytoMany";
    }


    @GET
    @Path("/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBook(@PathParam("bookId") Long id) {
        Book b = bookManager.getBook(id);
        return b;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        bookManager.addBook(book);

        return Response.status(201).entity("Book").build();
    }

    @DELETE
    @Path("/usun/{id}")
    public void deleteBook(@PathParam("id") Long id) {
        bookManager.deleteBook(bookManager.getBook(id));

    }

    @DELETE
    public Response clearBooks() {
        return Response.status(200).build();
    }


}
