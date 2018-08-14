package pack1;


import java.util.Scanner;

public class Test8switch {

	public static void main(String[] args) {
		// 조건 판단문
		int nai = 250;
		nai = nai / 10 * 10;
		//System.out.println(nai);
		
//		if(nai == 20) {
//			System.out.println("팔팔해");
//		}else if(nai == 30) {
//			System.out.println("덜 팔팔해");
//		}
		
		switch(nai) {
		case 20:
			System.out.println("팔팔해");
			System.out.println("20대");
			break;
		case 30:
			System.out.println("덜 팔팔해");
			System.out.println("30대");
			break;
		case 40:
			System.out.println("비실비실");
			System.out.println("40대");
			break;
		default:	
			System.out.println("기타");
		}
					
		System.out.println("다음");
		
		//double time = Math.random(); //난수 발생
		//int time = (int)(Math.random() * 10);
		int time = (int)(Math.random() * 4) + 8;
		System.out.println(time);
		
		switch(time) {
		case 8:
			System.out.println("출근하자");
			break;
		case 9:
			System.out.println("회의하자");
			break;
		case 10:
			System.out.println("프로그램 짜자");
			break;
		default:
			System.out.println("명상시간");
		}
		
		//문제) 두 개의 실수를 각각 입력 받아 사칙연산 수행
		
		Scanner sc = new Scanner(System.in);
		System.out.print("실수1번 : ");
		int num1 = sc.nextInt();
		System.out.print("실수2번 : ");
		int num2 = sc.nextInt();
		System.out.print("a,s,m,d 중 하나를 입력 :");
		String asmd = sc.next();
		sc.close();
		
		switch(asmd) {
		
		case "a":
			System.out.println(num1 + num2);
			break;
		case "s":
			System.out.println(num1 - num2);
			break;
		case "m":
			System.out.println(num1 * num2);
			break;
		case "d":
			System.out.println(num1 / num2);
		default:
			System.out.println("a,s,m,d 중에 없음");	
		}

	}

}























