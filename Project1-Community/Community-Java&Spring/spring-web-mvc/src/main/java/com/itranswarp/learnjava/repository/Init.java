package com.itranswarp.learnjava.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.Gson;



public class Init {
	
	private HashMap<Integer, Topic> topicIndexMap ;
	private HashMap<Integer, ArrayList<Post>> postIndexMap ;
	
	String path = "C:\\Users\\cheng\\Desktop\\abc\\spring-web-mvc\\src\\main" ;
	
	
	
	private Init() {
		topicIndexMap = new HashMap<Integer, Topic >() ;
		postIndexMap = new HashMap<Integer, ArrayList<Post>>() ;
	}
	public static Init getInstance() {
		return Once.INSTANCE ;
	}
	private static class Once{
		private static Init INSTANCE = new Init() ;
	}
	
	public HashMap<Integer, Topic> InitTopicIndexMap() throws IOException{
		String topicpath = path + "\\data\\topic" ;
		
		File file = new File(topicpath);
	    BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
    		Gson g = new Gson() ;
    		Topic topic = g.fromJson(st, Topic.class);
    		topic.disp();
    		this.topicIndexMap.put(topic.getId(), topic) ;
	    }
        
        return this.topicIndexMap ;
	}
	
	public HashMap<Integer, ArrayList<Post>> InitPostIndexMap() throws IOException{
		String topicpath = path + "\\data\\post" ;
		
		File file = new File(topicpath);
	    BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
    		Gson g = new Gson() ;
    		Post post = g.fromJson(st, Post.class);
    		post.disp();
    		
    		ArrayList<Post> postLst = this.postIndexMap.get(post.getParentId()) ;
    		if(postLst == null) {
    			postLst = new ArrayList<Post>() ;
    		}
    		postLst.add(post) ;
    		
    		this.postIndexMap.put(post.getParentId(), postLst) ;
	    }
        
        return this.postIndexMap ;
	}

	
	public static void main(String[] args) throws IOException {
//		HashMap<Integer, Topic> topicIndexMap ;
//		String s= "{\"id\":2,\"title\":\"青训营来啦!\",\"content\":\"小哥哥，快到碗里来~\",\"create_time\":1650437640}" ;
//		Gson g = new Gson() ;
//		Topic topic = g.fromJson(s, Topic.class);
//		topic.disp();
//		s = g.toJson(topic) ;
//		System.out.println( s ) ;
		Init init = new Init() ;
		HashMap<Integer, Topic> topicIndexMap = init.InitTopicIndexMap() ;
		System.out.println( topicIndexMap ) ;
		
		HashMap<Integer, ArrayList<Post>> postIndexMap = init.InitPostIndexMap() ;
		System.out.println( postIndexMap ) ;
	}
}
