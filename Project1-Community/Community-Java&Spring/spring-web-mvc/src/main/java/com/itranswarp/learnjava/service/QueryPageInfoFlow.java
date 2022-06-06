package com.itranswarp.learnjava.service;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

import com.itranswarp.learnjava.repository.*;

class PostRunnable implements Runnable{
	
	private ArrayList<Post> pLst = null ;
	private int topicId = 0 ;
	private String err = null ;
	
	public PostRunnable(int topicid, String err ) {
		this.topicId = topicid ;
		this.err = err ;
	}
	
	@Override 
	public void run() {
		PostDAO postdao = PostDAO.getInstance() ;
		
		this.pLst = postdao.QueryPostsByParentId(this.topicId, this.err) ;
	}
	public ArrayList<Post> getPostList(){
		return this.pLst ;
	}
}

class TopicRunnable implements Runnable{
	private Topic topic = null ;
	private int topicId = 0 ;
	private String err = null ;
	
	public TopicRunnable(int topicId, String err) {
		this.topicId = topicId ;
		this.err = err ;
	}
	
	@Override
	public void run() {
		TopicDAO topicdao = TopicDAO.getInstance() ;
		this.topic = topicdao.QueryTopicByParentId(this.topicId, this.err) ;
	}
	public Topic getTopic() {
		return this.topic ;
	}
}

public class QueryPageInfoFlow {
	Integer topicId = null ;
	PageInfo pageinfo = null ;
	Topic  topic = null ;
	ArrayList<Post> pLst = null ;
	
	
	public QueryPageInfoFlow(int topicid) {
		this.topicId = topicid ;
	}
	
	public PageInfo Do(String err ) throws InterruptedException {
		
		
		
		this.checkParam(err ) ;
		if( err != null) {
			return null ;
		}
		
		Lock rlock = MyLock.getInstance().getRLock() ;
		rlock.lock();
		
		this.prepareInfo() ;
		rlock.unlock();
		
		this.pageinfo = this.packPageInfo(err) ;
		if( err != null ) {
			return null ;
		}
		return this.pageinfo ;
	}
	public void checkParam(String err) {
		if( this.topicId< 0) {
			err = "topic id is below 0, invalid" ;
			return ;
		}
		return ;
	}
	public void preparePost(int topicId, String err) {

	}
	public void prepareTopic(int topicId, String err) {
		TopicDAO topicdao = TopicDAO.getInstance() ;
		this.topic = topicdao.QueryTopicByParentId(topicId, err) ;
	}
	public void prepareInfo() throws InterruptedException {
		PostRunnable postrun = new PostRunnable(this.topicId, null) ;
		TopicRunnable topicrun = new TopicRunnable(this.topicId, null) ;
		Thread postthread = new Thread( postrun ) ;
		Thread topicthread = new Thread( topicrun ) ;
		postthread.start();
		topicthread.start();
		postthread.join();
		topicthread.join();
		
		this.pLst = postrun.getPostList() ;
		this.topic = topicrun.getTopic() ;
	}
	
	public PageInfo packPageInfo(String err) {
		if( this.topic == null ) {
			return null ;
		}
		if( this.pLst == null ) {
			return null ;
		}
		
		err = null ;
		return new PageInfo(this.topic, this.pLst) ;
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		String err  = null ;
		PageInfo pageinfo = (new QueryPageInfoFlow(1)).Do(err) ;
		pageinfo.disp();
		System.out.println( err ) ;
	}
}
