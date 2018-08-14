package pack3;

public class PohamHandle {
	int quantity;//회전량
	
	public PohamHandle() {
		quantity=0;
		
	}
	
	String leftTurn(int q) {
		quantity=q;
		return "좌회전";
	}
	String rightTurn(int q) {
		quantity=q;
		return "우회전";
	}
	String straight(int q) {
		quantity=q;
		return "직진";
	}
	

}
