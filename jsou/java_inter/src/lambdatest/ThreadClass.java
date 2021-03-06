package lambdatest;

public class ThreadClass {

	private void sendEmail( String ss ) {
		System.out.println(ss + "메일을 전송");
		
	}
	public ThreadClass() {
		m1();
		m2();
		m3();
		m4();
	}
	
	void m1() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				sendEmail("m1");
				
			}
		}).start();
	}
	
	void m2() {//람다사용1
		Thread thread = new Thread(()->sendEmail("m2"));
		thread.start();
	}
	
	void m3() {//람다사용2
		new Thread(()->sendEmail("m3")).start();

	}
	
	void m4() {//람다사용2
		Runnable runnable = ()->sendEmail("m4");
		runnable.run();

	}
	
	public static void main(String[] args) {
		new ThreadClass();

	}

}
