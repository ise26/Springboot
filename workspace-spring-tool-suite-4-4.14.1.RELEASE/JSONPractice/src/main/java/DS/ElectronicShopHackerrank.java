package DS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ElectronicShopHackerrank {
	public static void main(String[] args) {

		int[] keyboard= {3,1};
		int[] driver= {5,2,8};
		int b=10;
		int max=0;
		int sum=0;
		
		
		for (int i = 0; i < keyboard.length; i++) {
			for (int j = 0; j < driver.length; j++) {
				sum = keyboard[i] + driver[j];
				if(sum <= b) {
					if(sum > max) {
						max=sum;
					}
				}
			}
		}
		
		
		if(max == 0) {
			System.out.println("-1");
		}else {
			System.out.println(max);
		}
		
	}
}
