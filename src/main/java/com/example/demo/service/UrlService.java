package com.example.demo.service;

/**
 * Interface exposing service url operations
 *
 * @author andres
 */
public interface UrlService {
	String encodeUrl(String url);

	String decodeUrl(String tinyUrl);
}
