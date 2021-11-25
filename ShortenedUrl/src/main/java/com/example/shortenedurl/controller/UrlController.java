package com.example.shortenedurl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shortenedurl.model.Url;
import com.example.shortenedurl.service.UrlService;

@RestController
//@RequestMapping("/url")
public class UrlController {
	@Autowired
	private UrlService service;

	@GetMapping("/url/list")
	public List<Url> listAllUrl() {
		return service.listAllUrl();
	}

	@PostMapping("/user/{userId}/{longUrl}/create")
	public void createUrl(@PathVariable int userId, @PathVariable String longUrl) {
		service.createUrl(userId, longUrl);
	}

	@GetMapping("/{userId}/{shortenedUrl}")
	public String getLongUrl(@PathVariable int userId, @PathVariable String shortenedUrl) {
		return service.getLongUrl(userId, shortenedUrl);
	}

	@GetMapping("/user/{userId}/url/list")
	public List<String> getShortenedUrl(@PathVariable int userId) {
		return service.getShortenedUrl(userId);
	}

	@GetMapping("/user/{userId}/url/detail/{urlId}")
	public Url getDetailUrl(@PathVariable int userId, @PathVariable int urlId) {
		return service.getDetailUrl(userId, urlId);
	}

	@DeleteMapping("/user/{userId}/url/delete/{urlId}")
	public void deleteUrl(@PathVariable int userId, @PathVariable int urlId) {
		service.deleteUrl(userId, urlId);
	}

}
