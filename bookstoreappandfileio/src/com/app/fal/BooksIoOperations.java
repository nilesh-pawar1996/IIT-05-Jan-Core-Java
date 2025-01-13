package com.app.fal;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.app.sunbeam.Book;

public class BooksIoOperations {
	String filePath;

	public BooksIoOperations() {
		this.filePath = "book1.dat";
	}

	public void writeFile(List<Book> bookList) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(filePath)))) {
			if (bookList != null)
				oos.writeObject(bookList);
		}
	}

	public List<Book> readFile() throws ClassNotFoundException, IOException, Exception {
		File file = new File(filePath);
		if (file.exists())
			try (ObjectInputStream ios = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
				return (List<Book>) ios.readObject();
			}
		return null;
	}

	public void writeBook(List<Book> bookList, Book book) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(filePath, true)))) {
			if (book != null)
				oos.writeObject(book);
		}
	}

	public List<Book> readBooks() throws ClassNotFoundException, IOException {
		List<Book> bookList = null;
		try (ObjectInputStream ios = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
			while (ios.available() > 0) {
				Object obj = ios.readObject();
				if (obj instanceof Book) {
					Book book = (Book) obj;
					System.out.println(book);
					bookList.add(book);
				}
			}
		}
		return bookList;
	}

}
