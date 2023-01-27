package java8;

import java.util.Arrays;

public class SortArrayWithoutSortFunction {
public static void main(String[] args) {
	
	int a[]= {12,44,55,65,2,34};
	
	for (int i = 0; i < a.length; i++) {
		for (int j = i+1; j < a.length; j++) {
			int temp=0;
			
			if(a[i] > a[j]) {
				temp=a[i];
				a[i]=a[j];
				a[j]=temp;
			}
		}
	}
	
	System.out.println(Arrays.toString(a));
	
}
}
