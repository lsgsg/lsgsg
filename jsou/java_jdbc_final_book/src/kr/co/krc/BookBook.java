package kr.co.krc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class BookBook extends JPanel implements ActionListener{
	JTextField txtBbun,txtBjemok,txtBjang,txtBkuil,txtBdaesu,txtBdaeyn,txtBdaebun,txtBdaeil,txtBbanil;
	JTextArea taBmemo;
	JButton btnInsert,btnUpdate,btnDel,btnFind,btnOption,btnClose;
	JButton btnF,btnP,btnN,btnL, btnsu;
	JButton btnin;
	JLabel lblRec,lblPic;
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs1 ,rs2;
	String sql2;

	String sql, imgPath; //--- 그림 파일 경로 변수
	int iTotal = 0;	// 자료의 갯수
	int iLast = 0; 	// 마지막 레코드 번호
	JPanel picPn;
	File file;  //--- 이미지 로딩하기 위한 변수
	boolean isInsert = false;	// Insert 버튼 눌림 여부
	boolean isUpdate = false;	// Update 버튼 눌림 여부
	
	public BookBook(){
		design();
		addListener();
		accDb();
		
		init();
		display();
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
		//btnin.addActionListener(this);
		btnsu.addActionListener(this);
		btnClose.addActionListener(this);
	}
	
	private void accDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott"	, "tiger");
		} catch (Exception e) {
			System.out.println("book accDb 실패:" + e);
		}
	}
	
	private void init() {
		try {
			sql = "select * from book order by b_bun asc";
			pstmt = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE
					);// 레코드 이동이 좌우로 가능함
			rs1 = pstmt.executeQuery();
			rs1.last();
			iTotal = rs1.getRow();
			iLast = rs1.getInt("b_bun");//마지막 도서번호 얻기 : 신규도서 등록시 사용
			rs1.first();

			
		} catch (Exception e) {
			System.out.println("book init err : " + e);
		}


	}
	
	private void display() {
		
		try {
			txtBbun.setText(rs1.getString("b_bun"));
			txtBdaeyn.setText(rs1.getString("b_daeyn"));
			if(rs1.getString("b_daeyn").equals("y")) {
				txtBdaeyn.setText("대여중");
				txtBdaeyn.setForeground(Color.RED);
			}else {
				txtBdaeyn.setText("비치중");
				txtBdaeyn.setForeground(Color.BLACK);
			}
			txtBjemok.setText(rs1.getString("b_jemok"));
			txtBjang.setText(rs1.getString("b_jang"));
			txtBdaesu.setText(rs1.getString("b_daesu"));
			txtBdaebun.setText(rs1.getString("b_daebun"));
			if(rs1.getString("b_kuipil")==null) {
				txtBkuil.setText("");
			}else {
				txtBkuil.setText(rs1.getString("b_kuipil").substring(0, 10));
			}
			
			if(rs1.getString("b_daeil")==null) {
				txtBdaeil.setText("");
			}else {
				txtBdaeil.setText(rs1.getString("b_daeil").substring(0, 10));
			}
			
			if(rs1.getString("b_banil")==null) {
				txtBbanil.setText("");
			}else {
				
				txtBbanil.setText(rs1.getString("b_banil").substring(0, 10));
			}
			
			
			taBmemo.setText(rs1.getString("b_memo"));
			
			lblRec.setText(rs1.getRow()+"/"+ iTotal);
			
			//이미지 출력
			imgPath = rs1.getString("b_image");
			dispImg();
		} catch (Exception e) {
			System.out.println("book display err : " + e);
		}
	}
	
	private void dispImg() {
		if(imgPath != null) {
			String dir = "C:\\work\\jsou\\java_jdbc_final_book\\src\\bookimage\\";
			//System.out.println(dir + imgPath);
			ImageIcon icon = new ImageIcon(dir + imgPath);
			lblPic.setIcon(icon);
			lblPic.setToolTipText("이미지 파일명 : " + imgPath);//이미지에 마우스 올려면 파일명 나옴;
			if(icon != null) lblPic.setText(null);
			else lblPic.setText("이미지 없음");
		}
	} 
	@Override
	public void actionPerformed(ActionEvent e) {
	 if(e.getSource()== btnInsert) {
		 if(isInsert== false) {
			 btnInsert.setText("확인");
			 isInsert = true;
			 
			 txtBbun.setText(iLast + 1 + "");
			 txtBjemok.setEditable(true);
			 txtBjang.setEditable(true);
			 txtBkuil.setEditable(true);
			 taBmemo.setEditable(true);
			 
			 txtBjemok.setText(null);
			 txtBjang.setText(null);
			
			 taBmemo.setText(null);
			 
			 taBmemo.setBackground(Color.white);
			 
			 imgPath = null; //도서 이미지 경로 초기화;
			 
			 Calendar calendar = Calendar.getInstance();
			 String imsi = calendar.get(Calendar.YEAR)+"-"+
					 	   (calendar.get(Calendar.MONTH)+ 1)+"-"+
					 	   calendar.get(Calendar.DATE);
			 
			 txtBkuil.setText(imsi);
			 txtBdaeyn.setText("n");
			 txtBdaesu.setText("0");
			 txtBdaebun.setText("");
			 txtBdaeil.setText("");
			 txtBbanil.setText("");
			 
			 //이미지를 이미지 추가버튼으로 대체 
			 picPn.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
			 btnin = new JButton("그림삽입");
			 btnin.addActionListener(this);
			 lblPic.setVisible(false);
			 picPn.add("Center",btnin);
			 txtBjemok.requestFocus();
		 }else {
			 btnInsert.setText("신규");
			 isInsert = false;
			 
			 //신규도서 등록작업 실행; 
			 try {
				sql = "insert into book values(?,?,?,?,0,'n',0,null,null,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, txtBbun.getText());
				pstmt.setString(2, txtBjemok.getText());
				pstmt.setString(3, txtBjang.getText());
				pstmt.setString(4, txtBkuil.getText());
				pstmt.setString(5, taBmemo.getText());
				if(file.getName() == null || file.getName().equals("")) {
					pstmt.setString(6, null);
				}else {
					pstmt.setString(6, file.getName());
				}
				
				pstmt.executeUpdate();
				
				txtBjemok.setEditable(false);
				txtBjang.setEditable(false);
				txtBkuil.setEditable(false);
				taBmemo.setEditable(false);
				txtBjemok.setEditable(false);
				taBmemo.setBackground(Color.LIGHT_GRAY);
				
				init();
				rs1.last();
				display();
			} catch (Exception e2) {
				System.out.println("도서 추가 오류 : " + e2);
			}
		 }
	 } else if(e.getSource()== btnin) {//이미지 삽입
		JFileChooser chooser = new JFileChooser("C:\\work\\jsou\\java_jdbc_final_book\\src\\bookimage");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = chooser.showOpenDialog(this);
		
		if(result == JFileChooser.CANCEL_OPTION) {file=null;}
		else {file = chooser.getSelectedFile();}
		
		//System.out.println(file.getName());
		
		
		imgPath = file.getName();
		
		dispImg();
		lblPic.setVisible(true);
		btnin.setVisible(false);
	 } else if (e.getSource()== btnDel) {

		 int re = JOptionPane.showConfirmDialog(this, "정말 삭제할꺼임? " ,"삭제",JOptionPane.YES_NO_OPTION);
		 if(re == JOptionPane.YES_OPTION) {
			 try {
				 if(rs1.getRow() == 0 ) {
					 JOptionPane.showMessageDialog(this, "삭제할 고객이 없다.");
					 return;
				 }
				 
				 // 현재 도서를 대여 중인 도서는 삭제할 수 없음;
				 
				 if(txtBdaeyn.getText().equals("비치중")) {
					 int currentRow2 = rs1.getRow();
					 
					 sql = "delete from book where b_bun = ? ";
					 pstmt=conn.prepareStatement(sql);
					 pstmt.setString(1, txtBbun.getText());
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
				
			} catch (Exception e2) {
				System.out.println("고객삭제오류 : " + e2);
			}
		 }
	 
	 }else if (e.getSource()== btnFind) {
		//검색
		 //도서명 별 검색 // 다른건 생략;
		 String fjemok = JOptionPane.showInputDialog(this, "검색할 도서명 입력하라");
		 if(fjemok == null || fjemok.equals("")) return;
		 try {
			int currentRow3 = rs1.getRow(); // 검색결과가 없는 경우를 대비;
			rs1.beforeFirst();//첫번째 레코드 포인터
			boolean find = false;
			while(rs1.next()) {
				String ss = rs1.getString("b_jemok");
				if(fjemok.equals(ss)) {
					display();
					find = true;
					break;
				}
			}
			
			if(find == false ) {
				JOptionPane.showMessageDialog(this, "검색결과 없어서 못찾음");
				rs1.absolute(currentRow3);
			}
		} catch (Exception e2) {
			System.out.println("제목 검색 오류 : " + e2);
		}
	 
	 }else if (e.getSource()== btnOption) {
		 
	 }else if (e.getSource()== btnUpdate) {
		 //제목, 장르 ,구입일, 메모
		 
		 if(isUpdate == false) {
				isUpdate = true;
				btnUpdate.setText("완료");
				
				 txtBjemok.setEditable(true);//편집가능
				 txtBjang.setEditable(true);
				 txtBkuil.setEditable(true);
				 taBmemo.setEditable(true);
				//수정작업시 부수 작업 필요하나 생략...
			}else {
				isUpdate = false;
				btnUpdate.setText("수정");
				
				//수정 처리
				
				try {
					sql = "update book set b_jemok = ? , b_jang = ? , b_memo = ? where b_bun = ?";
					
					//입력자료 검사 생략 
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, txtBjemok.getText());
					pstmt.setString(2, txtBjang.getText());
					//pstmt.setString(3, txtBkuil.getText());
					pstmt.setString(3, taBmemo.getText());
					pstmt.setString(4, txtBbun.getText());
					pstmt.executeUpdate();
					
					//현재 수정된 레코드 번호 기억하기 ;
					int currentRow = rs1.getRow();
					init();//수정된 자료 다시 보이기;
					rs1.absolute(currentRow);// currentRow의 지점으로 돌아간다.
					display();
				} catch (Exception e2) {
					System.out.println("도서 수정 오류 : " + e2);
				}
				 txtBjemok.setEditable(false);//읽기만가능
				 txtBjang.setEditable(false);
				 txtBkuil.setEditable(false);
				 taBmemo.setEditable(false);
				
			}
	 }else if (e.getSource()== btnsu) {
		 
	 }else if (e.getSource()== btnClose) {//닫기
		 try {
			if(rs1 != null) rs1.close();
			if(rs2 != null) rs2.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		} catch (Exception e2) {
			System.out.println("대여 닫기 오류 " + e2);
		} finally {
			BookMain.book_book.setEnabled(true); // bookMain 화면의 대여메뉴를 활성화 시킴
			BookMain.childWinBook.dispose(); // 메인화면에 있는 대여창 닫기
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
		System.out.println("포인터 이동 에러 : " + e2);
	}
	 
	 
	}
	
	public void design(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		//도서정보 패널========================
		JPanel bookPn=new JPanel(new GridLayout(6,1));
		bookPn.setBorder(new TitledBorder(new TitledBorder("도서 정보"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		JPanel bPn1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn4=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn5=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bPn6=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		txtBbun=new JTextField("",5);
		txtBjemok=new JTextField("",30);
		txtBjang=new JTextField("",10);
		txtBkuil=new JTextField("",10);
		txtBdaesu=new JTextField("",5);
		txtBdaeyn=new JTextField("",5);
		txtBdaebun=new JTextField("",5);
		txtBdaeil=new JTextField("",10);
		txtBbanil=new JTextField("",10);
		taBmemo=new JTextArea(2,30);
		JScrollPane scroll=new JScrollPane(taBmemo);
		taBmemo.setBackground(Color.lightGray);
		
		txtBbun.setEditable(false);
		txtBjemok.setEditable(false);
		txtBjang.setEditable(false);
		txtBkuil.setEditable(false);
		txtBdaesu.setEditable(false);
		txtBdaeyn.setEditable(false);
		txtBdaebun.setEditable(false);
		txtBdaeil.setEditable(false);
		txtBbanil.setEditable(false);
		taBmemo.setEditable(false);
				
		btnInsert=new JButton("신규");
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
		bPn1.add(new JLabel("번호 :"));
		bPn1.add(txtBbun);
		bPn1.add(new JLabel("                              대여표시 :"));
		bPn1.add(txtBdaeyn);
		
		bPn2.add(new JLabel("제목 :"));
		bPn2.add(txtBjemok);	

		bPn3.add(new JLabel("장르 :"));
		bPn3.add(txtBjang);
		bPn3.add(new JLabel("                구입일 :"));
		bPn3.add(txtBkuil);
		
		bPn4.add(new JLabel("대여횟수 :"));
		bPn4.add(txtBdaesu);
		bPn4.add(new JLabel("                 대여자번호 :"));
		bPn4.add(txtBdaebun);
		
		bPn5.add(new JLabel("대여일 :"));
		bPn5.add(txtBdaeil);
		bPn5.add(new JLabel("            반납일 :"));
		bPn5.add(txtBbanil);

		bPn6.add(new JLabel("메모 :"));
		bPn6.add(scroll);
		
		bookPn.add(bPn1);  bookPn.add(bPn2); 	bookPn.add(bPn3);
		bookPn.add(bPn4);  bookPn.add(bPn5); 	bookPn.add(bPn6);
		this.add(bookPn);
		
		//이미지 패널----------------------------------------------------
		picPn=new JPanel(new BorderLayout());
		lblPic = new JLabel();
		//lblPic.setPreferredSize(new Dimension(1000, 300));
		picPn.add(lblPic);

		this.add(picPn);

		//레코드 이동 패널------------------------------------------------
		JPanel movePn=new JPanel();
		movePn.setBorder(new TitledBorder(new TitledBorder("레코드 이동"), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.LEFT));
		movePn.add(btnF);
		movePn.add(btnP);
		movePn.add(lblRec);
		movePn.add(btnN);
		movePn.add(btnL);
		
		this.add(movePn);
		
        //명령 버튼 패널--------------------------------------------------
		JPanel bottomPn1=new JPanel();
		bottomPn1.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		bottomPn1.add(btnInsert);
		bottomPn1.add(btnUpdate);
		bottomPn1.add(btnDel);
		bottomPn1.add(btnFind);
		bottomPn1.add(btnOption);
		bottomPn1.add(btnClose);
		
		this.add(bottomPn1);
		
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		btnsu=new JButton("그림 수정");
		btnin=new JButton("");

	}
	

	public static void main(String[] args){
		BookBook bookBook = new BookBook();
		
		JFrame frame=new JFrame("도서 창");
		frame.getContentPane().add(bookBook);
		frame.setBounds(440,110,430,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}






/*
 * 
 * 		//setLayout(new BoxLayout(Java_sale.this, BoxLayout.Y_AXIS));
		//setLayout(new GridLayout(5, 5));
		setLayout(new FlowLayout());
		//JPanel outPn = new JPanel(new FlowLayout());
		JPanel pPn1 = new JPanel();
		pPn1.setSize(10, 470);
		pPn1.setLocation(10, 10);
		
		btnOk = new JButton("상품확인");

		addSang1 = new JTextField("", 10);

		pPn1.add(new JLabel("상품번호 입력 : "));
		pPn1.add(addSang1);
		pPn1.add(btnOk);

		add(pPn1);

		// 확인 테이블 -------------------------------------
		JPanel pPn2 = new JPanel();
		pPn2.setLayout(new GridLayout(7, 1));
		pPn2.setSize(470,200);
		pPn2.setLocation(10, 110);
		okSang1 = new JTextField("", 10);
		okSang2 = new JTextField("", 10);
		okSang3 = new JTextField("", 10);
		okSang4 = new JTextField("", 10);
		okSang5 = new JTextField("", 10);
		okSang6 = new JTextField("", 10);
		okSang7 = new JTextField("", 10);
		pPn2.add(new JLabel("상품명 : "));
		pPn2.add(okSang1);
		pPn2.add(new JLabel("행사 : "));
		pPn2.add(okSang2);
		pPn2.add(new JLabel("입고날짜 : "));
		pPn2.add(okSang3);
		pPn2.add(new JLabel("유통기한 : "));
		pPn2.add(okSang4);
		pPn2.add(new JLabel("가격 : "));
		pPn2.add(okSang5);
		pPn2.add(new JLabel("폐기상품 : "));
		pPn2.add(okSang6);
		pPn2.add(new JLabel("영수증번호 : "));
		pPn2.add(okSang7);
		
		
		
		
		
		add(pPn2);
		
		
		JPanel btnplus = new JPanel();
		btnplus.setSize(470,200);
		btnplus.setLocation(10, 300);
		
		btnplus .setLayout(new GridLayout(1, 2));
		btnAdd = new JButton("물품 추가");
		btnPegi = new JButton("폐기상품관리");
		btnplus.add(btnAdd);
		btnplus.add(btnPegi);
		add(btnplus);
		
		JPanel pPn3 = new JPanel();
		JScrollPane pane = new JScrollPane(billArea);
		billArea.setEditable(false);
		add(pane);
		
		gogekCf = new JButton("고객정보확인");
		gogekY = new JRadioButton("Yes");
		gogekN = new JRadioButton("No");
		JLabel lblgobun = new JLabel("고객번호 : ");
		gogekNum = new JTextField("",5);
		gogekNum.setEditable(false);
		JPanel pPn4 = new JPanel();
		JPanel pPn5 = new JPanel();
		pPn4.add(gogekCf);
		pPn4.add(gogekY);
		pPn4.add(gogekN);
		pPn5.add(lblgobun);
		pPn5.add(gogekNum);
		add(pPn4);
		add(pPn5);
		
		//JButton toTal,pPoint,bill;
		//JTextField totF,pointF;
		
		JPanel pPn6 = new JPanel();
		JPanel pPn7 = new JPanel();
		JPanel pPn8 = new JPanel();
		JLabel lblTot = new JLabel("총 금액 : ");
		JLabel lblpPint = new JLabel("예상적립액 : ");
		totF = new JTextField("",10);
		pointF = new JTextField("",10);
		bill = new JButton("계산");
		btnNew = new JButton("새로고침");
		bClose = new JButton("취소");
		pPn6.add(lblTot);
		pPn6.add(totF);
		pPn7.add(lblpPint);
		pPn7.add(pointF);
		pPn8.add(bill);
		pPn8.add(btnNew);
		pPn8.add(bClose);
		add(pPn6);
		add(pPn7);
		add(pPn8);
		//pPn1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		//pPn2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		//pPn3.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		//this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));*/
