package java_store_pack;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class Java_Sang extends JPanel implements ActionListener{
	JTextField txtPsangnum,txtPirum,txtPipgo,txtPuto,txtPpanme,txtPpepum;
	JButton btnSd,btnSg,btnClose;
	JRadioButton y1,n1;
	
	Date today = new Date();
	SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/dd");
	String nalja = format.format(today);

	
	ButtonGroup buttonGroup = new ButtonGroup();
	String o;
	String sql;
	int iTotal = 0;
	int iLast = 0;
	boolean isInsert = false;
	
	Connection conn;
	PreparedStatement pstmt;
	private Statement stmt;
	ResultSet rs;
	public Java_Sang() {
		design();
		addListener();
		accDb();
		
		init();
		display();
	}
	
	private void addListener() {
		btnSd.addActionListener(this);
		btnSg.addActionListener(this);
		btnClose.addActionListener(this);
	}
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:&127.0.0.1:1521:orcl", "scott", "tiger");
		} catch (Exception e) {
			System.out.println("accDb err : " + e.getMessage());
		}
	}
	
	private void init() {
		try {
			
		} catch (Exception e) {
			System.out.println("init err : " + e);
		}
	}
	private void display() {
		
		try {
			sql = "select * from p_convenience order by p_sangnum asc";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			rs.last();
			
			
			
			iLast = rs.getInt("p_sangnum");
			txtPsangnum.setText(iLast + 1 + "");

			if(rs.getString("p_ipgo") == null) {
				txtPipgo.setText("");
			}else {
				txtPipgo.setText(nalja);
			}
		} catch (Exception e) {
			System.out.println("display err : " + e);
		}
		}
//	}
	
	public void design() {
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		JPanel bookPn=new JPanel(new GridLayout(10,1));
		bookPn.setBorder(new TitledBorder(new TitledBorder("상품 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		JPanel bPn1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn4=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn5=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn6=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn7=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn8=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		txtPsangnum=new JTextField("",5);
		txtPirum=new JTextField("",10);
		txtPipgo=new JTextField("",15);
		txtPuto=new JTextField("",15);
		txtPpanme=new JTextField("",5);
		

		
		
		y1 = new JRadioButton("Y", false);
		n1 = new JRadioButton("N", true);
		buttonGroup.add(y1);
		buttonGroup.add(n1);
		

		txtPsangnum.setEditable(false);
		txtPirum.setEditable(true);
		txtPipgo.setEditable(false);
		txtPuto.setEditable(true);
		txtPpanme.setEditable(true);
		
		btnSd=new JButton("상품 등록");
		btnSg=new JButton("새로 고침");
		btnClose=new JButton("닫기");
		
		bPn1.add(new JLabel("상품명 :"));
		bPn1.add(txtPirum);
		
		bPn2.add(new JLabel("상품코드 :"));
		bPn2.add(txtPsangnum);
		
		bPn3.add(new JLabel("   판매가   :"));
		bPn3.add(txtPpanme);	

		bPn4.add(new JLabel("   입   고    :"));
		bPn4.add(txtPipgo);
		
		bPn5.add(new JLabel("유통기한 :"));
		bPn5.add(txtPuto);
		
		bPn6.add(new JLabel("폐기 상태 : "));
		bPn6.add(y1);
		bPn6.add(n1);
		txtPpepum = new JTextField("",10);
		bPn6.add(txtPpepum);
		
		bookPn.add(bPn1);  bookPn.add(bPn2); 	bookPn.add(bPn3);
		bookPn.add(bPn4);  bookPn.add(bPn5); 	bookPn.add(bPn6);
		bookPn.add(bPn7);  bookPn.add(bPn8);
		this.add(bookPn);
		

		JPanel bottomPn1=new JPanel();
		bottomPn1.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		bottomPn1.add(btnSd);
		bottomPn1.add(btnSg);
		bottomPn1.add(btnClose);
		
		this.add(bottomPn1);
		
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
	}



	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSd) {
			if (y1.isSelected()) {
				txtPpepum.setText("x");
			}else if (n1.isSelected()) {
				txtPpepum.setText("o");
			}
		try {
				sql = "insert into p_convenience values(?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				conn = DriverManager.getConnection("jdbc:oracle:thin:&127.0.0.1:1521:orcl", "scott", "tiger");
				
				pstmt.setString(1, txtPsangnum.getText());
				pstmt.setString(2, txtPirum.getText());
				pstmt.setString(3, txtPipgo.getText());
				pstmt.setString(4, txtPuto.getText());
				pstmt.setString(5, txtPpanme.getText());
				pstmt.setString(6, txtPpepum.getText());
				
				if(rs.getString("p_uto") == null) {
					txtPuto.setText("");
				}else {
					txtPuto.setText(rs.getString("p_uto").substring(0, 10));
				}
				
				
				String o = ("상품명 : " + txtPirum.getText() + "\n" + "상품 번호 : " + txtPsangnum.getText() + 
						"\n" + "판매액 : " + txtPpanme.getText() + "\n" + "입고 날짜 : " + txtPipgo.getText() + 
						"\n" + "유통 기한 : " + txtPuto.getText() + "\n" + "폐기 현황 : " + txtPpepum.getText() + "\n");
				System.out.println(o);
				int re = JOptionPane.showConfirmDialog(Java_Sang.this,o + "정말로 종료 할까요?", "확인절차", JOptionPane.YES_NO_OPTION);
				if(re == JOptionPane.YES_OPTION) {
					pstmt.executeUpdate();
				}

			} catch (Exception e2) {
				System.out.println("상품 추가 오류 : " + e2);
			}

				
				
		}else if (e.getSource() == btnSg) {
			txtPirum.setText(null);
			txtPuto.setText(null);
			txtPpanme.setText(null);
			txtPpanme.setText(null);
			
			display();
			
		}else if (e.getSource() == btnClose) {
			try {
				int re = JOptionPane.showConfirmDialog(this, 
						"정말 종료할까요?","선택",JOptionPane.YES_NO_OPTION);
					if(re == JOptionPane.NO_OPTION) {
						return;
					}else {
						System.exit(0);
					}
					
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (stmt != null)
					conn.close();
			} catch (Exception e2) {
				System.out.println("상품 닫기 오류 : " + e);
			} finally {
			}
	}
	}
	public static void main(String[] args) { //메인
		Java_Sang javasang = new Java_Sang();
		
		JFrame frame=new JFrame("상품 등록");
		frame.getContentPane().add(javasang);
		frame.setBounds(440,110,430,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
