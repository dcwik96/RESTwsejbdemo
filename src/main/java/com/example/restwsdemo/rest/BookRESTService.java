package com.example.restwsdemo.rest;

import java.util.ArrayList;
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

import com.example.restwsdemo.domain.Person;
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
	public Book getBook(@PathParam("bookId") Long id) {
		Book b = pm.getBook(id);
		return b;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBook(Book book) {
		Person author1 = new Person("Jan", "Zieliński");		
		Person author2 = new Person("Paweł", "Kwiatkowski");	
		
		List<Person> authors = new ArrayList<>();
		authors.add(author1);
		authors.add(author2);
		
		book.setAuthors(authors);
		pm.addBook(book);
		
		//book.setTitle("Pan Wołodyjowski");
		return Response.	status(Response.Status.CREATED).build();
	}

	@DELETE
	public Response clearPersons() {
		return Response.status(200).build();
	}

}
