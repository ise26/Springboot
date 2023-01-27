package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

interface Calculator{
	void calci(int i, int j);
}


public class LambdaExpressionPractice {

	public static void main(String[] args) {
		
//	
//	List<String> name = new ArrayList<>();
//	name.add("a");
//	name.add("b");
//	name.add("c");
//	name.add("g");
//	name.add("h");
//	name.add("x");
//	name.add("v");
//	
//	LambdaExpressionPractice l1=new LambdaExpressionPractice();
//	l1.sortUsingJAVA8(name);
//	System.out.println(name);
//	}
//	
//	//using normal java7
////	 private void sortUsingJava7(List<String> names) {   
////	      Collections.sort(names, new Comparator<String>() {
////	         @Override
////	         public int compare(String s1, String s2) {
////	            return s2.compareTo(s1);
////	         }
////	      });
////	   }
//	   
//	//using java 8
//	public void sortUsingJAVA8(List<String> name) {
//	name.stream().filter(name.contains("a")).Collections.sort(name,(s1,s2)->s1.compareTo(s2));
		
//		Calculator cal = (i ,j)-> System.out.println(i-j);
//		cal.calci(10, 30);
		
		
	//consumer
		
		//Consumer<String> con =(t)->System.out.println(t);
		//con.accept("hii");
		
		List<Integer> l1 =Arrays.asList(1,2,3,6,89,98);
		l1.stream().filter(t -> t % 2!=0).forEach(t -> System.out.println(t));
		
		//supplier
		Supplier<String> ssupplier = () -> "asdfghj";
		List<String> l2 = Arrays.asList("a","as","hgf","dtfgdbf");
		l2.stream().findAny().orElseGet(ssupplier);
	}
}

//class ConsumerDemo implements Consumer<Integer>{
//
//	@Override
//	public void accept(Integer t) {
//		// TODO Auto-generated method stub
//		
//}


//}
