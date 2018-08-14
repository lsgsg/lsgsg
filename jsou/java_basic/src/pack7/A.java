package pack7;

public class A {
	int field1;// 바깥쪽 클래스 멤버

	public A() {
		System.out.println("A 객체 생성");
	}

	void method1() {// 바깥쪽 클래스 멤버 method
		System.out.println("method1 수행" + field1);
	}

	class B {// 인스턴스 멤버 클래스
		int field2;

		public B() {
			System.out.println("B 객체 생성");
		}

		void method2() {
			System.out.println("method2 수행" + field2);
		}

	}

	static class C {
		int field3;

		public C() {
			System.out.println("C 오브젝트 생성");
		}

		void method3() {// 안쪽 클래스 멤버 method
			System.out.println("method3 처리" + field3);
		}
	}

	void method4() {// 바쪽 클래스 멤버 method
		System.out.println("method4가 처리됨");
		class D {
			int field4;

			public D() {
				System.out.println("D 인스턴스(생성자)");
			}

			void method5() {// 안쪽 클래스 멤버 method
				System.out.println("method5 처리" + field4);
			}
		}
		D d = new D();
		d.field4 = 7;
		d.method5();

	}
	//허용 범위
	B b2=new B();
	C c2=new C();
	//D d2=new D();//메소드 안에있어서 불가
	
	void test1() {
		B b3=new B();
		C c3=new C();
	}
	
	static void test2() {
		C c4=new C();
		//B b3=new B();//일반 멤버는 스테틱 접근불가
	}

	public static void main(String[] args) {
		System.out.println("바깥쪽 클래스 객체 생성------------");
		A a = new A();
		a.field1 = 1;
		a.method1();

		System.out.println("\n인스턴스 멤버 클래스 객체 생성----");
		// B b=new B();
		A.B b = a.new B();
		b.field2 = 2;
		b.method2();

		System.out.println("\n정적 멤버 클래스 객체 생성----");
		A.C c = new A.C();
		c.field3 = 3;
		c.method3();
		C c2 = new C();
		c2.field3 = 4;
		c2.method3();

		System.out.println("\n로컬 멤버 클래스 객체 생성----");
		a.method4();
		
		System.out.println();
		a.test1();
		A.test2();//스테틱
		

	}

}
