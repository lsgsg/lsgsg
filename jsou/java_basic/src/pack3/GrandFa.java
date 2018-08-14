package pack3;

public class GrandFa {
	private int nai = 80;// 현재 클래스에서만 사용
	public String gabo = "상감청자";// 현재 프로젝트에서 사용
	// String gabo 현재 패키지에서만 사용
	protected String gahun = "자바를 정복하자";// 현재 패키지 사용, 해당 클래스 자식클래스는 참조가능
	public String data="GrandFa data";

	public GrandFa() {
		System.out.println("할아버지 생성자");
	}
	public GrandFa(String ss) {
		//....
	}
	public GrandFa(String ss,int aa) {
		//....
	}

	public GrandFa(int nai) {
		this();//해당 생성자를 부를수있다. 대신 최상위에 기재
		this.nai = nai; // 멤버변수=지역번수
	}

	public String say() {
		return "할아버지 말씀:그날 그날 정리";
	}

	public void eat() { 
		System.out.println("밥은 맛있게");

	}

	public int getNai() {
		return nai;
	}
	


}
