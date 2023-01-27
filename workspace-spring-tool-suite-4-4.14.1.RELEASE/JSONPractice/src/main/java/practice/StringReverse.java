package practice;

public class StringReverse {
public static void main(String[] args) {
	String s="hii this is Reverse Progrm";
	String reverseString ="";
	int count=0;
//	for (int i = (s.length()-1); i >=0; i--) {
//		reverseString=reverseString + s.charAt(i);
//	}
	
	for (int i = 0; i < s.length(); i++) {
		if(Character.isUpperCase(s.charAt(i))) {
			
			count++;
		}
	}
	System.out.println(s.substring(0, s.length()-1));
}
}
