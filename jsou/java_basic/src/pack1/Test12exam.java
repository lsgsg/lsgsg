package pack1;

public class Test12exam {

	public static void main(String[] args) {
		// while 연습문제
		// 문1)1~100사이의 정수 중 3의 배수이지만 2의 배수가 아닌 수를 출력하고, 그 합과 갯수 출력

		int w = 1;
		int count = 0;
		int emp = 0;
		while (true) {

			if (w % 3 == 0 && w % 2 != 0) {
				System.out.print(w + ", ");
				count = count + 1;
				emp = emp + w;
			}

			w = w + 1;
			if (w > 100)
				break;
		}
		System.out.println();
		System.out.println("총합은 " + emp + "갯수는 " + count);

		// 문2)-1,3,-5,7,-9,11 ~ 99까지의 합 출력
		int a = 1;
		int sum = 0;
		while (a <= 100) {
			if (a % 4 == 1) {
				a = (-1) * a;
			}
			sum = a + sum;
			if (a < 0) {
				a = (-1) * a;
			}

			a = a + 2;

		}
		System.out.println();
		System.out.println("합은 " + sum);

		// 문3)1~1000 사이의 소수와 그 갯수를 출력
		int sosu = 2;
		int how = 0;
		while (sosu <= 1000) {
			boolean imsi = false;
			int bb = 2;
			while (bb <= sosu - 1) {
				if (sosu % bb == 0) {
					imsi = true;
				}
				bb = bb + 1;
			}
			if (imsi == false) {
				System.out.print(sosu + " ");
				how = how + 1;
			}

			sosu = sosu + 1;
		}
		System.out.println();
		System.out.println("갯수는 " + how);
	}
}
