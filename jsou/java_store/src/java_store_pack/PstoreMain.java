package java_store_pack;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.border.BevelBorder;

public class PstoreMain extends JFrame implements ActionListener{
	public static JMenuItem book_dae, book_ban, book_customer,book_book;
		
	public static JInternalFrame childWinDae,childWinBan,
		childWinCustomer,childWinBook,childWinEtc;
	public JDesktopPane desktopPane = new JDesktopPane();  //frame 생성
	
	public PstoreMain(String str) {
		super(str);
		setUndecorated(true); 
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		
		JMenuBar mbar = new JMenuBar();  //메뉴바
		mbar.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		JMenu mbook = new JMenu("메뉴 선택");
		book_dae = new JMenuItem("매출관리 & 폐기물품관리");
		book_ban = new JMenuItem("반품관리");
		book_customer = new JMenuItem("상품등록");
		book_book = new JMenuItem("상품주문");

		mbook.add(book_dae);       mbook.add(book_ban);
		mbook.add(book_customer);  mbook.add(book_book);
		mbook.addSeparator();  
		
		book_dae.addActionListener(this);  book_ban.addActionListener(this);
		book_customer.addActionListener(this);  book_book.addActionListener(this);
		
		
		mbar.add(mbook);
		setJMenuBar(mbar);  //frame에 메뉴바 장착
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(0, 0);
		this.setSize(dimension.width, dimension.height);
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(book_dae)){
			createListen("매출관리", 580, 400, "pan");
			Java_sale sale = new Java_sale();
			childWinDae.getContentPane().add(sale);
			childWinDae.setLocation(10, 10);
			desktopPane.add(childWinDae);
			this.getContentPane().add(desktopPane);
			childWinDae.show();
			book_dae.setEnabled(false);
		}else if(e.getSource().equals(book_ban)){
			createListen("반납", 500, 400, "ban");
			 Java_banpum banpum = new Java_banpum();
			childWinBan.getContentPane().add(banpum);
			childWinBan.setLocation(110, 60);
			desktopPane.add(childWinBan);
			this.getContentPane().add(desktopPane);
			childWinBan.show();
			book_ban.setEnabled(false);
		}else if(e.getSource().equals(book_customer)){
			createListen("등록", 430, 700, "insert");
			Java_Sang sang = new Java_Sang();
			childWinCustomer.getContentPane().add(sang);
			childWinCustomer.setLocation(210, 110);
			desktopPane.add(childWinCustomer);
			this.getContentPane().add(desktopPane);
			childWinCustomer.show();
			book_customer.setEnabled(false);
		}else if(e.getSource().equals(book_book)){
			createListen("주문", 430, 800, "call");
			Java_Jnmun2 book = new Java_Jnmun2();
			childWinBook.getContentPane().add(book);
			childWinBook.setLocation(310, 20);
			desktopPane.add(childWinBook);
			this.getContentPane().add(desktopPane);
			childWinBook.show();
			book_book.setEnabled(false);
		}
	}
	
	private void createListen(String title, int w, int h, String str){
		if(str.equals("pan")){
			childWinDae = new JInternalFrame(title, false, false, false, true);
			childWinDae.setSize(w, h);
		}else if(str.equals("ban")){
			childWinBan = new JInternalFrame(title, false, false, false, true);
			childWinBan.setSize(w, h);
		}else if(str.equals("insert")){
			childWinCustomer = new JInternalFrame(title, false, false, false, true);
			childWinCustomer.setSize(w, h);
		}else if(str.equals("call")){
			childWinBook = new JInternalFrame(title, false, false, false, true);
			childWinBook.setSize(w, h);
		}
	}
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		int y = calendar.get(Calendar.YEAR);
		int m = calendar.get(Calendar.MONTH) + 1;
		int d = calendar.get(Calendar.DATE);
		new PstoreMain("도서관리 ver 1.0   ★ 오늘은 " + y + "년 " + m + "월 " + d + "일");
	}
}