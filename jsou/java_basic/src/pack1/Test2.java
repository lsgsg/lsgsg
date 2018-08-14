package pack1;

public class Test2 {
	 public static void main(String[] args) { // 응용프로그램에 시작은 main 임. main + Crtl + Space bar 누르면 바로 만들어줌.
		// 산술연산자
		 int a = 5; // 치환 연산자. 오른쪽에 있는 값을 왼쪽에 저장 시킴.
		 int b = 3;
		 int c = a + b;
		 System.out.println("c:" + c);
		 
		 System.out.println(a + b);
		 System.out.println(a - b);
		 System.out.println(a * b);
		 System.out.println(a / b); // 몫
		 System.out.println(a % b); // 나머지
		 System.out.println(a / (double)b); // 실수형이기 때문에 몫과 나머지.
		 
		 //System.out.println(a / 0); // 0으로 나눌 수 없음.
		 System.out.println(a / 0.0); // Infinity(무한대)
		 System.out.println(a % 0.0); // NaN(없음을 의미?)
		 
		 System.out.println(3 + 4 * 5); // 산술 연산자 중 * /를 먼저함.
		 System.out.println((3 + 4) * 5); // 가로 연산 먼저 계산 후 곱셈.
		 
		 String ss1 = "자바";
		 String ss2 = "만세";
		 String ss3 = ss1 + ss2; // 문자열 더하기. 더하기는 숫자와 문자열 더하기 둘 다 해줌.
		 System.out.println(ss3);
		 System.out.println(ss3 + " " + 2018); // 숫자는 문자열화가 됨.
		 System.out.println(ss3 + " " + 2018 + 6);
		 System.out.println(ss3 + " " + (2018 + 6)); // 가로 먼저 문자열화 후 출력
		 
		 String ss4 = "5" + 6; // 문자열 더하기
		 System.out.println(ss4);
		 
		 int ia = Integer.parseInt("5") + 6; // Integer.parseInt() 문자열을 아라비아 숫자화 해줌.
		 System.out.println("ia:" + ia);
		 
		 String ss5 = Integer.toString(5) + 6; // Integer.toString() 숫자가 문자열로 바뀜. 위에와 반대 개념
		 System.out.println("ss5:" + ss5);
		 
		 //누적
		 System.out.println();
		 int no = 1;
		 //System.out.println("no는 " + Integer.toString(no));
		 no = no + 1;
		 no += 1; // 위에 것과 밑에 것은 같음. 누적 연산자
		 no++; // 후위 증가 연산자(증감연산자) *,/ 는 없음
		 ++no; // 전위 증가 연산자
		 System.out.println("no는 " + no);
		 
		 //증감연산자에 대해
		 System.out.println();
		 int imsi = 5;
		 int result = ++imsi + 1; // 전위는 연산을 참여함
		 System.out.println(imsi);
		 System.out.println(result);
		 
		 int imsi2 = 5;
		 int result2 = imsi2++ + 1; // 후위는 연산을 먼저 참여 후 증가 됨.
		 System.out.println(imsi2);
		 System.out.println(result2);
		 //증감 연산자는 홀로 쓸 것. 다른 연산자와 함께 쓰지 말 것.
		 
		 //부호에 관해
		 System.out.println();
		 int imsi3 = 3;
		 System.out.println(imsi3);
		 System.out.println(imsi3 * -1);
		 System.out.println(-imsi3);
		 
		 System.out.println("프로그램 종료");
	}
		
		
}
