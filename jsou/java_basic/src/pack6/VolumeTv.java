package pack6;

public class VolumeTv implements Volume {
	private int vol;

	public VolumeTv() {
		vol = 0;
	}

	@Override
	public void voiumUp(int level) {
		System.out.println("TV 소리 키워:" + level);

	}

	@Override
	public void voiumDown(int level) {
		System.out.println("TV 소리 줄여:" + level);

	}

	public void Kbs() {
		System.out.println("국영방송 9");
	}

}
