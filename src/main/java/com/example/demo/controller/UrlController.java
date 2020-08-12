package com.example.demo.controller;

import static com.example.demo.Constants.ROOT_API_URL;
import static java.util.Objects.isNull;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UrlService;

@RestController
@RequestMapping(ROOT_API_URL)
public class UrlController {

	@Autowired
	UrlService service;

	@GetMapping("/short")
	public ResponseEntity<String> shortUrl(@RequestParam String url) {
		try {
			new URL(url);
		} catch (MalformedURLException e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(service.encodeUrl(url));
	}

	@GetMapping("/long")
	public ResponseEntity<String> longUrl(@RequestParam String tinyUrl) {
		String originalUrl = service.decodeUrl(tinyUrl);
		if (isNull(originalUrl)) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(originalUrl);
		}
	}
}
