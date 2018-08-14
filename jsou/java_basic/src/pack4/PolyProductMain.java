package pack4;

public class PolyProductMain {

	public static void main(String[] args) {
		PolyProduct product=null;
		
		PolyRadio radio=new PolyRadio();
		radio.setVolumn(10);
		System.out.println(radio.getVolumn());
		radio.volumnControl();
		
		System.out.println();
		PolyTv tv= new PolyTv();
		tv.setVolumn(7);
		tv.volumnControl();
		tv.tvShow();
		
		System.out.println();
		product=radio;
		product.volumnControl();
		product=tv;
		product.volumnControl();
		//product.tvShow(); 불가 오버라이딩 메소드가아님
		
		System.out.println("---------------------");
		PolyProduct poly[]=new PolyProduct[2];
		poly[0]=radio;
		poly[1]=tv;
		
		for(PolyProduct my:poly) {
			my.volumnControl();
			
		}

	}

}
