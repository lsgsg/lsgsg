package pack2;

public class MyMain {

	public static void main(String[] args) {
		// 응용 프로그램의 시작 지점
		int imsi=10;
		System.out.println("imsi:"+imsi);
		System.out.println(Math.PI);
		System.out.println(Math.random());
		
		//내가 만든 클래스로 인스턴스(instance, Object, 객체, 개체)
		Car car1=new Car(); //car1:참조형 변수
		System.out.println(car1.wheel);
		
		//car1=null;;
		//System.out.println(car.wheel);//NullPointerException
		car1.abc();
		//car1.def(); //private method이므로 접근 불가
		//car1.speed();//private member이므로 접근 불가
		System.out.println("속도는 "+car1.getSpeed());
		//car1.setSpeed(80);
		car1.setSpeed(1111, 80);//비밀번호 불일치!!!
		car1.setSpeed(1234, 80);//비밀번호 일치
		System.out.println("속도는 "+car1.getSpeed());

	}

}
