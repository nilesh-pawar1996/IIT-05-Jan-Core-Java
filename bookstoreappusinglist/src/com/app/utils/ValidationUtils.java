package com.app.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import com.app.customexceptions.BookStoreException;
import com.app.sunbeam.Book;
import com.app.sunbeam.Category;

public class ValidationUtils {
	public static Book validateBook(String isbn,String category,double price,String publishDate,String author,int quantity,ArrayList<Book> books) throws BookStoreException {
		String validatedIsbn=validateIsbn(isbn, books);
		Category validatedCategory=validateCategory(category);
		Date publishDate1=validatePublishdate(publishDate);
		Book book=new Book(validatedIsbn, validatedCategory, price, publishDate1, author, quantity);
		return book;
	}

	public static String validateIsbn(String isbn, ArrayList<Book> books) throws BookStoreException {
		Book bookToValidate=new Book();
		bookToValidate.setIsbn(isbn);
		for (Book book : books) {
			if(book != null) {
				if(book.equals(bookToValidate))
					throw new BookStoreException("Isbn number should be unique!!");	
			}
		}
		return isbn;
	}

	public static Category validateCategory(String category) throws BookStoreException {
		try {
			Category category1 = Category.valueOf(category.toUpperCase());
			return category1;
		} catch (IllegalArgumentException ex) {
			StringBuffer sb=new StringBuffer("Invalid category!! It should be..");
			for (Category ele : Category.values()) {
				sb.append(" "+ele);
			}
			throw new BookStoreException(sb.toString());
		}

	}

	public static Date validatePublishdate(String date) throws BookStoreException {
		
		try {
			Date beforeDate=Book.sdf.parse("31-03-2024");
			Date afterDate=Book.sdf.parse("01-04-2023");
			Date publishDate=Book.sdf.parse(date);
			if(publishDate.before(beforeDate) && publishDate.after(afterDate))
				return publishDate;
			throw new BookStoreException("Invalid Date!!");
		} catch (ParseException e) {
			throw new BookStoreException("Invalid Date!!");
		}
		
	}

}
