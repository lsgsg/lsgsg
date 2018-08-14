package samples;

import java.util.Scanner;

import samples.CoinIn;

public class Machine {

	private int cupCount, coin, coffee = 20, sugar = 10;
	private CoinIn co = new CoinIn();

	public Machine() {

	}

	public void showData() {
		Scanner s = new Scanner(System.in);
		System.out.println("얼마:");
		coin = s.nextInt();

		System.out.println("몇잔:");
		cupCount = s.nextInt();

		s.close();

		co.calc(coin, cupCount);

		System.out.println("성분 커피:" + coffee + " 성분 설탕:" + sugar);

	}

}
