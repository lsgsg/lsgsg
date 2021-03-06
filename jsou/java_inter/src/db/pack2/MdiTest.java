package db.pack2;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import javax.swing.plaf.DesktopPaneUI;

public class MdiTest extends JFrame implements ActionListener {
	JMenuItem mnuNew, mnuExit;
	JInternalFrame childWin;
	JDesktopPane desktopPane = new JDesktopPane();
	
	public MdiTest() {
		setTitle("mdi 연습");
		
		JMenuBar mbar = new JMenuBar();
		JMenu mnuFile = new JMenu("파일");
		mnuNew = new JMenuItem("새창");
		mnuExit = new JMenuItem("종료");
		mnuFile.add(mnuNew);
		mnuFile.add(mnuExit);
		mbar.add(mnuFile);
		setJMenuBar(mbar);
		
		mnuNew.addActionListener(this);
		mnuExit.addActionListener(this);
		
		
		setBounds(200,200,400,300);
		setVisible(true);;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("새창")) {
			getContentPane().add(desktopPane);
			createListen();
			
			desktopPane.add(childWin);
			childWin.setLocation(10, 10);
			childWin.show();
		}else if(e.getActionCommand().equals("종료")) {
			System.exit(0);
		}
		
	}
	
	private void createListen() {
		childWin = new JInternalFrame("자식창",   true, true, true,true);
		childWin.getContentPane().setLayout(new BorderLayout());
		childWin.setSize(300, 200);
		childWin.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		new MdiTest();

	}

}
