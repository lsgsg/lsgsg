package pack2;

public class AnimalMain {

	public static void main(String[] args) {
		System.out.println("뭔가하다가");

		Animal tiger = new Animal();
		tiger.display();
		tiger.display(5);
		tiger.display("호돌이");
		tiger.display(7, "호순이");
		tiger.display("호국이", 10);
		System.out.println();

		Animal hen = new Animal(2);
		hen.display();
		hen.display("통닭", 1);
		
		System.out.println();
		Animal wolfDog=new Animal("늑대");
		wolfDog.display();
		
		System.out.println();
		
		Animal dog=new Animal("개", 3);
		dog.display();
		
		System.out.println("--------static");
		dog.myStaticMethod();
		Animal.myStaticMethod();
		dog.normalMethod();

	}
	

}
