package pack1;

public class Test10for {

	public static void main(String[] args) {
		// for 계속
		for (int i = 1; i <= 10; i++) {
			if (i == 3)
				continue;
			System.out.println(i);
			// if(i==5)break; //반복문탈출
			// if (i == 5)
			// return; // 메소드(method)탈출 main문을 빠져나감
			// if (i == 5)
			// System.exit(1); // 프로그램종료
		}
		System.out.println();
		int kk = 0;
		for (;;) {// 무한루프
			kk++;
			System.out.println("출력");
			if (kk == 5)
				break;
		}
		System.out.println();
		aa: for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 5; j++) {

				System.out.print(i + " " + j + " ");
				// if (j == 3) continue;
				// if (j == 3) continue aa;
				// if (j == 3) break;
				if (j == 3)
					break aa;
			}
			System.out.println("--------------");
		}

		System.out.println("종료");

	}

}
