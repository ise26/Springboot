package DS;

import java.util.Arrays;
import java.util.List;

public class HurdleRaceHackerrank {
	public static void main(String[] args) {

		List<Integer> li =Arrays.asList(2,5,4,5,2);
		
		int ans;
		int k=7;
		
		int max=li.stream().max(Integer::compare).get();
		if(max>k)
		{
			ans=max-k;
		}else {
			ans=0;
		}
		
		System.out.println(ans);
		
	}
}
