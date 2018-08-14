package pack3;

public class MyFamily {

	public static void main(String[] args) {
		GrandFa gr = new GrandFa();
		System.out.println("가보:" + gr.gabo);
		System.out.println("가훈:" + gr.gahun);
		System.out.println(gr.say());
		gr.eat();
		System.out.println("할아버지 나이:" + gr.getNai());

		System.out.println();
		GrandFa gr2 = new GrandFa(82);
		System.out.println("가보:" + gr2.gabo);
		System.out.println("할아버지 나이:" + gr2.getNai());

		System.out.println("----------------------");

		Father fa1 = new Father();
		System.out.println("가보:" + fa1.gabo);
		System.out.println("가훈:" + fa1.gahun);
		System.out.println(fa1.say());
		fa1.eat();
		System.out.println("아버지 나이:" + fa1.getNai());
		System.out.println("집은" + fa1.getHouse());

		System.out.println();

		// Father fa2=new Father(51);
		// System.out.println("아버지2 나이:"+fa2.getNai());

		System.out.println("------------------------");

		Me me = new Me();
		System.out.println("가보:" + me.gabo);
		System.out.println("가훈:" + me.gahun);
		System.out.println(me.say());
		me.eat();
		System.out.println("아버지 나이:" + me.getNai());
		System.out.println("집은" + me.getHouse());
		me.paly();
		me.displayData();

	}

}
