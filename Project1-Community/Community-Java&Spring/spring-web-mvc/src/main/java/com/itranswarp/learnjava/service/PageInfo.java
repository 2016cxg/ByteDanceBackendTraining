package com.itranswarp.learnjava.service;

import java.util.ArrayList;

import com.itranswarp.learnjava.repository.Post;
import com.itranswarp.learnjava.repository.Topic;

public class PageInfo{
	private Topic topic = null ;
	private ArrayList<Post> postLst = null ;
	
	public PageInfo(Topic topic, 
			ArrayList<Post> pLst) {
		this.topic = topic ;
		this.postLst = pLst ;
	}
	public void disp() {
		System.out.println(".............. disp PageInfo ") ;
		System.out.println("topic") ;
		this.topic.disp() ;
		System.out.println("postLst") ;
		for(Post pst: this.postLst) {
			pst.disp();
		}
		System.out.println("................. end disp PageInfo") ;
	}
}