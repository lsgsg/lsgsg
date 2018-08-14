package pack.thread;

import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;

public class ThreadTest3 extends Frame implements ActionListener, Runnable{
	Label lbl;
	Thread thread;
	boolean b;

	public ThreadTest3() {
		// 스레드를 이용한 디지털 시계
		lbl = new Label("Display datetime", Label.CENTER);
		add("Center", lbl);

		Button btnClick = new Button("click");
		add("South", btnClick);
		btnClick.addActionListener(this);
		
		setTitle("스레드 시계");
		setBounds(200, 200, 300, 250);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});thread =new Thread(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(thread.isAlive())return;
		thread.start();
		
	}
	
	@Override
	public void run() {
		while(true) {
			calendarShow();
			try {
				Thread.sleep(1000);
			}catch (Exception e) {
				
			}
		}
		
	}
	
	private void calendarShow() {
		Calendar cal= Calendar.getInstance();
		int y=cal.get(Calendar.YEAR);
		int m=cal.get(Calendar.MONTH)+1;
		int d=cal.get(Calendar.DATE);
		int h=cal.get(Calendar.HOUR);
		int mi=cal.get(Calendar.MINUTE);
		int s=cal.get(Calendar.SECOND);
		lbl.setText(y+"-"+m+"-"+d+" "+h+":"+mi+":"+s);
		lbl.setFont(new Font("궁서", Font.BOLD, 20));
	}

	public static void main(String[] args) {
		new ThreadTest3();

	}

}
