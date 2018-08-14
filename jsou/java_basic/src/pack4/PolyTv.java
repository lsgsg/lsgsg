package pack4;

public class PolyTv extends PolyProduct{
	@Override
	public void volumnControl() {
		System.out.println("TV 사운드 조절 하고 보면"+getVolumn());
	}
	public void tvShow() {
		System.out.println("TV 만의 고유 메소드");
	}
	
	
}
