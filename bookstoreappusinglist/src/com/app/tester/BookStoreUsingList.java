package com.app.tester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.app.customexceptions.BookStoreException;
import com.app.sunbeam.Book;
import com.app.utils.ValidationUtils;

public class BookStoreUsingList {
	ArrayList<Book> bookList;
	
	public BookStoreUsingList() {
		bookList=new ArrayList<Book>();
	}
	public void addBook(Scanner sc) throws BookStoreException {
	
			System.out.println("Enter book details: isbn,category,price,publishDate,author,quantity");

			Book book = ValidationUtils.validateBook(sc.next(), sc.next(), sc.nextDouble(), sc.next(),
					sc.next(), sc.nextInt(), bookList);
			bookList.add(book);
		
	}
	
	public Book findBook(String isbn)
	{
		Book book = new Book(isbn);
		if(bookList.contains(new Book(isbn)))
			return bookList.get(bookList.indexOf(book));
		return null;
	}
	
	public void sortBookByIsbn()
	{
		Collections.sort(this.bookList);
	}
	public void sortBookByAuthor()
	{
		this.bookList.sort((e1,e2)->e1.getAuthorName().compareTo(e2.getAuthorName()));
	}
	
	public void displayAllBooks()
	{
		for (Book book1 : this.bookList) {
			if (book1 != null)
				System.out.println(book1);
		}
	}
	
	public void removeBook(String isbn) throws BookStoreException
	{
		Book b=this.findBook(isbn);
		if(b!=null)
		{
			bookList.remove(b);
		}
		else
			throw new BookStoreException("Book not found!!");
	}
}
