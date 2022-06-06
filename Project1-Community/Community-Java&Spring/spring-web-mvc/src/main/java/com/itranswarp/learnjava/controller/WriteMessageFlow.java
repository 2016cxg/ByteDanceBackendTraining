package com.itranswarp.learnjava.controller;

import com.itranswarp.learnjava.service.WritePostFlow;

public class WriteMessageFlow {
	private String pid= null ;
	private String conent = null ;
	
	public WriteMessageFlow(String pid, String content) {
		this.pid = pid ;
		this.conent = content ;
	}
	
	public Message DoWritePost(String err) {
		WritePostFlow flow = new WritePostFlow() ;
		flow.DoWritePost(Integer.parseInt(this.pid), this.conent, err);
		if( err != null) {
			return new Message(this.pid, err, null ) ;
		}
		
		return new Message(this.pid, "success", null) ;
		
	}
	
	public static void main(String[] args) {
		WriteMessageFlow flow = new WriteMessageFlow("1", "eeee") ;
		String err = null ;
		Message message = flow.DoWritePost(err) ;
		message.disp();
	}
}
