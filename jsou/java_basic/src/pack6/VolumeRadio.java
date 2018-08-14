package pack6;

public class VolumeRadio implements Volume {
	private int volLevel;

	public VolumeRadio() {
		volLevel = 0;
	}

	@Override
	public void voiumUp(int level) {
		System.out.println("TV 소리 키워:" + level);

	}

	@Override
	public void voiumDown(int level) {
		System.out.println("TV 소리 줄여:" + level);

	}

}
