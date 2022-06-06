package com.itranswarp.learnjava.controller;


import com.itranswarp.learnjava.service.*;


public class QueryMessageFlow {
	private String topicId = null ;
	private String msg = null ;
	private PageInfo pageinfo = null ;
	
	public QueryMessageFlow(String id) {
		this.topicId = id ;
	}
	public Message Do() {
		Integer id = null ;
		try {
			id = Integer.parseInt(this.topicId) ;
		}catch( Exception e) {
			return new Message(this.topicId, e.getMessage(), null ) ;
		}
		
		String err  = null ;
		PageInfo pageinfo = null ;
		try {
			pageinfo = (new QueryPageInfoFlow(id)).Do(err);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			return new Message(this.topicId, e.getMessage(), null ) ;
		}
//		pageinfo.disp();
//		System.out.println( err ) ;
		return new Message(this.topicId, null, pageinfo) ;
	}
	
	public static void main(String[] args) {
		Message msg = new QueryMessageFlow("1").Do() ;
		msg.disp();
	}
}
