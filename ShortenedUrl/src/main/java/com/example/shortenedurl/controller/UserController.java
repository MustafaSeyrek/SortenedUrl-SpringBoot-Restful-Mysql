package com.example.shortenedurl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shortenedurl.model.User;
import com.example.shortenedurl.service.UserService;

@RestController
//@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/user/list")
	public List<User> listAllUser() {
		return service.listAllUser();
	}

	@PostMapping("/user/signup")
	public void createUser(@RequestBody User user) {
		service.createUser(user);
	}
}
