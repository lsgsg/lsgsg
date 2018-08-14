package pack1;

import java.util.Scanner;

public class Test8switch2 {

	public static void main(String[] args) {
		//
		String jik = "사원";
		String msg = "기타";
		switch (jik) {
		case "사원":
			msg = "열심히";
			break;
		case "대리":

		case "과장":
			msg = "우수해";
			break;
		default:
			System.out.println("그외 직급");
		}
		System.out.println(msg);

		// 키보드로 부터 년과 월을 각각 입력받아 해당 년,월의 날수를 출력
		// 년 365 윤년은 4의배수

		String str = "평년";
		int year, month, nalsu = 28;
		Scanner input = new Scanner(System.in);
		System.out.print("년도 : ");
		year = input.nextInt();
		System.out.print("월 : ");
		month = input.nextInt();
		input.close();
		if (month < 1 || month > 12) {
			System.out.println("월은 1 ~ 12 사이만 허용!");
			System.exit(0);
		}

		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			// int imsi = 7;
			nalsu = 29;
			str = "윤년";
		}
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			nalsu = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			nalsu = 31;
			break;
		case 2:
			break;
		}

		System.out.println(year + "년 " + month + "월은 " + nalsu + "일 " + str);

	}

}
