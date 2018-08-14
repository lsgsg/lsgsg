package pack2;

public class Animal {
	private int leg = 4;
	private int age;
	private String name;
	private static int mouse=1;

	public Animal() {
		// 생성자는 내용이 없을 시 생략 가능(컴파일러가 생성)
	}
	
	public Animal(int leg) {//constructor overload(ing)생성자 오버로딩
		this.leg=leg;
	}
	public Animal(String irum) {//constructor overload(ing)생성자 오버로딩
		this.name=irum;
	}
	public Animal(String irum, int nai) {//constructor overload(ing)생성자 오버로딩
		this.name=irum;
		age=nai;
	}


	public void display() {
		System.out.println("leg:" + leg + ", age:" + age + ", name:" + name);
	}
	
	public void display(int age) {// method overload(ing)
		this.age = age;
		System.out.println("leg:" + leg + ", age:" + age + ", name:" + name);

	}


	public void display(String name) {// method overload(ing)
		this.name = name;
		System.out.println("leg:" + leg + ", age:" + age + ", name:" + name);

	}
	
	public void display(int age, String str) {// method overload(ing)
		this.age = age;
		name=str;
		System.out.println("leg:" + leg + ", age:" + age + ", name:" + name);

	}
	
	public void display(String str, int age) {// method overload(ing)
		this.age = age;
		name=str;
		System.out.println("leg:" + leg + ", age:" + age + ", name:" + name);

	}
	
	
	
//	public int//여기이후 display(int bb) {// 메소드랑만 상관있음
//		this.age = age;
//		System.out.println("leg:" + leg + ", age:" + age + ", name:" + name);
//		return 5;
//	}
	
//	public void display(String str, int age) {// String 형, int 형 이미존재
//		this.age = age;
//		name=str;
//		System.out.println("leg:" + leg + ", age:" + age + ", name:" + name);
//
//	}

	
	public static void myStaticMethod(){
		System.out.println("난 스테틱 메소드");
		//System.out.println("다리 수 :"+leg);//static 멤버만 참조가능하쥬?
		System.out.println("마우스는"+mouse);
	}
	
	public void normalMethod(){
		System.out.println("난 일반 메소드");
		System.out.println("다리 수 :"+leg);
		System.out.println("마우스는"+mouse);
	}

	
	
}
