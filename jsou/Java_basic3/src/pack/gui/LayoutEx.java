package pack.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LayoutEx extends Frame implements ActionListener{
	Panel pn1 =new Panel();
	Panel pn2 =new Panel();
	Panel pn3 =new Panel();
	Panel pn4 =new Panel();
	Panel pn5 =new Panel();
	Button btnOk;
	CardLayout card=new CardLayout();
	TextField txtBunho,txtIrum;
	
	public LayoutEx() {
		//Frame은 기본이 Borderlayout
		setLayout(new GridLayout(2, 1));//layout 변경
		
		//1번째 행
		Label lbl1=new Label("bunho");
		txtBunho=new TextField("10",20);
		//Panel은 기본이 FlwLayout, 좌에서 우로 위에서 아래로
		pn1.add(lbl1);//Label을 Panel에 배치
		pn1.add(txtBunho);
		pn1.setBackground(Color.YELLOW);
		add(pn1);//Panel을 프레임에 배치
		
		Label lbl2=new Label("irum");
		txtIrum=new TextField("홍길동",20);
		pn2.add(lbl2);
		pn2.add(txtIrum);
		pn2.setBackground(Color.CYAN);
		//add(pn2);
		
		pn3.setLayout(card);//FlowLayout -> CardLayout
		pn3.add("kbs", pn1);
		pn3.add("mbc", pn2);
		//add(pn3);
		
		btnOk=new Button("OK");
		btnOk.addActionListener(this);
		pn4.add(pn3);
		pn4.add(btnOk);
		add(pn4);
		
		
		//2번째 행
		pn5.setBackground(Color.GREEN);
		pn5.setLayout(new BorderLayout());
		pn5.add("Center", new Label("My name is java",Label.CENTER));
		pn5.add("East", new Label("East"));
		pn5.add("West", new Label("West"));
		pn5.add("South", new Label("South", Label.CENTER));
		pn5.add("North", new Label("North", Label.CENTER));
		
		add(pn5);
		
		
		setTitle("레이아웃 연습");
		setBounds(200, 200, 400, 300);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		setTitle("클릭");
		
		//System.out.println(e.getActionCommand());
		if(e.getActionCommand().equalsIgnoreCase("ok")) {
			btnOk.setLabel("click");
			card.show(pn3, "mbc");
		}else {
			btnOk.setLabel("OK");
			card.show(pn3, "kbs");
		}
		
	}
	

	public static void main(String[] args) {
		new LayoutEx();

	}

}
