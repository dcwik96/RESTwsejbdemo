package com.example.restwsdemo.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.restwsdemo.domain.Book;
import com.example.restwsdemo.domain.Person;

@Stateless
public class BookManager {

	@PersistenceContext
	EntityManager em;

	public void addBook(Book book) {
		em.persist(book);
	}

	public Book getBook(Long id) {
		Book retrieved = em.find(Book.class, id);
		return retrieved;
	}

	public Collection<Person> getAuthorsOfBook(Long id) {
		Book retrieved = em.find(Book.class, id);
		Collection<Person> result = new ArrayList<>(retrieved.getAuthors());
		return result;
	}

}
