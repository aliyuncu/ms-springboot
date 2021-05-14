package com.library.book.valueObject;

import com.library.book.entity.Book;

public class ResponseBookWithUser {
	private Book book;
	private User user;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
