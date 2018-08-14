package pack1;

import java.math.BigDecimal;
public class Test5bigdecimal {

	public static void main(String[] args) {
		//double a = 1.5;
		//double b = 1.2;
		double a = 2.0;
		double b = 1.1;
		System.out.println(a + b);
		System.out.println(a - b);
		
		System.out.println();
		BigDecimal d1 = new BigDecimal("2.0");
		BigDecimal d2 = new BigDecimal("1.1");
		
		System.out.println(d1.subtract(d2));
		
		System.out.println();
		BigDecimal no1 = new BigDecimal("1231231219");
		BigDecimal no2 = new BigDecimal("1231231210");
		System.out.println(no1.add(no2));
		System.out.println(no1.subtract(no2));
		System.out.println(no1.multiply(no2));
		System.out.println(no1.divide(no2, 2, BigDecimal.ROUND_UP));
		
		
		
		
	}

}
