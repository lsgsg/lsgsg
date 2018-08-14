package pack5;

abstract public class Animal {
	abstract public String name();

	abstract public String eat();

	abstract public String action();

	public void print() {
		System.out.println("동물 클래스의 일반 메소드 print");
	}

}
