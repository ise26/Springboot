package practice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class LisrReverse {
public static void main(String[] args) throws ParseException {
	
//	List<Integer> list1= new ArrayList<Integer>();
//	List<Integer> list2= new ArrayList<Integer>();
//	list1.add(1);
//	list1.add(60);
//	list1.add(88);
//	list1.add(2);
//	
//	for (int i = list1.size()-1; i>=0 ; i--) {
//		list2.add(list1.get(i));
//	}
//	System.out.println(list2);
	
	
	////star pattern
//	for(int i=0;i<5;i++) {
//		for(int j=0;j<5;j++) {
//			System.out.print("* ");
//		}
//		System.out.println();
//	}
	
//	for(int i=1;i<=5;i++) {
//		for(int j=1;j<=i;j++) {
//			System.out.print("* ");
//		}System.out.println();
//	}
//	
	
//	for(int i=5;i>0;i--) {
//		for(int j=i;j>0;j--) {
//			System.out.print("* ");
//		}System.out.println();
//	}
	
	
//	for(int i=1;i<=5;i++) {
//		for(int j=i;j<=5;j++) {
//			System.out.print(" ");
//		}
//		for(int j=1;j<=i;j++) {
//			System.out.print("*");
//		}
//	System.out.println();
//	}
	
//	for(int i=1;i<=5;i++) {
//		for(int j=1;j<=i;j++) {
//			System.out.print(" ");
//		}
//		for(int j=i;j<=5;j++) {
//			System.out.print("*");
//		}
//		for(int j=i;j<=5;j++) {
//			System.out.print("*");
//		}
//	System.out.println();
//	}
	
	
	
//	
	
	String s="1:54:53Pm";
	SimpleDateFormat input=new SimpleDateFormat("hh:mm:ssaa");
	SimpleDateFormat output=new SimpleDateFormat("HH:mm:ss");
	
	Date date=input.parse(s);
	
	System.out.println(output.format(date));
	
	
	
//	List<Integer> list=Arrays.asList(1,2,3,4,5);
//	List<Integer> list2=new ArrayList<>();
//	
//	int sum=0;
//	for(int i=0; i<list.size();i++) {
//	
//			int exceptValue=list.get(i);
//			
//			for(int j=0;j<list.size();j++) {
//				if(list.get(j)!=exceptValue) {
//					sum+=list.get(j);
//			
//				}
//			}
//			System.out.println(sum);
//	list2.add(sum);
//	sum=0;
//		}
//	System.out.println(list2);
//	int max=0;
//	int min=0;
//	
//	Collections.sort(list2);
//	System.out.println(list2);
//	int max1=list2.get(list2.size()-1);
//	System.out.println("max "+max1);
//	System.out.println("min "+list2.get(0));
	
	
	
	
//	for(int i=0;i<list2.size();i++) {
//		for(int j=0;j<list2.size();j++) {
//			
//			if(list2.get(i)>list2.get(j)) {
//				max=list2.get(i);
//			}
//			
//			System.out.println(list2.get(i)+" "+list2.get(j));	
//		}
//		System.out.println(max);
//	}
//	
//	int count=0;
//	int minimum=list.get(0);
//	int maximum=list.get(0);
//	for(int i=0;i<list.size();i++) {
//		if(minimum>list.get(i)) {
//			minimum=list.get(i);
//		}
//		if(maximum<list.get(i)) {
//			maximum=list.get(i);
//		}
//	}
//	for (Integer i : list) {
//		if(maximum==i) {
//			count++;
//		}
//	}
//	//System.out.println(minimum);
//	//System.out.println(maximum);
//	System.out.println(count);
//	
	
//	
//	  for(int i=1;i<=6;i++){
//	        for(int j=i;j<=6;j++){
//	            System.out.print("*");
//	        }
//	        for(int j=1;j<=i;j++){
//	            System.out.print(" #");
//	        }
//	        System.out.println();
//	    }
	
	
//	List<Integer> list=Arrays.asList(1, 2, 3, 4, 5);
//	int min=list.get(0);
//	int max=list.get(0);
//	int sum=0;
//	for(int i=0;i<list.size();i++) {
//		sum+=list.get(i);
//		
//		if(min>list.get(i)) {
//			min=list.get(i);
//		}
//		if(max<list.get(i)) {
//			max=list.get(i);
//		}
//	}
//	System.out.println("min:"+min+" max:"+max+" sum:"+sum);
//	System.out.println(sum-min);
//	System.out.println(sum-max);
	
}
		
}

