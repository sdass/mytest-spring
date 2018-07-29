package com.subra;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class HelloService {
	
	public String getHello(){
		
		return "Hola Munda";
	}
	
	public List<Dummy> getJsonHello(){	
		//String retJson =  "{ \"title\": \"mno\",  \"value\": \"777\" }";
		
		ArrayList<Dummy> ret = new ArrayList<>();
		ret.add(new Dummy("title", "mno"));
		ret.add(new Dummy("value", "777"));
		return ret;
	}

}
