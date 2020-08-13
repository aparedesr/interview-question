package com.example.demo;

import static com.example.demo.Constants.EXAMPLE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.persistence.URLEntity;
import com.example.demo.persistence.UrlRepository;

@SpringBootTest
@AutoConfigureMockMvc
/**
 * Testing the JpaRepository
 * 
 * @author andres
 */
class DemoPersistenceTests {

	@Autowired
	private UrlRepository repository;

	@BeforeEach
	public void before() {
		repository.deleteAll();
	}

	@Test
	void whenUrl_IsAdded() throws Exception {
		URLEntity ue = new URLEntity();
		ue.setUrl(EXAMPLE_URL);
		ue.setHashcode("44c0183");
		repository.save(ue);
		assertEquals(repository.count(), 1);
	}

	@Test
	void whenFindByHashcode_IsFound() throws Exception {
		whenUrl_IsAdded();
		assertEquals(repository.findByHashCode("44c0183").isPresent(), true);
	}

}
