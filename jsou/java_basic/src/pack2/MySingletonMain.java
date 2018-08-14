package pack2;

import java.util.Calendar;

public class MySingletonMain {

	public static void main(String[] args) {
		MySingleton s1 = new MySingleton();
		MySingleton s2 = new MySingleton();

		System.out.println(s1 + " " + s2);
		MySingleton s3 = MySingleton.getInstance();
		MySingleton s4 = MySingleton.getInstance();
		System.out.println(s3 + " " + s4);
		
		
		System.out.println();
		Calendar calendar=Calendar.getInstance();
		int y=calendar.get(Calendar.YEAR);
		System.out.println("년도는 "+y+"년");
		
		Calendar calendar2=Calendar.getInstance();
		int y2=calendar2.get(Calendar.YEAR);
		int m2=calendar2.get(Calendar.MONTH)+1;
		System.out.println("년도는 "+y2+"년 "+m2+"월");
	}

}
