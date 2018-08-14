package kr.co.krc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.*;

public class BookDaeyeo extends JPanel implements ActionListener {
	JTextField txtCbun,txtCirum,txtCjunhwa,txtCjuso,txtBbun,txtBjemok,txtBdaeil;
	JTextArea taCmemo;
	JButton btnCbun,btnCirum,btnCjunhwa,btnBbun,btnBjemok,btnDaeyeo,btnNew,btnClose;
	
	boolean bg=false, bv=false;  //대여확인 버튼 활성화 여부
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs1 ,rs2;
	String sql;
	
	public BookDaeyeo(){
		design();
		addListener();
		accDb();
	}
	
	private void addListener() {
		btnCbun.addActionListener(this);
		btnCirum.addActionListener(this);
		btnCjunhwa.addActionListener(this);
		btnBbun.addActionListener(this);
		btnBjemok.addActionListener(this);
		btnDaeyeo.addActionListener(this);
		btnNew.addActionListener(this);
		btnClose.addActionListener(this);
	}
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	 if(e.getSource()== btnCbun) {
		
		 //actioncommand일때는 꼭 equals쓸것
		 if(txtCbun.getText().equals("")) {
			 txtCbun.requestFocus();
			 JOptionPane.showMessageDialog(this, "고객번호를 입력하세요");
			 return;
		 }
		 sql = "select * from customer where c_bun = ?";
		 processCustomer(txtCbun.getText().trim());
		 
	 }else if (e.getSource()== btnCirum) {
		 if(txtCirum.getText().equals("")) {
			 txtCirum.requestFocus();
			 JOptionPane.showMessageDialog(this, "고객번호를 입력하세요");
			 return;
		 }
		 sql = "select * from customer where c_irum = ?";
		 processCustomer(txtCirum.getText().trim());
	 }else if (e.getSource()== btnCjunhwa) {
		 if(txtCjunhwa.getText().equals("")) {
			 txtCjunhwa.requestFocus();
			 JOptionPane.showMessageDialog(this, "고객번호를 입력하세요");
			 
			 return;
		 }
		 sql = "select * from customer where c_junhwa = ?";
		 
		 processCustomer(txtCjunhwa.getText().trim());
	 }else if (e.getSource()== btnBbun) {
		 if(txtBbun.getText().equals("")) {
			 txtBbun.requestFocus();
			 JOptionPane.showMessageDialog(this, "고객번호를 입력하세요");
			 
			 return;
		 }
		 sql = "select * from book where b_bun = ?";
		 
		 processBook(txtBbun.getText().trim());
	 }else if (e.getSource()== btnBjemok) {
		 if(txtBjemok.getText().equals("")) {
			 txtBjemok.requestFocus();
			 JOptionPane.showMessageDialog(this, "고객번호를 입력하세요");
			 
			 return;
		 }
		 sql = "select * from book where b_jemok = ?";
		 
		 processBook(txtBjemok.getText().trim());
	 }else if (e.getSource()== btnDaeyeo) {//대여확인버튼을 누르면 
		 //도서 제목을 메모란에 표시 후 , 대여 수정에 참여.
		 if(taCmemo.getText().equals("")) {//첫번째 책빌림
			 taCmemo.setText(txtBjemok.getText().trim());
		 }else { // 이미 대여도서가 있는 상태;
			 taCmemo.setText(taCmemo.getText() + "," + txtBjemok.getText().trim());
		 }
		 
		 try {//닫기
			 conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
			 //Customer update
			 sql = "update customer set c_daesu = c_daesu + 1 ,c_memo = ? where c_bun = ?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, taCmemo.getText());
			 pstmt.setString(2, txtCbun.getText());
			 pstmt.executeUpdate();
			 
			 bg = false;
			 //Book update //customer과 book이 동시에 업데이트되어야함 모두멀쩡하면 commit 하나라도 오류가 있으면 rollback; ->트랜잭션;
			 sql = "update book set b_daesu = b_daesu + 1, b_daeyn = 'y', b_daebun = ?, b_daeil = ? where b_bun = ? ";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, txtCbun.getText());
			 pstmt.setString(2, txtBdaeil.getText());
			 pstmt.setString(3, txtBbun.getText());
			 pstmt.executeUpdate();
			 
			 bv = false;
			 
			 btnDaeyeo.setEnabled(false);
			 btnNew.setEnabled(true);
			 
		} catch (Exception e2) {
			System.out.println("업데이트 오류 : " + e2);
		}finally {
			 try {
					if(rs1 != null) rs1.close();
					if(rs2 != null) rs2.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
					
				} catch (Exception e2) {
					System.out.println("대여 갱신 오류 " + e2);
				}
		}
	 }else if (e.getSource()== btnNew) {
		 txtCbun.setText(null);
		 txtCirum.setText(null);
		 txtCjunhwa.setText(null);
		 txtCjuso.setText(null);
		 taCmemo.setText(null);
		 
		 txtBbun.setText(null);
		 txtBjemok.setText(null);
		 txtBdaeil.setText(null);
		 
		 btnNew.setEnabled(false);
		 
	 }else if (e.getSource()== btnClose) {//닫기
		 try {
			if(rs1 != null) rs1.close();
			if(rs2 != null) rs2.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		} catch (Exception e2) {
			System.out.println("대여 닫기 오류 " + e2);
		} finally {
			BookMain.book_dae.setEnabled(true); // bookMain 화면의 대여메뉴를 활성화 시킴
			BookMain.childWinDae.dispose(); // 메인화면에 있는 대여창 닫기
		}
	 }
		
	}
	
	private void processCustomer(String fData) {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fData);
			
			rs1 = pstmt.executeQuery();
			
			if(rs1.next()) {
				txtCbun.setText(rs1.getString("c_bun"));
				txtCirum.setText(rs1.getString("c_irum"));
				txtCjunhwa.setText(rs1.getString("c_junhwa"));
				txtCjuso.setText(rs1.getString("c_juso"));
				taCmemo.setText(rs1.getString("c_memo"));
				bg = true; //고객만족?
				btnDaeyeo.setEnabled(bg && bv);//둘다 만족하면  true;
			} else {
				JOptionPane.showMessageDialog(this, "등록된 자료가 아닙니다.");
				txtCbun.setText(null);
				txtCirum.setText(null);
				txtCjunhwa.setText(null);
				txtCjuso.setText(null);
				taCmemo.setText(null);
				bg = false;
				btnDaeyeo.setEnabled(false);
			}
		} catch (Exception e2) {
			System.out.println("processCustomer err : " + e2);
		}finally {
			try {
				if(rs1 != null) rs1.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				//if(rs1 != null) rs1.close();
			} catch (Exception e3) {
				// TODO: handle exception
			}
		}
	}
	
	private void processBook(String fData) {

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fData);
			
			rs2 = pstmt.executeQuery();
			
			if(rs2.next()) {
				txtBbun.setText(rs2.getString("b_bun"));
				txtBjemok.setText(rs2.getString("b_jemok"));
				
				
				//대여 가능 여부 판단 ------ 
				
				if(rs2.getString("b_daeyn").equals("y")) {
					JOptionPane.showMessageDialog(this, "현재 대여중인 도서입니다.");
					txtBbun.setText(null);
					txtBjemok.setText(null);
					return;
				}
				
				//--------------------------
				
				bv= true; //도서만족?
				btnDaeyeo.setEnabled(bg && bv);//둘다 만족하면  true;
				
				// 대여일
				Date today = new Date();
				SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/dd");
				String nalja = format.format(today);
				//System.out.println(nalja);
				txtBdaeil.setText(nalja);
				
				
				
				
			} else {
				JOptionPane.showMessageDialog(this, "등록된 자료가 아닙니다.");
				txtBbun.setText(null);
				txtBjemok.setText(null);

				bv = false;
				btnDaeyeo.setEnabled(false);
				
				
			}
		} catch (Exception e2) {
			System.out.println("processCustomer err : " + e2);
		}finally {
			try {
				if(rs1 != null) rs1.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				//if(rs1 != null) rs1.close();
			} catch (Exception e3) {
				// TODO: handle exception
			}
		}
	
	}
	
	public void design(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//고객정보 패널========================
		JPanel customerPn=new JPanel(new GridLayout(3,2));
		customerPn.setBorder(new TitledBorder(new TitledBorder("고객 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		JPanel cPn1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn4=new JPanel(new FlowLayout(FlowLayout.LEFT));

		txtCbun=new JTextField("",5);
		txtCirum=new JTextField("",10);
		txtCjunhwa=new JTextField("",10);
		txtCjuso=new JTextField("",20);
		txtCjuso.setEditable(false);
		
		taCmemo=new JTextArea(2,20);
		JScrollPane scroll=new JScrollPane(taCmemo,	ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		taCmemo.setEditable(false);
		taCmemo.setBackground(Color.lightGray);
		
		btnCbun=new JButton("확인1");
		btnCbun.setMargin(new Insets(0, 3, 0, 3));
		btnCirum=new JButton("확인2");
		btnCirum.setMargin(new Insets(0, 3, 0, 3));
		btnCjunhwa=new JButton("확인3");
		btnCjunhwa.setMargin(new Insets(0, 3, 0, 3));

		cPn1.add(new JLabel("번호:"));
		cPn1.add(txtCbun);
		cPn1.add(btnCbun);
		
		cPn2.add(new JLabel("이름:"));
		cPn2.add(txtCirum);	
		cPn2.add(btnCirum);
		
		cPn3.add(new JLabel("전화:"));
		cPn3.add(txtCjunhwa);
		cPn3.add(btnCjunhwa);
		
		cPn4.add(new JLabel("주소:"));
		cPn4.add(txtCjuso);
		
		customerPn.add(cPn1); customerPn.add(cPn2); customerPn.add(cPn3);
		customerPn.add(cPn4); 
		customerPn.add(new JLabel("고객이 대여한 도서  ☞   ",JLabel.RIGHT));
		customerPn.add(scroll); 
		scroll.setBorder(BorderFactory.createEmptyBorder(1, 1, 5, 5));
		this.add(customerPn);
				
		//도서정보 패널========================
		JPanel bookPn=new JPanel(new GridLayout(3,1));
		bookPn.setBorder(new TitledBorder(new TitledBorder("도서 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		
		JPanel bPn1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		txtBbun=new JTextField("",5);
		txtBjemok=new JTextField("",20);
		txtBdaeil=new JTextField("",10);
		btnBbun=new JButton("확인1");
		btnBbun.setMargin(new Insets(0, 3, 0, 3));
		btnBjemok=new JButton("확인2");
		btnBjemok.setMargin(new Insets(0, 3, 0, 3));

		bPn1.add(new JLabel("번호:"));
		bPn1.add(txtBbun);
		bPn1.add(btnBbun);
		
		bPn2.add(new JLabel("제목:"));
		bPn2.add(txtBjemok);
		bPn2.add(btnBjemok);
		
		bPn3.add(new JLabel("대여일:"));
		bPn3.add(txtBdaeil);	
		
		JPanel bottomPn=new JPanel();
		bottomPn.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		btnDaeyeo=new JButton("대여 확인");
		btnNew=new JButton("새로 입력");
		btnClose=new JButton(" 닫 기 ");
		
		bottomPn.add(btnDaeyeo);
		bottomPn.add(btnNew);
		JLabel lbl=new JLabel("    ");  //버튼 사이에 공백 부여
		bottomPn.add(lbl);
		bottomPn.add(btnClose);
		
		bookPn.add(bPn1);  bookPn.add(bPn2); 	bookPn.add(bPn3);	
		
		this.add(customerPn);
		this.add(bookPn);
		this.add(bottomPn);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		btnDaeyeo.setEnabled(false); //대여 버튼 비활성화
		btnNew.setEnabled(false);
	}

	public static void main(String[] args) {
		BookDaeyeo daeyeo=new BookDaeyeo();
		JFrame frame=new JFrame("대여 창");
		frame.getContentPane().add(daeyeo);
		//frame.setResizable(false);
		frame.setBounds(200,200,580,400);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
