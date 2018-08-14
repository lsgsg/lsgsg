package pack5;

public class JepumHandphone extends Jepum {
	@Override
	public void volumeControl() {
		// 오버라이딩 강요 무조건해야함
		System.out.println("핸드폰 사운드 조정");

	}

	@Override
	public void volumeShow() {
		// 오버라이딩을 선택적으로 함
		setVolume(10);
		System.out.println("소리 크기:" + getVolume());

	}
}
