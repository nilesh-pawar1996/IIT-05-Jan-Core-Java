package com.app.tester;

import java.io.FileNotFoundException;
import java.util.Scanner;

import com.app.fal.BooksIoOperations;
import com.app.sunbeam.Book;

public class Tester {
	static Scanner sc = new Scanner(System.in);
	static BooksIoOperations bookIo = new BooksIoOperations();

	public static int menuList() {

		System.out.println("------Menu----------");
		System.out.println("0.Exit");
		System.out.println("1. Add Book");
		System.out.println("2. Find Book");
		System.out.println("3. Remove Book");
		System.out.println("4. Edit Book details");
		System.out.println("5. Sort Books By Isbn");
		System.out.println("6. Display Books");
		System.out.println("7. Sort Books By author");
		System.out.print("Enter your choice	::");
		return sc.nextInt();
	}

	public static void main(String[] args) {
		int choice = 0;
		BookStoreUsingList bookStore = new BookStoreUsingList();
		try {
			try {
				if(bookIo.readFile()!=null)
					bookStore.setBookList(bookIo.readFile());
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}

			while ((choice = menuList()) != 0) {
				try {
					switch (choice) {
					case 1:
						bookStore.addBook(sc);
						break;
					case 2:
						System.out.print("Enter isbn::");
						Book book = bookStore.findBook(sc.next());
						if (book == null)
							System.out.println("Book not found!!");
						else
							System.out.println(book);
						break;
					case 3:
						System.out.println("Enter isbn you want to remove::");
						bookStore.removeBook(sc.next());
						break;
					case 4:
						break;
					case 5:
						bookStore.sortBookByIsbn();
						bookStore.displayAllBooks();
						break;
					case 6:
						bookStore.displayAllBooks();
						break;
					case 7:
						bookStore.sortBookByAuthor();
						bookStore.displayAllBooks();
					default:
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			bookIo.writeFile(bookStore.getBookList());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
