package pack4;

public class DogMain {

	public static void main(String[] args) {
		Dog dog = new Dog();
		dog.print();
		dog.callName();
		System.out.println(dog.callName());

		System.out.println("HouseDog--------------");
		HouseDog hd = new HouseDog("집 개");
		hd.print();
		hd.show();
		System.out.println(hd.callName());

		System.out.println("WolfDog---------------");
		WolfDog wd = new WolfDog("늑대");
		wd.print();
		wd.show();
		System.out.println(wd.callName());

		System.out.println("**********************");
		WolfDog bushdog = wd;
		bushdog.print();

		System.out.println();// 부모는 자식의 객체의 주소를 치환해서 사용가능
		Dog dog2 = wd;
		dog2.print();// 오버라이딩된 메소드만 호출 가능
		// dog2.show();//자식 고유의 메소드는 호출 불가
		// dog2.display();

		// bushdog=dog; //불가
		// wd=dog2;
		wd = (WolfDog) dog2;// 가능
		bushdog = (WolfDog) dog2;

		System.out.println();
		Dog coyote = new Dog("코요테");
		coyote.print();
		System.out.println(coyote.callName());
		coyote=bushdog;
		coyote.print();

	}

}
