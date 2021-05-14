package com.library.user.service;

import java.util.List;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.library.user.entity.User;
import com.library.user.repository.UserRepository;
import com.library.user.valueObject.Book;
import com.library.user.valueObject.BookRentInformation;

@Service
public class UserService {
	@Autowired
	private RestTemplate restTemplate;
	
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	public UserService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Value("${spring.rabbitmq.exchange}")
	private String exchange;

	@Value("${spring.rabbitmq.routingkey}")
	private String routingkey;

	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User findByUserId(Long userId) {
		return userRepository.findByUserId(userId);
	}

	public String rentBookToUser(BookRentInformation bookRentInformation) throws Exception {

		Book book = new Book();
		book.setBookId(bookRentInformation.getBookId());
		book.setUserId(bookRentInformation.getUserId());
		book = restTemplate.postForObject("http://BOOK-SERVICE/books/", book, Book.class);
		if (book != null && book.getUserId() != null) {
			return book.getBookId() + " id li kitap " + book.getUserId() + " id li usera verildi.";
		}
		return "Kitap kiralama işlemi gerçekleşmedi"; 

		/*rabbitTemplate.convertAndSend(exchange, routingkey, bookRentInformation);
		
		return "Kitap kiralama talebini alındı, kısa bir süre sonra kontrol edebilirsiniz"; */

	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
