package pack1;

import java.util.Scanner;

public class Test9exam {

	public static void main(String[] args) {
		// 문1 : 키보드로부터 숫자를 (2~9)구구단 출력

		Scanner sc = new Scanner(System.in);
		System.out.print("몇단? : ");
		int what = sc.nextInt();
		for (int count = 1; count < 10; count++) {
			System.out.println(what + "*" + count + "=" + (what * count));
		}
		sc.close();
		// 문2 : 1 ~100 사이의 정수 중 3의 배수이면서 5의 배수의 갯수와 그 수들의 합을 출력

		int q;
		int cou = 0;
		int sum = 0;
		for (q = 1; q <= 100; q++) {
			if (q % 3 == 0 && q % 5 == 0) {
				System.out.print(q);
				cou += 1;
				sum += q;
			}
		}
		System.out.println("3의 배수이고 5의 배수인 갯수는" + cou + "개");
		System.out.println("합은 : " + sum);
		// 문3 : 2~9 단을 모두 출력
		for (int all = 2; all < 10; all++) {
			int pri;
			for (pri = 1; pri < 10; pri++) {
				System.out.println(all + "*" + pri + "=" + (all * pri));
			}

		}
		// 문4 :
		for (int i = 6; i > 1; i--) {
			for (int j = 0; j <= 5 - i; j++) {
				System.out.print(" ");
			}
			for (int j = 1; j <= (i * 2) - 3; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

		// 문5 :
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5 - i; j++) {
				System.out.print(" ");
			}
			for (int j = 1; j < i * 2; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

	}

}
