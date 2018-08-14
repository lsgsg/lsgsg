package kr.co.krc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

public class BookCustomer extends JPanel implements ActionListener{
	JTextField txtCbun,txtCirum,txtCjunhwa,txtCjuso,txtCdaesu;
	JTextArea taCmemo;
	JButton btnInsert,btnOk,btnUpdate,btnDel,btnFind,btnOption,btnClose;
	JButton btnF,btnP,btnN,btnL;
	JLabel lblRec;
	
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs1 ,rs2;
	String sql;
	
	
	int iTotal = 0;	// 자료의 갯수
	int iLast = 0; 	// 마지막 레코드 번호
	boolean isInsert = false;	// Insert 버튼 눌림 여부
	boolean isUpdate = false;	// Update 버튼 눌림 여부
	
	// 생성자
	public BookCustomer(){
		design();
		addListener();
		accDb();
		init();
		display();
	}
	
	private void init() {
		try {
			sql = "select * from customer order by c_bun asc";
			pstmt = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE
					);// 레코드 이동이 좌우로 가능함
			rs1 = pstmt.executeQuery();
			rs1.last();
			iTotal = rs1.getRow();
			//System.out.println("전체자료 수 : " + iTotal);
			iLast = rs1.getInt("c_bun");//마지막 고객번호 얻기 : 신규고객등록시 사용
			//rs1.first();
			rs1.last(); //추가 시킨 마지막 레코드가 보이게
			display();
			
			txtCirum.setEditable(false);
			txtCjunhwa.setEditable(false);
			txtCjuso.setEditable(false);
			btnInsert.setText("신규");
			btnOk.setEnabled(false);
			
			//pstmt.close();
			//rs1.close();
			
		} catch (Exception e) {
			System.out.println("init err : " + e);
		}

	}
	private void display() {

		try {
			txtCbun.setText(rs1.getString("c_bun"));
			txtCirum.setText(rs1.getString("c_irum"));
			txtCjunhwa.setText(rs1.getString("c_junhwa"));
			txtCjuso.setText(rs1.getString("c_juso"));
			txtCdaesu.setText(rs1.getString("c_daesu"));
			taCmemo.setText(rs1.getString("c_memo"));
			
			lblRec.setText(rs1.getRow() + "/" +  iTotal); //1 첫번째레코드를 읽음 두번째 세번째??
			
		} catch (Exception e) {
			System.out.println("display err : " + e);
		}
	}
	
	private void addListener() {
		
		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDel.addActionListener(this);
		btnFind.addActionListener(this);
		btnOption.addActionListener(this);
		btnF.addActionListener(this);
		btnP.addActionListener(this);
		btnN.addActionListener(this);
		btnL.addActionListener(this);
		btnOk.addActionListener(this);
		btnClose.addActionListener(this);
		
	}
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	 if(e.getSource()== btnInsert) {//신규 고객 등록
		 if(isInsert == false) {
			 btnInsert.setText("취소");
			 isInsert = true;
			 btnOk.setEnabled(true);
			 
			 
			 txtCirum.setEditable(true);
			 txtCjuso.setEditable(true);
			 txtCjunhwa.setEditable(true);
			 txtCbun.setText(String.valueOf(iLast + 1));//신규고객의 번호 등록
			 
			 txtCirum.setText("");
			 txtCjuso.setText("");
			 txtCjunhwa.setText("");
			 txtCdaesu.setText("");
			 taCmemo.setText("");
			 txtCirum.requestFocus();
			 
			 
		 }else {
			 btnInsert.setText("신규");
			 isInsert = false;
			 btnOk.setEnabled(false);
			 
			 txtCirum.setEditable(false);
			 txtCjuso.setEditable(false);
			 txtCjunhwa.setEditable(false);
			 
			 display();
		 }
	 }else if (e.getSource()== btnOk) { //신규 고객 등록 작업 
		 //입력자료 오류 검사 필요하나 생략
		 try {
			sql = " insert into customer values(?,?,?,?,0,'')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, txtCbun.getText());
			pstmt.setString(2, txtCirum.getText());
			pstmt.setString(3, txtCjunhwa.getText());
			pstmt.setString(4, txtCjuso.getText());
			
			pstmt.executeUpdate();
			
			init();//전체 건수 출력
			
		} catch (Exception e2) {
			System.out.println("신규 등록 오류 : " + e2);
		}
		 
	 }else if (e.getSource()== btnFind) {//검색
		 // 고객 번호 별 검색 // 다른건 생략;
		 String fBun = JOptionPane.showInputDialog(this, "검색할 고객번호 입력하라");
		 if(fBun == null || fBun.equals("")) return;
		 try {
			int currentRow3 = rs1.getRow(); // 검색결과가 없는 경우를 대비;
			rs1.beforeFirst();//첫번째 레코드 포인터
			int find = 0;
			while(rs1.next()) {
				String ss = rs1.getString("c_bun");
				if(fBun.equals(ss)) {
					display();
					find++;
					break;
				}
			}
			
			if(find == 0 ) {
				JOptionPane.showMessageDialog(this, "검색결과 없어서 못찾음");
				rs1.absolute(currentRow3);
			}
		} catch (Exception e2) {
			System.out.println("고객번호 검색 오류 : " + e2);
		}
	 }else if (e.getSource()== btnOption) {//옵션
		 //생략
	 }else if (e.getSource()== btnUpdate) {//수정
		if(isUpdate == false) {
			isUpdate = true;
			btnUpdate.setText("완료");
			
			 txtCirum.setEditable(true);//편집가능
			 txtCjuso.setEditable(true);
			 txtCjunhwa.setEditable(true);
			//수정작업시 부수 작업 필요하나 생략...
		}else {
			isUpdate = false;
			btnUpdate.setText("수정");
			
			//수정 처리
			
			try {
				sql = "update customer set c_irum = ? , c_junhwa = ? , c_juso = ? where c_bun = ?";
				
				//입력자료 검사 생략 
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, txtCirum.getText());
				pstmt.setString(2, txtCjunhwa.getText());
				pstmt.setString(3, txtCjuso.getText());
				pstmt.setString(4, txtCbun.getText());
				pstmt.executeUpdate();
				
				//현재 수정된 레코드 번호 기억하기 ;
				int currentRow = rs1.getRow();
				init();//수정된 자료 다시 보이기;
				rs1.absolute(currentRow);// currentRow의 지점으로 돌아간다.
				display();
			} catch (Exception e2) {
				System.out.println("고객 수정 오류 : " + e2);
			}
			 txtCirum.setEditable(false);//읽기만가능
			 txtCjuso.setEditable(false);
			 txtCjunhwa.setEditable(false);
			
		}
		 
	 }else if (e.getSource()== btnDel) {
		 int re = JOptionPane.showConfirmDialog(this, "정말 삭제할꺼임? " ,"삭제",JOptionPane.YES_NO_OPTION);
		 if(re == JOptionPane.YES_OPTION) {
			 try {
				 if(rs1.getRow() == 0 ) {
					 JOptionPane.showMessageDialog(this, "삭제할 고객이 없다.");
					 return;
				 }
				 
				 // 현재 도서를 대여 중인 고객은 삭제할 수 없음;
				 
				 if(taCmemo.getText().equals("")) {
					 int currentRow2 = rs1.getRow();
					 
					 sql = "delete from customer where c_bun = ? ";
					 pstmt=conn.prepareStatement(sql);
					 pstmt.setString(1, txtCbun.getText());
					 pstmt.executeUpdate();
					 
					 init();//삭제 후 자료 다시 읽기; 
					 if(currentRow2 == 1 ) {
						 
					 }else {
						 rs1.absolute(currentRow2-1);
					 }
					 display();
				 }else {
					 JOptionPane.showMessageDialog(this, "삭제 불가능 : 도서 반납 후 삭제하라");
				 }
				sql = " delete ";
			} catch (Exception e2) {
				System.out.println("고객삭제오류 : " + e2);
			}
		 }
	 }else if (e.getSource()== btnClose) {//닫기
		 try {
			if(rs1 != null) rs1.close();
			if(rs2 != null) rs2.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		} catch (Exception e2) {
			System.out.println("대여 닫기 오류 " + e2);
		} finally {
			BookMain.book_customer.setEnabled(true); // bookMain 화면의 대여메뉴를 활성화 시킴
			BookMain.childWinCustomer.dispose(); // 메인화면에 있는 대여창 닫기
		}
	 }
	 
	 try {
		 if (e.getSource()== btnF) {
				
				rs1.first();
				display();
		
			 
		 }else if (e.getSource()== btnP) {
			 if(rs1.isFirst()) return;
			 	rs1.previous();
				display();
		 }else if (e.getSource()== btnN) {
			 	if(rs1.isLast()) return;
			 	rs1.next();
				display();
		 }else if (e.getSource()== btnL) {
			 	rs1.last();
				display();
		 }
	} catch (Exception e2) {
		
	}
	 
		
	}
	
	
	public void design(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//고객정보 패널========================
		JPanel customerPn=new JPanel(new GridLayout(4,1));
		customerPn.setBorder(new TitledBorder(new TitledBorder("고객 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		JPanel cPn1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cPn4=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		txtCbun=new JTextField("",5);
		txtCirum=new JTextField("",10);
		txtCjunhwa=new JTextField("",15);
		txtCjuso=new JTextField("",28);
		txtCdaesu=new JTextField("",5);
		taCmemo=new JTextArea(2,28);
		JScrollPane scroll=new JScrollPane(taCmemo);
		taCmemo.setBackground(Color.lightGray);
		
		txtCbun.setEditable(false);
		txtCirum.setEditable(false);
		txtCjunhwa.setEditable(false);
		txtCjuso.setEditable(false);
		txtCdaesu.setEditable(false);
		taCmemo.setEditable(false);
		
		btnInsert=new JButton("신규");
		btnOk=new JButton("확인");
		btnUpdate=new JButton("수정");
		btnDel=new JButton("삭제");
		btnFind=new JButton("검색");
		btnOption=new JButton("옵션");
		btnClose=new JButton("닫기");
		btnF=new JButton(" <<= ");
		btnP=new JButton("  <= ");
		btnN=new JButton(" =>  ");
		btnL=new JButton(" =>> ");
		lblRec=new JLabel(" 0 / 0 ",JLabel.CENTER);
		cPn1.add(new JLabel("번호 :"));
		cPn1.add(txtCbun);
		cPn1.add(new JLabel("       이름 :"));
		cPn1.add(txtCirum);
		
		cPn2.add(new JLabel("전화 :"));
		cPn2.add(txtCjunhwa);	
		cPn2.add(new JLabel("      대여횟수 :"));
		cPn2.add(txtCdaesu);
		
		cPn3.add(new JLabel("주소 :"));
		cPn3.add(txtCjuso);
		
		cPn4.add(new JLabel("메모 :"));
		cPn4.add(scroll);

		customerPn.add(cPn1);  customerPn.add(cPn2); 	customerPn.add(cPn3);	customerPn.add(cPn4);
		this.add(customerPn);
		
		btnOk.setEnabled(false);
		
		//레코드 이동 패널========================
		JPanel movePn=new JPanel();
		movePn.setBorder(new TitledBorder(new TitledBorder("레코드 이동"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		movePn.add(btnF);
		movePn.add(btnP);
		movePn.add(lblRec);
		movePn.add(btnN);
		movePn.add(btnL);
		
		this.add(movePn);
		
        //명령 버튼 패널========================
		JPanel bottomPn1=new JPanel();
		bottomPn1.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		bottomPn1.add(btnInsert);
		bottomPn1.add(btnOk);
		JLabel lbl1=new JLabel("    "); 
		bottomPn1.add(lbl1);
		bottomPn1.add(btnUpdate);
		bottomPn1.add(btnDel);
		
		JPanel bottomPn2=new JPanel();
		bottomPn2.add(btnFind);
		bottomPn2.add(btnOption);
		JLabel lbl2=new JLabel("                          "); 
		bottomPn2.add(lbl2);
		bottomPn2.add(btnClose);
		
		this.add(bottomPn1);
		this.add(bottomPn2);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}

	public static void main(String[] args) {
		BookCustomer bookCustomer=new BookCustomer();
		JFrame frame=new JFrame("고객 창");
		frame.getContentPane().add(bookCustomer);
		frame.setBounds(200,200,430,450);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}