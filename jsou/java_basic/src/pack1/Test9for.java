package pack1;

public class Test9for {

	public static void main(String[] args) {
		// 반복문 for
		int a, hap = 0;
		for (a = 1; a <= 10; a = a++) {
			System.out.print(a + " ");
			hap = hap + a;
		}
		System.out.println("\na:" + a);
		System.out.println("합은" + hap);

		System.out.println();
		for (int i = 65; i <= 90; i++) {
			System.out.println((char) i + "\t");
		}
		System.out.println();
		for (int i = 'A'; i <= 'Z'; i++) {
			// "A",'A'
			System.out.println(i + " ");
		}

		System.out.println();
		for (int j = 10; j > 1; j -= 2) {
			System.out.println(j + " ");
		}

		System.out.println();
		for (int ytn = 0, tv = 5; ytn <= 5; ytn++) {
			System.out.println(ytn + " " + tv + ",");
		}

		System.out.println();
		int aa = 1;
		for (; aa <= 5; aa++) {
			System.out.println(aa + "");
		}

		System.out.println();
		// 구구단(3)출력
		for (int count = 1; count < 10; count++) {
			if (count == 5) {
				System.out.println("5 만세");
			}
			System.out.println("3*" + count + "=" + (3 * count));
		}

		System.out.println("\n다중 for---------------");
		for (int m = 1; m <= 3; m++) {
			System.out.println("m=" + m);
			for (int n = 1; n <= 4; n++) {
				System.out.println("n=" + n + " ");
			}
			System.out.println();
		}

		for (char i = 65; i <= 90; i++) {
			System.out.println(i + " : ");
			for (char j = i; j <= 'Z'; j++) {
				System.out.println(j);
			}
			System.out.println();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		System.out.println("\n프로그램 종료");
	}

}
