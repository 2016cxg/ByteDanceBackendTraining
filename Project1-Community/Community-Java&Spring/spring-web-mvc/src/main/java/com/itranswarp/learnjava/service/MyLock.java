package com.itranswarp.learnjava.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyLock {
	
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

	private MyLock() {

	}
	public static MyLock getInstance() {
		return Once.INSTANCE ;
	}
	private static class Once{
		private static MyLock INSTANCE = new MyLock() ;
	}
	
	public Lock getRLock() {
		return this.r ;
	}
	public Lock getWLock() {
		return this.w ;
	}
}
