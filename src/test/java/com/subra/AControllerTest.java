package com.subra;

import java.util.ArrayList;

import kafka.utils.threadsafe;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



@RunWith(SpringJUnit4ClassRunner.class)
public class AControllerTest {
	
	@Mock
	private HelloService helloService;

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
		System.out.println("--begin testing 1-with-service");
		//"when" Mock service auto injected to Controller with MockInject
		Mockito.when(helloService.getHello()).thenReturn("hola"); //service mocked
		
		mockmvc.perform(
				MockMvcRequestBuilders.get("/hello")			
				
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("hola"));
		Mockito.verify(helloService).getHello(); //must call verify also when use "when"
		
		System.out.println("--end testing 1-with-service");
	}	

	/*
	@Test
	public void testHello() throws Exception {
		System.out.println("--begin testing 1");
		
		mockmvc.perform(
				MockMvcRequestBuilders.get("/hello")			
				
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Hola Munda"));
		
		System.out.println("--end testing 1");
	}
	*/
	
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
	public void testPostJson() throws Exception {
		
		System.out.println("--begin testing 2a post");
		String json = "{ \"title\": \"Greeting\",  \"value\": \"Hello\" }";
		mockmvc.perform(
				
	
				MockMvcRequestBuilders.post("/hello/postjson")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				//.accept(MediaType.APPLICATION_JSON)
				
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect( MockMvcResultMatchers.jsonPath("$.title", "hobby").exists());
				.andExpect( MockMvcResultMatchers.jsonPath("$.title", "hobby").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.value", Matchers.is("chess posted")));
		
		System.out.println("--end testing 2a post");
	}		

	@Test
	public void testPostJsonViaService() throws Exception {
		
		System.out.println("--begin testing 2b post via service");
		//String json = "{ \"title\": \"Greeting\",  \"value\": \"Hello\" }";
		String json = "{ \"title\": \"Greeting\" }";
		ArrayList<Dummy> mockList = new ArrayList<>(); 
		mockList.add(new Dummy("title", "Greeting"));
		String jsonMockList = "[ { \"title\": \"Greeting\" } ]";
		Mockito.when(helloService.getJsonHello()).thenReturn(mockList);
		//Mockito.when(helloService.getJsonHello()).thenReturn(jsonMockList);
		
		mockmvc.perform(				
				MockMvcRequestBuilders.post("/hello/postjsonByservice")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				
				)
				.andExpect(MockMvcResultMatchers.status().isOk())			
				//.andExpect( MockMvcResultMatchers.jsonPath("$.title", "Greeting").exists());
				//.andExpect(MockMvcResultMatchers.content())
				.andExpect(MockMvcResultMatchers.content().json(jsonMockList, false))
				;
			
				
		
		System.out.println("--end testing 2b post via service");
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
