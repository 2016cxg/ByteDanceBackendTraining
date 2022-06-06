package com.itranswarp.learnjava.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.google.gson.Gson;

public class PostDAO {
	
	private HashMap<Integer, ArrayList<Post>> postIndexMap ;
	String path = "C:\\Users\\cheng\\Desktop\\abc\\spring-web-mvc\\src\\main" ;
	
	
	private PostDAO() {
		try {
			this.postIndexMap = Init.getInstance().InitPostIndexMap() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static PostDAO getInstance() {
		return Once.INSTANCE ;
	}
	private static class Once{
		private static PostDAO INSTANCE = new PostDAO() ;
	}
	
	public ArrayList<Post> QueryPostsByParentId(int pid, String err){
		Set<Integer> set = this.postIndexMap.keySet() ;
		if( !set.contains(pid) ) {
			err = "pid not in postIndexMap keyset" ;
			return null ;
		}
		err = null ;
		return this.postIndexMap.get(pid) ;
	}
	
	public int MaxPostId() {
		ArrayList<Integer> lst = new ArrayList<Integer>();
		int id_ = -1 ;
		for(int key: this.postIndexMap.keySet() ) {
			for( Post post : this.postIndexMap.get(key)) {
				lst.add(post.getId()) ;
				if( post.getId() > id_ ) {
					id_ = post.getId() ;
				}
			}
		}
		return id_ ;
		
	}
	
	public Post WritePostIndexMapByParentId(int pid, String content, String err) {
		Set<Integer> set = this.postIndexMap.keySet() ;
		if( !set.contains(pid) ) {
			err = "pid not in postIndexMap keyset" ;
			return null ;
		}
		
		int id_ = this.MaxPostId()+1 ;
		System.out.println("this is id: "+id_ ) ;
		long create_time = 200000000 ;
		Post post = new Post(id_, pid, content, create_time ) ;
		ArrayList<Post> lst = this.postIndexMap.get(pid) ;
		lst.add(post) ;
		this.postIndexMap.put(pid, lst) ;
		
		err = null ;
		return post ;
	}
	
	public void WritePostFile(Post post, String err) throws IOException {
		Gson gson = new Gson() ;
		String s = gson.toJson(post) ;
		
		String postpath = this.path + "\\data\\post" ;
		
		FileWriter fw = new FileWriter(postpath, true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    bw.write( s );
	    bw.newLine();
	    bw.close();
	    
	    err = null ;
	}
	
	public void DoWritePost(int pid, String content, String err) throws IOException {
		String tmperr = null ;
		Post post = this.WritePostIndexMapByParentId(pid, content, tmperr) ;
		if( tmperr != null) {
			err = tmperr ;
			return ;
		}
		this.WritePostFile(post, tmperr);
		if( tmperr != null) {
			err = tmperr ;
			return ;
		}
		
		err = null ;
		return ;
	}
	
	public static void main(String[] args) throws IOException {
//		PostDAO postdao1 = PostDAO.getInstance() ;
//		PostDAO postdao2 = PostDAO.getInstance() ;
//		
//		System.out.println( System.identityHashCode(postdao1));
//		System.out.println( System.identityHashCode(postdao2)) ;
//		
//		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>() ;
//		map.put(1, 1) ;
//		map.put(2, 2) ;
//		
//		Set<Integer> set = map.keySet() ;
//		Boolean bol = set.contains(1) ;
//		System.out.println(bol);
		
		PostDAO postdao = PostDAO.getInstance() ;
//		
//		String err = null ;
//		ArrayList<Post> postLst = postdao.QueryPostsByParentId(1, err) ;
//		System.out.println( err) ;
//		System.out.println( postLst) ;
		
//		String err = null ;
//		Post post = postdao.WritePostIndexMapByParentId(1, "abc", err) ;
//		
//		post.disp();
//		
//		postdao.WritePostFile(post, err);
		
		String err = null ;
		postdao.DoWritePost(1, "abcd", err);
		if(err != null) {
			System.out.println("not null: "+err) ;
		}

	}
}
