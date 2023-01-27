package practice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HashmapIterator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Map<Integer, String> map = new HashMap<Integer, String>(); 
		map.put(1, "shubham");
		map.put(2, "hemant");
		map.put(3, "rohan");
		map.put(4, "adesh");
		map.put(5, "paresh");
		map.put(6, "ry");
		map.put(7, "akash");

		// iterate using entrySet
//		Iterator<Entry<Object,Object>> itr = map.entrySet().iterator();
//		while (itr.hasNext()) {
//			Entry<Object,Object> e =itr.next();
//			if (e.getValue().equals("ry")) {
//				System.out.println(e.getKey());
//			}
//		}

		// iterate using ketSet
		/*
		 * Iterator<Integer> itr= map.keySet().iterator(); while (itr.hasNext()) {
		 * Object key =itr.next(); if(key.equals(2)) { System.out.println(key+" "
		 * +map.get(key)); } }
		 */

		// for each loop
//		for (Map.Entry<Integer, String> entry : map.entrySet()) {
//			System.out.println(entry.getValue());
//			System.out.println(entry.getKey());
//		}

		Iterator<Integer> itr=map.keySet().iterator();
		while (itr.hasNext()) {
			System.out.println(map.get(2));
			
		}
		
	}

}
