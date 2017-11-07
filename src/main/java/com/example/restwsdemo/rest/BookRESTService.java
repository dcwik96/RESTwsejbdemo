package com.example.restwsdemo.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restwsdemo.domain.Book;
import com.example.restwsdemo.service.BookManager;

@Path("book")
@Stateless
public class BookRESTService {

	@Inject
	private BookManager pm;

	@GET
	@Path("/{bookId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getBook(@PathParam("bookId") Integer id) {
		Book b = pm.getBook(id);
		return b;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getBooks() {
		return pm.getAllBooks();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBook(Book book) {
		pm.addBook(book);

		return Response.status(201).entity("Book").build();
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "REST API /book is running today!";
	}

	@DELETE
	public Response clearPersons() {
		return Response.status(200).build();
	}

}
