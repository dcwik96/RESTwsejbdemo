package com.example.restwsdemo.service;

import com.example.restwsdemo.domain.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BookManager {

    @PersistenceContext
    EntityManager em;

    public void addBook(Book book) {
        em.persist(book);
    }

    public void deleteBook(Book book) {
        em.remove(book);
    }

    public Book getBook(Long id) {
        return em.find(Book.class, id);
    }

    public Book updateBook(Book book) {
        return em.merge(book);
    }

    @SuppressWarnings("unchecked")
    public List<Book> getAllBooks() {
        return em.createNamedQuery("book.all").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getBookOfAuthrByAuthorName(String firstName) {
        return em.createNamedQuery("bookAuthor.findByAuthorFirstName").setParameter("firstName", firstName).getResultList();
    }

    @SuppressWarnings("unchecked")
    public void deletAll() {
        em.createNamedQuery("book.delete.all").executeUpdate();
    }

}
