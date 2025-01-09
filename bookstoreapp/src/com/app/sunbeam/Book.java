package com.app.sunbeam;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book implements Comparable<Book>{
	private String isbn;
	private Category category;
	private double price;
	private Date publishDate;
	private String authorName;
	private int quantity;
	
	public static SimpleDateFormat sdf;
	static
	{
		sdf=new SimpleDateFormat("dd-MM-yyyy");
	}
	public Book() {
		System.out.println("Book()");
	}
	
	
	public Book(String isbn, Category category,double price, Date publishDate, String authorName, int quantity) {
		super();
		this.isbn = isbn;
		this.category = category;
		this.price=price;
		this.publishDate = publishDate;
		this.authorName = authorName;
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", category=" + category + ", publishDate=" + publishDate + ", authorName="
				+ authorName + ", quantity=" + this.quantity +",price"+this.price+ "]";
	}


	@Override
	public int compareTo(Book o) {
		
		return this.isbn.compareTo(o.isbn);
	}

}
