package pack3;

public class PohamCar {
	int speed = 0;
	String ownerName, turnShow;
	// ....
	PohamHandle handle;//클래스를 멤버필드로 사용

	public PohamCar() {
		handle= new PohamHandle();//클래스의 포함관계

	}

	public PohamCar(String name) {
		ownerName = name;
		handle= new PohamHandle();//클래스의 포함관계
		turnShow="직진";
	}
	
	public void playHandle(int q) {
		if(q>0) {
			turnShow=handle.rightTurn(q);
		}else if(q<0) {
			turnShow=handle.leftTurn(q);
		}else {
			turnShow=handle.straight(0);
		}
	}

}
