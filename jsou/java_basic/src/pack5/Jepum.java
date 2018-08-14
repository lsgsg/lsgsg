package pack5;

public abstract class Jepum {// 추상 클래스 : new XX
	private int volume = 0;

	public Jepum() {
		System.out.println("추상 클래스 생성자");
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getVolume() {
		return volume;
	}

	public void volumeShow() {
		System.out.println("소리 크기는 " + volume);
	}

	// 추상메소드 (자식이 있다는거)
	abstract public void volumeControl(); // body { } 가 없는 메소드

}
