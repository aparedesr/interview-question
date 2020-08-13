package com.example.demo;

import static com.example.demo.Constants.EXAMPLE_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration test for URLController
 *
 * @author andres
 */

@SpringBootTest
@AutoConfigureMockMvc
class DemoIntegrationTests {

	@Autowired
	private MockMvc mvc;

	@BeforeAll
	public static void before() {
	}

	@Test
	void whenShortUrl_IsValid() throws Exception {
		mvc.perform(get("/short")
				.param("url",
						EXAMPLE_URL))
				.andExpect(content().string("44c0173"))
				.andExpect(status().isOk());
	}

	@Test
	void whenShortUrl_IsInvalid() throws Exception {
		mvc.perform(get("/short")
				.param("url", "AA"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void whenLongUrl_IsValid() throws Exception {
		whenShortUrl_IsValid();
		mvc.perform(get("/long")
				.param("tinyUrl",
						"44c0173"))
				.andExpect(content().string(EXAMPLE_URL))
				.andExpect(status().isOk());
	}

}
