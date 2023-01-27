package DS;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SubArrayHackerrank {

	public static void main(String[] args) {

		List<Integer> li = Arrays.asList(1, 2, 1, 3, 2);
		int d = 3;
		int m = 2;
		int count = 0;

		for (int i = 0; i < li.size() - m; i++) {
			int sum = 0;

			for (int j = 0; j < m; j++) {
				sum += li.get(i + j);

			}
			if (sum == d) {
				count++;
			}
		}
		System.out.println(count);

	}
	


}
