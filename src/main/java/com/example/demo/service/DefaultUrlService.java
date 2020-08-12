package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
/**
 * Implementation of service for managing url operations (encoding and decoding)
 *
 * @author andres
 */
public class DefaultUrlService implements UrlService {
	Map<String, String> urls = new HashMap<>();

	@Override
	public String encodeUrl(String url) {
		String hash = String.format("%x", url.hashCode());
		if (!urls.containsKey(hash)) {
			urls.put(hash, url);
		}
		return hash;
	}

	@Override
	public String decodeUrl(String tinyUrl) {
		return urls.getOrDefault(tinyUrl, null);
	}
}
