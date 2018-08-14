package pack.thread;

public class ThreadTest1 extends Thread {
	
	public ThreadTest1() {
		// TODO Auto-generated constructor stub
	}

	public ThreadTest1(String name) {
		super(name);

	}

	public void run() {
		for (int i = 1; i <= 50; i++) {
			//System.out.println(i + " ");
			System.out.println(i+":"+super.getName());
		}
	}

	public static void main(String[] args) {
		try {
			// process 단위의 처리
			// Process p1=Runtime.getRuntime().exec("calc.exe");
			// Process p2=Runtime.getRuntime().exec("notepad.exe");
			// p1.waitFor();//자신은 정상종료 나머지프로그램은 강제종료
			// p2.destroy();
			// System.out.println("p1:"+p1.exitValue());
			// System.out.println("p2:"+p2.exitValue());

			ThreadTest1 t1 = new ThreadTest1("one ");
			ThreadTest1 t2 = new ThreadTest1("two ");
			t1.start(); //run()호출
			t2.start();
			t2.setPriority(MAX_PRIORITY);//우선순위 변경 요청
			
			t1.join();//일반 스레드의 수행이 끝날때까지 메인스레드를 대기
			t2.join();
			
			t1.yield();//다른스레드의 수행이 요청되면 현재 스레드의 수행을 일시정지
			
			//t1.stop();//deprecated
			System.out.println("프로그램종료");

		} catch (Exception e) {
			System.out.println("err:" + e);
		}

	}

}
