package com.example.demo.service;

import static com.example.demo.Constants.URL_NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.persistence.RecordNotFoundException;
import com.example.demo.persistence.URLEntity;
import com.example.demo.persistence.UrlRepository;

@Service
/**
 * Implementation of service for managing url operations (encoding and decoding)
 *
 * @author andres
 */
public class DefaultUrlService implements UrlService {
	@Autowired
	UrlRepository repository;

	@Override
	public String encodeUrl(String url) {
		String hashcode = String.format("%x", url.hashCode());
		if (!repository.findByURL(url).isPresent()) {
			URLEntity ue = new URLEntity();
			ue.setUrl(url);
			ue.setHashcode(hashcode);
			repository.save(ue);
		}
		return hashcode;
	}

	@Override
	public String decodeUrl(String tinyUrl) throws RecordNotFoundException {
		URLEntity ue = repository.findByHashCode(tinyUrl).orElseThrow(() -> new RecordNotFoundException(URL_NOT_FOUND));
		return ue.getUrl();
	}
}
