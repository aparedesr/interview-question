package com.example.demo;

import static com.example.demo.Constants.EXAMPLE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.example.demo.persistence.URLEntity;
import com.example.demo.persistence.UrlRepository;

/**
 * Testing the JpaRepository
 *
 * @author andres
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DemoPersistenceTests {

	@Autowired
	private UrlRepository repository;

	@BeforeAll
	public void before() {
		repository.deleteAll();
	}

	@Test
	void whenUrl_IsAdded() throws Exception {
		repository.deleteAll();
		URLEntity ue = new URLEntity();
		ue.setUrl(EXAMPLE_URL);
		ue.setHashcode("44c0183");
		ue.setCreateDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		repository.save(ue);
		assertEquals(repository.count(), 1);
	}

	@Test
	void whenFindByHashcode_IsFound() throws Exception {
		whenUrl_IsAdded();
		assertEquals(repository.findByHashCode("44c0183").isPresent(), true);
		repository.deleteAll();
	}

	@Test
	@Sql({ "/old_urls_data.sql" })
	void whenOldUrls_ArePurged() {
		// The test file has 6 records where 5 are older than 30 mins and 1 (id=1006 and hashCode=8f8967e3)
		// is in the future
		assertEquals(repository.count(), 6);
		repository.deleteOldUrls();
		assertEquals(repository.count(), 1);
		assertEquals(repository.findByHashCode("8f8967e3").isPresent(), true);
		repository.deleteAll();

	}

}
