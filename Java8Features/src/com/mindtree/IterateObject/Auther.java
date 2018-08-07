package com.mindtree.IterateObject;

import java.util.List;

public class Auther {
	private String aName;
	
	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	private List<Book> books;

}
