package pack1;

public class Test1 {

	public static void main(String[] args) {
		// 한 줄 주석 : 실행에 참여 하지 않음.
		System.out.print("환영합니다");
		System.out.println("안녕");
		System.out.println("반가워");
		/*
		 * 여러 줄 주석 ctrl + Enter 
		 */
		// 단순 변수 : 기억장소의 이름.
		// 기본형 기억장소 : 상수 자체를 기억장소가 기억.
		
		byte var1;
		var1 = 10;
		var1 = 3;
		System.out.println(var1);
		System.out.println(Byte.MAX_VALUE);
		System.out.println(Byte.MIN_VALUE + "\n");
		// System.out.println(); = + "\n" 둘 다 같은 의미(엔터효과)
		var1 = 127;
		System.out.println("var1:" + var1);
		
		System.out.println();
		short var2 = 32767; // 2byte : -32768 ~ 32767
		System.out.println("var2:" + var2);
		
		System.out.println();
		int var3 = 2147483647; // 4byte : -2147483648 ~ 2147483647
		System.out.println("var3:" + var3);
		
		System.out.println();
		//long var4 = 2147483648L; // Java에서는 정수형까지 밖에 안보여줌. 출력할려면 뒤에 L(자동형변환)을 붙여주거나 강제형변환(long)을 써주면 됨. 
		long var4 = 10L;
		System.out.println("var4:" + var4);
		
		System.out.println("-- 형변환");
		//Promotion : 자동 형변환, Casting : 강제 형변환
		//byte b1 = (byte)128; // 자동 형변환
		byte b1 = (byte)128; // 강제 형변환
		System.out.println(b1);
		byte b2 = 10;
		// byte b3 = (b2 + 1); // 오류가 뜨는 이유는 변수 이기때문에 byte 자체가 127까지 받지만 변수가 어떻게 변할지 모르기 때문에 +1에 대한 명령어를 미리 막아버리는 것.
		byte b3 = (byte)(b2 + 1); // 그래서 강제 형변환 시켜 주는 것.(출력 시키기 위해서)
		System.out.println(b3);
		
		System.out.println();
		//short s1 = (short)10;
		int i1 = 10; 
		System.out.println(i1);
		short s2 = (short)i1; // int값을 나타내기 위해 short로 강제 형변환 시킴.
		System.out.println(s2);
		
		System.out.println("\n-- 실수");
		double d1 = 1.2;// 8byte 실수형 기억장소. 실수는 double 기본임. 
		d1 = 3; // promotion(자동 형변환)되고 있음. int가 double형으로 바뀜.
		System.out.println("d1:" + d1);
		d1 = 56.7;
		
		//float f1 = 3.5F; // 4byte 실수형 기억장소. F를 써서 강제 형변환
		float f1 = (float)3.5; // (float)을 써서 강제 형변환
		System.out.println("f1:" + f1);
		
		int i2 = (int)3.5; // 실수형  8byte;
		System.out.println("i2:" + i2);
		
		System.out.println();
		//double result = 4.5 + 10; // 연산시 큰 타입으로 자동 형변환 됨.
		//double result = 4.5 + (double)10; // 과잉친절. 자동 형변환 시켜주기 때문에 굳이 쓸 필요 없음.
		double result = (int)4.5 + 10; // 4.5를 정수 형변환 시켜서 4+10으로 하고 출력 할 때 double형으로 변환시켜 출력.
		System.out.println("result:" + result);
		
		double dd = 5.5;
		int result2 = (int)4.5 + (int)dd; // 실수형을 정수형으로 강제 형변환하여 연산해주고 출력 해줌.
		System.out.println("result2:" + result2);
		
		System.out.println();
		boolean bu1 = true; // boolean 연산자는 true, false 둘 중 하나만 출력 가능
		bu1 = false;
		System.out.println("bu1:" + bu1);
		
		System.out.println();
		char c1 = 'a';// 문자형 변수. 문자(하나)는 작은 따옴표 씀.
		//c1 = 'abc' 에러 남. 문자열이 되서 표시해주지못함 char는 한 바이트만 표시해줌.
		c1 = 'a'; // String을 char에 넣을 수 없음. String은 문자열. "" 큰 따옴표는 문자열.
		System.out.println("c1:" + c1 + " " + (int)c1 + " " + (char)97 + " " + (int)'A' + " " + (int)'0');
		// ASCII 코드 : LF(10), CR(10) LF,CR은 엔터 기능, 0(48), A(65), a(97) <- 이것들은 외울 것(기본중에 기본!)
		
		System.out.println("\n문자열 : String 객체 - 참조형");
		String ss = "abc"; // String은 참조형이여서 new를 써야 하지만 편의를 봐주기 위해 String만 써도 됨.
		System.out.println("ss:" + ss );
		
		System.out.println();
		String msg = "자바는 문자열을 기본형처럼 표시 가능";
		System.out.println("msg:" + msg );
		// byte, short, int, long, float, double, boolean, char - 기본형, 나머지는 참조형
		
		
	}

}