package pack3;

public class StudentMain {

	public static void main(String[] args) {
		StuTom tom=new StuTom();
		System.out.println(tom.name);
		System.out.println(tom.grade);
		System.out.println(tom.gender);
		tom.study();
		
		System.out.println("----------------------");
		StuPage page=new StuPage();
		System.out.println(page.name);
		System.out.println(page.grade);
		System.out.println(page.gender);
		page.study();
		page.mySpec();
		page.print();
		

	}

}
