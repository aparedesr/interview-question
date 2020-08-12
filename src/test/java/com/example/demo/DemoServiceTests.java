package com.example.demo;

import static com.example.demo.Constants.EXAMPLE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.UrlService;

@SpringBootTest
class DemoServiceTests {

	@Autowired
	private UrlService service;

	@BeforeAll
	public static void before() {
	}

	@Test
	void encodeUrl_IsValid() throws Exception {
		assertEquals(service.encodeUrl(
				EXAMPLE_URL),
				"44c0173");
	}

	@Test
	void encodeUrl_IsInValid() throws Exception {
		assertNotEquals(service.encodeUrl(
				EXAMPLE_URL),
				"44c0175");
	}

	@Test
	void dencodeUrl_IsValid() throws Exception {
		encodeUrl_IsValid();
		assertEquals(service.decodeUrl("44c0173"),
				EXAMPLE_URL);
	}

}
