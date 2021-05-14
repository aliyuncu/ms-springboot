package com.library.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.library.book.entity.Book;
import com.library.book.repository.BookRepository;
import com.library.book.valueObject.ResponseBookWithUser;
import com.library.book.valueObject.User;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	public Book findByBookId(Long bookId) {
		return bookRepository.findByBookId(bookId);
	}
	
	public List<Book> getAllBooks() {
		List<Book> bookList = bookRepository.findAll();
		for (Book book : bookList) {
			ResponseBookWithUser responseBookWithUser = this.findByBookIdWithUserDetail(book.getBookId());
			if (responseBookWithUser != null && responseBookWithUser.getUser() != null) {
				book.setUserName(responseBookWithUser.getUser().getUserName());
			}
		}

		return bookList;
	}

	public ResponseBookWithUser findByBookIdWithUserDetail(Long bookId) {
		ResponseBookWithUser responseBookWithUser = new ResponseBookWithUser();
		Book book = bookRepository.findByBookId(bookId);
		responseBookWithUser.setBook(book);

		if (book != null && book.getUserId() != null) {
			User user = restTemplate.getForObject("http://USER-SERVICE/users/" + book.getUserId(),
					User.class);
			responseBookWithUser.setUser(user);
		}
		return responseBookWithUser;
	}
	
}
