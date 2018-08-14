package pack5;

public class AnimalMain {

	public static void main(String[] args) {
		Animal animal=null;
		
		AniCow cow=new AniCow();
		System.out.println(cow.name()+" "+cow.action()+"에 "+cow.eat()+"먹음");
		
		System.out.println();
		
		AniLion lion=new AniLion();
		System.out.println(lion.name()+" "+lion.action()+"에 "+lion.eat()+"먹음");
		lion.eatOther();//lion 고유 메소드
		
		System.out.println("----------------------");
		
		animal=cow;
		System.out.println(animal.name());
		animal=lion;
		System.out.println(animal.name());
		
		System.out.println("----------------------");
		
		AnimalUtil.find(cow);
		System.out.println();
		AnimalUtil.find(lion);
		
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^");
		
		//AniDog dog=new AniDog(); 안됨
		AniDog dog = new AniDogWolf();
		System.out.println(dog.name());
		System.out.println(dog.eat());
		System.out.println(dog.action());
	}

}
