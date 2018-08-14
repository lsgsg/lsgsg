package pack3;

public final class Me extends Father{//Me는 상속받을 수 없음final //상속>상속 타고가야 함
	private int nai=20;
	public final String IRUM="유일해";// 값 수정 불가
	public String data="Me data";
	
	public Me() {
		System.out.println("내 생성자");
	}
	public void paly() {
		System.out.println("하고 싶은거 하기");
	}
	@Override
	public int getNai() {
				return nai;
	}
	
///	public void final eat() { //final은 오버라이드 불가 
///		System.out.println("밥은 맛있게");
//	}
	
	public void displayData() {
		String data="displayData method의 data";
		System.out.println(data);
		System.out.println(this.data);
		System.out.println(super.data);
	}
	
	

}
