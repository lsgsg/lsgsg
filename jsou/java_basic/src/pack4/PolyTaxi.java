package pack4;

public class PolyTaxi extends PolyCar {

	private int passenger = 2;

	public void show() {
		System.out.println("택시");
	}

	@Override
	public void disData() {
		System.out.println("버스 승객은 " + passenger);

	}

}
