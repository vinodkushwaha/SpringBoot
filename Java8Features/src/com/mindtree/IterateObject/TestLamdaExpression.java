package com.mindtree.IterateObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestLamdaExpression {

	public static void main(String[] args) {
	
		List<Book> booksList = getBooks();
		
		Stream<Book> b = booksList.stream().filter(n-> n.getAuther().getaName()=="Vinod");
		b.forEach(p -> System.out.println("High Nums parallel="+p.toString()));
	}

	private static List<Book> getBooks() {
		Book b1 = new Book();
		b1.setBid(100);
		b1.setName("History");
		b1.setPrice(200);
		Auther auther1 = new Auther();
		auther1.setaName("Vinod");
		b1.setAuther(auther1);
		
		
		Book b2 = new Book();
		b2.setBid(300);
		b2.setName("English");
		b2.setPrice(100);
		Auther auther2 = new Auther();
		auther2.setaName("Vinod");
		b2.setAuther(auther2);
		
		Book b3 = new Book();
		b3.setBid(200);
		b3.setName("Sanskrit");
		b3.setPrice(908);
		Auther auther3 = new Auther();
		auther3.setaName("Neelam");
		b3.setAuther(auther3);
		
		List<Book> books = new ArrayList<>();
		books.add(b1);
		books.add(b2);
		books.add(b3);
		return books;
	}

}
