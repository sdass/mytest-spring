package com.subra;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class AController {
	
	@GetMapping
	String showRes(){
		return "hello world";
	}
	
	@GetMapping(value="/json", produces=MediaType.APPLICATION_JSON_VALUE)
	Dummy showJson(){
		return new Dummy("hobby", "chess" );
		
	}
	
	
	@GetMapping(value="/jsonlist", produces=MediaType.APPLICATION_JSON_VALUE)
	List<Dummy> showListJson(){
		List<Dummy>  retlist = new ArrayList<>();
		retlist.add(new Dummy("hobby", "chess" ));
		retlist.add(new Dummy("grade", "7th" ));
		retlist.add(new Dummy("town", "Fair Lawn" ));
		return retlist;
		
	}
	
	private class Dummy{
		private String title;
		private String value;
		public Dummy(String title, String value) {
			super();
			this.title = title;
			this.value = value;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		
	}

}
