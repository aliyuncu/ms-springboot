package com.library.user.valueObject;

import java.io.Serializable;

public class BookRentInformation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6713662319887005931L;
	private Long userId;
	private Long bookId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

}
