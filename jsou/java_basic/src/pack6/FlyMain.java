package pack6;

public class FlyMain {

	public static void main(String[] args) {
		System.out.println(Flyer.FAST);
		
		FlyerBird bird=new FlyerBird();
		bird.fly();
		FlyerAirplane airplane=new FlyerAirplane();
		airplane.fly();
		
		System.out.println();
		FlyerUtil.showData(bird);
		System.out.println();
		FlyerUtil.showData(airplane);

	}

}
