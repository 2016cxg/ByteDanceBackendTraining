package com.itranswarp.learnjava.repository;

public class Post{
	private int id ;
	private int parent_id ;
	private String content ;
	private long create_time ;
	
	public Post(int id_, int pid, String cnt, long crttime) {
		this.id = id_ ;
		this.parent_id = pid ;
		this.content = cnt ;
		this.create_time = crttime ;
	}
	
	public int getParentId() {
		return this.parent_id ;
	}
	public int getId() {
		return this.id ;
	}
	public void disp() {
		System.out.println(this.id+" "+ this.parent_id+ " "+ this.content+ " "+ this.create_time);
	}
}