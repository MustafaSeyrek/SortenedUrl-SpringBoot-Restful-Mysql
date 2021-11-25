package com.example.shortenedurl.service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shortenedurl.model.Url;
import com.example.shortenedurl.repository.UrlRepository;

@Service
public class UrlService {
	@Autowired
	private UrlRepository repository;

	// list all urls
	public List<Url> listAllUrl() {
		return repository.findAll();
	}

	// add new url
	public void createUrl(int userId, String longUrl) {
		Url url = new Url();
		String shortenedUrl = generateSortenedUrl();
		if (!controlDublicate(shortenedUrl)) {
			while (true) {
				shortenedUrl = generateSortenedUrl();
				if (controlDublicate(shortenedUrl)) {
					break;
				}
			}
		}
		url.setId(0);
		url.setUserId(userId);
		url.setLongUrl(longUrl);
		url.setShortenedUrl(shortenedUrl);
		repository.save(url);
	}

	public boolean controlDublicate(String shortenedUrl) {
		List<Url> listUrl = listAllUrl();
		boolean flag = true;
		for (int i = 0; i < listUrl.size(); i++) {
			if (listUrl.get(i).getShortenedUrl().equals(shortenedUrl)) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	// generate sortened random url
	public String generateSortenedUrl() {
		String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder();

		Random random = new Random();

		for (int i = 0; i < 10; i++) {
			int index = random.nextInt(charSet.length());
			char randomChar = charSet.charAt(index);
			sb.append(randomChar);
		}

		return sb.toString();
	}

	// get long url
	public String getLongUrl(int userId, String shortenedUrl) {
		String result = "Empty!";
		List<Url> listUrl = listAllUrl();
		for (int i = 0; i < listUrl.size(); i++) {
			if (listUrl.get(i).getUserId() == userId && listUrl.get(i).getShortenedUrl().equals(shortenedUrl)) {
				result = listUrl.get(i).getLongUrl();
				break;
			}
		}
		return result;
	}

	// get shortened url
	public List<String> getShortenedUrl(int userId) {
		List<Url> listUrl = listAllUrl();
		List<String> result = new ArrayList<>();
		for (int i = 0; i < listUrl.size(); i++) {
			if (listUrl.get(i).getUserId() == userId) {
				result.add(listUrl.get(i).getShortenedUrl());
			}
		}
		return result;
	}

	// get detail url
	public Url getDetailUrl(int userId, int urlId) {
		Url detailUrl = repository.findById(urlId).get();
		if (detailUrl.getUserId() == userId) {
			return detailUrl;
		} else {
			Url temp = new Url();
			return temp;
		}

	}

	// delete url
	public void deleteUrl(int userId, int urlId) {
		repository.deleteById(urlId);
	}
}
