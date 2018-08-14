package pack2;

public class OurProject {

	public static void main(String[] args) {
		System.out.println("뭔가하다가");
		
		Animal animal = new Animal("사자", 5);
		animal.display();
		
		System.out.println();
		
		MyCalc calc=new MyCalc();
		System.out.println(calc.goDiv(3.4, 10));
		System.out.println(calc.goMinus(5, 2.5));
		System.out.println(calc.goTri(5, 7));
		System.out.println((MyCalc.msg));
		
		System.out.println();
		System.out.println(Math.PI);
		System.out.println(Math.max(100, 300));
		
	}

}
