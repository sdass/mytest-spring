package com.subra;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



@RunWith(SpringJUnit4ClassRunner.class)
public class AControllerTest {

	@InjectMocks
	private AController aController;
	
	private MockMvc mockmvc;
	
	@Before
	public void setup1() throws Exception{
		mockmvc = MockMvcBuilders.standaloneSetup(aController)
				.build();
		
	}
	
	@Test
	public void testHello() throws Exception {
		
		System.out.println("--begin testing 1");
		
		mockmvc.perform(
				MockMvcRequestBuilders.get("/hello")			
				
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("hello world"));
		
		System.out.println("--end testing 1");
	}
	
	
	@Test
	public void testJson() throws Exception {
		
		System.out.println("--begin testing 2");
		
		mockmvc.perform(
				MockMvcRequestBuilders.get("/hello/json")
				.accept(MediaType.APPLICATION_JSON)
				
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect( MockMvcResultMatchers.jsonPath("$.title", "hobby").exists());
				.andExpect( MockMvcResultMatchers.jsonPath("$.title", "hobby").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.value", Matchers.is("chess")));
		
		System.out.println("--end testing 2");
	}
	
	
	@Test
	public void testJsonList() throws Exception {
		
		System.out.println("--begin testing 3");
		
		mockmvc.perform(
				MockMvcRequestBuilders.get("/hello/jsonlist")
				.accept(MediaType.APPLICATION_JSON)
				
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect( MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(3)));
				
		
		System.out.println("--end testing 3");
	}	
	
	
	
}
