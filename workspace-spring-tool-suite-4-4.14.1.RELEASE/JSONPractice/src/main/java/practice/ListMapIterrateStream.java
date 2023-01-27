package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListMapIterrateStream {

	public static void main(String[] args) {
		
	
//	List<String> l1 = new ArrayList<>();
//	l1.add("shubham");
//	l1.add("rohan");
//	l1.add("ankita");
//	l1.add("akash");
	
//	for (String string : l1) {
//		System.out.println(string);
//	}
	
	//l1.stream().forEach(t -> System.out.println(t));
	
	Map<String, Integer> map = new HashMap<>();
	map.put("akash", 65);
	map.put("shubham", 99);
	map.put("ankita", 75);
	map.put("rohan", 65);
	
	map.forEach((key,value)->System.out.println(key+ " " +value));
//	map.entrySet().stream().filter(t -> t.getKey().equals("shubham")) .forEach(obj-> System.out.println(obj));
//  map.entrySet().stream().filter(t -> t.getValue().equals(99)).forEach(obj-> System.out.println(obj));
	
}}
