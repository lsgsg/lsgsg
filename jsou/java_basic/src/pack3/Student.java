package pack3;

public class Student {
	protected String name = "학생";
	protected int grade = 1;
	protected String gender = "m";

	public Student() {
		System.out.println("Student 생성자");
	}

	protected void study() {
		System.out.println("학생은 자바를 해야한다");
	}

}
