package pack5;

public class AniLion extends Animal {
	@Override
	public String name() {
		return "사자";
	}

	@Override
	public String eat() {
		return "고기";
	}

	@Override
	public String action() {
		return "밤";
	}

	public void eatOther() {
		System.out.println("가끔은 물도 마심");
	}

}
