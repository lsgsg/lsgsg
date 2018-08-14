package pack2; //성격이 비슷한 클래스들을 모아두는 폴더

//클래스 선언 - 두 개의 멤버를 가질 수 있다.
public class Car {// [접근지정자] [기타제한자] class 클래스명
	// 접근지정자 : public, private, protected, 생략

	// 멤버필드(멤버변수, 전역변수)
	int wheel; // [접근지정자] [기타제한자] type 멤버명
	private int airBag = 1;// 캡슐화
	private int speed;
	public String name;

	public Car() {
		// 객체 생성 시 초기화 작업을 함
		// 객체 생성 시 가장 먼저 시스템에 의해 호출
		// 인위적으로 호출 불가
		System.out.println("Car 클래스 생성자");
		wheel = 4;
	}

	// 멤버 메소드
	// [접근지정자] [기타제한자] 반환형 메소드명(인수~, , ,)
	public void abc() {
		System.out.println("abc 메소드(method) 수행");
		System.out.println("에어백 수:" + airBag);// private
		System.out.println("바퀴 수:" + wheel);// default(생략)
		def(); // 자신이 속해있는 클래스 내의 메소드를 호출
		int result = korea(7);
		System.out.println("스피커 볼륨은 " + result);
		System.out.println("속도는 " + speed);
		int year = 2018;// 변수(지역변수)는 초기값 필수
		System.out.println("생산년도 : " + year);
	}

	private void def() {
		System.out.println("def 메소드 처리");
	}

	int korea(int speaker_vol) {
		int imsi = speaker_vol + 10;
		return imsi;
	}

	public int getSpeed() { // private 멤버에 대한 외부 접근 가능 메소드
		// getter
		return speed;
	}

	public void setSpeed(int password, int speed) {
		// setter
		if (password == 1234) {
			this.speed = speed;
		} else {
			System.out.println("비밀번호 불일치!!!");
		}
	}
}
