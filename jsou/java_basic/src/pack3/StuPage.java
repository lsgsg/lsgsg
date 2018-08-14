package pack3;

public class StuPage extends Student{
	String name = "페이지";
	int grade = 2;
	String gender = "f";
	
	public StuPage() {
		System.out.println("StuPage 생성자");
	}
	@Override
	protected void study() {
			System.out.println("학생은 자바+python 잘 해야한다");
	}
	
	public void mySpec() {
		System.out.println("보유기술:프로그래밍 전문");
	}
	
	public void print() {
		System.out.println("--------------------------");
		System.out.println(name);
		System.out.println(this.name);
		System.out.println(super.name);
		System.out.println();
		study();
		this.study();
		super.study();
		System.out.println("--------------------------");
	}

}
