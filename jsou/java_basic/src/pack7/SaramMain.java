package pack7;

public class SaramMain {

	public static void main(String[] args) {
		Saram saram=new Saram();
		System.out.println(saram.getIr());
		
		System.out.println();
		SaramJames james=new SaramJames();
		Saram saram2=james.getSaram();
		System.out.println(saram2.getIr());

	}

}
