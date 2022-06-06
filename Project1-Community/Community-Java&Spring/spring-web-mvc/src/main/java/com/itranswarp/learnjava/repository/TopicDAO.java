package com.itranswarp.learnjava.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class TopicDAO {
	
	private HashMap<Integer, Topic > topicIndexMap ;
	
	
	private TopicDAO() {
		try {
			this.topicIndexMap = Init.getInstance().InitTopicIndexMap() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static TopicDAO getInstance() {
		return Once.INSTANCE ;
	}
	private static class Once{
		private static TopicDAO INSTANCE = new TopicDAO() ;
	}
	
	public Topic QueryTopicByParentId(int pid, String err){
		Set<Integer> set = this.topicIndexMap.keySet() ;
		if( !set.contains(pid) ) {
			err = "pid not in postIndexMap keyset" ;
			return null ;
		}
		err = null ;
		return this.topicIndexMap.get(pid) ;
	}
	
	public static void main(String[] args) {
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
		
		String err = null ;
		ArrayList<Post> postLst = postdao.QueryPostsByParentId(1, err) ;
		System.out.println( err) ;
		System.out.println( postLst) ;
		
	}
}