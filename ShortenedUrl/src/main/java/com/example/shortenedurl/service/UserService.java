package com.example.shortenedurl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shortenedurl.model.User;
import com.example.shortenedurl.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	// list all users
	public List<User> listAllUser() {
		return repository.findAll();
	}

	// add new user
	public void createUser(User user) {
		repository.save(user);
	}

}
