package pack1;

import java.util.Scanner;

public class Test7if {

	public static void main(String[] args) {
		// 조건판단문 if
		int num = 5;

		if (num >= 3) {
			System.out.println("크다");
			System.out.println("배고파 - 조건이 참일 때 수행");
		}

		System.out.println("다음 작업 계속 1");

		num = 10;
		if (num < 3) {
			System.out.println("작네요");
			System.out.println("참일 때 수행");
		}

		else {
			System.out.println("작지 않아요");
		}

		System.out.println("다음 작업 계속 2");

		int jumsu = 40;

		if (jumsu >= 70) {
			if (jumsu >= 90) {
				System.out.println("우수");
			} else {
				System.out.println("보통(70 ~ 89)");
			}

		} else {
			if (jumsu >= 50) {
				System.out.println("저조");
			} else {
				System.out.println("엄청 저조");
			}
		}

		System.out.println();
		jumsu = 75;
		String re = "";
		if (jumsu >= 90) {
			re = "수";
		} else if (jumsu >= 80) {
			re = "우";
		} else if (jumsu >= 70) {
			re = "미";
		} else if (jumsu >= 60) {
			re = "양";
		} else {
			re = "가";
		}
		System.out.println("평가 결과:" + re);
		// 문제) 키보드로 부터 상품명, 수량, 단가를 각각 입력받아 금액(수량*단가)출력
		// 조건 : 금액이 5만원 이상이면 금액에 10%, 아니면 금액의 5%를 세금으로 출력
		// 출력 출력모양은 상품명:*** 금액:*** 세금:***

		Scanner sc = new Scanner(System.in);
		System.out.print("상품명 : ");
		String product = sc.next();
		System.out.print("단가 : ");
		int price1 = sc.nextInt();
		System.out.print("수량 : ");
		int each1 = sc.nextInt();

		int price2 = price1 * each1;
		int perc = 0;
		if (price2 >= 50000) {
			perc = (int) (0.1 * price2);
			System.out.println("상품명은 : " + product + ", 금액은 : " + price2 + ", 세금은 : " + perc);

		} else {
			perc = (int) (0.05 * price2);
			System.out.println("상품명은 : " + product + ", 금액은 : " + price2 + ", 세금은 : " + perc);
		}

		sc.close();

		System.out.println("종료");

	}

}
