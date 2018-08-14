package lambdatest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ButtonEvent extends JFrame {
	
	public ButtonEvent() {
		super("이벤트 람다 사용");
		setLayout(null);
		JButton btn1 = new JButton("click1");
	    btn1.setBounds(10,50,100,50);
	    add(btn1);
	    
	    setBounds(200,200,300,400);
	    setVisible(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
	    //전통적 방식
	    btn1.addActionListener(new ActionListener() { // new ActionListener() 드래그 -> 오.마 -> quick fix -> convert to lambda 어쩌고 클릭 -> 변신
	    	
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setTitle("첫번째 버튼");
				
			}
		});
	    
	    
	    JButton btn2 = new JButton("click2");
	    btn2.setBounds(10,150,100,50);
	    add(btn2);
	    btn2.addActionListener(e ->setTitle("두번째"));
	    
	    
	    //Debug 연습용
	    JButton btn3 = new JButton("click3");
	    btn3.setBounds(10,250,100,50);
	    add(btn3);
	    btn3.addActionListener(new ActionListener() { // new ActionListener() 드래그 -> 오.마 -> quick fix -> convert to lambda 어쩌고 클릭 -> 변신
	    	
	    	int cou = 0;
	    	int tot = 0;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 5; i++) {
					cou +=1;
					//System.out.println("cou : "+ cou); //변수값 확인;
					tot += cou;
					//JOptionPane.showMessageDialog(ButtonEvent.this, "cou :" + cou);
				}
				setTitle("합은 " + tot);
			}
		});
	    
	}
	public static void main(String[] args) {
		new ButtonEvent();
	}
}

