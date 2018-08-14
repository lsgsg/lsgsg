package pack1;

import java.util.Scanner;

public class Test11while {

	public static void main(String[] args) {
		// 반복문 while
		int w = 1; // for(변수=초기값; 조건; 증감식){}
		while (w <= 5) {// 조건이 참인 동안 반복
			System.out.println("w:" + w);
			w += 1;// 반복문 탈출을 위한 명령문 필수
		}
		System.out.println("\n반복문 탈출 후 w:" + w);

		System.out.println();
		w = 0;
		while (true) {// 무한루프
			w++;
			if (w == 3)
				continue;
			System.out.println("w는" + w);
			if (w == 5)
				break;
		}
		System.out.println();

		w = 10;
		do {// 최소 1회는 반드시 수행
			System.out.println("do while의 w:" + w);
			w = w + 1;
		} while (w <= 5);

		System.out.println("반복 처리의 예");
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("숫자 입력:");
			int num = sc.nextInt();
			int n = 1, total = 0;
			while (n <= num) {
				total = total + n;
				n = n + 1;
			}
			System.out.println("total : " + total);
			System.out.println("계속할까요?(0/1)");
			int c = sc.nextInt();
			if (c != 1) {
				System.out.println("반복작업 종료");
				break;
			}
		}
		sc.close();
		System.out.println("종료");
	}

}
