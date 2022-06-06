package com.itranswarp.learnjava.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itranswarp.learnjava.repository.Topic;

@Controller
public class View {

	
	@RequestMapping(value = "/ex/foos/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getFoosBySimplePathWithPathVariable(
	  @PathVariable("id") long id) {
	    return "Get a specific Foo with id=" + id;
	}
	
	@GetMapping("/Community/PageInfo/get/{id}")
	@ResponseBody
	public String getPostInfo(
	  @PathVariable("id") long id) throws UnsupportedEncodingException {
		Message msg = new QueryMessageFlow(Integer.toString((int) id)).Do() ;
		
		Gson g = new Gson() ;
		String s = g.toJson(msg) ;
		String ss = new String(s.getBytes(), "UTF-8");
		return ss; 
	}
	
	@PostMapping("/Community/PageInfo/post/{id}")
	@ResponseBody
    public String WritePostInfo(
    		@RequestParam(name = "content") String content, 
    		@PathVariable("id") String id) throws UnsupportedEncodingException {    
    	
//    	System.out.println(id) ;
//    	System.out.println(content) ;
    	String err = null ;
        Message msg = new WriteMessageFlow(id, content).DoWritePost(err) ;
        
		Gson g = new Gson() ;
		String s = g.toJson(msg) ;
		String ss = new String(s.getBytes(), "UTF-8");
		return ss; 
//    	return "abc" ;
    }
	
	
	public static void main(String[] args) {
//		HashMap<Integer, Topic> topicIndexMap ;
//		String s= "{\"id\":2,\"title\":\"青训营来啦!\",\"content\":\"小哥哥，快到碗里来~\",\"create_time\":1650437640}" ;
//		Gson g = new Gson() ;
//		Topic topic = g.fromJson(s, Topic.class);
//		topic.disp();
//		s = g.toJson(topic) ;
//		ArrayList<Topic> tlst = new ArrayList<Topic>() ;
//		tlst.add(topic) ;
//		tlst.add(topic) ;
//		
//		s = g.toJson(tlst) ;
//		
//		ArrayList<Topic> atlst = g.fromJson(s,  new TypeToken<List<Topic>>(){}.getType() ) ;
//		System.out.println( s ) ;	
		
		
		Message msg = new QueryMessageFlow("2").Do() ;
		msg.disp();
		Gson g = new Gson() ;
		String s = g.toJson(msg) ;
		System.out.println( s ) ;
	}
}
