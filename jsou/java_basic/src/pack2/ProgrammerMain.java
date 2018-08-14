package pack2;

public class ProgrammerMain {

	public static void main(String[] args) {
		Programmer tom = new Programmer(); // 생성자 호출
		// tom.Programmer(); //생성자 호출 불가
		System.out.println("tom 별명은 " + tom.nickName);
		tom.nickName = "자바 귀신";
		System.out.println("tom 별명은 " + tom.nickName);

		// System.out.println("tom 나이는 "+tom.age); age가 지역변수라 호출불가

		tom.setAge(24);
		System.out.println();
		System.out.println("tom 나이는 " + tom.getAge());
		System.out.println("tom 기술은 " + tom.spec);

		tom.displayData();
		tom.spec += ",파이썬 전문가";
		tom.displayData();

		System.out.println();

		//System.out.println(tom.motto);
		System.out.println(Programmer.motto);// new필요없이 클래스명.X로 호출
		//tom.PI = 12.3;
		//System.out.println("파이는 "+tom.PI);//참조만 가능, static final 에서는 비권장 참조방법
		System.out.println("파이는 "+Programmer.PI);//권장 참조방법
		
		System.out.println(Math.PI);

		System.out.println("-------------------------");
		Programmer oscar = new Programmer();
		oscar.displayData();

	}

}
