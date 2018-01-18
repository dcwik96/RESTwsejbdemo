package com.example.restwsejbdemo.service;

import com.example.restwsejbdemo.domain.Book;
import com.example.restwsejbdemo.domain.Book_;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    public List<Object[]> getBookOfAuthorByAuthorName(String firstName) {
        return em.createNamedQuery("bookAuthor.findByAuthorFirstName").setParameter("firstName", firstName).getResultList();
    }

    @SuppressWarnings("unchecked")
    public void deleteAll() {
        em.createNamedQuery("book.delete.all").executeUpdate();
    }

    public List<Book> getBooksByPrice(Double price) {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Book> c = qb.createQuery(Book.class);

        Root<Book> b = c.from(Book.class);

        Predicate condition = qb.gt(b.get(Book_.price), price);
        c.where(condition);

        TypedQuery<Book> q = em.createQuery(c);

        List<Book> result = q.getResultList();

        return result;
    }

}
