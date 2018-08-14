package java_basic3;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

public class DtoTest2Ex {

	ArrayList<Test2Ex> list;

	public DtoTest2Ex() {
		list = new ArrayList<Test2Ex>();
	}

	public void inputData(String[] args) {

		for (int i = 0; i < args.length; i++) {
			StringTokenizer tok = new StringTokenizer(args[i], ",");

			int sabun = Integer.parseInt(tok.nextToken());
			String name = tok.nextToken();
			int money = Integer.parseInt(tok.nextToken());
			int year = Integer.parseInt(tok.nextToken());

			Test2Ex test2Ex = new Test2Ex();
			test2Ex.setSabun(sabun);
			test2Ex.setName(name);
			test2Ex.setMoney(money);
			test2Ex.setYear(year);
			list.add(test2Ex);

		}

	}

	public void printData(String[] args) {

		System.out.println("사번\t이름\t기본급\t근무년수\t근속수당\t공제액\t\t수령액");
		
		for (Test2Ex test2Ex : list) {

			int incentive;
			double tax = 0;
			int total;
			

			Calendar calendar = Calendar.getInstance();
			int howlong = calendar.get(Calendar.YEAR) - test2Ex.getYear();

			if (howlong >= 9) {
				incentive = 1000000;
				total = test2Ex.getMoney() + incentive;
			} else if (howlong >= 4 && howlong <= 8) {
				incentive = 450000;
				total = test2Ex.getMoney() + incentive;
			} else {
				incentive = 150000;
				total = test2Ex.getMoney() + incentive;
			}

			if (test2Ex.getMoney() >= 3000000)
				tax = total * 0.05;
			else if (test2Ex.getMoney() < 2000000)
				tax = total * 0.015;
			else
				tax = total * 0.03;
			
		

			System.out.println(test2Ex.getSabun() + "\t" + test2Ex.getName() + "\t" + test2Ex.getMoney() + "\t"
					+ howlong + "\t" + incentive + "\t" + tax + "\t" + (total-(int)tax));

		}
		System.out.println("처리건수"+list.size());

	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("입력자료 없음");
			System.exit(0);
		}

		DtoTest2Ex test2 = new DtoTest2Ex();
		test2.inputData(args);
		test2.printData(args);

	}

}
