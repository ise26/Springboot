package DS;

import java.util.Arrays;
import java.util.List;

public class MigratoryBird {
	public static void main(String[] args) {

		List<Integer> arr = Arrays.asList(1,2,4,9,9,2);
		
		int maximum=0;
		int element=0;
		
		
		
		for(int i=0;i<arr.size();i++) {
			if(arr.get(i)>maximum) {
				maximum=arr.get(i);
				element=i;
			}
		}
		System.out.println(element);
		
		
//		for (int i = 0; i < arr.size(); i++) {
//			int count = 0;
//			for (int j = 0; j < arr.size(); j++) {
//
//				if (arr.get(i) == arr.get(j)) {
//					count++;
//				}
//				if(maximum<count) {
//					maximum=count;
//					element=arr.get(i);
//				}
//			}System.out.println("count of "+arr.get(i)+" is " +count);
//			
//			System.out.println("element "+element+" count is "+maximum);
//		}
	}
}
