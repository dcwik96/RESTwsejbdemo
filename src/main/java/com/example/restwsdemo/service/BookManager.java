package com.example.restwsdemo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Singleton;

import com.example.restwsdemo.domain.Book;

@Singleton
public class BookManager {
	
	private List<Book> db = Collections.synchronizedList(new ArrayList<>());

	public void addBook(Book book) {
		db.add(book);
	}

	public void deleteBook(Book book){
		
	}
	
	public Book getBook(Integer id) {
		return new Book("Bolek","Jakis", 19.90, 10);
	}
	
	public List<Book> getAllBooks(){
		return db;
	}

}
