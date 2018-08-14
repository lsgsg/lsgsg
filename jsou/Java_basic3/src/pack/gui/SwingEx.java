package pack.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwingEx extends JFrame implements ActionListener {
	JLabel lblShow;
	int count = 0;

	public SwingEx() {
		setTitle("Swing test");
		
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		//panel.setBackground(Color.YELLOW);
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));//Factory 는 지스스로 new 함
		
		//panel 의 첫 행
		JButton button=new JButton("클릭(C)");
		button.setMnemonic(KeyEvent.VK_C);
		panel.add(button);
		button.addActionListener(this);
		
		//panel 의 두번째 행
		lblShow=new JLabel("버튼 클릭 수:"+count);
		panel.add(lblShow);
		
		
		
		//getContentPane().add(panel,BorderLayout.CENTER));
		//add("Center",panel);같음
		add(panel, BorderLayout.CENTER);

		setBounds(200, 200, 300, 300);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		count++;
		lblShow.setText("버튼 클릭 수:"+count);

	}

	public static void main(String[] args) {
		new SwingEx();

	}

}
