package com.library.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.book.entity.Book;
import com.library.book.service.BookService;
import com.library.book.valueObject.ResponseBookWithUser;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@PostMapping("/")
	public Book saveBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}	

  	@GetMapping("/{id}")
	public Book getBookById(@PathVariable("id") Long bookId) {
		return bookService.findByBookId(bookId);
	}
  	
  	@GetMapping("/")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@GetMapping("/bookDetails/{id}")
	public ResponseBookWithUser getBookByIdWithUserDetail(@PathVariable("id") Long bookId) {
		return bookService.findByBookIdWithUserDetail(bookId);
	}
}
