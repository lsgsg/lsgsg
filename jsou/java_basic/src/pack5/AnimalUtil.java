package pack5;

public class AnimalUtil {
	public static void find(Animal animal) {
		animal.print();

		if (animal instanceof AniCow) {// instanceof 객체타입비교연산자
			Animal a = animal;// Animal a=(AniCow)animal; 도 같음
			System.out.println("이름:" + a.name());
		} else if (animal instanceof AniLion) {
			Animal b = animal;
			System.out.println("이름:" + b.name());
		} else {
			System.out.println("기타 동물");
		}
	}

}
