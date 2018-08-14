package samples;

public class CoinIn {

	private int jandon;

	public int getJandon() {
		return jandon;
	}

	public void calc(int coin, int cupCount) {
		if (coin < 200) {
			System.out.println("요금 부족");
		} else if (cupCount * 200 > coin) {
			System.out.println("요금 부족");
		} else {
			jandon = coin - (200 * cupCount);
			System.out.println("커피" + cupCount + "잔과 잔돈" + jandon + "원");
		}
	}

	// public String calc(int cupCount) { //해당도 가능 public String 으로 선언시,
	// String msg = " ";
	// if (coin < 200) {
	// msg = "요금 부족";
	// } else if (cupCount * 200 > coin) {
	// msg = "요금 부족";
	// } else {
	// jandon = coin - (200 * cupCount);
	// msg = "커피" + cupCount + "잔과 잔돈" + jandon + "원";
	// }
	// return msg;
	// }
}