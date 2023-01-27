package DS;

import java.util.Scanner;

public class SinglyLinkedList {

	static class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	Node head = null;

	void creation(){
		int data,n;
		Scanner sc= new Scanner(System.in);
		do{
			System.out.println("Enter data");
			data=sc.nextInt();
			Node new_node=new Node(data);
			
			if(head==null) {
				head=new_node;
			}else {
				new_node.next=head;
				head=new_node;
			}
			
			System.out.println("want to add new node press 1:");
			n=sc.nextInt();
		}while(n==1);
	}

	void traversal() {
		Node temp=head;
		if(temp==null) {
			System.out.println("no linked list");
		}else {
			while(temp!=null){
				System.out.println(temp.data);
				temp=temp.next;
			}
		}
			
		}
	public static void main(String[] args) {
		SinglyLinkedList li= new SinglyLinkedList();
		li.creation();
		li.traversal();
	}
}
