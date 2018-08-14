package pack4;

public class PolyCar {
	protected int speed = 80;

	public PolyCar() {
		System.out.println("난 자동차야~");
	}

	public void disData() {
		System.out.println("속도:" + speed);
	}

	public int getSpeed() {
		return speed;
	}

}
