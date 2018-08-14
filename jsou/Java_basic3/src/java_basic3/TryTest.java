package java_basic3;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class TryTest {
	
	public void ex( ) {
		try {
			int a[] = {1,2,3};
			System.out.println("배열 요소 : " + a[5]);
		} catch (Exception e) {
			System.out.println("허걱 에러 : " + e);
		}
	}
	
	public void ex2() {
		try {
			FileReader fr2 = new FileReader("C:\\work\\aa.txt"); // 외부 장치와 연결된 것은 오류를 바로바로 잡아 주어야 함. 안그러면 실행 조차 되지 않음.
		} catch (Exception e) {
			System.out.println("허걱 에러 : " + e);
		}
	}
	
	public static void main(String[] args) throws Exception{
		// 예외처리 방법
		// 1. JVM 에게 Throw
		// 2. try ~ catch
		
		try {
		//FileReader fr = new FileReader("C:/work/aa.txt"); 리눅스, 맥에서만 돌아감.
		FileReader fr = new FileReader("C:\\work\\aa.txt"); // FileReader : 파일 불러오기
		
		int k = 10;
		int re = k / 0;
		System.out.println("re: " + re);
		
		TryTest test = new TryTest();
		test.ex();
		test.ex2();
		
		} catch (FileNotFoundException e) { // 파일 오류 
			System.out.println("파일이 없거나 경로 오류");
		} 
		// 하나씩 에러를 잡을 때.
		/* 
		} catch (ArithmeticException e2) { // 연산 오류
			System.out.println("연산오류 : " + e2.getMessage());
		} catch (NullPointerException e3) { // 널 오류
			e3.printStackTrace();
			// 스택에 메서드가 호출된 기록을 남김. 이를 StackTrace라고 하고 에러가 발생한 메소드의 호출 기록을 출력 해줌.
		} catch (ArrayIndexOutOfBoundsException e4) { // 배열 오류
			System.out.println("배열 에러 : " + e4); 
		}
		*/
		
		// 모든 에러 잡을 때.
		catch (Exception e) { // Exception은 모든 에러를 잡아 줌.
			System.out.println("에러 : " + e.getMessage());
		} finally {
			System.out.println("에러와 상관없이 반드시 수행");
		}
		
		System.out.println("프로그램 정상 종료");
	}
}
