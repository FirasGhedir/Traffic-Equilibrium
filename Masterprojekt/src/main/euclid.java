package main;

public class euclid {
	
	public static void main(String[] args) {
		
		System.out.println(euklid(5,15));
		
	}
	
	public static int euklid(int a,int b) {
		int c = 0;
		while(b !=0) {
			c = a;
			a = b;
			b = c%b;
		}
		
		return a;
	}

}
