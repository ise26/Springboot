package DS;

import java.util.Arrays;
import java.util.List;

public class DivisibleSumHackerrank {

	
	public static int divisibleSumPairs(int n, int k, List<Integer> ar) {
		
		int sum=0;
		int count=0;
		
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j <n; j++) {
				sum=ar.get(i)+ar.get(j);
				System.out.println("i: "+ar.get(i)+ " j: "+ar.get(j)+" sum: "+sum);
				if(sum%k==0) {
					System.out.println("comdition true sum:" +sum);
					count++;
				}
			}
			
		}
		return count;
	    

	    }
	public static void main(String[] args) {
		DivisibleSumHackerrank dsh=new DivisibleSumHackerrank();
		List<Integer> li=Arrays.asList(1, 3, 2, 6, 1, 2);
		System.out.println(dsh.divisibleSumPairs(6, 3, li));
	}
}
