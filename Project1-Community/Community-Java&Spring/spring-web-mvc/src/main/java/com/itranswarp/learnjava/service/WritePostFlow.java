package com.itranswarp.learnjava.service;

import java.io.IOException;
import java.util.concurrent.locks.Lock;

import com.itranswarp.learnjava.repository.PostDAO;

public class WritePostFlow {
	
	public void checkParam(int pid, String err) {
		if(pid<0) {
			err = "in service, id is below 0" ;
			return ;
		}
		err = null ;
		return ;
	}
	
	public void WritePost(int pid, String content , String err) {
		PostDAO postdao = PostDAO.getInstance() ;
		try {
			postdao.DoWritePost(pid, content, err);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if( err != null) {
			return ;
		}
		err = null ;
		return ;
	}
	
	public void DoWritePost(int pid, String conent, String err) {
		this.checkParam(pid, err);
		
		Lock wlock = MyLock.getInstance().getWLock() ;
		wlock.lock();
		if( err != null) {
			return ;
		}
		this.WritePost(pid, conent, err);
		wlock.unlock(); 
		
		if( err != null) {
			return ;
		}
		err = null ;
		return ;
	}
	
	
	public static void main(String[] args) {
		String err = null ;
		WritePostFlow flow = new WritePostFlow() ;
		flow.DoWritePost(1, "abc", err);
		
		System.out.println( err ) ;
	}
}
