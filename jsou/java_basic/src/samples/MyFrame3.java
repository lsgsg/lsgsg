package samples;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MyFrame3 extends Frame implements WindowListener, MouseListener {
	public MyFrame3() {

		super("인터페이스 연습");
		addWindowListener(this);// 현재 Frame에 WindowListener 장착
		addMouseListener(this);

	}

	private void abc() {
		setLocation(200, 150);
		setSize(300, 250);
		setVisible(true);
	}

	// 7개의 WindowListener 내의 추상메소드를 오버라이딩
	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("화면 복귀");

	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("화면 아이콘화");

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	int count = 0;
	// Mouse 관련 메소드

	@Override
	public void mouseClicked(MouseEvent e) {
		// int count=0;//지역변수이므로 숫자가 안늘어남
		// System.out.println("폼바닥 클릭 수 : "+(count+=1));

		// setBackground(Color.BLUE);
		// setBackground(new Color(255, 0, 0));
		// System.out.println((int)(Math.random()*255));
		int r = (int) (Math.random() * 255);
		int g = (int) (Math.random() * 255);
		int b = (int) (Math.random() * 255);
		setBackground(new Color(r, g, b));
		setTitle(r+" "+g+" "+b);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	public static void main(String[] args) {
		MyFrame3 frame3 = new MyFrame3();
		frame3.abc();

	}

}
