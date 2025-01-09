package com.app.customexceptions;

@SuppressWarnings("serial")
public class BookStoreException extends Exception{
	public BookStoreException(String mesg) {
		super(mesg);
	}
}
