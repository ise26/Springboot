package Collection;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author Shubham.Ise
 *
 */
public class DequePractice {
	static int a = 10;



	public static void main(String[] args) {

		Deque<Integer> dq = new ArrayDeque<>();

		dq.add(12);
		dq.add(44);
		dq.addFirst(1);
		dq.addLast(5);
		dq.offer(9);
		System.out.println("Deque : " + dq);
		System.out.println(dq.pop());
		System.out.println("Deque : " + dq);
		

	}
}
