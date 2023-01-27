package java8;

public class FirstLArgestSecondLargestNoArray {
	
	
	int a=10;
	
	public static void main(String[] args) {
		int[] array = { 12, 44, 55, 55, 37, 44, 8, 9, 6, 4, 44, 8, 9 };

		int firstMax = 0;
		int secondMax = 0;
		for (int i = 0; i < array.length; i++) {
			if (firstMax < array[i]) {
				secondMax = firstMax;
				firstMax = array[i];
			}
		}
		System.out.println(firstMax);
		System.out.println(secondMax);
		
	}
}
