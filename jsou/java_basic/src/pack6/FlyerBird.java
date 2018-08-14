package pack6;

public class FlyerBird implements Flyer {

	@Override
	public void fly() {
		System.out.println("날개를 저으며 숲으로 날아감");

	}

	@Override
	public boolean isAnimal() {
		
		return false;
	}

}
