package com.itranswarp.learnjava.repository;

public class Topic{
	private int id ;
	private String title ;
	private String content ;
	private long create_time ;
	
	public int getId() {
		return this.id ;
	}
	public void disp() {
		System.out.println(this.id+" "+ this.title+ " "+ this.content+ " "+ this.create_time);
	}
}