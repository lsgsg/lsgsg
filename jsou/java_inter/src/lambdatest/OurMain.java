package lambdatest;

public class OurMain {

	public static void main(String[] args) {
		//1. 인자가 없는 추상 메소드
			OurInter inter = new OurInter() {
				
				@Override
				public void abc() {
					System.out.println("일반적 익명 클래스의 메소드1 ");
				}
			};
			
		 inter.abc();
		 
		//람다식으로 표현 
		 OurInter inter2 = ()->System.out.println("일반적 익명 클래스의 메소드2 ");
		 inter2.abc(); // () == abc() ->{} : 실행문 // 명령문이 복수개 일때는 {}로 감싸고 한개일땐 {} 생략
		System.out.println("----------------------------");
		OurInter inter3 = () ->{
			System.out.println("람다식으로 표현 ");
			System.out.println("복수 명령문은 {} 으로 감쌈  ");
		};
		inter3.abc();
		//2. 인자가 있고 반환은 없는(void)  추상메소드
		System.out.println("===============================");
		OurInterArg interArg = new OurInterArg() {
			
			@Override
			public void def(int a, int b) {
				System.out.println("두 수의 합은" + (a+b));
			}
		};
		interArg.def(5, 6);
		
		//   람다식표현
		//  OurInterArg interArg2 = a->{} : 인수가 한개일때는 () 생략가능;
			OurInterArg interArg2 = (a,b)->{
				System.out.println("람다 사용 : " + (a+b));
			};
			interArg2.def(6, 5);
		//3. 인자O 리턴 int 추상메소드
			System.out.println("===============================");
		OurInterArgOther other = new OurInterArgOther() {
			
			@Override
			public int def(int a, int b) {
				
				return a * b;
			}
		};

		int re = other.def(5, 9);
		System.out.println("re : " + re);
		
		//람다식으로 표현;
		
		OurInterArgOther other2 = (a,b)->{return a*b;};//리턴은 생략해도 ok (메소드의 행위가 리턴밖에 없을때 가능)
		int re2 = other2.def(5, 5);
		System.out.println("re2 : " + re2);
	}
}