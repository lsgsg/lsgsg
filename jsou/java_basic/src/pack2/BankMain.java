package pack2;

public class BankMain {

	public static void main(String[] args) {
		Bank tom = new Bank();
		tom.dePosit(5000);
		tom.withDraw(3000);
		tom.withDraw(9000);
		System.out.println("tom의 예금액:" + tom.getMoney());

		System.out.println();

		Bank oscar = new Bank(2000);
		System.out.println("oscar의 예금액:" + oscar.getMoney());
		oscar.dePosit(1000);
		oscar.withDraw(2500);
		System.out.println("oscar의 예금액:" + oscar.getMoney());

		System.out.println("객체주소-------");
		System.out.println("tom의 주소:" + tom);
		System.out.println("tom의 주소:" + tom.hashCode());
		System.out.println("oscar의 주소:" + oscar);
		System.out.println("oscar의 주소:" + oscar.hashCode());
		Bank james = null;
		// System.out.println("james의 예금액:"+james.getMoney());//NullPointerException
		System.out.println("james의 주소:" + james);
		james = oscar;// 주소의 치환 int a=5; int b=a;
		// Animal tiger = oscar;
		System.out.println("james의 예금액:" + james.getMoney());
		System.out.println("james의 주소:" + james);

		System.out.println();

		if (james == tom)// 주소의 비교 if(a==b) 값 비교
			System.out.println("둘은 같은 객체의 주소");
		else
			System.out.println("둘은 다른 객체의 주소");

		if (james instanceof Bank)// instanceof 클래스타입 비교 연산자
			System.out.println("Bank type");
		else
			System.out.println("Bank type이 아님");

		System.out.println("\nString 클래스----");
		String ss1 = "kor";
		String ss2 = new String();
		ss2 = "kor";
		String ss3 = new String("kor");

		System.out.println(ss1 + " " + ss2 + " " + ss3);

		if (ss1 == ss2)
			System.out.println("같아1");
		else
			System.out.println("달라1");

		if (ss1 == ss3)// String은 이런비교 XXXXXXXX
			System.out.println("같아2");
		else
			System.out.println("달라2");

		if (ss1.equals(ss2))
			System.out.println("같아1");
		else
			System.out.println("달라1");

		if (ss1.equals(ss3))// ss1.equalsIgnoreCase()
			System.out.println("같아2");
		else
			System.out.println("달라2");

		System.out.println("배열------------");
		int ar1[] = { 1, 2, 3 };
		System.out.println(ar1);
		System.out.println(ar1[0] + " " + ar1[1]);

		int ar2[][] = { { 1, 2, 3 }, { 4, 5, 6 } };
		System.out.println(ar2);
		System.out.println(ar2[0]);
		System.out.println(ar2[0][0]);
		
		System.out.println();
		
		Bank john=new Bank();
		System.out.println(john.imsi);
		System.out.println(john.imsi2);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
