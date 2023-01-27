 package java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
public static void main(String[] args) {
	
List<Integer> list1=Arrays.asList(12,13,44,54);

List list2=list1.stream().filter(p -> p < 20).collect(Collectors.toList());

list1.stream().filter(p -> p < 20).forEach(p ->System.out.println(p));

}
}
