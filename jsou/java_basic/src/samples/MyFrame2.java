package samples;

import java.awt.Frame;

public class MyFrame2 extends Frame{
	public MyFrame2() {
		
		//super("상속 연습");//제목은 Frame클래스의 private 멤버
		
//		setLocation(200, 150);
//		setSize(500, 300);
//		setVisible(true);
	}
	
	private void abc() {
		setTitle("상속 연습");
		setLocation(200, 150);
		setSize(500, 300);
		setVisible(true);
	}
	

	public static void main(String[] args) {
		//new MyFrame2();
		MyFrame2 frame2= new MyFrame2();
		frame2.abc();

	}

}
