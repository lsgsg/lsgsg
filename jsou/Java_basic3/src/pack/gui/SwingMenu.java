package pack.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SwingMenu extends JPanel implements ActionListener {
	JButton btnR, btnG, btnB;
	JTextArea txtArea = new JTextArea("", 10, 50);

	JMenuBar mBar;
	JMenuItem mnuMes, mnuOk, mnuInput;

	public SwingMenu() {
		setLayout(new BorderLayout());// F -> Border Layout
		btnR = new JButton("빨강");
		btnG = new JButton("초록");
		btnB = new JButton("파랑");
		btnR.addActionListener(this);
		btnG.addActionListener(this);
		btnB.addActionListener(this);

		JPanel jPanel = new JPanel();
		jPanel.add(btnR);
		jPanel.add(btnG);
		jPanel.add(btnB);
		add("South", jPanel);
		add("Center", txtArea);

		menuShow();
	}
	

	private void menuShow() {
		mBar=new JMenuBar();
		JMenu menu=new JMenu("메뉴");
		mnuMes=new JMenuItem("메세지");
		mnuOk=new JMenuItem("확인");
		mnuInput=new JMenuItem("입력");
		menu.add(mnuMes);
		menu.add(mnuOk);
		menu.add(mnuInput);
		
		mBar.add(menu);
		
		//메뉴에 리스너 장착
		mnuMes.addActionListener(this);
		mnuOk.addActionListener(this);
		mnuInput.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnR) {
			txtArea.setBackground(Color.RED);

		} else if (e.getSource() == btnG) {
			txtArea.setBackground(new Color(0, 255, 0));
		} else if (e.getSource() == btnB) {
			txtArea.setBackground(new Color(0, 0, 255));
		}else if(e.getSource()==mnuMes) {
			JOptionPane.showMessageDialog(this, "메세지 창", "알림", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("창이 닫히기 전까지 수행 안함");
		}else if(e.getSource()==mnuOk) {
			int re=JOptionPane.showConfirmDialog(this, "버튼선택","선택",JOptionPane.YES_NO_CANCEL_OPTION);
					
			//System.out.println(re);
//			switch(re) {
//			case 0:
//				txtArea.setText("예 선택");
//			
//			case 1:
//				txtArea.setText("아니오 선택");
//				
//			case 2:
//				txtArea.setText("취소 선택");
//				break;
//			}
			switch(re) {
			case JOptionPane.YES_OPTION:
				txtArea.append("예 선택");
			break;
			case JOptionPane.NO_OPTION:
				txtArea.append("아니오 선택");
				break;
			case JOptionPane.CANCEL_OPTION:
				txtArea.append("취소 선택\n");
				break;
			}
		}else if(e.getSource()==mnuInput) {
			String ss= JOptionPane.showInputDialog(this, "자료입력");
			txtArea.append(ss+"\n");
		}
//			else if(e.getSource()==mnuInput) {
//			JOptionPane.showMessageDialog(this, "메세지 창", "알림", JOptionPane.INFORMATION_MESSAGE);
//		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("메뉴&대화상자");
		SwingMenu panel1 = new SwingMenu();// Panel

		// frame.getContentPane().add(panel1);같음
		frame.add(panel1);//Panel을 Jframe에 배치
		frame.setJMenuBar(panel1.mBar);//Frame에 메뉴 배치
		frame.setBounds(200, 200, 300, 200);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
