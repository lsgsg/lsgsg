package pack.thread;

public class BreadPlate {
	private int breadCount = 0;// 공유대상

	public BreadPlate() {
		// TODO Auto-generated constructor stub
	}

	public synchronized void makeBread() {
		if (breadCount >= 10) {
			try {
				System.out.println("빵 생산 초과");
				wait();//thread 작업을 비활성화
			} catch (Exception e) {

			}
		}
		breadCount++;
		System.out.println("빵을 생산, 총 " + breadCount + "개");
		notify();

	}

	public synchronized void eatBread() {
		if (breadCount <= 1) {
			try {
				System.out.println("빵 소비 불가");
				wait();
			} catch (Exception e) {

			}
		}
		breadCount--;
		System.out.println("빵을 소비, 총 " + breadCount + "개");
		notify();

	}

}
