package pack2;

public class MyCalc {
	// 나만의 계산기 클라스
	private double no1, no2; //필요없음
	public static String msg = "자바는 웹용 언어";

	public double goPlus(double no1, double no2) {
		return no1 + no2 + 1000;
	}

	public double goMinus(double no1, double no2) {
		double imsi = no1;
		return imsi - no2 + 1000;
	}

	public double goMul(double no1, double no2) {
		return no1 * no2 + 1000;
	}

	public double goDiv(double no1, double no2) {
		if (no2 == 0.0) {
			System.out.println("0으로 나누지 말자");
			no2 = 1.0;
		}
		return no1 / no2 + 1000;
	}
	public String goTri(double no1, double no2) {
		double result=no1*no2/2;
		return "삼각형의 넓이는 "+result;
	}

}
