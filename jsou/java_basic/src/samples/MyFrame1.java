package samples;

import java.awt.Frame;

//자바 제작자가 제공하는 Frame 클래스를 사용 - 포함관계
public class MyFrame1 {
	private Frame fr;
	
	public MyFrame1() {
		fr=new Frame("포함 연습");
		
//		fr.setLocation(200, 150);
//		fr.setSize(500, 300);
//		fr.setVisible(true);
	}
	
	private void display() {
		fr.setLocation(200, 150);
		fr.setSize(500, 300);
		fr.setVisible(true);
	}
	
	public static void main(String[] args) {
		MyFrame1 frame1=new MyFrame1();
		//new MyFrame1(); 이렇게 써도 상관없음
	}
	

}
