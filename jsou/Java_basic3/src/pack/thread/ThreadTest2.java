package pack.thread;

public class ThreadTest2 implements Runnable {

	public ThreadTest2() {
		// TODO Auto-generated constructor stub
	}

	public ThreadTest2(String name) {
		Thread tt = new Thread(this, name);
		tt.start();

	}

	@Override
	public void run() {
		for (int i = 1; i <= 50; i++)
			System.out.println(i + ":" + Thread.currentThread().getName());
		try {
			Thread.sleep(300);
		} catch(Exception e) {

		}
	}

	public static void main(String[] args) {
		new ThreadTest2("하나");
		new ThreadTest2("두울");

		System.out.println();
		System.out.println("종료");

	}

}
