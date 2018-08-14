package pack.gui;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EventEx extends Frame implements ActionListener{
	private Button btn1=new Button("btn1");
	private Button btn2=new Button("btn2");
	private Button btn3=new Button("btn3");
	private Button btn4=new Button("btn4");
	private Button btn5=new Button("btn5(exit)");

	
	public EventEx() {
		super("이벤트 연습");
		
		makeLayout();
		setBounds(200, 200, 300, 300);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	private void makeLayout() {
		add("East",btn1);
		add("West",btn2);
		add("Center",btn3);
		add("South",btn4);
		add("North",btn5);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(new MyEvent());//내부클래스
		btn4.addMouseListener(new OurEvent());
		
		//내부 무명 클래스
		btn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getActionCommand());
		//System.out.println(e.getSource());
		//if(e.getActionCommand().equals("btn1"))...~
		if(e.getSource()==btn1) {
			setTitle("버튼1 클릭");
		}else if(e.getSource()==btn2) {
			setTitle("버튼2 선택");
		}
		
	}
	
	//내부 클래스1 - btn3
	class MyEvent implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//setTitle("세번째 버튼이라네");
			EventEx.this.setTitle("세번째 버튼이라네");//같음
			
		}
	}
	
	//내부 클래스2 - btn4
	class OurEvent extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			setTitle("네번째 버튼 만세");
		}
		
	}

	public static void main(String[] args) {
		new EventEx();

	}

}
