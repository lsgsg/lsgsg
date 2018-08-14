package pack2;

public class Programmer {
	public String nickName; // 초기값은 null
	// private int age; // 초기값은 0
	private int age = 0;
	String spec = "자바 개발자";

	public static String motto = "미치자";
	//public final double PI = 3.14;//호출해서 수정할 수 없다.(final 멤버는 대문자로 표기)
	public static final double PI = 3.14;

	public Programmer() {
		System.out.println("난 생성자, 초기화 담당");
		System.out.println("초기화 없으면 소스코딩 생략");
		age = 20;
	}

	public void displayData() {
		String sp = reSpeck();
		System.out.println("별명이 " + nickName + "은 " + age + "살, " + sp);
	}

	private String reSpeck() {
		return "보유 기술은 " + spec;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

}
