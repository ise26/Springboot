package java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Product{
	
	public Product(int id, String name, int salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	int id; String name; int salary;
}
public class StreamFilterMap {
public static void main(String[] args) {
	
	
	List<Product> product = new ArrayList<>();
	product.add(new Product(1, "mahesh", 59000));
	product.add(new Product(2, "sakib", 9000));
	product.add(new Product(3, "fahad", 8000));
	product.add(new Product(4, "nikhil", 55000));
	product.add(new Product(5, "aman", 33000));
	product.add(new Product(6, "nitesh", 49000));
	
//	List productPriceList= product.stream()
//		.filter(p -> p.salary>40000)
//			.map(p ->p.salary)
//			.collect(Collectors.toList());
//	System.out.println(productPriceList);
	
	product.stream().filter(p -> p.salary>=8000).collect(Collectors.toList()).forEach(p -> System.out.println(p.salary));
}
}
