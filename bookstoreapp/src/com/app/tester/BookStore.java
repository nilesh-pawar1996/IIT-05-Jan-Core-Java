package com.app.tester;

import java.util.Arrays;
import java.util.Scanner;

import com.app.customexceptions.BookStoreException;
import com.app.sunbeam.Book;
import com.app.utils.ValidationUtils;

public class BookStore {
	
	Book[] books ;
	int counter;
	
	public BookStore(int size) {
		this.books=new Book[size];
	}
	
	public void addBook(Scanner sc) throws BookStoreException {
		if (counter < books.length) {
			System.out.println("Enter book details: isbn,category,price,publishDate,author,quantity");

			Book book = ValidationUtils.validateBook(sc.next(), sc.next(), sc.nextDouble(), sc.next(),
					sc.next(), sc.nextInt(), books);
			books[counter++] = book;
		} else
			System.out.println("Emp array overflow!!");
	}
	
	public Book findBook(Scanner sc)
	{
		System.out.print("Enter isbn::");
		String isbn = sc.next();
		Book book = null;
		for (Book b : books) {
			if (b != null)
				if (b.getIsbn().equals(isbn))
					book = b;
		}
		return book;
	}
	
	public void sortBookByIsbn()
	{
		Arrays.sort(books);
	}
	public void sortBookByAuthor()
	{
		Arrays.sort(books, (e1,e2)->e1.getAuthorName().compareTo(e2.getAuthorName()));
	}
	
	public void displayAllBooks()
	{
		for (Book book1 : books) {
			if (book1 != null)
				System.out.println(book1);
		}
	}
}
