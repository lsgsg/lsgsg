package pack6;

public class FlyerBall extends FlyerAdapter {
	@Override
	public void fly() {
		System.out.println("공이 관중 속으로 날아감");
	}
	
	public static void main(String[] args) {
		FlyerBall ball=new FlyerBall();
		ball.fly();
	}

}
