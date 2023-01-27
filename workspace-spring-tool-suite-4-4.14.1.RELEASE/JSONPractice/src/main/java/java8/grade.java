package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class grade {
	public static void main(String[] args) {
//
		List<Integer> grades = Arrays.asList(73, 67, 38, 22);
		List<Integer> finalgrades = new ArrayList<>();
//
//		
//		 for (int i=0; i<grades.size();i++) {
//		             int temp = grades.get(i);
//		             
//		             if (temp % 5 == 3) {
//		                 temp += 2;
//		                 if ((temp - grades.get(i)) < 3 && grades.get(i)>33) {
//		                     finalgrades.add(temp);
//		                 }else {
//		                     finalgrades.add(grades.get(i));
//		                 }
//		             }
//		             if (temp % 5 == 2) {
//		                 temp += 3;
//		                 if ((temp - grades.get(i)) < 3 && grades.get(i)>33) {
//		                     finalgrades.add(temp);
//		                 }else {
//		                     finalgrades.add(grades.get(i));
//		                 }
//		             }
//		             if (temp % 5 == 1) {
//		                 temp += 4;
//		                 if ((temp - grades.get(i)) < 3 && grades.get(i)>33) {
//		                     finalgrades.add(temp);
//		                 }else {
//		                     finalgrades.add(grades.get(i));
//		                 }
//		             }
//		             if (temp % 5 == 4) {
//		                 temp += 1;
//		                 if ((temp - grades.get(i)) < 3 && grades.get(i)>33) {
//		                     finalgrades.add(temp);
//		                 }else {
//		                     finalgrades.add(grades.get(i));
//		                 }
//		             }
//
//		         }
//		 
		 
		 List<Integer> answer = new ArrayList<>();
	        int len = grades.size();
	        for(int i=0; i<len; i++){
	            
	            int originalvalue = grades.get(i);
	            int num = grades.get(i) % 5;
	            
	            if(originalvalue == 100 || originalvalue < 38){
	                answer.add(originalvalue);
	            }else {
	                if(num >= 3){
	                    answer.add(originalvalue + (5 - num));
	                }else {
	                    answer.add(originalvalue);
	                }
	            }
	        }
			
System.out.println(answer);
}}
