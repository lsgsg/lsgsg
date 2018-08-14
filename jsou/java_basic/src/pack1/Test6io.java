package pack1;



import java.util.Scanner;

public class Test6io {

	public static void main(String[] args) throws Exception {
		// 프로그램 진행 중 외부에서 값 얻기
		if (args.length == 0) {
			System.out.println("외부에서 값 받기 실패");
			System.exit(1); // 응용프로그램 강제 종료
		}

		System.out.println(args[0]);
		//
		// System.out.println("\n키보드로 값 얻기");
		// BufferedReader buf =
		// new BufferedReader(new InputStreamReader(System.in));
		// System.out.print("이름입력:");
		// String irum = buf.readLine();
		//
		// System.out.print("나이입력:");
		// String nai = buf.readLine();
		// System.out.println("이름은" + irum + ", 나이는" + nai);
		//
		// buf.close(); //자원 해제

		System.out.println("\n키보드로 값 얻기2");
		Scanner sc = new Scanner(System.in);
		System.out.print("상품명:");
		String product = sc.next();
		System.out.print("가격:");
		int price = sc.nextInt();
		System.out.println("상품명은" + product + ", 가격은" + price);
		sc.close();

		System.out.println("프로그램 정상 종료");
	}

}
