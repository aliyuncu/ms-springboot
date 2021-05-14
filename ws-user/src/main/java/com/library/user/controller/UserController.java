package com.library.user.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.user.entity.User;
import com.library.user.service.UserService;
import com.library.user.valueObject.BookRentInformation;
import com.library.user.valueObject.Message;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Value("${app.message}")
	private String message;

	@PostMapping("/")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Long userId) {
		return userService.findByUserId(userId);
	}

	@PostMapping("/rentBookToUser")
	public Message rentBookToUser(@RequestBody BookRentInformation bookRentInformation) throws Exception {		
		Message message = new Message();
		message.setMessage(userService.rentBookToUser(bookRentInformation));
		return message;
	}
	
	@GetMapping("/")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

}
