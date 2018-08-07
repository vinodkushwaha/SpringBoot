package com.boot;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.boot.model.JournalEntry;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("qa")
public class SpringBootJournalApplicationTests
{
	private final String						SPRING_BOOT_MATCH	= "Spring Boot";
	private final String						CLOUD_MATCH			= "Cloud";

	@Autowired
	private MappingJackson2HttpMessageConverter	mappingJackson2HttpMessageConverter;

	private MediaType							contentType			= new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	private MockMvc								mockMvc;

	@Autowired
	private WebApplicationContext				webApplicationContext;

	@Before
	public void setup() throws Exception
	{
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getAll() throws Exception
	{
		mockMvc.perform(get("/journal/all"))
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(contentType))
		       .andExpect(jsonPath("$", iterableWithSize(5)))
		       .andExpect(jsonPath("$[0]['title']", containsString(SPRING_BOOT_MATCH)));
	}

	@Test
	public void findByTitle() throws Exception
	{
		mockMvc.perform(get("/journal/findBy/title/" + CLOUD_MATCH))
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(contentType))
		       .andExpect(jsonPath("$", iterableWithSize(1)))
		       .andExpect(jsonPath("$[0]['title']", containsString(CLOUD_MATCH)));
	}

	@Test
	public void add() throws Exception
	{
		mockMvc.perform(post("/journal").content(this.toJsonString(new JournalEntry("Spring Boot Testing", "Create Spring Boot Tests", "17-01-2017")))

		                                .contentType(contentType))
		       .andExpect(status().isOk());
	}

	/*
	 * @Test public void vanillaService() { RestTemplate restTemplate = new
	 * RestTemplate(); Greet greet =
	 * restTemplate.getForObject("http://localhost:8080/greet", Greet.class);
	 * Assert.assertEquals("Hello World!", greet.getMessage());
	 * 
	 * }
	 */

	@SuppressWarnings("unchecked")
	protected String toJsonString(Object obj) throws IOException
	{
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(obj, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
