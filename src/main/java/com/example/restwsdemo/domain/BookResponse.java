package com.example.restwsdemo.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookResponse {
	private List<Book> book;
	
	public List<Book> getBook(){
		return book;
	}
	
	public void setBook(List<Book> book) {
		this.book = book;
	}
}
