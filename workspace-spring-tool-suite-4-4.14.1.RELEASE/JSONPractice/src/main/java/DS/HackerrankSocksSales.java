package DS;

import java.util.Arrays;
import java.util.List;

public class HackerrankSocksSales {
public static void main(String[] args) {
	int count=0;
    List<Integer> ar= Arrays.asList(10,20,20,10,10,30,50,10,20);
	for (int i = 0; i < ar.size(); i++) {
		if(ar.get(i)!=0) {
        for (int j = i+1; j < ar.size(); j++) {
            if(ar.get(i)==ar.get(j)){
                count++;
                ar.set(j, 0);
                break;
            }
        }}
    }System.out.println(count);
    }
}

