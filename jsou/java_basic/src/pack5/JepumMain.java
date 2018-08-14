package pack5;

public class JepumMain {

	public static void main(String[] args) {
		// Jepum jepum =new Jepum() ;//추상클래스는 인스턴스불가
		Jepum jepum = null;

		JepumTv tv = new JepumTv();
		tv.volumeShow();
		tv.volumeControl();

		System.out.println();
		JepumRadio radio = new JepumRadio();

		JepumHandphone handphone = new JepumHandphone();

		jepum = tv;
		jepum.volumeControl();
		jepum = radio;
		jepum.volumeControl();
		jepum = handphone;
		jepum.volumeControl();

		System.out.println();
		Jepum[] je = new Jepum[3];
		je[0]=tv;
		je[1]=radio;
		je[2]=handphone;
		for(int k=0;k<je.length;k++) {
			je[k].volumeControl();
		}
		
		for(Jepum k:je) {
			k.volumeControl();
		}
		System.out.println();
		
		
		
		

	}

}
