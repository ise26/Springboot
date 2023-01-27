
public class OuterClass {
///nested inner class
	/*
	 * static class Inner{ int a=10; }
	 * 
	 * public static void main(String[] args) { OuterClass.Inner oi= new
	 * OuterClass.Inner(); System.out.println(oi.a); }
	 */
////
	
	
	//local inner class
	
	void display() {
		
		class inner{
			void msg() {
				System.out.println("asdfgyuiol");
			}
		}
		inner in = new inner();
		in.msg();
	}
	
	public static void main(String[] args) {
		
		OuterClass oc= new OuterClass();
		oc.display();
		
	}
	
}
