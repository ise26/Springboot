package DS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class RecordBreakHackerRank {

	public static void main(String[] args) {

		List<Integer> li = Arrays.asList(10, 5, 20, 20, 4, 5, 2, 25, 1);

		int highScore = li.get(0);
		int lowScore = li.get(0);
		int hiCount = 0;
		int lowCount = 0;

		for (int i = 0; i < li.size(); i++) {
			if(li.get(i)>highScore) {
				highScore=li.get(i);
				hiCount++;
			}else if(li.get(i)<lowScore) {
				lowScore=li.get(i);
				lowCount++;
			}
		}
		System.out.println("low: "+lowCount);
		System.out.println("high: "+hiCount);
	}
}
