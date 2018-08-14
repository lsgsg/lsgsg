package pack3;

public class Father extends GrandFa {// 상속관계
	// GrandFa fa=new GrandFa(); //포함관계
	private int nai = 55;
	public String gabo = "꽃병";// 같은 멤버를 선언하면 부모의 멤버는 은닉화(숨어버림)
	private int house = 1;
	public String data="Father data";

	public Father() {
		// super();//생략되어있음
		System.out.println("아버지 생성자");
	}

	public Father(int n) {
		super(n);
		// this(); 생성자를 한번만 부를수있음
		System.out.println("아버지 생성자");
	}

	@Override 
	public String say() {// method override(ing) 부모와 완전히 똑같은경우
		return "아버지 말씀:주말에는 총정리";
	}
	public int getHouse() {
		return house;
	}
}
