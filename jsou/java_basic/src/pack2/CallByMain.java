package pack2;

public class CallByMain {

	public static void main(String[] args) {
		// 인자 전달시 call by value, call by reference
		CallBy1 my = new CallBy1();
		CallBy2 your = new CallBy2();

		System.out.println("원래 a:" + my.a + ", b:" + my.b);

		your.ex(my.a, my.b);// 인수로 기본형 변수(값이 전달)
		System.out.println("1.수행 후 a:" + my.a + ", b:" + my.b);

		System.out.println();
		your.ex(my);// 인수로 참조형 변수(주소 전달)
		System.out.println("2.수행 후 a:" + my.a + ", b" + my.b);
		
		System.out.println();
		your.ex(my.c);//인수로 참조형 변수(주소 전달)-배열
		System.out.println("3.수행 후 c[0]:" + my.c[0] + ", c[1]:" + my.c[1]);
		

	}

}
