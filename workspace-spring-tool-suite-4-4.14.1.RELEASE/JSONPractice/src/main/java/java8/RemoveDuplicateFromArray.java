package java8;

import java.util.Arrays;
import java.util.OptionalDouble;

public class RemoveDuplicateFromArray {
public static void main(String[] args) {
	
	int[] array= {12,44,55,55,37,44,8,9,6,4,44,8,9};
	
	int removedArray[]=Arrays.stream(array).distinct().toArray();
	
	for (int i : removedArray) {
		System.out.println(i);
	}
}}
