package com.itranswarp.learnjava.controller;

import com.itranswarp.learnjava.service.PageInfo;

public class Message{
	private String topicId = null ;
	private String msg = null ;
	private PageInfo pageinfo = null ;
	
	public Message(String topicId2, String msg, PageInfo pageinfo) {
		this.topicId = topicId2 ;
		this.msg = msg ;
		this.pageinfo = pageinfo ;
	}
	
	public void disp() {
		System.out.println("....... disp Message") ;
		System.out.println("topic id: "+this.topicId ) ;
		System.out.println("msg: "+ this.msg);
		if( this.pageinfo != null)
			this.pageinfo.disp();
		System.out.println("....... end disp Message") ;
	}
}