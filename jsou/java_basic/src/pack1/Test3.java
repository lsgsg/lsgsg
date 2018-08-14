package pack1;

public class Test3 {

	public static void main(String[] args) {
		// 관계, 논리, 기타 연산자
		int a = 5;
		System.out.println(a > 3);
		System.out.println(a <= 3);
		System.out.println(a == 3);
		System.out.println(a != 3);
		
		System.out.println();
		int b = 10;
		System.out.println(b > 3 && b<=10 ); //and
		System.out.println(b >=3 && b == 10);
		
		System.out.println(b > 6 || b < 10); //or
		System.out.println(b <= 6 || b == 3 + 7); //우선순위 () > 산술 > 관계 > 논리 > =
		
		
		System.out.println();
		int ii = 8, ij=0;
		System.out.println(ii + " " + ij);
		System.out.println("ii:" + ii + " " + 
				Integer.toBinaryString(ii));
		
		ij = ii << 1;
		System.out.println("ij:" + ij + " " + 
				Integer.toBinaryString(ij));
		
		ij = ii >> 2;
		System.out.println("ij:" + ij + " " + 
				Integer.toBinaryString(ij));
		

		ij = ii >>> 2;
		System.out.println("ij:" + ij + " " + 
				Integer.toBinaryString(ij));
		
		System.out.println();
		//삼항 연산자
		int result = ((ii <= 5)?100:100 + 20); //(조건)?참값:거짓값
		System.out.println("result:" + result);
		
		
		abc(); //abc() 호출
		System.out.println("이런저런 일을 하다가");
		abc();
		System.out.println("뭔가를 하다가");
		def(10); //def() 호출
		System.out.println("~~");
		def((int)12.3);
		System.out.println("프로그램 종료");
		
	}
	public static void abc() {
		System.out.println("unit 수행");
		}
	public static void def(int arg) { //(인자, parameter, argumnet)
		System.out.println("def unit 수행");
		System.out.println("arg" + arg);
		}

}
