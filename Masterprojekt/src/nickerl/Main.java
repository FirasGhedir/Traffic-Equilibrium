package nickerl;

import java.util.Scanner;

public class Main {

	static Scanner scan;

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		int x = scan.nextInt();
		while (x != 1) {
			if (x % 2 == 0) {
				x = x / 2;

			} else {
				x = 1 + x * 3;

			}
			System.out.print(x + " ==> ");
		}
	}
}
