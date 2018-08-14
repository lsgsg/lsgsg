package samples;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame4 extends WindowAdapter{
	private Frame frame;
	
	public MyFrame4(){
		frame =new Frame("Adapter(추상)클래스 사용");
		frame.setSize(400,250);
		frame.setLocation(200, 250);
		frame.setVisible(true);
		frame.addWindowListener(this);
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	

	public static void main(String[] args) {
		new MyFrame4();

	}

}
