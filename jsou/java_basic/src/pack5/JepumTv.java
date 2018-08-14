package pack5;

public class JepumTv extends Jepum {// 오버라이딩를 해야함
	public JepumTv() {
		System.out.println("TV 생성자");
	}

	@Override
	public void volumeControl() {
		System.out.println("티브이 소리 조절");

	}

}
