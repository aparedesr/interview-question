package com.example.demo.service;

import com.example.demo.persistence.RecordNotFoundException;

/**
 * Interface exposing service url operations
 *
 * @author andres
 */
public interface UrlService {
	String encodeUrl(String url);

	String decodeUrl(String tinyUrl) throws RecordNotFoundException;

	void purgeOldUrls();
}
