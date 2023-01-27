package DS;

public class Array {

	public static void main(String[] args) {
		int n = 4;
		int a[] = { 10, 20, 40, 30 };

		int add = 60;
		int position = 2;
		// insertion
//		int b[]=new int[n+1];
//		
//		for (int i=0;i<b.length;i++) {
//			if(i<position) {
//				b[i]=a[i];
//			}else if(i==position) {
//				b[i]=add;
//			}else {
//				b[i]=a[i-1];
//			}
//		}
//		for (int i=0;i<b.length;i++) {
//			System.out.println(b[i]);
//		}

		//delete
		int b[] = new int[n - 1];

		for (int i = 0; i < a.length; i++) {
			if (i < position) {
				b[i] = a[i];
			} else if (i == position) {
				continue;
			} else {
				b[i-1] = a[i];
			}
		}
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}
	}
}
