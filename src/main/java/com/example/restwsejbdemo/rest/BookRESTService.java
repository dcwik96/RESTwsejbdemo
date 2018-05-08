package com.example.restwsejbdemo.rest;

import com.example.restwsejbdemo.domain.Book;
import com.example.restwsejbdemo.domain.Company;
import com.example.restwsejbdemo.domain.Person;
import com.example.restwsejbdemo.domain.PlaceOnShelf;
import com.example.restwsejbdemo.service.BookManager;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("book")
public class BookRESTService {


    @Inject
    private BookManager bookManager;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Book> getAllBooks() {
        return bookManager.getAllBooks();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
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
    @Path("/lazy")
    @Produces(MediaType.TEXT_PLAIN)
    public Boolean lazyInitialization() {
        Person person1 = new Person("FIRST_PERSON_FIRST_NAME", "FIRST_PERSON_LAST_NAME");
        Person person2 = new Person("SECOND_PERSON_FIRST_NAME", "SECOND_PERSON_LAST_NAME");
        List<Person> authors = new ArrayList<>();
        authors.add(person1);
        authors.add(person2);

        Company company = new Company("COMPANY_NAME");
        PlaceOnShelf pos = new PlaceOnShelf(10, 20);
        Book book = new Book("BOOK_TITLE", authors, 99.99, company, pos);

        bookManager.addBook(book);

        Book addedBook = bookManager.getBook((long) bookManager.getAllBooks().size());
        try {
            System.out.println(addedBook.getCompany().getName());
        } catch (org.hibernate.LazyInitializationException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }


    @GET
    @Path("/{bookId}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBook(@PathParam("bookId") Long id) {
        Book b = bookManager.getBook(id);
        return b;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addBook(Book book) {
        bookManager.addBook(book);

        return Response.status(201).entity("Book").build();
    }

    @DELETE
    @Path("/usun/{id}")
    @Transactional
    public void deleteBook(@PathParam("id") Long id) {
        bookManager.deleteBook(bookManager.getBook(id));

    }

    @DELETE
    @Transactional
    public Response clearBooks() {
        bookManager.deleteAll();
        return Response.status(200).build();
    }

    @GET
    @Path("/booksauthor/{FirstName}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getBooksAuthor(@PathParam("FirstName") String firstName) {

        List<Object[]> rawAuthors = bookManager.getBookOfAuthorByAuthorName(firstName);
        JsonArrayBuilder authors = Json.createArrayBuilder();

        for (Object[] rawAuthor : rawAuthors) {
            String fName = (String) rawAuthor[0];
            String lName = (String) rawAuthor[1];
            String title = (String) rawAuthor[2];

            authors.add(Json.createObjectBuilder()
                    .add("firstName", fName)
                    .add("lastName", lName)
                    .add("title", title));

        }
        JsonObject json = Json.createObjectBuilder().add("result", authors).build();
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/cena/{cena}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Book> getBooksByPrice(@PathParam("cena") Double cena) {
        List<Book> books = bookManager.getBooksByPrice(cena);

        return books;
    }


}
