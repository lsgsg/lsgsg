package pack4;

public class PolyMain {

	public static void main(String[] args) {
		PolyCar car1 = new PolyCar();
		PolyBus bus1 = new PolyBus();
		PolyTaxi taxi1 = new PolyTaxi();

		car1.disData();
		System.out.println(car1.getSpeed());
		bus1.disData();
		bus1.show();
		System.out.println(bus1.getSpeed());

		System.out.println();

		taxi1.disData();
		taxi1.show();
		System.out.println(taxi1.getSpeed());

		System.out.println("---------------------");
		PolyCar car2 = new PolyBus();// promotion
		car2.disData();
		System.out.println(car2.getSpeed());
		// car2.show(); //err:자식 고유 메소드

		System.out.println();
		PolyCar car3 = taxi1;
		car3.disData();
		System.out.println(car3.getSpeed());

		System.out.println("++++++++++++++++++");
		// PolyBus bus2=new PolyCar();//부모를 자식에게 줄수없다
		// PolyBus bus2=car1;//X
		PolyBus bus2 = (PolyBus) car2;// O
		bus2.disData();
		System.out.println(bus2.getSpeed());

		System.out.println();
		// PolyTaxi taxi2=new PolyCar();//X
		// PolyTaxi taxi2=new PolyBus();//X
		PolyTaxi taxi2 = (PolyTaxi) car3;
		taxi2.disData();

		// PolyTaxi taxi3=(PolyTaxi)car1;//ClassCastException

		System.out.println("^^^^^^^^^^^^^^^^^^^");
		car1 = bus1;
		car1.disData();
		System.out.println();
		car1 = taxi1;
		car1.disData();
		
		car1=new PolyCar();

		System.out.println();
		PolyCar p[] = new PolyCar[3];// 배열 객체 생성
		p[0] = car1;
		p[1] = bus1;
		p[2] = taxi1;
		for (int a = 0; a < p.length; a++) {
			p[a].disData();
		}
		
		System.out.println();
		for(PolyCar my:p) {//p를 my한테준다
			my.disData();
		}

	}

}
